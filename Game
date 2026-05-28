
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Game extends JPanel implements Runnable, KeyListener
{
    private Player player;
    private Enemy enemy1;
    private Enemy enemy2;
    private Enemy enemy3;
    private Platform ground;
    private Coin coin1;
    private Coin coin2;
    private Coin coin3;
    private Coin coin4;
    private Coin coin5;
    private boolean left;
    private boolean right;
    private boolean gameOver;
    private boolean win;
    private int level;
    private int score;
    private Image background;

    public Game()
    {
        player = new Player();

        enemy1 = new Enemy(300, 500, 3, "enemy1.png");
        enemy2 = new Enemy(500, 500, 5, "enemy2.png");
        enemy3 = new Enemy(700, 500, 7, "enemy2.png");

        ground = new Platform(0, 550, 1000, 150);

        coin1 = new Coin(200, 480);
        coin2 = new Coin(400, 480);
        coin3 = new Coin(600, 480);
        coin4 = new Coin(750, 480);
        coin5 = new Coin(850, 480);

        level = 1;
        score = 0;
        gameOver = false;
        win = false;

        background = new ImageIcon("background.png").getImage();

        new Thread(this).start();
    }

    public void run()
    {
        try
        {
            while(true)
            {
                Thread.sleep(20);
                update();
                repaint();
            }
        }
        catch(Exception e) {}
    }

    public void update()
    {
        if(gameOver == true || win == true) { return; }

        if(left == true) { player.moveLeft(); }
        if(right == true) { player.moveRight(); }

        player.gravity();
        enemy1.move();

        if(level >= 2)
        {
            enemy2.move();
            enemy3.move();
        }

        checkCoins();
        checkCollision();
        checkWin();
    }

    public void checkCoins()
    {
        int px = player.getX();
        int py = player.getY();
        int pw = player.getWidth();
        int ph = player.getHeight();

        if(coin1.checkCollision(px, py, pw, ph)) { score += 10; }
        if(coin2.checkCollision(px, py, pw, ph)) { score += 10; }
        if(coin3.checkCollision(px, py, pw, ph)) { score += 10; }
        if(coin4.checkCollision(px, py, pw, ph)) { score += 10; }
        if(coin5.checkCollision(px, py, pw, ph)) { score += 10; }
    }

    public void checkCollision()
    {
        if(player.getX() + player.getWidth() >= enemy1.getX()
        && player.getX() <= enemy1.getX() + enemy1.getWidth()
        && player.getY() + player.getHeight() >= enemy1.getY())
        {
            gameOver = true;
        }

        if(level >= 2)
        {
            if(player.getX() + player.getWidth() >= enemy2.getX()
            && player.getX() <= enemy2.getX() + enemy2.getWidth()
            && player.getY() + player.getHeight() >= enemy2.getY())
            {
                gameOver = true;
            }

            if(player.getX() + player.getWidth() >= enemy3.getX()
            && player.getX() <= enemy3.getX() + enemy3.getWidth()
            && player.getY() + player.getHeight() >= enemy3.getY())
            {
                gameOver = true;
            }
        }
    }

    public void checkWin()
    {
        if(level == 1 && player.getX() >= 900)
        {
            level = 2;
            player.setX(100);
            score += 50;
            coin1 = new Coin(150, 480);
            coin2 = new Coin(300, 480);
            coin3 = new Coin(500, 480);
            coin4 = new Coin(650, 480);
            coin5 = new Coin(800, 480);
        }

        if(level == 2 && player.getX() >= 920)
        {
            score += 100;
            win = true;
        }
    }

    public void paint(Graphics g)
    {
        super.paint(g);

        g.drawImage(background, 0, 0, 1000, 700, null);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Forgotten Quest", 350, 50);
        g.drawString("Level: " + level, 50, 50);

        g.setColor(Color.YELLOW);
        g.drawString("Score: " + score, 800, 50);

        ground.draw(g);

        coin1.draw(g);
        coin2.draw(g);
        coin3.draw(g);
        coin4.draw(g);
        coin5.draw(g);

        player.draw(g);
        enemy1.draw(g);

        if(level >= 2)
        {
            enemy2.draw(g);
            enemy3.draw(g);

            g.setColor(Color.ORANGE);
            g.fillRect(350, 550, 120, 50);
            g.fillRect(600, 550, 100, 50);
            g.setColor(Color.WHITE);
            g.drawString("LAVA", 365, 585);
            g.drawString("LAVA", 615, 585);
        }

        g.setColor(Color.YELLOW);
        g.fillRect(930, 470, 40, 80);

        if(gameOver == true)
        {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 60));
            g.drawString("GAME OVER", 300, 300);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Final Score: " + score, 380, 370);
        }

        if(win == true)
        {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 60));
            g.drawString("YOU SAVED THE KINGDOM!", 120, 300);
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Final Score: " + score, 400, 370);
        }
    }

    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_A) { left = true; }
        if(key == KeyEvent.VK_D) { right = true; }
        if(key == KeyEvent.VK_SPACE) { player.jump(); }
    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_A) { left = false; }
        if(key == KeyEvent.VK_D) { right = false; }
    }

    public void keyTyped(KeyEvent e) {}
}
