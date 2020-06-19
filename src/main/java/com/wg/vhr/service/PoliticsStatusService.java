package com.wg.vhr.service;

import com.wg.vhr.mapper.PoliticsStatusMapper;
import com.wg.vhr.model.PoliticsStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author:insane
 * Date:2020/6/1520:44
 **/
@Service
public class PoliticsStatusService {
    @Autowired
    PoliticsStatusMapper politicsStatusMapper;

    public List<PoliticsStatus> getAllPol() {
        return politicsStatusMapper.getAllPol();
    }
}
