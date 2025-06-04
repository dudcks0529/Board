<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*, bbs.*" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
   <h3 align="center">�Խñ� ���</h3>
<div align="center">
<table border="1">
   <tr><th>�۹�ȣ</th><th>�ۼ���</th><th>�� ��</th><th>�ۼ���</th></tr>
<c:forEach var="dto" items="${ BOARD }">
	<tr><td>${ dto.seq }</td>
      <td>${ dto.writer }</td>
      <td><a href="boardDetail.do?SEQ=${dto.seq }">${ dto.title }</a></td>
      <td>${ dto.write_date }</td></tr>
</c:forEach>
</table>
<c:set var="currentPage" value="${ requestScope.currentPage }"/>
<c:set var="startPage" 
	   value="${ currentPage - (currentPage % 10 == 0 ? 10 : (currentPage % 10)) + 1 }" />
<c:set var="endPage" value="${ startPage + 9 }"/>
<c:set var="pageCount" value="${ PAGES }" />
<c:if test="${ endPage > PageCount }">
	<c:set var="endPage" value="${ pageCount }" />
</c:if>

<c:if test="${ startPage > 10 }">
	<a href="boardList.do?PAGE_NUM=${ startPage - 1 }">[����]</a>
</c:if>

<c:forEach begin="${ startPage }" end="${ endPage }" var="i">
	<c:if test="${ currentPage == i }">
		<font size="6">
	</c:if>
	<a href="boardList.do?PAGE_NUM=${ i }">${ i }</a>
	<c:if test="${ currentPage == i }">
		</font>
	</c:if>
</c:forEach>
<c:if test="${ endPage < pageCount }">
	<a href="boardList.do?PAGE_NUM=${ endPage + 1 }">[����]</a>
</c:if>
</div>   
</body>
</html>









