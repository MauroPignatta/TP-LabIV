const emailRegex = /^([a-zA-Z0-9-._ñ]+)@([a-zA-Z0-9-._ñ]+).([a-zA-Z]{2,5})$/;
const passRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
const namesRegex = /^([a-zA-Z])$/;
const numberRegex = /^([0-9])+$/;

class UIFunctions {

	// Funcion que supervisa que el campo no este nulo ni vacío
	isNotNullEmpty(text, typeText) {
	    let resul = true;
	    if(text===null || text===' '){
	    	setTimeout(ui.invalidNull(typeText + 'Esta vacío. Ingrese los datos por favor.'),2000);
	    	resul=false;
	    }	    
	    return resul;
	}

	// Funcion que supervisa el ingreso de Name y lastName
	isNameCorrect(namesAndSureName, typeNames) {	 
	    let resName = false;
	    if (/^([a-zA-Z])+$/.test(namesAndSureName)){
	    	resName = true;
	    } else{
	    	setTimeout(ui.invalidName(typeNames + ' ' + 'Debe contener solo letras.'), 2000);
	    }
	    return resName;
	}

	// Funcion que supervisa si el email cumple con los requisitos
	isEmailCorrect(emailAddRegister, typeEmail){
	    let resEmail = false;
	    if (/^([a-zA-Z0-9-.]+)@([a-zA-Z0-9-.]+).([a-zA-Z]{2,5})+$/.test(emailAddRegister)){
	    	resEmail = true;
	    } else{
	    	setTimeout(ui.invalidEmail(typeEmail + ' ' + 'Email ingresado incorrectamente. Intentelo nuevamente por favor.'),2000);
	    }
	    return resEmail;
 	}

 	//Funcion que valida si ambos emails ingresados son iguales o no
 	isEmailEquals(uno, dos){
 		let emailEquals = false;
 		if (uno == dos){
 			emailEquals = true;
 		} else{
 			setTimeout(ui.invalidEqualsEmail('Los campos de Emails no son iguales. Ingreselos nuevamente por favor.'),2000);
 		}
 		return emailEquals;
 	}

	// Funcion que supervisa si el password cumple con los requisitos
	isPassCorrect(passView, typePass){
		let resPass = false;
		if (/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/.test(passView)){
			resPass = true;
		} else{
			setTimeout(ui.invalidPass(typePass + ' ' + 'Debe contener Números, Letras Mayúsculas y Minúsculas, algunos caracteres.'),2000);
		}
	    return resPass;
	}

	// Funcion que supervisa si ambos password ingresados son iguales o no
	isPasswordEquals(p1,p2){
		let equalsPass = false;
		if (p1 == p2){
			equalsPass = true;
		} else {
			setTimeout(ui. invalidEqualsPass('Los campos de Password no son iguales. Ingreselos nuevamente por favor.'),2000);
		}
		return equalsPass;
	}

	// Funcion que supervisa que los numeros asi lo sean
	isNumber(num){
	    let resNumber = false;
	    if (/^([0-9])+$/.test(num)){
	    	resNumber = true;
	    } else{
	    	setTimeout(ui.invalidNumber('Los datos deben ser numéricos.'),2000);
	    }	
	    return resNumber;
	}

	// Funcion que revisa toda la fecha por cada campo y condición
	isFecha(checkin){
	    let resulFecha = true;
	    if(checkin.year<1915 || checkin.year>2005){setTimeout(ui.invalidYear('Debes ingresar un año válido. No se permiten menores de 15 años.'),2000);resulFecha=false;}
	    if(checkin.month<=0 || checkin.month>12){setTimeout(ui.invalidMonth('Debes ingresar un mes válido. Son del 1 al 12'),2000);resulFecha=false;}
	    if(checkin.day<=0 || checkin.day>31){setTimeout(ui.invalidDay('Debes ingresar un día válido. Son del 1 al 31.'),2000);resulFecha=false;}
	    if(checkin.month == 2 && checkin.day == 29){
	        if(!isBisiesto(checkin.year)){
	            setTimeout(ui.invalidBisiesto('El año ingresado no es bisiesto. Revise la fecha completa. Gracias.'),2000);
	            resulFecha=false;
	        }
	    }
	    return resulFecha;
	}

	// Funcion que analiza un año bisiesto o no
	isBisiesto(years){
	    return years % 100 === 0? years % 400 === 0 : years % 4 === 0;
	}

	// Funcion que pide GET 
	sendGetJson(urls){
	    var xhr = new XMLHttpRequest();
	    xhr.open("GET",urls,true);
	    xhr.withCredentials = true;
	    xhr.setRequestHeader("Content-Type", "application/json");    
	    xhr.setRequestHeader("Connection", "close");
	    

	    xhr.onload = function() {
	      if(xhr.readyState === 4 && xhr.status === 200) {
	        return this.responseText;
	      }
	    };
	    xhr.send();
	}

	// Funcion cartero envio POST
	sendPostJson(datta, url){	    
	    try{
	    	var xhr = new XMLHttpRequest();
		    xhr.open("POST",url,true);
		    xhr.setRequestHeader("Content-Type", "application/json");
		    xhr.onload = () => {
				if(xhr.status == 200){
					return this.responseText;
				} else {
					alert('no funciono')
				}
			}	    	
			xhr.send(JSON.stringify(datta));    
	    } catch (e){
	    	console.log('que pasa')
	    }
	}

}
const op = new UIFunctions();
