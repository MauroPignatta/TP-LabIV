document.querySelector('#profiles').addEventListener("click", function() {
	var urlProfile = "http://localhost:8081/Devs/rest/service/login";   
        resulAjaxGetProfile = JSON.parse(op.sendGetJson(urlProfile));
        newImage = new Image();

    newImage.src = resulAjaxGetProfile.img;
    newImage.with = newImage.height = "120";

    idUser.innerText(resulAjaxGetProfile.id);
    pname.innerText(resulAjaxGetProfile.name);    
    plastName.innerText(resulAjaxGetProfile.lastName);    
    pAnio.innerText(resulAjaxGetProfile.year);    
    pMes.innerText(resulAjaxGetProfile.month);    
    pDia.innerText(resulAjaxGetProfile.day);
    document.querySelector("#fotoProfile").setAttribute("src", newImage.src);
})

// funcion que me trae del Login HTML 
function formProfile(btnProfile) {		
	modifProfile();
};

// Funcion que toma las modificaciones
function modifProfile(){
	var urlProfile = "http://localhost:8081/Devs/rest/service/login";
        
    var changeProfile = {
            id : document.getElementByID('idUser').value,
            name : document.getElementByID('firstNameProfile').value,
            lastName : document.getElementByID('lastNameProfile').value,
            year : document.getElementByID('yearProfile').value,
            month : document.getElementByID('monthProfile').value,
            day : document.getElementByID('dayProfile').value
        };	

    let resProfile = op.isNotNullEmpty(changeProfile.name);
    resProfile &= op.isNotNullEmpty(changeProfile.lastName);
    resProfile &= op.isNotNullEmpty(changeProfile.year);
    resProfile &= op.isNotNullEmpty(changeProfile.month);
    resProfile &= op.isNotNullEmpty(changeProfile.day);
    resProfile &= op.isNumber(changeProfile.year);    
    resProfile &= op.isNumber(changeProfile.month);    
    resProfile &= op.isNumber(changeProfile.day);
    resProfile &= op.isNameCorrect(changeProfile.name);
    resProfile &= op.isNameCorrect(changeProfile.lastName);
    resProfile &= op.isFecha(changeProfile);

    if (resProfile){var resulAjaxPostChangeProfile = JSON.parse(op.sendPostJson(changeProfile, urlProfile));}
    if (resulAjaxPostChangeProfile.result){
        ui.correct();
        window.location.href = "index.html";
    }else{
        ui.invalidAdd('Ha ingresado algún dato erroneamente. Intentelo nuevamente. Gracias!');
        window.location.href = "profile.html";
	}
}