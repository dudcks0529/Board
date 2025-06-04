<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<% 	String result = request.getParameter("R");
	if(result.equals("OK")){ %>
<script type="text/javascript">
	setTimeout(function(){
		alert("게시글이 삭제되었습니다.");
		locaion.href="imageList.do"
	},100);
</script>	
<%	} else { %>
<script type="text/javascript">
setTimeout(function(){
	alert("게시글이 삭제되지 않았습니다. 관리자에게 문의해주세요.");
	locaion.href="imageList.do"
},100);
</script>		
<%	} %>
</body>
</html>





















