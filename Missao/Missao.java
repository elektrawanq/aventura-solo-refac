package Missao;
import Inimigo.Inimigo;
import Item.Item;
import Personagem.Personagem;

public class Missao {
    private final String titulo;
    private final String descricao;
    private boolean concluida;
    private EstadoMissao estadoMissao;
    private Inimigo alvo;
    private Item recompensa;
    private int ouroRecompensa;

    public Missao(String titulo, String descricao, Inimigo alvo, Item recompensa, int ouroRecompensa) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.alvo = alvo;
        this.recompensa = recompensa;
        this.ouroRecompensa = ouroRecompensa;
    }

    public String titulo() { return titulo; }
    public String descricao() { return descricao; }
    public boolean foiConcluida() { return concluida; }
    public Inimigo alvo() { return alvo; }
    public Item recompensa() { return recompensa; }
    public int ouroRecompensa() { return ouroRecompensa; }

    public void concluir(Personagem personagem) {
        concluida = true;
        personagem.adicionarOuro(ouroRecompensa);
        personagem.adicionarItem(recompensa);
        System.out.println("Missão concluída: " + titulo);
    }

    public void falhou(Personagem personagem) {
        concluida = false;
        System.out.println("A missão " + titulo + " falhou.");
    }
}