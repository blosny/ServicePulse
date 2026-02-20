package com.blosny.servicepulse.infrastructure.adapters.persistence;

import com.blosny.servicepulse.domain.model.CheckResult;
import com.blosny.servicepulse.domain.port.CheckResultPersistencePort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCheckResultRepository extends JpaRepository<CheckResult, Long>, CheckResultPersistencePort {
    
    @Override
    default void saveResult(CheckResult result) {
        this.save(result);
    }
}