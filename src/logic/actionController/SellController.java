package logic.actionController;

import logic.buildings.Building;
import logic.game.Player;
import logic.types.ObjectType;
import logic.actions.SellAction;

public class SellController extends ActionController
{
	private Building myBuilding;
	private ObjectType buildingType;

	public SellController(Player myPlayer, Building myBuilding,
			ObjectType buildingType)
	{
		super(myPlayer);
		this.myBuilding = myBuilding;
		this.buildingType = buildingType;
	}

	@Override
	public void nextTurn()
	{
		getMyPlayer().getDoneActions().add("[A " + buildingType + " WAS SOLD]");
		SellAction sellAction = new SellAction(getMyPlayer(), myBuilding,
				buildingType);
		sellAction.doAction();
	}
}
