let academias = [];
let templateCard = [];


// Função principal que gera os cards
async function carregarCards(dados) {
    const container = document.getElementById("cards-container");

    // Limpa os cards antigos
    container.innerHTML = "";

    // Atualiza o contador de academias
    document.getElementById("contador-academias").textContent = dados.length;

    dados.forEach(item => {
        const temp = document.createElement("div");
        temp.innerHTML = templateCard;

        temp.querySelector(".card-img").src = item.imagem;
        temp.querySelector(".card-img").alt = item.nome;
        temp.querySelector(".card-nome").textContent = item.nome;
        temp.querySelector(".card-endereco").textContent = item.endereco;
        temp.querySelector(".card-botao").href = `detalhes_academias.html?id=${item.id}`;

        container.appendChild(temp.firstElementChild);
    });
}

    document.addEventListener("DOMContentLoaded", async () => {
    // Carrega template
    templateCard = await fetch("../componentes/card_base.html")
        .then(res => {
        if (!res.ok) throw new Error("Erro ao carregar o template do card");
        return res.text();
        })
        .catch(err => {
        console.error(err);
        return "";
    });

    // Mock de dados
    academias = [
    {
        nome: "Academia Força Total",
        endereco: "Rua Exemplo, 123 - Centro - SP",
        imagem: "../assets/imagens/fundo-de-cidade-demolido-de-darth-vader-4k-lrk5z7pw2kgpincr.webp",
        id: 1
    },
    {
        nome: "Gym Pro",
        endereco: "Av. Fitness, 456 - Vila Saúde - RJ",
        imagem: "../assets/imagens/fundo-de-cidade-demolido-de-darth-vader-4k-lrk5z7pw2kgpincr.webp",
        id: 2
    },
    {
        nome: "Academia Força Total",
        endereco: "Rua Exemplo, 123 - Centro - SP",
        imagem: "../assets/imagens/fundo-de-cidade-demolido-de-darth-vader-4k-lrk5z7pw2kgpincr.webp",
        id: 3
    },
    {
        nome: "Gym Pro",
        endereco: "Av. Fitness, 456 - Vila Saúde - RJ",
        imagem: "../assets/imagens/fundo-de-cidade-demolido-de-darth-vader-4k-lrk5z7pw2kgpincr.webp",
        id: 4
    },
    {
    nome: "Academia Força Total",
        endereco: "Rua Exemplo, 123 - Centro - SP",
        imagem: "../assets/imagens/fundo-de-cidade-demolido-de-darth-vader-4k-lrk5z7pw2kgpincr.webp",
        id: 5
    },
    {
        nome: "Gym Pro",
        endereco: "Av. Fitness, 456 - Vila Saúde - RJ",
        imagem: "../assets/imagens/fundo-de-cidade-demolido-de-darth-vader-4k-lrk5z7pw2kgpincr.webp",
        id: 6
    },
    {
        nome: "Academia Força Total",
        endereco: "Rua Exemplo, 123 - Centro - SP",
        imagem: "../assets/imagens/fundo-de-cidade-demolido-de-darth-vader-4k-lrk5z7pw2kgpincr.webp",
        id: 7
    },
    {
        nome: "Gym Pro",
        endereco: "Av. Fitness, 456 - Vila Saúde - RJ",
        imagem: "../assets/imagens/fundo-de-cidade-demolido-de-darth-vader-4k-lrk5z7pw2kgpincr.webp",
        id: 8
    },
    {
        nome: "Academia Força Total",
        endereco: "Rua Exemplo, 123 - Centro - SP",
        imagem: "../assets/imagens/fundo-de-cidade-demolido-de-darth-vader-4k-lrk5z7pw2kgpincr.webp",
        id: 9
    },
    {
        nome: "Gym Pro",
        endereco: "Av. Fitness, 456 - Vila Saúde - RJ",
        imagem: "../assets/imagens/fundo-de-cidade-demolido-de-darth-vader-4k-lrk5z7pw2kgpincr.webp",
        id: 10
    },
    {
        nome: "Academia Força Total",
        endereco: "Rua Exemplo, 123 - Centro - SP",
        imagem: "../assets/imagens/fundo-de-cidade-demolido-de-darth-vader-4k-lrk5z7pw2kgpincr.webp",
        id: 11
    },
    {
        nome: "Gym Pro",
        endereco: "Av. Fitness, 456 - Vila Saúde - RJ",
        imagem: "../assets/imagens/fundo-de-cidade-demolido-de-darth-vader-4k-lrk5z7pw2kgpincr.webp",
        id: 12
    },
    {
        nome: "Academia Força Total",
        endereco: "Rua Exemplo, 123 - Centro - SP",
        imagem: "../assets/imagens/fundo-de-cidade-demolido-de-darth-vader-4k-lrk5z7pw2kgpincr.webp",
        id: 13
    },
    {
        nome: "Gym Pro",
        endereco: "Av. Fitness, 456 - Vila Saúde - RJ",
        imagem: "../assets/imagens/fundo-de-cidade-demolido-de-darth-vader-4k-lrk5z7pw2kgpincr.webp",
        id: 14
    },
    {
        nome: "Academia Força Total",
        endereco: "Rua Exemplo, 123 - Centro - SP",
        imagem: "../assets/imagens/fundo-de-cidade-demolido-de-darth-vader-4k-lrk5z7pw2kgpincr.webp",
        id: 15
    },
    {
        nome: "Gym Pro",
        endereco: "Av. Fitness, 456 - Vila Saúde - RJ",
        imagem: "../assets/imagens/fundo-de-cidade-demolido-de-darth-vader-4k-lrk5z7pw2kgpincr.webp",
        id: 16
    }

    ];
// Adiciona filtro de busca
    const inputPesquisa = document.getElementById("entradaPesquisa");
    inputPesquisa.addEventListener("input", e => {
        const termo = e.target.value.toLowerCase();

    const filtradas = academias.filter(academia =>
        academia.nome.toLowerCase().includes(termo) ||
        academia.endereco.toLowerCase().includes(termo)
    );

    carregarCards(filtradas);
});
})