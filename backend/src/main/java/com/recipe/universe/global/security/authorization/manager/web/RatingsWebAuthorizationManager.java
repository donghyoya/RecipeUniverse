package com.recipe.universe.global.security.authorization.manager.web;

import com.recipe.universe.domain.review.entity.UserReview;
import com.recipe.universe.domain.review.repository.UserReviewRepository;
import com.recipe.universe.domain.user.role.entity.RoleName;
import com.recipe.universe.global.security.authorization.manager.role.AuthorizationDelegator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@RequiredArgsConstructor
@Component
public class RatingsWebAuthorizationManager extends AbstractWebAuthorizationManager{
    private static final String SUPPORT_URI_PATTERN = "/ratings/**";
    private final AuthorizationDelegator manager;
    private final UserReviewRepository ratingsRepository;

    @Override
    public String getPattern() {
        return SUPPORT_URI_PATTERN;
    }


    @Override
    protected AuthorizationDecision post(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext context){
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

        Map<String, String> map = extractIdAndMethod(context);

        if(map.containsKey("method")){
            if(map.get("method").equals("delete")){
                return delete(authenticationSupplier, context, map);
            }else if(map.get("method").equals("update")){
                return update(authenticationSupplier, context, map);
            }
        }
        return new AuthorizationDecision(true);
    }

    private AuthorizationDecision delete(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext context, Map<String, String> map){
        Long id = Long.parseLong(map.get("id"));
        return new AuthorizationDecision(check(id, Long.valueOf(authenticationSupplier.get().getName())));
    }

    private AuthorizationDecision update(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext context, Map<String, String> map){
        Long id = Long.parseLong(map.get("id"));
        return new AuthorizationDecision(check(id, Long.valueOf(authenticationSupplier.get().getName())));
    }

    @Transactional(readOnly = true)
    protected boolean check(Long id, Long userId){
        Optional<UserReview> ratings = ratingsRepository.findById(id);
        return ratings.map(
                value -> value.getUserId().equals(userId)
        ).orElse(false);
    }


}
