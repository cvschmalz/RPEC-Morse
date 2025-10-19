import java.util.Scanner;

public class ArvoreBinariaMorse {

    Node raiz;

    public void inicializar(){
        raiz = new Node();
    }

    public void inserir(String codigo, String caractere){
        Node atual = raiz;
        for (int i = 0; i < codigo.length(); i++) {
            String digito = codigo.substring(i, i + 1);
            if (digito.equals(".")) {
                // esquerda
                if (atual.getEsquerdo() == null) atual.setEsquerdo(new Node());
                atual = atual.getEsquerdo();
            } else if (digito.equals("-")){
                // direita
                if (atual.getDireito() == null) atual.setDireito(new Node());
                atual = atual.getDireito();
            }
        }
        atual.setCaractere(caractere);
    }

    public String buscarPorMorse(String codigo) {
        String resultado = "";
        Node atual = raiz;

        for (int i = 0; i < codigo.length(); i++) {
            String digito = codigo.substring(i, i + 1);

            if (digito.equals(".")) {
                // esquerda
                if (atual.getEsquerdo() != null) atual = atual.getEsquerdo();
                else return "?"; // código inválido
            }
            else if (digito.equals("-")) {
                // direita
                if (atual.getDireito() != null) atual = atual.getDireito();
                else return "?";
            }
            else if (digito.equals(" ")) {
                // terminou uma letra
                if (atual.getCaractere() != null) resultado += atual.getCaractere();
                else resultado += "?";
                atual = raiz; // volta para o começo da árvore
            }
            else {
                System.out.println("Código inválido");
                return "?";
            }
        }

        // adiciona a última letra (caso não termine com espaço)
        if (atual != raiz) {
            if (atual.getCaractere() != null) resultado += atual.getCaractere();
            else resultado += "?";
        }

        return resultado;
    }

    public String buscarPorCaractere(String caractere) {
        String resultado = "";

        for (int i = 0; i < caractere.length(); i++){
            String caractere_parcial = caractere.substring(i, i + 1);
            // chama função recursiva que retorna código ou "" se não encontrado
            String resultado_parcial = buscarPorCaractereRec(raiz, caractere_parcial, "");
            if (resultado_parcial.equals("")) return "?";
            resultado = resultado + " " + resultado_parcial;
        }

        return resultado;
    }

    private String buscarPorCaractereRec(Node node, String alvo, String caminho) {
        if (node == null) return "";
        if (node.getCaractere() != null && node.getCaractere().equals(alvo)) {
            return caminho;
        }
        // esquerda
        String achado = buscarPorCaractereRec(node.getEsquerdo(), alvo, caminho + ".");
        if (!achado.equals("")) return achado;
        // direita
        achado = buscarPorCaractereRec(node.getDireito(), alvo, caminho + "-");
        return achado;
    }

    // percorre a árvore inteira para achar o caractere a ser removido
    public void removerPorCaractere(String caractere) {
        removerPorCaractereRec(raiz, caractere);
        raiz = removerNosVazios(raiz);  // remove todos os nós vazios que não tem filhos
    }

    private boolean removerPorCaractereRec(Node node, String alvo) {
        if (node == null) return false;

        // verificar se o alvo está em algum filho do nó (para poder remover o nó filho)

        // filho esquerdo:
        if (node.getEsquerdo() != null && alvo.equals(node.getEsquerdo().getCaractere())) {
            Node proximo = node.getEsquerdo();
            // verifica se o alvo não tem filhos antes de remover
            if (proximo.getEsquerdo() == null && proximo.getDireito() == null) {
                node.setEsquerdo(null);
            } else {
                proximo.setCaractere("");   // caso o nó tenha filhos, só remove o caracter
            }
            return true;
        }

        // filho direito
        if (node.getDireito() != null && alvo.equals(node.getDireito().getCaractere())) {
            Node proximo = node.getDireito();
            // verifica se o alvo não tem filhos antes de remover
            if (proximo.getEsquerdo() == null && proximo.getDireito() == null) {
                node.setDireito(null);
            } else {
                proximo.setCaractere("");   // caso o nó tenha filhos, só remove o caracter
            }
            return true;
        }

        // se não achou ainda, procura nas subárvores
        if (removerPorCaractereRec(node.getEsquerdo(), alvo)) return true;
        return removerPorCaractereRec(node.getDireito(), alvo);
    }



    public void removerPorMorse(String codigo) {
        Node atual = raiz;
        Node anterior = null; // gravar o nó anterior para poder remover o nó alvo
        String direcao = "";

        for (int i = 0; i < codigo.length(); i++) {
            String digito = codigo.substring(i, i + 1);
            anterior = atual;

            if (digito.equals(".")) {
                // esquerda
                atual = atual.getEsquerdo();
                direcao = "esq";
            } else if (digito.equals("-")) {
                // direita
                atual = atual.getDireito();
                direcao = "dir";
            } else {
                System.out.println("Código inválido");
                return;
            }

            if (atual == null) {
                System.out.println("Código não encontrado");
                return;
            }
        }

        // se o nó não tiver filhos, remove o nó usando o nó anterior
        if (atual.getEsquerdo() == null && atual.getDireito() == null) {
            if (direcao.equals("esq")) anterior.setEsquerdo(null);
            else anterior.setDireito(null);
        } else {    // se tiver filhos, só remove o caractere
            atual.setCaractere("");
        }

        // limpa a árvore para tirar nós vazios que não tem filhos
        removerNosVazios(raiz);
    }

