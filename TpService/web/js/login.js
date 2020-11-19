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
        password : document.getElementById('password').value
    };

	let resLogin = op.isNotNullEmpty(loged.email,"El Email");
    resLogin &= op.isEmailCorrect(loged.email,"El Email");
    resLogin &= op.isNotNullEmpty(loged.password,"La Contraseña");
	resLogin &= op.isPassCorrect(loged.password,"La Contraseña");

    loged.password = btoa(loged.password);

    if (resLogin){sendingLoged(JSON.stringify(loged));}
}

function sendingLoged(dataLogin) {
    fetch('http://localhost:8080/TpService/rest/account/login', {
        method: 'POST',
        body: dataLogin
    })
    .then(function(response) {
        if(response.ok){

            response.json().then(data=>{
            
                if(data){
                    request(dataLogin);
                }else{
                    console.log("contrasenia incorrecta")
                }
            })

        } else {
            throw 'Error en la llamada a Ajax';
        }
    })
    .catch(function(err) {
        sessionStorage.setItem('errores', err);
    });

}

// Funcion que solicita los datos del Perfil del Usuario
function request(dataLogin){
    // var resultLogin = JSON.parse(dataLogin);
    // ui.withImg();
     window.location.href = "html/index.html";
    // ui.addProfile(resultLogin);
}


// funcion que me trae del Forgot HTML
function formForgot(btnForgot) {
    let pass2 = document.getElementById('password2').value;

    let forgot = {
        email : document.getElementById('email').value,
        password : document.getElementById('password1').value
    };
    let resForgot = op.isNotNullEmpty(forgot.email,"El Email");
    resForgot &= op.isEmailCorrect(forgot.email,"El Email");
    resForgot &= op.isNotNullEmpty(forgot.password,"La Contraseña");
    resForgot &= op.isPassCorrect(forgot.password,"La Contraseña");
    resForgot &= op.isPasswordEquals(forgot.password,pass2);

    if (resForgot){sendingForgot(JSON.stringify(forgot));}
}
function sendingForgot(dataForgot) {
    fetch('http://localhost:8080/TpService/rest/account/forgot', {
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
