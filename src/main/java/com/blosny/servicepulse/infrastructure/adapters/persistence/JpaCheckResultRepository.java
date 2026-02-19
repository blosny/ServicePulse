package com.blosny.servicepulse.infrastructure.adapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blosny.servicepulse.domain.model.CheckResult;

@Repository
public interface JpaCheckResultRepository extends JpaRepository<CheckResult, Long> {
}