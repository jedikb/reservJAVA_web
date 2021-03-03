<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:forEach items="${list}" var="vo" varStatus="status">
${status.index eq 0 ? '<hr>' : ''}
<div data-reply_code='${vo.reply_code}'>${vo.member_name} [ ${vo.reply_update_date} ]
	<c:if test="${vo.reply_member_code eq loginInfo.member_code}">
		<span style='float: right;'>
			<a class='btn-fill-s btn-modify-save'>수정</a>
			<a class='btn-fill-s btn-delete-cancel'>삭제</a>
		</span>
	</c:if>
	<div class='original'>${fn: replace(fn: replace(vo.reply_content, lf, '<br>'), crlf, '<br>')}</div>
	<div class='modify' style="display:none; margin-top:6px;"></div>
</div>
<hr>
</c:forEach>

<script>
// 댓글 수정-저장
$('.btn-modify-save').on('click', function(){
	var $div = $(this).closest('div');
	
	if($(this).text()=='수정') {
		$div.children('.modify').css('height', $div.children('.original').height()-6);
		//원글이 textarea에 올라와 있게 한다
		var tag = '<textarea style="width:96%; height:90%; resize:none">'
							+ $div.children('.original').html().replace(/<br>/g, '\n')
							+ '</textarea>';
		$div.children('.modify').html(tag);
		display($div, 'm')
	} else {
		
		var comment = {reply_code: $div.data('reply_code'), reply_content:$div.children('.modify').find('textarea').val()};
/* 		console.log(comment);
		return; */
		$.ajax({
			type: 'post',
			url: 'notice/comment/update',
			data: JSON.stringify(comment),
			contentType: 'application/json',
			success: function( response ) {
				alert('댓글이 변경 되었습니다');
				comment_list();
			}, error: function(req, text) {
				alert(text+':' + req.status);
			}
		});
	}
	
});

// 보여지는 상태(보기/수정)에 따라 버튼이 달라진다.
// 보기상태 : 수정/삭제 버튼, 원글이 보여지고 변경 글은 안보이게
// 수정상태 : 저장/취소 버튼, 원글이 안 보이고 변경 글이 보이게
function display(div, mode) {
	div.find('.btn-modify-save').text( mode=='m' ? '저장' : '수정');
	div.find('.btn-delete-cancel').text( mode=='m' ? '취소' : '삭제');
	div.children('.original').css('display', mode=='m' ? 'none' : 'block');
	div.children('.modify').css('display', mode=='m' ? 'block' : 'none');
}

// 댓글 삭제-취소
$('.btn-delete-cancel').on('click', function(){
	var $div = $(this).closest('div');
	if($(this).text() == '취소') {
		display( $div,'d');
	} else {
		if( confirm('정말 삭제하시겠습니까?') ){
			$.ajax({
				url: 'notice/comment/delete/' + $div.data('reply_code'),
				success: function() {
					comment_list();
				}, error: function(req, text) {
					alert(text+':' + req.status);
				}
			});
		}
	}
});

</script>