function takeProfile(){
    return fetch('https://jsonplaceholder.typicode.com/users')
    .then(response => response.json())
}

document.querySelector('#profiles').addEventListener("click", async() => {
    let user = await takeProfile();

    var newImage = new Image();
    newImage.src = user.img;
    newImage.with = newImage.height = "120";        

    document.getElementByID('idUser').value = user.id;
    document.getElementByID('firstNameProfile').value = user.name;
    document.getElementByID('lastNameProfile').value = user.lastName;
    document.getElementByID('yearProfile').value = user.year;
    document.getElementByID('monthProfile').value = user.month;
    document.getElementByID('dayProfile').value = user.day;
    document.querySelector("#fotoProfile").setAttribute("src", newImage.src);

    var profile = {
        id: user.id,
        name: user.name,
        lastName: user.lastName
    }
    sessionStorage.setItem('profile', profile)
});