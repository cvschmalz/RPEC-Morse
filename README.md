# RPEC-Morse

Disciplina: Resolução de Problemas Estruturados na Computação

Professor: Andrey Cabral Meira

Alunos: Cecília Lucchesi Mardegan, Christine von Schmalz, Erick Maestri de Souza

## Explicação do código

Esse projeto implementa uma árvore binária para fazer a conversão entre textos simples e código morse. Cada nó da árvore representa um símbolo do código morse, e um caminho percorrido pela árvore representa um caractere. Nós à esquerda representam pontos (.), enquanto nós à direita representam traços (-).

A árvore contém letras do alfabeto de A até Z e números de 0 a 9.


## Node.java

Classe que define um nó na árvore binária.

- `caractere`: Variável String que armazena o caracter correspondente ao nó, se existir.
- `esquerdo` e `direito`: Variáveis Node que apontam para os filhos esquerdo e direito do nó.

## ArvoreBinariaMorse.java

Classe que contém toda a estrutura da árvore binária, e o código principal do programa. Contém os métodos responsáveis por popular, buscar, inserir, remover e exibir os dados na árvore, além da função `main()` com o menu interativo.

### Método `inicializar`

Cria a raiz da árvore como um nó vazio, preparando a estrutura para receber os caracteres.

### Método `inserir`

Insere um novo caractere na árvore seguindo o código morse informado. Cada ponto (.) leva ao filho esquerdo, e cada traço (-) leva ao filho direito. Se o caminho ainda não existir, novos nós são criados dinamicamente.

Ao final do caminho, o caractere é armazenado no nó correspondente.

### Método `buscarPorMorse`

Funciona de forma semelhante ao método `inserir`. Percorre a árvore com base no código morse recebido e retorna o texto correspondente, com cada ponto e traço sendo traduzido como um movimento na árvore. Um espaço indica o fim de uma letra. Retorna um "?" para códigos inválidos ou desconhecidos. Permite a conversão de palavras completas.

### Método `buscarPorCaractere`

Recursivamente percorre a árvore até encontrar o caractere desejado, e monta o código morse correspondente. Caso o caractere não exista, retorna "?".

### Método `removerPorMorse`

Navega pela árvore seguindo o código morse especificado e remove o caractere associado ao nó final, simplesmente removendo o atributo `caractere` do nó, assim mantendo a estrutura da árvore intacta.

### Método `removerPorCaractere`

Busca o nó que contém o caractere informado e o remove, limpando apenas o valor armazenado.

### Método `exibirArvore`

Imprime a árvore binária no console de forma hierárquica, mostrando o caminho percorrido até cada nó e o caractere armazenado ou ( ) caso o nó esteja vazio.

### Método `popularArvore`

Preenche a árvore binária com todos os caracteres padrão do alfabeto morse (letras de A a Z e dígitos de 0 a 9). Essa configuração inicial permite a tradução completa de textos simples.

### Método `main`

Contém o loop principal do programa, com um menu de opções que permite ao usuário interagir com a árvore. As opções são:

- Exibir árvore

- Buscar por caractere

- Buscar por código morse

- Inserir caractere

- Remover por caractere

- Remover por código morse

- Sair

As entradas são lidas com a classe Scanner, e o usuário pode testar livremente a inserção, remoção e conversão entre código morse e texto.
