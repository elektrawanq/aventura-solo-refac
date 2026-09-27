package Item;

import Personagem.Personagem;

public interface Usavel { //alterei para público para conseguir utilizar no jovoAventura

    void usar(Personagem personagem); 
    // A poção deverá implementar essa interface e recuperar pontos de vida do
    // personagem. Pense se todos os itens precisam implementar Usavel ou apenas
    // aqueles que realmente podem ser utilizados diretamente.
}