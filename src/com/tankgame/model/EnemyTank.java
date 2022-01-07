package com.tankgame.model;

import com.tankgame.view.MyPanel;
import com.tankgame.service.TankService;

import java.util.Vector;

/**
 * @author 小亮
 * @description 定义一个敌人坦克类
 **/
public class EnemyTank extends Tank implements Runnable{
    Vector<Bullet> bullets = new Vector<>();    // 敌方坦克的子弹
    private static final int BULLET_MAX = 2;    // 敌方坦克的最大弹容量
    private static final int BULLET_SPEED = 1;  // 敌方坦克子弹的速度
    public EnemyTank(int x, int y, int speed, Direction direction) {
        super(x, y, speed, direction, TankKind.ENEMY);
    }

    /**
     * 重写 run 方法，用于敌方坦克的随机移动以及射击
     */
    @Override
    public void run() {
        while (true) {
            switch ((int)(Math.random() * 4)) {
                case 0:
                    this.setDirection(Direction.UP);
                    break;
                case 1:
                    this.setDirection(Direction.DOWN);
                    break;
                case 2:
                    this.setDirection(Direction.RIGHT);
                    break;
                case 3:
                    this.setDirection(Direction.LEFT);
                    break;
            }
            shot();
            for (int i = 0; i < 30; i++) {
                TankService.tankMove(this, this.getDirection(), MyPanel.getWIDTH(), MyPanel.getHEIGHT());
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!this.isLive()) {
                break;
            }
        }
    }

    /**
     * 敌方坦克的射击方法
     */
    private void shot() {
        if (bullets.size() < BULLET_MAX) {
            Bullet bullet = null;
            switch (this.getDirection()) {
                case UP:
                    bullet = new Bullet(this.getX() + 10, this.getY(), BULLET_SPEED, Direction.UP);
                    break;
                case DOWN:
                    bullet = new Bullet(this.getX() + 10, this.getY() + 20, BULLET_SPEED, Direction.DOWN);
                    break;
                case LEFT:
                    bullet = new Bullet(this.getX(), this.getY() + 10, BULLET_SPEED, Direction.LEFT);
                    break;
                case RIGHT:
                    bullet = new Bullet(this.getX() + 20, this.getY() + 10, BULLET_SPEED, Direction.RIGHT);
                    break;
            }
            bullets.add(bullet);
            new Thread(bullet).start();
        }
    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }
}
