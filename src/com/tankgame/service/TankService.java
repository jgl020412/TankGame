package com.tankgame.service;

import com.tankgame.model.*;

import java.util.Vector;

/**
 * @author 小亮
 * @description 此类用来表示坦克之间的交互以及坦克的初始化的工具类
 **/
public class TankService {
    private static final int X_MY_TANK = 300;           // 表示我方坦克的初始的X坐标
    private static final int Y_MY_TANK = 450;           // 表示我方坦克的初始的Y坐标
    private static final int SPEED_MY_TANK = 3;         // 表示我方坦克的初始速度
    private static final int X_ENEMY_TANK = 0;          // 表示第一个敌方坦克的初始的X坐标
    private static final int Y_ENEMY_TANK = 0;          // 表示第一个敌方坦克的初始的Y坐标
    private static final int INTERVAL_TANK = 150;       // 表示敌方坦克初始化是的间隔
    private static final int ENEMY_MAX = 4;             // 表示敌方坦克的最大数量
    private static final int ENEMY_SPEED = 1;           // 表示敌方坦克的速度

    /**
     * 用于获取我方坦克的实例对象
     * @return 我方坦克
     */
    public static MyTank getMyTank() {
        MyTank myTank = new MyTank(X_MY_TANK, Y_MY_TANK, SPEED_MY_TANK, Direction.UP);
        return myTank;
    }

    /**
     * 用于获取敌方坦克类的实例对象集合
     * @return 敌方坦克集合
     */
    public static Vector<EnemyTank> getEnemies() {
        Vector<EnemyTank> enemyTanks = new Vector<>();
        for (int i = 0; i < ENEMY_MAX; i++) {
            EnemyTank enemyTank = new EnemyTank((X_ENEMY_TANK + i * INTERVAL_TANK), Y_ENEMY_TANK, ENEMY_SPEED, Direction.DOWN);
            enemyTanks.add(enemyTank);
            new Thread(enemyTank).start();
        }
        return enemyTanks;
    }

    /**
     * 根据坦克的方向以及活动区域，完成坦克的移动
     */
    public static void tankMove(Tank tank, Direction direction, int width, int height) {
        int x = tank.getX();
        int y = tank.getY();
        tank.setDirection(direction);
        switch (direction) {
            case UP:
                if (y > 0) tank.moveUp();
                break;
            case DOWN:
                if (y + 20 < height) tank.moveDown();
                break;
            case LEFT:
                if (x > 0) tank.moveLeft();
                break;
            case RIGHT:
                if (x + 20 < width) tank.moveRight();
                break;
        }
    }

    /**
     * 用于判断敌方坦克是否被击中
     * @param bullets 我方坦克子弹的集合
     * @param enemyTanks 敌方坦克的集合
     */
    public static void isHit(Vector<Bullet> bullets, Vector<EnemyTank> enemyTanks) {
        for (int i = 0; i < enemyTanks.size(); i++) {
            hitTank(bullets, enemyTanks.get(i));
        }
    }

    /**
     * 用于判断我方坦克是否被击中
     * @param enemyTanks 敌方坦克的集合
     * @param tank 我方坦克
     */
    public static void isHit(Vector<EnemyTank> enemyTanks, Tank tank) {
        for (int i = 0; i < enemyTanks.size(); i++) {
            hitTank(enemyTanks.get(i).getBullets(), tank);
        }
    }

    /**
     * 用于判断坦克是否被击中
     * @param bullets 子弹集合
     * @param tank 坦克
     */
    private static void hitTank(Vector<Bullet> bullets, Tank tank) {
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).isLive() && tank.isLive() &&
                    bullets.get(i).getX() >= tank.getX() &&
                    bullets.get(i).getX() <= (tank.getX() + 20) &&
                    bullets.get(i).getY() >= tank.getY() &&
                    bullets.get(i).getY() <= (tank.getY() + 20)) {
                tank.setLive(false);
                bullets.get(i).setLive(false);
                break;
            }
        }
    }

}
