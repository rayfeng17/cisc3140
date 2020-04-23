import java.awt.Graphics;

// EventListener interface
public interface Paddle {

    public void draw(Graphics g);

    public void move();

    public int getY();

}
