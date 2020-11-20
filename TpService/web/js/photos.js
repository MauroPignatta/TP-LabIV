// AL PRESIONAR EL BOTON
function formPhotos(btnPhotos) {
	taked();
}

// funcion que toma el texto publicado, y si esta correcto continúa
function taked() {
	var dataPostPhotos = document.getElementById("Textarea1").value;
	Textarea1.value = "";	

	if (dataPostPhotos === ""){
		ui.danger();
	}else{
		createPostPhotos(dataPostPhotos);
		ui.correct();
	}
};

// funcion que recibe el texto, y agrega la Foto publicada.
function createPostPhotos(dato){
	const urlPhotos = "http://localhost:8081/Devs/rest/service/login";
	const recentImageDataUrl = localStorage.getItem("recent-image");
	localStorage.removeItem("recent-image");

	var phName = document.getElementById("firstNameProfile").value + " " + document.getElementById("lastNameProfile").value;
	
	let postFoto = {
		name: phName,
		texto: dato,
		img: recentImageDataUrl
	}

	var resulAjaxPostPhotos = JSON.parse(op.sendPostJson(postFoto, urlPhotos));
	if (resulAjaxPostPhotos.result){
        requestPhotos();
    }else{
        ui.invalidAdd('Hubo un error al generar el post, intenta nuevamente por favor. Gracias.');
    }
}

// Funcion que pide al back la lista de posteos del usuario para mostrar en el sitio
function requestPhotos(){
	const urlPostList = "http://localhost:8081/Devs/rest/service/login";   
    	  resulAjaxGetPostList = JSON.parse(op.sendGetJson(urlPostList));
   	
 	if (resulAjaxGetPostList == null) {
 		ui.invalidAdd('La Lista de Photos llego vacía.');
 	} else {
 		ui.addPostPhotos(resulAjaxGetPostList);	
 	}    
}

// DOM EVENTS

// funcion que al presionar eliminar link lo destruye
document.getElementById('publicacionesPost').addEventListener('click', function (e) {
	ui.deletePost(e.target);
	ui.correct();	
	e.preventDefault();
});

// funcion que capta la img y la guarda en localStorage
document.querySelector('#fileFoto').addEventListener('change', (e)=>{	
	const reader = new FileReader();	
	reader.addEventListener("load", () => {
		localStorage.setItem("recent-image", reader.result); 
	});
	reader.readAsDataURL(e.target.files[0]);
});
