package com.wtw.repository;

import com.wtw.domain.Role;
import com.wtw.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {
    Optional<User> findUserByEmail(String email);

    User findUserById(Long id);

    @Transactional
    Long deleteUserById(Long id);

    @Transactional
    @Modifying
    @Query("update User p set p.name = ?1, p.lastName = ?2, p.email = ?3, p.passwordHash=?4, p.role=?5 where p.id = ?6")
    int updateUser(String name, String lastName, String email, String passwordHash, Role role, Long id);
}
