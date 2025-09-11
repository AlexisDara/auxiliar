import java.awt.*;
import java.util.Random;

public class Enemigos {
    private int enemigosX, enemigosY;
    private int enemigosAncho, enemigosAlto;
    private Rectangle hitbox;
    private int tipo; 

    private int tortugaVelocidadX = 0;
    
    public Enemigos(int x, int y, int ancho, int alto, int tipo) {
        this.enemigosX = x;
        this.enemigosY = y;
        this.enemigosAncho = ancho;
        this.enemigosAlto = alto;
        this.hitbox = new Rectangle(x, y, ancho, alto);
        this.tipo = tipo;
        
        if(tipo == 1) {
        	moverIzquierdaTortuga();
        }
    }

    public void dibujar(Graphics g, int cameraX) {
        if (tipo == 1) g.setColor(Color.BLUE);
        else if (tipo == 2) g.setColor(Color.ORANGE);
        else g.setColor(Color.MAGENTA);

        g.fillRect(enemigosX - cameraX, enemigosY, enemigosAncho, enemigosAlto);

        g.setColor(Color.BLACK);
        g.drawRect(hitbox.x - cameraX, hitbox.y, hitbox.width, hitbox.height);
    }

    public void actualizar() {
        enemigosX += tortugaVelocidadX;
        
        if (tipo == 1) {
            if (enemigosX < 0 || enemigosX > 5000 - enemigosAncho) {
                tortugaVelocidadX *= -1; 
            }
        }

        hitbox.setLocation(enemigosX, enemigosY);
    }

    public void moverIzquierdaTortuga() {
    	tortugaVelocidadX = -1; 
    }

    public void detener() {
    	tortugaVelocidadX = 0;
    }

    public Rectangle getHitbox() { return hitbox; }
}