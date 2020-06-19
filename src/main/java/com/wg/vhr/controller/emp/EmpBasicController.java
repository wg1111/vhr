package com.wg.vhr.controller.emp;

import com.wg.vhr.model.Department;
import com.wg.vhr.model.Employee;
import com.wg.vhr.model.JobLevel;
import com.wg.vhr.model.Nation;
import com.wg.vhr.model.PoliticsStatus;
import com.wg.vhr.model.Position;
import com.wg.vhr.model.RespPageBean;
import com.wg.vhr.model.ResponseBean;
import com.wg.vhr.service.DepartmentService;
import com.wg.vhr.service.EmployeeService;
import com.wg.vhr.service.JobLevelService;
import com.wg.vhr.service.NationService;
import com.wg.vhr.service.PoliticsStatusService;
import com.wg.vhr.service.PositionService;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author:insane
 * Date:2020/6/1320:46
 **/
@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    PositionService positionService;
    @Autowired
    NationService nationService;
    @Autowired
    PoliticsStatusService politicsStatusService;
    @Autowired
    JobLevelService jobLevelService;
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/")
    public RespPageBean getAllEmployeeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "12") Integer size,String keyword) {
        return employeeService.getAllEmployeeByPage(page, size,keyword);
    }

    @PostMapping("/")
    public ResponseBean addEmployee(@RequestBody Employee employee) {
        if (employeeService.addEmployee(employee) == 1) {
            return ResponseBean.success("添加成功！");
        }
        return ResponseBean.error("添加失败！");
    }

    @GetMapping("/nations")
    public List<Nation> getAllNation() {
        return nationService.getAllNation();
    }

    @GetMapping("/politics")
    public List<PoliticsStatus> getAllPol() {
        return politicsStatusService.getAllPol();
    }

    @GetMapping("/jobs")
    public List<JobLevel> getAllJobs() {
        return jobLevelService.getAllJobs();
    }

    @GetMapping("/positions")
    public List<Position> getAllPositions() {
        return positionService.getPositions();
    }

    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/maxWorkID")
    public ResponseBean maxWorkID() {
        ResponseBean responseBean = ResponseBean.build().setStatus(200).setObj(String.format("%08d", employeeService.maxWorkId() + 1));
        return responseBean;
    }

    @DeleteMapping("/")
    public ResponseBean delete(Integer id) {
        if (employeeService.delete(id) == 1) {
            return ResponseBean.success("删除成功！");
        }
        return ResponseBean.error("删除失败！");
    }

    @PutMapping("/")
    public ResponseBean updateEmp(@RequestBody Employee employee) {
        if (employeeService.updateEmp(employee)==1) {
            return ResponseBean.success("更新成功！");
        }
        return ResponseBean.error("更新失败！");
    }
}
