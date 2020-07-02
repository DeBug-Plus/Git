package testClass;

import static org.junit.Assert.*;

import org.junit.Test;

import com.test.entity.GameMap;

public class GameMapTest {
	private static GameMap gameMap = new GameMap();
	

	@Test
	public void testAddGeneration() {
		int generation = gameMap.getGeneration()+1;
		gameMap.addGeneration();
		assertEquals(generation, gameMap.getGeneration());
	}

}
