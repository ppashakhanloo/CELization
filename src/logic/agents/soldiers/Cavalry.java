package logic.agents.soldiers;

import logic.game.Player;
@SuppressWarnings("serial")
abstract public class Cavalry extends Soldier
{
	public Cavalry(Player myPlayer)
	{
		super(myPlayer);
		getMyPlayer().getCavalries().add(this);
		setHealth(myPlayer.getPlayerInfo().getMaxCavalryrHealth());
	}
}
