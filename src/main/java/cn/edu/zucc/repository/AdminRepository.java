package cn.edu.zucc.repository;

import cn.edu.zucc.entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AdminRepository extends MongoRepository<Admin, String> {

    Admin findByAdminAndPassword(String admin, String password);
}
