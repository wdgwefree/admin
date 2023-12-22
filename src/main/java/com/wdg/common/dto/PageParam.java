package com.wdg.common.dto;

import lombok.Data;

/**
 * 分页参数
 */
@Data
public class PageParam {

    /**
     * 当前记录起始索引
     */
    private Integer pageNum;

    /**
     * 每页显示记录数
     */
    private Integer pageSize;

}
