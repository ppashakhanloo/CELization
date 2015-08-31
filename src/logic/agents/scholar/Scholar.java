package logic.agents.scholar;

import logic.agents.Human;
import logic.game.Player;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
@SuppressWarnings("serial")
public class Scholar extends Human
{

	/**
	 * 
	 * @param my
	 *            player
	 */
	public Scholar(Player myPlayer)
	{
		super(myPlayer);
		getMyPlayer().getScholars().add(this);
	}

	/**
	 * remove the current Scholar from the player's objects
	 */
	@Override
	public void remove()
	{
		super.remove();
		getMyPlayer().getScholars().remove(this);
	}
}
