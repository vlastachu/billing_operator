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
            <li class="nav-item active hor-padding">
                <a class="nav-link" href="calls">Calls</a>
            </li>
            <li class="nav-item hor-padding">
                <a class="nav-link" href="accounts">Accounts</a>
            </li>
            <li class="nav-item hor-padding">
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

    <div class="row title justify-content-between col-12 col-lg-10 offset-lg-1">
            <h1>Calls History</h1>
            <button id="register-call-button" class="btn btn-success" onclick="$('#register-call-form').slideToggle()">
                <i class="fa fa-plus-circle" aria-hidden="true"></i> Register Call
            </button>
    </div>
    <div id="register-call-form" class="row" style="display: none">
        <form class="col-12 col-lg-10 offset-lg-1">

            <div class="form-group row">
                <label for="example-tel-input" class="col-sm-3 col-form-label">Phone Number</label>
                <div class="col-sm-9">
                    <input class="form-control" type="tel" value="" id="example-tel-input">
                </div>
            </div>
            <div class="form-group row">
                <label for="example-time-input" class="col-sm-3 col-form-label">Time</label>
                <div class="col-sm-9">
                    <input class="form-control" type="time" value="00:00:00" id="example-time-input">
                </div>
            </div>
            <div class="form-group row">
                <label for="tariff" class="col-sm-3 col-form-label">Tariff</label>
                <div class="col-sm-9">
                    <select class="custom-select mb-2 mr-sm-2 mb-sm-0" id="tariff">
                        <option selected>The single possible tariff</option>
                        <option>Sample Text</option>
                        <option>Sample Text</option>
                        <option>Sample Text</option>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <div class="offset-sm-3 col-sm-9">
                    <button type="submit" class="btn btn-primary">
                        <i class="fa fa-phone-square" aria-hidden="true" style="margin-right: 8px;"></i>Submit
                    </button>
                </div>
            </div>
        </form>
    </div>
    <div class="row">
        <form class="form-inline col-12 col-lg-10 offset-lg-1" action="calls" method="get">
            <div class="form-group my-2 col-9">
                <label class="sr-only" for="query">Phone number</label>
                <input type="text" class="form-control col" id="query" name="query" placeholder="Search by phone number" value="${query}">
            </div>
            <button type="submit" class="btn btn-primary col-3"><i class="fa fa-search" aria-hidden="true"></i> Search</button>
        </form>
    </div>
    <div class="row">
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th class="text-center">Phone number</th>
                    <th class="text-center">Duration</th>
                    <th class="text-center">Tariff per minute cost</th>
                    <th class="text-center">Call cost</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="call" items="${calls}">
                    <tr>
                        <td class="text-center"><strong> ${call.getPhoneNumber()}</strong></td>
                        <td class="text-center">${call.getDuration()}</td>
                        <td class="text-center">${tariff.getMoneyPerMinute()}</td>
                        <td class="text-center">${tariff.getCallCost(call)}</td>
                    </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
