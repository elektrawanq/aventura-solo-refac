package Missao;

import Inimigo.Inimigo;
import Item.Item;
import Personagem.Personagem;

public class Missao {
    private final String titulo;
    private final String descricao;
    private Inimigo alvo;
    private Item recompensa;
    private int ouroRecompensa;
    private EstadoMissao estadoMissao;

    public Missao(String titulo, String descricao, Inimigo alvo, Item recompensa, int ouroRecompensa) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.alvo = alvo;
        this.recompensa = recompensa;
        this.ouroRecompensa = ouroRecompensa;
        this.estadoMissao = EstadoMissao.PENDENTE;
    }

    public String titulo() { return titulo; }
    public String descricao() { return descricao; }
    public Inimigo alvo() { return alvo; }
    public Item recompensa() { return recompensa; }
    public int ouroRecompensa() { return ouroRecompensa; }
    public EstadoMissao estadoMissao() { return estadoMissao; }

    public void concluir(Personagem personagem) {
        if (estadoMissao != EstadoMissao.PENDENTE) return;
        if (alvo.estaVivo()) return;

        estadoMissao = EstadoMissao.CONCLUIDA;
        personagem.adicionarOuro(ouroRecompensa);
        personagem.inventario().adicionarItem(recompensa);
        System.out.println("Missão concluída: " + titulo);
        System.out.println("Recompensa: " + ouroRecompensa + " moedas");
        System.out.println("            " + recompensa.nome());
    }

    public void falhou() {
        if (estadoMissao != EstadoMissao.PENDENTE) return;
        estadoMissao = EstadoMissao.FALHOU;
        System.out.println("A missão " + titulo + " falhou.");
        alvo.mostrarStatus();
    }
}