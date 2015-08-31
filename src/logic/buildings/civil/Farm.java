package logic.buildings.civil;

import logic.buildings.Building;
import logic.game.Player;
import logic.types.ObjectType;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */

@SuppressWarnings("serial")
public class Farm extends Building
{
	public final int NUMBER;

	/**
	 * 
	 * @param my
	 *            player
	 */
	public Farm(Player myPlayer)
	{
		super(myPlayer);
		getMyPlayer().getFarms().add(this);
		setObjectType(ObjectType.FARM);
		NUMBER = 5;
	}

	/**
	 * remove the current Farm from the player's objects
	 */
	@Override
	public void remove()
	{
		super.remove();
		getMyPlayer().getFarms().remove(this);
	}
}
