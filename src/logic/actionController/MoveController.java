package logic.actionController;

import logic.actions.MoveAction;
import logic.agents.Human;
import logic.game.Player;
import logic.types.OccupationType;
import logic.utilities.Point;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
public class MoveController extends ActionController
{
	private MoveAction moveAction;
	private Human myHuman;
	private Point destination;

	/**
	 * 
	 * @param myPlayer
	 * @param myWorker
	 * @param destination
	 */
	public MoveController(Player myPlayer, Human myHuman, Point destination)
	{
		super(myPlayer);
		this.destination = destination;
		this.myHuman = myHuman;
		this.myHuman.setOccupationType(OccupationType.IDLE);
	}

	/**
	 * action controller getter
	 * 
	 * @return ActionController
	 */
	public ActionController getActionController()
	{
		return this;
	}

	/**
	 * move destination getter
	 * 
	 * @return destination Point
	 */
	public Point getDestination()
	{
		return destination;
	}

	/**
	 * Call the next turn of the MOVE
	 */
	@Override
	public void nextTurn()
	{
		if (!myHuman.getPosition().equals(destination))
		{
			moveAction = new MoveAction(getMyPlayer(), myHuman, destination);
			moveAction.doAction();
		}
		if (myHuman.getPosition().equals(destination))
		{
			this.myHuman.setOccupationType(OccupationType.IDLE);
			this.myHuman.setActionController(null);
		}
	}

	public Human getMyHuman()
	{
		return myHuman;
	}

}