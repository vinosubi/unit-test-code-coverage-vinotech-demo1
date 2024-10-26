package com.vinotech.codecoverage.Repository;
import com.vinotech.codecoverage.Model.Employee;
import com.vinotech.codecoverage.Repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void findByDepartment_shouldReturnEmployeesInDepartment() {
        Employee employee = new Employee(null, "John Doe", "Engineering", 50000);
        employeeRepository.save(employee);

        List<Employee> employees = employeeRepository.findByDepartment("Engineering");

        assertEquals(1, employees.size());
        assertEquals("Engineering", employees.get(0).getDepartment());
    }
}
