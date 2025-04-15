const roleSelect = document.getElementById("role");
const dynamicFields = document.getElementById("dynamic-fields");

roleSelect.addEventListener("change", () => {
  const role = roleSelect.value;
  dynamicFields.innerHTML = "";

  if (role === "") return;

  let specificField = "";

  switch (role) {
    case "estabelecimento":
      specificField = `
        <div class="mb-3">
          <label for="cnpj" class="form-label fw-semibold">CNPJ</label>
          <input type="text" id="cnpj" name="cnpj" class="form-control" required maxlength="18" minlength="18" />
        </div>
      `;
      break;

    case "personal":
      specificField = `
        <div class="mb-3">
          <label for="cref" class="form-label fw-semibold">CREF</label>
          <input type="text" id="cref" name="cref" class="form-control" required maxlength="10" />
        </div>
      `;
      break;

    case "aluno":
      specificField = `
        <div class="mb-3">
          <label for="cpf" class="form-label fw-semibold">CPF</label>
          <input type="text" id="cpf" name="cpf" class="form-control" required maxlength="14" minlength="14" />
        </div>
      `;
      break;
  }

  const commonFields = `
    <div class="mb-3">
      <label for="fullName" class="form-label fw-semibold">Nome Completo</label>
      <input type="text" id="fullName" name="fullName" class="form-control" required minlength="3" maxlength="80" />
    </div>

    <div class="mb-3">
      <label for="email" class="form-label fw-semibold">E-mail</label>
      <input type="email" id="email" name="email" class="form-control" required minlength="5" maxlength="100" />
    </div>

    <div class="mb-3">
      <label for="phone" class="form-label fw-semibold">Celular (Opcional)</label>
      <input type="tel" id="phone" name="phone" class="form-control" />
    </div>

    <div class="mb-3">
      <label for="password" class="form-label fw-semibold">Senha (mínimo 6 dígitos)</label>
      <input type="password" id="password" name="password" class="form-control" minlength="6" required />
    </div>
  `;

  dynamicFields.innerHTML = specificField + commonFields;

  applyInputMasks(role);
});

function applyInputMasks(role) {
  if (typeof Inputmask === 'undefined') {
    console.error("Inputmask não foi carregado corretamente.");
    return;
  }

  const phoneInput = document.getElementById("phone");
  if (phoneInput) {
    Inputmask({
      mask: ["(99) 9999-9999", "(99) 99999-9999"],
      keepStatic: true
    }).mask(phoneInput);
  }

  if (role === "estabelecimento") {
    Inputmask("99.999.999/9999-99").mask(document.getElementById("cnpj"));
  } else if (role === "aluno") {
    Inputmask("999.999.999-99").mask(document.getElementById("cpf"));
  } else if (role === "personal") {
    Inputmask("99999-9/AA").mask(document.getElementById("cref"));
  }
}
