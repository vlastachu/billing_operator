<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Calls list</title>
    <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script src="js/bootstrap.js"></script>
</head>
<body>
<nav class="navbar navbar-toggleable-md navbar-light bg-faded">
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <h1 class="navbar-brand mb-0" href="#">Billing Operations</h1>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active hor-padding">
                <a class="nav-link" href="#">Calls</a>
            </li>
            <li class="nav-item hor-padding">
                <a class="nav-link" href="#">Accounts</a>
            </li>
            <li class="nav-item hor-padding">
                <a class="nav-link" href="#">Tariffs</a>
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
            <button class="btn btn-success"><i class="fa fa-plus-circle" aria-hidden="true"></i> Register Call</button>
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
                        <%--<input type="hidden" name="tradeId" value="${trade.tradeId}">
                        <td><input type="submit" name="cancel" value="Cancel"></td>--%>
                    </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
