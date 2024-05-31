package com.sample.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sample.model.Employee;

@Repository
public class EmployeeDao {

    @Autowired
    DataSource dbconnection;

    public Employee saveemployee(Employee employees){
        try{Connection con = dbconnection.getConnection();
           PreparedStatement ptmt =con.prepareStatement("INSERT INTO empdata(id,name,salary)VALUES(?,?,?)");
           ptmt.setInt(1,employees.getId());
           ptmt.setString(2, employees.getName());
           ptmt.setInt(3, employees.getSalary());
           ptmt.executeUpdate();
        
        }catch(SQLException e){
           e.printStackTrace();
        }
        return employees;
    }

    public List<Employee> getallEmployees(){
        List<Employee> employees= new ArrayList<>();
        try{
            Connection con  = dbconnection.getConnection();
            PreparedStatement ptmt = con.prepareStatement("SELECT * FROM empdata");
            ResultSet resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                int employeeId=resultSet.getInt(1);
                String employeename= resultSet.getString(2);
                int employeesalary= resultSet.getInt(3);
                employees.add(new Employee(employeeId,employeename,employeesalary));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return employees;
    }


    public Employee getEmployee(int id){
        Employee employee=null;
        try{
            Connection con = dbconnection.getConnection();
            PreparedStatement ptmt = con.prepareStatement("SELECT * FROM empdata WHERE Id=?");
            ptmt.setInt(1,id);
            ResultSet resultSet = ptmt.executeQuery();
             while(resultSet.next()){
            
                int employeeId=resultSet.getInt(1);
                String employeename=resultSet.getString(2);
                int employeesalary=resultSet.getInt(3);
                employee=new Employee(employeeId,employeename,employeesalary);
            }
        }catch(SQLException e){
            e.printStackTrace();
            
        }
        return employee;
    }

    public boolean employeeExists(int employeeId) throws SQLException {
        Connection con = dbconnection.getConnection();
        PreparedStatement ptmt = con.prepareStatement("SELECT COUNT(*) FROM empdata WHERE id=?");
        ptmt.setInt(1, employeeId);
        ResultSet rs = ptmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        return count > 0;
    }


    public Employee updateEmployee(Employee employee){
        try{
            Connection con =dbconnection.getConnection();
            PreparedStatement ptmt = con.prepareStatement("UPDATE empdata SET name=?, salary=? WHERE id=?");
            ptmt.setString(1, employee.getName());
            ptmt.setInt(2, employee.getSalary());
            ptmt.setInt(3,employee.getId());
            ptmt.executeUpdate();
      }catch(SQLException e){
          e.printStackTrace();
      }
       return employee;
      }

    public void deleteemployee(int id){
        try{
            Connection con = dbconnection.getConnection();
            PreparedStatement ptmt = con.prepareStatement("DELETE FROM empdata WHERE id = ?");
            ptmt.setInt(1, id);
            ptmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            
        }
    
    }
}