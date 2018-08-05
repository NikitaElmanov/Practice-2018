import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameFiled extends JPanel implements ActionListener {

    private final int SIZE = 288;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;
    private Image dot;
    private Image apple;
    private Image headUp;
    private Image headDown;
    private Image headLeft;
    private Image headRight;
    private int appleX;
    private int appleY;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int dots;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;
    private int speed = 250;
    private int score;

    public GameFiled() {
        setBackground(Color.DARK_GRAY);
        loadImage();
        initGame();
        addKeyListener(new KeyListener());
        setFocusable(true);
    }

    private void initGame(){
        dots = 3;
        for (int i = 0; i < dots; i++){
            x[i] = 48 - i * DOT_SIZE;
            y[i] = 48;
        }
        timer = new Timer(speed, this);
        timer.start();
        creatApple();
    }

    private void creatApple(){
        appleX = new Random().nextInt(19)*DOT_SIZE;
        appleY = new Random().nextInt(19)*DOT_SIZE;
    }

    private void loadImage(){
        ImageIcon appleImg = new ImageIcon("apple.png");
        apple = appleImg.getImage();
        ImageIcon bodyImg = new ImageIcon("javaBody.jpg");
        dot = bodyImg.getImage();
        ImageIcon headImg = new ImageIcon("up.png");
        headUp = headImg.getImage();
        ImageIcon leftImg = new ImageIcon("left.png");
        headLeft = leftImg.getImage();
        ImageIcon rightImg = new ImageIcon("right.png");
        headRight = rightImg.getImage();
        ImageIcon downImg = new ImageIcon("down.png");
        headDown = downImg.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame){
            g.drawImage(apple, appleX, appleY, this);

            if (left){
                g.drawImage(headLeft, x[0], y[0], this);
            }
            if (right){
                g.drawImage(headRight, x[0], y[0], this);
            }
            if (down){
                g.drawImage(headDown, x[0], y[0], this);
            }
            if (up){
                g.drawImage(headUp, x[0], y[0], this);
            }

            for (int i = 1; i < dots; i++) {
                g.drawImage(dot, x[i], y[i], this);
            }
        } else {
          String str = "Game Over(";
          String str2 = "Score: " + score;
          Font font = new Font("Calibri", Font.BOLD, 24);
          g.setColor(Color.WHITE);
          g.setFont(font);
          g.drawString(str, 100, SIZE/2);
            g.drawString(str2, 110, SIZE/3);
        }
    }

    private void move(){
        for (int i = dots; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if (left){
            x[0] -= DOT_SIZE;
        }
        if (right){
            x[0] += DOT_SIZE;
        }
        if (up){
            y[0] -= DOT_SIZE;
        }
        if (down){
            y[0] += DOT_SIZE;
        }
    }

    private void checkApple(){
        if (x[0] == appleX && y[0] == appleY){
            dots++;
            creatApple();
            speedUp();
            score++;
        }
    }

    private void collisionMyself(){
        for(int i = dots-1; i > 0; i--){
            if (dots > 4 && x[0] == x[i] && y[0] == y[i]){
                inGame = false;
            }
        }
    }

    private void collisionBorsers(){
        if(x[0] > SIZE || x[0] < 0 || y[0] > SIZE || y[0] < 0){
            if (left){
                x[0] = SIZE;
            }
            if (right){
                x[0] = 0;
            }
            if (down){
                y[0] = 0;
            }
            if (up){
                y[0] = SIZE;
            }
        }
    }

    private void speedUp(){
        speed += 5;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame){
            move();
            checkApple();
            collisionMyself();
            collisionBorsers();
        }
        repaint();
    }

    class KeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && !right){
                left = true;
                up = false;
                down = false;
            }
            if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && !left){
                right = true;
                up = false;
                down = false;
            }
            if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && !down){
                up = true;
                left = false;
                right = false;
            }
            if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && !up){
                down = true;
                left = false;
                right = false;
            }
        }
    }
}
