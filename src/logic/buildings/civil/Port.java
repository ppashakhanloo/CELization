package logic.buildings.civil;

import logic.buildings.Building;
import logic.game.Player;
import logic.types.ObjectType;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */

@SuppressWarnings("serial")
public class Port extends Building
{
	public final int NUMBER;

	/**
	 * 
	 * @param player
	 */
	public Port(Player player)
	{
		super(player);
		getMyPlayer().getPorts().add(this);
		setObjectType(ObjectType.PORT);
		NUMBER = 9;
	}

	/**
	 * remove the current Port from the player's objects
	 */
	@Override
	public void remove()
	{
		super.remove();
		getMyPlayer().getPorts().remove(this);
	}
}
