import javax.swing.*;
import java.awt.*;

public class Boom {
    private Image img_boom = new ImageIcon("res/boom.png").getImage();
    private int x;
    private int y;

    public Image getImg_boom() {
        return img_boom;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Boom(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
