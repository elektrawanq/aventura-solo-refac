package JogoAventura;
import Inimigo.Inimigo;
import Inimigo.Goblin;
import Item.*;
import Missao.*;
import Personagem.Personagem;

public class JogoAventura {
    public static void main(String[] args) {
        Personagem heroi = new Personagem("Aventureiro");
        Item pocao = new Pocao("Poção de Cura", "POCAO", 20);
        Inimigo goblin = new Goblin("Goblin da Floresta", "GOBLIN", 30, 5);
        Missao missao = new Missao("Problemas na Floresta", "Derrote o Goblin da Floresta", goblin, pocao, 25);
        while (goblin.estaVivo() && heroi.vida() > 0) {
            heroi.atacar(goblin);
            if (goblin.estaVivo()) {
                goblin.atacar(heroi);
            }
        }
        if (heroi.vida() > 0) {
            missao.concluir(heroi);
        }
        heroi.mostrarStatus();
    }
}
