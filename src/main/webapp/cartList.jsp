<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*, items.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<h3>장바구니 목록</h3>
<c:choose>
	<c:when test="${ CARTLIST == null }">
		<h3>장바구니가 비어있습니다.</h3>
	</c:when>
	<c:otherwise>
		<table>
			<tr><th>상품코드</th><th>상품이름</th><th>가 격</th><th>상품갯수</th><th>소 계</th>
			<th>수정/삭제</th></tr>
				<c:forEach var="item" items="${ CARTLIST }">
						<form action="cartModify.do" method="post" onsubmit="return check()">
						<input type="hidden" name="CODE" value="${ item.item_code }"/>
						<tr><td>${ item.item_code }</td><td>${ item.item_title }</td>
						<td>${ item.price }원</td>
						<td><input type = "number" name = "NUM" min="0" value="${ item.num }"> ${ item.num }</td>
						<td>${ item.sum }</td><td><input type="submit" value="수정" name="BTN"/>
			<input type="submit" value="삭제" name="BTN"/></td></tr>
	</form>
				</c:forEach>
		</table>
	</c:otherwise>
</c:choose>
<script type="text/javascript">
function check(){
	if( ! confirm("정말로 진행하시겠습니까?")) return false;
}
</script>
<form action="">
	총 계 : ${ requestScope.TOTAL }원 <input type="submit" value="결제하기"/>
</form>
</div>
</body>
</html>