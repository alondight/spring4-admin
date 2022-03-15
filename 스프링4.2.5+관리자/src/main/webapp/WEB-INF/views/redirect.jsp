<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko"> 
<head> 
<title>location</title>
</head> 
<body> 
<%
String msg = "";
String url = "";
msg = (String)request.getAttribute("msg");
url = (String)request.getAttribute("url");
if( msg == null ){
	msg = "잘못된 경로로 접근하였습니다.";
	url = "/";
}

%>
	<script>
	var msg = "<%=msg%>";
	var url = "<%=request.getContextPath()+url%>";
	alert(msg);
	location.href=url;
	</script>
</body>
</html>
