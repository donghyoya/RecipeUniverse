package com.recipe.universe.domain.user.role.repository.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class RoleCacheRepository {
    private final RedisTemplate<String, UserRoleCO> redisTemplate;

    public void save(Long userId, List<String> roles){
        UserRoleCO cacheObject = new UserRoleCO(roles);
        redisTemplate.opsForValue().set(userId.toString(), cacheObject);
    }

    public List<String> findByUserId(Long userId) {
        UserRoleCO cacheObject = (UserRoleCO) redisTemplate.opsForValue().get(userId.toString());
        if(cacheObject != null){
            return cacheObject.getRole();
        }else {
            return new ArrayList<>();
        }
    }
}

