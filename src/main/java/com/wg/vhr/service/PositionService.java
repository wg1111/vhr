package com.wg.vhr.service;

import com.wg.vhr.mapper.PositionMapper;
import com.wg.vhr.model.Position;
import com.wg.vhr.model.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * author:insane
 * Date:2020/6/421:55
 **/
@Service
public class PositionService {
    @Autowired
    PositionMapper positionMapper;

    public List<Position> getPositions(){
        return positionMapper.getPositions();
    }

    public Integer addPosition(Position position) {
        position.setCreateDate(new Date());
        position.setEnabled(true);
        return positionMapper.insertSelective(position);
    }

    public int updatePosition(Position position) {
        return positionMapper.updateByPrimaryKeySelective(position);
    }


    public int deletePositionById(Integer id) {
        return positionMapper.deleteByPrimaryKey(id);

    }

    public Integer deletePositionByIds(Integer[] ids) {
        return positionMapper.deletePositionByIds(ids);
    }
}
