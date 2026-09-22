public class Piloto {

    private String nome;
    private int numero;
    private Equipe equipe;
    private int pontos;
    private int vitorias;
    private int poles;
    private int corridasCompletadas;
    private int abandonos;

    public Piloto(String nome, int numero, Equipe equipe) {
        this.nome = nome;
        this.numero = numero;
        this.equipe = equipe;
        this.pontos = 0;
        this.vitorias = 0;
        this.poles = 0;
        this.corridasCompletadas = 0;
        this.abandonos = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public int getPontos() {
        return pontos;
    }

    public int getVitorias() {
        return vitorias;
    }

    public int getPoles() {
        return poles;
    }

    public int getCorridasCompletadas() {
        return corridasCompletadas;
    }

    public int getAbandonos() {
        return abandonos;
    }

    public void adicionarPontos(int pontos) {
        this.pontos += pontos;
    }

    public void adicionarVitoria() {
        this.vitorias++;
    }

    public void adicionarPole() {
        this.poles++;
    }

    public void completarCorrida() {
        this.corridasCompletadas++;
    }

    public void abandonar() {
        this.abandonos++;
    }
}