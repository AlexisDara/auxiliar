import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        new MenuFrame(pantalla.width, pantalla.height);
    }
}
