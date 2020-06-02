package com.wg.vhr.model;

import lombok.Data;

import java.util.Date;
@Data
public class Oplog {
    private Integer id;

    private Date adddate;

    private String operate;

    private Integer hrid;


}