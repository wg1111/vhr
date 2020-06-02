package com.wg.vhr.controller;

import com.wg.vhr.model.ResponseBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author:insane
 * Date:2020/5/2721:11
 **/
@RestController
public class LoginController {
    @GetMapping("/login")
    public ResponseBean login(){
        return ResponseBean.error("尚未登录，请登录！");
    }
}
