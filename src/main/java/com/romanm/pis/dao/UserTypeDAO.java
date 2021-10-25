package com.romanm.pis.dao;

import com.romanm.pis.domain.UserType;

import java.util.List;
import java.util.Optional;

public interface UserTypeDAO {
    UserType save(UserType user);

    Optional<UserType> findById(Long id);

    List<UserType> findAll();
}
