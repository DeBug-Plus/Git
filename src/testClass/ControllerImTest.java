package testClass;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.test.entity.GameMap;
import com.test.service.ControllerIm;

public class ControllerImTest {

	//private static GameMap gameMap = new GameMap();
	private static ControllerIm controller = new ControllerIm();
	private int[][] map = new int[][] {
		{0,0,0,0,0,0,0,0,0,0,0}
	   ,{0,0,0,0,0,0,0,0,0,0,0}
	   ,{0,0,0,0,0,0,0,0,0,0,0}
	   ,{0,0,0,0,0,0,0,0,0,0,0}
	   ,{0,0,0,0,1,1,1,0,0,0,0}
	   ,{0,0,0,0,1,0,1,0,0,0,0}
	   ,{0,0,0,0,1,1,1,0,0,0,0}
	   ,{0,0,0,0,0,0,0,0,0,0,0}
	   ,{0,0,0,0,0,0,0,0,0,0,0}
	   ,{0,0,0,0,0,0,0,0,0,0,0}
	   ,{0,0,0,0,0,0,0,0,0,0,0}
	   
	};
	
	@Before
	public void setUp() throws Exception {
		//每次测试前将map初始化
		controller.init(map);
	}

	@Test
	public void testGetNeighborNum() {
		assertEquals(8,controller.getNeighborNum(controller.getGameMap(), 5, 5));
	}
	
	@Test
	public void testNextGeneration() {
		int[][] nextMap = new int[][] {
			    {0,0,0,0,0,0,0,0,0,0,0}
			   ,{0,0,0,0,0,0,0,0,0,0,0}
			   ,{0,0,0,0,0,0,0,0,0,0,0}
			   ,{0,0,0,0,0,1,0,0,0,0,0}
			   ,{0,0,0,0,1,0,1,0,0,0,0}
			   ,{0,0,0,1,0,0,0,1,0,0,0}
			   ,{0,0,0,0,1,0,1,0,0,0,0}
			   ,{0,0,0,0,0,1,0,0,0,0,0}
			   ,{0,0,0,0,0,0,0,0,0,0,0}
			   ,{0,0,0,0,0,0,0,0,0,0,0}
			   ,{0,0,0,0,0,0,0,0,0,0,0}
		};
		
		controller.nextGeneration(map);
		for(int i=0;i<map.length;i++) {
			for(int j =0;j<map[0].length;j++) {
				assertEquals(nextMap[i][j],controller.getGameMap()[i][j]);
			}
		}
		
	}

	@Test
	public void testCopyMap() {
		controller.copyMap(map);
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				assertEquals(map[i][j],controller.getGameMap()[i][j]);
			}
		}
	}
}
