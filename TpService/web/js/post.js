var user = JSON.parse(sessionStorage.getItem('user'));

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
		id: user.id,
		text: dato,
		photo: postPhoto
	}
	var post = JSON.stringify(postFoto)

	fetch('http://localhost:8080/TpService/rest/post/new', {
		method: 'POST',
		body: post
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


// Funcion que toma Get lista de Posteos, recorre y envia a producir objetos
const takeListPosted = async () => {
	var url = 'http://localhost:8080/TpService/rest/post/list/' + user.id
	fetch(url)
		.then(response => response.json())
		.then(data => {
			if (data === null) {
				msg.linksEmpty();
				op.saveErrorsList('Lista de Links vacía');
			} else {
				data.forEach(element => {
					ui.addPostPhotos(element)
				});
			}
		})
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
		userId: id,
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