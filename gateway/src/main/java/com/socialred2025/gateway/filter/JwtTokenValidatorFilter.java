package com.socialred2025.gateway.filter;

import com.socialred2025.gateway.client.IIdentityClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collection;

@Component
public class JwtTokenValidatorFilter implements WebFilter {

    private final IIdentityClient identityClient;

    public JwtTokenValidatorFilter(IIdentityClient identityClient) {
        this.identityClient = identityClient;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                .filter(authHeader -> authHeader.startsWith("Bearer "))
                .switchIfEmpty(chain.filter(exchange).then(Mono.empty()))
                .map(token -> token.replace("Bearer ", ""))
                .flatMap(token -> identityClient.validateToken(token)
                        .flatMap(response -> {
                            if (response.isSuccess() && response.getData().isValid()) {
                                String username = response.getData().getUsername();
                                String authorities = response.getData().getAuthorities();
                                Collection<GrantedAuthority> authoritiesList = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

                                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authoritiesList);
                                return Mono.just(authentication);
                            } else {
                                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                                return exchange.getResponse().setComplete().then(Mono.empty());
                            }
                        }))
                .flatMap(authentication -> chain.filter(exchange)
                        .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication)));

    }
}