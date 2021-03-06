package org.uni.model;

import javax.persistence.Column;
import java.io.Serializable;

public class DepartmentEmployeePrimaryKey implements Serializable {

    @Column(name = "emp_no", insertable = false, updatable = false)
    protected int employeeNumber;

    @Column(name = "dept_no", insertable = false, updatable = false)
    protected String departmentNumber;

    public DepartmentEmployeePrimaryKey() {
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        DepartmentEmployeePrimaryKey that = (DepartmentEmployeePrimaryKey) obj;

        if (employeeNumber != that.employeeNumber) return false;
        if (departmentNumber != null ? !departmentNumber.equals(that.departmentNumber) : that.departmentNumber != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = employeeNumber;
        result = 31 * result + (departmentNumber != null ? departmentNumber.hashCode() : 0);
        return result;
    }
}
