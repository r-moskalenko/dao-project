package com.romanm.pis.dao;

import com.romanm.pis.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    User save(User user);

    Optional<User> findUserById(Long id);

    List<User> findAll();
}
