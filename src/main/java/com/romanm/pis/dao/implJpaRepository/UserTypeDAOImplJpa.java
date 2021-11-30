package com.romanm.pis.dao.implJpaRepository;

import com.romanm.pis.dao.UserTypeDAO;
import com.romanm.pis.domain.UserType;
import com.romanm.pis.repository.UserTypeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserTypeDAOImplJpa implements UserTypeDAO {

    private static final Logger logger = LogManager.getLogger(UserTypeDAOImplJpa.class);

    private final UserTypeRepository userTypeRepository;

    public UserTypeDAOImplJpa(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public UserType save(UserType userType) {
        userTypeRepository.save(userType);

        return userType;
    }

    @Override
    public Optional<UserType> findById(Long id) {
        Optional<UserType> userType = userTypeRepository.findById(id);
        logger.debug(userType);
        return userType;
    }

    @Override
    public List<UserType> findAll() {
        List<UserType> userTypeList = userTypeRepository.findAll();
        logger.debug(userTypeList);
        return userTypeList;
    }
}
