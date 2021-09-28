package com.romanm.pis.repository;

import com.romanm.pis.domain.User;
import com.romanm.pis.domain.UserType;

import java.util.List;
import java.util.Optional;

public interface UserTypeRepository {
    UserType save(UserType user);

    Optional<User> findUserTypeById(Long id);

    List<User> findAll();
}
