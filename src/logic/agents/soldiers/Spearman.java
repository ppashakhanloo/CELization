package logic.agents.soldiers;

import logic.game.Player;

@SuppressWarnings("serial")
public class Spearman extends Infantry
{

	public Spearman(Player myPlayer)
	{
		super(myPlayer);
		getMyPlayer().getSpearmans().add(this);
	}

	@Override
	public void remove()
	{
		super.remove();
		getMyPlayer().getWorkers().remove(this);
	}

	@Override
	public int getAttackScore()
	{
		return getMyPlayer().getPlayerInfo().getSpearmanAttackScore();
	}

	@Override
	public int getDefenceScore()
	{
		return getMyPlayer().getPlayerInfo().getSpearmanDefenceScore();
	}

	@Override
	public int getScoreTowardCavalry()
	{
		return getMyPlayer().getPlayerInfo().getSpearmanScoreTowardCavalry();
	}

	@Override
	public int getScoreTowardInfantry()
	{
		return getMyPlayer().getPlayerInfo().getSpearmanScoreTowardInfantry();
	}

}
