// funcion que me trae del Login HTML 
function formProfile(btnProfile) {		    
    var changeProfile = {
            id : document.querySelector('#idUser').value,
            name : document.querySelector('#firstNameProfile').value,
            lastName : document.querySelector('#lastNameProfile').value,
            year : document.querySelector('#yearProfile').value,
            month : document.querySelector('#monthProfile').value,
            day : document.querySelector('#dayProfile').value
        };	
            
    let resProfile = op.isNotNullEmpty(changeProfile.name, "El Nombre");
    resProfile &= op.isNameCorrect(changeProfile.name, "El Nombre");    
    resProfile &= op.isNotNullEmpty(changeProfile.lastName, "El Apellido");
    resProfile &= op.isNameCorrect(changeProfile.lastName, "El Apellido");   
    resProfile &= op.isNotNullEmpty(changeProfile.year);  
    resProfile &= op.isNumber(changeProfile.year);    
    resProfile &= op.isNotNullEmpty(changeProfile.month);
    resProfile &= op.isNumber(changeProfile.month);
    resProfile &= op.isNotNullEmpty(changeProfile.day);  
    resProfile &= op.isNumber(changeProfile.day);    
    resProfile &= op.isFecha(changeProfile);

    if (resProfile){sendingChangeProfile(JSON.stringify(changeProfile));}    
}

function sendingChangeProfile(data) {    
    fetch('file:///home/mariano/Documentos/TP-MIO-LABO4/IndexCentral/html/profile.html', {
        method: 'POST',
        body: data
    })
    .then(function(response) {
        if(response.ok){
            addProfileData(data)
        } else {
            throw 'Error en la llamada a Ajax';
        }
    })   
    .catch(function(err) {
        sessionStorage.setItem('errores', err);
    });
}

function addProfileData(data){
    var prof = JSON.parse(data)
    var newImage = new Image();
    newImage.src = prof.img;
    newImage.with = newImage.height = "120";        

    document.querySelector('#idUser').value = prof.id;
    document.querySelector('#firstNameProfile').value = prof.name;
    document.querySelector('#lastNameProfile').value = prof.lastName;
    document.querySelector('#yearProfile').value = prof.year;
    document.querySelector('#monthProfile').value = prof.month;
    document.querySelector('#dayProfile').value = prof.day;
    document.querySelector("#fotoProfile").setAttribute("src", newImage.src);    
    sessionStorage.removeItem('profile');
    sessionStorage.setItem('profile', prof);
}