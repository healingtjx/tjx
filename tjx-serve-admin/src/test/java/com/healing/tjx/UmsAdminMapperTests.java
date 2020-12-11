package com.healing.tjx;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.healing.tjx.admin.AdminApplication;
import com.healing.tjx.admin.entity.UmsAdmin;
import com.healing.tjx.admin.service.IUmsAdminService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @作者: tjx
 * @描述:
 * @创建时间: 创建于9:59 2020-12-11
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApplication.class)
public class UmsAdminMapperTests {

//
    @Autowired
    private IUmsAdminService iUmsAdminService;

    @Test
    public void testAdd(){
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setNickName("test");
        boolean save = iUmsAdminService.save(umsAdmin);
        log.info(save+"");

    }

    @Test
    public void testGet(){
        UmsAdmin byId = iUmsAdminService.getById(1);
        byId.setCreateTime(LocalDateTime.now());
        iUmsAdminService.updateById(byId);
        log.info("json:"+JSONUtil.toJsonStr(byId));
    }


}
