function formLogout(logout) {    
    sendingLogout();    
    sendingErrorsLists();
}

function sendingLogout() {    
    let user = sessionStorage.getItem('elementProfile');
    if (ser.postFetch('url', user)) {
        msg.correct()
        window.location.href = "../login.html";
    } else {
        throw 'Error en la llamada a Ajax';
    }

    /*
    fetch('file:///home/mariano/Documentos/TP-MIO-LABO4/IndexCentral/html/register.html', {
        method: 'POST',
        body: user
    })
    .then(function(response) {
        if(response.ok){     
            msg.correct()       
            window.location.href = "../login.html";            
        } else {
            throw 'Error en la llamada a Ajax';
        }
    })
    .catch(function(err) {
        op.saveErrorsList(err);
        msg.danger()
    });
    */

}

function sendingErrorsLists() {
    var lista = op.getErrorList;
    fetch('file:///home/mariano/Documentos/TP-MIO-LABO4/IndexCentral/html/register.html', {
        method: 'POST',
        body: lista
    })
    .then(function (response) {
        if (response.ok) {
            console.log('okok') // ver para sacarlo
        } else {
            throw 'Error en la llamada a Ajax';
        }
    })
    .catch(function (err) {
        op.saveErrorsList(err);
        msg.danger()
    });
}