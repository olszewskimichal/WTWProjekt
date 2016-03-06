package com.wtw.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Admin on 2016-03-01.
 */
public enum Role implements GrantedAuthority {
    USER("user"),

    ADMIN("admin");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
