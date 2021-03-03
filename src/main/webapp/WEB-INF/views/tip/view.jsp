<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>사장님을 위한 꿀팁모음</h4><div class='btnSet' >
	<a class='btn-fill' href='list.tip?curPage=${page.curPage}&search=${page.search}&keyword=${page.keyword}'>목록으로</a>
	<!-- 본인의 문의만 수정/삭제 권한 부여 -->
	<c:if test="${loginInfo.member_code eq vo.board_member_code }">
		<a class='btn-fill' href='modify.tip?board_code=${vo.board_code}'>수정</a>
		<a class='btn-fill' onclick="if(confirm('정말 삭제하시겠습니까?')){location='delete.tip?board_code=${vo.board_code}'}">삭제</a>
	</c:if>
</div>

		
		<div style="margin: 30px auto; font-size: 24px; font-weight: bold;">${vo.board_subject}</div>
		<div>${fn:replace(vo.board_content, crlf,'<br>')}</div>


<form action="list.tip" method="post">
	<input type="hidden" name="board_code" value='${vo.board_code}'/>
	<input type="hidden" name="curPage" value='${page.curPage}'/>
	<input type="hidden" name="search" value='${page.search}'/>
	<input type="hidden" name="keyword" value='${page.keyword}'/>
	<input type="hidden" name="pageList" value='${page.pageList}'/>
	<input type="hidden" name="viewType" value='${page.viewType}'/>
</form>

<div style='margin:0 auto; padding-top:20px; width:500px'>
	<div id='comment_regist'>
		<span class='left'><strong>댓글작성</strong></span>
		<span class='right'>
			<a class='btn-fill-s' onclick='comment_regist()'>댓글등록</a>
		</span>
		<textarea id='comment' style='margin-top:5px; width:96%; height:60px; resize:none'></textarea>
	</div>
	<div id='comment_list' style='text-align:left'>
	</div>
</div>

<div id='popup-background' onclick="$('#popup, #popup-background').css('display', 'none');"></div>
<div id='popup'></div>

<script type="text/javascript" src="js/file_check.js"></script>
<script type="text/javascript">
comment_list();
function comment_regist(){
	if( ${empty loginInfo} ){
		alert('댓글을 등록하려면 로그인하세요!');
		return;
	}else if( $.trim($('#comment').val())=='' ){
		alert('댓글을 입력하세요!');
		$('#comment').val('');
		$('#comment').focus();
		return;
	}

	$.ajax({
		url: 'tip/comment/insert',
		data: { reply_sub_code:${vo.board_code}, reply_content:$('#comment').val() },
		success: function( response ){
			if( response ){
				alert('댓글이 등록되었습니다!');
				$('#comment').val('');
				comment_list();
			}else{
				alert('댓글 등록이 실패하였습니다');
			}
		},error: function(req, text){
			alert(text+ ':'+ req.status);
		}
	});
}

function comment_list() {
	$.ajax({
		url: 'tip/comment/${vo.board_code}',
		success: function( response ) {
			$('#comment_list').html(response);
			
		}, error: function(req, text){
			alert(text+ ':' + req.status);
		}
	});
}

$(function() {
	//첨부된 파일이 이미지라면 미리보기에 이미지가 보이게
	if( ${ !empty vo.board_file}) {
		if( isImage('${vo.board_file}') ) {
			var img = '<img src="${vo.board_filepath}" class="file-img" id="preview-img" />'
			$('#preview').html(img);
		}
	}
});
$(document).on('click', '#preview-img', function() {
	$('#popup, #popup-background').css('display', 'block');
	var img = '<img src="${vo.board_filepath}" class="popup" id="preview-img" />'
	$('#popup').html(img);
});

</script>
</body>
</html>