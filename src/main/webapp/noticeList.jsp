<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*, notice.*" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%   ArrayList<NoticeDTO> list = (ArrayList<NoticeDTO>)request.getAttribute("NOTICES"); %>
<c:set var="pageCount" value="${PAGES}" />
<h3 align="center">�������� ���</h3>
<div align="center">
<table border="1">
   <tr><th>�۹�ȣ</th><th>�� ��</th><th>�ۼ���</th><th>�ۼ���</th></tr>
   <c:forEach var ="dto" items="${NOTICES}">
   <tr><td>${dto.num }</td>
      <td><a href="noticeDetail.do?NO=${dot.num }">${dto.title}</a></td>
      <td>${dto.notice_date }</td><td>${dto.writer }</td></tr>
   </c:forEach>
</table>
<c:forEach begin="1" end="${pageCount }" var="i"></c:forEach>
<a href="noticeList.do?PAGE_NUM=${i }">${i }</a>
</div>
</body>
</html>













