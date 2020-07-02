package com.test.entity;

import javax.management.monitor.GaugeMonitor;

/**
 * <Description> <br>
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2020/05/14 16:18 <br>
 * @see com.test.entity <br>
 */
public class GameMap{
    private int[][] curMap;
    private int length;
    private int width;
    private static int generation = 0;

    public GameMap(){
        this.length = 0;
        this.width = 0;
    }
    public GameMap(int[][] map){
        this.length = map.length;
        this.width = map[0].length;
        this.curMap = new int[this.length][this.width];
        setCurMap(map);
    }

    public int[][] getCurMap() {
        return curMap;
    }

    public void setCurMap(int[][] Map) {
        for (int i = 0; i < this.length; i++) {
            for (int j = 0;j < this.width; j++) {
                this.curMap[i][j] = Map[i][j];
            }
        }
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getGeneration(){
        return this.generation;
    }
    public void addGeneration(){
        this.generation++;
    }
    public void setGeneration(){
        this.generation = 0;
    }
}
