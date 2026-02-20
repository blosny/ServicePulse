package com.blosny.servicepulse.domain.port;

import reactor.core.publisher.Mono;

public interface AiAnalysisPort {
    Mono<String> analyzeError(String errorMessage, String url);
}
