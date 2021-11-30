package com.romanm.pis.repository;

import com.romanm.pis.domain.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {
}
