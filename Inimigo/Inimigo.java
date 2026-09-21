package Inimigo;

import java.util.Objects;
import Personagem.Personagem;

public abstract class Inimigo {
    private final String nome;
    private int vida;
    private int dano;

    public Inimigo(String nome, int vida, int dano) {
        this.nome = nome;
        this.vida = vida;
        this.dano = dano;
    }

    public String nome() {
        return nome;
    }

    public int vida() {
        return vida;
    }

    public int dano() {
        return dano;
    }

    public abstract void atacar(Personagem personagem);

    public void receberCura(int cura) {
        // não é necessário verificar "quem chamou" dentro do método.
        // A responsabilidade fica distribuída entre as classes, não por inspeção de
        // tipos.
        vida += cura;
        System.out.println(nome + " recebeu " + cura + " de cura.");
    }

    public void receberDano(int dano) {
        vida -= dano;
        // A vida não pode receber valor negativo
        if (vida < 0)
            vida = 0;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void mostrarStatus() {
        System.out.println("Inimigo: " + nome);
        System.out.println("Vida: " + vida);
        System.out.println("Dano: " + dano);
    }

    @Override // não sei se preciso disso
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Inimigo other = (Inimigo) obj;
        return Objects.equals(nome, other.nome) &&
                vida == other.vida &&
                dano == other.dano;
    }
}