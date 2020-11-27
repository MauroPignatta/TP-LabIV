// Funcion que trae la lista de Todos los Usuarios y la muestra en users.html
function loadFriend(){
	var userid = JSON.stringify(user.id)
	fetch('http://localhost:8080/TpService/rest/friend/friendlist/' + userid)
	.then(function (response){
		if(response.ok){
			response.json().then(data =>{
				printFriendsHtml(data)
			})
		}
	})
}
loadFriend()

// Funcion que toma la lista de Usuarios y la muestra en users.html
function printFriendsHtml(listFriend) {
	listFriend.forEach(element => {
		const postFriendsList = document.getElementById('allFriends');
		const postFriends = document.createElement('div');
		postFriends.innerHTML = `
	        <div id="${element.id}" name="userList" class="card my-3">                
	            <div class="card-footer bg-white border-0 p-0">                                
	                <div class="d-flex justify-content-between align-items-center my-1">
		                <div>
	                    	<img class="rounded-circle" src=${element.profilePicturePath} width="60" height="60" alt="">
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
	                        <a href="#" class="btn btn-info" id="btnVer" name="Ver">Ver Publicaciones</a>
	                    </div>	                    
	                </div>
	            </div>				
	        </div>
	    `;
	    postFriendsList.appendChild(postFriends);
    })						
}

// Funcion que se acciona al ser presionado el boton de enviar solicitud de amistad
document.getElementById('allFriends').addEventListener('click', function (e) {
	let objBtnSee = e.target;
	let numFriendIdSee;
	if (objBtnSee.name === 'Enviar') {
		numFriendIdSee = objBtnSee.parentElement.parentElement.parentElement.parentElement.id;
	}
	var seeFriendRequest = {
		userId: user.id,
		friendId: numFriendIdSee
	}
	let url = //'http://localhost:8080/TpService/rest/post/friendpost'
	fetch(url, {
		method: 'POST',
		body: seeFriendRequest
	})
	.then((response) => {
		if (response.ok) {
			response.json().then(data=>{
				if(data){
					msg.correct();
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
