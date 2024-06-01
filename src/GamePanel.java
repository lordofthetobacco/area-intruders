import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel extends JPanel implements GameConstants, Runnable {

    private Thread mainThread = new Thread(this);
    
    private JFrame windowReference;
    private ControlBar controlBarReference;
    private SettingsFrame settingsFrameReference;
    private Score scoreReference;
    
    private Player player;
    private Image background;
    private Graphics bgGraphics;
    private int enemiesCountInRow = spawnCountInFirstRow;

    private Invader firstInvader;
    private Invader lastInvader;

    GamePanel(JFrame windowRef, ControlBar controlBarRef, SettingsFrame settingsFrameRef, Score scoreRef) {
        this.windowReference = windowRef;
        this.controlBarReference = controlBarRef;
        this.settingsFrameReference = settingsFrameRef;
        this.scoreReference = scoreRef;
        setFocusable(true);
        newGame();
        mainThread.start();
    }

    public void pauseGame() {
        
    }

    public void resumeGame() {
        
    }

    public void newGame() {
        spawnPlayer();
        newEnemies();
    }

    private void spawnPlayer() {
        player = new Player((frameWidth / 2) - playerWidth, frameHeight - playerHeight - toolbarHeight - menubarHeight, playerWidth, playerHeight);
    }

    public void paint(Graphics g) {
        try {
            background = ImageIO.read(new File(globalBackgrounds.get(GameVariables.currentBackgroundModelIndex)));
        } catch (IOException e) {
            background = createImage(getWidth(), getHeight());
        }
        bgGraphics = background.getGraphics();
        draw(bgGraphics);
        g.drawImage(background, 0, 0, this);
    }

    private void checkForPlayerCollisionsWithWalls() {
        if (player.x <= 0) {
            player.x = 0;
        }
        if (player.x >= frameWidth - playerWidth * 2) {
            player.x = frameWidth - playerWidth * 2;
        }
    }

    private void checkForPlayerCollisionWithHostileMissile() {
        Iterator<InvaderMissile> missileIterator = globalInvaderMissiles.iterator();
        while (missileIterator.hasNext()) {
           if (player.intersects(missileIterator.next())) {
                mainThread.interrupt();
           } 
        }
    }

    private void checkIfInvaderShotDown() {
        Missile missile;
        Iterator<Invader> invaderIterator = globalInvaders.iterator();
        Iterator<Missile> missileIterator = globalMissiles.iterator();
        while (missileIterator.hasNext()) {
            missile = missileIterator.next();
            if (!(missile.y <= 0)) {
                while (invaderIterator.hasNext()) {
                    if (missile.intersects(invaderIterator.next())) {
                        missileIterator.remove();
                        invaderIterator.remove();
                        if (globalInvaders.size() > 1) {
                            lastInvader = globalInvaders.stream().max(Comparator.comparingDouble(Invader::getX)).get();
                            firstInvader = globalInvaders.stream().min(Comparator.comparingDouble(Invader::getX)).get();
                        }
                        scoreReference.addPoints();
                    }
                }
            } else {
                missileIterator.remove();
            }
        }
    }

    private void checkForInvaderCollisionsWithWall() {
        for (Invader invader : globalInvaders) {
            if (lastInvader.x == (frameWidth - invaderSize) || firstInvader.x == 0) {
                invader.invertX();
                invader.y += invaderSize;
            }
        }
    }

    private void checkForPlayerCollisionWithInvader() {
        Iterator<Invader> invaderIterator = globalInvaders.iterator();
        while (invaderIterator.hasNext()) {
            if (player.intersects(invaderIterator.next())) {
                mainThread.interrupt();
            }
        }
    }

    private void checkForSettingsVisible() {
        if (settingsFrameReference.isVisible()) {
            try{
                mainThread.wait();
            } catch (Exception e) {
                return;
            }
        }
    }

    private void newEnemies() {
        int nextPosX;
        int nextPosY = 0;
        for (int i = 0; i < 3; i++) {
            nextPosX = (frameWidth / 2) - ((invaderSize * 2) * spawnCountInFirstRow) / 2;
            nextPosY += 2 * invaderSize;
            for (int j = 0; j < enemiesCountInRow; j++) {
                if (j % 2 == 0) {
                    globalInvaders.add(new Invader(nextPosX + invaderSize / 2, nextPosY, invaderSize, invaderSize));
                } else {
                    globalInvaders.add(new Invader(nextPosX, nextPosY, invaderSize, invaderSize));
                }
                nextPosX = nextPosX + invaderSize * 2;
            }
        }
        firstInvader = globalInvaders.stream().min(Comparator.comparingDouble(Invader::getX)).get();
        lastInvader = globalInvaders.stream().max(Comparator.comparingDouble(Invader::getX)).get();
    }

    private void checkForWin() {
        if (globalInvaders.isEmpty()) {
            new JOptionPane().showMessageDialog(this, "You win!");
            windowReference.dispose();  
        }
    }

    private void update() {
        moveAll();
        checkForPlayerCollisionWithHostileMissile();
        checkForPlayerCollisionsWithWalls();
        checkIfInvaderShotDown();
        checkForInvaderCollisionsWithWall();
        checkForPlayerCollisionWithInvader();
        checkForWin();
        checkForSettingsVisible();
        repaint();
    }

    private void moveAll() {
        for (InvaderMissile missile : globalInvaderMissiles) {
            missile.move();
        }
        for (Missile missile : globalMissiles) {
            missile.move();
        }
        for (Invader invader : globalInvaders) {
            invader.move();
        }
    }

    public void draw(Graphics g) {
        player.draw(g);
        for (Invader invader : globalInvaders) {
            invader.draw(g);
        }
        for (InvaderMissile missile : globalInvaderMissiles) {
            missile.draw(g);
        }
        for (Missile missile : globalMissiles) {
            missile.draw(g);
        }
    }

    private void gameOver() {
        String[] options = new String[] { "Restart", "Exit" };
        int returnCode = JOptionPane.showOptionDialog(this, "Game Over", "Game has ended", JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        if (returnCode == 0) {
            globalInvaders.clear();
            globalInvaderMissiles.clear();
            globalMissiles.clear();
            player = null;
            spawnPlayer();
            newEnemies();
            mainThread = new Thread(this);
            mainThread.start();
        }
        if (returnCode == 1) {
            windowReference.dispose();
        }
    }

    @Override
    public void run() {
        new ShootingHandler();
        int gameSpeed = GameVariables.gameSpeed;
        while (true) {
            update();
            try {
                Thread.sleep(1000L / gameSpeed);
            } catch (InterruptedException e) {
                gameOver();
                repaint();
                break;
            }
        }
    }
}