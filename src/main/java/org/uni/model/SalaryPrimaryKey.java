package org.uni.model;

import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Date;

public class SalaryPrimaryKey implements Serializable {

    @Column(name = "emp_no", insertable = false, updatable = false)
    protected int employeeNumber;

    @Column(name = "from_date")
    protected Date fromDate;

    public SalaryPrimaryKey() {
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        SalaryPrimaryKey that = (SalaryPrimaryKey) obj;

        if (employeeNumber != that.employeeNumber) return false;
        return fromDate.equals(that.fromDate);
    }

    @Override
    public int hashCode() {
        int result = employeeNumber;
        result = 31 * result + fromDate.hashCode();
        return result;
    }
}
