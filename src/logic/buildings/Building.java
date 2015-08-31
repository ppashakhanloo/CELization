package logic.buildings;

import java.util.ArrayList;

import logic.agents.workers.Worker;
import logic.game.GameObject;
import logic.game.Player;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
@SuppressWarnings("serial")
public abstract class Building extends GameObject
{

	private ArrayList<Worker> workersInside = new ArrayList<Worker>();

	/**
	 * 
	 * @param my
	 *            player
	 */
	public Building(Player myPlayer)
	{
		super(myPlayer);
		getMyPlayer().getBuildings().add(this);
	}

	/**
	 * remove the current Building from the player's objects
	 */
	@Override
	public void remove()
	{
		super.remove();
		getMyPlayer().getBuildings().remove(this);
	}

	/**
	 * get the array list of workers - working inside the building -
	 * 
	 * @return an array list of workers inside the building
	 */
	public ArrayList<Worker> getWorkersInside()
	{
		return workersInside;
	}

}
