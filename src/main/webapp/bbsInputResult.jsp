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
${param.R }
<c:choose>
<c:when test="${param.R == 'OK' }">
<script type="text/javascript">
   setTimeout(function(){ 
      alert("�Խñ��� ��ϵǾ����ϴ�.");
   }, 100);
</script>
</c:when>
<c:otherwise>
<script type="text/javascript">
   setTimeout(function(){
      alert("�Խñ��� ��ϵ��� �ʾҽ��ϴ�. �����ڿ��� �������ּ���.");
   }, 100);
</script>
</c:otherwise>
</c:choose>
</body>
</html>







