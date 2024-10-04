import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Imagem {
    private BufferedImage imagem;

    public Imagem(String caminhoImagem) throws IOException {
        BufferedImage originalImage = ImageIO.read(new File(caminhoImagem));


        if (originalImage.getType() != BufferedImage.TYPE_INT_RGB) {
            System.out.println("Convertendo imagem para RGB. Tipo original: " + originalImage.getType());
            BufferedImage newImage = new BufferedImage(
                    originalImage.getWidth(),
                    originalImage.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = newImage.createGraphics();
            g.drawImage(originalImage, 0, 0, null);
            g.dispose();
            this.imagem = newImage;
        } else {
            this.imagem = originalImage;
        }

        System.out.println("Tipo final da imagem: " + this.imagem.getType());
    }

    public int getLargura() {
        return imagem.getWidth();
    }

    public int getAltura() {
        return imagem.getHeight();
    }

    public Color getCorPixel(Pixel pixel) {
        int rgb = imagem.getRGB(pixel.getX(), pixel.getY());
        Color cor = new Color(rgb);
        System.out.println("Lendo pixel (" + pixel.getX() + "," + pixel.getY() +
                ") - R:" + cor.getRed() +
                ", G:" + cor.getGreen() +
                ", B:" + cor.getBlue());
        return cor;
    }

    public void setCorPixel(Pixel pixel, Color cor) {

        System.out.println("Definindo pixel (" + pixel.getX() + "," + pixel.getY() +
                ") para R:" + cor.getRed() +
                ", G:" + cor.getGreen() +
                ", B:" + cor.getBlue());

        int rgb = cor.getRGB();
        imagem.setRGB(pixel.getX(), pixel.getY(), rgb);


        Color novaCor = new Color(imagem.getRGB(pixel.getX(), pixel.getY()));
        System.out.println("Cor após definição - R:" + novaCor.getRed() +
                ", G:" + novaCor.getGreen() +
                ", B:" + novaCor.getBlue());
    }

    public void salvarImagem(String caminho) throws IOException {
        String extensao = caminho.substring(caminho.lastIndexOf('.') + 1);
        ImageIO.write(imagem, extensao, new File(caminho));
    }

    public BufferedImage getImagem() {
        return this.imagem;
    }
}