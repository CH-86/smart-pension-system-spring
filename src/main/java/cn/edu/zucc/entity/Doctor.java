package cn.edu.zucc.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Doctor {
//+神经内一科+神经内二科+神经内三科+呼吸与危重症医学科+消化内科+感染疾病科+皮肤科+中西医结合科+心血管内科一病区+心血管内科二病区+内分泌科+肾病科+普通外科一病区+普通外科二病区+骨一科+骨二科+泌尿外科+烧伤整形与皮肤外科+胸心外科+神经外科一病区+神经外科二病区+妇产科+小儿科一病区+小儿科二病区+新生儿病区+急诊医学科+重症医学科+榆林市口腔医院+耳鼻喉科+眼科+血液科+肿瘤诊疗中心+肿瘤诊疗中心一病区+肿瘤诊疗中心二病区+全科医学科+手足显微外科
//            医技科室
//+超声诊断科+检验科+输血科+内窥镜室+心电图室+脑电肌电图室+配镜中心

    private String id;
    private String department;
    private String hospital;
    private String name;
    private String position; //住院医师、主治医师、副主任医师、主任医师
    private Boolean status;

    @Basic
    @Column(name = "status")
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(boolean type) {
        this.status = type;
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
    @Column(name = "deparment")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String deparment) {
        this.department = deparment;
    }

    @Basic
    @Column(name = "hospital")
    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
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

        Doctor doctor = (Doctor) o;

        if (id != null ? !id.equals(doctor.id) : doctor.id != null) return false;
        if (department != null ? !department.equals(doctor.department) : doctor.department != null) return false;
        if (hospital != null ? !hospital.equals(doctor.hospital) : doctor.hospital != null) return false;
        if (name != null ? !name.equals(doctor.name) : doctor.name != null) return false;
        if (position != null ? !position.equals(doctor.position) : doctor.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (hospital != null ? hospital.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }
}
