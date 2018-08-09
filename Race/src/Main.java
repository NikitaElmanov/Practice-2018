import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        JFrame jFrame = new JFrame("Race");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(1100, 639);
        jFrame.setLocation((int) width / 2 - jFrame.getWidth() / 2, (int) height / 2 - jFrame.getHeight()/2);
        jFrame.setResizable(false);

        jFrame.add(new Road());
        jFrame.setVisible(true);
    }
}



