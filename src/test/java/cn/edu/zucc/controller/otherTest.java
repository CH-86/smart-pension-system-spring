package cn.edu.zucc.controller;

import cn.edu.zucc.entity.Order;
import cn.edu.zucc.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: finalWorkDemo0208
 * @description:
 * @author: chen hang
 * @create: 2022-03-08 14:27
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class otherTest {
//    @Autowired
//    private TagR

//    public void


    @Autowired
    private OrderRepository orderRepository;

    // 查看所有订单
    @Test
    public void getALLOrder(){
        for (Order o :orderRepository.findAll())
        System.out.println(o.getId()+" "+o.getDate()+" "+o.getUserId());
    }

}
