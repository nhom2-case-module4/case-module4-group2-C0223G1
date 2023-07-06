package com.example.project_book.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // Kiểm tra xem tài khoản có hết hạn hay không
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Kiểm tra xem tài khoản có bị khóa hay không
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Kiểm tra xem thông tin xác thực của tài khoản có hết hạn hay không
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Kiểm tra xem tài khoản có được kích hoạt hay không
        return true;
    }
}
