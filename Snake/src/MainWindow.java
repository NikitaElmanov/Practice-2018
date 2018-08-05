import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow() {
        Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        setTitle("Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(320, 345);
        setLocation(sSize.width/2-320/2, sSize.height/2-345/2);
        add(new GameFiled());
        setVisible(true);
    }

    public static void main(String[] args) {
        MainWindow mw =  new MainWindow();
    }
}
