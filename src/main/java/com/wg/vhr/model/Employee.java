package com.wg.vhr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class Employee {
    private Integer id;

    private String name;

    private String gender;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Beijing")
    private Date birthday;

    private String idCard;

    private String wedlock;

    private Integer nationId;

    private String nativePlace;

    private Integer politicId;

    private String email;

    private String phone;

    private String address;

    private Integer departmentId;

    private Integer jobLevelId;

    private Integer posId;

    private String engageForm;

    private String tiptopDegree;

    private String specialty;

    private String school;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Beijing")
    private Date beginDate;

    private String workState;

    private String workID;

    private Double contractTerm;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Beijing")
    private Date conversionTime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Beijing")
    private Date notWorkDate;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Beijing")
    private Date beginContract;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Beijing")
    private Date endContract;

    private Integer workAge;

    //一对一关系

    private Nation nations;

    private PoliticsStatus politics;

    private Department department;

    private JobLevel jobLevels;

    private Position positions;

}