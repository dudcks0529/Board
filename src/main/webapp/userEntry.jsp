<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h3 align="center">���� ���� �Է�</h3>
<form action="register.do" method="post" onsubmit="return check(this)" name="frm">
<table>
	<tr><th>�� ��</th><td><input type="text" name="ID"/>
		<input type="button" value="�ߺ��˻�" onclick="idCheck()"/></td></tr>
	<tr><th>�� ��</th><td><input type="text" name="NAME"/></td></tr>
	<tr><th>�� ��</th><td><input type="text" name="ADDR"/></td></tr>
	<tr><th>����ó</th><td><input type="text" name="PHONE"/></td></tr>
	<tr><th>�� ȣ</th><td><input type="password" name="PWD"/></td></tr>
	<tr><th>��ȣȮ��</th><td><input type="password" name="CONFIRM"/></td></tr>
	<tr><th>�� ��</th><td>��<input type="radio" name="GENDER" value="M"/>
					��<input type="radio" name="GENDER" value="F"/></td></tr>
	<tr><th>�̸���</th><td><input type="text" name="EMAIL"/></td></tr>
	<tr><th>�� ��</th><td><input type="date" name="BIRTH"/></td></tr>
	<tr><th>�� ��</th><td><select name="JOB"><option>---�����ϼ���---</option>
			<option>�� ��</option><option>ȸ���</option><option>�� ��</option>
			<option>�ڿ���</option><option>�� Ÿ</option></select></td></tr>
	<tr><td colspan="2" align="center"><input type="submit" value="�����ϱ�"/>
		<input type="reset" value="�� ��"/></td></tr>
</table><br/>
<script type="text/javascript">
function check(frm){
	if(frm.ID.value == ''){	alert("������ �Է��ϼ���."); frm.ID.focus(); return false;	}
	else {
		if(frm.ID.value.length < 6 || frm.ID.value.length > 15){
			alert("������ 6�� �̻�, 15�� ���Ϸ� �Է��ϼ���."); frm.ID.focus(); return false;
		}
	}
	if(frm.idChecked.value == ''){	alert("ID �ߺ��˻縦 �ؾ��մϴ�."); return false;	}
	if(frm.NAME.value == ''){alert("�̸��� �Է��ϼ���."); frm.NAME.focus(); return false;}
	else {
		if(frm.NAME.value.length > 10){
			alert("�̸��� 10�� �̳��� �Է��ϼ���."); frm.NAME.focus(); return false;
		}
	}
	if(frm.ADDR.value == ''){alert("�ּҸ� �Է��ϼ���."); frm.ADDR.focus(); return false;}
	else {
		if(frm.ADDR.value.length > 10){
			alert("�ּҴ� 10�� �̳��� �Է��ϼ���."); frm.ADDR.focus(); return false;
		}
	}
	if(frm.PHONE.value == ''){alert("����ó�� �Է��ϼ���."); frm.PHONE.focus(); return false;}
	else {
		if(frm.PHONE.value.length > 15){
			alert("����ó�� 15�� �̳��� �Է��ϼ���."); frm.PHONE.focus(); return false;
		}
	}
	if(frm.PWD.value == ''){alert("��ȣ�� �Է��ϼ���."); frm.PWD.focus(); return false;}
	else {
		if(frm.PWD.value.length > 15){
			alert("��ȣ�� 15�� �̳��� �Է��ϼ���."); frm.PWD.focus(); return false;
		}
	}
	if(frm.PWD.value != frm.CONFIRM.value){
		alert("��ȣ�� ��ġ���� �ʽ��ϴ�."); frm.PWD.focus(); return false;
	}
	if( ! frm.GENDER[0].checked && ! frm.GENDER[1].checked){
		alert("������ �����ϼ���."); return false;
	}
	if(frm.JOB.selectedIndex < 1){
		alert("������ �����ϼ���."); frm.JOB.focus(); return false;
	}//selectedIndex �Ӽ����� �޺��ڽ����� ������ �������� ������ȣ(�ε���)�� ����.
	
	if( ! confirm("������ �����Ͻðڽ��ϱ�?")) return false;
}
function idCheck(){
	if(document.frm.ID.value == ''){
		alert("������ �Է��ϼ���."); document.frm.ID.focus(); return false;
	}else {
		if(document.frm.ID.value.length < 6 || document.frm.ID.value.length > 15){
			alert("������ 6�� �̻�, 15�� ���Ϸ� �Է��ϼ���."); 
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