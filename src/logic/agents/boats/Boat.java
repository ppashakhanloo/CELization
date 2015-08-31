package logic.agents.boats;

import logic.game.GameObject;
import logic.game.Movable;
import logic.game.Player;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
@SuppressWarnings("serial")
public class Boat extends GameObject implements Movable
{
	/**
	 * 
	 * @param my
	 *            player
	 */
	public Boat(Player myPlayer)
	{
		super(myPlayer);
		getMyPlayer().getBoats().add(this);
	}

	/**
	 * remove the current Boat from the player's objects
	 */
	@Override
	public void remove()
	{
		super.remove();
		getMyPlayer().getBoats().remove(this);
	}
}
