package org.empManagement.service;

import java.util.HashMap;
import java.util.List;

import org.empManagement.Entities.Employee;
import org.empManagement.repositories.EmployeeRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EmployeeService {

    @Inject
    EmployeeRepository employeeRepository;

    private Integer pageEnteries = 5;

    public HashMap<String, Object> AddEmployee(Employee employee) {
        HashMap<String, Object> status = new HashMap<>();
        // ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Employee employee = objectMapper.readValue(stringEmployee, Employee.class);

            Employee existingEmployee = employeeRepository.findByPhoneNumber(employee.getPhoneNumber());
            if (existingEmployee != null) {
                status.put("status", "failure");
                status.put("message", "Employee with this phone number already exists.");
                return status;
            }

            employeeRepository.persist(employee);

            status.put("status", "success");
            status.put("message", "Employee added successfully.");
            status.put("employeeDetails", employee);

        // } catch (JsonProcessingException e) {
        //     status.put("status", "failure");
        //     status.put("message", "Invalid employee data format. Please check the JSON input.");
        //     status.put("errorDetails", e.getMessage());
        } 
        catch (Exception e) {
            status.put("status", "failure");
            status.put("message", "An unexpected error occurred while adding the employee.");
            status.put("errorDetails", e.getMessage());
        }

        return status;
    }

    public HashMap<String, Object> updateEmployee(Long id, String stringEmployee) {
        HashMap<String, Object> status = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            Employee employee = objectMapper.readValue(stringEmployee, Employee.class);
            Employee emp = employeeExist(employee);

            if (emp != null) {
                status.put("status", "success");
                status.put("Employee", emp);
                emp.setFullName(employee.getFullName());
                emp.setEmail(employee.getEmail());
                emp.setPhoneNumber(employee.getPhoneNumber());
                emp.setHiredate(employee.getHiredate());
                emp.setDateOfBirth(employee.getDateOfBirth());
                emp.setGender(employee.getGender());
                emp.setManagerID(employee.getManagerID());
                emp.setDepartmentID(employee.getDepartmentID());
                status.put("status", "success");
                status.put("Employee", emp);

            } else {
                status.put("status", "failure");
                status.put("Employee", "Employee could not be updated, check your inputs.");
            }
        } catch (JsonProcessingException e) {
            status.put("status", "failure");
            status.put("message", "Invalid employee data format. Please check the JSON input.");
            status.put("errorDetails", e.getMessage());
        } catch (Exception e) {
            status.put("status", "failure");
            status.put("message", "An unexpected error occurred while adding the employee.");
            status.put("errorDetails", e.getMessage());
        }
        return status;
    }

    public HashMap<String, Object> getEmployee(Long id) {
        HashMap<String, Object> status = new HashMap<>();
        if (employeeRepository.findById(id) != null) {
            status.put("Employee-Details", employeeRepository.findById(id));
            status.put("status", "success");
            return status;
        } else {
            status.put("status", "failure");
            status.put("message", "Employee not found.");
            return status;
        }

    }

    public List<Employee> getAllList(int pageNumber) {

        return employeeRepository.findAll(Sort.by("empID")).page(pageNumber - 1, pageEnteries).list();
    }

    public HashMap<String, Object> getAllList() {

        HashMap<String, Object> status = new HashMap<>();
        List<Employee> employees = employeeRepository.findAll(Sort.by("empID")).list();
        status.put("status", "success");
        status.put("employees", employees);
        return status;
    }

    public Integer getPageCount() {
        return (int) ((employeeRepository.findAll().count() / pageEnteries) + 1);
    }

    @Transactional
    public HashMap<String, Object> deleteEmployee(Long id) {

        HashMap<String, Object> status = new HashMap<>();
        Employee employee = employeeRepository.findById(id);

        if (employee != null) {

            status.put("status", "success");
            status.put("message", "Employee deleted successfully.");
            status.put("EMployee-Details", employee);
            employeeRepository.deleteById(id);
        } else {
            status.put("status", "failure");
            status.put("message", "Employee could not be deleted, check your inputs.");
        }

        return status;
    }

    private Employee employeeExist(Employee employee) {

        return employeeRepository.findByPhoneNumber(employee.getPhoneNumber());

    }

    public HashMap<String, Object> getManager(long id) {
        HashMap<String, Object> status = new HashMap<String, Object>();
        
        try {
            Employee manager = employeeRepository.getManager(id);
            if (manager != null) {
                status.put("status", "success");
                status.put("manager_details", manager);
            }
            else {
                status.put("status", "failure");
                status.put("message", "Manager not found.");
            }
        } catch (Exception e) {
            status.put("status", "failure");
            status.put("message", "An unexpected error occurred while adding the employee.");
            status.put("errorDetails", e.getMessage());
        }

        return status;
    }
}
