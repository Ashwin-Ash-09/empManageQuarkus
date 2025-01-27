package org.empManagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.empManagement.Entities.Employee;
import org.empManagement.service.EmployeeService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/employee")
public class EmployeeResource {

    @Inject
    EmployeeService employeeService;

    @Path("/new")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addNewEmployee(Employee employee) {

           HashMap<String, Object> status = employeeService.AddEmployee(employee);
          
                return Response
                        .status(Response.Status.OK)
                        .entity(status)
                        .build();
    }

    @Path("hello")
    @PermitAll
    @GET
    public String publicq() {
        return "Hello World ";
    }

    @GET
    @Path("/page-wise")
    public Response getAll(@QueryParam("page-number") int pageNumber) {

        List<Employee> employees = employeeService.getAllList(pageNumber);

        return Response.ok(employees).build();
    }

    @GET
    @Path("/page-count")
    public Map<String, Object> getPageCount() {

        HashMap<String, Object> status = new HashMap<>();

        Integer pageCount = employeeService.getPageCount();

        status.put("status", "success");
        status.put("pageCount", pageCount);
        return status;

    }

    @GET
    @Path("/all")
    public Response getAll() {

        

        return Response
                .ok(employeeService.getAllList())
                .build();
    }

    @Path("/{id}/update")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateEmployee(@PathParam("id") Long id,String employee) {

        HashMap<String, Object> status = employeeService.updateEmployee(id, employee);
        return Response
                .ok(status)
                .build();

    }

    @Path("/{id}")
    @GET
    public Response getEmployee(@PathParam("id") long id) {
        return Response
                .ok(employeeService.getEmployee(id))
                .build();
    }

    @Path("/{id}/delete")
    @DELETE
    public Response deleteEmployee(@PathParam("id") long id) {
        return Response
                .ok(employeeService.deleteEmployee(id))
                .build();
    }

    @GET
    @Path ("/{id}/manager")
    public Response getManager(@PathParam("id") long id) {

        HashMap<String, Object> status = employeeService.getManager(id);

        return Response
                .ok(status)
                .build();
    }
}
