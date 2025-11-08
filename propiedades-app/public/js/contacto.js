// Inicializar EmailJS con tu Public Key
emailjs.init("oV5h254JPwVUteW9K");

$(document).ready(function() {

  $(".formulario-contacto-principal").on("submit", function(event) {
    event.preventDefault(); // Evita recargar la página
    
    const nombre = $("#nombre").val().trim();
    const telefono = $("#telefono").val().trim();
    const email = $("#email").val().trim();
    const asunto = $("#asunto").val().trim();
    const mensaje = $("#mensaje").val().trim();

    // Validaciones
    if (!nombre || !telefono || !email || !asunto || !mensaje) {
      alert("⚠️ Por favor, complete todos los campos obligatorios.");
      return;
    }

    const soloNumeros = /^[0-9+\s-]+$/;
    if (!soloNumeros.test(telefono)) {
      alert("📞 El número de teléfono solo puede contener números, espacios, + y -.");
      return;
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      alert("📧 Ingrese un correo electrónico válido.");
      return;
    }

    // Convertir el valor del asunto a texto legible
    const asuntoTexto = {
      'compra': 'Consulta sobre compra',
      'venta': 'Quiero vender mi propiedad',
      'tasacion': 'Solicitar tasación',
      'asesoria': 'Asesoramiento',
      'otro': 'Otro'
    };

    // Parámetros que se enviarán al template de EmailJS
    const templateParams = {
      nombre_usuario: nombre,
      telefono_usuario: telefono,
      email_usuario: email,
      asunto_consulta: asuntoTexto[asunto] || asunto,
      mensaje_usuario: mensaje
    };

    console.log("🟢 Enviando correo de contacto con:", templateParams);

    // Enviar correo con EmailJS usando el template correcto
    emailjs.send("service_j0r6c2l", "template_8pl140t", templateParams)
      .then(function(response) {
        console.log("✅ Email enviado correctamente:", response.status, response.text);
        alert("✅ ¡Gracias por tu consulta! Te contactaremos pronto.");
        $(".formulario-contacto-principal").trigger("reset");
      })
      .catch(function(error) {
        console.error("❌ Error al enviar el correo:", error);
        alert("⚠️ Ocurrió un error al enviar el correo. Intente nuevamente más tarde.");
      });
  });

  // Validación en tiempo real del campo teléfono
  $("#telefono").on("input", function() {
    this.value = this.value.replace(/[^0-9+\s-]/g, "");
  });
});