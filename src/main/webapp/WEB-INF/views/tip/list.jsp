<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Tip 게시판</h3>

<div id='list-top'>
<form action="list.tip" method="post">
<div>
	<ul>
		<li><select name='search' class='w-px80'>
			<option value='all' ${page.search eq 'all' ? 'selected' : ''}>전체</option>
			<option value='board_subject' ${page.search eq 'board_subject' ? 'selected' : ''}>제목</option>
			<option value='board_content' ${page.search eq 'board_content' ? 'selected' : ''}>내용</option>
			<option value='member_name' ${page.search eq 'member_name' ? 'selected' : ''}>작성자</option>
			</select>
		</li>
		<li><input type="text" name='keyword' value='${page.keyword}' class='w-px300' />
		</li>
		<li><a class='btn-fill' onclick="$('form').submit()">검색</a></li>
	</ul>
	<ul>
		<li>
<!-- 			<select name='pageList' class='w-px80' -->
<!-- 					onchange="$('[name=curPage]').val(1); $('form').submit()"> -->
<%-- 				<option value='9' ${page.pageList eq 9 ? 'selected' : ''}>9개</option> --%>
<%-- 				<option value='18' ${page.pageList eq 18 ? 'selected' : ''}>18개</option> --%>
<%-- 				<option value='27' ${page.pageList eq 27 ? 'selected' : ''}>27개</option> --%>
<!-- 			</select> -->
		</li>		
		<!-- Tip 게시판은 그리드로 한다 -->		
		<!-- 로그인한 경우 글쓰기 가능 -->
		<c:if test="${!empty loginInfo}">
			<li><a class='btn-fill' href='new.tip'>글쓰기</a></li>
		</c:if>
	</ul>
</div>
<input type='hidden' name='curPage' value='1'/>
<input type="hidden" name='board_code' />
</form>
</div>	

<div id='data-list'>

<ul class='grid'>
	<c:forEach items="${page.list}" var="vo">
		<li>
			<a href='javascript:go_view(${vo.board_code})'>
				<div><img src="${vo.board_filepath}"></div>
				<div>${vo.board_subject}</div>
			</a>
			<%-- <div>${vo.member_name}</div> --%>
			<%-- <div><fmt:formatDate value="${vo.board_update_date}" pattern="yyyy-MM-dd"/></div> --%>
		</li>
	</c:forEach>
</ul>

</div>


<script type="text/javascript">
function go_view(board_code){
	$('[name=board_code]').val(board_code);
	$('form').attr('action', 'view.tip');
	$('form').submit();
}

$(function(){
	var len = $('.grid li').length;
	var height = ( ( len % 3 > 0 ? 1 : 0 ) + Math.floor(len / 3) ) 
					* $('.grid li').outerHeight(true) - 20;  
	
	$('#data-list ul.grid').css('height', height );
});

</script>
</body>
</html>