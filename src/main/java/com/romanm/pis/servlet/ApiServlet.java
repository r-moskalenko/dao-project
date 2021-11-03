package com.romanm.pis.servlet;

import com.romanm.pis.dao.DAOFactory;
import com.romanm.pis.dao.impl.JDBCDAOFactory;
import com.romanm.pis.servlet.command.Command;
import com.romanm.pis.servlet.command.CreateEventService;
import com.romanm.pis.servlet.command.GetAllEventsService;
import com.romanm.pis.servlet.command.GetAllUsersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@WebServlet("/api")
public class ApiServlet extends HttpServlet {
    public static final Logger logger = LogManager.getLogger(ApiServlet.class);

    DAOFactory mySQLFactory = new JDBCDAOFactory();
    Command getAllUsers = new GetAllUsersService();
    Command getAllEvents = new GetAllEventsService();
    Command createEvent = new CreateEventService();
//    Command deleteUser = new DeleteUserService();
//    Command updatePassword = new UpdateUserService();
//    Command getAllUsersRoles = new GetAllUsersRoleService();
//    Command addUserRole = new AddUserRoleService();
//    Command deleteUserRole = new DeleteUserRoleService();
//    Command getAllRequests = new GetAllRequestsService();
//    Command changeRequestStatus = new ChangeRequestStatusService();
//    Command createRequest = new CreateRequestService();
//    Command takeRequest = new SelectRequestService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getRequestURI().split("/")[3];
        Map<String, Command> hashMap = new HashMap<>();
        hashMap.put("getAllUsers", getAllUsers);
        hashMap.put("getAllEvents", getAllEvents);
        hashMap.put("createEvent", createEvent);
//        hashMap.put("/deleteUser", deleteUser);
//        hashMap.put("/updatePassword", updatePassword);
//        hashMap.put("/getAllUsersRoles", getAllUsersRoles);
//        hashMap.put("/addUserRole", addUserRole);
//        hashMap.put("/deleteUserRole", deleteUserRole);
//        hashMap.put("/getAllRequests", getAllRequests);
//        hashMap.put("/changeStatus", changeRequestStatus);
//        hashMap.put("/createRequest", createRequest);
//        hashMap.put("/takeRequest", takeRequest);
        Command service = hashMap.get(action);
        service.get(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String postAction = req.getRequestURI().split("/")[3];
        Map<String, Command> hashMap = new HashMap<>();
        hashMap.put("createEvent", createEvent);
//        hashMap.put("updateUserPassword",updatePassword);
//        hashMap.put("addUserRole",addUserRole);
//        hashMap.put("changeRequestStatus",changeRequestStatus);
//        hashMap.put("createRequest",createRequest);
//        hashMap.put("takeRequest",takeRequest);
        System.out.println(postAction);
        Command service = hashMap.get(postAction);
        service.post(req, resp);
    }
}
