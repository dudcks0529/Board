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
<%
	ImageBBS dto = (ImageBBS)request.getAttribute("DETAIL");
%>
<div align="center">
<h3>�̹��� �Խñ� �󼼺���</h3>
<table>
	<tr><th>�� ��</th><td><%= dto.getTitle() %></td></tr>
	<tr><th>�ۼ���</th><td><%= dto.getWriter() %></td></tr>
	<tr><th>�ۼ���</th><td><%= dto.getW_date() %></td></tr>
	<tr><td colspan="2" align="center"><img alt="" src="upload/<%= dto.getImagename() %>"
			width="250" height="200"/></td></tr>
	<tr><th>�� ��</th><td><textarea rows="5" cols="60" 
			readonly="readonly"><%= dto.getContent() %></textarea></td></tr>
	<tr><td colspan="2" align="center">
		<a href="javascript:goReply()">[���]</a>
		<a href="javascript:goModify()">[����]</a>
		<a href="javascript:goDelete()">[����]</a>
		<a href="imageList.do">[���]</a></td></tr>
</table>
</div>
<form name="frm" method="post">
	<input type="hidden" name="id" value="<%= dto.getW_id() %>">
	<input type="hidden" name="parentid" value="<%= dto.getW_id() %>">
	<input type="hidden" name="groupid" value="<%= dto.getGroup_id() %>">
</form>
<!-- form�� action�� �Ʒ��� �ڹٽ�ũ��Ʈ���� ä�� -->
<script type="text/javascript">
function goReply(){
   document.frm.action = "imageForm.do";
   document.frm.submit();
}
function goModify(){
   document.frm.action = "imageModify.do";
   document.frm.submit();//���� ȣ��
}
function goDelete(){
   document.frm.action = "imageDelete.do";
   document.frm.submit();//���� ȣ��
}
</script>

</body>
</html>