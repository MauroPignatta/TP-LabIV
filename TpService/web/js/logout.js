function formLogout() {
    let user = JSON.parse(sessionStorage.getItem('user'));
    let url = 'http://localhost:8080/TpService/rest/account/logout/' + user.id

    fetch(url)
        .then(response => {
            console.log(response)
            window.location.href = "http://localhost:8080/TpService/";
        })

    //sendingErrorsLists();
}

function sendingLogout() {

}

// function sendingErrorsLists() {
//     var lista = op.getErrorList;
//     fetch('file:///home/mariano/Documentos/TP-MIO-LABO4/IndexCentral/html/register.html', {
//         method: 'POST',
//         body: lista
//     })
//     .then(function (response) {
//         if (response.ok) {
//             console.log('okok') // ver para sacarlo
//         } else {
//             throw 'Error en la llamada a Ajax';
//         }
//     })
//     .catch(function (err) {
//         op.saveErrorsList(err);
//         msg.danger()
//     });
// }