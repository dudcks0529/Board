<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<div align="right"><a href="index.jsp?BODY=userEntry.jsp">�����ϱ�</a></div>
<div align="center">
<% 	String msg = request.getParameter("MSG"); 
	if(msg != null){
%>
	<h4><font color="red">�ش� ���񽺸� �̿��Ͻ÷���, �α����� �ؾ� �մϴ�.</font></h4>
<% } %>
	<form action="Login.do" method="post" onsubmit="return check(this)">
<% if(msg != null){ %>
	<input type="hidden" name="POPUP" value="YES"/>
<% } %>
	���� : <input type="text" name="ID" size="10"/><br/>
	��ȣ : <input type="password" name="PWD" size="10"/><br/><br/>
	<input type="submit" value="�α���"/>
	<input type="reset" value="�� ��"/>
	</form>
</div>
<script type="text/javascript">
function check(frm){
	if(frm.ID.value == ''){
		alert("������ �Է��ϼ���."); frm.ID.focus(); return false;
	}
	if(frm.PWD.value == ''){
		alert("��ȣ�� �Է��ϼ���."); frm.PWD.focus(); return false;
	}
}
</script>
</body>
</html>








