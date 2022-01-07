package com.tankgame;

import com.tankgame.view.MyPanel;

import javax.swing.*;

/**
 * @author 小亮
 * @description 用来启动并显式游戏界面
 **/
@SuppressWarnings({"all"})
public class TankGame extends JFrame {

    private static final int WIDTH = 800;   // 表示界面的宽
    private static final int HEIGHT = 600;  // 表示界面的高

    public static void main(String[] args) {
        TankGame tankGame = new TankGame();
    }

    public TankGame() {
        MyPanel myPanel = new MyPanel();
        new Thread(myPanel).start();
        this.setBounds(0, 0, WIDTH, HEIGHT);
        this.add(myPanel);
        this.addKeyListener(myPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setVisible(true);
    }
}
