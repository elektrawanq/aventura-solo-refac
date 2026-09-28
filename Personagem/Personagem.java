package Personagem;

import java.util.List;
import Inimigo.Inimigo;
import Inventario.Inventario;
import Item.Item;

public class Personagem {
    private final String nome;
    private int vida;
    private int ouro;
    private Inventario<Item> inventario = new Inventario<>();

    public Personagem(String nome) {
        this.nome = nome;
        this.vida = 100;
        this.ouro = 0;
        // O personagem inicia o jogo com o inventário vazio
    }

    public String nome() {
        return nome;
    }

    public int vida() {
        return vida;
    }

    public int ouro() {
        return ouro;
    }

    public Inventario<Item> inventario() {
        return inventario;
    }

    public void atacar(Inimigo inimigo) {
        inimigo.receberDano(10);
        System.out.println(nome + " atacou " + inimigo.nome() + " causando 10 de dano.");
    }

    public void receberCura(int cura) {
        vida += cura;
        System.out.println(nome + " recebeu " + cura + " de cura.");
    }

    public void receberDano(int dano) {
        vida -= dano;
        // A vida não pode receber valor negativo
        if (vida < 0)
            vida = 0;
    }

    public void adicionarOuro(int ouro) {
        this.ouro += ouro;
    }

    public void removerOuro(int ouro) {
        this.ouro -= ouro;
    }

    public void mostrarStatus() {
        System.out.println("Personagem: " + nome);
        System.out.println("Vida: " + vida);
        System.out.println("Ouro: " + ouro);
        System.out.println("Itens: " + inventario.tamanho());

        // Impressão da tabela do inventário
        System.out.println("Inventário:");
        List<Item> listaDeItens = inventario.listar();
        if (listaDeItens.isEmpty()) {
            System.out.println("(vazio)");
            return;
        }
        int tamanhoNome = "Nome".length() + 1;
        int tamanhoTipo = "Tipo".length() + 1;
        int tamanhoValor = "Valor".length() + 1;
        for (Item item : listaDeItens) {
            if (item.nome().length() > tamanhoNome)
                tamanhoNome = item.nome().length() + 1;
            if (item.getClass().toString().length() > tamanhoTipo)
                tamanhoTipo = item.getClass().toString().length() + 1;
            String itemValor = String.valueOf(item.valor());
            if (itemValor.length() > tamanhoValor)
                tamanhoValor = itemValor.length() + 1;
        }
        String cabecalho = "Nome" + " ".repeat(tamanhoNome - 4) + "Tipo" + " ".repeat(tamanhoTipo - 4) + "Valor"
                + " ".repeat(tamanhoValor - 5);
        System.out.println(cabecalho);
        for (Item item : listaDeItens) {
            System.out.println(item.nome() + " ".repeat(tamanhoNome - item.nome().length()) + item.getClass()
                    + " ".repeat(tamanhoTipo - item.getClass().toString().length()) + item.valor());
        }
    }
}