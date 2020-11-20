var infoProfile = [];

function formProfileUser() {
    /*var user = await takeProfile();*/
    let uno = '1';
        dos = 'Pepe';
        tres = 'Argento';
        cuatro = 2020;
        cinco = 11;
        seis = 20;
    
    document.getElementById('firstNameProfile').value = dos;
    document.getElementById('lastNameProfile').value = tres;
    document.getElementById('yearProfile').value = cuatro;
    document.getElementById('monthProfile').value = cinco;
    document.getElementById('dayProfile').value = seis;

    /*ui.addPhotoProfileUser(); //pasarle el objeto */
    
    let profile = {
        id: uno,
        name: dos,
        lastName: tres
    }
    infoProfile.push(profile)
    sessionStorage.setItem('elementProfile', JSON.stringify(infoProfile)); 
}

function getProfileList() {
    var profList = sessionStorage.getItem('elementProfile');
    var listPhotos = [];
    if (profList == null) {
        listPhotos = [];
    } else {
        listPhotos = JSON.parse(profList);
    }
    return listPhotos;
}

formProfileUser()
/*

function takeProfile(){
    return fetch('https://jsonplaceholder.typicode.com/users')
    .then(response => response.json())
}



document.querySelector('#btnProfiles').addEventListener("click", () => { async
    
});
*/