package cn.edu.zucc.repository;

import cn.edu.zucc.entity.Tags;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TagsRepository extends MongoRepository<Tags, ObjectId> {
    List<Tags> findByType(String type);
}
