package logic.actionController;

import logic.actions.BuildAction;
import logic.actions.MoveAction;
import logic.agents.workers.Worker;
import logic.buildings.Building;
import logic.game.Player;
import logic.types.BlockType;
import logic.types.ObjectType;
import logic.types.OccupationType;
import logic.utilities.Point;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
public class BuildController extends ActionController
{
	private int remainingTurns;
	private ObjectType buildType;
	private Point buildPoint;
	private boolean isBeginingOfTheConstructing;
	private Worker myWorker;
	private boolean abilityToBuild;

	/**
	 * 
	 * @param myPlayer
	 * @param buildType
	 * @param myWorker
	 * @param buildPoint
	 */
	public BuildController(Player myPlayer, ObjectType buildType,
			Worker myWorker, Point buildPoint)
	{
		super(myPlayer);
		remainingTurns = getMyPlayer().getPlayerInfo().getTurnsToBuild()
				.get(buildType)
				- (int) Math.ceil((myWorker.getBuildingExperience() * 2));

		this.buildType = buildType;
		this.buildPoint = buildPoint;
		this.myWorker = myWorker;
		this.abilityToBuild = false;
		this.isBeginingOfTheConstructing = true;
		this.myWorker.setOccupationType(OccupationType.CONSTRUCTING);
		this.myWorker.setActionController(this);

		abilityToBuild = (isResourcesEnough() && isLocationValid()
				&& isResearchesEnough() && isCapableOfBuilding() && isNumberOfThisBuildingAvailable());
		if (abilityToBuild)
			decreaseResources();
		else
		{
			myWorker.setOccupationType(OccupationType.IDLE);
			myWorker.setActionController(null);
		}
	}

	/**
	 * Call the next turn of the BUILD
	 */
	@Override
	public void nextTurn()
	{
		if (doIMove())
		{
			Point dest = getMoveDestination();
			if (dest == null)
				System.err
						.println("BuildController class::nextTurn(): move-destination is null!!");
			else
			{
				MoveAction move = new MoveAction(getMyPlayer(), myWorker, dest);
				move.doAction();
			}
		} else
		{
			myWorker.setBuildingExperience(myWorker.getBuildingExperience() + 0.01);
			remainingTurns--;
			if (isBeginingOfTheConstructing)
			{
				for (int i = buildPoint.getX(); i < buildPoint.getX()
						+ Integer.valueOf(getMyPlayer().getPlayerInfo()
								.getBuildingSize().get(buildType)); i++)
				{
					for (int j = buildPoint.getY(); j < buildPoint.getY()
							+ Integer.valueOf(getMyPlayer().getPlayerInfo()
									.getBuildingSize().get(buildType)); j++)
					{
						try
						{
							getMyPlayer().getMyMap().getMapBlocks()[i][j]
									.setWalkableByWorker(false);
							getMyPlayer().getMyMap().getMapBlocks()[i][j]
									.setWalkableByMilitary(false);
							getMyPlayer().getMyMap().getMapBlocks()[i][j]
									.setPassableByBoat(false);
						} catch (Exception e)
						{
							System.err.println("OUT OF ARRAY OCCURED!!!");
						}
					}
				}
				decreaseResources();
				isBeginingOfTheConstructing = false;

			} else if (remainingTurns == 0)
			{
				BuildAction buildAction = new BuildAction(buildType,
						getMyPlayer(), buildPoint);
				buildAction.doAction();

				setBuildingThere();

				myWorker.setOccupationType(OccupationType.IDLE);
				myWorker.setActionController(null);
				getMyPlayer().getDoneActions().add(
						"[A " + buildType + " IS BUILD IN " + buildPoint + "]");
			}
		}

	}

	private void setBuildingThere()
	{
		for (int i = 0; i < getMyPlayer().getPlayerInfo().getBuildingSize()
				.get(buildType); i++)
		{
			for (int j = 0; j < getMyPlayer().getPlayerInfo().getBuildingSize()
					.get(buildType); j++)
			{
				getMyPlayer().getMyMap().getMapBlocks()[i + buildPoint.getX()][j
						+ buildPoint.getY()].setBuildingThere(true);
			}
		}
	}

	/**
	 * show if the number of MainBuilding, Marker, and University is at last
	 * equal to one
	 * 
	 * @return a boolean showing whether the player can build from this building
	 *         anymore or not
	 */
	private boolean isNumberOfThisBuildingAvailable()
	{
		if ((buildType == ObjectType.MAIN_BUILDING && getMyPlayer()
				.getMainBuildings().size() == 0)
				|| (buildType == ObjectType.UNIVERSITY && getMyPlayer()
						.getMarkets().size() == 0))
			return true;
		return false;
	}

