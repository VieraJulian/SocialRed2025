package com.socialred2025.gateway.client;

import com.socialred2025.gateway.dto.ApiAuthResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class IIdentityClient {

    private final WebClient webClient;

    public IIdentityClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://identity-service").build();
    }

    public Mono<ApiAuthResponseDTO> validateToken(String token) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/auth/validateToken").queryParam("token", token).build())
                .retrieve()
                .bodyToMono(ApiAuthResponseDTO.class);
    }
}