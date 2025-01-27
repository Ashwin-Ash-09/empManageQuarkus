package org.empManagement;

import java.util.List;

import org.empManagement.Entities.Department;
import org.empManagement.service.DepartmentService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/department")
public class DepartmentResource {

    @Inject
    DepartmentService departmentService;

    @GET
    public List<Department> getDepartment() {
        return departmentService.getAllList();
    }

    @POST
    @Transactional
    public String addDepartment(Department department) {
        departmentService.addDepartment(department);
        return "success";
    }

    @Path("/{id}")
    @PUT
    @Transactional
    public String updateDepartment(@PathParam("id") long departmentID) {
        
        return departmentService.getDepartment(departmentID).toString();
    }
}
