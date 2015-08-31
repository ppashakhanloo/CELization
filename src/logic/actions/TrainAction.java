package logic.actions;

import javax.swing.JOptionPane;

import logic.agents.boats.Boat;
import logic.agents.scholar.Scholar;
import logic.agents.workers.Worker;
import logic.game.GameObject;
import logic.game.Player;
import logic.types.ObjectType;
import logic.utilities.*;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
public class TrainAction extends Action
{
	private GameObject newAgent;
	private ObjectType trainType;
	private Player myPlayer;

	/**
	 * 
	 * @param train
	 *            type
	 * @param my
	 *            player
	 */
	public TrainAction(ObjectType trainType, Player myPlayer)
	{
		this.myPlayer = myPlayer;
		this.trainType = trainType;
	}

	/**
	 * Do the core action of the train action - without checking conditions
	 */
	@Override
	public void doAction()
	{

		String position = "";
		if (trainType.equals(ObjectType.SCHOLAR))
		{
			// DO NOT SHOW THE DIALOG BOX
		} else
		{
			position = JOptionPane
					.showInputDialog("PLEASE ENTER THE POSITION OF THE NEW\n"
							+ trainType
							+ " WHICH IS TRAINED RIGHT NOW\nIN THE FORMAT OF [x,y]");
		}

		if (trainType.equals(ObjectType.SCHOLAR))
		{
			newAgent = new Scholar(myPlayer);
			newAgent.setPosition(new Point(0, 0));
		} else if (trainType.equals(ObjectType.WORKER))
		{
			newAgent = new Worker(myPlayer);
			newAgent.setPosition(new Point(
					Integer.parseInt(position.split(",")[0]), Integer
							.parseInt(position.split(",")[1])));
		} else if (trainType.equals(ObjectType.BOAT))
		{
			newAgent = new Boat(myPlayer);
			newAgent.setPosition(new Point(
					Integer.parseInt(position.split(",")[0]), Integer
							.parseInt(position.split(",")[1])));
		} else if (trainType.equals(ObjectType.AGILE_CAVALRY))
		{
			newAgent = new Boat(myPlayer);
			newAgent.setPosition(new Point(
					Integer.parseInt(position.split(",")[0]), Integer
							.parseInt(position.split(",")[1])));
		} else if (trainType.equals(ObjectType.AXMAN))
		{
			newAgent = new Boat(myPlayer);
			newAgent.setPosition(new Point(
					Integer.parseInt(position.split(",")[0]), Integer
							.parseInt(position.split(",")[1])));
		} else if (trainType.equals(ObjectType.SPEARMAN))
		{
			newAgent = new Boat(myPlayer);
			newAgent.setPosition(myPlayer.getPorts().get(0).getPosition());
		} else if (trainType.equals(ObjectType.HORSE_MACEMAN))
		{
			newAgent = new Boat(myPlayer);
			newAgent.setPosition(new Point(
					Integer.parseInt(position.split(",")[0]), Integer
							.parseInt(position.split(",")[1])));
		}
		newAgent.setObjectType(trainType);
	}
}
