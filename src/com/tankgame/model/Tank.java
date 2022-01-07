package com.tankgame.model;

/**
 * @author 小亮
 * @description 提供一个坦克类的模板，是所有坦克的父类
 **/
public class Tank {
    private int x;                  // 表示坦克的x坐标
    private int y;                  // 表示坦克的y坐标
    private int speed;              // 表示坦克的移动速度
    private Direction direction;    // 表示坦克的方向
    private boolean isLive = true;  // 表示坦克是否存活
    private TankKind tankKind;      // 表示坦克种类

    public Tank(int x, int y, int speed, Direction direction, TankKind tankKind) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
        this.tankKind = tankKind;
    }

    /**
     * 提供以下四个方法用来坦克的移动
     */
    public void moveUp() { y -= speed; }
    public void moveDown() { y += speed; }
    public void moveLeft() { x -= speed; }
    public void moveRight() { x += speed; }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public TankKind getTankKind() {
        return tankKind;
    }
}
