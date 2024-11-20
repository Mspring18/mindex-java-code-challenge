import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import com.mindex.challenge.dao.EmployeeRepository;

import java.util.Arrays;
import java.util.Collections;

public class ReportingStructureServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private ReportingStructureService reportingStructureService;

    public ReportingStructureServiceImplTest() {
    }

    @Test
    public void testCalculateNumberOfReports() {
        // Employee hierarchy setup
        Employee john = new Employee();
        john.setFirstName("John");
        john.setLastName("Doe");
        john.setDepartment("Engineering");
        john.setPosition("Developer");
        Employee paul = new Employee();
        john.setFirstName("Paul");
        john.setLastName("McCartney");
        john.setDepartment("Engineering");
        john.setPosition("Developer");
        Employee ringo = new Employee();
        john.setFirstName("Ringo");
        john.setLastName("Starr");
        john.setDepartment("Engineering");
        john.setPosition("Engineer");
       
        john.setDirectReports(Arrays.asList(paul, ringo));

        when(employeeRepository.findByEmployeeId("1")).thenReturn(john);
        when(employeeRepository.findByEmployeeId("2")).thenReturn(paul);
        when(employeeRepository.findByEmployeeId("3")).thenReturn(ringo);

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
        john.setFirstName("John");
        john.setLastName("Doe");
        john.setDepartment("Engineering");
        john.setPosition("Developer");
        when(employeeRepository.findByEmployeeId("1")).thenReturn(john);

        // Execute
        int result = reportingStructureService.calculateNumberOfReports(john);

        // Verify
        assertNotNull(result);
        assertEquals(0, result);
    }

    @Test
    public void testCalculateNumberOfReports_EmployeeNotFound() {
        // Employee not found
        when(employeeRepository.findByEmployeeId("1")).thenReturn(null);

        // Execute and Verify
        assertThrows(RuntimeException.class, () -> reportingStructureService.calculateNumberOfReports(employeeRepository.findByEmployeeId("1")));
    }
}
