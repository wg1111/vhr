package com.wg.vhr.model;

import lombok.Data;

@Data
public class Department {
    private Integer id;

    private String name;

    private Integer parentid;

    private String deppath;

    private Boolean enabled;

    private Boolean isparent;

}