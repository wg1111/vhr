package com.wg.vhr.service;

import com.wg.vhr.mapper.EmployeeMapper;
import com.wg.vhr.model.Employee;
import com.wg.vhr.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author:insane
 * Date:2020/6/1320:52
 **/
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    public RespPageBean getAllEmployeeByPage(Integer page, Integer size, String keyword) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> data = employeeMapper.getAllEmployeeByPage(page, size,keyword);
        Long total = employeeMapper.getDataTotal();
        RespPageBean pageBean = new RespPageBean();
        pageBean.setData(data);
        pageBean.setTotal(total);
        return pageBean;
    }

    public Integer addEmployee(Employee employee) {
        return employeeMapper.insertSelective(employee);
    }

    public Integer maxWorkId() {
        return employeeMapper.maxWorkID();
    }

    public Integer delete(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    public Integer updateEmp(Employee employee) {
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }

}
