package logic.actions;

import logic.buildings.Building;
import logic.buildings.army.Barrack;
import logic.buildings.army.Stable;
import logic.buildings.civil.Farm;
import logic.buildings.civil.GoldMine;
import logic.buildings.civil.MainBuilding;
import logic.buildings.civil.Market;
import logic.buildings.civil.Port;
import logic.buildings.civil.Stockpile;
import logic.buildings.civil.StoneMine;
import logic.buildings.civil.University;
import logic.buildings.civil.WoodCamp;
import logic.game.Player;
import logic.types.ObjectType;
import logic.types.ResourceType;
import logic.utilities.Point;
import logic.utilities.Resource;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
public class BuildAction extends Action
{
	private Building newBuilding;
	private ObjectType buildType;
	private Player myPlayer;
	private Point buildPoint;

	/**
	 * 
	 * @param building
	 *            type
	 * @param my
	 *            player
	 * @param build
	 *            point
	 */
	public BuildAction(ObjectType buildType, Player myPlayer, Point buildPoint)
	{
		this.buildType = buildType;
		this.myPlayer = myPlayer;
		this.buildPoint = buildPoint;
	}

	/**
	 * object type getter
	 * 
	 * @return the BuildType of building
	 */
	public ObjectType getBuildType()
	{
		return buildType;
	}

	/**
	 * Do the core action of the build action - without checking conditions
	 */
	@Override
	public void doAction()
	{
		if (buildType.equals(ObjectType.FARM))
		{
			newBuilding = new Farm(myPlayer);

		} else if (buildType.equals(ObjectType.GOLD_MINE))
		{
			newBuilding = new GoldMine(myPlayer);
		} else if (buildType.equals(ObjectType.MAIN_BUILDING))
		{
			newBuilding = new MainBuilding(myPlayer);
		} else if (buildType.equals(ObjectType.MARKET))
		{
			newBuilding = new Market(myPlayer);
		} else if (buildType.equals(ObjectType.PORT))
		{
			newBuilding = new Port(myPlayer);
		} else if (buildType.equals(ObjectType.STOCKPILE))
		{
			newBuilding = new Stockpile(myPlayer);

			myPlayer.getPlayerInfo().setFoodInStock(
					new Resource(myPlayer.getPlayerInfo().getFoodInStock()
							.getAmount(), ResourceType.FOOD, 100 + myPlayer
							.getPlayerInfo().getFoodInStock().getMAX()));

			myPlayer.getPlayerInfo().setGoldInStock(
					new Resource(myPlayer.getPlayerInfo().getGoldInStock()
							.getAmount(), ResourceType.GOLD, 100 + myPlayer
							.getPlayerInfo().getGoldInStock().getMAX()));

			myPlayer.getPlayerInfo().setLumberInStock(
					new Resource(myPlayer.getPlayerInfo().getLumberInStock()
							.getAmount(), ResourceType.LUMBER, 100 + myPlayer
							.getPlayerInfo().getLumberInStock().getMAX()));

			myPlayer.getPlayerInfo().setStoneInStock(
					new Resource(myPlayer.getPlayerInfo().getStoneInStock()
							.getAmount(), ResourceType.STONE, 100 + myPlayer
							.getPlayerInfo().getStoneInStock().getMAX()));

		} else if (buildType.equals(ObjectType.STONE_MINE))
		{
			newBuilding = new StoneMine(myPlayer);
		} else if (buildType.equals(ObjectType.UNIVERSITY))
		{
			newBuilding = new University(myPlayer);
		} else if (buildType.equals(ObjectType.WOOD_CAMP))
		{
			newBuilding = new WoodCamp(myPlayer);
		} else if (buildType.equals(ObjectType.BARRACK))
		{
			newBuilding = new Barrack(myPlayer);
		} else if (buildType.equals(ObjectType.STABLE))
		{
			newBuilding = new Stable(myPlayer);
		}

		newBuilding.setPosition(buildPoint);
	}
}
