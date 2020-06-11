package com.wg.vhr.controller.system.basic;

import com.wg.vhr.model.Department;
import com.wg.vhr.model.ResponseBean;
import com.wg.vhr.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

/**
 * author:insane
 * Date:2020/6/1022:11
 **/
@RestController
@RequestMapping("/system/basic/dep")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @GetMapping("/")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    //下面方法都采用了存储过程进行实现，简化了业务

    @PostMapping("/")
    @Transactional
    public ResponseBean addDepartment(@RequestBody Department department) {
        departmentService.addDepartment(department);
        if (department.getResult() == 1) {
            return ResponseBean.success("添加成功！", department);
        }
        return ResponseBean.error("添加失败！");
    }

    /**
     *
     * 删除前判断是否有子部门或部门下是否有员工，都没有才能执行删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseBean deleteDepartmentById(@PathVariable Integer id) {
        Department department = new Department();
        department.setId(id);
        departmentService.deleteDepartmentById(department);
        if (department.getResult() == -2) {
            return ResponseBean.error("该部门下有子部门，删除失败！");
        } else if (department.getResult() == -1) {
            return ResponseBean.error("该部门下有员工，删除失败！");
        } else if (department.getResult() == 1) {
            return ResponseBean.success("删除成功！");
        } else {
            return ResponseBean.error("删除失败！");
        }
    }
}
