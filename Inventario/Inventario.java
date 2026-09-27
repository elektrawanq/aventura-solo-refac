package Inventario;
import java.util.List;
import java.util.ArrayList;
import Item.*;

public class Inventario<T> {
    // Motivo de instanciar como List:  o código fica focado no comportamento genérico 
    // da lista e não em uma estrutura específica
    private List<T> inventario = new ArrayList<>();

    public int tamanho() { return inventario.size(); }

    public void adicionarItem(T t) {
        inventario.add(t);
    }

    public void removerItem(T t) {
        inventario.remove(t);
    }

    public boolean buscarItem(T t) { return inventario.contains(t); }

    public T buscarPorNome(String nome) throws ItemNaoEncontradoException {
        for(T item : inventario){
            if (item instanceof Item i && i.nome().equals(nome)){
                return item;
            }
        } 
        throw new ItemNaoEncontradoException("Item não encontrado: " + nome);
    }

    public List<T> listar() { return inventario; }
}
