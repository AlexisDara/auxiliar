import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;

public class PanelJuego extends JPanel implements ActionListener, KeyListener {

    private Timer timer;
    private Jugador jugador;
    private Enemigos enemigo;
    private Meta meta;
    private MenuFrame menuFrame;
    private boolean enPausa = false;

    private int alturaSuelo = 100;

    private int anchoPantalla;
    private int altoPantalla;
    
    private int xJugador = 100;
    private int altoJugador = 100;
    private int anchoJugador = 25;
    
    private int camaraX = 0;
    
    private ArrayList<Enemigos> enemigos = new ArrayList<>();
    private int anchoMundo = 5000;
    
    private int nivelActual = 1;
    private final int nivelesTotales = 5;
    
    private boolean nivelCompletado = false;
    
    private panelPausa panelPausa;

    public PanelJuego(int anchoPantalla, int altoPantalla, MenuFrame menuFrame, int nivel) {
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;
        this.menuFrame = menuFrame;
        this.nivelActual = nivel;
        setPreferredSize(new Dimension(anchoPantalla, altoPantalla));
        setBackground(Color.CYAN);
        int yJugador = altoPantalla - alturaSuelo - altoJugador;
        jugador = new Jugador(xJugador, yJugador, anchoJugador, altoJugador);
        cargarNivel(nivelActual);
        timer = new Timer(16, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        
        panelPausa = new panelPausa(this);
        panelPausa.setVisible(false);
        setLayout(null);
        add(panelPausa);
        panelPausa.setBounds(0, 0, anchoPantalla, altoPantalla);
    }
    
    
    private void cargarNivel(int nivel) {
        enemigos.clear();

        Random rand = new Random();
        int yEnemigo = altoPantalla - alturaSuelo - 40;

        for (int i = 0; i < 30; i++) {
            int x = 300 + rand.nextInt(anchoMundo - 400);
            int tipo = 1 + rand.nextInt(3);
            enemigos.add(new Enemigos(x, yEnemigo, 40, 40, tipo));
        }

        int yMeta = altoPantalla - alturaSuelo - 100;
        meta = new Meta(anchoMundo - 200, yMeta, 50, 100);

        jugador.setX(100);
        jugador.setY(altoPantalla - alturaSuelo - altoJugador);
        camaraX = 0;
        nivelCompletado = false;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Timer) {
            jugador.actualizar(altoPantalla - alturaSuelo);
            
            camaraX = jugador.getX() - anchoPantalla / 2;
            if (camaraX < 0) camaraX = 0;
            if (camaraX > anchoMundo - anchoPantalla) {
                camaraX = anchoMundo - anchoPantalla;
            }

            for (Enemigos enemigo : enemigos) {
                enemigo.actualizar();
            }

            camaraX = jugador.getX() - anchoPantalla / 2;
            if (camaraX < 0) camaraX = 0;
            if (camaraX > anchoMundo - anchoPantalla) camaraX = anchoMundo - anchoPantalla;
            
            
            if (!nivelCompletado && meta != null && jugador.getHitbox().intersects(meta.getBounds())) {
                nivelCompletado = true;

                if (nivelActual < nivelesTotales) {
                    nivelActual++;
                    JOptionPane.showMessageDialog(this, "�Nivel " + (nivelActual - 1) + " completado! Avanzando al nivel " + nivelActual);
                    cargarNivel(nivelActual);
                } else {
                    JOptionPane.showMessageDialog(this, "�Has completado todos los niveles!");
                    System.exit(0);
                }
            }
            
            repaint();
        } else if (e.getSource() instanceof JButton) {
            String cmd = ((JButton) e.getSource()).getActionCommand();
            if (cmd.equals("reanudar")) {
                reanudar();
            } else if (cmd.equals("menu")) {
                if (menuFrame != null) menuFrame.volverAlMenuPrincipal();
            } else if (cmd.equals("salir")) {
                System.exit(0);
            }
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GREEN);
        g.fillRect(0 - camaraX, altoPantalla - alturaSuelo, anchoMundo, alturaSuelo);

        jugador.dibujar(g, camaraX);

        for (Enemigos enemigo : enemigos) {
            enemigo.dibujar(g, camaraX);
        }
        
        meta.dibujar(g, camaraX);
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, anchoPantalla, 40);
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Puntaje: 0", 20, 25);
        g.drawString("Vida: 3", 200, 25);
        g.drawString("Nivel: " + nivelActual, 350, 25);
    }



    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_ESCAPE && !enPausa) {
            if (menuFrame != null) {
                pausar();
                panelPausa.setVisible(true);
                panelPausa.requestFocusInWindow();
            }
        }
        if (!enPausa) {
            if(tecla == KeyEvent.VK_LEFT) {
                jugador.moverIzquierda();
            }
            if(tecla == KeyEvent.VK_RIGHT) {
                jugador.moverDerecha();
            }
            if(tecla == KeyEvent.VK_SPACE) {
                jugador.saltar();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	int tecla = e.getKeyCode();

        if (tecla == KeyEvent.VK_LEFT || tecla == KeyEvent.VK_RIGHT) {
            jugador.detener();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    	jugador.actualizar(altoPantalla - alturaSuelo);
        repaint();
    }
    
    public void pausar() {
        enPausa = true;
        if (timer != null) timer.stop();
        if (panelPausa != null) panelPausa.setVisible(true);
    }

    public void reanudar() {
        enPausa = false;
        if (timer != null) timer.start();
        if (panelPausa != null) panelPausa.setVisible(false);
        requestFocusInWindow();
    }
}