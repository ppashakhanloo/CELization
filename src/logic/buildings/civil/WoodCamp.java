package logic.buildings.civil;

import logic.buildings.Building;
import logic.game.Player;
import logic.types.ObjectType;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */

@SuppressWarnings("serial")
public class WoodCamp extends Building
{
	public final int NUMBER;

	/**
	 * 
	 * @param player
	 */
	public WoodCamp(Player player)
	{
		super(player);
		getMyPlayer().getWoodCamps().add(this);
		setObjectType(ObjectType.WOOD_CAMP);
		NUMBER = 6;
	}

	/**
	 * remove the current WoodCamp from the player's objects
	 */
	@Override
	public void remove()
	{
		super.remove();
		getMyPlayer().getWoodCamps().remove(this);
	}
}
