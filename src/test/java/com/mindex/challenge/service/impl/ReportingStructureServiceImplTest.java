package com.mindex.challenge.service.impl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import com.mindex.challenge.service.impl.ReportingStructureServiceImpl;
import com.mindex.challenge.dao.EmployeeRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

@RunWith(SpringRunner.class)
public class ReportingStructureServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private ReportingStructureServiceImpl reportingStructureService;

    public ReportingStructureServiceImplTest() {
    }

    @Test
    public void testCalculateNumberOfReports() {
        // Employee hierarchy setup
        Employee john = new Employee();
        john.setEmployeeId("1");
        john.setFirstName("John");
        john.setLastName("Doe");
        john.setDepartment("Engineering");
        john.setPosition("Developer");
        Employee paul = new Employee();
        paul.setEmployeeId("2");
        paul.setFirstName("Paul");
        paul.setLastName("McCartney");
        paul.setDepartment("Engineering");
        paul.setPosition("Developer");
        Employee ringo = new Employee();
        ringo.setEmployeeId("3");
        ringo.setFirstName("Ringo");
        ringo.setLastName("Starr");
        ringo.setDepartment("Engineering");
        ringo.setPosition("Engineer");

        List<Employee> reports = new ArrayList<>(List.of(paul, ringo));
        john.setDirectReports(reports);

        // Execute
        int result = reportingStructureService.calculateNumberOfReports(john);
        // Verify
        assertNotNull(result);
        assertEquals(2, result);
    }

    @Test
    public void testCalculateNumberOfReports_NoReports() {
        // Employee without reports
        Employee john = new Employee();
        john.setEmployeeId("1");
        john.setFirstName("John");
        john.setLastName("Doe");
        john.setDepartment("Engineering");
        john.setPosition("Developer");

        // Execute
        int result = reportingStructureService.calculateNumberOfReports(john);

        // Verify
        assertNotNull(result);
        assertEquals(0, result);
    }

}
