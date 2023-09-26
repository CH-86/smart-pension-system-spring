package cn.edu.zucc.repository;

import cn.edu.zucc.entity.Nurse;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface NurseRepository extends MongoRepository<Nurse, String> {
    List<Nurse> findNurseByStatusIsTrue();

    Nurse findNurseById(String id);
}
