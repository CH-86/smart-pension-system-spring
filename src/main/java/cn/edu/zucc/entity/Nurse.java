package cn.edu.zucc.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Nurse {
    private String id;

    @NotNull(message = "department 不能为空")
    private String department;

    @NotNull(message = "name 不能为空")
    private String name;

    @NotNull(message = "position 不能为空")
    private String position;
    private Boolean status;

    @Basic
    @Column(name = "_id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "department")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String deparment) {
        this.department = deparment;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Nurse nurse = (Nurse) o;

        if (id != null ? !id.equals(nurse.id) : nurse.id != null) return false;
        if (department != null ? !department.equals(nurse.department) : nurse.department != null) return false;
        if (name != null ? !name.equals(nurse.name) : nurse.name != null) return false;
        if (position != null ? !position.equals(nurse.position) : nurse.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "status")
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
