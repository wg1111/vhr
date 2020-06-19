package com.wg.vhr.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

/**
 * author:insane
 * Date:2020/6/1320:48
 **/
@Data
public class RespPageBean {
    private Long total;

    private List<?> data;

}
