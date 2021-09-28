package com.healing.tjx.common.interceptor;

import com.healing.tjx.common.utils.RequestReadUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author: tjx
 * @Description: 用于解决requestBody 只能读取一次的问题
 * @Date: 创建于14:23 2021-09-28
 **/
public class BaseRequestWrapper extends HttpServletRequestWrapper {
    private final String body;


    public BaseRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.body = RequestReadUtils.read(request);
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        return new ServletInputStream() {

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() {
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

}
