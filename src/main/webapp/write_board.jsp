<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<h3 align="center">게시글 쓰기</h3>
<form action="writeBoard.do" method="post">
<p>자유롭게 글을 작성하신 후 글올리기 버튼을 누르면, 작성한 글이 게시글에 올라갑니다.</p>
<table border="1">
	<tr><th>글제목</th><td><input type="text" name="TITLE" size="30"/></td></tr>
	<tr><th>글내용</th><td><textarea rows="4" cols="50" name="CONTENT"></textarea></td></tr>
	<tr><td colspan="2" align="center"><input type="submit" value="글 올리기"/>
						<input type="reset" value="취 소"/></td></tr>
</table>
</form>
</div>
</body>
</html>










