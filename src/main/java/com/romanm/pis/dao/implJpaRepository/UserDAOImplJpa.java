package com.romanm.pis.dao.implJpaRepository;

import com.romanm.pis.dao.UserDAO;
import com.romanm.pis.domain.User;
import com.romanm.pis.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserDAOImplJpa implements UserDAO {

    private static final Logger logger = LogManager.getLogger(UserDAOImplJpa.class);

    private final UserRepository userRepository;

    public UserDAOImplJpa(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        userRepository.save(user);

        return user;
    }

    @Override
    public Optional<User> findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        logger.debug(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        logger.debug(Arrays.toString(users.toArray()));
        return users;
    }
}
