package com.hamsoft.authservice.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created By kabiruahmed on May 2019
 */

public enum  Authorities  implements GrantedAuthority {

    ROLE_USER;

    @Override
    public String getAuthority() {
        return name();
    }

}
