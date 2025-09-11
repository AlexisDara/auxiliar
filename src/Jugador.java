import java.awt.*;

public class Jugador {
    private int jugadorX, jugadorY;
    private int jugadorAncho, jugadorAlto;
    private int jugadorVidas;
    private Rectangle jugadorHitbox;

    private int jugadorVelocidadX = 0;
    private int jugadorVelocidadY = 0;

    private boolean enSuelo = true; 

    public static final int GRAVEDAD = 1;
    public static final int FUERZA_SALTO = -15;

    public Jugador(int x, int y, int ancho, int alto) {
        this.jugadorX = x;
        this.jugadorY = y;
        this.jugadorAncho = ancho;
        this.jugadorAlto = alto;
        this.jugadorVidas = 3;
        this.jugadorHitbox = new Rectangle(x, y, ancho, alto);
    }

    public void dibujar(Graphics g, int cameraX) {
        g.setColor(Color.RED);
        g.fillRect(jugadorX - cameraX, jugadorY, jugadorAncho, jugadorAlto);

        g.setColor(Color.BLACK);
        g.drawRect(jugadorHitbox.x - cameraX, jugadorHitbox.y, jugadorHitbox.width, jugadorHitbox.height);
    }

    public void actualizar(int alturaSuelo) {
        if (!enSuelo) {
            jugadorVelocidadY += GRAVEDAD;
        }

        jugadorY += jugadorVelocidadY;
        jugadorX += jugadorVelocidadX;

        if (jugadorY + jugadorAlto >= alturaSuelo) {
            jugadorY = alturaSuelo - jugadorAlto;
            jugadorVelocidadY = 0;
            enSuelo = true;
        }

        jugadorHitbox.setLocation(jugadorX, jugadorY);
    }

    public void moverIzquierda() { jugadorVelocidadX = -5; }
    public void moverDerecha() { jugadorVelocidadX = 10; }
    public void detener() { jugadorVelocidadX = 0; }

    public void saltar() {
        if (enSuelo) {
            jugadorVelocidadY = FUERZA_SALTO;
            enSuelo = false;
        }
    }

    public Rectangle getHitbox() { 
        return jugadorHitbox; 
    }
    public Rectangle getBounds() {            
        return jugadorHitbox;
    }
    public int getX() { return jugadorX; }
    public int getY() { return jugadorY; }
    public int getAncho() { return jugadorAncho; }
    public int getAlto() { return jugadorAlto; }

    public void setX(int x) {
        this.jugadorX = x;
        jugadorHitbox.setLocation(jugadorX, jugadorY);
    }
    public void setY(int y) {
        this.jugadorY = y;
        jugadorHitbox.setLocation(jugadorX, jugadorY);
    }
    public void setPosition(int x, int y) {
        this.jugadorX = x;
        this.jugadorY = y;
        this.jugadorVelocidadX = 0;
        this.jugadorVelocidadY = 0;
        this.enSuelo = true;
        jugadorHitbox.setLocation(jugadorX, jugadorY);
    }

    public void resetToStart(int startX, int sueloY) {
        this.jugadorX = startX;
        this.jugadorY = sueloY - jugadorAlto; 
        this.jugadorVelocidadX = 0;
        this.jugadorVelocidadY = 0;
        this.enSuelo = true;
        jugadorHitbox.setLocation(jugadorX, jugadorY);
    }

}
