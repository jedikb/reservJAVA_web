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
<h3>문의 게시판</h3>

<div id='list-top'>
<form action="list.qu" method="post">
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
			<select name='pageList' class='w-px80'
					onchange="$('[name=curPage]').val(1); $('form').submit()">
				<option value='10' ${page.pageList eq 10 ? 'selected' : ''}>10개</option>
				<option value='20' ${page.pageList eq 20 ? 'selected' : ''}>20개</option>
				<option value='30' ${page.pageList eq 30 ? 'selected' : ''}>30개</option>
			</select>
		</li>		
		<!-- 문의 게시판은 그리드일 필요 없음 -->		
		<!-- 로그인한 경우 글쓰기 가능 -->
		<c:if test="${!empty loginInfo}">
		<li><a class='btn-fill' href='new.qu'>글쓰기</a></li>
		</c:if>
	</ul>
</div>
<input type='hidden' name='curPage' value='1'/>
<input type="hidden" name='board_code' />
</form>
</div>	

<div id='data-list'>
<table>
	<tr><th class='w-px80'>번호</th>
			<th>제목</th>
			<th class='w-px120'>작성자</th>
			<th class='w-px120'>작성일자</th>
			<th class='w-px80'>첨부파일</th>
	</tr>
	<c:forEach items='${page.list}' var='vo'>
		<tr><td>${vo.no}</td>
				<td class='left'><a href='javascript:go_view(${vo.board_code})'>${vo.board_subject}</a></td>
				<td>${vo.member_name}</td>
				<td><fmt:formatDate value="${vo.board_update_date}" pattern="yyyy-MM-dd"/></td>
				<td>${empty vo.board_file ? '' : '<img class="file-img" src="imgs/attach.png"/>'}</td>
		</tr>
	</c:forEach>
</table>
</div>

<div class='btnSet'>
	<jsp:include page="/WEB-INF/views/include/page.jsp" />
</div>
<script type="text/javascript">
function go_view(board_code){
	$('[name=board_code]').val(board_code);
	$('form').attr('action', 'view.qu');
	$('form').submit();
}
</script>
</body>
</html>