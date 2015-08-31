package logic.agents.soldiers;

import logic.game.Player;
@SuppressWarnings("serial")
abstract public class Infantry extends Soldier
{
	public Infantry(Player myPlayer)
	{
		super(myPlayer);
		getMyPlayer().getInfantries().add(this);
		setHealth(myPlayer.getPlayerInfo().getMaxInfantryHealth());
	}
}
