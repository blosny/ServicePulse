package com.blosny.servicepulse.infrastructure.adapters.web;

import com.blosny.servicepulse.application.dto.SiteRequest;
import com.blosny.servicepulse.domain.model.Site;
import com.blosny.servicepulse.domain.port.SitePersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sites")
@RequiredArgsConstructor
public class SiteController {

    private final SitePersistencePort sitePersistencePort;

    @PostMapping
    public Site addSite(@RequestBody SiteRequest request) {
        Site site = Site.builder()
                .name(request.getName())
                .url(request.getUrl())
                .isUp(false) // başlangıçta false
                .build();
        return sitePersistencePort.saveSite(site);
    }

    @GetMapping
    public List<Site> getAllSites() {
        return sitePersistencePort.findAllSites();
    }
}