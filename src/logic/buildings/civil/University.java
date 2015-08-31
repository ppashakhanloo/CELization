package logic.buildings.civil;

import logic.buildings.Building;
import logic.game.Player;
import logic.types.ObjectType;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */

@SuppressWarnings("serial")
public class University extends Building
{
	public final int NUMBER;

	/**
	 * 
	 * @param player
	 */
	public University(Player player)
	{
		super(player);
		getMyPlayer().getUniversities().add(this);
		setObjectType(ObjectType.UNIVERSITY);
		NUMBER = 2;
	}

	/**
	 * remove the current University from the player's objects
	 */
	@Override
	public void remove()
	{
		super.remove();
		getMyPlayer().getUniversities().remove(this);
	}
}
