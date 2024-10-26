package com.vinotech.codecoverage.Controller;

import com.vinotech.codecoverage.Model.Employee;
import com.vinotech.codecoverage.Service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void getEmployeeById_shouldReturnEmployee() throws Exception {
        Employee employee = new Employee(1L, "John Doe", "Engineering", 50000);
        Mockito.when(employeeService.getEmployeeById(1L)).thenReturn(employee);

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.department").value("Engineering"));
    }

    @Test
    public void createEmployee_shouldReturnCreatedEmployee() throws Exception {
        Employee employee = new Employee(null, "Jane Doe", "Marketing", 45000);
        Mockito.when(employeeService.saveEmployee(Mockito.any(Employee.class)))
                .thenReturn(new Employee(1L, "Jane Doe", "Marketing", 45000));

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Jane Doe\",\"department\":\"Marketing\",\"salary\":45000}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.department").value("Marketing"));
    }
}
