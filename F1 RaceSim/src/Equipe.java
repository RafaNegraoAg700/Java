public class Equipe {

    private String nome;
    private Piloto piloto1;
    private Piloto piloto2;
    private int pontos;

    public Equipe(String nome) {
        this.nome = nome;
        this.pontos = 0;
    }

    public String getNome() {
        return nome;
    }

    public Piloto getPiloto1() {
        return piloto1;
    }

    public Piloto getPiloto2() {
        return piloto2;
    }

    public int getPontos() {
        return pontos;
    }

    public void adicionarPiloto(Piloto piloto) {

        if (piloto1 == null) {
            piloto1 = piloto;
        } else if (piloto2 == null) {
            piloto2 = piloto;
        }
    }

    public void adicionarPontos(int pontos) {
        this.pontos += pontos;
    }
}