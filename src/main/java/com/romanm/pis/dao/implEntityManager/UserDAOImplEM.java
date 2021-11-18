package com.romanm.pis.dao.implEntityManager;

import com.romanm.pis.dao.UserDAO;
import com.romanm.pis.domain.User;
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
public class UserDAOImplEM implements UserDAO {

    private static final Logger logger = LogManager.getLogger(UserDAOImplEM.class);

    @PersistenceContext(unitName = "persistenceUnit")
    private EntityManager entityManager;

    @Override
    public User save(User user) {
        entityManager.persist(user);

        return user;
    }

    @Override
    public Optional<User> findUserById(Long id) {
        User user = entityManager.find(User.class, id);

        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        List<User> users = entityManager.createQuery("Select u From User u", User.class).getResultList();
        return users;
    }
}