	/**
	 * enable the worker to smartly choose the point in which it can settle and
	 * start constructing
	 * 
	 * @return a boolean showing the point in which worker must start
	 *         constructing
	 */
	@SuppressWarnings("unused")
	private Point getMoveDestination(Building b)
	{
		ObjectType buildType = b.getObjectType();
		Point workPoint = b.getPosition();
		for (int i = workPoint.getX(); i < workPoint.getX()
				+ getMyPlayer().getPlayerInfo().getBuildingSize()
						.get(buildType) - 1; i++)
		{
			Point p1 = new Point(i, workPoint.getY() - 1);
			if (p1.getY() < getMyPlayer().getMyMap().getMapBlocks()[0].length
					&& (getMyPlayer().getMyMap().getMapBlocks()[i][p1.getY()]
							.isWalkableByWorker() || (!getMyPlayer()
							.getMapVisiblity()[i][p1.getY()])))
				return p1;
			Point p2 = new Point(i, workPoint.getY()
					+ getMyPlayer().getPlayerInfo().getBuildingSize()
							.get(buildType));
			if ((p2.getY() < getMyPlayer().getMyMap().getMapBlocks()[0].length)
					&& (getMyPlayer().getMyMap().getMapBlocks()[i][p2.getY()]
							.isWalkableByWorker() || (!getMyPlayer()
							.getMapVisiblity()[i][p2.getY()])))
				return p2;

		}
		for (int j = workPoint.getY(); j < workPoint.getY()
				+ getMyPlayer().getPlayerInfo().getBuildingSize()
						.get(buildType); j++)
		{
			Point p1 = new Point(workPoint.getX() - 1, j);
			if (p1.getX() < getMyPlayer().getMyMap().getMapBlocks().length
					&& (getMyPlayer().getMyMap().getMapBlocks()[p1.getX()][j]
							.isWalkableByWorker() || (!getMyPlayer()
							.getMapVisiblity()[p1.getX()][j])))
				return p1;
			Point p2 = new Point(workPoint.getX()
					+ getMyPlayer().getPlayerInfo().getBuildingSize()
							.get(buildType), j);
			if (p2.getX() < getMyPlayer().getMyMap().getMapBlocks().length
					&& (getMyPlayer().getMyMap().getMapBlocks()[p2.getX()][j]
							.isWalkableByWorker() || (!getMyPlayer()
							.getMapVisiblity()[p2.getX()][j])))
				return p2;
		}
		return null;
	}

	private Point getMoveDestination()
	{
		Point workPoint = buildPoint;
		for (int i = workPoint.getX(); i < workPoint.getX()
				+ getMyPlayer().getPlayerInfo().getBuildingSize()
						.get(buildType) - 1; i++)
		{
			Point p1 = new Point(i, workPoint.getY() - 1);
			if (p1.getY() >= 0
					&& (getMyPlayer().getMyMap().getMapBlocks()[i][p1.getY()]
							.isWalkableByWorker() || (!getMyPlayer()
							.getMapVisiblity()[i][p1.getY()])))
				return p1;
			Point p2 = new Point(i, workPoint.getY()
					+ getMyPlayer().getPlayerInfo().getBuildingSize()
							.get(buildType));
			if ((p2.getY() < getMyPlayer().getMyMap().getMapBlocks()[0].length)
					&& (getMyPlayer().getMyMap().getMapBlocks()[i][p2.getY()]
							.isWalkableByWorker() || (!getMyPlayer()
							.getMapVisiblity()[i][p2.getY()])))
				return p2;

		}
		for (int j = workPoint.getY(); j < workPoint.getY()
				+ getMyPlayer().getPlayerInfo().getBuildingSize()
						.get(buildType); j++)
		{
			Point p1 = new Point(workPoint.getX() - 1, j);
			if (p1.getX() >= 0
					&& (getMyPlayer().getMyMap().getMapBlocks()[p1.getX()][j]
							.isWalkableByWorker() || (!getMyPlayer()
							.getMapVisiblity()[p1.getX()][j])))
				return p1;
			Point p2 = new Point(workPoint.getX()
					+ getMyPlayer().getPlayerInfo().getBuildingSize()
							.get(buildType), j);
			if (p2.getX() < getMyPlayer().getMyMap().getMapBlocks().length
					&& (getMyPlayer().getMyMap().getMapBlocks()[p2.getX()][j]
							.isWalkableByWorker() || (!getMyPlayer()
							.getMapVisiblity()[p2.getX()][j])))
				return p2;

		}
		return null;
	}

