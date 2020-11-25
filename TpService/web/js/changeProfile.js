// funcion que me trae del Login HTML 
function formChangeProfile(btnChangeProfile) {		    
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

function sendingChangeProfile(newProfile) {    
    fetch('file:///home/mariano/Documentos/TP-MIO-LABO4/IndexCentral/html/profile.html', {
        method: 'POST',
        body: newProfile
    })
    .then((response) => {
        if(response.ok){
            response.json().then(data=>{
                if(data){
                    msg.correct()
                    addProfileData(newProfile)        
                }
            })            
        } else {
            throw 'Error en la llamada a Ajax';
        }
    })   
    .catch(function(err) {
        op.saveErrorsList(err);
        msg.danger()
    });
}

function addProfileData(newProfile){
    var prof = JSON.parse(newProfile)
    var newImage = new Image();
    newImage.src = prof.img;

    ui.addProfile(prof)
    ui.addPhotoProfileUser();

    document.querySelector('#idUser').value = prof.id;
    document.querySelector('#firstNameProfile').value = prof.name;
    document.querySelector('#lastNameProfile').value = prof.lastName;
    document.querySelector('#yearProfile').value = prof.birthday.year;
    document.querySelector('#monthProfile').value = prof.birthday.month;
    document.querySelector('#dayProfile').value = prof.birthday.day;

    sessionStorage.removeItem('elementProfile');
    let listChangeProfile = []
    listChangeProfile.push(prof)
    sessionStorage.setItem('elementProfile', JSON.stringify(listChangeProfile));    
}
