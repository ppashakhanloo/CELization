package logic.buildings.civil;

import logic.buildings.Building;
import logic.game.Player;
import logic.types.ObjectType;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */

@SuppressWarnings("serial")
public class Market extends Building
{
	public final int NUMBER;

	/**
	 * 
	 * @param player
	 */
	public Market(Player player)
	{
		super(player);
		getMyPlayer().getMarkets().add(this);
		setObjectType(ObjectType.MARKET);
		NUMBER = 8;
	}

	/**
	 * remove the current Market from the player's objects
	 */
	@Override
	public void remove()
	{
		super.remove();
		getMyPlayer().getMarkets().remove(this);
	}
}
