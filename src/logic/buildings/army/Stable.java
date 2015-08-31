package logic.buildings.army;

import logic.buildings.Building;
import logic.game.Player;
import logic.types.ObjectType;

@SuppressWarnings("serial")
public class Stable extends Building
{
	public final int NUMBER;

	public Stable(Player myPlayer)
	{
		super(myPlayer);
		getMyPlayer().getStables().add(this);
		setObjectType(ObjectType.STABLE);
		NUMBER = 10;
	}

	/**
	 * remove the current Stable from the player's objects
	 */
	@Override
	public void remove()
	{
		super.remove();
		getMyPlayer().getStables().remove(this);
	}

}
