package com.example.apisystem.security;


import java.util.Collection;
import java.util.Collections;

import com.example.apisystem.entities.Worker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



import lombok.AllArgsConstructor;


@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final Worker worker;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return worker.getPassword();
    }

    @Override
    public String getUsername() {
        return worker.getEmail();
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
        return true;
    }

    public String getName() {
        return worker.getName();
    }

}

