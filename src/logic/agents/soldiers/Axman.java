package logic.agents.soldiers;

import logic.game.Player;
@SuppressWarnings("serial")
public class Axman extends Infantry
{
	public Axman(Player myPlayer)
	{
		super(myPlayer);
		getMyPlayer().getAxmans().add(this);
	}

	@Override
	public int getAttackScore()
	{
		return getMyPlayer().getPlayerInfo().getAxmanAttackScore();
	}

	@Override
	public int getDefenceScore()
	{
		return getMyPlayer().getPlayerInfo().getAxmanDefenceScore();
	}

	@Override
	public int getScoreTowardCavalry()
	{
		return getMyPlayer().getPlayerInfo().getAxmanScoreTowardCavalry();
	}

	@Override
	public int getScoreTowardInfantry()
	{
		return getMyPlayer().getPlayerInfo().getAxmanScoreTowardInfantry();
	}

}
