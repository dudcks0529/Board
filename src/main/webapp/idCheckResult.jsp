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
<h2 align="center">계정 중복 확인</h2>
<form action="idCheck.do">
계 정 : <input type="text" name="ID" value="${ ID }"/>
	<input type="submit" value="중복검사"/>
</form>
<c:choose>
	<c:when test="${ DUP == null }">
		${ ID }는 사용 가능합니다. <input type="button" value="사용" onclick="idOk('${ ID }')"/>
	</c:when>
	<c:otherwise>
		${ ID }는 사용 중입니다.
		<script type="text/javascript">
			opener.document.frm.ID.value = ""; //opener.document -> 부모 jsp 오픈(여기서는 userEntry.jsp)
		</script>
	</c:otherwise>
</c:choose>
<script type="text/javascript">
function idOk(userId){
	opener.document.frm.ID.value = userId;
	opener.document.frm.ID.readOnly = true;//편집이 안되게 속성을 readOnly로 바꾼다.
	opener.document.frm.idChecked.value = "yes";//ID중복검사용 파라미터(idChecked)에 값을 넣는다.
	self.close();
}
</script>
</body>
</html>















