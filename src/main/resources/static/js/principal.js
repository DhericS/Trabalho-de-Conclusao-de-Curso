// class CarregadorDeComponentes {
//   constructor(urlMenu, urlRodape, idMenuPlaceholder, idRodapePlaceholder) {
//     this.urlMenu = urlMenu;
//     this.urlRodape = urlRodape;
//     this.idMenuPlaceholder = idMenuPlaceholder;
//     this.idRodapePlaceholder = idRodapePlaceholder;
//   }

//   // Método para carregar um componente HTML no elemento correspondente
//   carregarComponente(url, idPlaceholder) {
//     return fetch(url)
//       .then((resposta) => resposta.text())
//       .then((dados) => {
//         document.getElementById(idPlaceholder).innerHTML = dados;
//       })
//       .catch((erro) =>
//         console.error(
//           `Erro ao carregar o componente de ${idPlaceholder}:`,
//           erro
//         )
//       );
//   }

//   // Método para carregar tanto o menu quanto o rodapé
//   carregarMenuERodape() {
//     this.carregarComponente(this.urlMenu, this.idMenuPlaceholder).then(() =>
//       this.esconderLinksSeNaPagina()
//     );

//     this.carregarComponente(this.urlRodape, this.idRodapePlaceholder);
//   }
//   esconderLinksSeNaPagina() {
//     const urlAtual = window.location.pathname.split("/").pop();
//     const links = document.querySelectorAll(".nav-links a");

//     links.forEach((link) => {
//       const destino = link.getAttribute("href")?.split("/").pop();
//       const id = link.getAttribute("id");

//       if (
//         urlAtual === "index.html" ||
//         urlAtual === "login.html" ||
//         urlAtual === "academias.html" ||
//         urlAtual === "cadastro.html" ||
//         urlAtual === ""
//       ) {
//         // Em páginas públicas, mostra apenas os links que NÃO são a própria página
//         if (
//           (urlAtual !== "login.html" && id === "login-link") ||
//           (urlAtual !== "cadastro.html" && id === "cadastro-link") ||
//           id === "academias-link"
//         ) {
//           // mantém visível
//         } else {
//           link.style.display = "none";
//         }
//       }
//     });
//   }
// }

// // Instância da classe com os caminhos para os componentes HTML e os IDs dos placeholders
// const carregadorDeComponentes = new CarregadorDeComponentes(
//   "../componentes/menu.html",
//   "../componentes/rodape.html",
//   "nav-placeholder",
//   "footer-placeholder"
// );

// // Carrega o menu e rodapé
// carregadorDeComponentes.carregarMenuERodape();
