package com.blosny.servicepulse.domain.service;

import com.blosny.servicepulse.domain.port.CheckResultPersistencePort;
import com.blosny.servicepulse.domain.port.ExternalPulsePort;
import com.blosny.servicepulse.domain.port.SitePersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j // Log yazmamızı sağlar
public class PulseService {

    private final SitePersistencePort sitePersistencePort;
    private final ExternalPulsePort externalPulsePort;
    private final CheckResultPersistencePort checkResultPersistencePort;

    public void monitorAllSites() {
        log.info("servis taraması başlatıldı.");

        sitePersistencePort.findAllSites().forEach(site -> {
            // Her bir site için WebClient'ı ateşliyoruz
            externalPulsePort.checkHealth(site)
                .subscribe(result -> {
                    // Sonucu veritabanına kaydediyoruz
                    checkResultPersistencePort.saveResult(result);
                    
                    // Sitenin son durumunu güncelliyoruz
                    site.setUp(result.isSuccess());
                    sitePersistencePort.saveSite(site);

                    log.info("Site: {} | Durum: {} | Yanıt Süresi: {}ms", 
                             site.getName(), result.isSuccess() ? "AYAKTA" : "ÇÖKMÜŞ", result.getResponseTime());
                });
        });
    }
}