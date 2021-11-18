package com.romanm.pis.dao.implEntityManager;

import com.romanm.pis.dao.UserTypeDAO;
import com.romanm.pis.domain.UserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserTypeDAOImplEM implements UserTypeDAO {

    private static final Logger logger = LogManager.getLogger(UserTypeDAOImplEM.class);

    @PersistenceContext(unitName = "persistenceUnit")
    private EntityManager entityManager;

    @Override
    public UserType save(UserType userType) {
        entityManager.persist(userType);

        return userType;
    }

    @Override
    public Optional<UserType> findById(Long id) {
        UserType userType = entityManager.find(UserType.class, id);

        return Optional.ofNullable(userType);
    }

    @Override
    public List<UserType> findAll() {
        List<UserType> userTypeList = entityManager.createQuery("Select ut From UserType ut", UserType.class).getResultList();
        return userTypeList;
    }
}
