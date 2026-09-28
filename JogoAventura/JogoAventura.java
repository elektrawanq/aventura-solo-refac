package JogoAventura;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

import Inimigo.*;
import Item.*;
import Item.ItemNaoEncontradoException;
import Missao.*;
import Personagem.*;

public class JogoAventura {

    public static void main(String[] args) {
        // try-with-resources: o Scanner é fechado automaticamente
        try (Scanner scanner = new Scanner(System.in)) {
            jogar(scanner);
        } catch (NoSuchElementException e) {
            // Entrada encerrada (Ctrl+D / Ctrl+Z)
            System.out.println("\nEntrada encerrada. Até a próxima, aventureiro!");
        }
    }

    private static void jogar(Scanner scanner) {
        System.out.println("=== AVENTURA SOLO ===");
        Personagem heroi = criarHeroi(scanner);
        heroi.adicionarItem(new Pocao("Poção de Cura", 20));

        // Referências do tipo Inimigo: o jogo não sabe (nem precisa saber) qual é a subclasse
        Inimigo goblin = new Goblin("Goblin da Floresta", 30, 5);
        Inimigo esqueleto = new Esqueleto("Esqueleto da Cripta", 40, 5);

        List<Personagem> herois = List.of(heroi);
        List<Missao> missoes = new ArrayList<>();
        missoes.add(new Missao(
                "Problemas na Floresta",
                "Derrote o Goblin da Floresta",
                List.of(goblin),
                herois,
                List.of(new Pocao("Poção de Cura", 20)),
                25));
        missoes.add(new Missao(
                "A Cripta Esquecida",
                "Derrote o Esqueleto da Cripta",
                List.of(esqueleto),
                herois,
                List.of(new Arma("Espada Enferrujada", 15)),
                35));

        // Set usa equals()/hashCode() de Inimigo
        Set<Inimigo> derrotados = new HashSet<>();
        int missoesConcluidas = 0;

        for (Missao missao : missoes) {
            missao.iniciarMissao();
            System.out.println("\n>>> Missão: " + missao.titulo() + " - " + missao.descricao());

            for (Inimigo inimigo : missao.alvo()) {
                boolean venceu = batalhar(scanner, heroi, inimigo);
                if (!venceu) {
                    missao.falhou();
                    System.out.println("\n============================== DERROTA ==============================");
                    System.out.println(heroi.nome() + " caiu em combate. Fim de jogo.");
                    return;
                }
                derrotados.add(inimigo);
            }

            missao.concluir();
            if (missao.estadoMissao() == EstadoMissao.CONCLUIDA) {
                System.out.println();
                missao.imprimirTelaDeConclusao();
                missoesConcluidas++;
            }
        }

        System.out.println("\n============================== AVENTURA CONCLUÍDA ==============================");
        System.out.println("Inimigos derrotados: " + derrotados.size());
        System.out.println("Missões concluídas: " + missoesConcluidas);
        System.out.println("Ouro obtido: " + heroi.ouro());
        System.out.println("Parabéns, " + heroi.nome() + "!");
    }

    /** Retorna true se o herói venceu a batalha, false se morreu. */
    private static boolean batalhar(Scanner scanner, Personagem heroi, Inimigo inimigo) {
        System.out.println("\nVocê encontrou: " + inimigo.nome());

        while (heroi.estaVivo() && inimigo.estaVivo()) {
            System.out.println("\n--- " + heroi.nome() + " | Vida: " + heroi.vida() + " | Ouro: " + heroi.ouro()
                    + " ---");
            System.out.println("Inimigo: " + inimigo.nome() + " | Vida: " + inimigo.vida());
            System.out.println("1 - Atacar");
            System.out.println("2 - Usar item");
            System.out.println("3 - Ver inventário");
            System.out.println("4 - Ver status");
            System.out.print("Escolha: ");

            int opcao = lerOpcao(scanner);
            boolean gastouTurno = false;

            switch (opcao) {
                case 1 -> {
                    heroi.atacar(inimigo);
                    gastouTurno = true;
                }
                case 2 -> gastouTurno = usarItem(scanner, heroi);
                case 3 -> listarInventario(heroi);
                case 4 -> heroi.mostrarStatus();
                case -1 -> { /* entrada inválida: mensagem já exibida */ }
                default -> System.out.println("Por favor, escolha uma das opções de 1 a 4.");
            }

            if (!gastouTurno) {
                continue;
            }

            if (!inimigo.estaVivo()) {
                System.out.println(inimigo.nome() + " foi derrotado.");
                return true;
            }

            // Polimorfismo: cada Inimigo ataca do seu jeito
            inimigo.atacar(heroi);
            System.out.println("Vida de " + heroi.nome() + ": " + heroi.vida());
            System.out.println("Vida de " + inimigo.nome() + ": " + inimigo.vida());
        }
        return heroi.estaVivo();
    }

    /** Retorna true se um item foi realmente usado (gasta o turno). */
    private static boolean usarItem(Scanner scanner, Personagem heroi) {
        if (heroi.inventario().tamanho() == 0) {
            System.out.println("Inventário vazio.");
            return false;
        }
        listarInventario(heroi);
        System.out.print("Nome do item: ");
        String nomeItem = scanner.nextLine().trim();
        try {
            Item item = heroi.inventario().buscarPorNome(nomeItem);
            if (item instanceof Usavel usavel) {
                usavel.usar(heroi);
                return true;
            }
            System.out.println("Esse objeto não pode ser usado.");
        } catch (ItemNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return false;
    }

    private static void listarInventario(Personagem heroi) {
        if (heroi.inventario().tamanho() == 0) {
            System.out.println("Inventário vazio.");
            return;
        }
        for (Item i : heroi.inventario().listar()) {
            System.out.println("- " + i.nome());
        }
    }

    /** Lê um número; devolve -1 se a entrada não for numérica. */
    private static int lerOpcao(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida, por favor, digite um número.");
            return -1;
        }
    }

    private static Personagem criarHeroi(Scanner scanner) {
        String nome = "";
        while (nome.isBlank()) {
            System.out.print("Nome do herói: ");
            nome = scanner.nextLine().trim();
        }

        while (true) {
            System.out.println("Escolha sua classe:");
            System.out.println("1 - Guerreiro");
            System.out.println("2 - Mago");
            System.out.println("3 - Arqueiro");
            System.out.println("4 - Aventureiro");
            System.out.print("Escolha: ");

            switch (lerOpcao(scanner)) {
                case 1 -> { return new Guerreiro(nome); }
                case 2 -> { return new Mago(nome); }
                case 3 -> { return new Arqueiro(nome); }
                case 4 -> { return new Personagem(nome); }
                case -1 -> { /* mensagem já exibida */ }
                default -> System.out.println("Escolha um número de 1 a 4.");
            }
        }
    }
}