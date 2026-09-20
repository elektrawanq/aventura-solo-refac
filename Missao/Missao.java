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
        if(estadoMissao == EstadoMissao.CONCLUIDA) {
            personagem.adicionarOuro(ouroRecompensa);
            personagem.adicionarItem(recompensa);
            System.out.println("Missão concluída: " + titulo);
            // deveria aparecer uma mensagem mostrando o ouro e a recompensa
            personagem.mostrarStatus();
        }
    }

    public void falhou(Personagem personagem) {
        if(estadoMissao == EstadoMissao.FALHOU) {
        System.out.println("A missão " + titulo + " falhou.");
        personagem.mostrarStatus();
        //inimigo.mostrarStatus();
        // deveria aparecer uma mensagem monstrando os itens remanescentes do personagem
        // ou algo sobre o inimigo
        }
    }
}