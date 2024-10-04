public class Pilha {

    private Pixel[] pilha;
    private int topo;

    public Pilha(int capacidade) {
        this.pilha = new Pixel[capacidade];
        this.topo = -1;
    }

    public void empilhar(Pixel pixel) {
        if (topo < pilha.length - 1) {
            topo++;
            pilha[topo] = pixel;
        } else {
            System.out.println("Pilha cheia!");
        }
    }

    public Pixel desempilhar() {
        if (estaVazia()) {
            return null;
        }
        Pixel pixelRemovido = pilha[topo];
        pilha[topo] = null;
        topo--;
        return pixelRemovido;
    }

    public boolean estaVazia() {
        return topo == -1;
    }
}