import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class panelNiveles extends JPanel {
    private JButton[] botonesNiveles;
    private JButton botonVolver;
    private Image fondo;

    public panelNiveles(ActionListener listener) {
        setLayout(new BorderLayout());
        try {
            fondo = new ImageIcon(getClass().getResource("/tukitux/imagenes/FONDOPRINCIPAL.png")).getImage();
        } catch (Exception e) {
            fondo = null;
        }

        // Panel con BoxLayout para alinear a la izquierda y centrar verticalmente
        JPanel panelBotones = new JPanel();
        panelBotones.setOpaque(false); // Permite ver el fondo del panelNiveles
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        Dimension botonSize = new Dimension(400, 100); // Tamaño original de los botones
        panelBotones.setBorder(BorderFactory.createEmptyBorder(140, 40, 20, 40)); // Aumenta el margen superior
        panelBotones.add(Box.createVerticalGlue());

        // Botón 1: fvcklove.png
        JButton fvckLoveBtn = new JButton();
        setButtonImage(fvckLoveBtn, "/tukitux/imagenes/fvcklove.png", botonSize);
        fvckLoveBtn.setMaximumSize(botonSize);
        fvckLoveBtn.setPreferredSize(botonSize);
        fvckLoveBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        fvckLoveBtn.setFocusPainted(false);
        fvckLoveBtn.setBorderPainted(false);
        fvckLoveBtn.setContentAreaFilled(false);
        fvckLoveBtn.addActionListener(listener);
        panelBotones.add(fvckLoveBtn);
        panelBotones.add(Box.createVerticalStrut(20));

        // Botón 2: michainderoke.png
        JButton chainBtn = new JButton();
        setButtonImage(chainBtn, "/tukitux/imagenes/michainderoque.png", botonSize);
        chainBtn.setMaximumSize(botonSize);
        chainBtn.setPreferredSize(botonSize);
        chainBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        chainBtn.setFocusPainted(false);
        chainBtn.setBorderPainted(false);
        chainBtn.setContentAreaFilled(false);
        chainBtn.addActionListener(listener);
        panelBotones.add(chainBtn);
        panelBotones.add(Box.createVerticalStrut(20));

        // Botón 3: tengo30.png
        JButton fumarBtn = new JButton();
        setButtonImage(fumarBtn, "/tukitux/imagenes/tengo30.png", botonSize);
        fumarBtn.setMaximumSize(botonSize);
        fumarBtn.setPreferredSize(botonSize);
        fumarBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        fumarBtn.setFocusPainted(false);
        fumarBtn.setBorderPainted(false);
        fumarBtn.setContentAreaFilled(false);
        fumarBtn.addActionListener(listener);
        panelBotones.add(fumarBtn);
        panelBotones.add(Box.createVerticalStrut(20));

        // Botón 4: ballin.png
        JButton ballinBtn = new JButton();
        setButtonImage(ballinBtn, "/tukitux/imagenes/BALLIN.png", botonSize);
        ballinBtn.setMaximumSize(botonSize);
        ballinBtn.setPreferredSize(botonSize);
        ballinBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        ballinBtn.setFocusPainted(false);
        ballinBtn.setBorderPainted(false);
        ballinBtn.setContentAreaFilled(false);
        ballinBtn.addActionListener(listener);
        panelBotones.add(ballinBtn);
        panelBotones.add(Box.createVerticalStrut(20));

        // Botón 5: HIELO.png
        JButton hieloBtn = new JButton();
        setButtonImage(hieloBtn, "/tukitux/imagenes/HIELO.png", botonSize);
        hieloBtn.setMaximumSize(botonSize);
        hieloBtn.setPreferredSize(botonSize);
        hieloBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        hieloBtn.setFocusPainted(false);
        hieloBtn.setBorderPainted(false);
        hieloBtn.setContentAreaFilled(false);
        hieloBtn.addActionListener(listener);
        panelBotones.add(hieloBtn);
        panelBotones.add(Box.createVerticalStrut(20));

        panelBotones.add(Box.createVerticalGlue());

        botonesNiveles = new JButton[]{fvckLoveBtn, chainBtn, fumarBtn, ballinBtn, hieloBtn};
        add(panelBotones, BorderLayout.WEST);

        botonVolver = new JButton();
        setButtonImage(botonVolver, "/tukitux/imagenes/VolverAtras.png", botonSize);
        botonVolver.setMaximumSize(botonSize);
        botonVolver.setPreferredSize(botonSize);
        botonVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonVolver.setFocusPainted(false);
        botonVolver.setBorderPainted(false);
        botonVolver.setContentAreaFilled(false);
        botonVolver.addActionListener(listener);
        JPanel panelVolver = new JPanel();
        panelVolver.setOpaque(false); // Permite ver el fondo del panelNiveles
        panelVolver.setLayout(new BoxLayout(panelVolver, BoxLayout.Y_AXIS));
        panelVolver.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));
        panelVolver.add(botonVolver);
        add(panelVolver, BorderLayout.SOUTH);
        setOpaque(true); // Asegura que el panelNiveles es opaco y cubre todo
    }

    public JButton getBotonNivel(int i) { return botonesNiveles[i]; }
    public JButton getBotonVolver() { return botonVolver; }

    // Método utilitario para setear imagen escalada en un botón
    private void setButtonImage(JButton button, String imagePath, Dimension size) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
            Image img = icon.getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            // Si no se encuentra la imagen, el botón queda vacío
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
        // No llamar a super.paintComponent(g) para evitar fondo blanco
    }
}