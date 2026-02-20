package com.blosny.servicepulse.domain.port;

import java.util.List;

import com.blosny.servicepulse.domain.model.Site;

public interface SitePersistencePort {
    List<Site> findAllSites();
    Site saveSite(Site site);
}
