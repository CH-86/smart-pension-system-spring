package cn.edu.zucc.controller;

import cn.edu.zucc.entity.Admin;
import cn.edu.zucc.entity.Order;
import cn.edu.zucc.entity.User;
import cn.edu.zucc.jwt.JwtUtil;
import cn.edu.zucc.jwt.PermitUtil;
import cn.edu.zucc.repository.OrderRepository;
import cn.edu.zucc.repository.UserRepository;
import org.bson.BSONObject;
import org.bson.BasicBSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("user")
public class UserController {
    /**
     *  添加 add
     *  更新 update
     *  覆写 set
     *  删除 delete
     *  数组移除 remove
     */
    @Autowired
    private UserRepository userRepository;

    @Resource
    private MongoTemplate mongoTemplate;


    /**
     *  覆写 info 子字段
     *  String key, Object value, String id
     */
    @PostMapping("/info/set")
    @ResponseBody
    public Object setInfo(@RequestBody Map<String, Object> set){
        // 3月21日 phone 替换_id
        Query query = new Query(Criteria.where("phone").is(set.get("phone")));
        Update update = new Update();
        update.set("info."+set.get("key"), set.get("value"));
        return mongoTemplate.updateFirst(query, update, User.class);
    }
    /* 输入
    {
    "key":"id",
    "value":"330327196003012242",
    "id": "621907809928fd72b043dfdc"
}
     */
    /* 输出
    {
    "matchedCount": 1,
    "modifiedCount": 1,
    "upsertedId": null
}
     */

    /**
     *  删除info 子字段
     *  String key
     *  String id
     *  retuen     "matchedCount": query获取数据条数,
     *     "modifiedCount": 操作数据,
     *     "upsertedId": null
     */
    @PostMapping("/info/delete")
    @ResponseBody
    public Object deleteInfo(@RequestBody Map<String, Object> set){
        System.out.println(set.get("key")+" "+set.get("id"));
        // 3月21日 phone 替换_id
        Query query = new Query(Criteria.where("phone").is(set.get("phone")));
        // 或者用push
        Update update = new Update().unset("info."+set.get("key"));
        return mongoTemplate.updateFirst(query, update, User.class);
    }
    /* 输入
    {
    "key":"id",
    "id": "621907809928fd72b043dfdc"
}
     */

    /**
     *  修改用户密码
     */
    @PostMapping("/password/set")
    public Object setPassword(@RequestBody Map<String, Object> set){
        Query query = new Query(Criteria.where("_id").is(set.get("id")));
        Update update = new Update().set("password", set.get("password"));
        return mongoTemplate.updateFirst(query, update, User.class);
        // TODO: 2022/3/9 返回值
    }
    /*
    {
    "password":"123123",
    "id": "621907809928fd72b043dfdc"
}
{
    "matchedCount": 1,
    "modifiedCount": 1,
    "upsertedId": null
}
     */

    /**
     *  添加用户
     */
    @PostMapping("/user/add")
    public Object addUser(@RequestBody Map<String, Object> set){
        String phone = (String) set.get("phone");
        String password = (String) set.get("password");
        Object info = set.get("info");
        Optional<User> result = userRepository.findTypeByPhone(phone);
        boolean flag = result.isPresent();
        if(flag) {    // 号码存在 不能注册
            //System.out.println("号码存在 不能注册");
            User user = result.get();
            String str = user.getType()? "该号码已注册":"该号码已注销";
            System.out.println(str);
            Map<String, Object> m = new HashMap<>();

            m.put("code", str);
            return m;
        }
        else {// 号码不存在  可以注册
            //System.out.println("号码不存在  可以注册");


            User user = new User();
            user.setPassword(password);
            user.setPhone(phone);
            user.setInfo(info);
            user.setType(true);

            List<Object> l = new ArrayList<>();
            user.setCases(l);
            user.setMemos(l);
            Map<String, Object> m = new HashMap<>();
            user.setTags(m); //空Map
            return userRepository.insert(user); // 返回User
        }
    }

    @PostMapping("/login")  // 用户登录
    public Map<String, Object> findAdmin(@RequestBody User user) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String password = user.getPassword();
        User result = userRepository.findByPhone(user.getPhone());
        if (result != null && password.equals(result.getPassword())){
            // 用户名称
            String token = JwtUtil.createToken(user.getPhone(), PermitUtil.User);
            map.put("token", token);
            map.put("name", user.getPhone());
            map.put("userId",result.getId());
        }
        else map.put("message","手机号码或密码错误");
        return map;
    }
