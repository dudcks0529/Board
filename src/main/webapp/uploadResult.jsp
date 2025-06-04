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
			alert("게시글이 등록되었습니다.");
			location.href="imageList.do";
		},100); 
		//게시글 목록을 출력
	</script>
</c:if>
<c:if test="${ result=='NOK' }">
	<script type="text/javascript">
		alert("업로드가 실패했습니다. 파일을 확인하세요.");
		location.href="imageList.do";//게시글 목록을 출력
	</script>
</c:if>
</body>
</html>
