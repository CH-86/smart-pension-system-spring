package cn.edu.zucc.controller;


import cn.edu.zucc.entity.*;
import cn.edu.zucc.jwt.JwtUtil;
import cn.edu.zucc.repository.*;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class adminTest {
    @Autowired
    private AdminRepository adminRepository;

    // 获取所有管理员信息
    @Test
    public void findAll(){
        System.out.println("findAll is ok");
        for(Admin admin: adminRepository.findAll()){
            System.out.println(admin);
        }
        System.out.println("findAll is end");
    }

    @Test
    public void findAdmin(){
//        System.out.println("stats is findAdmin");
//        System.out.println(admin.getAdmin() + " " + admin.getPassword());
////        System.out.println("admin is "+ admin);
////        String username = administratorRepository.findByAdmin(admin).getAdmin();
////        int type = administratorRepository.findByAdmin(admin).getType();
////        String token = JwtUtil.createToken(username, type);
//        String token = JwtUtil.createToken(admin.getAdmin(), admin.getType());
//        Map<String, Object> map = new HashMap<>();
//        map.put("token", token);
//        map.put("loginInfo", adminService.findByAdmin(admin));
//        return map;
    }


    //  获取操作日志
    @Test
    public void findLogsByAdmin(){
    }

    @Autowired
    AdminController adminController;

    //登录添加token
    @Test
    public void login() throws Exception {
        Admin admin = new Admin();
        admin.setAdmin("root");
        admin.setPassword("123");
        Object result = adminController.findAdmin(admin);
        System.out.println(result);
    }





}
