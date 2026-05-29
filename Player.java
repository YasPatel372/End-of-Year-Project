import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Player
{
    private int x;
    private int y;
    private int width;
    private int height;
    private int velocityY;
    private boolean jumping;
    private Image image;

    public Player()
    {
        x = 100;
        y = 500;
        width = 50;
        height = 50;
        velocityY = 0;
        jumping = false;
        image = new ImageIcon("player.png").getImage();
    }

    public void moveLeft()
    {
        x -= 7;
    }

    public void moveRight()
    {
        x += 7;
    }

    public void jump()
    {
        if(jumping == false)
        {
            velocityY = -18;
            jumping = true;
        }
    }

    public void gravity()
    {
        y += velocityY;
        velocityY += 1;
        if(y >= 500)
        {
            y = 500;
            velocityY = 0;
            jumping = false;
        }
    }

    public void draw(Graphics g)
    {
        g.drawImage(image, x, y, width, height, null);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
}
