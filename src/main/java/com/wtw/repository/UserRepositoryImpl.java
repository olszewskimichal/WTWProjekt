package com.wtw.repository;

import com.wtw.domain.Role;
import com.wtw.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;


final public class UserRepositoryImpl implements CustomUserRepository {

    @Autowired
    private UserRepository userRepository;


    @Override
    public <S extends User> S createUser(S user) {
        if (Objects.nonNull(userRepository) && userRepository.findUserById(user.getId()) == null) {
            user.setPasswordHash(new BCryptPasswordEncoder().encode(user.getPasswordHash()));
            user.setRole(Role.USER);
            return userRepository.save(user);
        }
        return null;
    }
}
