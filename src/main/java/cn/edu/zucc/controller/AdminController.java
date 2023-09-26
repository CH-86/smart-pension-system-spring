package cn.edu.zucc.controller;

import cn.edu.zucc.entity.*;
import cn.edu.zucc.jwt.PermitUtil;
import cn.edu.zucc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;
import cn.edu.zucc.jwt.JwtUtil;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;

//    @PostMapping("/hello")
//    public Map<String, Object> hello(){
//        Map<String, Object> map = new HashMap<>();
//        map.put("hello","old");
//        return map;
//    }

    // 管理员登录
    @PostMapping("/login")
    public Map<String, Object> findAdmin(@RequestBody Admin admin) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Admin result = adminRepository.findByAdminAndPassword(admin.getAdmin(), admin.getPassword());
        if (result != null) { //
//            if(admin.getType() == result.getType()){ // 类型正确
                // 管理员名称
                String token = JwtUtil.createToken(admin.getAdmin(), PermitUtil.Admin);
                map.put("token", token);
                map.put("name", result.getAdmin());
                map.put("type",result.getType());
                if ( result.getNearId() != null){
                    map.put("nearId", result.getNearId());
                }
//            }
//            else {
//                map.put("message","类型错误");
//            }
            //System.out.println(admin.getType().getClass());
            System.out.println(result.getType().getClass());

        } else map.put("message", "用户名或密码错误");
        return map;
    }
    /*输入
    {
    "admin":"root",
    "password":"123"
}
     */
    /*返回
    {
        "name": "root",
            "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJKV1QiLCJUeXBlIjozLCJVc2VybmFtZSI6InJvb3QiLCJleHAiOjE2NDY5OTc0MzcsImlhdCI6MTY0NjkxMTAzN30.4gy0_Oa0hLgCqvFun6bT4Kyz1wQ37fFyRTu2lc79Z2A"
    }
     */


    @Autowired
    private UserRepository userRepository;


    @GetMapping("/allUser")  //获取所有用户信息
    public List<User> findAllUser(HttpServletRequest request) {
        List<User> result = new ArrayList<>();

        String token = request.getHeader("token");
        if (PermitUtil.TokenCheck(token, PermitUtil.Admin)) {
            result = userRepository.findAll();
        }
        return result; // token错误返回空
    }

    @GetMapping("/findUserById") // 获取用户info信息
    public User findUserById(String id, HttpServletRequest request) {
        //System.out.println(id);
        String token = request.getHeader("token");
        if (PermitUtil.TokenCheck(token, PermitUtil.Admin)) {
            return userRepository.findUserById(id);
        }
        return null; // token错误返回空
    }

    @GetMapping("/findDoctorById")
    public Doctor findDoctorById(String id, HttpServletRequest request){
        //System.out.println(id);
        String token = request.getHeader("token");
        if (PermitUtil.TokenCheck(token, PermitUtil.Admin)) {
            return doctorRepository.findDoctorById(id);
        }
        return null;
    }

    @GetMapping("/findNurseById")
    public Nurse findNurseById(String id, HttpServletRequest request){
        //System.out.println(id);
        if (PermitUtil.TokenCheck(request.getHeader("token"), PermitUtil.Admin)) {
            return nurseRepository.findNurseById(id);
        }
        return null;
    }


    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/allOrder") ////获取所有订单信息
    public List<Order> findAllOrder(HttpServletRequest request) {
        List<Order> result = new ArrayList<>();

        String token = request.getHeader("token");
        if (PermitUtil.TokenCheck(token, PermitUtil.Admin)) {
            result = orderRepository.findAll();
        }
        return result; // token错误返回空
    }

    @Autowired
    private NurseRepository nurseRepository;

    @GetMapping("/allNurseTrue") //获取在籍护工
    public List<Nurse> findNurseByStatusIsTrue(HttpServletRequest request) {
        List<Nurse> result = new ArrayList<>();

        String token = request.getHeader("token");
        if (PermitUtil.TokenCheck(token, PermitUtil.Admin)) {
            result = nurseRepository.findNurseByStatusIsTrue();
        }
        return result;
    }

    @PostMapping("/nurse/update") //修改护工
    public Nurse updateNurse(@RequestBody Nurse nurse, HttpServletRequest request) {
        String token = request.getHeader("token");
        if (PermitUtil.TokenCheck(token, PermitUtil.Admin)) {
            return nurseRepository.save(nurse);
        }
        return null;
    }

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("/allDoctorTrue") //获取在籍医生
    public List<Doctor> findDoctorsByTypeIsTrue(HttpServletRequest request) {
        List<Doctor> result = new ArrayList<>();

        String token = request.getHeader("token");
        if (PermitUtil.TokenCheck(token, PermitUtil.Admin)) {
            result = doctorRepository.findDoctorsByStatusIsTrue();
        }
        return result; // token错误返回空
    }

    @PostMapping("/doctor/update") //修改医生
    public Doctor updateDoctor(@RequestBody Doctor doctor, HttpServletRequest request) {
        String token = request.getHeader("token");
        if (PermitUtil.TokenCheck(token, PermitUtil.Admin)) {
            return doctorRepository.save(doctor);
        }
        return null;// TODO: 2022/3/14  测试端口
    }

    @PostMapping("/addDoctor")//添加医生
    public Doctor addDoctor(@RequestBody Doctor doctor, HttpServletRequest request) {
        doctor.setStatus(true);
        String token = request.getHeader("token");
        return PermitUtil.TokenCheck(token, PermitUtil.Admin) ? doctorRepository.insert(doctor) : null;
    }


    @PostMapping("/addNurse")//添加护理 // @Validated 校验参数
    public Nurse findAllUser(@RequestBody Nurse nurse, HttpServletRequest request) {
        nurse.setStatus(true);
        String token = request.getHeader("token");
        return PermitUtil.TokenCheck(token, PermitUtil.Admin) ? nurseRepository.insert(nurse) : null;
    }


    @Resource
    private MongoTemplate mongoTemplate;

    @PostMapping("/setOrderInfo")
    public Map<String, Object> setOrderInfo(@RequestBody Order order, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        String token = request.getHeader("token");
        if (PermitUtil.TokenCheck(token, PermitUtil.Admin)) {
            Query query = new Query(Criteria.where("_id").is(order.getId()));
            Update update = new Update();
            update.set("status",Order.finish);
            update.set("info", order.getInfo());
            mongoTemplate.updateFirst(query,update, Order.class);
            map.put("orderId",order.getId());
        }
        else map.put("message","非法请求");
        return  map;
    }


    @PostMapping("/setHandler") // 设置订单处理人
    public Map<String, Object> setHandler(@RequestBody Order order,HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        String token = request.getHeader("token");
        if (PermitUtil.TokenCheck(token, PermitUtil.Admin)){
            Query query = new Query(Criteria.where("_id").is(order.getId()));
            Update update = new Update();
            update.set("handler", order.getHandler());
            update.set("status", Order.task);
            Object o = mongoTemplate.updateFirst(query,update, Order.class);
            System.out.println(o); // TODO: 2022/3/11

            //map.put("message","ok");
            map.put("orderId",order.getId());
        }
        else map.put("message","非法请求");
        return  map;
    }

    @Autowired
    private  TagsRepository tagsRepository;
    // CASE
    @GetMapping("/getTags") //获取标签
    public List<Tags> findTagsByType(String type,HttpServletRequest request){
        String token = request.getHeader("token");
        if (PermitUtil.TokenCheck(token, PermitUtil.Admin) || PermitUtil.TokenCheck(token, PermitUtil.User)){
            return tagsRepository.findByType(type);
        }
        return null;
    }

    @PostMapping("/addTag")
    public Map<String, Object> addTags(@RequestBody Map<String,Object> res, HttpServletRequest request){
        String token = request.getHeader("token");
        System.out.println(res);
        if (PermitUtil.TokenCheck(token, PermitUtil.Admin)) {
            String orderId = (String) res.get("orderId");
            String key = (String) res.get("key");
            Number value = (Number) res.get("value");


            Optional<Order> or  = orderRepository.findById(orderId);
            if(or.isPresent()){
                Order ord = or.get();
                Query query = new Query(Criteria.where("_id").is(ord.getUserId()));
                Update update = new Update();
                Map<String, Object> m = new HashMap<>();
                m.put("target", value);
                m.put("orderId", orderId);
                update.push("tags."+key, m);
                mongoTemplate.updateFirst(query, update, User.class);
                return res;
            }
        }
        return null;
    }

    @GetMapping("/chart")
    public List<Object> chart(String key, String collectionName){
        //Query query = new Query(Criteria.where("_id").is(orderId));
        //mongoTemplate.

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project(key),
                Aggregation.group(key).count().as("value"),
                Aggregation.project(key, "value").and("name").previousOperation(),
                Aggregation.sort(Sort.Direction.ASC, "value")
        );
        AggregationResults<Object> res = mongoTemplate.aggregate(aggregation, collectionName, Object.class);
        List<Object> list = res.getMappedResults();
        return list;
    }

    @PostMapping("/setMemo") // 填写体检结果
    public Map<String, Object> setMemo (@RequestBody Map<String,Object> userMemo,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String token = request.getHeader("token");
        if (PermitUtil.TokenCheck(token, PermitUtil.Admin)) {
            // id userId memo
            String orderId = (String) userMemo.get("id");
            String userId = (String) userMemo.get("userId");
            String memo = (String) userMemo.get("memo");

            // 设置订单状态完成
            Query query = new Query(Criteria.where("_id").is(orderId));
            Update update = new Update().set("status", Order.finish);
            mongoTemplate.updateFirst(query,update, Order.class); // 不接收状态

            if( memo != "") {
                // 添加memo
                Map<String, Object> memoResult = new HashMap<>();
                memoResult.put("memo", memo);
                memoResult.put("id", orderId);
                memoResult.put("date", new Date());
                query = new Query(Criteria.where("_id").is(userId));
                update = new Update();
                update.push("memos", memoResult);
                mongoTemplate.updateFirst(query, update, User.class); // 不接收状态
                System.out.println(memoResult);
                map.put("memo", memoResult);
            }
        }
        return map;
    }

    @PostMapping("/setCase") // 填写体检结果
    public Map<String, Object> setCase (@RequestBody Map<String,Object> userCase,HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        String token = request.getHeader("token");
        if (PermitUtil.TokenCheck(token, PermitUtil.Admin)){
            String orderId = (String) userCase.get("id");
            String userId = (String) userCase.get("userId");
            Map<String, Object> caseResult = (Map<String, Object>) userCase.get("case");
            caseResult.put("id", orderId);
            caseResult.put("date",new Date()); // TODO: 2022/3/14  userCase 添加一个日期

            // 设置订单状态完成
            Query query = new Query(Criteria.where("_id").is(orderId));
            Update update = new Update();
            update.set("status", Order.finish);
            mongoTemplate.updateFirst(query,update, Order.class); // 不接收状态

            // 添加用户体检结果
            query = new Query(Criteria.where("_id").is(userId));
            update = new Update();
            update.push("cases",caseResult);
            mongoTemplate.updateFirst(query,update, User.class); // 不接收状态

            System.out.println( orderId);
            System.out.println( userId);
            System.out.println( caseResult);

            map.put("case", caseResult);
            //map.put("message","ok");
            return  map;
        }
//        else map.put("message","非法请求");
//        return  map;
        return null;
    }
    /* 输入
{
    "id":"621c5f547bdf101566cb7db6",
    "userId":"621907809928fd72b043dfdc",
    "case":{
            "cp":3, //心脏疼痛类型
            "trestbps":145, //静息血压
            "chol,":233, //胆固醇
            "fbs,":1, //空腹血糖
            "restecg":0, //心电图
            "thalach":150, //最大的心率
            "exang":0, //运动引起的心绞痛
            "oldpeak":2.3, //ST段抑制
            "slope":0, //运动高峰的心电图
            "ca":0, //萤光显色的主要血管数目
            "thal":1 //贫血
    }
}
     */
    /* 返回值
    {
    "case": {
        "cp": 3,
        "trestbps": 145,
        "chol,": 233,
        "fbs,": 1,
        "restecg": 0,
        "thalach": 150,
        "exang": 0,
        "oldpeak": 2.3,
        "slope": 0,
        "ca": 0,
        "thal": 1,
        "id": "621c5f547bdf101566cb7db6"
    }
}
     */

}
