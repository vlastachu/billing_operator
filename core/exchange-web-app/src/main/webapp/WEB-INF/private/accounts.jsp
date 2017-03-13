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
            <li class="nav-item hor-padding active">
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
        <h1>Accounts</h1>
        <button class="btn btn-success" onclick="$('#add-account-form').slideToggle()">
            <i class="fa fa-plus-circle" aria-hidden="true"></i> Add new account
        </button>
    </div>

    <c:if test='${error.equals("NoSuchAccountException")}'>
        <div class="row">
            <div class="alert alert-danger col-lg-10 offset-lg-1">
                <h4>No such account.</h4>
                <p>The account you was trying to update was already removed by another user.</p>
            </div>
        </div>
    </c:if>
    <c:if test='${error.equals("NumberFormatException")}'>
        <div class="row">
            <div class="alert alert-danger col-lg-10 offset-lg-1">
                <h4>Wrong number format.</h4>
                <p>You pass number field with empty or incorrect string. Possibly you mistyped char instead of number.</p>
            </div>
        </div>
    </c:if>

    <div id="add-account-form" class="row" style="display: none">
        <form class="col-12 col-lg-10 offset-lg-1" method="post">
            <div class="form-group row">
                <label for="phone-number" class="col-sm-3 col-form-label">Phone Number</label>
                <div class="col-sm-9">
                    <input class="form-control" type="tel" value="" id="phone-number" name="phoneNumber">
                </div>
            </div>
            <div class="form-group row">
                <label for="money" class="col-sm-3 col-form-label">Money in cents</label>
                <div class="col-sm-9">
                    <input class="form-control" type="number" value="0" id="money" name="money">
                </div>
            </div>
            <div class="form-group row">
                <div class="offset-sm-3 col-sm-9">
                    <button type="submit" class="btn btn-primary">
                        <i class="fa fa-user-plus" aria-hidden="true" style="margin-right: 8px;"></i>Submit
                    </button>
                </div>
            </div>
        </form>
    </div>
    <div class="row">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th class="text-center">Phone number</th>
                <th class="text-center">Money</th>
                <th class="text-center">Manage</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="account" items="${accounts}">
                <tr>
                    <td class="text-center">${account.getPhoneNumber()}</td>
                    <td class="text-center">${account.getMoney()}</td>
                    <td>
                        <form class="form-inline justify-content-lg-center justify-content-end" method="post">
                            <input type="hidden" name="phoneNumber" value="${account.getPhoneNumber()}">
                            <input type="hidden" name="money" value="${account.getMoney()}">
                            <div class="input-group">
                                <div class="input-group-addon">&#162;</div>
                                <input class="form-control" type="number" placeholder="Money in cents" name="money_add" >
                            </div>
                            <button class="btn btn-outline-warning" style="margin: 8px;" type="submit" name="addMoney" value="addMoney">
                                <i class="fa fa-credit-card" aria-hidden="true"></i> Add Balance
                            </button>
                            <button class="btn btn-outline-danger" type="submit" name="archiveAccount" value="archiveAccount">
                                <i class="fa fa-archive" aria-hidden="true"></i> Archive Account
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
