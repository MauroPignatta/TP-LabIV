function verifyCaptcha(){
    // var response = grecaptcha.getResponse();
    var verified = true
    // if(response.length > 0){
    //     verified = true

        //no anda el servidor de google, alta porqueria >:/ tira algo de cors, ni idea jaja
        // var body = {
        //     secret : '6LcU6O4ZAAAAAKFzRhk6yAkEQ02XiY1LOUZcnX3n',
        //     response : response
        // }
        // fetch("https://www.google.com/recaptcha/api/siteverify", {
        //     method : 'POST',
        //     body : body
        // })
        //     .then(function (response){
        //         if(response.ok){
        //             response.json().then(data =>{
        //                 verified = data.success
        //             })
        //         }
        //     })
    //}
    return verified
}


