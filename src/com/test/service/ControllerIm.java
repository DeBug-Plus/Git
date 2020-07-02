package com.test.service;

import com.test.entity.GameMap;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2020/05/14 16:22 <br>
 * @see com.test.entity <br>
 */
public class ControllerIm implements Controller {
    private GameMap gameMap;  //游戏地图

    //private int ALIVE = 1;
    //private int DEATH = 0;
    //移动
    private int[] dx = { -1,-1,-1,0,1,1,1,0 };
    private int[] dy = { -1,0,1,1,1,0,-1,-1 };

    public int getNeighborNum(int[][] curMap,int x,int y){
        int count = 0;
        for (int i = 0; i < 8; i++)
        {
        	//计算周围生存多少细胞，考虑了边界
            int nx = x + dx[i], ny = y + dy[i];
            if (nx >= 0 && nx < gameMap.getLength() && ny >= 0 && ny < gameMap.getWidth() && curMap[nx][ny] == 1)
                count++;
        }
        return count;
    }

    public void nextGeneration(int[][] Map){
         for (int i = 0; i < gameMap.getLength(); i++) {
             for (int j = 0; j < gameMap.getWidth(); j++) {
                 int cnt = getNeighborNum(Map, i, j);
                 switch (cnt) {
                     case 2:
                         continue;
                     case 3:
                         gameMap.getCurMap()[i][j] = 1;
                         break;
                     default:
                         gameMap.getCurMap()[i][j] = 0;
                         break;
                 }
             }
         }
         gameMap.addGeneration();
         copyMap(Map);
    }

    public void init(int[][] map) {
        this.gameMap = new GameMap(map);
    }

    public void copyMap(int[][] map){
        for (int i = 0; i < gameMap.getLength(); i++) {
            for (int j = 0; j < gameMap.getWidth(); j++) {
                map[i][j] = gameMap.getCurMap()[i][j];
            }
        }
    }
    public int getGeneration(){
        return gameMap.getGeneration();
    }
    public void resetGeneration(){
        gameMap.setGeneration();
    }
    
    public int[][] getGameMap(){
    	return this.gameMap.getCurMap();
    }
    
}
