package cn.edu.zucc.controller;


import cn.edu.zucc.jwt.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("jwt")
public class JwtController {

    @GetMapping("/checkToken")
    public Boolean checkToken(HttpServletRequest request) throws Exception{
        String token = request.getHeader("token");
        System.out.println(token);
        System.out.println(JwtUtil.verifyToken(token));
        if(JwtUtil.verifyToken(token) == null) {
            return false;
        } else {
            return true;
        }
    }

}
