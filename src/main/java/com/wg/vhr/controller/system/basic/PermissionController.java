package com.wg.vhr.controller.system.basic;


import com.wg.vhr.model.Menu;
import com.wg.vhr.model.ResponseBean;
import com.wg.vhr.model.Role;
import com.wg.vhr.service.MenuService;
import com.wg.vhr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

/**
 * author:insane
 * Date:2020/6/711:35
 **/
@RestController
@RequestMapping("/system/basic/per")
public class PermissionController {
    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;

    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @GetMapping("/menus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @GetMapping("/checked/{rid}")
    public List<Integer> getMidsByRid(@PathVariable Integer rid){
        return menuService.getMidsByRid(rid);
    }

    /**
     * 根据传来的rid删除所有原来的可操作菜单，再根据mids来执行插入操作
     * @param rid
     * @param mids
     * @return
     */
    @PutMapping("/")
    public ResponseBean updateMenuRole(Integer rid,Integer[] mids) {
        if (menuService.updateMenuRole(rid, mids)) {
            return ResponseBean.success("更新成功！");
        }
        return ResponseBean.error("更新失败!");
    }

    @PostMapping("/")
    public ResponseBean addRole(@RequestBody Role role){
        if (roleService.addRole(role) == 1) {
            return ResponseBean.success("添加成功！");
        }
        return ResponseBean.error("添加失败！");
    }

    @DeleteMapping("/role/{id}")
    public ResponseBean deleteRoleById(@PathVariable Integer id) {
        if (roleService.deleteRoleById(id) == 1) {
            return ResponseBean.success("删除成功！");
        }
        return ResponseBean.error("删除失败！");
    }
}
