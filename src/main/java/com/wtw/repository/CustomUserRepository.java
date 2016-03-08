package com.wtw.repository;

import com.wtw.domain.User;
import org.springframework.transaction.annotation.Transactional;

public interface CustomUserRepository {
    @Transactional
    <S extends User> S createUser(S user);
}

