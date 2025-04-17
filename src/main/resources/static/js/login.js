document.getElementById('login-form').addEventListener('submit', function(e) {
  e.preventDefault(); // Impede o envio padrão do formulário

  const email = document.getElementById('email').value;
  const senha = document.getElementById('password').value;

  // Enviar os dados como JSON
  fetch('/auth/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json' // Enviando como JSON
    },
    body: JSON.stringify({ email, senha })
  })
  .then(response => {
    if (!response.ok) {
      // Caso o servidor retorne um erro
      throw new Error('Erro no login');
    }
    return response.json();
  })
  .then(data => {
    // Trate a resposta aqui, exemplo de redirecionamento para outra página após o login
    if (data.token) {
      console.log('Login bem-sucedido:', data);
      // Você pode redirecionar o usuário para o dashboard ou qualquer outra página
      window.location.href = "/dashboard"; // Altere para a página desejada
    } else {
      alert('Login falhou. Verifique suas credenciais.');
    }
  })
  .catch(error => {
    console.error('Erro no login:', error);
    alert('Erro no login, tente novamente.');
  });
});
