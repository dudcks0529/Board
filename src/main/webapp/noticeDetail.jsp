<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "notice.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	NoticeDTO dto = (NoticeDTO)request.getAttribute("NOTICE");
	String id = (String)session.getAttribute("ID");
%>
<h3 align="center">공지글 상세보기</h3>
<form action="noticeModify.do" method="post" onsubmit="return check()">
<!-- onsubmit에서 return 값이 true이면 action을 호출, false이면 아무일도 X -->
<input type="hidden" name="NO" value="<%= dto.getNum() %>"/>
<table>
	<tr><th>글번호</th><td><%= dto.getNum() %></td></tr>
   <tr><th>제 목</th><td><input type="text" name="TITLE" value="<%= dto.getTitle() %>"></td></tr>
   <tr><th>작성자</th><td><%= dto.getWriter() %></td></tr>
   <tr><th>작성일</th><td><%= dto.getNotice_date() %></td></tr>
   <tr><th>내 용</th><td><textarea rows="5" name="CONTENT"
            cols="40"><%= dto.getContent() %></textarea></td></tr>
<% if(id != null && id.equals("admin")){ %>
	<tr><td colspan="2" align="center"><input type="submit" value="수정" name="BTN"/>
		<input type="submit" value="삭제" name="BTN"/> </td></tr>
<% } %>
</table>
</form>
<script type="text/javascript">
function check(){
	if( ! confirm("정말로 해당 작업을 진행하시겠습니까?")) return false;
}
</script>
</body>
</html>