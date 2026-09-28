package Personagem;

import Inimigo.Inimigo;

public class Guerreiro extends Personagem {
    public Guerreiro(String nome) {
        super(nome);
    }

    @Override
    public void atacar(Inimigo inimigo) {
        inimigo.receberDano(15);
        System.out.println("O guerreiro " + nome() + " reúne toda sua força e executa um Golpe Devastador!");
        System.out.println("A vitalidade de " + inimigo.nome() + " é drenada em 15 pontos.");
    }
}
