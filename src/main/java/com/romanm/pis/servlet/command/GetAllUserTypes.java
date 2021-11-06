package com.romanm.pis.servlet.command;

import com.romanm.pis.dao.DAOFactory;
import com.romanm.pis.dao.UserTypeDAO;
import com.romanm.pis.domain.UserType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllUserTypes implements Command {
    @Override
    public void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserTypeDAO eventDAO = DAOFactory.getInstance().createUserTypeDao();
        List<UserType> userTypes = eventDAO.findAll();
        request.setAttribute("listUserTypes", userTypes);
        request.getServletContext().getRequestDispatcher("/userType-list.jsp").forward(request, response);
    }
}
