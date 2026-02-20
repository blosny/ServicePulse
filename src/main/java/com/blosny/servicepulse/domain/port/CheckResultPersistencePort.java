package com.blosny.servicepulse.domain.port;

import com.blosny.servicepulse.domain.model.CheckResult;

public interface CheckResultPersistencePort {
    void saveResult(CheckResult checkResult);    
}
