package com.healing.tjx.ums;

import cn.hutool.json.JSONUtil;
import com.healing.tjx.admin.AdminApplication;
import com.healing.tjx.admin.entity.UmsMenu;
import com.healing.tjx.admin.mapper.UmsMenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @作者: tjx
 * @描述:
 * @创建时间: 创建于15:51 2020-12-18
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApplication.class)
public class UmsMenuTests {

    @Resource
    private UmsMenuMapper umsMenuMapper;

    @Test
    public void test() {
        List<Long> ids = new ArrayList<>();
        ids.add(1l);
        ids.add(2l);
        List<UmsMenu> umsMenus = umsMenuMapper.selectMenuByRoleIds(ids);
        log.debug(JSONUtil.toJsonStr(umsMenus));
    }


    @Test
    public void testList() {

    }
}
