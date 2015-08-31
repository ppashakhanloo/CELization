package logic.buildings.civil;

import logic.buildings.Building;
import logic.game.Player;
import logic.types.ObjectType;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */

@SuppressWarnings("serial")
public class StoneMine extends Building
{
	public final int NUMBER;

	/**
	 * 
	 * @param player
	 */
	public StoneMine(Player player)
	{
		super(player);
		getMyPlayer().getStoneMines().add(this);
		setObjectType(ObjectType.STONE_MINE);
		NUMBER = 4;
	}

	/**
	 * remove the current StoneMine from the player's objects
	 */
	@Override
	public void remove()
	{
		super.remove();
		getMyPlayer().getStoneMines().remove(this);
	}
}
