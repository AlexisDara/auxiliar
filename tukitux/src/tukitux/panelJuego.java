package tukitux;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class panelJuego extends JFrame implements ActionListener {
 
	private static final long serialVersionUID = 1L;
	private panelPrincipal panelPrincipal;
    private panelNiveles panelNiveles;

    public panelJuego() {
        setTitle("tuki-tux");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla completa
        setUndecorated(true); // Sin bordes ni barra de título
        setLayout(new BorderLayout());
        panelPrincipal = new panelPrincipal(this);
        panelNiveles = new panelNiveles(this);

        setContentPane(panelPrincipal);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        // Botón 2 del menú principal lleva a panelNiveles
        if (src == panelPrincipal.getBoton2()) {
            setContentPane(panelNiveles);
            revalidate();
            repaint();
        }
        // Botón volver en panelNiveles regresa al menú principal
        else if (src == panelNiveles.getBotonVolver()) {
            setContentPane(panelPrincipal);
            revalidate();
            repaint();
        }
        // Botón 3 del menú principal cierra el juego
        else if (src == panelPrincipal.getBoton3()) {
            System.exit(0);
        }
        // Puedes agregar más acciones para otros botones aquí
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(panelJuego::new);
    }
}