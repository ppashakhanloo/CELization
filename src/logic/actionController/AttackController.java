package logic.actionController;

import logic.agents.soldiers.Cavalry;
import logic.agents.soldiers.Infantry;
import logic.agents.soldiers.Soldier;
import logic.agents.workers.Worker;
import logic.buildings.Building;
import logic.game.GameObject;
import logic.game.Player;
import logic.utilities.Point;

public class AttackController extends ActionController
{
	private Soldier mySoldier;
	private Point attackPoint;
	private GameObject attackObject;

	public AttackController(Player myPlayer, Soldier mySoldier,
			Point attackPoint, GameObject attackObject)
	{
		super(myPlayer);
		this.attackPoint = attackPoint;
		this.mySoldier = mySoldier;
		this.mySoldier.setActionController(this);
		this.attackObject = attackObject;

	}

	private boolean isAbleToAttackBuilding()
	{
		Point attPoint = attackPoint;
		for (int i = attPoint.getX(); i < attPoint.getX()
				+ getMyPlayer().getPlayerInfo().getBuildingSize()
						.get(attackObject.getObjectType()) - 1; i++)
		{
			Point p1 = new Point(i, attPoint.getY() - 1);
			if (p1.getY() >= 0
					&& (getMyPlayer().getMyMap().getMapBlocks()[i][p1.getY()]
							.isWalkableByWorker() || (!getMyPlayer()
							.getMapVisiblity()[i][p1.getY()])))
				return true;
			Point p2 = new Point(i, attPoint.getY()
					+ getMyPlayer().getPlayerInfo().getBuildingSize()
							.get(attackObject.getObjectType()));
			if ((p2.getY() < getMyPlayer().getMyMap().getMapBlocks()[0].length)
					&& (getMyPlayer().getMyMap().getMapBlocks()[i][p2.getY()]
							.isWalkableByWorker() || (!getMyPlayer()
							.getMapVisiblity()[i][p2.getY()])))
				return true;

		}
		for (int j = attPoint.getY(); j < attPoint.getY()
				+ getMyPlayer().getPlayerInfo().getBuildingSize()
						.get(attackObject.getObjectType()); j++)
		{
			Point p1 = new Point(attPoint.getX() - 1, j);
			if (p1.getX() >= 0
					&& (getMyPlayer().getMyMap().getMapBlocks()[p1.getX()][j]
							.isWalkableByWorker() || (!getMyPlayer()
							.getMapVisiblity()[p1.getX()][j])))
				return true;
			Point p2 = new Point(attPoint.getX()
					+ getMyPlayer().getPlayerInfo().getBuildingSize()
							.get(attackObject.getObjectType()), j);
			if (p2.getX() < getMyPlayer().getMyMap().getMapBlocks().length
					&& (getMyPlayer().getMyMap().getMapBlocks()[p2.getX()][j]
							.isWalkableByWorker() || (!getMyPlayer()
							.getMapVisiblity()[p2.getX()][j])))
				return true;

		}
		return false;
	}

	private boolean isAbleToAttackBlock()
	{
		if (mySoldier.getPosition().getX() == attackPoint.getX()
				&& (mySoldier.getPosition().getY() == attackPoint.getY() - 1 || mySoldier
						.getPosition().getY() == attackPoint.getY() + 1))
			return true;

		if (mySoldier.getPosition().getY() == attackPoint.getY()
				&& (mySoldier.getPosition().getX() == attackPoint.getX() - 1 || mySoldier
						.getPosition().getX() == attackPoint.getX() + 1))

			return true;
		return false;
	}

	@Override
	public void nextTurn()
	{
		String attackID = attackObject.getId();
		String mySoldierID = mySoldier.getId();
		if (!getMyPlayer().getGameObjects().contains(attackObject))
		{
			if ((attackObject instanceof Building) && isAbleToAttackBuilding())
			{
				attackObject.remove();
				mySoldier.setPosition(attackPoint);
				mySoldier.setActionController(null);
			} else if (attackObject instanceof Worker)
			{
				attackObject.remove();

				mySoldier.setPosition(attackObject.getPosition());
				mySoldier.setActionController(null);
			} else if ((attackObject instanceof Soldier)
					&& isAbleToAttackBlock())
			{
				int A1 = 0;
				int B1 = 0;
				int S1 = 0;

				// MY SOLDIER
				if (attackObject instanceof Cavalry)
					A1 = mySoldier.getScoreTowardCavalry(); // :(
				else if (attackObject instanceof Infantry)
					A1 = mySoldier.getScoreTowardInfantry();
				B1 = mySoldier.getAttackScore();
				S1 = A1 + B1;
				mySoldier.setScore(mySoldier.getScore() + S1);

				// FACING SOLDIER
				int A2 = 0;
				int B2 = 0;
				int S2 = 0;

				if (mySoldier instanceof Cavalry)
					A2 = ((Soldier) attackObject).getScoreTowardCavalry();
				else if (mySoldier instanceof Infantry)
					A2 = ((Soldier) attackObject).getScoreTowardInfantry();
				B2 = ((Soldier) attackObject).getDefenceScore();
				S2 = A2 + B2;
				((Soldier) attackObject).setScore(((Soldier) attackObject)
						.getScore() + S2);

				// Choose Winner
				if (mySoldier.getHealth() * S1 > ((Soldier) attackObject)
						.getHealth() * S2)
				{
					mySoldier.setHealth(mySoldier.getHealth()
							- ((Soldier) attackObject).getHealth()
							* ((Soldier) attackObject).getScore()
							/ mySoldier.getScore());
					mySoldier.setPosition(attackObject.getPosition());
					getMyPlayer().getPlayerInfo().setTotalScore(
							getMyPlayer().getPlayerInfo().getTotalScore() + S1);
					mySoldier.setActionController(null);
					attackObject.remove();
				} else if (mySoldier.getHealth() * S1 < ((Soldier) attackObject)
						.getHealth() * S2)
				{
					((Soldier) attackObject).setHealth(((Soldier) attackObject)
							.getHealth()
							- mySoldier.getHealth()
							* mySoldier.getScore()
							/ ((Soldier) attackObject).getScore());
					attackObject
							.getMyPlayer()
							.getPlayerInfo()
							.setTotalScore(
									attackObject.getMyPlayer().getPlayerInfo()
											.getTotalScore()
											+ S2);
					mySoldier.remove();
				} else
				{
					((Soldier) attackObject).setHealth(((Soldier) attackObject)
							.getHealth()
							- mySoldier.getHealth()
							* mySoldier.getScore()
							/ ((Soldier) attackObject).getScore());

					mySoldier.setHealth(mySoldier.getHealth()
							- ((Soldier) attackObject).getHealth()
							* ((Soldier) attackObject).getScore()
							/ mySoldier.getScore());
					mySoldier.setActionController(null);
				}
			}

		}
		getMyPlayer().getDoneActions().add(
				"[AN ATTACK ACTION COMPLETED BY " + mySoldierID + " ON "
						+ attackID + " OF THE OPPONENT");
	}
}
