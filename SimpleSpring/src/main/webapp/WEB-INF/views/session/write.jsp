<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
	<!-- Twitter Bootstrap3 & jQuery -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<h1>세션 예제</h1>
	
	<%-- JSTL을 통해 쿠키에 직접 접근하기 --%>
	<c:choose>
		<c:when test="${my_session_value == null}">
			<h2>저장된 세션 없음</h2>
		</c:when>
		<c:otherwise>
			<h2>저장된 세션=${my_session_value}</h2>
		</c:otherwise>
	</c:choose>
	
	<%-- 컨트롤러에서 Model 객체를 통해 넘어온 값 출력하기 --%>
	<c:choose>
		<c:when test="${my_session == ''}">
			<h2>컨트롤러에서 식별한 세션 없음</h2>
		</c:when>
		<c:otherwise>
			<h2>컨트롤러에서 식별한 세션=${my_session}</h2>
		</c:otherwise>
	</c:choose>
	
	<form method="post" action="${pageContext.request.contextPath}/session/save.do">
		<label for="user_input">저장할 내용 입력</label>
		<input type="text" name="user_input" id="user_input" />
		<button type="submit">세션저장</button>
	</form>
</body>
</html>
