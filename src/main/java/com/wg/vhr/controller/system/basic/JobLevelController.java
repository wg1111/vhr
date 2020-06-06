package com.wg.vhr.controller.system.basic;


import com.wg.vhr.model.JobLevel;
import com.wg.vhr.model.Position;
import com.wg.vhr.model.ResponseBean;
import com.wg.vhr.service.JobLevelService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author:insane
 * Date:2020/6/616:30
 **/
@RestController
@RequestMapping("/system/basic/job")
public class JobLevelController {
    @Autowired
    JobLevelService jobLevelService;

    @GetMapping("/")
    public List<JobLevel> getALLJobs(){
        return jobLevelService.getAllJobs();
    }

    @PostMapping("/")
    public ResponseBean addJob(@RequestBody JobLevel jobLevel){
        if (jobLevelService.addJob(jobLevel) == 1) {
            return ResponseBean.success("添加成功!");
        }
        return ResponseBean.error("添加失败！");
    }

    @PutMapping("/")
    public ResponseBean updateJob(@RequestBody JobLevel jobLevel) {
        if (jobLevelService.updateJob(jobLevel) == 1) {
            return ResponseBean.success("修改成功！");
        }
        return ResponseBean.error("修改失败！");
    }

    @DeleteMapping("/{id}")
    public ResponseBean deleteJobById(@PathVariable Integer id) {
        if (jobLevelService.deleteJobById(id) == 1) {
            return ResponseBean.success("删除成功！");
        }
        return ResponseBean.error("删除失败");
    }

    @DeleteMapping("/")
    public ResponseBean deleteJobByIds(Integer[] ids) {
        if (jobLevelService.deleteJobByIds(ids) == ids.length) {
            return ResponseBean.success("删除成功！");
        }
        return ResponseBean.error("删除失败！");
    }
}
