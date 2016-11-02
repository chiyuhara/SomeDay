// JavaScript Document

function passwdcheck() {
		if (!document.passwdcheckform.password.value) {
			alert("��й�ȣ�� �Է��ϼ���");
			document.passwdcheckform.password.focus();
			return false;
		}
		if (document.passwdcheckform.password.value != document.passwdcheckform.password2.value) {
			alert("��й�ȣ�� �����ϰ� �Է��ϼ���");
			document.passwdcheckform.password2.focus();
			return false;
		}
		if (confirm("Ż���ϰڽ��ϱ�?")) {

		}  {
			alert("��ҵǾ����ϴ�");
			window.location.href = 'memberMF.action';
			return false;
		}
	}
function checkForm() {
	var ok1 = document.getElementById("OK1");
	var ok2 = document.getElementById("OK2");
	if (ok1.checked == true && ok2.checked == true)// üũ�ڽ��� �Ӽ� checked�Ӽ��� true��
	{
		window.location.href = "joinForm.action"; // �������������̵� �̵�
	}  {
		alert("����� �����ϼž��մϴ�.");
		return false;
	}
}
function findidcheckIt(){
	if (!document.findform.name.value) {
		alert("�̸��� �Է��ϼ���");
		document.findform.name.focus();
		return false;
	}
	if(!document.findform.phone.value){
		alert("�޴�����ȣ�� �Է��ϼ���");
		document.findform.phone.focus();
		return false;
	}
}
function findid() {
	var url = "memberfindF.action";
	window
			.open(
					url,
					"post",
					"toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=400,height=250");
}
function findpasswordcheckIt(){
	if (!document.findpasswordform.id.value) {
		alert("���̵� �Է��ϼ���");
		document.findpasswordform.id.focus();
		return false;
	}
	if (!document.findpasswordform.phone.value) {
		alert("�޴�����ȣ�� �Է��ϼ���");
		document.findpasswordform.phone.focus();
		return false;
	}
}
function findpassword() {
	var url = "memberpfindF.action";
	window
			.open(
					url,
					"post",
					"toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=400,height=250");
}
function begin() {
	document.loginform.id.focus();
}
function idCheck() {
	if (!document.idcheckform.id.value) {
		alert("���̵� �Է��ϼ���");
		return false;
	}
}


function logout() {
	if (confirm("�α׾ƿ� �Ͻðڽ��ϱ�?")) {
		alert("�̿����ּż� �����մϴ�");
		window.location.href = 'memberLO2.action';
	}  {
		alert("��ҵǾ����ϴ�");
	}
}
function zipCheck() {
	var url = "zipcodeF.action";
	window
			.open(
					url,
					"post",
					"toolbar=no,width=500,height=300,directoris=no,status=yes,scrollbars=yes,menubar=no");
}
function openConfirmId(joinform) {
	if (joinform.id.value == "") {
		alert("���̵� �Է��ϼ���");
		return;
	}
	url = "checkForm.action?id=" + joinform.id.value;
	open(
			url,
			"confirm",
			"toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=300,height=200");
}
function openmodifyPassword() {
	var url = "memberPMF.action";
	window
			.open(
					url,
					"post",
					"toolbar=no,width=400,height=250,directoris=no,status=yes,scrollbars=no,menubar=no");
}
function joinCheck() {
	if (!document.joinform.id.value) {
		alert("���̵� �Է��ϼ���.");
		return false;
	}
	if (!document.joinform.password.value) {
		alert("��й�ȣ�� �Է��ϼ���");
		return false;
	}
	if (!document.joinform.password.value != !document.joinform.password2.value) {
		alert("��й�ȣ�� �����ϰ� �Է��ϼ���");
		return false;
	}
	if (!document.joinform.name.value) {
		alert("�̸��� �Է��ϼ���");
		return false;
	}
	if (!document.joinform.jumin1.value || !document.joinform.jumin2.value) {
		alert("������ �Է��ϼ���");
		return false;
	}
	if (!document.joinform.phone.value) {
		alert("�ڵ��� ��ȣ�� �Է��ϼ���");
		return false;
	}
	if (!document.joinform.zipcode.value) {
		alert("�����ȣ�� �Է��ϼ���");
		return false;
	}
	if (!document.joinform.address.value) {
		alert("�ּҸ� �Է��ϼ���");
		return false;
	}
}
function back() {
	history.go(-1);
}
function checknum() {
	if (event.keyCode < 48 || event.keyCode > 57) {
		alert("���ڸ� �Է��ϼ���");
		event.returnValue = false;
	}
}
function checkIt() {
	if (!document.loginform.id.value) {
		alert("���̵� �Է��ϼ���");
		document.loginform.id.focus();
		return false;
	}
	if (!document.loginform.password.value) {
		alert("��й�ȣ�� �Է��ϼ���");
		document.loginform.password.focus();
		return false;
	}

}
function dongCheck() {
	if (document.zipform.area3.value == "") {
		alert("�� �̸��� �Է��ϼ���");
		document.zipform.area3.focus();
		return;
	}
	document.zipform.submit();
}

function sendAddress(zipcode, area1, area2, area3, area4) {
	var address = area1 + " " + area2 + " " + area3 + " " + area4;
	opener.document.joinform.zipcode.value = zipcode;
	opener.document.joinform.addr1.value = address;
	self.close();
}


function ordersendAddress(zipcode, area1, area2, area3, area4) {
	var address = area1 + " " + area2 + " " + area3 + " " + area4;
	opener.document.orderForm.order_receive_zipcode.value = zipcode;
	opener.document.orderForm.order_receive_addr1.value = address;
	self.close();
}
function basketsendAddress(zipcode, area1, area2, area3, area4) {
	var address = area1 + " " + area2 + " " + area3 + " " + area4;
	opener.document.orderForm.order_receive_zipcode.value = zipcode;
	opener.document.orderForm.order_receive_address.value = address;
	self.close();
} 
