package com.healing.tjx.common.api;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @作者: tjx
 * @描述: 分页返回结果
 * @创建时间: 创建于10:39 2020-12-10
 **/
@Getter
@Setter
public class PageResult<T> extends BasicsResult {
    /**
     * 当前页码
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 总条数
     */
    private Long total;
    /**
     * 分页数据
     */
    private List<T> list;

    PageResult(ResultCode code) {
        super(code);
    }

    /**
     * 成功返回结果
     */
    public static <T> PageResult<T> success(List<T> list) {
        PageResult<T> result = new PageResult<>(ResultCode.SUCCESS);
        result.setTotal((long) 20);
        result.setPageSize(1);
        result.setTotalPage(1);
        result.setPageNum(1);
        result.setList(list);
        return result;
    }
}
