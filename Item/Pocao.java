package Item;

import Personagem.Personagem;

public class Pocao extends Item implements Usavel {
    public Pocao(String nome, int valor) {
        super(nome, valor);
    }

    @Override
    public void usar(Personagem personagem) {
        personagem.receberCura(15);
        personagem.removerItem(this);
    }
}
