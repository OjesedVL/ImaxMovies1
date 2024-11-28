import java.util.Scanner;

public class MenuController {

    private Scanner scanner;
    private Filme[] filmes;
    private String[] sessoes;
    private double precoInteira;
    private double precoMeia;

    public MenuController() {
        this.scanner = new Scanner(System.in);

        // Inicializando filmes com objetos da classe Filme
        filmes = new Filme[]{
                new Filme("Matrix", "Lilly Wachowski; Lana Wachowskio",
                        "Um hacker descobre que o mundo em que vive é uma simulação virtual criada por máquinas para escravizar a humanidade.",
                        "Ação, Aventura, Ficção Científica", 136, false),
                new Filme("Seven: Os Sete Crimes Capitais", "David Finchers",
                        "Dois detetives perseguem um assassino que usa os sete pecados capitais como inspiração para seus crimes.",
                        "Crime, Drama, Mistério", 127, false),
                new Filme("O Iluminado", "Stanley Kubrick",
                        "Um homem fica obcecado por uma entidade maligna em um hotel isolado durante o inverno, levando-o a perder a razão.",
                        "Terror, Mistério", 146, false),
                new Filme("Scarface", "Brian De Palma",
                        "A história de Tony Montana, um imigrante cubano que se torna um grande traficante de drogas em Miami.",
                        "Crime, Drama", 165, false)
        };

        // Sessões disponíveis
        sessoes = new String[]{
                "1 - 14:00",
                "2 - 16:30",
                "3 - 19:00",
                "4 - 21:30"
        };

        // Preço dos ingressos
        precoInteira = 32.0;
        precoMeia = 16.0;
    }

    public void iniciarCompra() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- Menu de Compra de Ingressos ---");
            Filme filmeEscolhido = selecionarFilme();
            int sessaoEscolhida = selecionarSessao();
            int ingressosInteiros = obterQuantidadeIngressos("inteiros");
            int ingressosMeia = obterQuantidadeIngressos("meia-entrada");

            double total = calcularTotal(ingressosInteiros, ingressosMeia);

            // Criar ingresso
            Ingresso ingresso;
            if (filmeEscolhido.isFilme3D()) {
                ingresso = new IngressoVIP(filmeEscolhido, "Inteira", sessoes[sessaoEscolhida - 1].split(" - ")[1]);
            } else {
                ingresso = new Ingresso(filmeEscolhido, "Inteira", sessoes[sessaoEscolhida - 1].split(" - ")[1]);
            }

            System.out.println("\nResumo do Ingresso:");
            System.out.println(ingresso);
            System.out.println("Total a pagar: R$ " + total);

            ingresso.acessoLanchonete();

            continuar = desejaContinuar();
        }

        System.out.println("Obrigado por utilizar o sistema de compra de ingressos!");
    }

    private boolean desejaContinuar() {
        System.out.print("\nDeseja realizar outra compra? (S/N): ");
        String resposta = scanner.next();
        return resposta.equalsIgnoreCase("S");
    }

    private Filme selecionarFilme() {
        System.out.println("\nEscolha um filme:");
        for (int i = 0; i < filmes.length; i++) {
            System.out.println((i + 1) + " - " + filmes[i].getNome());
        }
        int opcao = obterOpcaoValida("Digite o número do filme desejado: ", 1, filmes.length);
        return filmes[opcao - 1];
    }

    private int selecionarSessao() {
        System.out.println("\nEscolha uma sessão:");
        for (String sessao : sessoes) {
            System.out.println(sessao);
        }
        return obterOpcaoValida("Digite o número da sessão desejada: ", 1, sessoes.length);
    }

    private int obterQuantidadeIngressos(String tipo) {
        System.out.print("Digite a quantidade de ingressos " + tipo + ": ");
        return scanner.nextInt();
    }

    private double calcularTotal(int ingressosInteiros, int ingressosMeia) {
        return (ingressosInteiros * precoInteira) + (ingressosMeia * precoMeia);
    }

    private int obterOpcaoValida(String mensagem, int min, int max) {
        int opcao;
        while (true) {
            System.out.print(mensagem);
            opcao = scanner.nextInt();
            if (opcao >= min && opcao <= max) {
                break;
            } else {
                System.out.println("Opção inválida. Digite um número entre " + min + " e " + max + ".");
            }
        }
        return opcao;
    }
}
