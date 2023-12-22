package com.wdg.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据
 */
@Data
public class PageData implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 总记录数
     */
    private long total;
    /**
     * 分页数据
     */
    private List<?> data;

    /**
     * 分页
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    public PageData(List<?> list, long total) {
        this.data = list;
        this.total = total;
    }

}
