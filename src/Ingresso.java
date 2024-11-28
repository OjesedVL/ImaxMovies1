public class Ingresso {
    private Filme filme;
    private String tipoIngresso; // Inteira ou Meia
    private String horario;

    // Construtor
    public Ingresso(Filme filme, String tipoIngresso, String horario) throws IllegalArgumentException {
        if (filme.isFilme3D()) {
            throw new IllegalArgumentException("Filme 3D só pode ser associado a ingressos VIP.");
        }
        this.filme = filme;
        this.tipoIngresso = tipoIngresso;
        this.horario = horario;
    }

    public void acessoLanchonete() {
        System.out.println("Compre um ingresso VIP para acesso à lanchonete.");
    }

    @Override
    public String toString() {
        return "Filme: " + filme.getNome() +
                "\nTipo de Ingresso: " + tipoIngresso +
                "\nHorário: " + horario;
    }
}
