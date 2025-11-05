// Inicializar EmailJS con tu Public Key
emailjs.init("oV5h254JPwVUteW9K");

$(document).ready(function() {

  $("form").on("submit", function(event) {
    event.preventDefault(); // Evita recargar la página
    
    const nombre = $("#nombre").val().trim();
    const telefono = $("#telefono").val().trim();
    const email = $("#email").val().trim();
    const mensaje = $("#mensaje").val().trim();

    // Obtener información de la propiedad desde el DOM
    const nombrePropiedad = $(".nombre-propiedad").text().trim();
    const precioARS = $(".precio-detalle .ars").text().trim();
    const precioUSD = $(".precio-detalle .usd").text().trim() || "No especificado";

    // Validaciones
    if (!nombre || !telefono || !email) {
      alert("⚠️ Por favor, complete todos los campos obligatorios: Nombre, Teléfono y Correo Electrónico.");
      return;
    }

    const soloNumeros = /^[0-9]+$/;
    if (!soloNumeros.test(telefono)) {
      alert("📞 El número de teléfono solo puede contener números.");
      return;
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      alert("📧 Ingrese un correo electrónico válido.");
      return;
    }

    // Parámetros que se enviarán al template de EmailJS
    const templateParams = {
      nombre_usuario: nombre,
      telefono_usuario: telefono,
      email_usuario: email,
      mensaje_usuario: mensaje || "Sin mensaje adicional.",
      propiedad_nombre: nombrePropiedad,
      propiedad_precio_ars: precioARS,
      propiedad_precio_usd: precioUSD
    };

    console.log("🟢 Enviando correo con:", templateParams);

    // Enviar correo con EmailJS
    emailjs.send("service_j0r6c2l", "template_eu67isn", templateParams)
      .then(function(response) {
        console.log("✅ Email enviado correctamente:", response.status, response.text);
        alert("✅ ¡Gracias por tu consulta! Te contactaremos pronto.");
        $("form").trigger("reset");
      })
      .catch(function(error) {
        console.error("❌ Error al enviar el correo:", error);
        alert("⚠️ Ocurrió un error al enviar el correo. Intente nuevamente más tarde.");
      });
  });

  // Validación en tiempo real del campo teléfono
  $("#telefono").on("input", function() {
    this.value = this.value.replace(/[^0-9]/g, "");
  });
});
