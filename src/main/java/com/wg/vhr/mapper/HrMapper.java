package com.wg.vhr.mapper;

import com.wg.vhr.model.Hr;
import com.wg.vhr.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hr record);

    int insertSelective(Hr record);

    Hr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hr record);

    int updateByPrimaryKey(Hr record);

    Hr loadUserByUsername(String username);

    List<Role> getRolesByHrId(Integer id);

    List<Hr> getAllHr(@Param("id") Integer id,@Param("keywords") String keywords);

    List<Hr> getHrByName(String name);
}