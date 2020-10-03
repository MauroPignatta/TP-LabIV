console.log('correcto');
document.getElementById('formLogin').addEventListener('submit', saveLogin); //login o register


function saveLogin(e) {	
	// ahora a recuperar los datos ingresados en el form
	console.log('correcto');
	
	let email = document.getElementById('email').value;
	let pass = document.getElementById('password').value;

	let logins = { // creacion de objeto
		email,
		pass
	};
	console.log('correcto');
	var jsonLogin = JSON.stringify(logins);
	console.log(jsonLogin);
	
	

	e.preventDefault(); //para evitar el refresco de pantalla
}



/*

var http = new XMLHttpRequest();
	var url = "tu_url";
	http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	http.open("POST", url, true);

	http.onreadystatechange = function() {
		if(http.readyState == 4 && http.status == 200) { 
		//aqui obtienes la respuesta de tu peticion
		alert(http.responseText);
		}
	}
	http.send(jsonLogin);    

http.send(JSON.stringify({email:email, password: password}));

*/