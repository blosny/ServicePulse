package com.blosny.servicepulse.infrastructure.adapters.external;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.blosny.servicepulse.domain.model.CheckResult;
import com.blosny.servicepulse.domain.model.Site;
import com.blosny.servicepulse.domain.port.ExternalPulsePort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class WebClientPulseAdapter implements ExternalPulsePort {

    private final WebClient webClient;

    @Override // Bu ibare, bu metodun Port'taki sözleşmeye uyduğunu kanıtlar.
    public Mono<CheckResult> checkHealth(Site site) {
        long startTime = System.currentTimeMillis();

        return webClient.get()
                .uri(site.getUrl())
                .exchangeToMono(response -> {
                    long endTime = System.currentTimeMillis();
                    return Mono.just(CheckResult.builder()
                            .site(site)
                            .statusCode(response.statusCode().value())
                            .responseTime(endTime - startTime)
                            .success(response.statusCode().is2xxSuccessful())
                            .checkedAt(LocalDateTime.now())
                            .build());
                })
                .onErrorResume(ex -> {
                    long endTime = System.currentTimeMillis();
                    return Mono.just(CheckResult.builder()
                            .site(site)
                            .statusCode(500)
                            .responseTime(endTime - startTime)
                            .success(false)
                            .errorMessage(ex.getMessage())
                            .checkedAt(LocalDateTime.now())
                            .build());
                })
                .timeout(Duration.ofSeconds(10));
    }
}