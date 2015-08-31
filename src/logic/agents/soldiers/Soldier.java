package logic.agents.soldiers;

import logic.agents.Human;
import logic.game.Player;

@SuppressWarnings("serial")
abstract public class Soldier extends Human
{
	private int health;
	private int score;

	public Soldier(Player myPlayer)
	{
		super(myPlayer);
		getMyPlayer().getSoldiers().add(this);
	}

	public int getScore()
	{
		return score;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

	public int getHealth()
	{
		return health;
	}

	public void setHealth(int health)
	{
		this.health = health;
	}

	abstract public int getAttackScore();

	abstract public int getDefenceScore();

	abstract public int getScoreTowardCavalry();

	abstract public int getScoreTowardInfantry();

}
