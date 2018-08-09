import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Road extends JPanel implements ActionListener, Runnable {
    private Image img = new ImageIcon("res/road.png").getImage();
    private Player player = new Player();

    private List<Boom> booms = new ArrayList<>();

    private Timer timer = new Timer(20, this);

    private Thread enemiesFactory = new Thread(this);

    Thread audioThread = new Thread(new AudioThread());

    private List<Enemy> enemies = new ArrayList<>();

    public Player getPlayer() {
        return player;
    }

    public Road() {
        timer.start();
        enemiesFactory.start();
        audioThread.start();
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(img, player.getLayer1(), 0, null);
        g.drawImage(img, player.getLayer2(), 0, null);
        g.drawImage(player.getCurrentImgPlayer(), player.getX(), player.getY(), null);
        for (Enemy e : enemies) {
            g.drawImage(e.getImgEnemy(), e.getX(), e.getY(), null);
            checkPositionEnemy(e);
        }
        try{
            getTextSpeed(g);
            getTextWin(g);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            for (Boom b : booms){
                g.drawImage(b.getImg_boom(), b.getX(), b.getY(), null);
                booms.remove(b);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.move();

        for (Enemy o : enemies) o.move();

        repaint();

        collisionWithEnemy();
        collisionEneiesEachOther();
    }

    private void getTextWin(Graphics g){
        long s = player.getS()/10;
        g.setColor(Color.WHITE);
        g.setFont(new Font("Calibri", Font.BOLD, 18));
        g.drawString("Distance: " + s + "m", 20, 570);
    }

    private class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e){
            player.keyReleased(e);
        }
    }

    @Override
    public void run() {
        while(true){
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(2000));
                enemies.add(new Enemy(1200, random.nextInt(540), random.nextInt(20), this));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    private void checkPositionEnemy(Enemy e){
        if (e.getX() <= -2000 || e.getX() >= 2000){

            enemies.remove(e);
        }
    }

    private void collisionWithEnemy(){
        for (Enemy enemy : enemies){
            if (enemy.getRectangle().intersects(player.getRectangle())){
                JOptionPane.showMessageDialog(null, "You are loser)");
                booms.add(new Boom(player.getX()+50, player.getY()-70));
            }
        }
    }

    private void collisionEneiesEachOther(){
        try {
            for (Enemy enemy1 : enemies) {
                for (Enemy enemy2 : enemies) {
                    if (enemy1.getRectangle().intersects(enemy2.getRectangle()) && enemy1 != enemy2) {
                        enemies.remove(enemy1);
                        enemies.remove(enemy2);
                        booms.add(new Boom(enemy1.getX()+50, enemy1.getY()-70));
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void getTextSpeed(Graphics g){
        double speed = player.getV() * 3;
        g.setColor(Color.WHITE);
        g.setFont(new Font("Calibri", Font.BOLD, 18));
        g.drawString("Speed: " + speed + "km/h", 20, 20);
    }
}