// Funcion que solicita al Back los datos del perfil cuando el usuario 
// toca el boton de Perfil en la barra de menú del index.html
// Inserta los datos en el archivo profile.html
var requestProfileUser = async() => {
    var user = await ser.getFetch('ACA VA EL URL');    
    var infoProfile = [];
       
    document.getElementById('firstNameProfile').value = user.name;
    document.getElementById('lastNameProfile').value = user.lastName;
    document.getElementById('yearProfile').value = user.birthday.year;
    document.getElementById('monthProfile').value = user.birthday.month;
    document.getElementById('dayProfile').value = user.birthday.day;
    
    ui.addPhotoProfileUser(user);     

    let profile = {
        id: user.id,
        name: user.name,
        lastName: user.lastName
    }
    
    infoProfile.push(profile)
    sessionStorage.setItem('elementProfile', JSON.stringify(infoProfile)); 
}

// Funcion que devuelve los datos del perfil guardados en sessionStorage elementProfile
function getProfileList() {
    var profList = sessionStorage.getItem('elementProfile');
    var dataProfile = [];
    if (profList == null) {
        dataProfile = [];
    } else {
        dataProfile = JSON.parse(profList);
    }
    return dataProfile;
}
requestProfileUser()