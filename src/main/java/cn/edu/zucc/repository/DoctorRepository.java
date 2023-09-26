package cn.edu.zucc.repository;

import cn.edu.zucc.entity.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DoctorRepository extends MongoRepository<Doctor, String> {
    List<Doctor> findDoctorsByStatusIsTrue();

    Doctor findDoctorById(String id);
}
