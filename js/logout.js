function formLogout(logout) {
	let user = sessionStorage.getItem('elementProfile');
	sendingLogout(user)
}

function sendingLogout(datas) {    
    let info;
    fetch('file:///home/mariano/Documentos/TP-MIO-LABO4/IndexCentral/html/register.html', {
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
    /*
    .then(response => response.json())   
    .then(data => {
        info=data.nombre
        console.log(info)
        window.location.href = "../login.html";
    })
    */
    .catch(function(err) {
        throw 'Error en la llamada a Ajax';
        sessionStorage.setItem('errores', err);
    });
}