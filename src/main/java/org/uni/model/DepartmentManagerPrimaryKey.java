package org.uni.model;

import javax.persistence.Column;
import java.io.Serializable;

public class DepartmentManagerPrimaryKey implements Serializable {

    @Column(name = "dept_no", insertable = false, updatable = false)
    protected String departmentNumber;

    @Column(name = "emp_no", insertable = false, updatable = false)
    protected int employeeNumber;

    public DepartmentManagerPrimaryKey() {
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        DepartmentManagerPrimaryKey that = (DepartmentManagerPrimaryKey) obj;

        if (employeeNumber != that.employeeNumber) return false;
        return !(departmentNumber != null ? !departmentNumber.equals(that.departmentNumber) : that.departmentNumber != null);

    }

    @Override
    public int hashCode() {
        int result = departmentNumber != null ? departmentNumber.hashCode() : 0;
        result = 31 * result + employeeNumber;
        return result;
    }
}
