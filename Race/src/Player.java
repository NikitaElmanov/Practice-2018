import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Player {

    private Image imgPlayer = new ImageIcon("res/player.png").getImage();
    private Image imgPlayer_l = new ImageIcon("res/player_up.png").getImage();
    private Image imgPlayer_r = new ImageIcon("res/player_down.png").getImage();
    private Image currentImgPlayer = new ImageIcon("res/player.png").getImage();
    private int v = 0;
    private int dv = 0;
    private int s = 0;
    private int layer1 = 0;
    private int layer2 = 1200;
    private int x = 100;
    private int y = 370;
    private int width = 145;
    private int height = 55;

    public static final int MAX_V = 30;
    public static final int MAX_TOP = 325;
    public static final int MAX_BOTTOM = 530;

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Image getImgPlayer() {
        return imgPlayer;
    }

    public int getLayer1() {
        return layer1;
    }

    public int getLayer2() {
        return layer2;
    }

    public int getV() {
        return v;
    }

    public Image getCurrentImgPlayer() {
        return currentImgPlayer;
    }

    public int getS() {
        return s;
    }

    public void move(){
        s += v;
        v += dv;
        if (y <= 325 && y >= 310 && v >= 10) v = 10;  //stopping
        if (v <= 0) v = 0;                 //don't move back
        if (v >= MAX_V) v = MAX_V;
        if (y < MAX_TOP) y = MAX_TOP;      // limitation on the top
        if (y > MAX_BOTTOM) y = MAX_BOTTOM;//limitation on the bottom
        if (layer1 <= -1200){
            layer1 = layer2 + 1200;
        }
        if (layer2 <= -1200){
            layer2 = layer1 + 1200;
        } else {
            layer1 -= v;
            layer2 -= v;
        }
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D){
            dv = 1;
        }
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A){
            dv = -1;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W){
            y -= 15;
            currentImgPlayer = imgPlayer_l;
        }
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S){
            y += 15;
            currentImgPlayer = imgPlayer_r;
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        currentImgPlayer = imgPlayer;

        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A){
            dv = 0;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W){
            y = y;
        }
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S){
            y = y;
        }
    }

    public Rectangle getRectangle(){
        return new Rectangle(x, y, this.width, this.height);
    }
}
