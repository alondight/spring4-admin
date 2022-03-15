<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head lang="kor">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>BOOKS</title>

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
	<form action="<%=request.getContextPath()%>/formAdd" method="post" >

	<div class="form-group">
		<label for="exampleInputEmail1">Email address</label>
		<input type="text" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" value="">
		<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
	</div>

	<div class="form-group">
		<label for="exampleInputPassword1">Password</label>
		<input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
	</div>

	<div class="form-group">
		<label for="exampleSelect1">Example select</label>
		<select class="form-control" id="exampleSelect1" name="selectOption">
			<option>1</option>
			<option>2</option>
			<option>3</option>
			<option>4</option>
			<option>5</option>
			</select>
	</div>

	<div class="form-group">
		<label for="exampleTextarea">Example textarea</label>
		<textarea  name="contents" class="form-control" id="exampleTextarea" rows="3"></textarea>
	</div>

	<fieldset class="form-group">
		<legend>Radio buttons</legend>
		<div class="form-check">
			<label class="form-check-label">
			<input type="radio" class="form-check-input" name="optionsRadios" id="optionsRadios1" value="option1" checked>
			Option one is this and that&mdash;be sure to include why it's great
			</label>
		</div>
		<div class="form-check">
		<label class="form-check-label">
			<input type="radio" class="form-check-input" name="optionsRadios" id="optionsRadios2" value="option2">
			Option two can be something else and selecting it will deselect option one
		</label>
		</div>
		<div class="form-check disabled">
		<label class="form-check-label">
			<input type="radio" class="form-check-input" name="optionsRadios" id="optionsRadios3" value="option3" disabled>
			Option three is disabled
			</label>
		</div>
	</fieldset>
	<button type="submit" class="btn btn-primary">Submit</button>
	</form>
</body>
</html>