const emailRegex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
const passRegex = /^(?=.*\d)(?=.*[a-záéíóúüñ]).*[A-ZÁÉÍÓÚÜÑ]/;
const namesRegex = /^([a-zA-Z])+$/;

// funcion que me trae del Login HTML 
function formLogin(btnLogin) {		
	log();
};

// funcion que me trae del Forgot HTML
function formForgot(btnForgot) {
	forgoted();
};

// funcion que me trae del Register HTML
function formRegister(btnRegister) {
	registed();
};

// Funcion que supervisa si el email cumple con los requisitos
function isEmailCorrect(mail){
	if(mail===null || mail===' '){ui.danger();}else{return emailRegex.test(mail.value);}	
}
// // Funcion que supervisa si el password cumple con los requisitos
function isPassCorrect(passView){
	if(passView===null || passView===' '){ui.danger();}else{return passRegex.test(passView.value);}	
}

// Funcion que supervisa el ingreso de Name y lastName
function isNameCorrect(names) {
	if(names===null || names===' '){ui.danger();}else{return namesRegex.test(names.value);}    
}

function log(){
	loged.email = document.getElementById('email');
	loged.pass = document.getElementById('password');

	let resLogin = isEmailCorrect(loged.email);
	resLogin &= isPassCorrect(loged.pass);

	if (resLogin){
		$.ajax({
			url: "rest/restServices",
			type: "POST",
			data: JSON.stringify(loged),
			contentType: "application/json",
			complete: resultado
		});					
		ui.withImg();
	}else{
		ui.danger();
	}	
	
	// FALTA DEVOLVER AL FRONT Y CONTINUAR AL SITIO O QUE LO INGRESE NUEVAMENTE
}


function forgoted(){
	forgot.email = document.getElementById('email');
    forgot.pass1 = document.getElementById('password1');
    forgot.pass2 = document.getElementById('password2');

    let resForgot = isPassCorrect(forgot.pass1);
    resForgot &= forgot.pass1 == forgot.pass2 ? true : false;

    if (resForgot) {
        $.ajax({
            url: "rest/rest/restServices",
            type: "POST",
            data: JSON.stringify(forgot),
            contentType: "application/json",
            complete: resultado
        });
        ui.withImg();
    } else {
        ui.danger();
    }

    // FALTA DEVOLVER AL FRONT Y CONTINUAR AL SITIO O QUE LO INGRESE NUEVAMENTE
}

function registed(){
	checkin.name = document.getElementById('firstName');
    checkin.lastName = document.getElementById('lastName');
    checkin.birthdate = document.getElementById('birthdate');
    checkin.email1 = document.getElementById('email1');
    checkin.email2 = document.getElementById('email2');
    checkin.address1 = document.getElementById('address1');
    checkin.address2 = document.getElementById('address2');

    let resCheck = isEmailCorrect(checkin.email1);
    resCheck &= isPassCorrect(checkin.address1);
    resCheck &= isNameCorrect(checkin.name);
    resCheck &= isNameCorrect(checkin.lastName);
    resCheck &= checkin.email1 == checkin.email2 ? true : false;
    resCheck &= checkin.address1 == checkin.address2 ? true : false;

    if (resCheck) {
        $.ajax({
            url: "rest/rest/restServices",
            type: "POST",
            data: JSON.stringify(checkin),
            contentType: "application/json",
            complete: resultado
        });
        ui.withImg();
    } else {
        ui.danger();
    }

    // FALTA DEVOLVER AL FRONT Y CONTINUAR AL SITIO O QUE LO INGRESE NUEVAMENTE
}
