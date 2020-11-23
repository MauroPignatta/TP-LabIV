class UI {
    // Funcion que inserta la Foto mas Nombre y Apellido en index.html
    addProfile(dataLogin){       
        var names = dataLogin.name + " " + dataLogin.lastName;        
        var imgProfile = new Image();
        imgProfile.src = dataLogin.profilePicturePath;
        imgProfile.with = imgProfile.height = "120";

        const profileList = document.getElementById('photoProfileUser');
        const elementProfile = document.createElement('div');
        element.innerHTML = `
            <div class="d-flex">
                <div>
                    <img class="rounded-circle" src="${imgProfile.src}" alt="">
                </div>
                <div class="ml-3 h7">
                    <a href="profile.html">${names}</a>
                </div>
            </div>                      
        `;
        profileList.appendChild(elementProfile);
    }

    // Funcion que Actualiza los datos del perfil en profile.html del usuario
    addPhotoProfileUser(prof){
        // Hay que ver si necesita que se parsee o no el JSON
        const ppu = document.getElementById('photoProfileUser');
        const elementPPU = document.createElement('div');
        var imgPPU = new Image();
        //imgPPU.src = prof.profilePicturePath;
        elementPPU.innerHTML = `
            <div class="d-flex">
                <div>                    
                    <img class="rounded-circle" src="${prof.profilePicturePath}" 
                    width="300" height="300" class="foto row col-lg-10" alt="">
                </div>                
            </div>                      
        `;
        ppu.appendChild(elementPPU);
    }

    // Funcion que agrega posteos de texto y fotos en el index.html
    addPostPhotos(post) {
        const postLists = document.getElementById('publicacionesPost');
        const postPhoto = document.createElement('div');
        
        var newImage = new Image();
        newImage.src = post.imagePath;

        var getProfile = JSON.parse(sessionStorage.getItem('user'));
        console.log(getProfile)
        var name = getProfile.name + ' ' + getProfile.lastname

        if(post.imagePath === ''){
            postPhoto.innerHTML = `
            <div id="${post.postId}" name="postId" class="card my-3">
                <div class="card userName d-flex justify-content-center">
                    <p><b>${name}</b></p>
                </div>               
                <div class="card my-3 card-body pt-0 pb-2">
                    ${post.text}
                </div>
                <div class="card-footer bg-white border-0 p-0">                                
                    <div class="d-flex justify-content-between align-items-center my-1">
                        <div class="col">
                            <p>Fecha: ${post.date.date.year}:${post.date.date.month}:${post.date.date.day}</p>    
                        </div>
                        <div class="col">
                            <p>Hora: ${post.date.time.hour}:${post.date.time.minute}:${post.date.time.second}</p>    
                        </div>
                        <div class="col">
                            <a href="#" class="btn btn-danger" id="btnDelete" name="delete">Delete</a>
                        </div>
                    </div>
                </div>				
            </div>
        `;
        }else{
            postPhoto.innerHTML = `
            <div id="${post.postId}" name="postId" class="card my-3">
                <div class="card userName d-flex justify-content-center">
                    <p><b>${name}</b></p>
                </div>
                <div class="card-img">							
                    <img src="${newImage.src}" width="800" height="700" alt="">
                </div>
                <div class="card my-3 card-body pt-0 pb-2">
                    ${post.text}
                </div>
                <div class="card-footer bg-white border-0 p-0">                                
                    <div class="d-flex justify-content-between align-items-center my-1">
                        <div class="col">
                            <p>Fecha: ${post.date.date.year}:${post.date.date.month}:${post.date.date.day}</p>    
                        </div>
                        <div class="col">
                            <p>Hora: ${post.date.time.hour}:${post.date.time.minute}:${post.date.time.second}</p>    
                        </div>
                        <div class="col">
                            <a href="#" class="btn btn-danger" id="btnDelete" name="delete">Delete</a>
                        </div>
                    </div>
                </div>				
            </div>
        `;
        }
        postLists.appendChild(postPhoto);                 
    }

    deletePost(element) {
        if (element.name === "delete") {
            element.parentElement.parentElement.parentElement.parentElement.remove();
        }
    }
}
const ui = new UI();
