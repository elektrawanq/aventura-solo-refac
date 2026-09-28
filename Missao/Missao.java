package Missao;

import java.util.List;
import java.util.ArrayList;
import Inimigo.Inimigo;
import Item.Item;
import Personagem.Personagem;

public class Missao {
    private final String titulo;
    private final String descricao;
    private List<Inimigo> alvo = new ArrayList<>();
    private List<Personagem> listaHerois = new ArrayList<>();
    private List<Item> recompensa = new ArrayList<>();
    private int ouroRecompensa;
    private EstadoMissao estadoMissao;

    public Missao(String titulo, String descricao, List<Inimigo> alvo, List<Personagem> listaHerois,
            List<Item> recompensa, int ouroRecompensa) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.alvo = alvo;
        this.listaHerois = listaHerois;
        this.recompensa = recompensa;
        this.ouroRecompensa = ouroRecompensa;
        this.estadoMissao = EstadoMissao.PENDENTE;
    }

    public String titulo() {
        return titulo;
    }

    public String descricao() {
        return descricao;
    }

    public List<Inimigo> alvo() {
        return alvo;
    }

    public List<Personagem> listaHerois() {
        return listaHerois;
    }

    public List<Item> recompensa() {
        return recompensa;
    }

    public int ouroRecompensa() {
        return ouroRecompensa;
    }

    public EstadoMissao estadoMissao() {
        return estadoMissao;
    }

    public void imprimirTelaDeConclusao() {
        System.out.println("Missão concluída: " + titulo);
        System.out.println("Ouro concedido: " + ouroRecompensa);
        System.out.println("Recompensa: ");
        if (recompensa.isEmpty()) {
            System.out.println("(vazio)");
            return;
        }
        int tamanhoNome = "Nome".length() + 1;
        int tamanhoTipo = "Tipo".length() + 1;
        int tamanhoValor = "Valor".length() + 1;
        for (Item item : recompensa) {
            if (item.nome().length() > tamanhoNome)
                tamanhoNome = item.nome().length() + 1;
            if (item.getClass().getSimpleName().length() > tamanhoTipo)
                tamanhoTipo = item.getClass().getSimpleName().length() + 1;
            String itemValor = String.valueOf(item.valor());
            if (itemValor.length() > tamanhoValor)
                tamanhoValor = itemValor.length() + 1;
        }
        String cabecalho = "Nome" + " ".repeat(tamanhoNome - 4) + "Tipo" + " ".repeat(tamanhoTipo - 4) + "Valor"
                + " ".repeat(tamanhoValor - 5);
        System.out.println(cabecalho);
        for (Item item : recompensa) {
            System.out.println(
                    item.nome() + " ".repeat(tamanhoNome - item.nome().length()) + item.getClass().getSimpleName()
                            + " ".repeat(tamanhoTipo - item.getClass().getSimpleName().length()) + item.valor());
        }
        for (Personagem personagem : listaHerois) {
            personagem.mostrarStatus();
        }
    }

    public boolean verificarConclusao() {
        if (estadoMissao != EstadoMissao.EM_ANDAMENTO)
            return false;
        for (Inimigo inimigo : alvo) {
            if (inimigo.estaVivo())
                return false;
        }
        return true;
    }

    public void iniciarMissao() {
        estadoMissao = EstadoMissao.EM_ANDAMENTO;
    }

    public void concluir() {
        if (verificarConclusao()) {
            estadoMissao = EstadoMissao.CONCLUIDA;
            for (Personagem personagem : listaHerois) {
                personagem.adicionarOuro(ouroRecompensa);
            }
            for (Item item : recompensa) {
                for (Personagem personagem : listaHerois) {
                    personagem.adicionarItem(item);
                }
            }
        }
    }

    public void falhou() {
        estadoMissao = EstadoMissao.FALHOU;
        System.out.println("A missão " + titulo + " falhou.");
        System.out.println("O alvo sobreviveu e derrotou os heróis.");
        for (Personagem personagem : listaHerois) {
            personagem.mostrarStatus();
        }
        for (Inimigo inimigo : alvo) {
            inimigo.mostrarStatus();
        }
    }
}