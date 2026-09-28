# Atividade 3 – Aventura Solo refac

## Aventura Solo Refac - Link do Projeto no  Github

[aventura-solo-refac](<https://github.com/elektrawanq/aventura-solo-refac.git>)

## Equipe

- Bruno Ferreira da Silva
  - email: <brenoouhd@gmail.com>
- Gabriel Brandão Vilar
  - email: <gabrielbrandaovilar@gmail.com>
- Letícia Queiroz Wanderley
  - email: <leticia.queiroz.109@ufrn.edu.br>

## Como jogar

1. Digite o nome do herói e escolha a classe (Guerreiro, Mago, Arqueiro ou Aventureiro).
2. Em cada batalha, escolha uma opção digitando o número:
   - `1` - Atacar
   - `2` - Usar item (digite o nome exato do item, por exemplo `Poção de Cura`)
   - `3` - Ver inventário
   - `4` - Ver status
3. Derrote todos os inimigos para concluir as missões e vencer. Se a vida do herói chegar a zero, o jogo termina em derrota.

## Instruções de Compilação e de Execução

Execute os comandos na pasta raiz do projeto.

```bash
# Compilação
mkdir -p out
javac -encoding UTF-8 -d out $(find . -name "*.java")

# Execução
java -cp out JogoAventura.JogoAventura
```

## Sua tarefa

A versão apresentada possui campos públicos, tipos representados por String, classes excessivamente genéricas, acesso direto ao estado dos objetos e pouca utilização dos recursos oferecidos pela orientação a objetos.

Você deverá modificar esse projeto progressivamente.

- [ ] Encapsulamento e proteção do estado – 0,45 ponto. Todos os atributos das classes deverão deixar de ser públicos. Utilize modificadores de acesso adequados e crie apenas os métodos necessários para consultar ou modificar o estado dos objetos. A vida do personagem e dos inimigos não poderá ser alterada diretamente por outras classes. Crie métodos como receberDano(), curar() e outros que considerar necessários. A vida nunca poderá assumir valor negativo. O ouro também não deverá ser alterado diretamente.

- [ ] Relacionamentos entre objetos e enum – 0,45 ponto. Organize os relacionamentos entre Personagem, Item, Missao e Inimigo. O personagem deverá possuir seu próprio inventário. A missão deverá possuir um inimigo como alvo e uma recompensa. Substitua o boolean concluida por um enum chamado EstadoMissao, contendo pelo menos PENDENTE e CONCLUIDA. Não utilize String para representar estados do sistema quando um enum for mais adequado.

- [ ] Herança e polimorfismo – 0,55 ponto. Transforme Inimigo em uma classe abstrata. Crie pelo menos duas subclasses: Goblin e Esqueleto. Cada inimigo deverá possuir sua própria implementação de ataque. O programa principal deverá trabalhar com referências do tipo Inimigo, sem precisar perguntar se o inimigo é um Goblin ou um Esqueleto. Evite estruturas como: **FEITO**

if (inimigo.tipo.equals("GOBLIN")) { // ... } else if (inimigo.tipo.equals("ESQUELETO")) { // ... }
A diferença de comportamento deverá ser resolvida utilizando sobrescrita de métodos e polimorfismo.

- [ ] Classes abstratas e interfaces – 0,45 ponto. Transforme Item em uma classe abstrata e crie pelo menos Arma e Pocao. Crie também uma interface chamada Usavel, contendo um método semelhante a:

void usar(Personagem personagem);

A poção deverá implementar essa interface e recuperar pontos de vida do personagem. Pense se todos os itens precisam implementar Usavel ou apenas aqueles que realmente podem ser utilizados diretamente.

- [ ] Exceções e gerenciamento de recursos – 0,40 ponto. Crie pelo menos uma exceção personalizada relacionada às regras do jogo. Um exemplo seria ItemNaoEncontradoException, lançada quando o jogador tentar utilizar um item que não existe no inventário. Essa situação deverá ser tratada com try e catch. O programa também deverá tratar pelo menos uma exceção não verificada, como uma entrada inválida digitada pelo jogador. Caso utilize Scanner, utilize-o com try-with-resources.

- [x] Collections, generics, equals e hashCode – 0,50 ponto. Crie uma classe genérica de inventário:

`public class Inventario<T> { // implementação }`

Ela deverá utilizar internamente uma coleção adequada e possuir operações para **adicionar, remover, procurar e listar elementos. FEITO** O personagem deverá utilizar algo semelhante a:

`Inventario<Item>`

O jogo deverá utilizar pelo menos mais uma estrutura do Collections Framework, como List, Set ou Map, para organizar inimigos, missões ou outras entidades. Implemente equals() e hashCode() em pelo menos uma entidade na qual a comparação lógica entre objetos seja necessária.

- [ ] Integração do jogo – 0,20 ponto. Depois das refatorações, implemente uma pequena aventura jogável no terminal. O jogador deverá conseguir visualizar seu estado, enfrentar inimigos e utilizar pelo menos uma poção. O jogo deverá possuir pelo menos um Goblin e um Esqueleto. Quando todos os objetivos forem concluídos, deverá ser apresentada uma mensagem de vitória. Caso a vida do personagem chegue a zero, deverá ser apresentada uma mensagem de derrota.

## Critérios de avaliação

| Critério                                 | Valor  |
| ---------------------------------------- |  :---: |
| Encapsulamento e proteção do estado      |  0,45  |
| Relacionamentos e enum                   |  0,45  |
| Herança e polimorfismo                   |  0,55  |
| Classes abstratas e interfaces           |  0,45  |
| Exceções e gerenciamento de recursos     |  0,40  |
| Collections, generics, equals e hashCode |  0,50  |
| Funcionamento e integração final         |  0,20  |
| Total                                    |  3,00  |
