const ui1 = new UI();
const publicaciones = new ElementsFotos();
var idFotos = 0;
var image = new Image();

// PODRIA SER UNA FOTO, FOTO Y TEXTO, O TEXTO SOLO
function formHistory(btnHistory) {
	postPhotos(1);
}
function formPhotos(btnPhotos) {
	postPhotos(2);
}

// SUPERVISA LOCAL STORAGE DEL PC
function postPhotos(num) {
	if (storageAvailable('localStorage')) {
		taked(num);
	}
	else {
		ui1.noStorage();
	}
}

function storageAvailable(type) {
	try {
		var storage = window[type],
			x = '__storage_test__';
		storage.setItem(x, x);
		storage.removeItem(x);
		return true;
	}
	catch (e) {
		return e instanceof DOMException && (
			// everything except Firefox
			e.code === 22 ||
			// Firefox
			e.code === 1014 ||
			// test name field too, because code might not be present
			// everything except Firefox
			e.name === 'QuotaExceededError' ||
			// Firefox
			e.name === 'NS_ERROR_DOM_QUOTA_REACHED') &&
			// acknowledge QuotaExceededError only if there's something already stored
			storage.length !== 0;
	}
}

// funcion que realiza de acuerdo al tipo de publicacion que debe hacer
function taked(numero) {	
	publicaciones.dataHistory = document.getElementById("Textarea1").value;
	publicaciones.dataPostPhotos = document.getElementById("Textarea1").value;
	Textarea1.value = "";
	idFotos=1

	if (publicaciones.dataHistory === "" || publicaciones.dataPostPhotos === ""){
		ui1.danger();
	}

	switch (numero) {
		case 1:
			ui1.addHistoryPhotos(publicaciones.dataHistory, idFotos);
			ui1.correct();
			break;
		case 2:
			addPostPhotos(publicaciones.dataPostPhotos, idFotos);
			ui1.correct();
			break;
		default:
			ui1.danger();
			break;
	}
};

function addPostPhotos(dato) {
	const postLists = document.getElementById('publicacionesPost');
	const element = document.createElement('div');

	element.innerHTML = `
			<div class="card my-3">
						<div class="card userName d-flex justify-content-center">
							<p><b>Nombre del Usuario</b></p>
						</div>
						<div class="card-img">							
							<img id="idFotos" src="" alt="Preview">
						</div>
						<div class="card my-3 card-body pt-0 pb-2">
							${dato}
                        </div>
						<div class="card-footer bg-white border-0 p-0">                                
                            <div class="d-flex justify-content-between align-items-center my-1">
                                <div class="col">
                                    <button id="meGusta" type="button" class="btn btn-fbook btn-block btn-sm"> <i class="fa fa-thumbs-up"
										aria-hidden="true"></i> Me gusta
									</button>
                                </div>
                                <div class="col">
                                    <button id="comentar" type="button" class="btn btn-fbook btn-block btn-sm"><i class="fa fa-comment"
												aria-hidden="true"></i> Comentar
									</button>
                                </div>
                                <div class="col">
                                    <button id="compartir" type="button" class="btn btn-fbook btn-block btn-sm"><i class="fa fa-share"
												aria-hidden="true"></i> Compartir
									</button>
								</div>
								<div class="col">
									<a href="#" class="btn btn-danger" id="btnDelete" name="delete">X</a>
								</div>
                            </div>
                        </div>				
			</div>
		`;
	postLists.appendChild(element);
	addFotoDiv();
}

function addFotoDiv(){
								
	const recentImageDataUrl = localStorage.getItem("recent-image");

	if (recentImageDataUrl) {
		document.querySelector("#idFotos").setAttribute("src", recentImageDataUrl);
	}
}

/*
document.addEventListener("DOMContentLoaded", () => {
		const recentImageDataUrl = localStorage.getItem("recent-image");

		if (recentImageDataUrl) {
			document.querySelector("#imgPreview").setAttribute("src", recentImageDataUrl);
		}

		localStorage.removeItem("recent-image");
	});
*/

// DOM EVENTS

// funcion que al presionar eliminar link lo destruye
document.getElementById('publicacionesPost').addEventListener('click', function (e) {
	ui1.deletePost(e.target);
	ui1.correct();
	e.preventDefault();
});

// funcion que capta la img y la guarda en localStorage
document.querySelector('#fileFoto').addEventListener('change', (e)=>{	
	const reader = new FileReader();	

	reader.addEventListener("load", () => {
		if (idFotos==0){
			localStorage.setItem("recent-image", reader.result); /*recent-image*/	
			idFotos++;
			localStorage.setItem("idsFot", idFotos);
		}else{
			idFotos = parseInt(localStorage.getItem("idsFot"));
			localStorage.removeItem("idFotos");
			console.log(idFotos)
			idFotos++;
			localStorage.setItem("recent-image", reader.result); /*recent-image*/
			localStorage.setItem("idsFot", idFotos);
		}
		
	});

	reader.readAsDataURL(e.target.files[0]);
});	

function showImages(num){
	for (let i=0; i<localStorage.length; i++){
		if (i===num){
			let res = localStorage.getItem(localStorage.key(i))
			image.src = res;
		}		
	}
}
