package com.wg.vhr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author:insane
 * Date:2020/5/1620:11
 **/
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/employee/basic/hello")
    public String hello1(){
        return "/employee/basic/hello";
    }



    @GetMapping("/employee/advanced/hello")
    public String hello2(){
        return "/employee/advanced/hello";
    }

}
