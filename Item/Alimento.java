package Item;

public class Alimento extends Item {
    public Alimento(String nome, String tipo, int valor) {
        super(nome, tipo, valor);
    }

    @Override 
    public void usar() {}
}
