package Personagem;
import java.util.ArrayList;
import Inimigo.Inimigo;
import Item.Item;

public class Personagem {
    private final String nome;
    private int vida;
    private int ouro;
    private ArrayList<Item> inventario = new ArrayList<>();

    public Personagem(String nome) {
        this.nome = nome;
        this.vida = 100;
        this.ouro = 0;
        // O personagem inicia o jogo com o inventário vazio
    }

    public String nome() { return nome; }
    public int vida() { return vida; }
    public int ouro() { return ouro; }
    public ArrayList<Item> inventario() { return inventario; }

    public void atacar(Inimigo inimigo) {
        inimigo.receberDano(10);
        System.out.println(nome + " atacou " + inimigo.nome() + " causando 10 de dano.");
    }

    public void receberCura(int cura) {
        vida += cura;
    }

    public void receberDano(int dano) {
        vida -= dano;
        // A vida não pode receber valor negativo
        if(vida < 0) vida = 0;
    }

    public void adicionarOuro(int ouro) {
        this.ouro += ouro;
    }

    public void removerOuro(int ouro) {
        this.ouro -= ouro;
    }

    public void adicionarItem(Item item) {
        inventario.add(item);
    }

    public void removerItem(Item item) {
        inventario.remove(item);
    }

    public void mostrarStatus() {
        System.out.println("Personagem: " + nome);
        System.out.println("Vida: " + vida);
        System.out.println("Ouro: " + ouro);
        System.out.println("Itens: " + inventario.size());
        
        // Impressão da tabela do inventário
        System.out.println("Inventário:");
        if(inventario.isEmpty()) {
            System.out.println("(vazio)");
            return;
        }
        int tamanhoNome = "Nome".length() + 1;
        int tamanhoTipo = "Tipo".length() + 1;
        int tamanhoValor = "Valor".length() + 1;
        for(Item item : inventario) {
            if(item.nome().length() > tamanhoNome)
                tamanhoNome = item.nome().length() + 1;
            if(item.tipo().length() > tamanhoTipo)
                tamanhoTipo = item.tipo().length() + 1;
            String itemValor = String.valueOf(item.valor());
            if(itemValor.length() > tamanhoValor)
                tamanhoValor = itemValor.length() + 1;
        }
        String cabecalho = "Nome" + " ".repeat(tamanhoNome - 4) + "Tipo" + " ".repeat(tamanhoTipo - 4) + "Valor" + " ".repeat(tamanhoValor - 5);
        System.out.println(cabecalho);
        for(Item item : inventario) {
            System.out.println(item.nome() + " ".repeat(tamanhoNome - item.nome().length()) + item.tipo() + " ".repeat(tamanhoTipo - item.tipo().length()) + item.valor());
        }
    }
}