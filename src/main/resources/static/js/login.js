document.getElementById("login-form").addEventListener("submit", function(event) {
  event.preventDefault(); // Impede o envio do formulário da maneira tradicional

  const email = document.getElementById("email").value;
  const senha = document.getElementById("password").value;

  const loginData = {
    email: email,
    senha: senha
  };

  fetch("/auth/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json" // Envia os dados como JSON
    },
    body: JSON.stringify(loginData) // Converte os dados para JSON
  })
  .then(response => response.json()) // Processa a resposta do backend
  .then(data => {
    if (data.token) {
      // Redireciona ou armazena o token, caso o login tenha sido bem-sucedido
      localStorage.setItem("token", data.token);
      window.location.href = "/dashboard"; // Redireciona após o login
    } else {
      // Exibe erro de login
      alert("Credenciais inválidas");
    }
  })
  .catch(error => {
    console.error('Error:', error);
    alert("Ocorreu um erro ao tentar realizar o login.");
  });
});
