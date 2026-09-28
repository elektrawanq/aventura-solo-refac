package JogoAventura;
import Inimigo.Inimigo;

import java.util.Scanner;

import Inimigo.Goblin;
import Item.*;
import Missao.*;
import Personagem.Personagem;

public class JogoAventura {
    public static void main(String[] args) {
        Personagem heroi = new Personagem("Aventureiro");
        // TODO Pessoa 3: criar lista/estrutura com os inimigos da aventura (Goblin, Esqueleto, etc.)
        // TODO Pessoa 3: criar lista/estrutura com as missões da aventura
        boolean jogoRodando = true;

        Item pocao = new Pocao("Poção de Cura", "POCAO", 20);
        Inimigo goblin = new Goblin("Goblin da Floresta", 30, 5);
        Missao missao = new Missao("Problemas na Floresta", "Derrote o Goblin da Floresta", 25);
        try(Scanner scanner = new Scanner(System.in)){
            while(jogoRodando){
                System.out.println("1 - Atacar");
                System.out.println("2 - Usar item");
                System.out.println("3 - Ver inventário");
                System.out.println("4 - Ver status");
                System.out.print("Escolha: ");

                int opcao;
                try{
                    opcao = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e){
                    System.out.println("Entrada inválida, por favor, digite um número de 1 a 4.");
                    continue;
                }

                switch (opcao) {
                    case 1:
                        // TODO Pessoa 3: lógica de ataque contra o inimigo atual      
                        break;    
                    case 2:
                         System.out.print("Nome do item: ");
                         String nomeItem = scanner.nextLine().trim();
                         try{
                            Item item = heroi.inventario().buscarPorNome(nomeItem);
                            if(item instanceof Usavel usavel) {
                                usavel.usar(heroi);
                            }
                            else{
                                System.out.print("Esse objeto não pode ser usado");
                            }
                         } catch(ItemNaoEncontradoException e){
                            System.out.println("Erro: " + e.getMessage());
                         }
                         break;
                    case 3:
                        if(heroi.inventario().tamanho() == 0){
                            System.out.println("Inventário vazio.");
                        }
                        else{
                            for(Item i : heroi.inventario().listar()){
                                System.out.println("- " + i.nome());
                            }
                        }
                        break;
                    case 4:
                        heroi.mostrarStatus();
                        break;
                    default:
                        System.out.println("Por favor, escolha uma das opções de 1 a 4");
                }
            }
        }
    }

}
