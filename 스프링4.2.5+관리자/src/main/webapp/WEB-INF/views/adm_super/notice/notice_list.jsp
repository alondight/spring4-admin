<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="ko"> 
<head> 
<title>BOARD</title>
<%@ include file="/resources/adm_super/include/docHead.jsp" %>	
<script>
function goDelete()	{
	if(confirm("삭제하시겠습니까?")) {
		if($('input:checkbox[name=delChk]').is(":checked")== false) {
			alert("삭제할 정보를 선택해 주세요.");return false;
		} else {
			document.frm.action = "notice_delete";
			document.frm.submit();
		}
	}
}
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

				<form id="frm" name="frm" method="get">
				<table class="tbs01">
				<caption>공지사항 리스트입니다.</caption>
				<colgroup>
					<col width="10%"><col width="*"><col width="12%"><col width="12%"><col width="8%"><col width="5%">
				</colgroup>
				<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">작성일</th>
					<th scope="col">전시여부</th>
					<th scope="col"></th>
				</tr>
				</thead>
				<tbody>
					<c:forEach var="n" items="${list}">
						<tr>
							<td>${dpNum}</td>
							<td class="tit"><a href="notice_view?id=${n.id}">${n.title}</a></td>
							<td>${n.name}</td>
							<td>${n.regdate}</td>
							<td>
							<c:choose>
								<c:when test="${n.display == 'on'}">게시</c:when>
								<c:when test="${n.display == 'off'}">미게시</c:when>
							</c:choose>
							</td>
							<td><input type="checkbox" name="delChk" value="${n.id}"></td>
						</tr>
						<c:set var="dpNum" value="${dpNum - 1}" scope="request" />	
					</c:forEach>
				</tbody>
				</table>

				<div class="box1">
					<div class="paging mb15">
						${pgSet}
					</div>	
					<div class="tac">
						<select id="sg" name="sg" title="검색 구분을 선택해주세요" class="sel1" style="width:58px">
							<option ${sg == 'title' ? 'selected="selected"' : '' } value="title">제목</option>
							<option ${sg == 'content' ? 'selected="selected"' : '' } value="content">내용</option>
						</select>
						<label for="sw" class="blind">이슈&뉴스 검색</label>
						<input type="text" id="sw" name="sw" style="width:160px" value="${sw}" /> 
						<input type="image" src="<%=request.getContextPath()%>/resources/adm_super/images/common/btn_search.gif" alt="검색" title="검색" />

						<!-- 
						<a href="notice_view"><span class="btn2">작성하기</span></a>
						<a href="#none" onclick="goDelete();"><span class="btn1">선택삭제</span></a>
						 -->
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>	
	<%@ include file="/resources/adm_super/include/footer.jsp" %>
</div>
</body>
</html>
