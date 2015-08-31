package logic.agents.soldiers;

import logic.game.Player;

@SuppressWarnings("serial")
public class HorseMaceman extends Cavalry
{
	// savareh nezaam
	public HorseMaceman(Player myPlayer)
	{
		super(myPlayer);
		getMyPlayer().getHorseMacemans().add(this);
	}

	@Override
	public int getAttackScore()
	{
		return getMyPlayer().getPlayerInfo().getHorseMacemanAttackScore();
	}

	@Override
	public int getDefenceScore()
	{
		return getMyPlayer().getPlayerInfo().getHorseMacemanDefenceScore();
	}

	@Override
	public int getScoreTowardCavalry()
	{
		return getMyPlayer().getPlayerInfo()
				.getHorseMacemanScoreTowardCavalry();
	}

	@Override
	public int getScoreTowardInfantry()
	{
		return getMyPlayer().getPlayerInfo()
				.getHorseMacemanScoreTowardInfantry();
	}

}
