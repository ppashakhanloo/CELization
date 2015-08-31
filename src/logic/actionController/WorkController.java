package logic.actionController;

import java.util.Random;

import logic.actions.MoveAction;
import logic.agents.workers.Worker;
import logic.buildings.Building;
import logic.buildings.civil.Farm;
import logic.buildings.civil.GoldMine;
import logic.buildings.civil.StoneMine;
import logic.buildings.civil.WoodCamp;
import logic.game.Player;
import logic.types.ObjectType;
import logic.types.OccupationType;
import logic.utilities.Point;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
public class WorkController extends ActionController
{
	@SuppressWarnings("unused")
	private boolean isBeginingOfTheWork;
	private Worker myWorker;
	private Building destBuilding;

	/**
	 * 
	 * @param my
	 *            player
	 * @param destination
	 *            building
	 * @param my
	 *            Worker
	 */
	public WorkController(Player myPlayer, Building destBuilding,
			Worker myWorker)
	{
		super(myPlayer);
		this.isBeginingOfTheWork = true;
		this.myWorker = myWorker;
		this.destBuilding = destBuilding;
		this.myWorker.setOccupationType(OccupationType.WORKING);
		this.isBeginingOfTheWork = true;
		this.myWorker.setActionController(this);
		this.destBuilding.getWorkersInside().add(myWorker);
	}

