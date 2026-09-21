package Inimigo;

import Personagem.Personagem;

// Esqueleto – possui maior resistência e causa um tipo diferente de ataque.

public class Esqueleto extends Inimigo {
    public Esqueleto(String nome, int vida, int dano) {
        super(nome, vida, dano);
    }

    @Override
    public void atacar(Personagem personagem) {
        personagem.receberDano(2 * dano());
        System.out.println(nome() + " atacou " + personagem.nome() + " causando " + 2 * dano() + " de dano.");
    }

    @Override
    public void receberDano(int dano) {
        if (dano > 1)
            super.receberDano(dano / 2);
        else
            super.receberDano(dano);
    }
}