//    {
//        "phone":"13587831111",
//            "password":"1233"
//    }
//    {
//        "name": "13587831111",
//            "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJKV1QiLCJUeXBlIjoyLCJVc2VybmFtZSI6IjEzNTg3ODMxMTExIiwiZXhwIjoxNjQ3MDA0MDYzLCJpYXQiOjE2NDY5MTc2NjN9.l6YGLcUhJIQspljGXcWN0jRYRO6qTyCf90qQdMiHDFA"
//    }


    //token校验
    // 操作
    // 返回值

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/allOrder")
    public List<Order> findByUserId(String userId ,HttpServletRequest request){
        if (PermitUtil.TokenCheck(request.getHeader("token"), PermitUtil.User)) {
            return orderRepository.findByUserId(userId);
        }
        return null;
    }

    @GetMapping("/cancelOrder") // 取消订单
    public Object cancelOrder(String id, HttpServletRequest request) {
        if (PermitUtil.TokenCheck(request.getHeader("token"), PermitUtil.User)) {
            // 判断该订单是否已被更新
            Optional<Order> order = orderRepository.findById(id);
            if(order.isPresent()) {    //
                int status = order.get().getStatus();
                System.out.println(status);
                if(status == Order.task || status == Order.finish){
                    return "状态已更新,不能取消";
                }

                System.out.println(id);
                Query query = new Query(Criteria.where("_id").is(id));
                Update update = new Update();
                update.set("status", Order.cancel);
                return mongoTemplate.updateFirst(query, update, Order.class);
            }
        }
        return null;
    }

    // userId
    @PostMapping("/addOrder") //生成订单
    public Map<String, Object> addOrder(@RequestBody Order order, HttpServletRequest request){
        // 护理 咨询 体检
        Map<String, Object> map = new HashMap<>();
        if (PermitUtil.TokenCheck(request.getHeader("token"), PermitUtil.User)) {
            order.setStatus(Order.wait);
            Date date = new Date();
            Order result = orderRepository.insert(order);
            if(result != null){
                map.put("orderId", result.getId());
            }
        }
        else map.put("message","非法请求");
        return map;
    }
//    {
//        "userId":"621907809928fd72b043dfdc",
//            "type": "体检",
//            "date":"2022-03-06",
//            "info":""
//    }
//    {
//        "orderId": "6229fa6c4eea1c1618493519"
//    }


    /**
     *  用戶添加memo
     */
    @PostMapping("/memos/add")
    @ResponseBody
    public Object addMemo(@RequestBody Map<String, Object> set){
//        System.out.println(set.get("id"));
//        System.out.println(set.get("memo"));

        Query query = new Query(Criteria.where("_id").is(set.get("id")));
        Update update = new Update();

        BSONObject obj = new BasicBSONObject();
        Date now = new Date();
        obj.put("date", now);
        set.remove("id");
        for (String key: set.keySet()){
            obj.put(key,set.get(key));
        }
        System.out.println(obj);

        //update.push("memos",obj);// 末尾增加 push
        update.addToSet("memos",obj);// 末尾增加 addToSet
        return mongoTemplate.updateFirst(query, update, User.class);
    }

//    /**
//     *  获取用户memos
//     */
//    @GetMapping("/memos/all")
//    public List<Object> getMemos(String phone){
//        User user = userRepository.findMemosByPhone(phone);
//        // TODO: 2022/3/12 判空
//        List<Object> list = (List<Object>) user.getMemos();
//        if(list != null){
//            for (int i = 0; i < list.size(); i++) {
//                System.out.println(i);
//                System.out.println(list.get(i));
//            }
//            return list;
//        }
//        return null;
//    }
    // get
    // http://localhost:8080/user/memos/all?phone=13587831111
    @GetMapping("/memos/all")
    public User getMemos(String phone){
        return userRepository.findMemosByPhone(phone);
    }

    /**
     * 获取info
     */
    @GetMapping("/info/all")
    public Object getInfo(String phone){
        return userRepository.findInfoByPhone(phone).getInfo();
    }// TODO: 2022/3/12 包装返回值

    /**
     * 获取cases
     */
    @GetMapping("/cases/all")
    public Object getCases(String id){
        return userRepository.findCasesById(id).getCases();
    }


    @GetMapping("/tags/all")
    public Object getTags(String phone){
        return userRepository.findTagsByPhone(phone).getTags();
    }


    /**
     * 按数组下标修改memo
     */
    /*
    {
    "key":"memo",
    "memo":"0309新备注",
    "phone":"13587831111",
    "index":1
}
     */
    @PostMapping("/memos/update")
    public Object updateMemo(@RequestBody Map<String, Object> set) {
        String keyName = (String) set.get("key");//"memo";
        String newMemo = (String) set.get("memo");//"0309新备注";
        String phone = (String) set.get("phone");//"13587831111";
        int index = (int) set.get("index"); //0

        User user  = userRepository.findMemosByPhone(phone);

        if(user != null) {
            Object memos = user.getMemos();
            System.out.println(memos);
            if (memos != null) {
                List<Map<String, Object>> list = (List<Map<String, Object>>) memos;

//            System.out.println(list.size());

                list.get(index).put(keyName, newMemo);
//            for (int i = 0; i < list.size(); i++) {
//                System.out.println(i);
//                System.out.println(list.get(i));
//            }

                Query query = new Query(Criteria.where("phone").is(phone));
                Update update = new Update();
                update.set("memos", list);

                return mongoTemplate.updateFirst(query, update, User.class);
            }
        }
        return "memos 空"; // TODO: 2022/3/9 包装返回值
    }


    //public Map<String, Object> getUser



//    @GetMapping("/")
//    public List<User> getAll(){
//        List<User> list = userRepository.findAll();
//        return list;
//    }



}
