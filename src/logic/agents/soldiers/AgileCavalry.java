package logic.agents.soldiers;

import logic.game.Player;

@SuppressWarnings("serial")
public class AgileCavalry extends Cavalry
{
	public AgileCavalry(Player myPlayer)
	{
		super(myPlayer);
		getMyPlayer().getAgileCavalries().add(this);
	}

	@Override
	public int getAttackScore()
	{
		return getMyPlayer().getPlayerInfo().getAgileCavalryAttackScore();
	}

	@Override
	public int getDefenceScore()
	{
		return getMyPlayer().getPlayerInfo().getAgileCavalryDefenceScore();
	}

	@Override
	public int getScoreTowardCavalry()
	{
		return getMyPlayer().getPlayerInfo()
				.getAgileCavalryScoreTowardCavalry();
	}

	@Override
	public int getScoreTowardInfantry()
	{
		return getMyPlayer().getPlayerInfo()
				.getAgileCavalryScoreTowardInfantry();
	}

}
