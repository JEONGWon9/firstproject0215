<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<title>Insert title here</title>
</head>
<body>
	<h1 align="center"> 글쓰기 ! </h1>
	<br><br>
	
	<div class="container" >
	<form action="/notice"method="post" >
		<table class="table table-bordered" >
		<tr>
			<th> 작성자	</th>
			<td> <input type="text" name="writer"> </td>
			<th> 비밀번호</th>
			<td> <input type="password" name="password"></td>
		</tr>
		<tr>
			<th> 제목</th>
			<td colspan="3"> <input type="text" name="title" style="width:650px; height:auto;"> </td>
		</tr>
		<tr>
			<th> 내용	</th>
			<td colspan="3"> 
			<textarea name="content" rows="5" cols="40" style="width:650px; height: auto;"></textarea> 
			</td>
		</tr>
		
		</table>	
		<button type="submit">글쓰기</button>
	</form>
	</div>
</body>
</html>