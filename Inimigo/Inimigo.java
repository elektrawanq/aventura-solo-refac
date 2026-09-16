package Inimigo;

import Personagem.Personagem;

public abstract class Inimigo {
    private final String nome;
    private final String tipo;
    private int vida;
    private int dano;

    public Inimigo(String nome, String tipo, int vida, int dano) {
        this.nome = nome;
        this.tipo = tipo;
        this.vida = vida;
        this.dano = dano;
    }

    public String nome() { return nome; }
    public String tipo() { return tipo; }
    public int vida() { return vida; }
    public int dano() { return dano; }

    public void atacar(Personagem personagem) {
        personagem.receberDano(dano);
        System.out.println(nome + " atacou " + personagem.nome() + " causando " + dano + " de dano.");
    }

    public void receberCura(int cura) {
        vida += cura;
    }

    public void receberDano(int dano) {
        vida -= dano;
        // A vida não pode receber valor negativo
        if(vida < 0) vida = 0;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void mostrarStatus() {
        System.out.println("Inimigo: " + nome);
        System.out.println("Tipo: " + tipo);
        System.out.println("Vida: " + vida);
        System.out.println("Dano: " + dano);
    }

    public abstract void usar();
}