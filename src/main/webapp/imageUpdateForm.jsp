<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "image.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<% ImageBBS dto = (ImageBBS)request.getAttribute("IMAGE"); %>
<div align="center">
<form action = "imageUpdateDo.do" method="post" enctype="multipart/form-data" 
	onsubmit="return check(this)">
<input type="hidden" name="id" value="<%= dto.getW_id() %>"/>
<input type="hidden" name="pwd" value="<%= dto.getPassword() %>"/>
<input type="hidden" name="old_image" value="<%= dto.getImagename() %>"/>
<table>
	<tr><th>글제목</th><td><input type="text" name="TITLE" value="<%= dto.getTitle() %>"/>
		</td></tr>
	<tr><th>작성자</th><td><%= dto.getWriter() %></td></tr>
	<tr><th>작성일</th><td><%= dto.getW_date() %></td></tr>
	<tr><th>암 호</th><td><input type="password" name="PWD"/></td></tr>
	<tr><td colspan="2" align="center"><input type="file" name="image_name"/><br/>
		<img alt="" src="upload/<%= dto.getImagename() %>" width="250" height="200"/></td></tr>
	<tr><th>내 용</th><td><textarea rows="5" cols="60" 
		name="CONTENT"><%= dto.getContent() %></textarea></td></tr>
	<tr><td colspan="2" align="center"><input type="submit" value="수 정"/>
		<input type="reset" value="취소"/></td></tr>
</table>
</form>
<script type="text/javascript">
function check(frm){
	if(frm.TITLE.value==''){
		alert("제목을 입력하세요."); frm.TITLE.focus(); return false;
	}
	if(frm.PWD.value==''){
		alert("암호를 입력하세요."); frm.PWD.focus(); return false;
	} else{
		if(frm.pwd.value != frm.PWD.value){ //입력한 암호와 DB의 암호가 일치하지 않는 경우
			alert("암호가 일치하지 않습니다. 암호를 확인하세요."); frm.PWD.focus(); return false;
		}
	}
	if(frm.CONTENT.value==''){
		alert("내용을 입력하세요."); frm.CONTENT.focus(); return false;	
	}
	if( ! confrim("정말로 수정하시겠습니까?")) return false;
}
</script>
</div>
</body>
</html>