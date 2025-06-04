<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
table { width: 90%; height: 90%; border: 1px solid blue; background-color: skyblue;
		border-collapse: collapse; margin-left: 20px; margin-right: 20px;}
td.main { width: 30%; border: 1px solid green; }
#menu { margin-left: 10px; margin-top: 10px; margin-right: 10px; 
		background-color: lightgreen; width: 90%; border: 1px dashed red; }
#login { margin-left: 10px; margin-top: 10px; width: 90%; background-color: yellow;
		border: 1px dashed orange; }	
#content { background-color: wheat; }			
</style>
</head>
<body onload="startClock()">
<header>
	<div align="center"><img alt="" src="logo.gif"></div>
</header>
<section>
<c:set var="body" value="${param.BODY }"/>
<c:set var="id" value="${sessionScope.ID }"/>
	<table id="body">
		<tr>
			<td width="40%" height="300px" class="main">
				<div id="login">
				<c:choose>
					<c:when test="${id ==null }">
						<jsp:include page="login.jsp"/>
					</c:when>
					<c:otherwise>
						<jsp:include page="logout.jsp"/>
					</c:otherwise>
				</c:choose>
				</div>
				<div id="menu">
					<a href="index.jsp">�� Ȩ����</a><br/>
					<a href="boardList.do">�� �Խñ� ����</a><br/>
					<a href="bbsLoginCheck.do">�� �Խñ� ����</a><br/>
					<a href="itemsList.do">�� ��ǰ ����</a><br/>
					<c:if test="${id != null && id !='admin' }">
						<a href="cartList.do">�� ��ٱ��� ����</a><br/>
					</c:if>
					<c:if test="${id != null && id == 'admin' }">
						<a href="index.jsp?BODY=inputNation.jsp">�� ��ǰ ������ ���</a><br/>
						<a href="findNations.do">�� ��ǰ ���</a><br/>
					</c:if>
					<a href="noticeList.do">�� �������� ����</a><br/>
					<a href="index.jsp?BODY=imageWriteForm.jsp">�� �̹��� �� ��� �Խñ� ����</a><br/>
					<a href="imageList.do">�� �̹��� �� ��� �Խñ� ����</a><br/>
					<c:if test="${id != null && id=='admin' }">
						<a href="index.jsp?BODY=notice_input.jsp">�� �������� ����</a><br/>
					</c:if>
				</div>
			</td>
			<td id="content">
			<c:choose>
				<c:when test="${body != null }">
					<jsp:include page="${ param.BODY }"/>
				</c:when>
				<c:otherwise>
					<jsp:include page="intro.jsp"/>
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
	</table>
</section>
<footer>
	<h3 align="center">�� ���ø����̼� �ۼ� ������Ʈ. Copyright 2024 
	<font color="red"><span id="clock"></span></font></h3>
</footer>
<script type="text/javascript">
function workingClock(){
	var today = new Date();
	var year = today.getFullYear(); 	var month = today.getMonth() + 1;
	if(month < 10) month = "0" + month; var date = today.getDate();
	if(date < 10) date = "0" + date;	var hour = today.getHours();
	if(hour < 10) hour = "0" + hour;	var min = today.getMinutes();
	if(min < 10) min = "0" + min;		var sec = today.getSeconds();
	if(sec < 10) sec = "0" + sec;		
	var str = year+"/"+month+"/"+date+" "+hour+":"+min+":"+sec;
	document.getElementById("clock").innerHTML = str;
}
function startClock(){
	workingClock();//���� �ð��� �����ִ� �ð踦 ����Ѵ�.
	setInterval(workingClock, 1000);//1�� �������� workingClock�Լ��� ��� ȣ���Ѵ�.
}</script>
</body>
</html>









