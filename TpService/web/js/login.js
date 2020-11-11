// funcion que me trae del Register HTML
function formRegister(btnRegister) {
    registed();
};

// funcion que me trae del Login HTML 
function formLogin(btnLogin) {		
	log();
};

// funcion que me trae del Forgot HTML
function formForgot(btnForgot) {
	forgoted();
};

// Funcion que atiende al Login
function log(){
    var urlLog = "http://localhost:8080/TpService/rest/service/login";

    var loged = {
        email : document.getElementById('email').value,
        pass : document.getElementById('password').value    
    };	

	let resLogin = op.isNotNullEmpty(loged.email);
    resLogin &= op.isNotNullEmpty(loged.pass);
    resLogin &= op.isEmailCorrect(loged.email);
	resLogin &= op.isPassCorrect(loged.pass);

	if (resLogin){var resulAjaxPostLog = JSON.parse(op.sendPostJson(loged, urlLog));}
	if (resulAjaxPostLog.result){request();}else{ui.danger();}
}

// Funcion que solicita los datos del Perfil del Usuario
function request(){
    var urlUser = "http://localhost:8081/Devs/rest/service/login";  
        resulAjaxGetUser = JSON.parse(op.sendGetJson(urlUser));

    ui.withImg();
    window.location.href = "index.html";
    ui.addProfile(resulAjaxGetUser);
}

// Funcion que atiende a una nueva contraseña
function forgoted(){
    var urlForgot = "http://localhost:8081/Devs/rest/service/login";
        pass2 = document.getElementById('password2').value;

    var forgot = {
        email : document.getElementById('email').value,
        pass1 : document.getElementById('password1').value
    };    	

    let resForgot = op.isNotNullEmpty(forgot.email);
    resForgot &= op.isNotNullEmpty(forgot.pass1);
    resForgot &= op.isEmailCorrect(forgot.email);
    resForgot &= op.isPassCorrect(forgot.pass1);
    resForgot &= forgot.pass1 == pass2 ? true : false;    

    if (resForgot){var resulAjaxPostForgot = JSON.parse(op.sendPostJson(forgot, urlForgot));}
    if (resulAjaxPostForgot.result){
        ui.correct();
        window.location.href = "login.html";
    }else{
        ui.invalidAdd('Ha ingresado erroneamente su contraseña. Intentelo nuevamente. Gracias!');
        window.location.href = "forgot.html";
    }    
}

// Funcion que atiende al Registro de un Nuevo Usuario
function registed(){
    var urlRegister = "http://localhost:8080/TpService/rest/account/register";
    var checkin = {
        name : document.getElementById('firstName').value,
        lastName : document.getElementById('lastName').value,
        email : document.getElementById('email1').value,        
        password : document.getElementById('password').value,
        year : document.getElementById('year').value,
        month : document.getElementById('month').value,
        day : document.getElementById('day').value,
        photo : localStorage.getItem('myPhotoPerfil')
    };    
    var cemail2 = document.getElementById('email2').value;
        password2 = document.getElementById('password2').value;

    /*let resCheck = op.isNotNullEmpty(checkin.name);
    resCheck &= op.isNotNullEmpty(checkin.lastName);
    resCheck &= op.isNotNullEmpty(checkin.email);
    resCheck &= op.isNotNullEmpty(checkin.password);
    resCheck &= op.isNotNullEmpty(checkin.year);    
    resCheck &= op.isNotNullEmpty(checkin.month);
    resCheck &= op.isNotNullEmpty(checkin.day);  
    resCheck &= op.isNumber(checkin.year);  
    resCheck &= op.isNumber(checkin.month);
    resCheck &= op.isNumber(checkin.day);
    resCheck &= op.isEmailCorrect(checkin.email);
    resCheck &= op.isPassCorrect(checkin.password);
    resCheck &= op.isNameCorrect(checkin.name);
    resCheck &= op.isNameCorrect(checkin.lastName);    
    resCheck &= checkin.email === cemail2;
    resCheck &= checkin.password === password2;
    resCheck &= checkin.password === password2;
    resCheck &= op.isFecha(checkin);*/

    if (true){ //resCheck
        var xhr = new XMLHttpRequest();
        var data = JSON.stringify(checkin);

        xhr.open("POST",urlRegister,true);
        xhr.withCredentials = true;

        xhr.onload = function() {
            if(xhr.readyState === 4 && xhr.status === 200) {


                if (this.responseText === "true"){
                    ui.correct();
                    window.location.href = "../login.html";
                    //localStorage.removeItem('myPhotoPerfil');
                } else {
                    ui.invalidAdd('Ha ingresado erroneamente su contraseña. Intentelo nuevamente. Gracias!');
                    window.location.href = "register.html";
                }
            }
        };
        xhr.send(data);



    }

}

// DOM EVENTS

// Funcion que capta la img y la guarda en localStorage
/*document.querySelector('#fotoPerfil').addEventListener('click', (e) => {
    const reader = new FileReader();    
    reader.addEventListener("load", () => {
        localStorage.setItem("myPhotoPerfil", reader.result); 
    });
    reader.readAsDataURL(e.target.files[0]);
}); */

