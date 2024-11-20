package com.mindex.challenge.service.impl;

//import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.impl.CompensationServiceImpl;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.dao.CompensationRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Test;
import org.junit.runner.RunWith;

//@SpringBootTest
@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    @Mock
    private CompensationRepository compensationRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private CompensationServiceImpl compensationService;

    public CompensationServiceImplTest() {
    }

    @Test
    public void testCreateCompensation() {
        try {
            String dateString = "2024-01-01"; // Example date string
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date testDate;
            testDate = formatter.parse(dateString);

            Employee testEmployee = new Employee();
            testEmployee.setFirstName("John");
            testEmployee.setLastName("Doe");
            testEmployee.setDepartment("Engineering");
            testEmployee.setPosition("Developer");
            Compensation compensation = new Compensation(testEmployee, 100000, testDate);

            when(compensationRepository.save(compensation)).thenReturn(compensation);

            // Execute
            Compensation result = compensationService.createCompensation(compensation);

            // Verify
            assertNotNull(result);
            assertEquals(100000, result.getSalary());
            assertEquals(testDate, result.getEffectiveDate());
            assertEquals("John Doe", result.getEmployee().getFirstName() + " " + result.getEmployee().getLastName());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetCompensationByEmployeeId() {
        try {
            String dateString = "2024-01-01"; // Example date string
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date testDate;
            testDate = formatter.parse(dateString);
            Employee testEmployee = new Employee();
            testEmployee.setFirstName("John");
            testEmployee.setLastName("Doe");
            testEmployee.setDepartment("Engineering");
            testEmployee.setPosition("Developer");
            Compensation compensation = new Compensation(testEmployee, 100000, testDate);

            when(compensationRepository.findByEmployee_EmployeeId("1")).thenReturn(compensation);

            // Execute
            Compensation result = compensationService.getCompensationByEmployeeId("1");

            // Verify
            assertNotNull(result);
            assertEquals(100000, result.getSalary());
            assertEquals(testDate, result.getEffectiveDate());
            assertEquals("John Doe", result.getEmployee().getFirstName() + " " + result.getEmployee().getLastName());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
