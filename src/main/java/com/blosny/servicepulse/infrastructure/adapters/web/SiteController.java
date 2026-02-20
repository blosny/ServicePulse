package com.blosny.servicepulse.infrastructure.adapters.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; // PulseService import edildi
import org.springframework.web.bind.annotation.RestController;

import com.blosny.servicepulse.application.dto.SiteRequest;
import com.blosny.servicepulse.domain.model.CheckResult;
import com.blosny.servicepulse.domain.model.Site;
import com.blosny.servicepulse.domain.port.SitePersistencePort;
import com.blosny.servicepulse.domain.service.PulseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sites")
@RequiredArgsConstructor // Bu anatasyon sayesinde 'final' olan tüm field'lar otomatik enjekte edilir
public class SiteController {

    private final SitePersistencePort sitePersistencePort;
    private final PulseService pulseService; // DEĞİŞİKLİK BURADA: PulseService'i içeri aldık

    @PostMapping
    public Site addSite(@RequestBody SiteRequest request) {
        Site site = Site.builder()
                .name(request.getName())
                .url(request.getUrl())
                .isUp(false)
                .build();
        return sitePersistencePort.saveSite(site);
    }

    @GetMapping
    public List<Site> getAllSites() {
        return sitePersistencePort.findAllSites();
    }

    @GetMapping("/history")
    public List<CheckResult> getHistory() {
        // Artık doğrudan enjekte ettiğimiz pulseService'i kullanıyoruz
        return pulseService.getHistory(); 
    }
}