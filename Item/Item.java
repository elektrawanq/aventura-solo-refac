package Item;

import java.util.Objects;

public abstract class Item {
    private final String nome;
    private final String tipo;
    private int valor;

    public Item(String nome, String tipo, int valor) {
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }

    public String nome() {
        return nome;
    }

    public String tipo() {
        return tipo;
    }

    public int valor() {
        return valor;
    }

    // Método abstrato que força cada subclasse a ter sua própria lógica de uso
    public abstract void usar();

    // Implementação de equals() para inventario.contains() em
    // Inventario.buscarItem()

    @Override // sobrescrita de equals() na classe Object
    public boolean equals(Object obj) {
        if (this == obj)
            return true; // '==' compara referências de memória

        if (obj == null || getClass() != obj.getClass()) // se são da mesma classe concreta
            return false;

        Item other = (Item) obj; // permite acessar os atributos de obj enquanto objeto de classe Item

        return Objects.equals(nome, other.nome) && // Objects.equals pois se trata de String, evita NullPointerException
                Objects.equals(tipo, other.tipo) &&
                valor == other.valor; // comparação de tipos primitivos
    }

    @Override // sobrescrita de hashCode() na classe Object, produz um código compatível com a
              // definição de equals()
    public int hashCode() {
        return Objects.hash(nome, tipo, valor);
    }
}