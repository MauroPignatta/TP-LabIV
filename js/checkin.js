const emailRegex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
const passRegex = /^(?=.*\d)(?=.*[a-záéíóúüñ]).*[A-ZÁÉÍÓÚÜÑ]/;
const namesRegex = "/^([a-zA-Z])+$/";

// Funcion que supervisa si el email cumple con los requisitos
function isEmailCorrect(mail) {
    return emailRegex.test(mail.value);
}
// Funcion que supervisa si el password cumple con los requisitos
function isPassCorrect(passView) {
    return passRegex.test(passView.value);
}
// Funcion que supervisa el ingreso de Name y lastName
function isNameCorrect(names) {
    return namesRegex.test(names.value);
}

// DOM EVENTS


document.getElementById('formRegister').addEventListener('submit', function (e) {
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
            url: "rest/servicioPersonas/personas",
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

});
