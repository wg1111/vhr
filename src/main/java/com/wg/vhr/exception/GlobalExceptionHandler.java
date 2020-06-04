package com.wg.vhr.exception;

import com.wg.vhr.model.ResponseBean;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * author:insane
 * Date:2020/6/423:11
 **/
//全局异常
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public ResponseBean SQLException(SQLException e){
        return ResponseBean.error("数据库异常，操作失败！");
    }
}
