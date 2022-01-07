package com.tankgame.model;

/**
 * @author 小亮
 * @description 定义一个方向的枚举类，包括上、下、左、右
 **/
public enum Direction {
    UP("上"), DOWN("下"), LEFT("左"), RIGHT("右");
    private String describe;    // 简单的描述

    Direction(String describe) {
        this.describe = describe;
    }
}
