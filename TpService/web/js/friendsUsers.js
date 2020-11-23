var userPendients = getProfileList();
// Funcion que trae la lista de Amigos y la muestra en friends.html
function loadFriend(){
	var resulAjaxGetFriends = ser.getFetch('GET URL A LISTA AMIGOS');
    var	tbody = document.querySelector('#friendsTable tbody');

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

// Funcion que trae la lista de Todos los Usuarios y la muestra en users.html
function loadUser(){	
	var resulAjaxGetUsers = ser.getFetch('URL A LISTA DE USUARIOS');
    var	tbody = document.querySelector('#usersTable tbody');

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

// Funcion que trae las solicitudes pendientes de quien lo solicita y lo muestra en toMe.html
function pendients(){	
	var resulAjaxGetToMe = ser.postFetch('URL A LISTADO DE SOLICITUDES PENDIENTES', userPendients.id);
	var	tbody = document.querySelector('#pendientsToMe tbody');

	tbody.innerHTML = '';
    resulAjaxGetToMe.forEach(element => {
    	var row = tbody.insertRow(i),
    		idCell = row.insertCell(0),
			nameCell = row.insertCell(1),
			lastNameCell = row.insertCell(2),
			selectAddCell = row.insertCell(3),
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

// Funcion que selecciona el usuario para enviar una solicitud de amistad
function formUsers(btnSendUsers) {
	var sendSolFriend = {
		userId: userPendients.id,
		friendId: btnSendUsers.value
	}
	var	resUser = ser.postFetch('URL A ENVIAR SOLICITUD DE AMISTAD', sendSolFriend);

	if (resUser){ ui.correct();}else{ui.danger();}
}

// Funcion que Acepta la solicitud de amistad de la lista de Pendientes
function formAdd(btnAddFriends) {
	var acceptSolFriend = {
		userId: userPendients.id,
		friendId: btnAddFriends.value
	}
	var	resAdd = ser.postFetch('URL QUE ACEPTA SOLICITUD AMISTAD', acceptSolFriend);
		
	if (resAdd){ui.correct();}else{ui.danger();}
}

// Funcion que Rechaza la solicitud de amistad de la lista de Pendientes
function formNot(btnNotFriends) {
	var notAcceptSolFriend = {
		userId: userPendients.id,
		friendId: btnNotFriends.value
	}
	var	resNotAdd = ser.postFetch('URL QUE RECHAZA SOLICITUD DE AMISTAD', notAcceptSolFriend);
		
	if (resNotAdd){ui.correct();}else{ui.danger();}	
}

// DOM EVENTS

document.querySelector('#contactos').addEventListener('click', (e)=>{loadFriend();});
document.querySelector('#buscar').addEventListener('click', (e)=>{loadUser();});
document.querySelector('#solicitud').addEventListener('click', (e)=>{pendients();});