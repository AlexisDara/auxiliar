import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class panelPausa extends JPanel {
    private JButton btnReanudar;
    private JButton btnMenu;
    private JButton btnSalir;

    public panelPausa(ActionListener listener) {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Dimension botonSize = new Dimension(400, 100);
        setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));

        btnReanudar = new JButton();
        setButtonImage(btnReanudar, "tukitux/imagenes/Reanudar.png", botonSize);
        btnReanudar.setMaximumSize(botonSize);
        btnReanudar.setPreferredSize(botonSize);
        btnReanudar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnReanudar.setFocusPainted(false);
        btnReanudar.setBorderPainted(false);
        btnReanudar.setContentAreaFilled(false);
        btnReanudar.addActionListener(listener);
        btnReanudar.setActionCommand("reanudar");
        add(btnReanudar);
        add(Box.createVerticalStrut(30));

        btnMenu = new JButton();
        setButtonImage(btnMenu, "tukitux/imagenes/VolverAtras.png", botonSize);
        btnMenu.setMaximumSize(botonSize);
        btnMenu.setPreferredSize(botonSize);
        btnMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnMenu.setFocusPainted(false);
        btnMenu.setBorderPainted(false);
        btnMenu.setContentAreaFilled(false);
        btnMenu.addActionListener(listener);
        btnMenu.setActionCommand("menu");
        add(btnMenu);
        add(Box.createVerticalStrut(30));

        btnSalir = new JButton();
        setButtonImage(btnSalir, "tukitux/imagenes/BotonSalir.png", botonSize);
        btnSalir.setMaximumSize(botonSize);
        btnSalir.setPreferredSize(botonSize);
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.setFocusPainted(false);
        btnSalir.setBorderPainted(false);
        btnSalir.setContentAreaFilled(false);
        btnSalir.addActionListener(listener);
        btnSalir.setActionCommand("salir");
        add(btnSalir);
    }

    public JButton getBtnReanudar() { return btnReanudar; }
    public JButton getBtnMenu() { return btnMenu; }
    public JButton getBtnSalir() { return btnSalir; }

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
        super.paintComponent(g);
        // Fondo negro semitransparente
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(new Color(0, 0, 0, 150)); // 150/255 alpha para oscurecer
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.dispose();
    }
}