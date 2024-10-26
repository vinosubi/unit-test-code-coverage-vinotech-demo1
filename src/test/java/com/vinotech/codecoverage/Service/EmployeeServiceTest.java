package com.vinotech.codecoverage.Service;

import com.vinotech.codecoverage.Model.Employee;
import com.vinotech.codecoverage.Repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void saveEmployee_shouldReturnSavedEmployee() {
        Employee employee = new Employee(null, "Jane Smith", "Finance", 60000);
        Mockito.when(employeeRepository.save(employee)).thenReturn(new Employee(1L, "Jane Smith", "Finance", 60000));

        Employee savedEmployee = employeeService.saveEmployee(employee);

        assertNotNull(savedEmployee.getId());
        assertEquals("Jane Smith", savedEmployee.getName());
    }
}

