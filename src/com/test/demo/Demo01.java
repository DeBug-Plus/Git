package com.test.demo;

import com.test.service.Controller;
import com.test.service.ControllerIm;

public class Demo01 {
    public static void main(String[] args) {
        Controller control = new ControllerIm();
        int[][] map = new int[][]{
                {0,0,0,0,0},
                {0,1,1,1,0},
                {0,1,0,1,0},
                {0,1,1,1,0},
                {0,0,0,0,0}
        };
        System.out.println("1111");
        control.init(map);
        System.out.println("1111");
        for (int i = 0; i < 10; i++) {
            control.nextGeneration(map);
        }
    }
}
