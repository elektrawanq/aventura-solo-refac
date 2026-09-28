package Personagem;

import Inimigo.Inimigo;

public class Arqueiro extends Personagem {
    public Arqueiro(String nome) {
        super(nome);
    }

    public void atacar(Inimigo inimigo) {
        inimigo.receberDano(20);
        System.out.println("O arqueiro " + nome() + " mira com calma e acerta um Tiro Preciso!");
        System.out.println("A vitalidade de " + inimigo.nome() + " é drenada em 20 pontos.");
    }
}
