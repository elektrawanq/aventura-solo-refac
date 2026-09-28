package Item;

import Personagem.Personagem;

public class Alimento extends Item implements Usavel {
    public Alimento(String nome, int valor) {
        super(nome, valor);
    }

    @Override 
    public void usar(Personagem personagem) {
        personagem.receberCura(10);
        personagem.removerItem(this);
    }
}
