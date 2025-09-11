import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuFrame extends JFrame implements ActionListener {
    private panelPrincipal menuPrincipal;
    private panelNiveles panelNiveles;
    private PanelJuego panelJuego;
    private panelPausa panelPausa;
    private int anchoPantalla;
    private int altoPantalla;
    private int nivelEnCurso = 1;

    public MenuFrame(int ancho, int alto) {
        super("TukiTux");
        this.anchoPantalla = ancho;
        this.altoPantalla = alto;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLayout(new BorderLayout());

        menuPrincipal = new panelPrincipal(this);
        panelNiveles = new panelNiveles(this);
        setContentPane(menuPrincipal);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        // Botón Jugar
        if (src == menuPrincipal.getBoton1()) {
            iniciarJuego(1);
        }
        // Botón Niveles
        else if (src == menuPrincipal.getBoton2()) {
            setContentPane(panelNiveles);
            revalidate();
            repaint();
        }
        // Botón Salir
        else if (src == menuPrincipal.getBoton3()) {
            System.exit(0);
        }
        // Botón Volver en menú niveles
        else if (src == panelNiveles.getBotonVolver()) {
            setContentPane(menuPrincipal);
            revalidate();
            repaint();
        }
        else if (src == panelNiveles.getBotonNivel(0)) {
            iniciarJuego(1);
        }
        else if (src == panelNiveles.getBotonNivel(1)) {
            iniciarJuego(2);
        }
        else if (src == panelNiveles.getBotonNivel(2)) {
            iniciarJuego(3);
        }
        else if (src == panelNiveles.getBotonNivel(3)) {
            iniciarJuego(4);
        }
        else if (src == panelNiveles.getBotonNivel(4)) {
            iniciarJuego(5);
        }
        // PAUSA: Botones del panelPausa
        else if (panelPausa != null && src == panelPausa.getBtnReanudar()) {
            reanudarJuego();
        }
        else if (panelPausa != null && src == panelPausa.getBtnMenu()) {
            setContentPane(menuPrincipal);
            revalidate();
            repaint();
        }
        else if (panelPausa != null && src == panelPausa.getBtnSalir()) {
            System.exit(0);
        }
    }

    public void pausarJuego() {
        if (panelJuego != null) panelJuego.pausar();
        if (panelPausa == null) panelPausa = new panelPausa(this);
        setContentPane(panelPausa);
        revalidate();
        repaint();
        panelPausa.requestFocusInWindow();
    }

    public void reanudarJuego() {
        if (panelJuego != null) panelJuego.reanudar();
        setContentPane(panelJuego);
        revalidate();
        repaint();
        panelJuego.requestFocusInWindow();
    }

    private void iniciarJuego(int nivel) {
        nivelEnCurso = nivel;
        panelJuego = new PanelJuego(anchoPantalla, altoPantalla, this, nivel);
        setContentPane(panelJuego);
        revalidate();
        repaint();
        panelJuego.requestFocusInWindow();
    }

    public void volverAlMenuPrincipal() {
        setContentPane(menuPrincipal);
        revalidate();
        repaint();
    }
}