<%--
  Created by IntelliJ IDEA.
  User: vlastachu
  Date: 26/02/2017
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Calls list</title>
</head>
<body>

<div align="center">
    <h1>EXCHANGE</h1>
    <table>
        <tr>
            <th>Phone number</th>
            <th>Duration</th>
            <th>Tariff per minute cost</th>
            <th>Call cost</th>
            <th>Total price</th>
        </tr>
        <c:forEach var="call" items="${calls}">
                <tr>
                    <td><strong> ${call.getPhoneNumber()}</strong></td>
                    <td>${call.getDuration()}</td>
                    <td>${tariff.getMoneyPerMinute()}</td>
                    <td>${tariff.getCallCost(call)}</td>
                    <%--<input type="hidden" name="tradeId" value="${trade.tradeId}">
                    <td><input type="submit" name="cancel" value="Cancel"></td>--%>
                </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
