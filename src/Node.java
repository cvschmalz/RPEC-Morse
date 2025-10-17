public class Node {
    String caractere;
    Node esquerdo;
    Node direito;

    public Node(String caractere) {
        this.caractere = caractere;
        this.esquerdo = null;   // ponto
        this.direito = null;    // traço
    }

    public Node() {
        this.caractere = null;
        this.esquerdo = null;   // ponto
        this.direito = null;    // traço
    }

    public String getCaractere(){
        return this.caractere;
    }

    public void setCaractere(String caractere) {
        this.caractere = caractere;
    }

    public Node getEsquerdo() {
        return esquerdo;
    }

    public void setEsquerdo(Node esquerdo) {
        this.esquerdo = esquerdo;
    }

    public Node getDireito() {
        return direito;
    }

    public void setDireito(Node direito) {
        this.direito = direito;
    }
}