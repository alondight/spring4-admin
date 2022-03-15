<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>파일</title>
 
 <!-- css -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/dataTables.bootstrap4.min.css" />
<!-- js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/dataTables.bootstrap4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/popper.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<form action="<%=request.getContextPath()%>/upload.file" method="post" enctype="multipart/form-data">
			<span class="btn btn-success fileinput-button">
				<i class="glyphicon glyphicon-plus"></i>
				<span>파일추가</span>
				<input id="fileupload" type="file" name="upFile" />
			</span>
			<input type="submit" value="File Upload">
		</form>
	</div>
</body>
</html>