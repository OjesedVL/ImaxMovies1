public class IngressoVIP extends Ingresso {
    // Construtor
    public IngressoVIP(Filme filme, String tipoIngresso, String horario) {
        super(filme, tipoIngresso, horario);
    }

    @Override
    public void acessoLanchonete() {
        System.out.println("Lanchonete do cinema liberada.");
    }

    @Override
    public String toString() {
        return super.toString() + "\nCategoria: VIP";
    }
}
