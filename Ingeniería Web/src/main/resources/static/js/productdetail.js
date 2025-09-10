"use strict";
function pujar() {
  let inputPuja = document.getElementById("nuevaPuja");
  let nuevaPuja = parseFloat(inputPuja.value);
  let productoId = window.location.pathname.split("/").pop();

  if (isNaN(nuevaPuja) || nuevaPuja <= 0) {
    mostrarMensaje("Introduce un valor válido.", "danger");
    return;
  }
  
}

function mostrarMensaje(mensaje, tipo) {
  let mensajeDiv = document.getElementById("mensaje-puja");
  mensajeDiv.innerHTML = `<div class="alert alert-${tipo}" role="alert">${mensaje}</div>`;
}

function subirPuja(precio) {
  const nuevaPuja = document.getElementById("nuevaPuja");
  nuevaPuja.value = Number(nuevaPuja.value) + Number(precio);
}
