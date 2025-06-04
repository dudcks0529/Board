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
<h3 align="center">상품 코드 중복 검사 결과</h3>
<form action="codeCheck.do" name="frm">
	상품 코드 : <input type="text" name="CODE" value="${ CODE }"/>
	<input type="submit" value="중복 검사"/>
</form><br/>
<c:choose>
	<c:when test="${ DUP == null }">
		${ CODE }는 사용 가능합니다. <input type="button" value="사용" onclick="codeOk()">
	</c:when>
	<c:otherwise>
		${ CODE }는 이미 사용 중입니다.
	</c:otherwise>
</c:choose>
<script type="text/javascript">
function codeOk(){
	//자식jsp에서 부모jsp로 이동
	opener.document.fm.CODE.value = document.frm.CODE.value;
	self.close(); //팝업창을 닫는다
}
</script>
</body>
</html>