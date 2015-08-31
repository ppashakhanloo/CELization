package logic.actionController;

import logic.game.NextTurnable;
import logic.game.Player;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
public abstract class ActionController implements NextTurnable
{
	private Player myPlayer;

	/**
	 * 
	 * @param myPlayer
	 */
	ActionController(Player myPlayer)
	{
		this.myPlayer = myPlayer;
	}

	/**
	 * 
	 * @return current Player reference
	 */
	public Player getMyPlayer()
	{
		return myPlayer;
	}
}
