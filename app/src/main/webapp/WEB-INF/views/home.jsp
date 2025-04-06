<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    if (session.getAttribute("loginUser") == null) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Spring MVC</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2>${message}</h2>
    <table>
        <thead>
            <tr>
                <th>학번</th>
                <th>이름</th>
                <th>반</th>
                <th>담임 선생님</th>
                <th>국어</th>
                <th>영어</th>
                <th>수학</th>
                <th>과학</th>
                <th>역사</th>
                <th>평균</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="i" begin="0" end="${fn:length(students) - 1}">
                <c:set var="student" value="${students[i]}" />
                <c:set var="average" value="${averages[i]}" />
                    <tr>
                        <td>${student.id()}</td>
                        <td>${student.name()}</td>
                        <td>${student.classNum()}</td>
                        <td>${student.teacher()}</td>
                        <td>${student.korean()}</td>
                        <td>${student.english()}</td>
                        <td>${student.math()}</td>
                        <td>${student.science()}</td>
                        <td>${student.history()}</td>
                        <td>${average}</td>
                    </tr>
            </c:forEach>
        </tbody>
    </table>
    <form action="${pageContext.request.contextPath}/logout" method="post">
        <button type="submit">로그아웃</button>
    </form>    
</body>
</html>
