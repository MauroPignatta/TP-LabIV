var botonDia = document.getElementByID('dia');
var botonNoche = document.getElementByID('noche');
var cuerpo = document.body;

botonDia.addEventListener("click", ()=>{cuerpo.style.backgroundColor = "white";})
botonNoche.addEventListener("click", ()=>{cuerpo.style.backgroundColor = "black";})
