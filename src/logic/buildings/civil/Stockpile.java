package logic.buildings.civil;

import logic.buildings.Building;
import logic.game.Player;
import logic.types.ObjectType;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */

@SuppressWarnings("serial")
public class Stockpile extends Building
{
	public final int NUMBER;

	/**
	 * 
	 * @param player
	 */
	public Stockpile(Player player)
	{
		super(player);
		getMyPlayer().getStockpiles().add(this);
		setObjectType(ObjectType.STOCKPILE);
		NUMBER = 7;
		getMyPlayer().getPlayerInfo().setMaxFoodResource(
				getMyPlayer().getPlayerInfo().getMaxFoodResource() + 100);
		getMyPlayer().getPlayerInfo().setMaxGoldResource(
				getMyPlayer().getPlayerInfo().getMaxGoldResource() + 100);
		getMyPlayer().getPlayerInfo().setMaxLumberResource(
				getMyPlayer().getPlayerInfo().getMaxLumberResource() + 100);
		getMyPlayer().getPlayerInfo().setMaxStoneResource(
				getMyPlayer().getPlayerInfo().getMaxStoneResource() + 100);
	}

	/**
	 * remove the current Stockpile from the player's objects
	 */
	@Override
	public void remove()
	{
		super.remove();
		getMyPlayer().getStockpiles().remove(this);
	}
}
