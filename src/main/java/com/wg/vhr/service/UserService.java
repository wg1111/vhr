package com.wg.vhr.service;

import com.wg.vhr.mapper.HrMapper;
import com.wg.vhr.mapper.HrRoleMapper;
import com.wg.vhr.model.Hr;
import com.wg.vhr.model.Role;
import com.wg.vhr.utils.HrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * author:insane
 * Date:2020/5/1619:56
 **/
@Service
public class UserService implements UserDetailsService {
    @Autowired
    HrMapper hrMapper;

    @Autowired
    HrRoleMapper hrRoleMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Hr hr = hrMapper.loadUserByUsername(username);
    if (hr == null){
        throw new UsernameNotFoundException("用户名不存在！");
    }
    hr.setRoles(hrMapper.getRolesByHrId(hr.getId()));
    return hr;
    }

    public List<Hr> getAllHr(String keywords) {
        return hrMapper.getAllHr(HrUtils.getCurrentHr().getId(),keywords);
    }

    public Integer updateHr(Hr hr) {
        return hrMapper.updateByPrimaryKeySelective(hr);
    }

    @Transactional
    public boolean updateHrRoles(Integer hrid, Integer[] rids) {
        hrRoleMapper.deleteByHrId(hrid);
        return hrRoleMapper.addRole(hrid,rids)==rids.length;
    }

    public List<Hr> getHrByName(String name) {
        return hrMapper.getHrByName(name);
    }

    public Integer deleteByid(Integer id) {
        return hrMapper.deleteByPrimaryKey(id);
    }
}
