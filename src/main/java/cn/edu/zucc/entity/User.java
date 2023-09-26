package cn.edu.zucc.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @program: finalWorkDemo0208
 * @description:
 * @author: liu yan
 * @create: 2022-02-28 19:11
 */
@Entity
public class User {
    private String id;
    private String password;
    private Object tags;
    private Object cases;
    private Object memos;
    private Object info;
    private String phone;
    private Boolean type;

    @Basic
    @Column(name = "_id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    @Column(name = "tags")
    public Object getTags() {
        return tags;
    }

    public void setTags(Object tags) {
        this.tags = tags;
    }

    @Basic
    @Column(name = "cases")
    public Object getCases() {
        return cases;
    }

    public void setCases(Object cases) {
        this.cases = cases;
    }

    @Basic
    @Column(name = "memos")
    public Object getMemos() {
        return memos;
    }

    public void setMemos(Object memos) {
        this.memos = memos;
    }

    @Basic
    @Column(name = "info")
    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (tags != null ? !tags.equals(user.tags) : user.tags != null) return false;
        if (cases != null ? !cases.equals(user.cases) : user.cases != null) return false;
        if (memos != null ? !memos.equals(user.memos) : user.memos != null) return false;
        if (info != null ? !info.equals(user.info) : user.info != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (cases != null ? cases.hashCode() : 0);
        result = 31 * result + (memos != null ? memos.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "type")
    public Boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}
