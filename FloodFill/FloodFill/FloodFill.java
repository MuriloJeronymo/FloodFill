import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FloodFill {
    private JFrame frameAnimacao;
    private JLabel labelAnimacao;
    private static final int DELAY = 10;

    public void preencherComPilha(Imagem imagem, Pixel pixelInicial, Color novaCor) {
        iniciarFrameAnimacao(imagem.getImagem());

        int capacidade = imagem.getLargura() * imagem.getAltura();
        Pilha pilha = new Pilha(capacidade);
        Color corFundo = imagem.getCorPixel(pixelInicial);

        pilha.empilhar(pixelInicial);

        while (!pilha.estaVazia()) {
            Pixel pixelAtual = pilha.desempilhar();

            if (pixelAtual != null && dentroDosLimites(imagem, pixelAtual) &&
                    imagem.getCorPixel(pixelAtual).equals(corFundo)) {

                imagem.setCorPixel(pixelAtual, novaCor);
                atualizarAnimacao(imagem.getImagem());

                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                pilha.empilhar(new Pixel(pixelAtual.getX() + 1, pixelAtual.getY(), null));
                pilha.empilhar(new Pixel(pixelAtual.getX() - 1, pixelAtual.getY(), null));
                pilha.empilhar(new Pixel(pixelAtual.getX(), pixelAtual.getY() + 1, null));
                pilha.empilhar(new Pixel(pixelAtual.getX(), pixelAtual.getY() - 1, null));
            }
        }
    }

    public void preencherComFila(Imagem imagem, Pixel pixelInicial, Color novaCor) {
        iniciarFrameAnimacao(imagem.getImagem());

        int capacidade = imagem.getLargura() * imagem.getAltura();
        Fila fila = new Fila(capacidade);
        Color corFundo = imagem.getCorPixel(pixelInicial);

        fila.enfileirar(pixelInicial);

        while (!fila.estaVazia()) {
            Pixel pixelAtual = fila.desenfileirar();

            if (pixelAtual != null && dentroDosLimites(imagem, pixelAtual) &&
                    imagem.getCorPixel(pixelAtual).equals(corFundo)) {

                imagem.setCorPixel(pixelAtual, novaCor);
                atualizarAnimacao(imagem.getImagem());

                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                fila.enfileirar(new Pixel(pixelAtual.getX() + 1, pixelAtual.getY(), null));
                fila.enfileirar(new Pixel(pixelAtual.getX() - 1, pixelAtual.getY(), null));
                fila.enfileirar(new Pixel(pixelAtual.getX(), pixelAtual.getY() + 1, null));
                fila.enfileirar(new Pixel(pixelAtual.getX(), pixelAtual.getY() - 1, null));
            }
        }
    }

    private void iniciarFrameAnimacao(BufferedImage imagem) {
        frameAnimacao = new JFrame("Animação do Preenchimento");
        frameAnimacao.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        labelAnimacao = new JLabel(new ImageIcon(imagem));
        frameAnimacao.getContentPane().add(labelAnimacao);
        frameAnimacao.pack();
        frameAnimacao.setLocationRelativeTo(null);
        frameAnimacao.setVisible(true);
    }

    private void atualizarAnimacao(BufferedImage imagem) {
        if (frameAnimacao != null && labelAnimacao != null) {
            SwingUtilities.invokeLater(() -> {
                labelAnimacao.setIcon(new ImageIcon(imagem));
            });
        }
    }

    private boolean dentroDosLimites(Imagem imagem, Pixel pixel) {
        return pixel.getX() >= 0 && pixel.getX() < imagem.getLargura() &&
                pixel.getY() >= 0 && pixel.getY() < imagem.getAltura();
    }
}