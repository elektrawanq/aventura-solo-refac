package Inimigo;

public class Esqueleto extends Inimigo {
    public Esqueleto(String nome, String tipo, int vida, int dano) {
        super(nome, tipo, vida, dano);
    }

    @Override
    public void usar() {}
}