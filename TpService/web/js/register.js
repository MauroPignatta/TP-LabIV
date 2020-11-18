// funcion que me trae del Register HTML
function formRegister(btnRegister) {
    var newImages = new Image();
    newImages = document.getElementById('fotoPerfil').value;
    sessionStorage.setItem("photoregile", newImages); 
   
    var checkin = {
        name : document.getElementById('firstName').value,
        lastName : document.getElementById('lastName').value,
        email : document.getElementById('email1').value,        
        password : document.getElementById('password1').value,
        year : document.getElementById('year').value,
        month : document.getElementById('month').value,
        day : document.getElementById('day').value,
        photo : sessionStorage.getItem('photoregile')
    };  
    var mail2 = document.getElementById('email2').value;
        pass2 = document.getElementById('password2').value;

    sessionStorage.removeItem("photoregile");

    let resCheck = op.isNotNullEmpty(checkin.name, "El Nombre");
    resCheck &= op.isNameCorrect(checkin.name, "El Nombre");    
    resCheck &= op.isNotNullEmpty(checkin.lastName, "El Apellido");
    resCheck &= op.isNameCorrect(checkin.lastName, "El Apellido");    
    resCheck &= op.isNotNullEmpty(checkin.email, "El Email");
    resCheck &= op.isEmailCorrect(checkin.email, "El Email");
    resCheck &= op.isEmailEquals(checkin.email,mail2);
    resCheck &= op.isNotNullEmpty(checkin.password, "La contraseña");
    resCheck &= op.isPassCorrect(checkin.password, "La contraseña");
    resCheck &= op.isPasswordEquals(checkin.password,pass2);
    resCheck &= op.isNotNullEmpty(checkin.year);  
    resCheck &= op.isNumber(checkin.year);    
    resCheck &= op.isNotNullEmpty(checkin.month);
    resCheck &= op.isNumber(checkin.month);
    resCheck &= op.isNotNullEmpty(checkin.day);  
    resCheck &= op.isNumber(checkin.day);    
    resCheck &= op.isFecha(checkin);

    if (resCheck){sendingRegister(JSON.stringify(checkin));}
}

function sendingRegister(datas) {    
    fetch('http://localhost:8080/TpService/rest/account/register', {
        method: 'POST',
        body: datas
    })
    .then(function(response) {
        if(response.ok){
            window.location.href = "../login.html";
        } else {
            throw 'Error en la llamada a Ajax';
        }
    })   
    .catch(function(err) {
        sessionStorage.setItem('errores', err);
    });
}
