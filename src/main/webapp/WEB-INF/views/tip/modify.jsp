<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="js/summernote/summernote-lite.min.js"></script>
<script src="js/summernote/lang/summernote-ko-KR.min.js"></script>
<link rel="stylesheet" href="css/summernote/summernote-lite.min.css">

<script>
var fonts = ['맑은 고딕', '돋움', '궁서', '굴림', '굴림체', '궁서체', '나눔 고딕', '바탕', '바탕체', '새굴림', 'HY견고딕', 'HY견명조', 'HY궁서B', 'HY그래픽M', 'HY목각파임B', 'HY신명조', 'HY얕은샘물M', 'HY엽서L', 'HY엽서M', 'HY중고딕', 'HY헤드라인M', '휴먼매직체', '휴먼모음T', '휴먼아미체', '휴먼둥근헤드라인', '휴먼편지체', '휴먼옛체'];	
$(document).ready(function() {
	
  $('#summernote').summernote({
	  placeholder: '자유롭게 글을 작성할 수 있습니다. <br>명예훼손이나 상대방을 비방, 불쾌감을 주는 글, 욕설, 남을 모욕하는 글은 <br>임의로 제제가 있을 수 있습니다.',
	  height: 350,
	  minHeight: null, 
    maxHeight: null,
    lang: "ko-KR",
    fontNamesIgnoreCheck : [ '맑은고딕' ],
		focus: true,
    tabsize: 2,
    fontNames: fonts.sort(),
		fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
    imageTitle: {
       	specificAltField: true,
       },
    popover: {
       image: [
	       	['imageResize', ['resizeFull', 'resizeHalf', 'resizeQuarter', 'resizeNone']],
	        ['float', ['floatLeft', 'floatRight', 'floatNone']],
	        ['remove', ['removeMedia']],
	        ['custom', ['imageTitle']],
       ],
       link: [
    	    ['link', ['linkDialogShow', 'unlink']]
    	  ],
    	 table: [
    	    ['add', ['addRowDown', 'addRowUp', 'addColLeft', 'addColRight']],
    	    ['delete', ['deleteRow', 'deleteCol', 'deleteTable']],
    	  ],
    	 air: [
    	    ['color', ['color']],
    	    ['font', ['bold', 'underline', 'clear']],
    	    ['para', ['ul', 'paragraph']],
    	    ['table', ['table']],
    	    ['insert', ['link', 'picture']]
    	  ]
    },
	  toolbar: [
			    ['Font Style', ['fontname']],
			    ['fontsize', ['fontsize']],
					['style', ['style']],
			    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
			    ['color', ['forecolor','color']],
			    ['table', ['table']],
			    ['para', ['paragraph']],
			    ['height', ['height']],
			    ['insert', ['picture','link','video']],
			    ['highlight', ['highlight']],
			    ['view', ['fullscreen', 'help']]
		  ],
	  callbacks: {
		  onImageUpload : function(files, editor, welEditable) {  //얘만 들어가면 그림 삽입이 안됨
			  for (var i = files.length - 1; i >= 0; i--) {
	            	sendFile(files[i], this);
	      }
			},
			onPaste: function (e) {
				var clipboardData = e.originalEvent.clipboardData;
				if (clipboardData && clipboardData.items && clipboardData.items.length) {
					var item = clipboardData.items[0];
					if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
						e.preventDefault();
					}
				}
			}
		}
  });
  
  /* 이미지 파일 업로드	*/
	function sendFile(file, editor) {
		data = new FormData();
		data.append("file", file);
		data.append("ca", "tip");
		$.ajax({
			data : data,
			type : "POST",
			url : "uploadSummernoteImageFile",
			contentType : false,
			processData : false,
			success : function(data) {
          	//항상 업로드된 파일의 url이 있어야 한다.
				$(editor).summernote('insertImage', data.url);
			}
		});
	}
});
</script>

</head>
<body>
<h3>Tip글 수정</h3>
<form action="update.tip" method="post" enctype="multipart/form-data">
<table>
	<tr><th class='w-px140'>제목</th>
			<td><input type="text" name='board_subject' class='chk' title='제목'
					value='${vo.board_subject}' /></td>
	</tr>
	<tr><th>내용</th>
			<td><textarea id='summernote' name='board_content' class='chk' title='내용' >${vo.board_content}</textarea>
			</td>
	</tr>
	<tr><th>첨부파일</th>
			<td class='left'>
				<label>
					<input type="file" name='file' id='attach-file' />
					<img src='imgs/select.png' class='file-img' />	
				</label>
				<span id='file-name'>${vo.board_file}</span>
				<span id='preview'></span>
				<span id='delete-file'><i class='fas fa-times'></i></span>
			</td>
	</tr>
</table>
<input type="hidden" name='board_code' value='${vo.board_code}' />
<input type="hidden" name='attach' />
</form>

<div class='btnSet'>
	<a class='btn-fill' 
		 onclick=' $("[name=attach]").val( $("#file-name").text() );  $("form").submit()'>저장</a>
	<a class='btn-empty' onclick='history.go(-1)'>취소</a>
</div>
<script type="text/javascript" 
				src='js/file_check.js?v=<%=new java.util.Date().getTime()%>'></script>
<script type="text/javascript">
if( ${!empty vo.board_file}) {
	$('#delete-file').css('display', 'inline');
	//첨부된 파일이 이미지라면 미리보기에 이미지가 보이게
	if( ${ !empty vo.board_file}) {
		if( isImage('${vo.board_file}') ) {
			var img = '<img src="${vo.board_filepath}" class="file-img" id="preview-img" />'
			$('#preview').html(img);
		}
	}
}

</script>				
</body>
</html>