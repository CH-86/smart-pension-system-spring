package cn.edu.zucc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: finalWorkDemo0208
 * @description:
 * @author: chen hang
 * @create: 2022-03-25 21:59
 */

@RestController
@RequestMapping("socket")
public class WebSocket {
    @GetMapping("/")
    public void hello(Object o){
        System.out.println("socket"+ o);
    }
}
