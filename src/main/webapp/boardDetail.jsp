<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="bbs.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h3 align="center">�Խñ� �� ����</h3>
<div align="center">
<form action="boardModify.do" method="post">
<input type="hidden" name="SEQ" value="${ requestScope.DTO.seq }">
   <table border="1">
      <tr><th>������</th>
         <td><input type="text" name="TITLE" 
         value="${ requestScope.DTO.title }"/></td></tr>
      <tr><th>�ۼ���</th><td>${ requestScope.DTO.writer }</td></tr>
      <tr><th>�ۼ���</th><td>${ requestScope.DTO.write_date }</td></tr>
      <tr><th>�۳���</th>
         <td><textarea rows="10" cols="20" name="CONTENT">${ requestScope.DTO.content }</textarea>
         </td></tr>
         
<c:if test="${ sessionScope.ID != null && 
			   sessionScope.ID == requestScope.DTO.writer}">
<tr><td colspan="2" align="center">
      <input type="submit" value="����" name="BTN"/>
      <input type="submit" value="����" name="BTN"/></td></tr> 
</c:if>    
   </table>
</form>
</div>
</body>
</html>