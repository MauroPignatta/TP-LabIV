var profileUser = sessionStorage.getItem('elementProfile');

// Al presionar el boton de publicar Photos
function formPhotos(btnPhotos) {
	var dataPostPhotos = document.getElementById("Textarea1").value;
	Textarea1.value = "";
	if (dataPostPhotos === ""){msg.danger();} else{createPostPhotos(dataPostPhotos);}
}

// Funcion que envia el Texto publicado, con o sin foto
function createPostPhotos(dato){
	const postPhoto = localStorage.getItem("recent-image");
	localStorage.removeItem("recent-image");		

	let postFoto = {
		id: profileUser.id,
		texto: dato,
		img: postPhoto
	}
	fetch('file:///home/mariano/Documentos/TP-MIO-LABO4/IndexCentral/html/index.html', {
		method: 'POST',
		body: postFoto
	})
	.then((response) => {
		if (response.ok) {
			response.json().then(data=>{
				if(data){
					msg.correct();
					takeListPosted();		
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

// Funcion GET pide Lista de Posteos de Texto y Fotos
function listAllBack() {
	return fetch('algun lugar')
	.then(response => response.json())
}

// Funcion que toma Get lista de Posteos, recorre y envia a producir objetos
const takeListPosted = async () => {
	var textList = await listAllBack()
	if (textList === null) {
		msg.linksEmpty();
		op.saveErrorsList('Lista de Links vacía');
	} else {
		textList.forEach(element => {
			ui.addPostPhoto(element)
		});
	}
}

// DOM EVENTS

// funcion que capta la img y la guarda en localStorage
document.querySelector('#fileFoto').addEventListener('change', (e)=>{	
	const reader = new FileReader();	
	reader.addEventListener("load", () => {
		localStorage.setItem("recent-image", reader.result); 
	});
	reader.readAsDataURL(e.target.files[0]);
});


// funcion que al presionar eliminar en algun objeto de Post : lo destruye
document.getElementById('publicacionesPost').addEventListener('click', function (e) {	
	let objPostTextDelete = e.target;
	let numIdPostText;
	if (objPostTextDelete.name === 'delete') {
		numIdPostText = objPostTextDeletenumero.parentElement.parentElement.parentElement.parentElement.id;
	}
	var postTextDelete = {
		userId: profileUser.id,
		postId: numIdPostText
	}
	fetch('file:///home/mariano/Documentos/TP-MIO-LABO4/IndexCentral/html/register.html', {
		method: 'POST',
		body: postTextDelete
	})
		.then((response) => {
			if (response.ok) {
				response.json().then(data=>{
					if(data){
						takeListPosted();
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
	e.preventDefault();
});