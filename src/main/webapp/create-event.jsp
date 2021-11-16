<%--
  Created by IntelliJ IDEA.
  User: romanm
  Date: 02/11/2021
  Time: 00:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>New event</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/jquery.datetimepicker.min.css" >
    <!-- Custom styles for this template -->
    <link href="${pageContext.servletContext.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>

<h1 class="pt-5 text-center">
    New event form
</h1>

<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <a class="navbar-brand" href="#"> <img src="${pageContext.servletContext.contextPath}/icons/icon.png" alt="Brand name"/> </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
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

<main role="main" class="container-md">
    <div class="container-md">
    <form method="post">
        <div class="row justify-content-center">
            <div class="col-4 order-1 py-4">
                <label for="shortDescription" class="form-label">Short description</label>
                <input type="text" name="shortDescription" class="form-control" id="shortDescription" placeholder="Title of event">
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col-4 order-1 py-2">
                <label for="dateTime" class="form-label">Date and time of event</label>
                <input id="dateTime" name="dateTime" class="form-control" type="text" >
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col-4 py-2">
                <label for="longDescription" class="form-label">Long description</label>
                <textarea class="form-control" name="longDescription" id="longDescription" rows="3"></textarea>
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col-4 py-2">
                <button type="submit" class="btn btn-primary">Create event</button>
            </div>
        </div>
    </form>
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
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="${pageContext.servletContext.contextPath}/js/jquery-slim.min.js"><\/script>')</script>
<script src="${pageContext.servletContext.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
<script src="${pageContext.servletContext.contextPath}/js/holder.min.js"></script>


<script src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/jquery.datetimepicker.full.min.js"></script>

<script>
    jQuery('#dateTime').datetimepicker();
</script>
</body>
</html>
