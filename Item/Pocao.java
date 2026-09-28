package Item;

import Personagem.Personagem;

public class Pocao extends Item implements Usavel {
    public Pocao(String nome, int valor) {
        super(nome, valor);
    }

    @Override
    public void usar(Personagem personagem) {
        super.diminuirValor(1, "Não há poção disponível para cura.");
        personagem.receberCura(15);
    }
}
