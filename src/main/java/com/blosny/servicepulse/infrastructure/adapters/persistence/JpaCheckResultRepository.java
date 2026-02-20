package com.blosny.servicepulse.infrastructure.adapters.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blosny.servicepulse.domain.model.CheckResult;
import com.blosny.servicepulse.domain.port.CheckResultPersistencePort;

@Repository
public interface JpaCheckResultRepository extends JpaRepository<CheckResult, Long>, CheckResultPersistencePort {
    
    @Override
    default void saveResult(CheckResult result) {
        this.save(result);
    }

    @Override
    default List<CheckResult> findAllResults() {
        return this.findAll();
    }
}