package com.blosny.servicepulse.domain.port;

import com.blosny.servicepulse.domain.model.CheckResult;
import com.blosny.servicepulse.domain.model.Site;

import reactor.core.publisher.Mono;

public interface ExternalPulsePort {
    Mono<CheckResult> checkHealth(Site site);
}