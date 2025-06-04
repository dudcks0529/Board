<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@   page import="java.util.*, items.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%      ArrayList<MadeIn> nations = (ArrayList<MadeIn>)request.getAttribute("NATIONS"); %>
<div align="center">
<h3>상품 등록</h3>
<script type="text/javascript">
function codeCheck() {
   if(document.fm.CODE.value == '') {   
      alert("상품코드를 입력하세요."); document.fm.focus(); return;
   }
   var url = "codeCheck.do?CODE="+document.fm.CODE.value;
   window.open(url,"_blank_","width=400, height=250");
}
</script>
<form action="putItems.do" method="post" onsubmit="return check(this)" name="fm">
<table>
   <tr><th>상품 코드</th><td><input type="text" name="CODE"/>
      <input type="button" value="중복 검사" onclick="codeCheck()"/></td></tr>
   <tr><th>상품 이름</th><td><input type="text" name="NAME"/></td></tr>
   <tr><th>가 격</th><td><input type="text" name="PRICE"/></td></tr>
   <tr><th>원산지</th><td><select name="NATION">
   <% for(MadeIn nation : nations) { %>
      <option value="<%= nation.getCode() %>"><%= nation.getName() %></option>
   <% } %>
   </select></td></tr>
   <tr><th>상품 설명</th><td><textarea rows="4" cols="50" name="SPEC"></textarea></td></tr>
   <tr><td colspan="2" align="center"><input type="submit" value="상품 등록"/>
      <input type="reset" value="취 소"/></td></tr>
</table>
</form>
</div>
</body>
</html>