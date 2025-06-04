<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="image.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<% ImageBBS dto = (ImageBBS)request.getAttribute("DETAIL"); %>
<div align="center">
<h3>이미지 게시글 상세 보기</h3>
<form action="imageDeleteDo.do" method="post" onsubmit="return check(this)">
<input type="hidden" name="id" value="<%= dto.getW_id() %>"/>
<input type="hidden" name="pwd" value="<%= dto.getPassword() %>"/>
<table>
	<tr><th>제 목</th><td><%= dto.getTitle() %></td></tr>
	<tr><th>작성자</th><td><%= dto.getWriter() %></td></tr>
	<tr><th>암 호</th><td><input type="password" name="PWD"/></td></tr>
	<tr><th>작성일</th><td><%= dto.getW_date() %></td></tr>
	<tr><td colspan="2" align="center"><img alt="" src="upload/<%= dto.getImagename() %>"
			width="250" height="200"/></td></tr>
	<tr><th>내 용</th><td><textarea rows="5" cols="60" 
			readonly="readonly"><%= dto.getContent() %></textarea></td></tr>
	<tr><td colspan="2" align="center"><input type="submit" value="삭제"/>
		<input type="reset" value="취 소"/></td></tr>
</table>
</form>
<script type="text/javascript">
function check(frm){
	if(frm.PWD.value == ''){
		alert("암호를 입력하세요."); frm.PWD.focus(); return false;
	} else{
		if(frm.pwd.value != frm.PWD.value){ //입력한 암호와 DB의 암호가 다른 경우
			alert("입력한 암호와 게시글의 암호가 일치하지 않습니다. 암호를 확인하세요.");
			frm.PWD.value
			return false;
		}
	}
	if( ! comfirm("정말로 삭제하시겠습니까?")) return false;
}
</script>
</div>
</body>
</html>