package cn.edu.zucc.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * @program: finalWorkDemo0208
 * @description:
 * @author: chen hang
 * @create: 2022-03-17 12:31
 */
@Entity
public class Tags {
    private String id;
    private Date date;
    private String name;
    private Object nameInfo;
    private String type;
    private Object valueInfo;
    private Object info;

    @Basic
    @Column(name = "info")
    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    @Basic
    @Column(name = "_id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
    @Column(name = "nameInfo")
    public Object getNameInfo() {
        return nameInfo;
    }

    public void setNameInfo(Object nameInfo) {
        this.nameInfo = nameInfo;
    }


    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "valueInfo")
    public Object getValueInfo() {
        return valueInfo;
    }

    public void setValueInfo(Object valueInfo) {
        this.valueInfo = valueInfo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tags tags = (Tags) o;

        if (id != null ? !id.equals(tags.id) : tags.id != null) return false;
        if (date != null ? !date.equals(tags.date) : tags.date != null) return false;
        if (name != null ? !name.equals(tags.name) : tags.name != null) return false;
        if (nameInfo != null ? !nameInfo.equals(tags.nameInfo) : tags.nameInfo != null) return false;
        if (type != null ? !type.equals(tags.type) : tags.type != null) return false;
        if (valueInfo != null ? !valueInfo.equals(tags.valueInfo) : tags.valueInfo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (nameInfo != null ? nameInfo.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (valueInfo != null ? valueInfo.hashCode() : 0);
        return result;
    }
}
