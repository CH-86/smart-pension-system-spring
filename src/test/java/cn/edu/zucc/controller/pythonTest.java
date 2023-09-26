package cn.edu.zucc.controller;

import cn.edu.zucc.entity.Order;
import cn.edu.zucc.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
//import org.python.util.PythonInterpreter;


import java.io.*;
import java.util.HashMap;
import java.util.Map;
/**
 * @program: finalWorkDemo0208
 * @description:
 * @author: chen hang
 * @create: 2022-03-08 14:27
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class pythonTest {

    @Test
    public void hello() throws IOException , InterruptedException{
        String[] keys = {"age",
                "sex",
                "cp",
                "trestbps",
                "chol",
                "fbs",
                "restecg",
                "thalach",
                "exang",
                "oldpeak",
                "slope",
                "ca",
                "thal",
                "target"};
//        Map<String, Object> map = new HashMap<>();
//        map.put("age",63);
//        map.put("sex",1);
//        map.put("cp",3);
//        map.put("trestbps",145);
//        map.put("chol,",233);
//        map.put("fbs,",1);
//        map.put("restecg",0);
//        map.put("thalach",150);
//        map.put("exang",0);
//        map.put("oldpeak",2.3);
//        map.put("slope",0);
//        map.put("ca",0);
//        map.put("thal",1);


        String num = "63, 1, 3, 145, 233, 1, 0, 150, 0, 2.3, 0, 0, 1";
        StringBuilder result = new StringBuilder();
        String exe = "python";
        //String command = "E:\\BaiduNetdiskDownload\\PycharmProjects\\CV\\heart\\heart_use.py";
        String command = "src\\main\\java\\cn\\edu\\zucc\\python\\heart_use.py";
        String[] cmdArr = new String[] {exe, command, num};

        BufferedReader bufrIn = null;
        BufferedReader bufrError = null;
        Process process = Runtime.getRuntime().exec(cmdArr);
        process.waitFor();

        // 获取命令执行结果, 有两个结果: 正常的输出 和 错误的输出（PS: 子进程的输出就是主进程的输入）
        bufrIn = new BufferedReader(new InputStreamReader(process.getInputStream(), "gbk"));
        bufrError = new BufferedReader(new InputStreamReader(process.getErrorStream(), "gbk"));
        String line = null;
        while ((line = bufrIn.readLine()) != null) {
            System.out.println(line);
            result.append(line).append('\n');
        }
        while ((line = bufrError.readLine()) != null) {
            System.out.println(line);
            result.append(line).append('\n');
        }
        //System.out.println(result);

        //[[0.35 0.65]]
    }

}
