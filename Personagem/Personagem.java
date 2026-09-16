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
    }
}