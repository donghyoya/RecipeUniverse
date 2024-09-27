package com.recipe.universe.global.security.authorization.manager.web;

import com.recipe.universe.domain.dish.dish.entity.Dish;
import com.recipe.universe.domain.rating.entity.UserDishRatings;
import com.recipe.universe.domain.rating.repository.UserDishRatingsRepository;
import com.recipe.universe.domain.rating.service.UserDishRatingsService;
import com.recipe.universe.domain.user.role.entity.RoleName;
import com.recipe.universe.global.security.authorization.manager.role.AuthorizationDelegator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@RequiredArgsConstructor
@Component
public class RatingsWebAuthorizationManager extends AbstractWebAuthorizationManager{
    private static final String SUPPORT_URI_PATTERN = "/ratings/**";
    private final AuthorizationDelegator manager;
    private final UserDishRatingsRepository ratingsRepository;

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

        // 금지된 사용자인가
        decision = manager.hasRole(RoleName.ROLE_BANNED, authenticationSupplier);
        if(decision.isGranted()){
            return decision;
        }

        if(method == HttpMethod.POST){
            return new AuthorizationDecision(true);
        }

        if(method == HttpMethod.PUT){
            return put(authenticationSupplier, context);
        }

        if(method == HttpMethod.DELETE){
            return delete(authenticationSupplier, context);
        }


        return new AuthorizationDecision(false);
    }

    public Long extract(RequestAuthorizationContext context){
        Map<String, String> map = extractPathVariable("/ratings/{id}", context);
        String id = map.getOrDefault("id", "-1");
        return Long.parseLong(id);
    }

    private AuthorizationDecision delete(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext context){
        Long id = extract(context);
        if(id == -1l){
            return new AuthorizationDecision(false);
        }
        return new AuthorizationDecision(check(id, Long.valueOf(authenticationSupplier.get().getName())));
    }

    private AuthorizationDecision put(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext context){
        Long id = extract(context);
        if(id == -1l){
            return new AuthorizationDecision(false);
        }
        return new AuthorizationDecision(check(id, Long.valueOf(authenticationSupplier.get().getName())));

    }

    @Transactional(readOnly = true)
    protected boolean check(Long id, Long userId){
        Optional<UserDishRatings> ratings = ratingsRepository.findById(id);
        return ratings.map(
                value -> value.getUserId().equals(userId)
        ).orElse(false);
    }


}
