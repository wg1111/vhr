package com.wg.vhr.model;

import lombok.Data;

import java.util.Date;
@Data
public class MsgContent {
    private Integer id;

    private String title;

    private String message;

    private Date createdate;

}