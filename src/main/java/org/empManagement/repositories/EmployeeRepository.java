package org.empManagement.repositories;

import org.empManagement.Entities.Employee;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeeRepository implements PanacheRepository<Employee> {

    public Employee findByName(String name){

        return find("fullName",name).firstResult();
    }

    public Employee findByPhoneNumber(String phoneNumber) {
        return find("phoneNumber",phoneNumber).firstResult();
    }

    public Employee getManager(long id) {
        return find("empID",id).firstResult();
    }
}