	/**
	 * tell if the worker need to still move or not
	 * 
	 * @return a boolean showing the worker has not reached to build place yet:
	 *         so it should still move
	 */

	private boolean doIMove()
	{
		ObjectType type = buildType;
		for (int i = buildPoint.getX(); i < buildPoint.getX()
				+ getMyPlayer().getPlayerInfo().getBuildingSize().get(type) - 1; i++)
		{
			if ((myWorker.getPosition().getX() == i && buildPoint.getY() - 1 == myWorker
					.getPosition().getY())
					|| (myWorker.getPosition().getX() == i && buildPoint.getY()
							+ getMyPlayer().getPlayerInfo().getBuildingSize()
									.get(type) == myWorker.getPosition().getY()))
				return false;
		}

		for (int i = buildPoint.getY(); i < buildPoint.getY()
				+ getMyPlayer().getPlayerInfo().getBuildingSize().get(type) - 1; i++)
		{
			if ((myWorker.getPosition().getY() == i && buildPoint.getX() - 1 == myWorker
					.getPosition().getX())
					|| (myWorker.getPosition().getY() == i && buildPoint.getX()
							+ getMyPlayer().getPlayerInfo().getBuildingSize()
									.get(type) == myWorker.getPosition().getX()))
				return false;
		}
		return true;
	}

	/**
	 * decrease resources after commanding to build a building
	 */
	private void decreaseResources()
	{
		getMyPlayer()
				.getPlayerInfo()
				.getGoldInStock()
				.removeFrom(
						getMyPlayer().getPlayerInfo().getNeededResources()
								.get(buildType)[0]);
		getMyPlayer()
				.getPlayerInfo()
				.getStoneInStock()
				.removeFrom(
						getMyPlayer().getPlayerInfo().getNeededResources()
								.get(buildType)[1]);
		getMyPlayer()
				.getPlayerInfo()
				.getLumberInStock()
				.removeFrom(
						getMyPlayer().getPlayerInfo().getNeededResources()
								.get(buildType)[2]);
	}

	/**
	 * show whether the player is able to build the building or not
	 * 
	 * @return a boolean showing whether the worker is able to build the
	 *         building or not
	 */
	private boolean isCapableOfBuilding()
	{
		switch (buildType)
		{
			case FARM:
				if (getMyPlayer().getPlayerInfo().isAbilityToBuildFarm())
					return true;
				break;
			case GOLD_MINE:
				if (getMyPlayer().getPlayerInfo().isAbilityToBuildGoldMine())
					return true;
				break;
			case MAIN_BUILDING:
				if (getMyPlayer().getPlayerInfo()
						.isAbilityToBuildMainBuilding())
					return true;
				break;
			case MARKET:
				if (getMyPlayer().getPlayerInfo().isAbilityToBuildMarket())
					return true;
				break;
			case PORT:
				if (getMyPlayer().getPlayerInfo().isAbilityToBuildPort())
					return true;
				break;
			case STOCKPILE:
				if (getMyPlayer().getPlayerInfo().isAbilityToBuildStockpile())
					return true;
				break;
			case STONE_MINE:
				if (getMyPlayer().getPlayerInfo().isAbilityToBuildStoneMine())
					return true;
				break;
			case UNIVERSITY:
				if (getMyPlayer().getPlayerInfo().isAbilityToBuildUniversity())
					return true;
				break;
			case WOOD_CAMP:
				if (getMyPlayer().getPlayerInfo().isAbilityToBuildWoodCamp())
					return true;
				break;
		}
		return false;
	}

	/**
	 * show whether needed or optional researches are finished or not
	 * 
	 * @return a boolean showing whether needed researches have been done or not
	 */
	public boolean isResearchesEnough()
	{
		if (buildType.equals(ObjectType.FARM))
		{
			if (getMyPlayer().getPlayerInfo().getResearches()[1].isFinished())
				return true;
		}

		if (buildType.equals(ObjectType.GOLD_MINE)
				&& !getMyPlayer().getPlayerInfo().getResearches()[2]
						.isFinished())
			return false;

		else if (buildType.equals(ObjectType.STONE_MINE)
				&& !getMyPlayer().getPlayerInfo().getResearches()[2]
						.isFinished())
			return false;

		else if (buildType.equals(ObjectType.WOOD_CAMP)
				&& !getMyPlayer().getPlayerInfo().getResearches()[3]
						.isFinished())
			return false;

		else if (buildType.equals(ObjectType.PORT)
				&& !getMyPlayer().getPlayerInfo().getResearches()[8]
						.isFinished())
			return false;

		else if (buildType.equals(ObjectType.MARKET)
				&& !getMyPlayer().getPlayerInfo().getResearches()[15]
						.isFinished())
			return false;

		return true;
	}

