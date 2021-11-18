package com.romanm.pis.service;

import com.romanm.pis.dao.UserTypeDAO;
import com.romanm.pis.domain.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTypeService {

    private final UserTypeDAO userTypeDAO;

    @Autowired
    public UserTypeService(UserTypeDAO userTypeDAO) {
        this.userTypeDAO = userTypeDAO;
    }


    public List<UserType> getAllUserTypes() {
        List<UserType> userTypes = userTypeDAO.findAll();

        return userTypes;
    }
}
