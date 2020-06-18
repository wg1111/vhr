package com.wg.vhr.controller.system.server;

import com.wg.vhr.model.ResponseBean;
import com.wg.vhr.model.Server;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * author: insane
 * Date: 2020/6/18
 * Time: 17:14
 **/
@RestController
@RequestMapping("/sys/cfg")
public class ServerController {
    @GetMapping("/")
    public ResponseBean getInfo() throws Exception
    {
        Server server = new Server();
        server.copyTo();
        return ResponseBean.success("操作成功！",server);
    }
}