	/**
	 * Call the next turn of the WORK
	 */
	@Override
	public void nextTurn()
	{
		if (isInventoryFull())
		{
			getMyPlayer().getDoneActions().add(
					"[THE INVENTORY OF " + myWorker.getId()
							+ " IS FULL AND IS GONNA UNLOAD]");
			destBuilding.getWorkersInside().remove(myWorker);
			Building unloadBuilding = null;
			if (getMyPlayer().getStockpiles().size() > 0)
			{
				Random random = new Random(100);
				int chosenStockpile = random.nextInt(getMyPlayer()
						.getStockpiles().size());

				unloadBuilding = getMyPlayer().getStockpiles().get(
						chosenStockpile);
			} else if (getMyPlayer().getMainBuildings().size() > 0)
			{
				unloadBuilding = getMyPlayer().getMainBuildings().get(0);
			}

			if (doIMoveToBuilding(unloadBuilding))
			{
				MoveAction move;
				Point dest = getMoveDestination(unloadBuilding);
				if (dest != null)
				{
					move = new MoveAction(getMyPlayer(), myWorker, dest);
					move.doAction();
				}
			} else
			{
				getMyPlayer().getDoneActions().add(
						"[" + myWorker.getId() + " ADDED "
								+ myWorker.getLoadInInventory()
								+ " TO RESOURCES]");
				if (destBuilding instanceof Farm)
					getMyPlayer().getPlayerInfo().getFoodInStock()
							.addTo(myWorker.getLoadInInventory());
				else if (destBuilding instanceof StoneMine)
					getMyPlayer().getPlayerInfo().getStoneInStock()
							.addTo(myWorker.getLoadInInventory());
				else if (destBuilding instanceof GoldMine)
					getMyPlayer().getPlayerInfo().getGoldInStock()
							.addTo(myWorker.getLoadInInventory());
				else if (destBuilding instanceof WoodCamp)
					getMyPlayer().getPlayerInfo().getLumberInStock()
							.addTo(myWorker.getLoadInInventory());
				myWorker.setOccupationType(OccupationType.IDLE);
				destBuilding.getWorkersInside().remove(myWorker);
				myWorker.setLoadInInventory(0);
				myWorker.setActionController(null);
			}
		} else if (doIMoveToBuilding(destBuilding))
		{
			Point dest = getMoveDestination(destBuilding);
			if (dest == null)
				System.err
						.println("BuildController class:nextTurn(): dest is null! do something!!!!");
			else
			{
				MoveAction move = new MoveAction(getMyPlayer(), myWorker, dest);
				move.doAction();
			}
		} else if (!isInventoryFull())
		{
			destBuilding.getWorkersInside().add(myWorker);
			int collectResourcePerTurn = 0;
			if (destBuilding instanceof Farm)
			{
				myWorker.setFoodCollectingExperience(myWorker
						.getFoodCollectingExperience() + 0.01);
				for (int i = 0; i < getMyPlayer().getPlayerInfo()
						.getBuildingSize().get(destBuilding.getObjectType()); i++)
				{
					for (int j = 0; j < getMyPlayer().getPlayerInfo()
							.getBuildingSize()
							.get(destBuilding.getObjectType()); j++)
					{
						collectResourcePerTurn += getMyPlayer().getMyMap()
								.getMapBlocks()[i
								+ destBuilding.getPosition().getX()][j
								+ destBuilding.getPosition().getY()].getFood();
					}
				}
				collectResourcePerTurn /= (getMyPlayer().getPlayerInfo()
						.getBuildingSize().get(destBuilding.getObjectType()) * getMyPlayer()
						.getPlayerInfo().getBuildingSize()
						.get(destBuilding.getObjectType()));
				collectResourcePerTurn *= (int) ((1 + myWorker
						.getFoodCollectingExperience()) * (getMyPlayer()
						.getPlayerInfo().getFoodProductionRate()));
			} else if (destBuilding instanceof GoldMine)
			{
				myWorker.setGoldMiningExperience(myWorker
						.getGoldMiningExperience() + 0.01);
				for (int i = 0; i < getMyPlayer().getPlayerInfo()
						.getBuildingSize().get(destBuilding.getObjectType()); i++)
				{
					for (int j = 0; j < getMyPlayer().getPlayerInfo()
							.getBuildingSize()
							.get(destBuilding.getObjectType()); j++)
					{
						collectResourcePerTurn += getMyPlayer().getMyMap()
								.getMapBlocks()[i
								+ destBuilding.getPosition().getX()][j
								+ destBuilding.getPosition().getY()].getGold();
					}
				}
				collectResourcePerTurn /= (getMyPlayer().getPlayerInfo()
						.getBuildingSize().get(destBuilding.getObjectType()) * getMyPlayer()
						.getPlayerInfo().getBuildingSize()
						.get(destBuilding.getObjectType()));

				collectResourcePerTurn *= (int) ((1 + myWorker
						.getGoldMiningExperience()) * (getMyPlayer()
						.getPlayerInfo().getGoldProductionRate()));
			} else if (destBuilding instanceof StoneMine)
			{
				myWorker.setStoneMiningExperience(myWorker
						.getFoodCollectingExperience() + 0.01);
				for (int i = 0; i < getMyPlayer().getPlayerInfo()
						.getBuildingSize().get(destBuilding.getObjectType()); i++)
				{
					for (int j = 0; j < getMyPlayer().getPlayerInfo()
							.getBuildingSize()
							.get(destBuilding.getObjectType()); j++)
					{
						collectResourcePerTurn += getMyPlayer().getMyMap()
								.getMapBlocks()[i
								+ destBuilding.getPosition().getX()][j
								+ destBuilding.getPosition().getY()].getStone();
					}
				}
				collectResourcePerTurn /= (getMyPlayer().getPlayerInfo()
						.getBuildingSize().get(destBuilding.getObjectType()) * getMyPlayer()
						.getPlayerInfo().getBuildingSize()
						.get(destBuilding.getObjectType()));

				collectResourcePerTurn *= (int) ((1 + myWorker
						.getStoneMiningExperience()) * (getMyPlayer()
						.getPlayerInfo().getStoneProductionRate()));

			} else if (destBuilding instanceof WoodCamp)
			{
				myWorker.setWoodCampExperience(myWorker.getWoodCampExperience() + 0.01);
				for (int i = 0; i < getMyPlayer().getPlayerInfo()
						.getBuildingSize().get(destBuilding.getObjectType()); i++)
				{
					for (int j = 0; j < getMyPlayer().getPlayerInfo()
							.getBuildingSize()
							.get(destBuilding.getObjectType()); j++)
					{
						collectResourcePerTurn += getMyPlayer().getMyMap()
								.getMapBlocks()[i
								+ destBuilding.getPosition().getX()][j
								+ destBuilding.getPosition().getY()]
								.getLumber();
					}
				}
				collectResourcePerTurn /= (getMyPlayer().getPlayerInfo()
						.getBuildingSize().get(destBuilding.getObjectType()) * getMyPlayer()
						.getPlayerInfo().getBuildingSize()
						.get(destBuilding.getObjectType()));

				collectResourcePerTurn *= (int) ((1 + myWorker
						.getWoodCampExperience()) * (getMyPlayer()
						.getPlayerInfo().getWoodProductionRate()));
				getMyPlayer().getDoneActions().add(
						"[" + myWorker.getId() + " ADD "
								+ collectResourcePerTurn
								+ " OF RESOURCE THIS TURN]");
			}

			myWorker.setLoadInInventory(collectResourcePerTurn
					+ myWorker.getLoadInInventory());
		}
	}

