package logic.actionController;

import logic.game.Player;
import logic.types.ResourceType;
import logic.actions.*;

public class ExchangeController extends ActionController
{
	private ResourceType fromResource;
	private ResourceType toResource;
	private int amount;

	public ExchangeController(Player myPlayer, ResourceType fromResource,
			ResourceType toResource, int amount)
	{
		super(myPlayer);
		this.fromResource = fromResource;
		this.toResource = toResource;
		this.amount = amount;
	}

	@Override
	public void nextTurn()
	{
		ExchangeAction exchangeAction = new ExchangeAction(getMyPlayer(),
				amount, fromResource, toResource);
		exchangeAction.doAction();
		getMyPlayer().getDoneActions().add(
				"[AN EXCHANGE WAS DONE FROM " + amount + " " + fromResource
						+ " TO " + toResource + " COMPLETED]");
		this.getMyPlayer().getMarkets().get(0).setActionController(null);
	}

}
