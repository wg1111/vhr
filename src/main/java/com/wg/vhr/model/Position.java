package com.wg.vhr.model;

import lombok.Data;

import java.util.Date;
@Data
public class Position {
    private Integer id;

    private String name;

    private Date createdate;

    private Boolean enabled;


}