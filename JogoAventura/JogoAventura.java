package JogoAventura;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Inimigo.Esqueleto;
import Inimigo.Inimigo;
import Inimigo.Goblin;
import Item.*;
import Missao.*;
import Personagem.Personagem;

public class JogoAventura {

    public static void main(String[] args) {
        Personagem heroi = new Personagem("Violet");

        Inimigo goblin = new Goblin("Goblin da Floresta", 30, 5);
        Inimigo esqueleto = new Esqueleto("Esqueleto do Deserto", 40, 4);

        List<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(goblin);
        inimigos.add(esqueleto);

        List<Missao> missoes = new ArrayList<>();
        missoes.add(new Missao("Problemas na Floresta", "Derrote o Goblin da Floresta",
                goblin, new Pocao("Poção de Cura", 1), 25));
        missoes.add(new Missao("Ameaça no Deserto", "Derrote o Esqueleto do Deserto",
                esqueleto, new Pocao("Poção de Cura", 1), 35));

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("=== AVENTURA SOLO ===");

            for (Inimigo inimigo : inimigos) {
                if (heroi.vida() <= 0) break;
                System.out.println("\nVocê encontrou: " + inimigo.nome());

                while (inimigo.estaVivo() && heroi.vida() > 0) {
                    int opcao = lerOpcao(sc);
                    switch (opcao) {
                        case 1 -> {
                            heroi.atacar(inimigo);
                            if (inimigo.estaVivo()) inimigo.atacar(heroi);
                        }
                        case 2 -> {
                            usarItem(sc, heroi);
                            if (inimigo.estaVivo()) inimigo.atacar(heroi);
                        }
                        case 3 -> mostrarInventario(heroi);
                        case 4 -> {
                            heroi.mostrarStatus();
                            System.out.println("Vida de " + inimigo.nome() + ": " + inimigo.vida());
                        }
                        default -> System.out.println("Opção inválida.");
                    }
                }

                if (!inimigo.estaVivo()) {
                    System.out.println(inimigo.nome() + " foi derrotado.");
                    for (Missao m : missoes) m.concluir(heroi);
                }
            }

            if (heroi.vida() > 0) {
                int derrotados = 0;
                for (Inimigo i : inimigos) if (!i.estaVivo()) derrotados++;
                int concluidas = 0;
                for (Missao m : missoes) if (m.estadoMissao() == EstadoMissao.CONCLUIDA) concluidas++;

                System.out.println("\n==============================");
                System.out.println("      AVENTURA CONCLUÍDA");
                System.out.println("==============================");
                System.out.println("Inimigos derrotados: " + derrotados);
                System.out.println("Missões concluídas: " + concluidas);
                System.out.println("Ouro obtido: " + heroi.ouro());
                System.out.println("Parabéns, aventureiro(a)!");
            } else {
                for (Missao m : missoes) m.falhou();
                System.out.println("\n=== DERROTA === " + heroi.nome() + " não tankou.");
            }
        }
    }

    private static int lerOpcao(Scanner sc) {
        System.out.println("\n1 - Atacar");
        System.out.println("2 - Usar item");
        System.out.println("3 - Ver inventário");
        System.out.println("4 - Ver status");
        System.out.print("Escolha: ");
        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            sc.nextLine();
            return -1;
        }
    }

    private static void usarItem(Scanner sc, Personagem heroi) {
        for (Item item : heroi.inventario().listar()) {
            if (item instanceof Usavel usavel) {
                usavel.usar(heroi);
                heroi.inventario().removerItem(item);
                return;
            }
        }
        System.out.println("Você não tem nenhum item para usar.");
    }

    private static void mostrarInventario(Personagem heroi) {
        List<Item> itens = heroi.inventario().listar();
        if (itens.isEmpty()) {
            System.out.println("Inventário vazio.");
            return;
        }
        for (Item item : itens) {
            System.out.println("- " + item.nome() + " (valor " + item.valor() + ")");
        }
    }
}
