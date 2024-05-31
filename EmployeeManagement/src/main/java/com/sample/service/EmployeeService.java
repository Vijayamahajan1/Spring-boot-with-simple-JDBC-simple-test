package com.sample.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sample.Dao.EmployeeDao;
import com.sample.Exception.DataNullException;
import com.sample.Exception.UserNotFound;
import com.sample.model.Employee;
@Service
public class EmployeeService {

    Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    
    @Autowired
    EmployeeDao employeeDao;


    public Employee saveEmployee(Employee employees)throws DataNullException{
        Employee employee2=null;
        try{
            if(employees.getName()==null || employees.getId()==0 || employees.getSalary()==0){
                throw new DataNullException("Data is null fill all the data");
            }
            else{
                employee2 = employeeDao.saveemployee(employees);
                return employee2;
            }
        }catch(Exception e){
          logger.error("Exception is"+e);
        }
        return employee2;
    }

    public List<Employee> getallEmployees(){
        return employeeDao.getallEmployees();

    }

    public Employee getEmployees(int id) throws UserNotFound{ 
        Employee employee =new Employee();
    try{
        if(!employeeDao.employeeExists(employee.getId())){
            throw new UserNotFound("USER NOT FOUND");
        }
        else{
            return employeeDao.getEmployee(id);
        }
    }catch(Exception e){
          logger.error("Exception is:",e);
    }
return employee;

}



    public Employee updateEmployee(Employee employee){
        Employee employee2=null;
        try{
            if(!employeeDao.employeeExists(employee.getId())){
                throw new UserNotFound("USER NOT FOUND");
            }
            else{
                return employeeDao.updateEmployee(employee);
            }
        }catch(Exception e){
              logger.error("Exception is:",e);
        }
    return employee2;
    }


    public boolean deletEmployee(int id){
     try{
        if(!employeeDao.employeeExists(id)){
            throw new UserNotFound("USER NOT FOUND");
        }
        else{
            employeeDao.deleteemployee(id);
            return true;
        }
     }catch(Exception e){
          logger.error("Exception is",e);
     }
        
        return false;
    }
}

