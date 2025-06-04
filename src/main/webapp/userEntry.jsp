<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h3 align="center">개인 정보 입력</h3>
<form action="register.do" method="post" onsubmit="return check(this)" name="frm">
<table>
	<tr><th>계 정</th><td><input type="text" name="ID"/>
		<input type="button" value="중복검사" onclick="idCheck()"/></td></tr>
	<tr><th>이 름</th><td><input type="text" name="NAME"/></td></tr>
	<tr><th>주 소</th><td><input type="text" name="ADDR"/></td></tr>
	<tr><th>연락처</th><td><input type="text" name="PHONE"/></td></tr>
	<tr><th>암 호</th><td><input type="password" name="PWD"/></td></tr>
	<tr><th>암호확인</th><td><input type="password" name="CONFIRM"/></td></tr>
	<tr><th>성 별</th><td>남<input type="radio" name="GENDER" value="M"/>
					여<input type="radio" name="GENDER" value="F"/></td></tr>
	<tr><th>이메일</th><td><input type="text" name="EMAIL"/></td></tr>
	<tr><th>생 일</th><td><input type="date" name="BIRTH"/></td></tr>
	<tr><th>직 업</th><td><select name="JOB"><option>---선택하세요---</option>
			<option>학 생</option><option>회사원</option><option>주 부</option>
			<option>자영업</option><option>기 타</option></select></td></tr>
	<tr><td colspan="2" align="center"><input type="submit" value="가입하기"/>
		<input type="reset" value="취 소"/></td></tr>
</table><br/>
<script type="text/javascript">
function check(frm){
	if(frm.ID.value == ''){	alert("계정을 입력하세요."); frm.ID.focus(); return false;	}
	else {
		if(frm.ID.value.length < 6 || frm.ID.value.length > 15){
			alert("계정은 6자 이상, 15자 이하로 입력하세요."); frm.ID.focus(); return false;
		}
	}
	if(frm.idChecked.value == ''){	alert("ID 중복검사를 해야합니다."); return false;	}
	if(frm.NAME.value == ''){alert("이름을 입력하세요."); frm.NAME.focus(); return false;}
	else {
		if(frm.NAME.value.length > 10){
			alert("이름은 10자 이내로 입력하세요."); frm.NAME.focus(); return false;
		}
	}
	if(frm.ADDR.value == ''){alert("주소를 입력하세요."); frm.ADDR.focus(); return false;}
	else {
		if(frm.ADDR.value.length > 10){
			alert("주소는 10자 이내로 입력하세요."); frm.ADDR.focus(); return false;
		}
	}
	if(frm.PHONE.value == ''){alert("연락처를 입력하세요."); frm.PHONE.focus(); return false;}
	else {
		if(frm.PHONE.value.length > 15){
			alert("연락처는 15자 이내로 입력하세요."); frm.PHONE.focus(); return false;
		}
	}
	if(frm.PWD.value == ''){alert("암호를 입력하세요."); frm.PWD.focus(); return false;}
	else {
		if(frm.PWD.value.length > 15){
			alert("암호는 15자 이내로 입력하세요."); frm.PWD.focus(); return false;
		}
	}
	if(frm.PWD.value != frm.CONFIRM.value){
		alert("암호가 일치하지 않습니다."); frm.PWD.focus(); return false;
	}
	if( ! frm.GENDER[0].checked && ! frm.GENDER[1].checked){
		alert("성별을 선택하세요."); return false;
	}
	if(frm.JOB.selectedIndex < 1){
		alert("직업을 선택하세요."); frm.JOB.focus(); return false;
	}//selectedIndex 속성에는 콤보박스에서 선택한 데이터의 순서번호(인덱스)가 들어간다.
	
	if( ! confirm("정말로 가입하시겠습니까?")) return false;
}
function idCheck(){
	if(document.frm.ID.value == ''){
		alert("계정을 입력하세요."); document.frm.ID.focus(); return false;
	}else {
		if(document.frm.ID.value.length < 6 || document.frm.ID.value.length > 15){
			alert("계정은 6자 이상, 15자 이하로 입력하세요."); 
			document.frm.ID.focus(); return false;
		}
	}
	var url="idCheck.do?ID="+document.frm.ID.value;
	window.open(url, "_blank_", "width=450,height=200");
}
</script>
</form>
</body>
</html>