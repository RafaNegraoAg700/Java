import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // =====================================
        // EQUIPES
        // =====================================

        ArrayList<Equipe> equipes = new ArrayList<>();

        Equipe mercedes = new Equipe("Mercedes");
        Equipe mclaren = new Equipe("McLaren");
        Equipe ferrari = new Equipe("Ferrari");
        Equipe redBull = new Equipe("Red Bull");
        Equipe astonMartin = new Equipe("Aston Martin");

        equipes.add(mercedes);
        equipes.add(mclaren);
        equipes.add(ferrari);
        equipes.add(redBull);
        equipes.add(astonMartin);

        // =====================================
        // PILOTOS
        // =====================================

        ArrayList<Piloto> pilotos = new ArrayList<>();

        Piloto russell =
                new Piloto("George Russell", 63, mercedes);

        Piloto antonelli =
                new Piloto("Kimi Antonelli", 12, mercedes);

        Piloto norris =
                new Piloto("Lando Norris", 4, mclaren);

        Piloto piastri =
                new Piloto("Oscar Piastri", 81, mclaren);

        Piloto leclerc =
                new Piloto("Charles Leclerc", 16, ferrari);

        Piloto hamilton =
                new Piloto("Lewis Hamilton", 44, ferrari);

        Piloto verstappen =
                new Piloto("Max Verstappen", 3, redBull);

        Piloto hadjar =
                new Piloto("Isack Hadjar", 6, redBull);

        Piloto alonso =
                new Piloto("Fernando Alonso", 14, astonMartin);

        Piloto stroll =
                new Piloto("Lance Stroll", 18, astonMartin);

        pilotos.add(russell);
        pilotos.add(antonelli);
        pilotos.add(norris);
        pilotos.add(piastri);
        pilotos.add(leclerc);
        pilotos.add(hamilton);
        pilotos.add(verstappen);
        pilotos.add(hadjar);
        pilotos.add(alonso);
        pilotos.add(stroll);

        // =====================================
        // ADICIONAR PILOTOS ÀS EQUIPES
        // =====================================

        mercedes.adicionarPiloto(russell);
        mercedes.adicionarPiloto(antonelli);

        mclaren.adicionarPiloto(norris);
        mclaren.adicionarPiloto(piastri);

        ferrari.adicionarPiloto(leclerc);
        ferrari.adicionarPiloto(hamilton);

        redBull.adicionarPiloto(verstappen);
        redBull.adicionarPiloto(hadjar);

        astonMartin.adicionarPiloto(alonso);
        astonMartin.adicionarPiloto(stroll);

        // =====================================
        // CALENDÁRIO - 10 CORRIDAS
        // =====================================

        String[] corridas = {
                "GP da Austrália",
                "GP do Japão",
                "GP de Miami",
                "GP da Emília-Romanha",
                "GP de Mônaco",
                "GP da Espanha",
                "GP da Grã-Bretanha",
                "GP da Bélgica",
                "GP da Itália",
                "GP de São Paulo"
        };

        String[] circuitos = {
                "Albert Park",
                "Suzuka",
                "Miami",
                "Imola",
                "Monte Carlo",
                "Barcelona",
                "Silverstone",
                "Spa-Francorchamps",
                "Monza",
                "Interlagos"
        };

        int corridaAtual = 0;

        int opcao;

        do {

            System.out.println("\n====================================");
            System.out.println("        🏎️ F1 RACE SIMULATOR");
            System.out.println("====================================");

            System.out.println(
                    "Corrida: " +
                            corridaAtual +
                            "/10"
            );

            System.out.println("\n1 - Ver pilotos");
            System.out.println("2 - Simular próxima corrida");
            System.out.println("3 - Classificação de pilotos");
            System.out.println("4 - Classificação de construtores");
            System.out.println("5 - Ver calendário");
            System.out.println("6 - Ver campeão");
            System.out.println("0 - Sair");

            System.out.print("\nEscolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:

                    mostrarPilotos(pilotos);

                    break;

                case 2:

                    if (corridaAtual >= 10) {

                        System.out.println(
                                "\nA temporada já terminou!"
                        );

                        break;
                    }

                    Corrida corrida = new Corrida(
                            corridas[corridaAtual],
                            circuitos[corridaAtual],
                            pilotos
                    );

                    corrida.simular();

                    corridaAtual++;

                    break;

                case 3:

                    mostrarClassificacao(pilotos);

                    break;

                case 4:

                    mostrarConstrutores(equipes);

                    break;

                case 5:

                    mostrarCalendario(
                            corridas,
                            corridaAtual
                    );

                    break;

                case 6:

                    mostrarCampeao(
                            pilotos,
                            equipes,
                            corridaAtual
                    );

                    break;

                case 0:

                    System.out.println(
                            "\nEncerrando o campeonato..."
                    );

                    break;

                default:

                    System.out.println(
                            "\nOpção inválida!"
                    );
            }

        } while (opcao != 0);

        scanner.close();
    }

    // =====================================
    // PILOTOS
    // =====================================

    public static void mostrarPilotos(
            ArrayList<Piloto> pilotos
    ) {

        System.out.println(
                "\n========== PILOTOS =========="
        );

        for (Piloto piloto : pilotos) {

            System.out.printf(
                    "#%-3d %-20s | %-15s | %d pts%n",
                    piloto.getNumero(),
                    piloto.getNome(),
                    piloto.getEquipe().getNome(),
                    piloto.getPontos()
            );
        }
    }

    // =====================================
    // CLASSIFICAÇÃO
    // =====================================

    public static void mostrarClassificacao(
            ArrayList<Piloto> pilotos
    ) {

        ArrayList<Piloto> classificacao =
                new ArrayList<>(pilotos);

        classificacao.sort(
                Comparator.comparingInt(
                        Piloto::getPontos
                ).reversed()
        );

        System.out.println(
                "\n====== CAMPEONATO DE PILOTOS ======"
        );

        for (int i = 0; i < classificacao.size(); i++) {

            Piloto piloto = classificacao.get(i);

            System.out.printf(
                    "%2dº - %-20s | %3d pts | " +
                            "Vitórias: %d | Poles: %d%n",

                    i + 1,
                    piloto.getNome(),
                    piloto.getPontos(),
                    piloto.getVitorias(),
                    piloto.getPoles()
            );
        }
    }

    // =====================================
    // CONSTRUTORES
    // =====================================

    public static void mostrarConstrutores(
            ArrayList<Equipe> equipes
    ) {

        ArrayList<Equipe> classificacao =
                new ArrayList<>(equipes);

        classificacao.sort(
                Comparator.comparingInt(
                        Equipe::getPontos
                ).reversed()
        );

        System.out.println(
                "\n===== CAMPEONATO DE CONSTRUTORES ====="
        );

        for (int i = 0; i < classificacao.size(); i++) {

            Equipe equipe = classificacao.get(i);

            System.out.printf(
                    "%dº - %-20s | %d pontos%n",
                    i + 1,
                    equipe.getNome(),
                    equipe.getPontos()
            );
        }
    }

    // =====================================
    // CALENDÁRIO
    // =====================================

    public static void mostrarCalendario(
            String[] corridas,
            int corridaAtual
    ) {

        System.out.println(
                "\n========== CALENDÁRIO =========="
        );

        for (int i = 0; i < corridas.length; i++) {

            String status;

            if (i < corridaAtual) {

                status = "✓ Finalizada";

            } else if (i == corridaAtual) {

                status = "→ Próxima";

            } else {

                status = "Pendente";
            }

            System.out.printf(
                    "%2d. %-25s | %s%n",
                    i + 1,
                    corridas[i],
                    status
            );
        }
    }

    // =====================================
    // CAMPEÃO
    // =====================================

    public static void mostrarCampeao(
            ArrayList<Piloto> pilotos,
            ArrayList<Equipe> equipes,
            int corridaAtual
    ) {

        if (corridaAtual < 10) {

            System.out.println(
                    "\nA temporada ainda não terminou!"
            );

            System.out.println(
                    "Corridas realizadas: " +
                            corridaAtual +
                            "/10"
            );

            return;
        }

        ArrayList<Piloto> pilotosOrdenados =
                new ArrayList<>(pilotos);

        pilotosOrdenados.sort(
                Comparator.comparingInt(
                        Piloto::getPontos
                ).reversed()
        );

        ArrayList<Equipe> equipesOrdenadas =
                new ArrayList<>(equipes);

        equipesOrdenadas.sort(
                Comparator.comparingInt(
                        Equipe::getPontos
                ).reversed()
        );

        Piloto campeao =
                pilotosOrdenados.get(0);

        Equipe equipeCampea =
                equipesOrdenadas.get(0);

        System.out.println(
                "\n===================================="
        );

        System.out.println(
                "        🏆 CAMPEÕES DA TEMPORADA"
        );

        System.out.println(
                "===================================="
        );

        System.out.println(
                "\n🏆 CAMPEÃO DE PILOTOS"
        );

        System.out.println(
                campeao.getNome()
        );

        System.out.println(
                "Pontos: " +
                        campeao.getPontos()
        );

        System.out.println(
                "Vitórias: " +
                        campeao.getVitorias()
        );

        System.out.println(
                "Poles: " +
                        campeao.getPoles()
        );

        System.out.println(
                "\n🏆 CAMPEÃO DE CONSTRUTORES"
        );

        System.out.println(
                equipeCampea.getNome()
        );

        System.out.println(
                "Pontos: " +
                        equipeCampea.getPontos()
        );
    }
}