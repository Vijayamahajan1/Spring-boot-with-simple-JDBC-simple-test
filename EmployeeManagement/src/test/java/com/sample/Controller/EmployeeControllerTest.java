package com.sample.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sample.Dao.EmployeeDao;
import com.sample.model.Employee;
import com.sample.service.EmployeeService;

@SpringBootTest 
@ExtendWith(MockitoExtension.class)   
public class EmployeeControllerTest {
   @Mock
   EmployeeDao employeeDao;

   @Mock
   EmployeeService employeeService;

   @InjectMocks
   EmployeeController employeeController;

   @Test
   void getcheck(){
   String expectedResult="Hello this is alive";
   String actuatualvalue=employeeController.getcheck();
   assertEquals(expectedResult, actuatualvalue);
}

   @Test
   void saveemployeeTest(){
    Employee expectedresult = new Employee(1,"vijaya",6000);
    when(employeeService.saveEmployee(any(Employee.class))).thenReturn(expectedresult);
    ResponseEntity <Object> actualresultResponseEntity = employeeController.save(expectedresult);
    assertEquals(HttpStatus.OK, actualresultResponseEntity.getStatusCode());
    assertEquals(expectedresult, actualresultResponseEntity.getBody());
     
      

   }

    @Test 
    void getallemployeeTest(){
    List <Employee> expectedResult = new ArrayList<>();
    expectedResult.add(new Employee(1,"Vijaya",5000));
    expectedResult.add(new Employee(1,"kirtika",6000 ));
    when(employeeService.getallEmployees()).thenReturn(expectedResult);
    List <Employee> actualresult = employeeController.getAllEmployees();
   assertEquals(expectedResult, actualresult);
   }

   @Test
   void updateEmployeeTest(){
      Employee updatedEmployee = new Employee(1, "John Doe", 6000);
      when(employeeService.updateEmployee(updatedEmployee)).thenReturn(null);
      ResponseEntity<Object> responseEntity = employeeController.update(updatedEmployee);
      assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
      assertEquals("USER NOT FOUND", responseEntity.getBody());
   }

   @Test
   void deleteEmployeeTest(){
      int id = 1;
        when(employeeService.deletEmployee(id)).thenReturn(false);
        ResponseEntity<Object> responseEntity = employeeController.delete(id);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("User Not Found", responseEntity.getBody());
}
}
