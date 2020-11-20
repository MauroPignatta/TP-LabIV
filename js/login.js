// probar esta o la que le sigue
function enableCaptcha(token) {
    document.getElementByName('login')[0].disable = false;
}

/*
poner en el form  onsubmit="return miFuncion(this)" method="POST"

function miFuncion(a) {
    var response = grecaptcha.getResponse();

    if(response.length == 0){
        alert("Captcha no verificado");
        return false;
      event.preventDefault();
    } else {
      alert("Captcha verificado");
      return true;
    }
  }

*/

// funcion que me trae del Login HTML 
function formLogin(btnLogin) {		

    var loged = {
        email : document.getElementById('email').value,
        pass : document.getElementById('password').value    
    };	

	let resLogin = op.isNotNullEmpty(loged.email,"El Email");
    resLogin &= op.isEmailCorrect(loged.email,"El Email");
    resLogin &= op.isNotNullEmpty(loged.pass,"La Contraseña");    
	resLogin &= op.isPassCorrect(loged.pass,"La Contraseña");

    if (resLogin){sendingLoged(JSON.stringify(loged));}
}

function sendingLoged(dataLogin) {    
    fetch('file:///home/mariano/Documentos/TP-MIO-LABO4/IndexCentral/login.html', {
        method: 'POST',
        body: dataLogin
    })
    .then(function(response) {
        if(response.ok){
            ui.addProfile(dataLogin);
            ui.withImg();
            window.location.href = "html/index.html";
        } else {
            throw 'Error en la llamada a Ajax';
        }
    })   
    .catch(function(err) {
        sessionStorage.setItem('errores', err);
    });
}

// funcion que me trae del Forgot HTML
function formForgot(btnForgot) {
    var pass2 = document.getElementById('password2').value;

    var forgot = {
        email : document.getElementById('email').value,
        pass1 : document.getElementById('password1').value
    };    	
    let resForgot = op.isNotNullEmpty(forgot.email,"El Email");
    resForgot &= op.isEmailCorrect(forgot.email,"El Email");
    resForgot &= op.isNotNullEmpty(forgot.pass1,"La Contraseña");    
    resForgot &= op.isPassCorrect(forgot.pass1,"La Contraseña");    
    resForgot &= op.isPasswordEquals(forgot.pass1,pass2);

    if (resForgot){sendingForgot(JSON.stringify(forgot));}        
}
function sendingForgot(dataForgot) {    
    fetch('file:///home/mariano/Documentos/TP-MIO-LABO4/IndexCentral/login.html', {
        method: 'POST',
        body: dataForgot
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
