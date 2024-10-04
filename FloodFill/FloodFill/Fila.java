public class Fila {

    private Pixel[] fila;
    private int inicio;
    private int fim;
    private int tamanho;

    public Fila(int capacidade) {
        this.fila = new Pixel[capacidade];
        this.inicio = 0;
        this.fim = -1;
        this.tamanho = 0;
    }

    public void enfileirar(Pixel pixel) {
        if (tamanho < fila.length) {
            fim = (fim + 1) % fila.length;
            fila[fim] = pixel;
            tamanho++;
        } else {

            System.out.println("Fila cheia!");
        }
    }

    public Pixel desenfileirar() {
        if (estaVazia()) {
            return null;
        }
        Pixel pixelRemovido = fila[inicio];
        fila[inicio] = null;
        inicio = (inicio + 1) % fila.length;
        tamanho--;
        return pixelRemovido;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }
}