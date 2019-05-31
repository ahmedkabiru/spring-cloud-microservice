package com.hamsoft.authservice.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created By kabiruahmed on May 2019
 */

public class Authority implements GrantedAuthority {

    private final String authority;

    public Authority(String authority) {
        this.authority = authority;
    }


    @Override
    public String getAuthority() {
        return authority;
    }

}
