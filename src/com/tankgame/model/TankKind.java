package com.tankgame.model;

/**
 * @author 小亮
 * @description 定义一个枚举类，用来表示坦克的种类
 **/
public enum TankKind {
    ENEMY("敌方坦克"), MY_TANK("我方坦克");
    private String describe;    // 简单的描述

    TankKind(String describe) {
        this.describe = describe;
    }
}
