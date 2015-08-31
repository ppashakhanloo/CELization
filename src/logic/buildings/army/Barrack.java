package logic.buildings.army;

import logic.buildings.Building;
import logic.game.Player;
import logic.types.ObjectType;

@SuppressWarnings("serial")
public class Barrack extends Building
{
	public final int NUMBER;

	// Soldiers' house!!
	public Barrack(Player myPlayer)
	{
		super(myPlayer);
		getMyPlayer().getBarracks().add(this);
		setObjectType(ObjectType.BARRACK);
		NUMBER = 11;
	}

	/**
	 * remove the current Barrack from the player's objects
	 */
	@Override
	public void remove()
	{
		super.remove();
		getMyPlayer().getBarracks().remove(this);
	}

}
