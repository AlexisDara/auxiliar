package tukitux;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class panelPrincipal extends JPanel {
    private JButton boton1;
    private JButton boton2;
    private JButton boton3;
    private Image fondo;

    public panelPrincipal(ActionListener listener) {
        setLayout(new BorderLayout());
        try {
            fondo = new ImageIcon(getClass().getResource("/tukitux/imagenes/FONDOPRINCIPAL.png")).getImage();
        } catch (Exception e) {
            fondo = null;
        }

        // Panel de botones alineados a la izquierda y centrados verticalmente
        JPanel panelBotones = new JPanel();
        panelBotones.setOpaque(false);
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        Dimension botonSize = new Dimension(400, 100);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 0));
        panelBotones.add(Box.createVerticalGlue());

        // Botón 1: imagen BotonJugar.png
        boton1 = new JButton();
        setButtonImage(boton1, "/tukitux/imagenes/BotonJugar.png", botonSize);
        boton1.setMaximumSize(botonSize);
        boton1.setPreferredSize(botonSize);
        boton1.setAlignmentX(Component.LEFT_ALIGNMENT);
        boton1.setFocusPainted(false);
        boton1.setBorderPainted(false);
        boton1.setContentAreaFilled(false);
        boton1.addActionListener(listener);
        panelBotones.add(boton1);
        panelBotones.add(Box.createVerticalStrut(30));

        // Botón 2: imagen BotonNiveles.png
        boton2 = new JButton();
        setButtonImage(boton2, "/tukitux/imagenes/BotonNiveles.png", botonSize);
        boton2.setMaximumSize(botonSize);
        boton2.setPreferredSize(botonSize);
        boton2.setAlignmentX(Component.LEFT_ALIGNMENT);
        boton2.setFocusPainted(false);
        boton2.setBorderPainted(false);
        boton2.setContentAreaFilled(false);
        boton2.addActionListener(listener);
        panelBotones.add(boton2);
        panelBotones.add(Box.createVerticalStrut(30));

        // Botón 3: imagen BotonSalir.png
        boton3 = new JButton();
        setButtonImage(boton3, "/tukitux/imagenes/BotonSalir.png", botonSize);
        boton3.setMaximumSize(botonSize);
        boton3.setPreferredSize(botonSize);
        boton3.setAlignmentX(Component.LEFT_ALIGNMENT);
        boton3.setFocusPainted(false);
        boton3.setBorderPainted(false);
        boton3.setContentAreaFilled(false);
        boton3.addActionListener(listener);
        panelBotones.add(boton3);
        panelBotones.add(Box.createVerticalGlue());

        add(panelBotones, BorderLayout.WEST);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void setButtonImage(JButton button, String imagePath, Dimension size) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
            Image img = icon.getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            // Si no se encuentra la imagen, el botón queda vacío
        }
    }

    public JButton getBoton1() { return boton1; }
    public JButton getBoton2() { return boton2; }
    public JButton getBoton3() { return boton3; }
}