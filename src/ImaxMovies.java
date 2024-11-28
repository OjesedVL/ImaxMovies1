import java.util.Scanner;

public class ImaxMovies {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Lista de filmes com suas informações detalhadas
        Filme[] filmes = {
                new Filme("Matrix", "Lana Wachowski, Lilly Wachowski",
                        "Um hacker descobre que o mundo em que vive é uma simulação virtual criada por máquinas para escravizar a humanidade.",
                        "Ação, Ficção Científica", 136, true),
                new Filme("Seven: Os Sete Crimes Capitais", "David Fincher",
                        "Dois detetives perseguem um assassino que usa os sete pecados capitais como inspiração para seus crimes.",
                        "Crime, Drama, Mistério", 127, false),
                new Filme("O Iluminado", "Stanley Kubrick",
                        "Um homem fica obcecado por uma entidade maligna em um hotel isolado durante o inverno, levando-o a perder a razão.",
                        "Terror, Mistério", 146, false),
                new Filme("Scarface", "Brian De Palma",
                        "A história de Tony Montana, um imigrante cubano que se torna um grande traficante de drogas em Miami.",
                        "Crime, Drama", 165, true) // Filme 3D
        };

        // Preço dos ingressos
        double precoInteira = 32.0;
        double precoMeia = 16.0;

        boolean continuar = true;

        while (continuar) {
            // Exibir lista de filmes e suas informações
            System.out.println("Escolha um filme e veja suas informações:\n");
            for (int i = 0; i < filmes.length; i++) {
                System.out.println((i + 1) + " - " + filmes[i].getNome());
                System.out.println(filmes[i]); // Exibe informações do filme usando toString()
                System.out.println("----------------------------------------------------");
            }

            // Perguntar ao usuário qual filme ele deseja escolher
            System.out.print("Digite o número do filme desejado: ");
            int filmeEscolhido = scanner.nextInt();

            // Validação da escolha do filme
            while (filmeEscolhido < 1 || filmeEscolhido > filmes.length) {
                System.out.println("Opção inválida. Por favor, escolha um número entre 1 e " + filmes.length);
                System.out.print("Digite o número do filme desejado: ");
                filmeEscolhido = scanner.nextInt();
            }

            // Lista de sessões
            String[] sessoes = {
                    "1 - 14:00",
                    "2 - 16:30",
                    "3 - 19:00",
                    "4 - 21:30"
            };

            // Exibir lista de sessões
            System.out.println("\nEscolha a sessão:");
            for (String sessao : sessoes) {
                System.out.println(sessao);
            }
            System.out.print("Digite o número da sessão desejada: ");
            int sessaoEscolhida = scanner.nextInt();

            // Validação da escolha da sessão
            while (sessaoEscolhida < 1 || sessaoEscolhida > sessoes.length) {
                System.out.println("Opção inválida. Por favor, escolha um número entre 1 e " + sessoes.length);
                System.out.print("Digite o número da sessão desejada: ");
                sessaoEscolhida = scanner.nextInt();
            }

            // Perguntar a quantidade de ingressos
            System.out.print("\nDigite a quantidade de ingressos inteiros: ");
            int ingressosInteiros = scanner.nextInt();

            System.out.print("Digite a quantidade de ingressos meia-entrada: ");
            int ingressosMeia = scanner.nextInt();

            // Calcular total
            double total = (ingressosInteiros * precoInteira) + (ingressosMeia * precoMeia);

            // Criar ingresso para o filme escolhido
            Filme filmeEscolhidoObjeto = filmes[filmeEscolhido - 1];
            String tipoIngresso = (ingressosInteiros > 0) ? "Inteira" : "Meia";
            String horarioSessao = sessoes[sessaoEscolhida - 1].split(" - ")[1];

            Ingresso ingresso;

            // Verifica o tipo de filme e cria o ingresso correspondente
            if (filmeEscolhidoObjeto.isFilme3D()) {
                System.out.println("Filme 3D selecionado. Criando ingresso VIP automaticamente.");
                ingresso = new IngressoVIP(filmeEscolhidoObjeto, tipoIngresso, horarioSessao);
            } else {
                ingresso = new Ingresso(filmeEscolhidoObjeto, tipoIngresso, horarioSessao);
            }

            // Exibir o ingresso
            System.out.println("\nResumo do Ingresso:");
            System.out.println(ingresso);
            System.out.println("Total a pagar: R$ " + total);

            // Acesso à lanchonete
            ingresso.acessoLanchonete();

            // Perguntar se o usuário deseja realizar outra compra
            System.out.print("\nDeseja realizar outra compra? (S/N): ");
            String resposta = scanner.next();

            if (!resposta.equalsIgnoreCase("S")) {
                continuar = false;
            }
        }

        System.out.println("Obrigado por utilizar o ImaxMovies. Até logo!");
        scanner.close();
    }
}
