package logic.actionController;

import logic.actions.TrainAction;
import logic.buildings.Building;
import logic.game.Player;
import logic.types.ObjectType;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
public class TrainController extends ActionController
{
	private boolean isBeginingOfTraining;
	private int remainingTurns;
	private ObjectType trainType;
	private Building building;

	/**
	 * 
	 * @param my
	 *            player
	 * @param building
	 *            that must give the command of training
	 * @param train
	 *            type
	 */
	public TrainController(Player myPlayer, Building building,
			ObjectType trainType)
	{
		super(myPlayer);
		this.trainType = trainType;

		this.remainingTurns = getMyPlayer().getPlayerInfo().getTurnsToBuild()
				.get(trainType);
		this.isBeginingOfTraining = true;
		this.building = building;
		building.setActionController(this);
	}

	/**
	 * Call the next turn of the TRAIN
	 */
	@Override
	public void nextTurn()
	{
		remainingTurns--;
		if (isBeginingOfTraining)
		{
			if (isResourcesEnough())
				if ((trainType == ObjectType.SCHOLAR && getMyPlayer()
						.getScholars().size() < getMyPlayer().getPlayerInfo()
						.getMaxUniversityCapacity())
						|| trainType == ObjectType.BOAT
						|| trainType == ObjectType.WORKER
						|| trainType == ObjectType.AGILE_CAVALRY
						|| trainType == ObjectType.AXMAN
						|| trainType == ObjectType.SPEARMAN
						|| trainType == ObjectType.HORSE_MACEMAN)
				{
					decreaseResources();
					isBeginingOfTraining = false;
				}
		} else if (remainingTurns == 0 && !isBeginingOfTraining)
		{
			TrainAction trainAction = new TrainAction(trainType, getMyPlayer());
			trainAction.doAction();
			getMyPlayer().getDoneActions().add(
					"[A " + trainType + " WAS TRAINED]");
			building.setActionController(null);
		}
	}

	/**
	 * decrease resources if the object is able to be trained
	 */
	public void decreaseResources()
	{
		getMyPlayer()
				.getPlayerInfo()
				.getGoldInStock()
				.removeFrom(
						getMyPlayer().getPlayerInfo().getNeededResources()
								.get(trainType)[0]);
		getMyPlayer()
				.getPlayerInfo()
				.getStoneInStock()
				.removeFrom(
						getMyPlayer().getPlayerInfo().getNeededResources()
								.get(trainType)[1]);
		getMyPlayer()
				.getPlayerInfo()
				.getLumberInStock()
				.removeFrom(
						getMyPlayer().getPlayerInfo().getNeededResources()
								.get(trainType)[2]);
	}

	/**
	 * 
	 * @return a boolean showing if resources are enough for training the object
	 *         or not
	 */
	public boolean isResourcesEnough()
	{
		if (getMyPlayer().getPlayerInfo().getGoldInStock().getAmount() > getMyPlayer()
				.getPlayerInfo().getNeededResources().get(trainType)[0]
				&& getMyPlayer().getPlayerInfo().getStoneInStock().getAmount() > getMyPlayer()
						.getPlayerInfo().getNeededResources().get(trainType)[1]
				&& getMyPlayer().getPlayerInfo().getLumberInStock().getAmount() > getMyPlayer()
						.getPlayerInfo().getNeededResources().get(trainType)[2]
				&& getMyPlayer().getPlayerInfo().getKnowledgeInStock()
						.getAmount() > getMyPlayer().getPlayerInfo()
						.getNeededResources().get(trainType)[3]
				&& getMyPlayer().getPlayerInfo().getFoodInStock().getAmount() > getMyPlayer()
						.getPlayerInfo().getNeededResources().get(trainType)[4])
			return true;

		return false;
	}

}
