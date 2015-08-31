package logic.map;

import java.io.Serializable;

import logic.types.BlockType;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
public class MapBlock implements Serializable
{
	private static final long serialVersionUID = -2822005321532561784L;
	private boolean isWalkableByWorker;
	private boolean isPassableByBoat;
	private boolean isWalkableByMilitary;
	private boolean isBuildingThere;

	private BlockType blockType;

	private int gold;
	private int stone;
	private int lumber;
	private int food;

	private String ID;

	/**
	 * @param blockType
	 *            a BlockType enumeration indicating whether a block is WATER,
	 *            PLAIN, FOREST, or MOUNTAIN
	 * @param goldMap
	 *            an integer indicating number of golds in the block
	 * @param stoneMap
	 *            an integer indicating number of stones in the block
	 * @param lumberMap
	 *            an integer indicating number of lumbers in the block
	 * @param foodMap
	 *            an integer indicating number of foods in the block
	 */
	public MapBlock(String ID, BlockType blockType, int goldMap, int stoneMap,
			int lumberMap, int foodMap)
	{
		this.ID = ID;
		gold = goldMap;
		stone = stoneMap;
		lumber = lumberMap;
		food = foodMap;
		this.blockType = blockType;
		this.isBuildingThere = false;

		if (blockType.equals(BlockType.MOUNTAIN))
		{
			isWalkableByWorker = false;
			isPassableByBoat = false;
			isWalkableByMilitary = false;
		} else if (blockType.equals(BlockType.WATER))
		{
			isWalkableByWorker = false;
			isPassableByBoat = true;
			isWalkableByMilitary = false;
		} else if (blockType.equals(BlockType.PLAIN))
		{
			isWalkableByWorker = true;
			isPassableByBoat = false;
			isWalkableByMilitary = true;
		} else if (blockType.equals(BlockType.FOREST))
		{
			isWalkableByWorker = true;
			isPassableByBoat = false;
			isWalkableByMilitary = true;
		}
	}

	/**
	 * 
	 * @return an boolean saying whether the block is passable by a boat or not
	 */
	public boolean isPassableByBoat()
	{
		return isPassableByBoat;
	}

	/**
	 * @return a BlockType
	 */
	public BlockType getBlockType()
	{
		return blockType;
	}

	/**
	 * 
	 * @return an boolean saying whether the block is walkable by a worker or
	 *         not
	 */
	public boolean isWalkableByWorker()
	{
		return isWalkableByWorker;
	}

	/**
	 * @return an integer showing the number of golds in the block
	 */
	public int getGold()
	{
		return gold;
	}

	/**
	 * @return an integer showing the number of lumbers in the block
	 */
	public int getLumber()
	{
		return lumber;
	}

	/**
	 * @return an integer showing the number of stones in the block
	 */
	public int getStone()
	{
		return stone;
	}

	/**
	 * @return an integer showing the number of foods in the block
	 */
	public int getFood()
	{
		return food;
	}

	/**
	 * set the status of a block - is walkable by worker or not -
	 * 
	 * @param isWalkableByWorker
	 *            new status
	 */
	public void setWalkableByWorker(boolean isWalkableByWorker)
	{
		this.isWalkableByWorker = isWalkableByWorker;
	}

	/**
	 * set the status of a block - is pass-able by a boat or not -
	 * 
	 * @param isPassableByBoat
	 *            new status
	 */
	public void setPassableByBoat(boolean isPassableByBoat)
	{
		this.isPassableByBoat = isPassableByBoat;
	}

	public void setWalkableByMilitary(boolean isWalkableByMilitary)
	{
		this.isWalkableByMilitary = isWalkableByMilitary;
	}

	public boolean isWalkableByMilitary()
	{
		return isWalkableByMilitary;
	}

	public String getID()
	{
		return ID;
	}

	public boolean isBuildingThere()
	{
		return isBuildingThere;
	}

	public void setBuildingThere(boolean flag)
	{
		isBuildingThere = flag;
	}

}
