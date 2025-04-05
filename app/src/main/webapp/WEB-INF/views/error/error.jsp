<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>에러 발생</title>
</head>
<body>
    <h2>죄송합니다. 오류가 발생했습니다.</h2>
    <p>요청하신 페이지를 찾을 수 없거나, 서버 오류가 발생했습니다.</p>
    <p>에러 메시지: ${errorMessage}</p>
    <a href="${pageContext.request.contextPath}/">메인으로 돌아가기</a>
</body>
</html>
