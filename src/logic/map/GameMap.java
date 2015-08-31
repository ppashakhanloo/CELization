package logic.map;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

import logic.types.BlockType;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
public class GameMap implements Serializable
{
	private static final long serialVersionUID = -4331009154438108341L;

	private MapBlock[][] mapBlocks;

	/**
	 * @return a 2d array of map blocks which keep the elements of the whole map
	 */
	public MapBlock[][] getMapBlocks()
	{
		return mapBlocks;
	}

	/**
	 * Generate a map based on parameters
	 * 
	 * @param map
	 *            a 2d array of characters {'P', 'F', 'J', 'M'} containing the
	 *            types of blocks
	 * @param goldMap
	 *            a 2d array of integers containing the number of golds in
	 *            blocks
	 * @param stoneMap
	 *            a 2d array of integers containing the number of stones in
	 *            blocks
	 * @param lumberMap
	 *            a 2d array of integers containing the number of lumbers in
	 *            blocks
	 * @param foodMap
	 *            a 2d array of integers containing the number of foods in
	 *            blocks
	 */
	@SuppressWarnings("serial")
	public void generateMap(char[][] map, int[][] goldMap, int[][] stoneMap,
			int[][] lumberMap, int[][] foodMap)
	{
		mapBlocks = new MapBlock[map.length][map[0].length];
		java.util.Map<Character, BlockType> charToBlockType = new HashMap<Character, BlockType>()
		{
			{
				put('P', BlockType.PLAIN);
				put('F', BlockType.FOREST);
				put('M', BlockType.MOUNTAIN);
				put('W', BlockType.WATER);
			}
		};
		int counter = 0;
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[0].length; j++)
			{
				mapBlocks[i][j] = new MapBlock("block"
						+ Integer.toString(counter),
						charToBlockType.get(map[i][j]), goldMap[i][j],
						stoneMap[i][j], lumberMap[i][j], foodMap[i][j]);
				counter++;
			}
		}
	}

	/**
	 * generate a random map by being called
	 * 
	 * @param rows
	 *            of the game map
	 * @param columns
	 *            of the game map
	 */
	@SuppressWarnings("serial")
	public void generateRandomMap(int row, int column)
	{
		char[][] map = new char[row][column];
		int[][] goldMap = new int[row][column];
		int[][] stoneMap = new int[row][column];
		int[][] lumberMap = new int[row][column];
		int[][] foodMap = new int[row][column];

		for (int i = 0; i < row; i++)
		{
			for (int j = 0; j < column; j++)
			{
				Random randomNumber = new Random();
				int randomCharInt = randomNumber.nextInt(4);
				switch (randomCharInt)
				{
					case 0:
						map[i][j] = 'F';
						stoneMap[i][j] = 0;
						goldMap[i][j] = 0;
						foodMap[i][j] = 0;
						lumberMap[i][j] = randomNumber.nextInt(9) + 1;
						break;
					case 1:
						map[i][j] = 'M';
						stoneMap[i][j] = 1 + randomNumber.nextInt(9);
						goldMap[i][j] = 1 + randomNumber.nextInt(9);
						foodMap[i][j] = 0;
						lumberMap[i][j] = 0;
						break;
					case 2:
						map[i][j] = 'W';
						stoneMap[i][j] = 0;
						goldMap[i][j] = 0;
						foodMap[i][j] = 1 + randomNumber.nextInt(9);
						lumberMap[i][j] = 0;
						break;
					case 3:
						map[i][j] = 'P';
						stoneMap[i][j] = 0;
						goldMap[i][j] = 0;
						foodMap[i][j] = randomNumber.nextInt(10);
						lumberMap[i][j] = 0;
				}

			}
		}

		mapBlocks = new MapBlock[map.length][map[0].length];
		java.util.Map<Character, BlockType> charToBlockType = new HashMap<Character, BlockType>()
		{
			{
				put('P', BlockType.PLAIN);
				put('F', BlockType.FOREST);
				put('M', BlockType.MOUNTAIN);
				put('W', BlockType.WATER);
			}
		};
		int counter = 0;
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[0].length; j++)
			{
				mapBlocks[i][j] = new MapBlock("block"
						+ Integer.toString(counter),
						charToBlockType.get(map[i][j]), goldMap[i][j],
						stoneMap[i][j], lumberMap[i][j], foodMap[i][j]);
				counter++;
			}
		}

	}

	/**
	 * GameMap toString method
	 * 
	 * @return a string showing map block types in a two dimensional shape
	 */
	@Override
	public String toString()
	{
		String result = "";
		for (int i = 0; i < mapBlocks.length; i++)
		{
			for (int j = 0; j < mapBlocks[0].length; j++)
			{
				result += mapBlocks[i][j].getBlockType() + " ";
			}
			result += "\n";
		}
		return result;
	}
}
