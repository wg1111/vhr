package com.wg.vhr.controller.system.basic;

import com.wg.vhr.model.Position;
import com.wg.vhr.model.ResponseBean;
import com.wg.vhr.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author:insane
 * Date:2020/6/421:49
 **/
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {
    @Autowired
    PositionService positionService;

    @GetMapping("/")
    public List<Position> getPosition(){
        return positionService.getPositions();
    }
    @PostMapping("/")
    public ResponseBean addPosition(@RequestBody Position position){
        if (positionService.addPosition(position) == 1) {
            return ResponseBean.success("添加成功！");
        }
        return ResponseBean.error("添加失败！");
    }

    @PutMapping("/")
    public ResponseBean updatePosition(@RequestBody Position position){
        if (positionService.updatePosition(position) == 1) {
            return ResponseBean.success("更新成功！");
        }
        return ResponseBean.error("更新失败！");
    }

    @DeleteMapping("/{id}")
    public ResponseBean deletePositionById(@PathVariable Integer id) {
        if (positionService.deletePositionById(id) == 1) {
            return ResponseBean.success("删除成功！");
        }
        return ResponseBean.error("删除失败！");
    }

    @DeleteMapping("/")
    public ResponseBean deletePositionByIds(Integer[] ids) {
        if (positionService.deletePositionByIds(ids) == ids.length) {
            return ResponseBean.success("删除成功！");
        }
        return ResponseBean.error("删除失败！");
    }
}
