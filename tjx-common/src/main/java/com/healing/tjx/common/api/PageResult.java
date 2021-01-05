package com.healing.tjx.common.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
    private Long pageNum;
    /**
     * 每页数量
     */
    private Long pageSize;
    /**
     * 总页数
     */
    private Long totalPage;
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
     * 封装 查询结果
     *
     * @param iPage myabatis-plus 查询处理的结果
     */
    public static <T> PageResult<T> success(IPage<T> iPage) {
        PageResult<T> result = new PageResult<>(ResultCode.SUCCESS);
        result.setTotal(iPage.getTotal());
        result.setPageSize(iPage.getSize());
        //计算 总页数 公式 total/size 如果不被整除就 +1
        result.setTotalPage(iPage.getTotal() % iPage.getSize() == 0 ? iPage.getTotal() / iPage.getSize() : iPage.getTotal() / iPage.getSize() + 1);
        result.setPageNum(iPage.getCurrent());
        result.setList(iPage.getRecords());
        return result;
    }


    /**
     * 封装 自定义返回结果
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> PageResult<T> success(List<T> list) {
        PageResult<T> result = new PageResult<>(ResultCode.SUCCESS);
        result.setList(list);
        return result;
    }

}
