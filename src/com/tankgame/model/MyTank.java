package com.tankgame.model;

import java.util.Vector;

/**
 * @author 小亮
 * @description 我方的坦克
 **/
public class MyTank extends Tank {

    private static int bulletMax = 5;               // 表示子弹的最大数量
    private static int bulletSpeed = 1;             // 表示子弹的移动速度
    private Vector<Bullet> bullets;                 // 表示该坦克的子弹

    public MyTank(int x, int y, int speed, Direction direction) {
        super(x, y, speed, direction, TankKind.MY_TANK);
        bullets = new Vector<>();
    }


    /**
     * 我方坦克的射击方法
     */
    public void shot() {
        if (bullets.size() < bulletMax) {
            Bullet bullet = null;
            switch (this.getDirection()) {
                case UP:
                    bullet = new Bullet(this.getX() + 10, this.getY(), bulletSpeed, Direction.UP);
                    break;
                case DOWN:
                    bullet = new Bullet(this.getX() + 10, this.getY() + 20, bulletSpeed, Direction.DOWN);
                    break;
                case LEFT:
                    bullet = new Bullet(this.getX(), this.getY() + 10, bulletSpeed, Direction.LEFT);
                    break;
                case RIGHT:
                    bullet = new Bullet(this.getX() + 20, this.getY() + 10, bulletSpeed, Direction.RIGHT);
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
