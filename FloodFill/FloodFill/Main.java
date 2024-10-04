import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static boolean cliqueCapturado = false;
    private static int xInicial, yInicial;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o caminho da imagem (sem aspas):");
        String caminhoImagem = scanner.nextLine();

        try {
            Imagem imagem = new Imagem(caminhoImagem);

            exibirImagemOriginal(imagem.getImagem(), "Selecione o ponto inicial");

            while (!cliqueCapturado) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Coordenadas do pixel inicial: (" + xInicial + ", " + yInicial + ")");

            System.out.println("Digite a nova cor (R G B)(255 255 255):");
            int r = scanner.nextInt();
            int g = scanner.nextInt();
            int b = scanner.nextInt();
            scanner.nextLine();
            Color novaCor = new Color(r, g, b);

            System.out.println("Usar Pilha (P) ou Fila (F)?");
            String escolha = scanner.nextLine().toUpperCase();

            FloodFill floodFill = new FloodFill();
            Pixel pixelInicial = new Pixel(xInicial, yInicial, null);

            if (escolha.equals("P")) {
                floodFill.preencherComPilha(imagem, pixelInicial, novaCor);
            } else if (escolha.equals("F")) {
                floodFill.preencherComFila(imagem, pixelInicial, novaCor);
            } else {
                System.out.println("Escolha inválida.");
                return;
            }

            System.out.println("Digite o caminho para salvar a imagem preenchida:");
            String caminhoSalvar = scanner.nextLine();
            imagem.salvarImagem(caminhoSalvar);
            System.out.println("Imagem salva com sucesso!");

        } catch (IOException e) {
            System.err.println("Erro ao carregar ou salvar a imagem: " + e.getMessage());
        }
    }

    private static void exibirImagemOriginal(BufferedImage imagem, String titulo) {
        JFrame frame = new JFrame(titulo);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel(new ImageIcon(imagem));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xInicial = e.getX();
                yInicial = e.getY();
                cliqueCapturado = true;
                frame.dispose();
            }
        });

        frame.getContentPane().add(label);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}