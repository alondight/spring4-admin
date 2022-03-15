<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="kr.co.thesmc.model.Notice"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
boolean update = false;
Notice n = (Notice)request.getAttribute("notice");
if(n != null ) {
	update = true;
}
%>
<!doctype html>
<html lang="ko"> 
<head> 
<title>BOARD</title>
<%@ include file="/resources/adm_super/include/docHead.jsp" %>
<!-- datepicker ui추가 -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script type="text/javascript" src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script type="text/javascript">
//전역변수
var oEditors = [];

$(function(){
	//스마트에디터 프레임생성
	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: "editor",
		sSkinURI: "<%=request.getContextPath()%>/resources/editor/SmartEditor2Skin.html",
		htParams : {
			bUseToolbar : true,
			bUseVerticalResizer : true,
			bUseModeChanger : true,
		}
	})

	//datepicker
	$("#regdate").datepicker({dateFormat:"yy-mm-dd"});
	$("#sdate").datepicker({dateFormat:"yy-mm-dd"});
	$("#edate").datepicker({dateFormat:"yy-mm-dd"});
});


function writeChk(){
	oEditors.getById["editor"].exec("UPDATE_CONTENTS_FIELD", []);
	if(document.frm.editor.value == "<p>&nbsp;</p>"){alert("내용을 입력해 주세요.");document.frm.editor.focus();return false;}
	checkForm(document.frm);
};

<%if(!update){%>
$(window).load(function() {
	var d = new Date();
	var month = parseFloat(d.getMonth())+1;
	var day = parseFloat(d.getDate());
	if( day < 10  ){
		day = ""+day;
		day = "0"+day;
	}
	if (month  < 10 ){
		month = ""+month;
		month = "0"+month;
	}
	$("#regdate").val(d.getFullYear()+"-"+month+"-"+day);
});
<%}%>

</script>
</head> 
<body> 
<!-- s: skip navigation -->
<ul id="skipnavigation">
	<li><a href="#contents">본문 바로가기</a></li>
</ul>
<div id="wrap">
	<%@ include file="/resources/adm_super/include/header.jsp" %>	
	<div id="contentWrap">
		<div id="contWrap">
			<%@ include file="/resources/adm_super/include/lnb.jsp" %>	
			<div id="contents">
				<h3 class="subTitle">게시판 관리</h3>

				<form id="frm" name="frm" <%if(update)out.print("action='notice_update'");else out.print("action='notice_write'"); %>  method="post">
				<%if(update){%><input type="hidden" name="id" value="${notice.id}"><%}%>
				<table class="tbs01 view01">
				<caption>공지사항 게시판입니다.</caption>
				<colgroup>
					<col width="10%" /><col width="40%" /><col width="10%" /><col width="40%" />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">제목</th>
						<td colspan="3"><input type="text" id="title" name="title" value="${notice.title}" maxlength="200" msg="제목을"  style="width:600px"></td>
					</tr>
					<tr>
						<th scope="row">작성자</th>
						<td><input type="text" id="name" name="name"  value="${notice.name}" maxlength="50" msg="이름을"></td>
						<th scope="row">작성일</th>
						<td><input type="text" id="regdate" name="regdate"  value="${notice.regdate}" maxlength="10"  msg="작성일을"></td>
					</tr>
					<tr>
						<th scope="row">게시여부</th>
						<td colspan="3">
							<select id="display" name="display" msg="게시여부를">
								<option value="">미입력</option>
								<option <%if(update){if(("on").equals(n.getDisplay()))out.print("selected");}%> value="on">게시</option>
								<option <%if(update){if(("off").equals(n.getDisplay()))out.print("selected");}%> value="off">미게시</option>
							</select>
						</td>
					</tr>
					<tr>
						<th scope="row">시작일</th>
						<td><input type="text" id="sdate" name="sdate"  maxlength="10" value="${notice.sdate}" msg="시작일을"></td>
						<th scope="row">종료일</th>
						<td><input type="text" id="edate" name="edate"  maxlength="10" value="${notice.edate}" msg="종료일을"></td>
					</tr>
					<tr>
						<th scope="row">팝업여부</th>
						<td colspan="3">
							<select id="displaypop" name="displaypop" >
								<option <%if(update){if(("on").equals(n.getDisplaypop()))out.print("selected");}%> value="on"  >팝업</option>
								<option <%if(update){if(("off").equals(n.getDisplaypop()))out.print("selected");}%> value="off" >미팝업</option>
							</select>
						</td>
					</tr>
					<tr>
						<th scope="row">내용</th>
						<td colspan="3">
							<textarea id="editor" name="editor" class="inputText" rows="10" cols="5" maxlength="400" style="width:610px;height:420px">${notice.editor}</textarea>
						</td>
					</tr>
				</tbody>
				</table>
				</form>

				<div class="box1 st01">
					<div class="tac">
						<a href="#none" onclick="writeChk();"><span class="btn2">저장</span></a>
						<a href="notice_list"><span class="btn1">취소</span></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/resources/adm_super/include/footer.jsp" %>
</div>
</body>
</html>
