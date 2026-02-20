package com.blosny.servicepulse.domain.port;

import java.util.List;

import com.blosny.servicepulse.domain.model.CheckResult;

public interface CheckResultPersistencePort {
    void saveResult(CheckResult result);
    List<CheckResult> findAllResults(); 
}