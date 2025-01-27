package org.empManagement.Entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Department extends PanacheEntityBase {


    @Id
    public long departmentID;
    public String departmentName;
    public long managerID;

    public long getDepartmentID() {
        return this.departmentID;
    }

    public void setDepartmentID(long departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public long getManagerID() {
        return this.managerID;
    }

    public void setManagerID(long managerID) {
        this.managerID = managerID;
    }


}
