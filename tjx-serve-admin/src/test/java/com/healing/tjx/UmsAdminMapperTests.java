package com.healing.tjx;


import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.healing.tjx.admin.AdminApplication;
import com.healing.tjx.admin.entity.UmsAdmin;
import com.healing.tjx.admin.mapper.UmsAdminMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;


/**
 * @作者: tjx
 * @描述: 单元测试案例
 * @创建时间: 创建于9:59 2020-12-11
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApplication.class)
public class UmsAdminMapperTests {

    @Resource
    private UmsAdminMapper umsAdminMapper;

    @Test
    public void testAll(){

        List<UmsAdmin> umsAdmins = umsAdminMapper.selectList(null);
        System.out.println(JSONUtil.toJsonStr(umsAdmins));
    }

    @Test
    public void testList() {
        Page<UmsAdmin> page = new Page<>(3, 1, true);
        IPage<UmsAdmin> selectPage = umsAdminMapper.selectPage(page, null);
        log.info(JSONUtil.toJsonStr(selectPage));

    }

    @Test
    public void testCustomList() {
        Page<UmsAdmin> page = new Page<>(1, 10, true);
        IPage<UmsAdmin> selectPage = umsAdminMapper.selectPageVo(page, 10);
        log.info(JSONUtil.toJsonStr(selectPage));
    }

}
