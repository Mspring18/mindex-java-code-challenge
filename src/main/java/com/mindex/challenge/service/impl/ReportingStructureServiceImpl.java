package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.dao.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mindex.challenge.service.ReportingStructureService;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService  {

    @Autowired
    private EmployeeRepository employeeRepository;

    public int calculateNumberOfReports(Employee employee) {
        if (employee == null || employee.getDirectReports() == null || employee.getDirectReports().isEmpty()) {
            return 0;
        }

        Set<String> uniqueReports = new HashSet<>();
        calculateReportsRecursively(employee.getDirectReports(), uniqueReports);
        return uniqueReports.size();
    }

      private void calculateReportsRecursively(List<Employee> directReports, Set<String> uniqueReports) {
        for (Employee report : directReports) {
            if (report == null || uniqueReports.contains(report.getEmployeeId())) {
                continue;
            }

            // Add this employee's ID to the unique reports set
            uniqueReports.add(report.getEmployeeId());

            // Fetch the details for this employee (if not fully populated in the list)
            Employee detailedReport = employeeRepository.findByEmployeeId(report.getEmployeeId());

            // Recursively calculate reports for this employee
            if (detailedReport != null && detailedReport.getDirectReports() != null && !detailedReport.getDirectReports().isEmpty()) {
                calculateReportsRecursively(detailedReport.getDirectReports(), uniqueReports);
            }
        }
    }
}
