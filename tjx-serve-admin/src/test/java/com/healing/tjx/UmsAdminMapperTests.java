package com.healing.tjx;


import cn.hutool.json.JSONUtil;
import com.healing.tjx.admin.AdminApplication;
import com.healing.tjx.admin.entity.UmsAdmin;
import com.healing.tjx.admin.mapper.UmsAdminMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * @作者: tjx
 * @描述:
 * @创建时间: 创建于9:59 2020-12-11
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApplication.class)
public class UmsAdminMapperTests {

    @Resource
    private UmsAdminMapper umsAdminMapper;


    @Test
    public void testAdd() {
        UmsAdmin umsAdmin = umsAdminMapper.selectById(1);
        log.info(JSONUtil.toJsonStr(umsAdmin));

    }


}
