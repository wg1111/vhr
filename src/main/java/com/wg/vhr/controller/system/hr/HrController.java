package com.wg.vhr.controller.system.hr;

import com.wg.vhr.model.Hr;
import com.wg.vhr.model.ResponseBean;
import com.wg.vhr.model.Role;
import com.wg.vhr.service.RoleService;
import com.wg.vhr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author:insane
 * Date:2020/6/1219:40
 **/
@RestController
@RequestMapping("/system/hr")
public class HrController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @GetMapping("/")
    public List<Hr> getAllHr(String keywords) {
        return userService.getAllHr(keywords);
    }

    @PutMapping("/")
    public ResponseBean updateHr(@RequestBody Hr hr) {
        if (userService.updateHr(hr) == 1) {
            return ResponseBean.success("修改成功！");
        }
        return ResponseBean.error("修改失败！");
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PutMapping("/updateRole")
    public ResponseBean updateHrRoles( Integer hrid, Integer[] rids) {
        if (userService.updateHrRoles(hrid, rids)) {
            return ResponseBean.success("更新成功！");
        }
        return ResponseBean.error("更新失败！");
    }

    @DeleteMapping("/{id}")
    public ResponseBean deleteByid(@PathVariable Integer id) {
        if (userService.deleteByid(id)==1) {
            return ResponseBean.success("删除成功！");
        }
        return ResponseBean.error("删除失败");
    }

}
