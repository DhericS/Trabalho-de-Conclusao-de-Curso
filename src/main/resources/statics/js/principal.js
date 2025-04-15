class CarregadorDeComponentes {
    constructor(urlMenu, urlRodape, idMenuPlaceholder, idRodapePlaceholder) {
        this.urlMenu = urlMenu; 
        this.urlRodape = urlRodape; 
        this.idMenuPlaceholder = idMenuPlaceholder; 
        this.idRodapePlaceholder = idRodapePlaceholder; 
    }

    // Método para carregar um componente HTML no elemento correspondente
    carregarComponente(url, idPlaceholder) {
        return fetch(url) 
        .then(resposta => resposta.text())  
        .then(dados => {
            document.getElementById(idPlaceholder).innerHTML = dados;  
    })
        .catch(erro => console.error(`Erro ao carregar o componente de ${idPlaceholder}:`, erro));
    }

    // Método para carregar tanto o menu quanto o rodapé
    carregarMenuERodape() {
        this.carregarComponente(this.urlMenu, this.idMenuPlaceholder)
            .then (() => this.esconderLinksSeNaPagina()); 
            
        this.carregarComponente(this.urlRodape, this.idRodapePlaceholder);  
    }
    esconderLinksSeNaPagina() {
        const urlAtual = window.location.pathname.split('/').pop()  
        const links = document.querySelectorAll('.nav-links a'); 

        links.forEach(link => {
            const destino = link.getAttribute('href')?.split('/').pop();  // Pega o destino do link (href)
            const id = link.getAttribute('id');
            if (destino === urlAtual && id !== 'sobre-link') {  // Se o destino do link for igual à URL atual
                link.style.display = 'none';  // Esconde o link
            }
        });
    }
}
  
    // Instância da classe com os caminhos para os componentes HTML e os IDs dos placeholders
    const carregadorDeComponentes = new CarregadorDeComponentes(
        '../componentes/menu.html',  
        '../componentes/rodape.html', 
        'nav-placeholder',  
        'footer-placeholder'  
    );
    
// Carrega o menu e rodapé
carregadorDeComponentes.carregarMenuERodape();

