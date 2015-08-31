package logic.actions;

import logic.buildings.Building;
import logic.game.Player;
import logic.types.ObjectType;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
public class SellAction extends Action
{
	private Player myPlayer;
	private Building myBuilding;
	private ObjectType buildingType;

	/**
	 * 
	 * @param my
	 *            player
	 * @param my
	 *            building
	 * @param build
	 *            type
	 */
	public SellAction(Player myPlayer, Building myBuilding,
			ObjectType buildingType)
	{
		this.myBuilding = myBuilding;
		this.myPlayer = myPlayer;
		this.buildingType = buildingType;
	}

	/**
	 * Do the core action of the sell action - without checking conditions
	 */
	@Override
	public void doAction()
	{
		myPlayer.getPlayerInfo()
				.getGoldInStock()
				.addTo(myPlayer.getPlayerInfo().getNeededResources()
						.get(buildingType)[0] / 2);
		myPlayer.getPlayerInfo()
				.getStoneInStock()
				.addTo(myPlayer.getPlayerInfo().getNeededResources()
						.get(buildingType)[1] / 2);
		myPlayer.getPlayerInfo()
				.getLumberInStock()
				.addTo(myPlayer.getPlayerInfo().getNeededResources()
						.get(buildingType)[2] / 2);
		myBuilding.remove();
	}
}
