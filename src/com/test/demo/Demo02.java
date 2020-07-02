package com.test.demo;

import com.test.ui.LifeGameUI;

public class Demo02 {
    public static void main(String[] args) {

        //实现length，Width动态改变功能

        //速度改变功能。
        Thread Runnable = new Thread(new Runnable() {
            @Override
            public void run() {
                //实例化UI
                LifeGameUI game = new LifeGame("LifeGame");
            }
        });
        Runnable.start();
    }
}
