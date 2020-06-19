package com.wg.vhr.service;

import com.wg.vhr.mapper.NationMapper;
import com.wg.vhr.model.Nation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author:insane
 * Date:2020/6/1520:39
 **/
@Service
public class NationService {
    @Autowired
    NationMapper nationMapper;

    public List<Nation> getAllNation() {
        return nationMapper.getAllNation();
    }
}
