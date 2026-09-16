package Item;
public abstract class Item {
    private final String nome;
    private final String tipo;
    private int valor;

    public Item(String nome, String tipo, int valor) {
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }

    public String nome() { return nome; }
    public String tipo() { return tipo; }
    public int valor() { return valor; }

    // Método abstrato que força cada subclasse a ter sua própria lógica de uso
    public abstract void usar();
}