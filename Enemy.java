import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Enemy
{
    private int x;
    private int y;
    private int width;
    private int height;
    private int speed;
    private Image image;

    public Enemy(int x, int y, int speed, String imageName)
    {
        this.x = x;
        this.y = y;

        this.speed = speed;

        width = 90;
        height = 90;

        image = new ImageIcon(imageName).getImage();
    }

    public void move()
    {
        x += speed;

        if(x >= 900 || x <= 0)
        {
            speed *= -1;
        }
    }

    public void draw(Graphics g)
    {
        g.drawImage(image, x, y, width, height, null);
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
}
