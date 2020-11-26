function searchUser(buscar){

	fetch('http://localhost:8080/TpService/rest/user/list')
		.then(response => response.json())
		.then(data => {
			console.log("holaaaaaa")
			if (data === null) {

				msg.linksEmpty();
				op.saveErrorsList('Lista de Usuarios vacía');
			} else {
				printUsersHtml(data)
			}
		})
}

// Funcion que trae la lista de Todos los Usuarios y la muestra en users.html


// Funcion que toma la lista de Usuarios y la muestra en users.html
function printUsersHtml(listUser) {
	listUser.forEach(element => {

		const postUsersList = document.getElementById('allUsers');
    	const postUsers = document.createElement('div');

    	postUsers.innerHTML = `
	        <div id="${element.id}" name="userList" class="card my-3">                
	            <div class="card-footer bg-white border-0 p-0">                                
	                <div class="d-flex justify-content-between align-items-center my-1">
		                <div>
	                    	<img class="rounded-circle" src="${element.profilePicturePath}" alt="">
	                	</div>
	                    <div class="col">
	                        <p>${element.name}</p>    
	                    </div>
	                    <div class="col">
	                        <p>${element.lastname}</p>    
	                    </div>
	                    <div class="col">
	                        <p>${element.birthdate.year}:${element.birthdate.month}:${element.birthdate.day}</p>    
	                    </div>
	                    <div class="col">
	                        <a href="#" class="btn btn-info" id="btnEnviar" name="Enviar">Enviar Solicitud</a>
	                    </div>
	                </div>
	            </div>				
	        </div>
	    `;
	    postUsersList.appendChild(postUsers);
    })						
}

// Funcion que se acciona al ser presionado el boton de enviar solicitud de amistad
document.getElementById('allUsers').addEventListener('click', function (e) {	
	let objBtnSend = e.target;
	let numUserIdSend;
	if (objBtnSend.name === 'Enviar') {
		numUserIdSend = objBtnSend.parentElement.parentElement.parentElement.parentElement.id;		
	} 
	var sendSolFriend = {
		userId: user.id,
		friendId: numUserIdSend
	}
	let url = 'URL A ENVIAR SOLICITUD ' + user.id
	fetch(url, {
		method: 'POST',
		body: sendSolFriend
	})
	.then((response) => {
		if (response.ok) {
			response.json().then(data=>{
				if(data){
					msg.correct();
					loadUser();
				}
			})			
		} else {
			throw 'Error en la llamada a Ajax';
		}
	})														
	.catch(function (err) {
		op.saveErrorsList(err);
		msg.danger()
	});		
})
