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
<c:set var="result" value="${ param.R }"/>
<c:if test="${result == 'OK' }">
	<script type="text/javascript">
		setTimeout(function(){ 
			alert("�Խñ��� ��ϵǾ����ϴ�.");
			location.href="imageList.do";
		},100); 
		//�Խñ� ����� ���
	</script>
</c:if>
<c:if test="${ result=='NOK' }">
	<script type="text/javascript">
		alert("���ε尡 �����߽��ϴ�. ������ Ȯ���ϼ���.");
		location.href="imageList.do";//�Խñ� ����� ���
	</script>
</c:if>
</body>
</html>
