<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
    <style>
        .tabs {
            display: flex;
            margin-bottom: 10px;
        }
        .tab {
            padding: 10px 20px;
            border: 1px solid #ccc;
            border-bottom: none;
            cursor: pointer;
            background-color: #f0f0f0;
        }
        .tab.active {
            background-color: white;
            font-weight: bold;
        }
        .tab-content {
            border: 1px solid #ccc;
            padding: 20px;
        }
        .error {
            color: red;
        }
    </style>
    <script>
        function showTab(tabId) {
            document.getElementById("studentTab").style.display = "none";
            document.getElementById("teacherTab").style.display = "none";
            document.getElementById(tabId).style.display = "block";

            document.getElementById("studentTabBtn").classList.remove("active");
            document.getElementById("teacherTabBtn").classList.remove("active");
            document.getElementById(tabId + "Btn").classList.add("active");
        }

        window.onload = function () {
            var defaultTab = '<c:out value="${userType}" default="student" />';
            if (defaultTab === 'teacher') {
                showTab('teacherTab');
            } else {
                showTab('studentTab');
            }
        };
    </script>
</head>
<body>
    <div class="tabs">
        <div id="studentTabBtn" class="tab" onclick="showTab('studentTab')">학생 로그인</div>
        <div id="teacherTabBtn" class="tab" onclick="showTab('teacherTab')">선생님 로그인</div>
    </div>

    <div class="tab-content">

        <!-- 학생 로그인 -->
        <div id="studentTab" style="display:none;">
            <form:form modelAttribute="loginForm" method="post" action="${pageContext.request.contextPath}/login/student">
                아이디: <form:input path="id" /><br/>
                <form:errors path="id" cssClass="error"/><br/>
                이름: <form:input path="name" /><br/>
                <form:errors path="name" cssClass="error"/><br/>
                <input type="submit" value="로그인" />
            </form:form>

            <c:if test="${not empty errorMessage}">
                <div class="error">${errorMessage}</div>
            </c:if>
        </div>

        <!-- 선생님 로그인 -->
        <div id="teacherTab" style="display:none;">
            <form:form modelAttribute="loginForm" method="post" action="${pageContext.request.contextPath}/login/teacher">
                아이디: <form:input path="id" /><br/>
                <form:errors path="id" cssClass="error"/><br/>
                이름: <form:input path="name" /><br/>
                <form:errors path="name" cssClass="error"/><br/>
                <input type="submit" value="로그인" />
            </form:form>

            <c:if test="${not empty errorMessage}">
                <div class="error">${errorMessage}</div>
            </c:if>
        </div>

    </div>
</body>
</html>
