package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService {

     @Autowired
    private CompensationRepository compensationRepository;

    public Compensation createCompensation(Compensation compensation) {
        return compensationRepository.save(compensation);
    }

    public Compensation getCompensationByEmployeeId(String employeeId) {
        return compensationRepository.findByEmployee_EmployeeId(employeeId);
    }

}