package cn.edu.zucc.repository;

import cn.edu.zucc.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface OrderRepository extends MongoRepository<Order, String>{
    List<Order> findByUserId(String userId);

    Optional<Order> findById(String id);
}
