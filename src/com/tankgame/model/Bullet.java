package com.tankgame.model;

import com.tankgame.view.MyPanel;

/**
 * @author 小亮
 * @description 子弹类，表示坦克射出的子弹
 **/
public class Bullet implements Runnable{
    private int x;                  // 表示子弹的x坐标
    private int y;                  // 表示子弹的y坐标
    private boolean isLive = true;  // 表示子弹是否存活
    private int speed;              // 表示子弹的移动速度
    private Direction direction;    // 表示子弹的移动方向

    public Bullet(int x, int y, int speed, Direction direction) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
    }

    /**
     * 子弹的移动方法
     */
    private void move() {
        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    /**
     * 重写 run 方法，用于子弹的移动
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (isLive && y < MyPanel.getHEIGHT() && x < MyPanel.getWIDTH() && x > 0 && y > 0) {
                move();
            } else {
                isLive = false;
                break;
            }
        }
    }
}
