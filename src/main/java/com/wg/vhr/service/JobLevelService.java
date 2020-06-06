package com.wg.vhr.service;

import com.wg.vhr.mapper.JobLevelMapper;
import com.wg.vhr.model.JobLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * author:insane
 * Date:2020/6/616:34
 **/
@Service
public class JobLevelService {
    @Autowired
    JobLevelMapper jobLevelMapper;

    public List<JobLevel> getAllJobs() {

        return jobLevelMapper.getAllJobs();
    }

    public Integer addJob(JobLevel jobLevel) {
        jobLevel.setCreateDate(new Date());
        jobLevel.setEnabled(true);
        return jobLevelMapper.insertSelective(jobLevel);
    }

    public Integer updateJob(JobLevel jobLevel) {
        return jobLevelMapper.updateByPrimaryKeySelective(jobLevel);
    }

    public Integer deleteJobById(Integer id) {
        return jobLevelMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteJobByIds(Integer[] ids) {
        return jobLevelMapper.deleteJobByIds(ids);
    }
}
