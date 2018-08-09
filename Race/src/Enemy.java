import javax.swing.*;
import java.awt.*;

public class Enemy {
    private int x;
    private int y;
    private int v;
    private int width = 145;
    private int height = 55;
    private Image imgEnemy = new ImageIcon("res/enemy.png").getImage();
    Road road;

    public Image getImgEnemy() {
        return imgEnemy;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public Enemy(int x, int y, int v, Road road) {
        this.x = x;
        this.v = v;
        this.road = road;
        if (y < 330){
            this.y = 310;
        } else {
          this.y = y;
        }

    }

    public void move(){
        x = x - road.getPlayer().getV() + this.v;
    }

    public Rectangle getRectangle(){
        return new Rectangle(x, y, this.width, this.height);
    }
}

