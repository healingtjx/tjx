package com.healing.tjx.admin.bo;

import com.healing.tjx.admin.entity.UmsAdmin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @作者: tjx
 * @描述: 用于 SpringSecurity
 * @创建时间: 创建于10:23 2020-12-15
 **/
public class AdminUserDetails implements UserDetails {

    private UmsAdmin umsAdmin;

    public AdminUserDetails(UmsAdmin umsAdmin) {
        this.umsAdmin = umsAdmin;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
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
        return umsAdmin.getStatus().equals(1);
    }
}
