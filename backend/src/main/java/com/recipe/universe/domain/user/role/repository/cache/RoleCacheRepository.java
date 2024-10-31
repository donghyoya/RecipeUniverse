package com.recipe.universe.domain.user.role.repository.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class RoleCacheRepository {
    private static String REDIS_KEY_PREFIX = "user:key:%d";
    private final RedisTemplate<String, UserRoleCO> redisTemplate;

    public void save(Long userId, List<String> roles){
        UserRoleCO cacheObject = new UserRoleCO(roles);
        redisTemplate.opsForValue().set(getKey(userId), cacheObject);
    }

    public List<String> findByUserId(Long userId) {
        UserRoleCO cacheObject = redisTemplate.opsForValue().get(getKey(userId));
        if(cacheObject != null){
            return cacheObject.getRoles();
        }else {
            return new ArrayList<>();
        }
    }

    /**
     * UserRole에 대한 변화가 있는 경우 캐시를 무효화할 것 (캐시 갱신보다 무효화전략이 보다 안전하다고 판단함)
     * @param userId
     */
    public void invalidation(Long userId){
        redisTemplate.delete(getKey(userId));
    }

    private String getKey(Long userId){
        return String.format(REDIS_KEY_PREFIX, userId);
    }
}

