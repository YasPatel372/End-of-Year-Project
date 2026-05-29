import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Platform
{
    private int x, y;
    private int width, height;
    private Image image;

    public Platform(int x, int y, int width, int height, String imageName)
    {
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        image = new ImageIcon(imageName).getImage();
    }

    public void draw(Graphics g)
    {
        g.drawImage(image, x, y, width, height, null);
    }
}
