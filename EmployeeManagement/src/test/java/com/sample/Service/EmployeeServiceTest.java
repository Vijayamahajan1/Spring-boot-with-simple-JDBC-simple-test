package com.sample.Service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.sample.Dao.EmployeeDao;
import com.sample.model.Employee;
import com.sample.service.EmployeeService;

@SpringBootTest
public class EmployeeServiceTest {
    
    @Mock
    EmployeeDao employeeDao;

    @InjectMocks
    EmployeeService employeeService;


    @Test
    void testdata(){
        Employee expectedresult = new Employee(1,"Vijaya",4000);
        when(employeeDao.saveemployee(expectedresult)).thenReturn(expectedresult);
        Employee actualresult= employeeService.saveEmployee(expectedresult);
        assertEquals(expectedresult, actualresult);
    }

    @Test
    void testgetall(){
       List<Employee> expectedresult = new ArrayList<>();
       expectedresult.add(new Employee(1,"vijaya",3000));
       expectedresult.add(new Employee(2,"Kirtika",9000));
       when(employeeDao.getallEmployees()).thenReturn(expectedresult);
       List<Employee> actualresult = employeeService.getallEmployees();
       assertEquals(expectedresult, actualresult);
       
    }

    @Test
    void testupdate(){
      Employee expectedresult = new Employee();
      expectedresult.setId(1);
      expectedresult.setName("Vijaya");
      expectedresult.setSalary(6000);
      when(employeeDao.updateEmployee(expectedresult)).thenReturn(expectedresult);
      Employee actualresult = employeeService.updateEmployee(expectedresult);
      assertEquals(expectedresult, actualresult);
    }

    @Test
    void testdelete(){
        int id =1;
        employeeService.deletEmployee(id);
        verify(employeeDao,times(1)).deleteemployee(id);
    }
    
}
