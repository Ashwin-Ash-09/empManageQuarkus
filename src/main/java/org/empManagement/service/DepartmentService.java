package org.empManagement.service;

import java.util.List;

import org.empManagement.Entities.Department;
import org.empManagement.repositories.DepartmentRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DepartmentService {
    @Inject
    private DepartmentRepository departmentRepository;

    public void addDepartment(Department department) {
        departmentRepository.persist(department);
    }

    public List<Department> getAllList() {
        return departmentRepository.listAll();
    }

    public void updateDepartment(int departmentID,String attribute,String newVal) {
    }
    public Department getDepartment(long departmentID){
       return departmentRepository.findById( departmentID);
    }
}
