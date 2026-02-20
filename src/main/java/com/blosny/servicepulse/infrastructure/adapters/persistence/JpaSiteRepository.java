package com.blosny.servicepulse.infrastructure.adapters.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blosny.servicepulse.domain.model.Site;
import com.blosny.servicepulse.domain.port.SitePersistencePort;

@Repository
public interface JpaSiteRepository extends JpaRepository<Site, Long>, SitePersistencePort {
    
    @Override
    default List<Site> findAllSites() {
        return this.findAll();
    }

    @Override
    default Site saveSite(Site site) {
        return this.save(site);
    }
}