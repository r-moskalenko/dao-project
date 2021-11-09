package com.romanm.pis.servlet;

import com.romanm.pis.dao.DAOFactory;
import com.romanm.pis.dao.impl.JDBCDAOFactory;
import com.romanm.pis.servlet.command.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ApiServlet extends HttpServlet {
    public static final Logger logger = LogManager.getLogger(ApiServlet.class);

    DAOFactory mySQLFactory = new JDBCDAOFactory();
    Command getAllUsers = new GetAllUsersService();
    Command getAllEvents = new GetAllEventsService();
    Command getAllReports = new GetAllReportsService();
    Command createEvent = new CreateEventService();
    Command getAllUserTypes = new GetAllUserTypes();
    Command updateReport = new UpdateReportService();
    Command createReport = new CreateReportService();
//    Command deleteUserRole = new DeleteUserRoleService();
//    Command getAllRequests = new GetAllRequestsService();
//    Command changeRequestStatus = new ChangeRequestStatusService();
//    Command createRequest = new CreateRequestService();
//    Command takeRequest = new SelectRequestService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] uriParts = request.getRequestURI().split("/");
        String action = uriParts[uriParts.length - 1];
        Map<String, Command> hashMap = new HashMap<>();
        hashMap.put("getAllUsers", getAllUsers);
        hashMap.put("events", getAllEvents);
        hashMap.put("createEvent", createEvent);
        hashMap.put("getAllUserTypes", getAllUserTypes);
        hashMap.put("reports", getAllReports);
        hashMap.put("createReport", createReport);
//        hashMap.put("/addUserRole", addUserRole);
//        hashMap.put("/deleteUserRole", deleteUserRole);
//        hashMap.put("/getAllRequests", getAllRequests);
//        hashMap.put("/changeStatus", changeRequestStatus);
//        hashMap.put("/createRequest", createRequest);
//        hashMap.put("/takeRequest", takeRequest);
        Command service = hashMap.get(action);
        service.get(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String[] uriParts = request.getRequestURI().split("/");
        String postAction = uriParts[uriParts.length - 1];
        Map<String, Command> hashMap = new HashMap<>();
        hashMap.put("createEvent", createEvent);
        hashMap.put("reports", updateReport);
        hashMap.put("createReport", createReport);
//        hashMap.put("addUserRole",addUserRole);
//        hashMap.put("changeRequestStatus",changeRequestStatus);
//        hashMap.put("createRequest",createRequest);
//        hashMap.put("takeRequest",takeRequest);
        System.out.println(postAction);
        Command service = hashMap.get(postAction);
        service.post(request, response);
    }
}
