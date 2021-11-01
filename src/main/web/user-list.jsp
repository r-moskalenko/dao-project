<%--
  Created by IntelliJ IDEA.
  User: romanm
  Date: 28/10/2021
  Time: 02:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<table>

  <tr>
    <th>Id</th>
    <th>Name</th>
  </tr>

  <c:forEach var="user" items="${listUsers}">
  <tr>
    <td><c:out value="${user.id}"/></td>
    <td><c:out value="${user.name}"/></td>
  </tr>
  </c:forEach>
</table>
</body>
</html>
