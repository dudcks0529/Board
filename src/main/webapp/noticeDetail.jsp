<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "notice.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	NoticeDTO dto = (NoticeDTO)request.getAttribute("NOTICE");
	String id = (String)session.getAttribute("ID");
%>
<h3 align="center">������ �󼼺���</h3>
<form action="noticeModify.do" method="post" onsubmit="return check()">
<!-- onsubmit���� return ���� true�̸� action�� ȣ��, false�̸� �ƹ��ϵ� X -->
<input type="hidden" name="NO" value="<%= dto.getNum() %>"/>
<table>
	<tr><th>�۹�ȣ</th><td><%= dto.getNum() %></td></tr>
   <tr><th>�� ��</th><td><input type="text" name="TITLE" value="<%= dto.getTitle() %>"></td></tr>
   <tr><th>�ۼ���</th><td><%= dto.getWriter() %></td></tr>
   <tr><th>�ۼ���</th><td><%= dto.getNotice_date() %></td></tr>
   <tr><th>�� ��</th><td><textarea rows="5" name="CONTENT"
            cols="40"><%= dto.getContent() %></textarea></td></tr>
<% if(id != null && id.equals("admin")){ %>
	<tr><td colspan="2" align="center"><input type="submit" value="����" name="BTN"/>
		<input type="submit" value="����" name="BTN"/> </td></tr>
<% } %>
</table>
</form>
<script type="text/javascript">
function check(){
	if( ! confirm("������ �ش� �۾��� �����Ͻðڽ��ϱ�?")) return false;
}
</script>
</body>
</html>