package com.sample.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.sample.model.Employee;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeTest {
    @Test
    void testsetgetId(){
        Employee expected = new Employee(1,"vijaya",10000);
        expected.setId(2);
        assertEquals(2, expected.getId());
    }
    @Test
    void testsetandgetName(){
        Employee expected = new Employee(1,"vijaya",7000);
        expected.setName("kirtika");
        assertEquals("kirtika", expected.getName());
    }

    @Test
    void testsetandgetSalary(){
        Employee expected = new Employee(1,"vijaya",7000);
        expected.setSalary(5000);
        assertEquals(5000, expected.getSalary());
    }


}
