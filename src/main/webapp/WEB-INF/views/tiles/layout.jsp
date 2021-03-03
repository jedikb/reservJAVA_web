<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:choose>
	<c:when test="${category eq 'rj'}">
		<c:set var="title" value="예약자바 : "/>
	</c:when>
	<c:when test="${category eq 'bu'}">
		<c:set var="title" value="매장관리 : "/>
	</c:when>
	<c:when test="${category eq 'pr'}">
		<c:set var="title" value="제품관리 : "/>
	</c:when>
	<c:when test="${category eq 're'}">
		<c:set var="title" value="예약내역 : "/>
	</c:when>
	<c:when test="${category eq 'tip'}">
		<c:set var="title" value="TIP게시판 : "/>
	</c:when>
	<c:when test="${category eq 'no'}">
		<c:set var="title" value="공지사항 : "/>
	</c:when>
	<c:when test="${category eq 'qu'}">
		<c:set var="title" value="문의하기 : "/>
	</c:when>
	<c:when test="${category eq 'login'}">
		<c:set var="title" value="로그인 : "/>
	</c:when>
	<c:when test="${category eq 'join'}">
		<c:set var="title" value="회원가입 : "/>
	</c:when>
</c:choose>

<title>${title}Reserv</title>
<!-- <link rel="icon" type="image/x-icon" href="imgs/hanul.ico"> -->
 <link rel="stylesheet" type="text/css" 
	href="css/common.css?v=<%=new java.util.Date().getTime()  %>" /> 
<script src='https://code.jquery.com/jquery-3.5.1.min.js'></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/js/all.min.js"></script>

</head>
<body>
<tiles:insertAttribute name="header" />
<div id='content'>
<tiles:insertAttribute name="content" />
</div>
<tiles:insertAttribute name="footer" />
</body>
</html>