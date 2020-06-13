package com.wg.vhr.utils;

import com.wg.vhr.model.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * author:insane
 * Date:2020/6/1313:03
 **/
public class HrUtils {
    /**
     * 获取当前登录用户
     * @return
     */
    public static Hr getCurrentHr() {
        return ((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
