package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.dao.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ReportingStructureService reportingStructureService;
    @Autowired
    private CompensationService compensationService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/employee")
    public Employee create(@RequestBody Employee employee) {
        LOG.debug("Received employee create request for [{}]", employee);

        return employeeService.create(employee);
    }

    @GetMapping("/employee/{id}")
    public Employee read(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        return employeeService.read(id);
    }

    @PutMapping("/employee/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee employee) {
        LOG.debug("Received employee create request for id [{}] and employee [{}]", id, employee);

        employee.setEmployeeId(id);
        return employeeService.update(employee);
    }
    
    @GetMapping("/reportingStructure/{id}")
    public ReportingStructure getReportingStructure(@PathVariable String id) {
        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        int numberOfReports = reportingStructureService.calculateNumberOfReports(employee);
        return new ReportingStructure(employee, numberOfReports);
    }

    @PostMapping("/compensation")
    public Compensation createCompensation(@RequestBody Compensation compensation) {
        return compensationService.createCompensation(compensation);
    }

    @GetMapping("/compensation/{id}")
    public Compensation getCompensation(@PathVariable String id) {
        return compensationService.getCompensationByEmployeeId(id);
    } 

}
