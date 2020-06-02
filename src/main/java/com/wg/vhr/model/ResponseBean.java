package com.wg.vhr.model;

import lombok.Data;

/**
 * author:insane
 * Date:2020/5/2719:57
 **/
@Data
public class ResponseBean {
    private Integer status;

    private String msg;

    private Object obj;

    public static ResponseBean success(String msg){
        return new ResponseBean(200,msg,null);
    }

    public static ResponseBean success(String msg, Object obj){
        return new ResponseBean(200,msg,obj);
    }

    public static ResponseBean error(String msg){
        return new ResponseBean(500,msg,null);
    }

    public static ResponseBean error(String msg, Object obj){
        return new ResponseBean(500,msg,obj);
    }

    public ResponseBean() {
    }

    private ResponseBean(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }
}
