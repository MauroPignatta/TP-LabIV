const urlLoadFriends = "http://localhost:8081/Devs/rest/service/login"; 
const urlLoadUsers = "http://localhost:8081/Devs/rest/service/login";  
const urlPendientsToMe = "http://localhost:8081/Devs/rest/service/login"; 
const urlSendUser = "http://localhost:8081/Devs/rest/service/login";
const urlAddFriends = "http://localhost:8081/Devs/rest/service/login";
const urlNotAcept = "http://localhost:8081/Devs/rest/service/login";

function loadFriend(){
	var resulAjaxGetFriends = JSON.parse(op.sendGetJson(urlLoadFriends));
    	tbody = document.querySelector('#friendsTable tbody');

	tbody.innerHTML = '';
    resulAjaxGetFriends.forEach(element => {
    	var row = tbody.insertRow(i),			
			nameCell = row.insertCell(0),
			lastNameCell = row.insertCell(1),
			yearCell = row.insertCell(2),
			monthCell = row.insertCell(3),
			dayCell = row.insertCell(4),
			selectCell = row.insertCell(5);
		
		nameCell.innerHTML = element.name;
		lastNameCell.innerHTML = element.lastName;
		yearCell.innerHTML = element.year;
		monthCell.innerHTML = element.month;
		dayCell.innerHTML = element.day;

		var inputSelect = document.createElement('input');
		inputSelect.type= 'button';
		inputSelect.value = element.id;
		onclick="formFriends(btnSendFriends)";
		selectCell.appendChild(inputSelect);

		tbody.appendChild(row);
	});	
}

function loadUser(){
	
	var resulAjaxGetUsers = JSON.parse(op.sendGetJson(urlLoadUsers));
    	tbody = document.querySelector('#usersTable tbody');

	tbody.innerHTML = '';
    resulAjaxGetUsers.forEach(element => {
    	var row = tbody.insertRow(i),			
			nameCell = row.insertCell(0),
			lastNameCell = row.insertCell(1),
			yearCell = row.insertCell(2),
			monthCell = row.insertCell(3),
			dayCell = row.insertCell(4),
			selectUserCell = row.insertCell(5);
		
		nameCell.innerHTML = element.name;
		lastNameCell.innerHTML = element.lastName;
		yearCell.innerHTML = element.year;
		monthCell.innerHTML = element.month;
		dayCell.innerHTML = element.day;

		var inputSelectUser = document.createElement('input');
		inputSelectUser.type= 'button';
		inputSelectUser.value = element.id;
		onclick="formUsers(btnSendUsers)";
		selectUserCell.appendChild(inputSelectUser);

		tbody.appendChild(row);
	});	
}

function pendients(){
	var resulAjaxGetToMe = JSON.parse(op.sendGetJson(urlPendientsToMe));
    	tbody = document.querySelector('#pendientsToMe tbody');

	tbody.innerHTML = '';
    resulAjaxGetToMe.forEach(element => {
    	var row = tbody.insertRow(i),
    		idCell = row.insertCell(0),
			nameCell = row.insertCell(1),
			lastNameCell = row.insertCell(2),
			selectAddCell = row.insertCell(3);
			selectNotCell = row.insertCell(4);

		idCell.innerHTML = element.id;
		nameCell.innerHTML = element.name;
		lastNameCell.innerHTML = element.lastName;

		var inputSelectAdd = document.createElement('input');
		inputSelectAdd.type = 'button';		
		inputSelectAdd.value = element.id;
		inputSelectAdd.id = 'addFriends';
		onclick="formAdd(btnAddFriends)";
		selectAddCell.appendChild(inputSelectAdd);

		var inputSelectNot = document.createElement('input');
		inputSelectNot.type = 'button';		
		inputSelectNot.value = element.id;
		inputSelectNot.id = 'notFriends';
		onclick="formNot(btnNotFriends)";
		selectNotCell.appendChild(inputSelectNot);

		tbody.appendChild(row);
	});	
}

function formUsers(btnSendUsers) {
	var id = btnSendUsers.value;
		resUser = JSON.parse(op.sendPostJson(urlSendUser));

	if (resUser.result){
        ui.correct();
    }else{
        ui.danger();
    }
}

function formAdd(btnAddFriends) {
	var id = btnAddFriends.value;
		resAdd = JSON.parse(op.sendPostJson(urlAddFriends));
		
	if (resAdd.result){
        ui.correct();
    }else{
        ui.danger();
    }
}

function formNot(btnNotFriends) {
	var id = btnNotFriends.value;
		resNotAdd = JSON.parse(op.sendPostJson(urlNotAcept));
		
	if (resNotAdd.result){
        ui.correct();
    }else{
        ui.danger();
    }	
}

// DOM EVENTS

document.querySelector('#contactos').addEventListener('click', (e)=>{loadFriend();});
document.querySelector('#buscar').addEventListener('click', (e)=>{loadUser();});
document.querySelector('#solicitud').addEventListener('click', (e)=>{pendients();});