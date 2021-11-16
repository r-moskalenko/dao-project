package com.romanm.pis.service;

import com.romanm.pis.dao.DAOFactory;
import com.romanm.pis.dao.UserTypeDAO;
import com.romanm.pis.domain.UserType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTypeService {
    public List<UserType> getAllUserTypes() {
        UserTypeDAO userTypeDAO = DAOFactory.getInstance().createUserTypeDao();
        List<UserType> userTypes = userTypeDAO.findAll();

        return userTypes;
    }
}
