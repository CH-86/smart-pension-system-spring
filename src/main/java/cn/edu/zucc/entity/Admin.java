package cn.edu.zucc.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Admin {
    private String id;
    private String admin;
    private Object log;
    private String password;
    private Integer type;
    private Object logs;
    private String nearId;

    @Basic
    @Column(name = "_id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "admin")
    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    @Basic
    @Column(name = "logs")
    public Object getLogs() {
        return log;
    }

    public void setLogs(Object log) {
        this.log = log;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin1 = (Admin) o;

        if (id != null ? !id.equals(admin1.id) : admin1.id != null) return false;
        if (admin != null ? !admin.equals(admin1.admin) : admin1.admin != null) return false;
        if (log != null ? !log.equals(admin1.log) : admin1.log != null) return false;
        if (password != null ? !password.equals(admin1.password) : admin1.password != null) return false;
        if (type != null ? !type.equals(admin1.type) : admin1.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (admin != null ? admin.hashCode() : 0);
        result = 31 * result + (log != null ? log.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "nearId")
    public String getNearId() {
        return nearId;
    }

    public void setNearId(String nearId) {
        this.nearId = nearId;
    }
}
