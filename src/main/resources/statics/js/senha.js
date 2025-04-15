document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("resetForm");

  form.addEventListener("submit", function (e) {
    e.preventDefault();

    const email = document.getElementById("email").value.trim();

    if (email === "") {
      alert("Por favor, insira um e-mail.");
      return;
    }

    //backend

    const modal = new bootstrap.Modal(document.getElementById('successModal'));
    modal.show();
  });
});