	/**
	 * destination building getter
	 * 
	 * @return The destination Building to work
	 */
	public Building getDestBuilding()
	{
		return destBuilding;
	}

	private boolean doIMoveToBuilding(Building b)
	{
		ObjectType type = b.getObjectType();
		for (int i = b.getPosition().getX(); i < b.getPosition().getX()
				+ getMyPlayer().getPlayerInfo().getBuildingSize().get(type) - 1; i++)
		{
			if ((myWorker.getPosition().getX() == i && b.getPosition().getY() - 1 == myWorker
					.getPosition().getY())
					|| (myWorker.getPosition().getX() == i && b.getPosition()
							.getY()
							+ getMyPlayer().getPlayerInfo().getBuildingSize()
									.get(type) == myWorker.getPosition().getY()))
				return false;
		}

		for (int i = b.getPosition().getY(); i < b.getPosition().getY()
				+ getMyPlayer().getPlayerInfo().getBuildingSize().get(type) - 1; i++)
		{
			if ((myWorker.getPosition().getY() == i && b.getPosition().getX() - 1 == myWorker
					.getPosition().getX())
					|| (myWorker.getPosition().getY() == i && b.getPosition()
							.getX()
							+ getMyPlayer().getPlayerInfo().getBuildingSize()
									.get(type) == myWorker.getPosition().getX()))
				return false;
		}
		return true;

	}

	/**
	 * show if the the inventory of the worker is full or not
	 * 
	 * @return a boolean showing if the inventory is full
	 */
	public boolean isInventoryFull()
	{
		if (myWorker.getLoadInInventory() >= getMyPlayer().getPlayerInfo()
				.getMaxCarryingLoadByWorker())
			return true;
		return false;
	}

	/**
	 * a method to find the destination point in a smart way
	 * 
	 * @return a point showing the point where the worker must move
	 */
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

	/**
	 * show if the worker needs to still move to the work place or not
	 * 
	 * @return a boolean showing if the worker needs to move
	 */
	public boolean doIMoveToWorkPlace()
	{
		// if(myWorker.getPosition().getX() == destBuilding.getPosition().getX()
		// && myWorker.getPosition().getY() ==
		// destBuilding.getPosition().getY())
		// return false;
		// return true;
		ObjectType workplaceType = destBuilding.getObjectType();
		Point workplacePoint = destBuilding.getPosition();
		for (int i = destBuilding.getPosition().getX(); i < destBuilding
				.getPosition().getX()
				+ getMyPlayer().getPlayerInfo().getBuildingSize()
						.get(workplaceType) - 1; i++)
		{
			if ((myWorker.getPosition().getX() == i && workplacePoint.getY() - 1 == myWorker
					.getPosition().getY())
					|| (myWorker.getPosition().getX() == i && workplacePoint
							.getY()
							+ getMyPlayer().getPlayerInfo().getBuildingSize()
									.get(workplaceType) == myWorker
							.getPosition().getY()))
				return false;
		}

		for (int i = destBuilding.getPosition().getY(); i < destBuilding
				.getPosition().getY()
				+ getMyPlayer().getPlayerInfo().getBuildingSize()
						.get(workplaceType) - 1; i++)
		{
			if ((myWorker.getPosition().getY() == i && workplacePoint.getX() - 1 == myWorker
					.getPosition().getX())
					|| (myWorker.getPosition().getY() == i && workplacePoint
							.getX()
							+ getMyPlayer().getPlayerInfo().getBuildingSize()
									.get(workplaceType) == myWorker
							.getPosition().getX()))
				return false;
		}
		return true;
	}
}
