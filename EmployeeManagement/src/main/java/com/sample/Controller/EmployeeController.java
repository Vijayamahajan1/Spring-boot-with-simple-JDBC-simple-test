package com.sample.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sample.model.Employee;
import com.sample.service.EmployeeService;


@RequestMapping("/api/employees")
@RestController
public class EmployeeController {
    
    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeservice;


    @GetMapping("/Hello")
    public String getcheck(){
        return "Hello this is alive";
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Employee employee){
        Employee emp = new Employee();
        emp = employeeservice.saveEmployee(emp);
        logger.info("The user is created:{}",emp); 
        if(emp==null){
            return new ResponseEntity<Object>("NULL DATA CAN NOT BE INSERT",HttpStatus.NOT_ACCEPTABLE);
          
        }
        else{
            return new ResponseEntity<Object>(emp,HttpStatus.OK);
        }

    }
             
    @GetMapping
    public List<Employee> getAllEmployees(){
        logger.info("Got deatails of all users");

        return employeeservice.getallEmployees();
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable int id){
        
        Employee employee=new Employee();
        employee = employeeservice.getEmployees(id);
        if(employee!=null)
        {
            return new ResponseEntity<Object>(employee, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Object>("User Not Found", HttpStatus.NOT_FOUND);
        }
       
    }

    @PutMapping()
    public ResponseEntity<Object> update(@RequestBody Employee employee) {
        logger.info("The user is updated as given employee {}");
        Employee employee2 = employeeservice.updateEmployee(employee);
        if (employee2==null) {
            return new ResponseEntity<Object>("USER NOT FOUND",HttpStatus.NOT_FOUND);
         
        }
        else{
            return new ResponseEntity<Object>(employee,HttpStatus.OK);
        }
       
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id){
        boolean result = employeeservice.deletEmployee(id);
        if(result==true){
            return new ResponseEntity<Object>("employee deleted successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Object>("User Not Found",HttpStatus.NOT_FOUND);
        }  
    }
    }
    

