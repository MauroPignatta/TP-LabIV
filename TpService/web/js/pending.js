document.querySelector('#solicitud').addEventListener('click', (e)=>{pendientsAll();});

// Funcion que trae la lista de Solicitudes Pendientes
function pendientsAll(){
	fetch('URL DONDE SE PIDE LA LISTA DE TODOS LAS SOLICITUDES PENDIENTES')
	.then(response => response.json())
	.then(data => {
		if (data === null) {
			msg.linksEmpty();
			op.saveErrorsList('Lista de Pendientes vacía');
		} else {
			printPendingHtml(data)
		}
	})    
}

// Funcion que trae las solicitudes pendientes de quien lo solicita y lo muestra en toMe.html
function printPendingHtml(listPending){	
	listPending.forEach(element => {
		const postPendingList = document.getElementById('pendingAll');
    	const postPendings = document.createElement('div');
    	postPendings.innerHTML = `
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
	                        <a href="#" class="btn btn-success" id="btnAceptar" name="Aceptar">Aceptar</a>
	                    </div>
	                    <div class="col">
	                        <a href="#" class="btn btn-danger" id="btnCancelar" name="Cancelar">Cancelar</a>
	                    </div>
	                </div>
	            </div>				
	        </div>
	    `;
	    postPendingList.appendChild(postPendings);
    })	
}

document.getElementById('pendingAll').addEventListener('click', function (e) {	
	let objBtnAceptar = e.target;
	let objBtnCancelar = e.target;
	let numUserIdAdd;
	let numUserIdNo;
	if (objBtnAceptar.name === 'Aceptar') {
		numUserIdAdd = objBtnAceptar.parentElement.parentElement.parentElement.parentElement.id;
		
		let addPendient = {
			userId: user.id,
			friendId: numUserIdAdd
		}
		fetch('RUTA ADD PERSONAS PENDIENTES', {
		method: 'POST',
		body: addPendient
		})
		.then((response) => {
			if (response.ok) {
				response.json().then(data=>{
					if(data){
						msg.correct();
						pendientsAll()
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

	} else {
		numUserIdNo = objBtnCancelar.parentElement.parentElement.parentElement.parentElement.id;
		
		let notAddPendient = {
			userId: user.id,
			friendId: numUserIdNo
		}
		fetch('RUTA NO QUIERO PERSONAS PENDIENTES', {
		method: 'POST',
		body: notAddPendient
		})
		.then((response) => {
			if (response.ok) {
				response.json().then(data=>{
					if(data){
						msg.correct();
						pendientsAll()
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
	}	
})
