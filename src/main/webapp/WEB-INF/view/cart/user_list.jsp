<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>使用者列表</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
</head>
<body>
    <!-- menu bar -->
    <%@ include file="/WEB-INF/view/cart/menu.jsp" %>
    <!-- content -->
    <div style="padding: 15px" class="pure-form">
        <fieldset>
            <legend>使用者列表</legend>
            <table class="pure-table">
                <thead>
                    <tr>
                        <th>序號</th><th>帳號</th><th>Email</th><th>Email 驗證</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${users}" var="user" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${user.username}</td>
                            <td>${user.email}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${user.completed}">已驗證</c:when>
                                    <c:otherwise>未驗證</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </fieldset>
    </div>
</body>
</html>