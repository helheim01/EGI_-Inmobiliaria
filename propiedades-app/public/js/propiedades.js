$(document).ready(function() {
  $("form").on("submit", function(event) {
    event.preventDefault(); // Evita recargar la página
    
    const nombre = $("#nombre").val().trim();
    const telefono = $("#telefono").val().trim();
    const email = $("#email").val().trim();
    const mensaje = $("#mensaje").val().trim();
    
    // Validaciones
    if (!nombre || !telefono || !email) {
      alert("⚠️ Por favor, complete todos los campos obligatorios: Nombre, Teléfono y Correo Electrónico.");
      return;
    }

    // Verificar que el teléfono contenga solo números
    const soloNumeros = /^[0-9]+$/;
    if (!soloNumeros.test(telefono)) {
      alert("📞 El número de teléfono solo puede contener números.");
      return;
    }

    // Verificar formato básico de email
    if (!email.includes("@")) {
      alert("📧 El correo electrónico debe contener '@'.");
      return;
    }

    // Si todo está bien:
    console.log({
      nombre,
      telefono,
      email,
      mensaje
    });

    alert("✅ ¡Gracias por tu consulta! Nos pondremos en contacto pronto.");
    $(this).trigger("reset");
  });

  // Restringir ingreso de caracteres no numéricos en tiempo real
  $("#telefono").on("input", function() {
    this.value = this.value.replace(/[^0-9]/g, "");
  });
});