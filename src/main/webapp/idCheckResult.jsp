<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h2 align="center">���� �ߺ� Ȯ��</h2>
<form action="idCheck.do">
�� �� : <input type="text" name="ID" value="${ ID }"/>
	<input type="submit" value="�ߺ��˻�"/>
</form>
<c:choose>
	<c:when test="${ DUP == null }">
		${ ID }�� ��� �����մϴ�. <input type="button" value="���" onclick="idOk('${ ID }')"/>
	</c:when>
	<c:otherwise>
		${ ID }�� ��� ���Դϴ�.
		<script type="text/javascript">
			opener.document.frm.ID.value = ""; //opener.document -> �θ� jsp ����(���⼭�� userEntry.jsp)
		</script>
	</c:otherwise>
</c:choose>
<script type="text/javascript">
function idOk(userId){
	opener.document.frm.ID.value = userId;
	opener.document.frm.ID.readOnly = true;//������ �ȵǰ� �Ӽ��� readOnly�� �ٲ۴�.
	opener.document.frm.idChecked.value = "yes";//ID�ߺ��˻�� �Ķ����(idChecked)�� ���� �ִ´�.
	self.close();
}
</script>
</body>
</html>















