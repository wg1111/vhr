package com.wg.vhr.service;

import com.wg.vhr.mapper.DepartmentMapper;
import com.wg.vhr.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author:insane
 * Date:2020/6/1022:24
 **/
@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartmentsByParentId(-1);
    }

    public Integer insertDep(Department department) {
        return departmentMapper.insertSelective(department);
    }

    public void addDepartment(Department department) {
        department.setEnabled(true);
        departmentMapper.addDepartment(department);
    }

    public void deleteDepartmentById(Department department) {
        departmentMapper.deleteDepartmentById(department);
    }
}
