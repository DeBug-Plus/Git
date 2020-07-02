package com.test.service;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2020/05/14 16:06 <br>
 * @see com.test.entity <br>
 */
public interface Controller {
    public int getNeighborNum(int[][] curMap,int x,int y);//获得九宫格内存活数
    public void nextGeneration(int[][] Map);//获得下一代
    public void init(int[][] map);
    public void copyMap(int[][] map);
    public int getGeneration();
    public void resetGeneration();
}
