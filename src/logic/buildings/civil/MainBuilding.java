package logic.buildings.civil;

import logic.buildings.Building;
import logic.game.Player;
import logic.types.ObjectType;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */

@SuppressWarnings("serial")
public class MainBuilding extends Building
{
	public final int NUMBER;

	/**
	 * 
	 * @param player
	 */
	public MainBuilding(Player player)
	{
		super(player);
		getMyPlayer().getMainBuildings().add(this);
		setObjectType(ObjectType.MAIN_BUILDING);
		NUMBER = 1;
	}

	/**
	 * remove the current MainBuilding from the player's objects
	 */
	@Override
	public void remove()
	{
		super.remove();
		getMyPlayer().getMainBuildings().remove(this);
	}
}
