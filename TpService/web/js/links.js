const urlPostText = "http://localhost:8081/Devs/rest/service/login";
const urlTextList = "http://localhost:8081/Devs/rest/service/login";
const urlLinks = "http://localhost:8081/Devs/rest/service/login";
const urlLinksList = "http://localhost:8081/Devs/rest/service/login";

// funcion que me trae del HTML central a formLinks	
function formLinks(btnLinks) {		
	take(1);
};

// funcion que me trae del HTML central a formPost	
function formPosted(btnPosted) {
	take(2);
};

// funcion que realiza de acuerdo al tipo de publicacion que debe hacer
function take(numero){
	var dataLinkName = document.getElementById("Textarea1").value;		
		dataPostText = document.getElementById("Textarea1").value;

	Textarea1.value = "";	
	if (dataLinkName === "" || dataPostText === ""){
		ui.showMessage("Please insert data in this field", "danger");
		ui.danger();
	}
	switch(numero){
		case 1:
			requestLinks(dataLinkName);			
			break;
		case 2:
			requestText(dataPostText);
			break;
		default:
			ui.danger();
			break;
	}
};

// Funcion que envia el Link publicado, y pide la lista de links para mostrarlo
function requestLinks(dataLink){
	var resulAjaxPostLinks = JSON.parse(op.sendPostJson(dataLink, urlLinks));
    if (resulAjaxPostLinks.result){
    	var resulAjaxGetLinksPostList = JSON.parse(op.sendGetJson(urlLinksList)); 
    	if (resulAjaxGetLinksPostList.result){
    		ui.addLinks(resulAjaxGetLinksPostList);		
    	}
    }
    // Si anda sacarlo
    if (!resulAjaxGetLinksPostList.result){
    	ui.invalidAdd('La Lista de Photos llego vacía.');
    }
}

// Funcion que envia el Texto publicado, y pide la lista asi luego la muestra
function requestText(dataText) {
	var resulAjaxPostPostText = JSON.parse(op.sendPostJson(dataText, urlPostText));
    if (resulAjaxPostPostText.result){
    	var resulAjaxGetTextList = JSON.parse(op.sendGetJson(urlTextList)); 
		if (resulAjaxGetTextList.resul){
			ui.addPost(resulAjaxGetTextList);
		}
    }
    // Si anda sacarlo
    if (!resulAjaxGetTextList.result){
    	ui.invalidAdd('La Lista de Photos llego vacía.');
    }
}

// DOM EVENTS

// funcion que al presionar eliminar link lo destruye
document.getElementById('links-list').addEventListener('click', function(e) {	
	ui.deleteLinks(e.target);
	ui.correct();
	e.preventDefault();
});

// funcion que al presionar eliminar post lo destruye
document.getElementById('publicacionesPost').addEventListener('click', function(e) {
	ui.deletePost(e.target);
	ui.correct();
	e.preventDefault();
});