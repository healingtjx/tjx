package com.healing.tjx.datasource.expand;

import com.healing.tjx.datasource.entity.UmsAdmin;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tjx
 * @Description 返回用户列表，需要带上角色集合
 * @Date 2021/5/30 9:47 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsAdminResult extends UmsAdmin {

    /**
     * 角色集合
     */
    private String roles;
}
