package com.tankgame.view;

import com.tankgame.model.*;
import com.tankgame.service.TankService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author 小亮
 * @description 定义一个我的画板，用来绘制游戏界面
 **/
public class MyPanel extends JPanel implements KeyListener, Runnable {
    private static final int WIDTH = 600;        // 表示游戏界面的宽
    private static final int HEIGHT = 500;       // 表示游戏界面的高
    private MyTank myTank = null;                // 表示我方坦克
    private Vector<EnemyTank> enemyTanks = null; // 定义一个敌方坦克的集合

    public MyPanel() {
        myTank = TankService.getMyTank();
        enemyTanks = TankService.getEnemies();
    }

    /**
     * 重写 paint 方法，用来绘制游戏界面中的事物
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        TankService.isHit(enemyTanks, myTank);
        TankService.isHit(myTank.getBullets(), enemyTanks);
        if (myTank.isLive() && !enemyTanks.isEmpty()) {
            drawTank(myTank, g);
            drawBullet(myTank.getBullets(), g);
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive()) {
                    drawTank(enemyTank, g);
                    drawBullet(enemyTank.getBullets(), g);
                } else {
                    enemyTanks.remove(i);
                    i--;
                }
            }
        } else {
            System.out.println("GAME OVER");
            System.exit(0);
        }
    }

    /**
     * 用于绘制子弹
     * @param bullets 表示需要绘制的子弹集合
     * @param g 画笔
     */
    private void drawBullet(Vector<Bullet> bullets, Graphics g) {
        if (bullets != null && !bullets.isEmpty()) {
            Bullet bullet = null;
            for (int i = 0; i < bullets.size(); i++) {
                bullet = bullets.get(i);
                if (bullet.isLive()) {
                    g.setColor(Color.gray);
                    g.draw3DRect(bullet.getX(), bullet.getY(), 1, 1, false);
                } else {
                    bullets.remove(i);
                    i--;
                }
            }
        }
    }

    /**
     * 用于绘制坦克
     * @param tank 表示需要绘制的坦克
     * @param g 画笔
     */
    private void drawTank(Tank tank, Graphics g) {
        int x = tank.getX();
        int y = tank.getY();
        TankKind tankKind = tank.getTankKind();
        Direction direction = tank.getDirection();
        switch (tankKind) {
            case ENEMY:
                g.setColor(Color.cyan);
                break;
            case MY_TANK:
                g.setColor(Color.yellow);
                break;
        }
        switch (direction) {
            case UP:
                g.drawLine(x + 10, y, x + 10, y + 10);
                g.fill3DRect(x, y, 5, 20, false);
                g.fill3DRect(x + 15, y, 5, 20, false);
                g.fill3DRect(x + 5, y + 5, 10, 10, false);
                g.fillOval(x + 5, y + 5, 10, 10);
                break;
            case DOWN:
                g.drawLine(x + 10, y + 20, x + 10, y + 10);
                g.fill3DRect(x, y, 5, 20, false);
                g.fill3DRect(x + 15, y, 5, 20, false);
                g.fill3DRect(x + 5, y + 5, 10, 10, false);
                g.fillOval(x + 5, y + 5, 10, 10);
                break;
            case LEFT:
                g.drawLine(x, y + 10, x + 10, y + 10);
                g.fill3DRect(x, y, 20, 5, false);
                g.fill3DRect(x, y + 15, 20, 5, false);
                g.fill3DRect(x + 5, y + 5, 10, 10, false);
                g.fillOval(x + 5, y + 5, 10, 10);
                break;
            case RIGHT:
                g.drawLine(x + 20, y + 10, x + 10, y + 10);
                g.fill3DRect(x, y, 20, 5, false);
                g.fill3DRect(x, y + 15, 20, 5, false);
                g.fill3DRect(x + 5, y + 5, 10, 10, false);
                g.fillOval(x + 5, y + 5, 10, 10);
                break;
        }
    }


    /**
     * 监控画板上的事件，判断并完成我方坦克的移动，射击
     * @param e 监听的事件
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            TankService.tankMove(myTank, Direction.UP, WIDTH, HEIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            TankService.tankMove(myTank, Direction.DOWN, WIDTH, HEIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            TankService.tankMove(myTank, Direction.LEFT, WIDTH, HEIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            TankService.tankMove(myTank, Direction.RIGHT, WIDTH, HEIGHT);
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            myTank.shot();
        }
        this.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * 重新 run 方法，每隔100毫秒重新绘制画板
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }
}
