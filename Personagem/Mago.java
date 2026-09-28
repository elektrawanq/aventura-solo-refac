package Personagem;

import Inimigo.Inimigo;

public class Mago extends Personagem {
    public Mago(String nome) {
        super(nome);
    }

    @Override
    public void atacar(Inimigo inimigo) {
        inimigo.receberDano(20);
        System.out.println("O mago " + nome() + " invoca um Raio Arcano e eletrocuta o adversário.");
        System.out.println("A vitalidade de " + inimigo.nome() + " é drenada em 20 pontos.");
    }
}
