
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pong extends Applet implements Runnable, KeyListener {

    // variables for pong game  
    final int WIDTH = 700, HEIGHT = 500;
    Thread thread;
    UserPaddle p1;
    AIPaddle p2;
    Ball b1; 
    boolean gameStarts;

    public void init() {
        this.resize(WIDTH, HEIGHT);
        gameStarts = false;
        this.addKeyListener(this);
        p1 = new UserPaddle(1);
        b1 = new Ball();
        p2 = new AIPaddle(2, b1);

        thread = new Thread(this);
        thread.start();

    }
    
    // draw objects
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        if (b1.getX() < -10 || b1.getX() > 710) {
            g.setColor(Color.red);
            g.drawString("Game Over", 320, 250);
        } else {

            p1.draw(g);
            b1.draw(g);
            p2.draw(g);
        }
        if (!gameStarts) { // message for user to press input to start
            g.setColor(Color.white);
            g.drawString("Tennis", 320, 100);
            g.drawString("Press space bar to begin", 280, 130);
        }
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void run() {
        for (;;) { //infinite loop
            if (gameStarts) {
                p1.move();
                p2.move();
                b1.move();
                b1.checkPaddleCollision(p1, p2);
            }
            repaint(); //
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
// abstract method
    public void keyTyped(KeyEvent e) {

    }
// if statements for key registration
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            p1.setUpAccel(true);

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            p1.setDownAccel(true);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            gameStarts = true;
        }
    }
// if statements for deacceleration
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            p1.setUpAccel(false);

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            p1.setDownAccel(false);

        }
    }
}

