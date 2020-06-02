package com.wg.vhr.service;

import com.wg.vhr.mapper.HrMapper;
import com.wg.vhr.model.Hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * author:insane
 * Date:2020/5/1619:56
 **/
@Service
public class UserService implements UserDetailsService {
    @Autowired
    HrMapper hrMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Hr hr = hrMapper.loadUserByUsername(username);
    if (hr == null){
        throw new UsernameNotFoundException("用户名不存在！");
    }
    hr.setRoles(hrMapper.getRolesByHrId(hr.getId()));
    return hr;
    }

}
