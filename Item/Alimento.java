package Item;

import Personagem.Personagem;

public class Alimento extends Item implements Usavel {
    public Alimento(String nome, int valor) {
        super(nome, valor);
    }

    public void usar(Personagem personagem) {
        super.diminuirValor(2, "Não há alimento disponível para aumentar a vitalidade.");
        personagem.receberCura(10);
    }
}
