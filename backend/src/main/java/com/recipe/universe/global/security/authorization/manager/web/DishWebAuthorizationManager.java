package com.recipe.universe.global.security.authorization.manager.web;

import com.recipe.universe.domain.dish.dish.dto.DishDto;
import com.recipe.universe.domain.dish.dish.entity.Dish;
import com.recipe.universe.domain.dish.dish.repository.DishRepository;
import com.recipe.universe.domain.dish.dish.service.DishService;
import com.recipe.universe.domain.user.role.entity.RoleName;
import com.recipe.universe.global.security.authorization.manager.role.AuthorizationDelegator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.AntPathMatcher;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;


@Component
@RequiredArgsConstructor
public class DishWebAuthorizationManager extends AbstractWebAuthorizationManager {
    private static final String SUPPORT_URI_PATTERN = "/dish/**";
    private final AuthorizationDelegator manager;
    private final DishRepository dishRepository;

    @Override
    public String getPattern() {
        return SUPPORT_URI_PATTERN;
    }

    @Override
    public AuthorizationDecision decide(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext context) {
        HttpMethod method = getMethod(context);

        // GET 명령은 다 승인
        if(method == HttpMethod.GET){
            return new AuthorizationDecision(true);
        }

        // 인증된 사용자인지 먼저 확인
        AuthorizationDecision decision = manager.isAuthentication(authenticationSupplier);
        if(!decision.isGranted()){
            return decision;
        }

        // 밴된 사용자인지 확인
        decision = manager.hasRole(RoleName.ROLE_BANNED, authenticationSupplier);
        if(decision.isGranted()){
            return decision;
        }

        if(method == HttpMethod.POST){
            return post(authenticationSupplier, context);
        }

        if(method == HttpMethod.PUT){
            return put(authenticationSupplier, context);
        }

        if(method == HttpMethod.DELETE){
            return delete(authenticationSupplier, context);
        }


        return new AuthorizationDecision(false);
    }

    @Override
    protected AuthorizationDecision get(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext context){
        return new AuthorizationDecision(true);
    }

    @Override
    protected AuthorizationDecision post(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext context){
        // 이미 앞에서 다 확인했으므로
        return new AuthorizationDecision(true);
    }

    private AuthorizationDecision delete(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext context){
        Long dishId = extractDishId(context);
        if(dishId == -1l){
            return new AuthorizationDecision(false);
        }
        return new AuthorizationDecision(check(dishId, Long.valueOf(authenticationSupplier.get().getName())));
    }

    private AuthorizationDecision put(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext context){
        Long dishId = extractDishId(context);
        if(dishId == -1l){
            return new AuthorizationDecision(false);
        }
        return new AuthorizationDecision(check(dishId, Long.valueOf(authenticationSupplier.get().getName())));

    }

    private Long extractDishId(RequestAuthorizationContext context){
        Map<String, String> map = extractPathVariable("/dish/{id}", context);
        if(!map.containsKey("id")){
            return -1l;
        }
        return Long.parseLong(map.get("id"));
    }

    @Transactional(readOnly = true)
    protected boolean check(Long dishId, Long userId){
        Optional<Dish> dish = dishRepository.findById(dishId);
        return dish.map(value -> value.getUserId().equals(userId)).orElse(false);
    }

}
