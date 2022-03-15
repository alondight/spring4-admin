<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="ko"> 
<head> 
<title>로그인</title>
<%@ include file="/resources/adm_super/include/docHead.jsp" %>
<script>
$(function(){
	$("#loginTel").keypress(function (e) {
		if (e.which == 13) login();
	});
})
function login(){
	checkForm(document.frm);
}

</script>	
</head> 
<body> 
<!-- s: skip navigation -->
<ul id="skipnavigation">
	<li><a href="#mainContents">본문 바로가기</a></li>
</ul>
<div id="wrap">
	<%@ include file="/resources/adm_super/include/header.jsp" %>	
	<div id="contentWrap">
		<div id="contWrap">
			<div id="mainContents">
				<form name="frm" action="/adm_super/notice_list" method="get" > 
						<div id="login">
							<div class="inputArea" >
								<div class="id_input"><input msg="EMAIL을" chkemail type="text" id="loginName" name="name" value="" placeholder="EMAIL을 입력해주세요" ></div>
								<div class="pw_input"><input msg ="비밀번호를" type="password" id="loginTel" name="tel"  value=""></div>
							</div>
							<div class="tac" style="padding:70px;"><a href="#none" onclick="login();return false;"><img src="<%=request.getContextPath()%>/resources/adm_super/images/login/btn_login.png" alt="로그인" /></a></div>
						</div>
				</form>
			</div>
		</div>
	</div>	
	<%@ include file="/resources/adm_super/include/footer.jsp" %>
</div>
</body>
</html>
