package cn.edu.zucc.repository;

import cn.edu.zucc.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.repository.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    /**
     *  手机号 密码
     *  登录成功，返回用户
     */
    User findByPhone(String phone);

    User findUserById(String id);

    /**
     *  根据用户手机获取memos
     */
    @Query(value= "{phone:?0}", fields = "{memos:1}")
    User findMemosByPhone(String phone);


    /**
     *  根据用户手机获取info
     */
    @Query(value= "{phone:?0}", fields = "{info:1}")
    User findInfoByPhone(String phone);

    /**
     *  根据id获取case
     */
    @Query(value= "{_id:?0}", fields = "{cases:1,}")
    User findCasesById(String id);

    /**
     *  根据id获取tags
     */
    @Query(value= "{phone:?0}", fields = "{tags:1}")
    User findTagsByPhone(String phone);




    /**
     *  获取用户部分字段 屏蔽id
     *  info phone type case memos tags
     */





    /**
     *  根据用户手机获取type
     */
    // @Query(value = "{phone:\"13055874837\"}")
    @Query(value= "{phone:?0}",fields = "{type:1}")
    Optional<User> findTypeByPhone(String phone);

//    /**
//     *  获取所有用户手机号码
//     */
//    @Query(value = "{}",fields = "{phone:1}")
//    List<String> findAllPhone();



//    @Query(value = "{${set:{type:false}}}")
//    Optional<Object> setUserType();//Boolean status);

    Optional<User> findById(String userId);

    //db.lists.update({_id:id}, {$unset : {"interests.2" : 1 }})
    @Query(value= "{'password':'123'},{$set:{'memos.2':234}}")
    void deleteMemoById();

    //(String userId);//,int index);

//    @Aggregation(value = "{password:123}")
//    void setPassword();

//
//    @ExistsQuery
//
//    @Meta
//
//    @Near
//
//    @CountQuery
}
