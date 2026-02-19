package com.blosny.servicepulse.infrastructure.adapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blosny.servicepulse.domain.model.Site;

@Repository
public interface JpaSiteRepository extends JpaRepository<Site, Long> {
}