	/**
	 * show whether enough resources are available for building
	 * 
	 * @return a boolean showing whether enough resources are available to build
	 *         or not
	 */
	public boolean isResourcesEnough()
	{
		if (getMyPlayer().getPlayerInfo().getGoldInStock().getAmount() > getMyPlayer()
				.getPlayerInfo().getNeededResources().get(buildType)[0]
				&& getMyPlayer().getPlayerInfo().getStoneInStock().getAmount() > getMyPlayer()
						.getPlayerInfo().getNeededResources().get(buildType)[1]
				&& getMyPlayer().getPlayerInfo().getLumberInStock().getAmount() > getMyPlayer()
						.getPlayerInfo().getNeededResources().get(buildType)[2]
				&& getMyPlayer().getPlayerInfo().getKnowledgeInStock()
						.getAmount() > getMyPlayer().getPlayerInfo()
						.getNeededResources().get(buildType)[3]
				&& getMyPlayer().getPlayerInfo().getFoodInStock().getAmount() > getMyPlayer()
						.getPlayerInfo().getNeededResources().get(buildType)[4])
			return true;

		return false;
	}

	/**
	 * show whether the chosen location to build is valid or not
	 * 
	 * @return a boolean indication whether the area in which the building is
	 *         commanded to be built is valid or not
	 */
	public boolean isLocationValid()
	{
		for (int i = buildPoint.getX(); i < buildPoint.getX()
				+ Integer.valueOf(getMyPlayer().getPlayerInfo()
						.getBuildingSize().get(buildType)); i++)
		{
			for (int j = buildPoint.getY(); j < buildPoint.getY()
					+ Integer.valueOf(getMyPlayer().getPlayerInfo()
							.getBuildingSize().get(buildType)); j++)
			{
				if (i < 0
						|| j < 0
						|| i >= getMyPlayer().getMyMap().getMapBlocks().length
						|| j >= getMyPlayer().getMyMap().getMapBlocks()[0].length
						|| !getMyPlayer().getMapVisiblity()[i][j])
					return false;
				if ((buildType.equals(ObjectType.WOOD_CAMP) && !getMyPlayer()
						.getMyMap().getMapBlocks()[i][j].getBlockType().equals(
						BlockType.FOREST))
						|| (buildType.equals(ObjectType.UNIVERSITY) && !getMyPlayer()
								.getMyMap().getMapBlocks()[i][j].getBlockType()
								.equals(BlockType.PLAIN))
						|| (buildType.equals(ObjectType.STONE_MINE) && !getMyPlayer()
								.getMyMap().getMapBlocks()[i][j].getBlockType()
								.equals(BlockType.MOUNTAIN))
						|| (buildType.equals(ObjectType.STOCKPILE) && !getMyPlayer()
								.getMyMap().getMapBlocks()[i][j].getBlockType()
								.equals(BlockType.PLAIN))
						|| (buildType.equals(ObjectType.MARKET) && !getMyPlayer()
								.getMyMap().getMapBlocks()[i][j].getBlockType()
								.equals(BlockType.PLAIN))
						|| (buildType.equals(ObjectType.MAIN_BUILDING) && !getMyPlayer()
								.getMyMap().getMapBlocks()[i][j].getBlockType()
								.equals(BlockType.PLAIN))
						|| (buildType.equals(ObjectType.GOLD_MINE) && !getMyPlayer()
								.getMyMap().getMapBlocks()[i][j].getBlockType()
								.equals(BlockType.MOUNTAIN))
						|| (buildType.equals(ObjectType.FARM) && !getMyPlayer()
								.getMyMap().getMapBlocks()[i][j].getBlockType()
								.equals(BlockType.PLAIN))
						|| (buildType.equals(ObjectType.PORT) && !getMyPlayer()
								.getMyMap().getMapBlocks()[i][j].getBlockType()
								.equals(BlockType.WATER))
						|| (buildType.equals(ObjectType.BARRACK) && !getMyPlayer()
								.getMyMap().getMapBlocks()[i][j].getBlockType()
								.equals(BlockType.PLAIN))
						|| (buildType.equals(ObjectType.STABLE) && !getMyPlayer()
								.getMyMap().getMapBlocks()[i][j].getBlockType()
								.equals(BlockType.PLAIN)))
					return false;
				if (getMyPlayer().getMyMap().getMapBlocks()[i][j]
						.isBuildingThere())
					return false;
			}
		}

		return true;

	}
}
