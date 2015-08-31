package logic.agents;

import logic.actions.MoveAction;
import logic.game.GameObject;
import logic.game.Movable;
import logic.game.Player;
import logic.types.OccupationType;
import logic.utilities.Point;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
@SuppressWarnings("serial")
public class Human extends GameObject implements Movable
{

	private OccupationType occupationType;

	/**
	 * 
	 * @param my
	 *            player
	 */
	public Human(Player myPlayer)
	{
		super(myPlayer);
		getMyPlayer().getHumans().add(this);
		this.occupationType = OccupationType.IDLE;
	}

	public OccupationType getOccupationType()
	{
		return occupationType;
	}

	public void setOccupationType(OccupationType occupationType)
	{
		this.occupationType = occupationType;
	}

	@Override
	public void setPosition(Point position)
	{
		super.setPosition(position);
		MoveAction.makeRoundBlocksVisible(getMyPlayer(), position);
	}

	/**
	 * remove the current Human from the player's objects
	 */
	@Override
	public void remove()
	{
		super.remove();
		getMyPlayer().getHumans().remove(this);
	}
}
