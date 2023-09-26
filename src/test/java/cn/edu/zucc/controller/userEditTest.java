package cn.edu.zucc.controller;

import cn.edu.zucc.entity.User;
import cn.edu.zucc.repository.UserRepository;
import org.bson.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;
import java.util.*;
/**
 * @program: finalWorkDemo0208
 * @description:
 * @author: chen hang
 * @create: 2022-01-28 22:21
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class userEditTest {
    @Autowired
    private UserRepository userRepository;

    /**
     *  根据号码查询用户
     *  @param
     *  @return id type
     */
    @Test
    public void findTypeByPhone(){
        Optional<User> result = userRepository.findTypeByPhone("13587831111");
        if(result.isPresent()) {
            User user = result.get();
            System.out.println(user.getType());
            // false
            System.out.println(user.getId());
        }
    }



    /**
     *  通过ID修改用户状态 注销：正常
     */
    @Test
    public void setUserType(){
        Optional<User> result = userRepository.findById("621907809928fd72b043dfdc");
        if(result.isPresent()){// 如果存在
            User user = result.get();
            user.setType(true);
            userRepository.save(user);
        }
    }

    @Resource
    private MongoTemplate mongoTemplate;


    @Autowired
    UserController userController;



//    @Test
//    public void test(){
//        userRepository.deleteMemoById();
//        Query query = new Query(Criteria.where("_id").is("621907809928fd72b043dfdc"));
//        // 使用set，field.index 来更新数组中的值
//        // 更新数组中的元素，如果元素存在，则直接更新；如果数组个数小于待更新的索引位置，则前面补null
//        Update update = new Update().set("memos.3", null);
//        mongoTemplate.updateFirst(query, update, User.class);
        //queryAndPrint(query, "updateListData");

//        update = new Update().set("add.10", "nullBefore");
//        mongoTemplate.updateFirst(query, update, User.class);
//        queryAndPrint(query, "updateListData");
//    }



    /**
     *  按内容删除用户memo ，不成功
     */
    @Test
    public void deleteMemo(){
        Query query = new Query(Criteria.where("_id").is("621907809928fd72b043dfdc"));
//        Date now = new Date();
        Update update = new Update();

        /** pop FIRST删除头 LAST删除尾*/
        // update.pop("memos", Update.Position.LAST);

        /**
         * 删除指定memo字段的元素，找不到不报错
         * 如存在多个与匹配内容，一并删除, 匹配单个字段
         */

        BSONObject obj = new BasicBSONObject();
        obj.put("memo","多运动");
        update.pull("memos",obj);

        /** unset删除指定字段 */
        //update.set("memos.$[0]","111");
        // 无论怎样只能映射到memos 废弃
        // update.set("memos.1","111");

        // 无论是 .1 .$[1] 写法都失败
//        int index = 1;
//        String str = "memos."+index;
//        BSONObject obj = new BasicBSONObject();
//        obj.put("date", now);
//        obj.put("memo", "早点休息");
//        int[] i = new int[]{1, 2, 3};
//
//        .set("memos.1",obj);
        mongoTemplate.updateFirst(query,update,User.class);
    }


    /**
    *      下标删除memo
     */
    @Test
    public void deleteMemoByIndex(){
        int index = 2;

        User user = userRepository.findMemosByPhone("13587831111");
        if(user != null) {
            Object memos = user.getMemos();
            System.out.println(memos);
            if (memos != null) {
                List<Map<String, Object>> list = (List<Map<String, Object>>) memos;

                System.out.println(list.size());
                list.remove(index);

                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i);
                    System.out.println(list.get(i));
                }

                Query query = new Query(Criteria.where("phone").is("13587831111"));
                Update update = new Update();
                update.set("memos", list);

                mongoTemplate.updateFirst(query, update, User.class);
            } else {
                System.out.println("memos 空");
            }
        }
    }


    /**
     *      获取用户所有个人信息
     * @return
     */
//    public Map<String, Object> getUser(String userId){
//        Map<String, Object> map = new HashMap<>();
//        Optional<User> result = userRepository.findById(userId);
//        if (result.isPresent()){
//            User user = result.get();
//            map.put("info",user.getInfo());
//            map.put("phone",user.getPhone());
//            map.put("memos",user.getMemos());
//            map.put("cases",user.getCases());
//        }
//        return map;
//    }
    public User findByPhone(String phone){
        return userRepository.findByPhone(phone);
    }

//    @Test // 获取生日和性别测试
//    public void test0312(){
//        //String userId = "621907809928fd72b043dfdc";
//        String phone = "13587831111";
//
//        User user = userRepository.findInfoByPhone(phone);
//        if(user != null && user.getInfo() != null){
//            Map<String, Object> info = (Map<String, Object>) user.getInfo();
//
//            int sex = (int) info.get("sex");
//
//            String id = (String) info.get("id");
//            int birthYear =  Integer.parseInt(id.substring(6,10));
//            int year  = Calendar.getInstance().get(Calendar.YEAR);
//
//            System.out.println(sex);
//            System.out.println(birthYear+" "+year);
//            System.out.println(year-birthYear);
//        }
//        else{
//
//        }
//    }

//    @Test // 获取cases
//    public void test0312() {
//
//    }

}
