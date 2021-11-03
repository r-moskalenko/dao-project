package com.romanm.pis.servlet.command;

import com.romanm.pis.dao.DAOFactory;
import com.romanm.pis.dao.UserDAO;
import com.romanm.pis.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllUsersService implements Command {
    @Override
    public void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = DAOFactory.getInstance().createUserDao();
        List<User> users = userDAO.findAll();
        request.setAttribute("listUsers", users);
        request.getServletContext().getRequestDispatcher("/user-list.jsp").forward(request, response);
    }

    @Override
    public void post(HttpServletRequest request, HttpServletResponse response) {

    }
}
