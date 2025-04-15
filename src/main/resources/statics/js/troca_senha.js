document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("newPasswordForm");

  form.addEventListener("submit", function (e) {
    e.preventDefault();

    const novaSenha = document.getElementById("newPassword").value.trim();
    const confirmarSenha = document.getElementById("confirmPassword").value.trim();
    const mensagem = document.getElementById("mensagem");

    mensagem.textContent = "";
    mensagem.classList.remove("text-success");
    mensagem.classList.add("text-danger");

    if (!novaSenha || !confirmarSenha) {
      mensagem.textContent = "Por favor, preencha ambos os campos.";
      return;
    }

    if (novaSenha.length < 6) {
      mensagem.textContent = "A senha deve ter pelo menos 6 caracteres.";
      return;
    }

    if (novaSenha !== confirmarSenha) {
      mensagem.textContent = "As senhas não coincidem.";
      return;
    }

    form.reset();
    mensagem.textContent = "";

    const modal = new bootstrap.Modal(document.getElementById('successModal'));
modal.show();
  });
});
