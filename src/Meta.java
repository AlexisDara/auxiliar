import java.awt.*;

public class Meta {
    private int metaX, metaY, metaAncho, metaAlto;

    public Meta(int x, int y, int ancho, int alto) {
        this.metaX = x;
        this.metaY = y;
        this.metaAncho = ancho;
        this.metaAlto = alto;
    }

    public void dibujar(Graphics g, int camaraX) {
        g.fillRect(metaX - camaraX, metaY, metaAncho, metaAlto);
    }

    public Rectangle getBounds() {
        return new Rectangle(metaX, metaY, metaAncho, metaAlto);
    }
}
