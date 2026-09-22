import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Corrida {

    private String nome;
    private String circuito;
    private ArrayList<Piloto> pilotos;
    private Random random;

    public Corrida(
            String nome,
            String circuito,
            ArrayList<Piloto> pilotos
    ) {
        this.nome = nome;
        this.circuito = circuito;
        this.pilotos = pilotos;
        this.random = new Random();
    }

    public void simular() {

        System.out.println("\n======================================");
        System.out.println("              " + nome);
        System.out.println("              " + circuito);
        System.out.println("======================================");

        boolean chuva = random.nextInt(100) < 30;

        System.out.println(
                "\nCondição da pista: " +
                        (chuva ? "🌧️ CHUVA" : "☀️ SECA")
        );

        System.out.println("\n=== CLASSIFICAÇÃO ===");

        ArrayList<Resultado> resultados = new ArrayList<>();

        for (Piloto piloto : pilotos) {

            double tempoPole = 80 + random.nextDouble() * 8;

            resultados.add(
                    new Resultado(piloto, tempoPole)
            );
        }

        // =========================
        // POLE POSITION
        // =========================

        resultados.sort(
                Comparator.comparingDouble(
                        Resultado::getTempo
                )
        );

        Piloto pole = resultados.get(0).getPiloto();

        pole.adicionarPole();

        System.out.println(
                "\n⏱️ POLE POSITION: " +
                        pole.getNome()
        );

        // =========================
        // CORRIDA
        // =========================

        for (Resultado resultado : resultados) {

            Piloto piloto = resultado.getPiloto();

            double tempo = resultado.getTempo();

            String pneu;

            if (chuva) {

                pneu = random.nextBoolean()
                        ? "Intermediário"
                        : "Chuva";

                tempo += 3 + random.nextDouble() * 5;

            } else {

                String[] pneus = {
                        "Macio",
                        "Médio",
                        "Duro"
                };

                pneu = pneus[
                        random.nextInt(pneus.length)
                        ];

                if (pneu.equals("Macio")) {
                    tempo -= 1.5;
                }

                if (pneu.equals("Duro")) {
                    tempo += 1.5;
                }
            }

            // =========================
            // PIT STOP
            // =========================

            boolean fezPitStop =
                    random.nextInt(100) < 75;

            if (fezPitStop) {
                tempo += 2 + random.nextDouble() * 3;
            }

            // =========================
            // DNF
            // =========================

            boolean abandonou =
                    random.nextInt(100) < 8;

            resultado.setTempo(tempo);
            resultado.setPneu(pneu);
            resultado.setPitStop(fezPitStop);
            resultado.setAbandonou(abandonou);
        }

        resultados.sort(
                Comparator.comparingDouble(
                        Resultado::getTempo
                )
        );

        // =========================
        // PONTUAÇÃO
        // =========================

        int[] pontos = {
                25, 18, 15, 12, 10,
                8, 6, 4, 2, 1
        };

        int posicao = 1;

        for (Resultado resultado : resultados) {

            Piloto piloto = resultado.getPiloto();

            if (resultado.isAbandonou()) {

                piloto.abandonar();

                System.out.println(
                        "DNF - " +
                                piloto.getNome() +
                                " | Problema mecânico"
                );

                continue;
            }

            int pontuacao = 0;

            if (posicao <= pontos.length) {
                pontuacao = pontos[posicao - 1];
            }

            piloto.adicionarPontos(pontuacao);
            piloto.getEquipe().adicionarPontos(pontuacao);
            piloto.completarCorrida();

            if (posicao == 1) {
                piloto.adicionarVitoria();
            }

            System.out.printf(
                    "%dº - %-20s | %2d pts | " +
                            "Pneu: %-13s | Pit: %s%n",

                    posicao,
                    piloto.getNome(),
                    pontuacao,
                    resultado.getPneu(),
                    resultado.isPitStop()
                            ? "Sim"
                            : "Não"
            );

            posicao++;
        }

        System.out.println(
                "\n🏁 Corrida finalizada!"
        );
    }

    // ======================================
    // CLASSE INTERNA
    // ======================================

    private static class Resultado {

        private Piloto piloto;
        private double tempo;
        private String pneu;
        private boolean pitStop;
        private boolean abandonou;

        public Resultado(
                Piloto piloto,
                double tempo
        ) {
            this.piloto = piloto;
            this.tempo = tempo;
        }

        public Piloto getPiloto() {
            return piloto;
        }

        public double getTempo() {
            return tempo;
        }

        public void setTempo(double tempo) {
            this.tempo = tempo;
        }

        public String getPneu() {
            return pneu;
        }

        public void setPneu(String pneu) {
            this.pneu = pneu;
        }

        public boolean isPitStop() {
            return pitStop;
        }

        public void setPitStop(boolean pitStop) {
            this.pitStop = pitStop;
        }

        public boolean isAbandonou() {
            return abandonou;
        }

        public void setAbandonou(boolean abandonou) {
            this.abandonou = abandonou;
        }
    }
}