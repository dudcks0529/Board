<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="items.*, java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%   ArrayList<Items> list = (ArrayList<Items>)request.getAttribute("ITEMS");%>
<c:set var="userId" value="${sessionScope.ID }"/>
<div align="center">
<h3>상품 목록</h3>
<table>
   <tr><td align="right">${startRow }~${endRow }/${total }</td></tr>
</table>
<table>
   <tr><th>상품코드</th><th>상품이름</th><th>상품가격</th><th>원산지</th><th>등록일</th>
      <th>비 고</th></tr>
<c:forEach var="item" items="${ITEMS }">
   <tr><td>${item.item_code }</td>
      <td><a href="itemDetail.do?CODE=${item.item_code }">${item.item_title }</a></td>
      <td><fmt:formatNumber value="${ item.price }" groupingUsed="true" currencySymbol="￦"/><br/></td><td>${item.nation }</td>
      <td>${item.reg_date }</td>
<c:if test="${userID == null || userId != 'admin' }">
   <td><a href="#"
   onclick="window.open('addCart.do?CODE=${item.item_code}','_blank_','width=450,height=200,top=200,left=200')">장바구니 담기</a></td>
</c:if>
</tr>
</c:forEach>
</table>
<c:set var="pageCount" value="${pageCount }"/>
<c:set var="currentPage" value="${ currentPage}"/>
<c:set var="startPage" 
   value="${currentPage - (currentPage % 10 == 0 ? 10 : (currentPage % 10)) + 1}"/>
<c:set var="endPage" value="${startPage + 9}"/>
<c:if test="${endPage > pageCount}">
   <c:set var="endPage" value="${pageCount}"/>
</c:if>
<c:if test="${startPage > 10}">
   <a href="itemsList.do?PAGE_NUM=${startPage - 1}">[이전]</a>
</c:if>
<c:forEach begin="${startPage}" end="${endPage}" var="i">
   <c:if test="${currentPage == i}"><font size="6"></c:if>
   <a href="itemsList.do?PAGE_NUM=${ i }">${ i }</a>
   <c:if test="${currentPage == i}"></font></c:if>
</c:forEach>
<c:if test="${endPage < pageCount}">
   <a href="itemsList.do?PAGE_NUM=${endPage + 1 }">[다음]</a>
</c:if>
</div>
</body>
</html>










