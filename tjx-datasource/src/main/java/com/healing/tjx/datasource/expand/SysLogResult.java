package com.healing.tjx.datasource.expand;

import com.healing.tjx.datasource.entity.SysLog;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tjx
 * @Description 日志结果集
 * @Date 2021/5/30 3:48 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysLogResult extends SysLog {

    /**
     * 用户名
     */
    private String username;
}
