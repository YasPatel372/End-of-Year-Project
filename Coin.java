import java.awt.Graphics;
import java.awt.Color;

public class Coin
{
    private int x;
    private int y;
    private int size;
    private boolean collected;

    public Coin(int x, int y)
    {
        this.x = x;
        this.y = y;

        size = 20;

        collected = false;
    }

    public void draw(Graphics g)
    {
        if(collected == false)
        {
            g.setColor(Color.YELLOW);
            g.fillOval(x, y, size, size);

            g.setColor(Color.ORANGE);
            g.drawOval(x, y, size, size);
        }
    }

    public boolean checkCollision(int px, int py, int pw, int ph)
    {
        if(collected)
        {
            return false;
        }

        if(px + pw >= x &&
           px <= x + size &&
           py + ph >= y &&
           py <= y + size)
        {
            collected = true;
            return true;
        }

        return false;
    }

    // ⭐ ADD THIS METHOD (lets coins move between levels)
    public void setPosition(int newX, int newY)
    {
        this.x = newX;
        this.y = newY;
        this.collected = false; // optional: resets coin so it appears again
    }
}
