package com.healing.tjx.cms.dto;

import com.healing.tjx.datasource.entity.UserBase;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Author: tjx
 * @Description: 用于 SpringSecurity
 * @Date: 创建于10:23 2021-06-03
 **/
public class UserBaseDetails implements UserDetails {

    private UserBase userBase;

    public UserBaseDetails(UserBase userBase) {
        this.userBase = userBase;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return userBase.getSalt();
    }

    @Override
    public String getUsername() {
        return userBase.getId() + "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userBase.getStatus().equals(1);
    }
}
