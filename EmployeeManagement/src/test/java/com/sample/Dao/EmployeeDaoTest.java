package com.sample.Dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.sample.model.Employee;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeDaoTest {
    @Mock
    EmployeeDao employeeDao;    

    @Test
    void savetest(){
        Employee expectedresult = new Employee(1,"vijaya",9000);
        when(employeeDao.saveemployee(expectedresult)).thenReturn(expectedresult);
        Employee actualresult = employeeDao.saveemployee(expectedresult);
        assertEquals(expectedresult, actualresult);
    }

    @Test
    void testgetall(){
       List<Employee> expectedresult = new ArrayList<>();
       expectedresult.add(new Employee(1,"vijaya",3000));
       expectedresult.add(new Employee(2,"Kirtika",9000));
       when(employeeDao.getallEmployees()).thenReturn(expectedresult);
       List<Employee> actualresult = employeeDao.getallEmployees();
       assertEquals(expectedresult, actualresult);
       
    }

    @Test
    void testupdate(){
      Employee expectedresult = new Employee();
      expectedresult.setId(1);
      expectedresult.setName("Vijaya");
      expectedresult.setSalary(6000);
      when(employeeDao.updateEmployee(expectedresult)).thenReturn(expectedresult);
      Employee actualresult = employeeDao.updateEmployee(expectedresult);
      assertEquals(expectedresult, actualresult);
    }

    @Test
    void testdelete(){
        int id =1;
       employeeDao.deleteemployee(id);
        verify(employeeDao,times(1)).deleteemployee(id);
    }
  
    
}
