package com.healing.tjx.security.component;

import com.healing.tjx.security.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @作者: tjx
 * @描述: 用于jwt 鉴权
 * @创建时间: 创建于10:01 2020-12-15
 **/
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //鉴权
        String authHeader = request.getHeader(tokenHeader);
        log.info("authHeader {}", authHeader);
        if (authHeader != null && authHeader.startsWith(jwtTokenUtil.getTokenHead())) {
            // 提取去 token
            String authToken = authHeader.substring(jwtTokenUtil.getTokenHead().length());
            //从 token 中获取 username
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            log.info("checking username:{}", username);
            // 有 Authentication 对象的直接放行
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //根据 username 获取 userDetails
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                //鉴权
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    // 生成 authentication 并且存入 security 框架里面
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    log.info("authenticated user:{}", username);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }


}
