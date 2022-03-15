<%@ page contentType="text/html" pageEncoding="UTF-8" %>
 <div id="header">
	<div class="topArea">
		<div class="topAccount">
			<p class="adminName"><a href="">관리자 님</a></p>
			<div class="btn_log"><a href="<%=request.getContextPath()%>/adm_super"><img src="<%=request.getContextPath()%>/resources/adm_super/images/common/btn_logout.gif" alt="로그아웃"></a></div>
		</div>
	</div>
	<div class="headWrap">
		<h1><a href=""><img src="" alt=""></a></h1>
		<ul id="gnb">
			<li id="gnb01"><a href="<%=request.getContextPath()%>/adm_super/notice_list">게시판</a></li>
			<!-- <li id="gnb02" class="last"><a href="">채용관리</a></li> -->
		</ul>
	</div>
</div>
