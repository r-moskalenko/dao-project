<%--
  Created by IntelliJ IDEA.
  User: romanm
  Date: 04/11/2021
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Reports List</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${pageContext.servletContext.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/css/reportList.css" rel="stylesheet">
</head>
<body>
<header>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="#"> <img src="${pageContext.servletContext.contextPath}/icons/icon.png"
                                           alt="Brand name"/> </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
            aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}">Home <span
                        class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="#">Disabled</a>
            </li>
        </ul>
        <form class="form-inline mt-2 mt-md-0">
            <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>
</header>

<main role="main">
    <div class="container marketing">

        <div class="container-md">
            <div class="row">
                <div class="col-md-7 justify-content-end my-md-5">
                    <a href="${pageContext.servletContext.contextPath}/api/createEvent" type="button"
                       class="btn btn-primary">
                        New report
                    </a>
                </div>
            </div>
        </div>

        <h1 class="text-center">List of Reports</h1>

        <div class="row row-cols-lg-4 ">
            <c:forEach var="report" items="${listReports}" varStatus="status">
            <div class="col m-3">
                <div class="card" style="width: 18rem;">
                <img src="${pageContext.servletContext.contextPath}/img/rimage3.png" class="card-img-top" alt="...">
                <div class="card-body">
                        <input type="hidden" name="reportId" class="report-id" value="${report.id}">
                        <h5 class="card-title">${report.topic}</h5>
                        <label>
                            <input type="text" name="reportTopic" placeholder="Enter card title..."
                                   class="card-input card-title-input" style="display: none;">
                        </label>
                        <p class="card-text">${report.text}</p>
                        <label>
                            <textarea type="text" name="reportText"
                                      class="card-input card-text-input" style="display: none;"> </textarea>
                        </label>
                        <button type="button" class="btn btn-primary edit-report">Edit</button>
                </div>
                </div>
            </div>
            </c:forEach>
        </div>

        <!-- /END THE FEATURETTES -->

    </div>


    <!-- FOOTER -->
    <footer class="container">
        <p class="float-right"><a href="#">Back to top</a></p>
        <p>&copy; 2017-2021 Company, Inc. &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a></p>
    </footer>
</main>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="${pageContext.servletContext.contextPath}/js/jquery-slim.min.js"><\/script>')</script>
<script src="${pageContext.servletContext.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/holder.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/reportList.js"></script>
</body>
</html>
