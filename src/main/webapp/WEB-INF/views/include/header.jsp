<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header style='border-bottom:1px solid #ccc; padding:6px 0; text-align:left'>
	<div class="category" style="margin-left: 50px">
		<ul>
			<li><a href="<c:url value='/' />"><img src="imgs/hanul.logo.png" /></a></li>
			<li class='sub-menu'>
				<a href="#">예약자바 <i class='fa fa-angle-down'></i>
				</a>
				<ul>
					<li><a href='intro.rj'> 회사 소개 </a></li>
					<li><a href='guide.rj'> 이용 가이드 </a></li>
					<li><a href='intro_serv.rj'> 서비스 소개 </a></li>
				</ul>
			</li>
			<li class='sub-menu'>
				<a href="#">매장 관리 <i class='fa fa-angle-down'></i>
				</a>
				<ul>
					<li><a href='list.bu'> 매장 보기 </a></li>
					<li><a href='list.pr'> 상품 보기 </a></li>
				</ul>
			</li>
			<li class='sub-menu'>
				<a href="#">예약 내역 <i class='fa fa-angle-down'></i>
				</a>
				<ul>
					<li><a href='list_res.re'> 예약 현황 </a></li>
					<li><a href='list_pay.re'> 결제내역 조회 </a></li>
				</ul>
			</li>
			<li>
				<a href="list.tip">TIP 게시판</a></li>
			<li class='sub-menu'>
				<a href="#">고객지원 <i class='fa fa-angle-down'></i>
				</a>
				<ul>
					<li><a href='list.no'> 공지사항 </a></li>
					<li><a href='list.qu'> 문의하기 </a></li>
				</ul>
			</li>
		</ul>
	</div>
	<div style="position:absolute; right:50px; top:14px; margin-right:0px">
		<ul>
			<!-- 로그인하지 않은 경우 -->
			<c:if test="${empty loginInfo}">
			<li><a class='btn-fill' href='login'>로그인</a></li>
			<li><a class='btn-fill' href='join'>회원가입</a></li>
			</c:if>
			<!-- 로그인 한 경우 -->
			<c:if test="${!empty loginInfo}">
			<li style='padding-right:10px'><strong>${loginInfo.member_name }</strong>님</li>
						<li><a class='btn-fill' href='login'>회원정보</a></li>
			<li><a class='btn-fill' href='logout'>로그아웃</a></li>
			</c:if>
		</ul>
	</div>
</header>
<style>

</style>
<script type="text/javascript">
$(".sub-menu ul").hide();
$(".sub-menu a").click(function () {
  $(this).parent(".sub-menu").children("ul").slideToggle("200");
  $(this).find("i.fa").toggleClass("fa-angle-up fa-angle-down");
});
</script>
