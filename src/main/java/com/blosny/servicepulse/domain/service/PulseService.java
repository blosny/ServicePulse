package com.blosny.servicepulse.domain.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.blosny.servicepulse.domain.model.CheckResult;
import com.blosny.servicepulse.domain.port.AiAnalysisPort;
import com.blosny.servicepulse.domain.port.CheckResultPersistencePort;
import com.blosny.servicepulse.domain.port.ExternalPulsePort;
import com.blosny.servicepulse.domain.port.SitePersistencePort;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PulseService {

    private final SitePersistencePort sitePersistencePort;
    private final ExternalPulsePort externalPulsePort;
    private final CheckResultPersistencePort checkResultPersistencePort;
    private final AiAnalysisPort aiAnalysisPort;

    public void monitorAllSites() {
        log.info("Servis taraması başlatıldı.");

        sitePersistencePort.findAllSites().forEach(site -> {
            externalPulsePort.checkHealth(site)
                .subscribe(result -> {
                    // sitenin durumunu güncelle
                    site.setUp(result.isSuccess());
                    sitePersistencePort.saveSite(site);

                    if (!result.isSuccess()) {
                        // site çökerse: ai analizi başlat
                        aiAnalysisPort.analyzeError(result.getErrorMessage(), site.getUrl())
                            .subscribe(aiComment -> {
                                result.setAiAnalysis(aiComment);
                                checkResultPersistencePort.saveResult(result);
                                log.warn("Site ÇÖKTÜ: {} | AI Analizi: {}", site.getName(), aiComment);
                            });
                    } else {
                        checkResultPersistencePort.saveResult(result);
                        log.info("Site AYAKTA: {} | Süre: {}ms", site.getName(), result.getResponseTime());
                    }
                });
        });
    }

    public List<CheckResult> getHistory() {
        return checkResultPersistencePort.findAllResults();
    
    }
}