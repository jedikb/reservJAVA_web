<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%SimpleDateFormat df = new SimpleDateFormat("yyyy년 MM월 dd일");
String dfNow = df.format(System.currentTimeMillis());%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 예약자 명단</title>
</head>
<body>

<div>${vo.member_nick}</div>
</body>
</html>