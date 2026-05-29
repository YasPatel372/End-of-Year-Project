import javax.swing.JPanel;
import java.awt.*;
import javax.swing.ImageIcon;
import java.awt.event.*;

public class Game extends JPanel implements Runnable, KeyListener
{
    private Player player;

    private Enemy enemy1, enemy2, enemy3, bossEnemy;

    private Platform ground;

    private Coin coin1, coin2, coin3, coin4, coin5;

    private boolean left, right;
    private boolean gameOver, win, paused;
    private boolean shield;

    private int level, score;

    private Image background;

    public Game()
    {
        setPreferredSize(new Dimension(1000, 700));

        addKeyListener(this);
        setFocusable(true);

        gameOver = false;
        win = false;
        paused = false;
        left = false;
        right = false;
        level = 1;
        score = 0;
        shield = false;

        resetGame();

        background = new ImageIcon("Background.png").getImage();

        new Thread(this).start();
    }

    public void resetGame()
    {
        player = new Player();
        player.setX(100);

        enemy1 = new Enemy(300, 480, 3, "enemy1.png");
        enemy2 = new Enemy(500, 480, 5, "enemy2.png");
        enemy3 = new Enemy(700, 480, 7, "enemy2.png");

        bossEnemy = new Enemy(850, 480, 12, "enemyBoss.png");

        ground = new Platform(0, 500, 1000, 150, "platform.png");

        coin1 = new Coin(200, 450);
        coin2 = new Coin(400, 450);
        coin3 = new Coin(600, 450);
        coin4 = new Coin(750, 450);
        coin5 = new Coin(900, 450);

        left = false;
        right = false;

        gameOver = false;
        win = false;
        paused = false;

        level = 1;
        score = 0;
    }

    public void run()
    {
        while(true)
        {
            try { Thread.sleep(20); } catch(Exception e) {}

            update();
            repaint();
        }
    }

    public void update()
    {
        if(gameOver || win || paused) return;

        if(left) player.moveLeft();
        if(right) player.moveRight();

        player.gravity();

        enemy1.move();

        if(level >= 2)
        {
            enemy2.move();
            enemy3.move();
        }

        if(level == 3)
        {
            bossEnemy.move();
        }

        checkCoins();
        checkCollision();
        checkWin();
    }

    public void checkWin()
    {
        if (getWidth() == 0) return;

        // LEVEL 1 → LEVEL 2
        if(level == 1 && player.getX() + player.getWidth() >= getWidth())
        {
            level = 2;
            player.setX(100);
            score += 50;

            // ⭐ LEVEL 2 COINS
            coin1.setPosition(250, 450);
            coin2.setPosition(450, 450);
            coin3.setPosition(650, 450);
            coin4.setPosition(800, 450);
            coin5.setPosition(900, 450);
        }

        // LEVEL 2 → LEVEL 3
        if(level == 2 && player.getX() + player.getWidth() >= getWidth())
        {
            level = 3;
            player.setX(100);
            score += 100;

            // ⭐ LEVEL 3 COINS (spread out)
            coin1.setPosition(150, 450);
            coin2.setPosition(350, 430);
            coin3.setPosition(550, 450);
            coin4.setPosition(750, 430);
            coin5.setPosition(900, 450);
        }

        // LEVEL 3 → WIN
        if(level == 3 && player.getX() + player.getWidth() >= getWidth())
        {
            win = true;
        }
    }

    public void checkCoins()
    {
        int px = player.getX();
        int py = player.getY();
        int pw = player.getWidth();
        int ph = player.getHeight();

        if(coin1.checkCollision(px, py, pw, ph)) score += 10;
        if(coin2.checkCollision(px, py, pw, ph)) score += 10;
        if(coin3.checkCollision(px, py, pw, ph)) score += 10;
        if(coin4.checkCollision(px, py, pw, ph)) score += 10;
        if(coin5.checkCollision(px, py, pw, ph)) score += 10;
    }

    public void checkCollision()
    {
        if(shield) return;

        if(hit(enemy1)) gameOver = true;

        if(level >= 2)
        {
            if(hit(enemy2)) gameOver = true;
            if(hit(enemy3)) gameOver = true;
        }

        if(level == 3)
        {
            if(hit(bossEnemy)) gameOver = true;
        }
    }

    public boolean hit(Enemy e)
    {
        int px = player.getX();
        int py = player.getY();
        int pw = player.getWidth();
        int ph = player.getHeight();

        int ex = e.getX();
        int ey = e.getY();
        int ew = e.getWidth();
        int eh = e.getHeight();

        boolean xOverlap = px + pw > ex && px < ex + ew;
        boolean yOverlap = py + ph > ey && py < ey + eh;

        return xOverlap && yOverlap;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

        ground.draw(g);

        player.draw(g);

        enemy1.draw(g);
        if(level >= 2)
        {
            enemy2.draw(g);
            enemy3.draw(g);
        }

        if(level == 3)
        {
            bossEnemy.draw(g);
        }

        coin1.draw(g);
        coin2.draw(g);
        coin3.draw(g);
        coin4.draw(g);
        coin5.draw(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));

        g.drawString("Score: " + score, 800, 40);
        g.drawString("Level: " + level, 50, 40);

        if(shield)
        {
            g.setColor(Color.CYAN);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("SHIELD ACTIVE", 380, 80);
        }

        if(gameOver)
        {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 60));
            g.drawString("GAME OVER", 300, 300);
        }

        if(win)
        {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 60));
            g.drawString("YOU WIN", 350, 300);
        }

        if(paused)
        {
            g.setColor(Color.BLUE);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("PAUSED", 380, 300);
        }
    }

    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();

        if(key == 37) left = true;
        if(key == 39) right = true;
        if(key == 32) player.jump();

        if(key == 82) resetGame();
        if(key == 80) paused = !paused;

        if(key == 90) shield = !shield;
    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        if(key == 37) left = false;
        if(key == 39) right = false;
    }

    public void keyTyped(KeyEvent e) {}
}
