const emailRegex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
const passRegex = /^(?=.*\d)(?=.*[a-záéíóúüñ]).*[A-ZÁÉÍÓÚÜÑ]/;
const namesRegex = /^([a-zA-Z])+$/;
const numberRegex = /^([0-9])+$/;

class UIFunctions {

	// Funcion que supervisa que el campo no este nulo ni vacío
	isNotNullEmpty(text) {
	    let resul = true;
	    if(text===null || text===' '){ui.danger();resul=false;}
	    return resul;
	}

	// Funcion que supervisa si el email cumple con los requisitos
	isEmailCorrect(mail){
	    let ress=true; 
	    if(!emailRegex.test(mail.value)){ui.invalidAdd('Email inválido. Vuelve a cargarlo.');ress=false;}
	    return ress;
	}

	// Funcion que supervisa si el password cumple con los requisitos
	isPassCorrect(passView){
	    let resPass=true;  
	    if(!passRegex.test(passView.value)){ui.invalidAdd('Debe contener Números, Letras Mayúsculas y Minúsculas, algunos caracteres.');
	        resPass=false;}
	    return resPass;
	}

	// Funcion que supervisa el ingreso de Name y lastName
	isNameCorrect(names) {
	    let resName = true;
	    if(!namesRegex.test(names.value)){ui.invalidAdd('Debe contener solo letras.');resName=false;}
	    return resName;
	}

	// Funcion que supervisa que los numeros asi lo sean
	isNumber(num){
	    let resNumber = true;
	    if(!numberRegex.test(num.value)){ui.invalidAdd('Los datos deben ser numéricos.');resName=false;}
	    return resNumber;
	}

	// Funcion que revisa toda la fecha por cada campo y condición
	isFecha(checkin){
	    let resulFecha = true;
	    if(checkin.year<1915 || checkin.year>2005){ui.invalidAdd('Debes ingresar un año válido. No se permiten menores de 15 años.');resulFecha=false;}
	    if(checkin.month<=0 || checkin.month>12){ui.invalidAdd('Debes ingresar un mes válido. Son del 1 al 12');resulFecha=false;}
	    if(checkin.day<=0 || checkin.day>31){ui.invalidAdd('Debes ingresar un día válido. Son del 1 al 31.');resulFecha=false;}
	    if(checkin.month == 2 && checkin.day == 29){
	        if(!isBisiesto(checkin.year)){
	            ui.invalidAdd('El año ingresado no es bisiesto. Revise la fecha completa. Gracias.');
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

	    xhr.onreadystatechange = function() {
	      if(xhr.readyState === 4 && xhr.status === 200) {
	        return this.responseText;
	      }
	    };
	    xhr.send();
	}

	// Funcion cartero envio POST
	sendPostJson(datta, url){
	    var xhr = new XMLHttpRequest();
	    var data = JSON.stringify(datta);
	    xhr.open("POST",url,true);
	    xhr.withCredentials = true;
	    xhr.setRequestHeader("Content-Type", "application/json");
	    xhr.setRequestHeader("Content-length", data.lenght);
	    xhr.setRequestHeader("Connection", "close");

	    // ver tema load
	    
	    xhr.onreadystatechange = function() {
	      if(xhr.readyState === 4 && xhr.status === 200) {
	        return this.responseText;
	      }
	    };
	    xhr.send(data);
	}

}
const op = new UIFunctions();