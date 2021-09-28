package com.romanm.pis.repository;

import com.romanm.pis.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findUserById(Long id);

    List<User> findAll();
}
