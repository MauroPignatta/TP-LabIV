function formLogout(logout) {

    let url = 'http://localhost:8080/TpService/rest/account/logout/' + user.id
    fetch(url)
    sessionStorage.removeItem("user")

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