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
<h3 align="center">��ǰ �ڵ� �ߺ� �˻� ���</h3>
<form action="codeCheck.do" name="frm">
	��ǰ �ڵ� : <input type="text" name="CODE" value="${ CODE }"/>
	<input type="submit" value="�ߺ� �˻�"/>
</form><br/>
<c:choose>
	<c:when test="${ DUP == null }">
		${ CODE }�� ��� �����մϴ�. <input type="button" value="���" onclick="codeOk()">
	</c:when>
	<c:otherwise>
		${ CODE }�� �̹� ��� ���Դϴ�.
	</c:otherwise>
</c:choose>
<script type="text/javascript">
function codeOk(){
	//�ڽ�jsp���� �θ�jsp�� �̵�
	opener.document.fm.CODE.value = document.frm.CODE.value;
	self.close(); //�˾�â�� �ݴ´�
}
</script>
</body>
</html>