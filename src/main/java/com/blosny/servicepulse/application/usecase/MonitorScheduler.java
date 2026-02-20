package com.blosny.servicepulse.application.usecase;

import com.blosny.servicepulse.domain.service.PulseService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MonitorScheduler {

    private final PulseService pulseService;

    // her 60.000 milisaniyede (1 dakika) bir çalışır
    @Scheduled(fixedRate = 60000)
    public void runPulseCheck() {
        pulseService.monitorAllSites();
    }
}