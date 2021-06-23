<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학과추가</title>
</head>
<body>
	<h1>학과관리</h1>
	<a href="${pageContext.request.contextPath}/department/add.do">[학과추가]</a>
	
	<!-- 검색폼 -->
	<form method="get" action="${pageContext.request.contextPath}/department/list.do">
		<label for="keyword">검색어 : </label>
		<input type="search" name="keyword" id="keyword" placeholder="학과명 or 위치 검색"
			value="${keyword}" />
		<button type="submit">검색</button>
	</form>
	<hr />
	
	<!-- 조회 결과 목록 -->
	<table border="1">
		<thead>
			<tr>
				<th width="100" align="center">학과번호</th>
				<th width="300" align="center">학과이름</th>
				<th width="200" align="center">학과위치</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<%-- 조회결과가 없는 경우 --%>
				<c:when test="${output == null || fn:length(output) == 0}">
					<tr>
						<td colspan="3" align="center">조회결과가 없습니다.</td>
					</tr>
				</c:when>
				<%-- 조회결과가 있는 경우 --%>
				<c:otherwise>
					<%-- 조회 결과에 따른 반복 처리 --%>
					<c:forEach var="item" items="${output}" varStatus="status">
						<%-- 출력을 위해 준비한 학과이름과 위치 --%>
						<c:set var="dname" value="${item.dname}" />
						<c:set var="loc" value="${item.loc}" />
						
						<%-- 검색어가 있다면 --%>
						<c:if test="${keyword != ''}">
							<%-- 검색어에 <mark> 태그를 적용하여 형광팬 효과 준비 --%>
							<c:set var="mark" value="<mark>${keyword}</mark>" />
							<%-- 출력을 위해 준비한 학과이름과 위치에서 검색어와 일치하는 단어를 형광팬 효과로 변경 --%>
							<c:set var="dname" value="${fn:replace(dname, keyword, mark)}" />
							<c:set var="loc" value="${fn:replace(loc, keyword, mark)}" />
						</c:if>
						
						<%-- 상세페이지로 이동하기 위한 URL --%>
						<c:url value="/department/detail.do" var="viewUrl">
							<c:param name="deptno" value="${item.deptno}" />
						</c:url>
						
						<tr>
							<td align="center">${item.deptno}</td>
							<td align="center"><a href="${viewUrl}">${dname}</a></td>
							<td align="center">${loc}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</body>
</html>