    // função para retirar nós vazios que não tem filhos
    private Node removerNosVazios(Node node) {
        if (node == null) return null;
        // recursivamente limpa as subárvores do nó
        node.setEsquerdo(removerNosVazios(node.getEsquerdo()));
        node.setDireito(removerNosVazios(node.getDireito()));

        if ((node.getCaractere() == null || node.getCaractere().equals("")) &&
                node.getEsquerdo() == null && node.getDireito() == null) {
            return null;
        }
        return node;
    }

    public void exibirArvore() {
        exibirRec(raiz, "", "RAIZ");
    }

    private void exibirRec(Node node, String prefixo, String rota) {
        if (node == null) {
            return;
        }

        String s = node.getCaractere();
        if (s == null || s.equals("")) s = "( )";
        System.out.println(prefixo + rota + " -> " + s);

        exibirRec(node.getEsquerdo(), prefixo + "    ", ".");
        exibirRec(node.getDireito(), prefixo + "    ", "-");
    }


    public void popularArvore() {
        inserir(".-", "A");
        inserir("-...", "B");
        inserir("-.-.", "C");
        inserir("-..", "D");
        inserir(".", "E");
        inserir("..-.", "F");
        inserir("--.", "G");
        inserir("....", "H");
        inserir("..", "I");
        inserir(".---", "J");
        inserir("-.-", "K");
        inserir(".-..", "L");
        inserir("--", "M");
        inserir("-.", "N");
        inserir("---", "O");
        inserir(".--.", "P");
        inserir("--.-", "Q");
        inserir(".-.", "R");
        inserir("...", "S");
        inserir("-", "T");
        inserir("..-", "U");
        inserir("...-", "V");
        inserir(".--", "W");
        inserir("-..-", "X");
        inserir("-.--", "Y");
        inserir("--..", "Z");
        inserir("-----", "0");
        inserir(".----", "1");
        inserir("..---", "2");
        inserir("...--", "3");
        inserir("....-", "4");
        inserir(".....", "5");
        inserir("-....", "6");
        inserir("--...", "7");
        inserir("---..", "8");
        inserir("----.", "9");
    }

    public static void main(String[] args){
        ArvoreBinariaMorse arvore = new ArvoreBinariaMorse();
        arvore.inicializar();
        arvore.popularArvore();

        Scanner scanner = new Scanner(System.in);

        Boolean loop = true;

        while(loop) {

            System.out.println("Escolha uma opção:\n1. Exibir árvore\n2. Buscar por caractere\n3. Buscar por código morse\n" +
                    "4. Inserir caractere\n5. Remover por caractere\n6. Remover por código morse\n7. Sair");
            String escolha = scanner.next();
            scanner.nextLine();
            String caractere = "???";
            String codigo = "";

            switch(escolha){
                case "1":
                    System.out.println("=== Árvore Morse ===");
                    arvore.exibirArvore();
                    System.out.println();
                    break;
                case "2":
                    System.out.println("Digite um caractere:");
                    caractere = scanner.next();
                    scanner.nextLine();
                    System.out.println("Código morse: " + arvore.buscarPorCaractere(caractere));
                    break;

                case "3":
                    System.out.println("Digite um código morse:");
                    codigo = scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("Caractere: " + arvore.buscarPorMorse(codigo));
                    break;

                case "4":
                    while (caractere.length() != 1){
                        System.out.println("Digite um caractere:");
                        caractere = scanner.next();
                        scanner.nextLine();
                        if (caractere.length() != 1){
                            System.out.println("Caractere inválido");
                        }
                    }
                    while (codigo.isEmpty()){
                        System.out.println("Digite um código morse:");
                        codigo = scanner.next();
                        scanner.nextLine();
                        if (codigo.isEmpty()){
                            System.out.println("Código inválido");
                        }
                    }
                    arvore.inserir(codigo, caractere);
                    break;

                case "5":
                    while (caractere.length() != 1){
                        System.out.println("Digite um caractere:");
                        caractere = scanner.next();
                        scanner.nextLine();
                        if (caractere.length() != 1){
                            System.out.println("Caractere inválido");
                        }
                    }
                    arvore.removerPorCaractere(caractere);
                    break;

                case "6":
                    while (codigo.isEmpty()){
                        System.out.println("Digite um código morse:");
                        codigo = scanner.next();
                        scanner.nextLine();
                        if (codigo.isEmpty()){
                            System.out.println("Código inválido");
                        }
                    }
                    arvore.removerPorMorse(codigo);
                    break;

                case "7":
                    System.out.println("Saindo...");
                    loop = false;
                    break;
            }

        }

    }
}
