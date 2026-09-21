package Inimigo;

import Personagem.Personagem;

// Goblin – inimigo mais fraco, mas que pode realizar ataques rápidos.

public class Goblin extends Inimigo {
    public Goblin(String nome, int vida, int dano) {
        super(nome, vida, dano);
    }

    @Override
    public void atacar(Personagem personagem) {
        personagem.receberDano(dano());
        System.out.println(nome() + " atacou " + personagem.nome() + " causando " + dano() + " de dano.");
        int danoAplicado = dano();
        if (danoAplicado > 1)
            danoAplicado = dano() / 2;
        personagem.receberDano(danoAplicado);
        System.out.println(
                nome() + " atacou novamente " + personagem.nome() + " causando mais " + danoAplicado + " de dano.");
    }

    @Override
    public void receberDano(int dano) {
        super.receberDano(2 * dano);
    }
}