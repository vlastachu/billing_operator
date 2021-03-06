<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Calls list</title>
    <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script src="js/bootstrap.js"></script>
</head>
<body>
<nav class="navbar navbar-toggleable-md navbar-light bg-faded fixed-top">
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <h1 class="navbar-brand mb-0" href="#">Billing Operations</h1>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item hor-padding">
                <a class="nav-link" href="calls">Calls</a>
            </li>
            <li class="nav-item hor-padding">
                <a class="nav-link" href="accounts">Accounts</a>
            </li>
            <li class="nav-item hor-padding active">
                <a class="nav-link" href="tariffs">Tariffs</a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0 hor-padding" action="calls" method="get">
            <input class="form-control mr-sm-2" type="text" placeholder="Search" id="navbar-query" name="query" >
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><i class="fa fa-search" aria-hidden="true"></i> Search</button>
        </form>
        <form class="form-inline my-2 my-lg-0" action="logout" method="get">
            <button class="btn my-2 my-sm-0 btn-outline-primary" type="submit"><i class="fa fa-sign-out" aria-hidden="true"></i> Logout</button>
        </form>
    </div>
</nav>
<div class="container">

    <h1 class="title">Tariffs</h1>
    <div class="row">
        <p>
            There is only one tariff with price equal ${price}&#162; per minute.
        </p>
    </div>
</div>
</body>
</html>
