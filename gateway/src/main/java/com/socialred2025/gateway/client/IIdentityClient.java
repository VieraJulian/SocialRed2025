package com.socialred2025.gateway.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class IIdentityClient {

    private final WebClient webClient;

    public IIdentityClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://identity-service").build();
    }

    public Mono<Map<String, Object>> validateToken(String token) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/auth/validateToken").queryParam("token", token).build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                });
    }
}