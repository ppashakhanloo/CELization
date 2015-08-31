package logic.buildings.civil;

import logic.buildings.Building;
import logic.game.Player;
import logic.types.ObjectType;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */

@SuppressWarnings("serial")
public class GoldMine extends Building
{
	public final int NUMBER;

	/**
	 * 
	 * @param player
	 */
	public GoldMine(Player player)
	{
		super(player);
		getMyPlayer().getGoldMines().add(this);
		setObjectType(ObjectType.GOLD_MINE);
		NUMBER = 3;
	}

	/**
	 * remove the current GoldMine from the player's objects
	 */
	@Override
	public void remove()
	{
		super.remove();
		getMyPlayer().getGoldMines().remove(this);
	}
}
