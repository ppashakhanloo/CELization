package logic.actions;

import logic.game.Player;
import logic.types.ResourceType;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
public class ExchangeAction extends Action
{
	private Player myPlayer;

	private ResourceType fromResource;
	private ResourceType toResource;
	private int amount;

	/**
	 * 
	 * @param player
	 * @param amount
	 * @param from
	 *            -resource type
	 * @param to
	 *            -resource type
	 */
	public ExchangeAction(Player player, int amount, ResourceType fromResource,
			ResourceType toResource)
	{
		this.myPlayer = player;

		this.amount = amount;
		this.fromResource = fromResource;
		this.toResource = toResource;
	}

	/**
	 * Do the core action of the exchange action - without checking conditions
	 */
	@Override
	public void doAction()
	{
		if (myPlayer.getMarkets().size() != 0)
		{
			int amountAdded = (int) Math
					.ceil((getResourceValue(toResource) * amount)
							/ getResourceValue(fromResource));

			switch (toResource)
			{
				case GOLD:
					myPlayer.getPlayerInfo().getGoldInStock()
							.addTo(amountAdded);
					break;
				case STONE:
					myPlayer.getPlayerInfo().getStoneInStock()
							.addTo(amountAdded);
					break;
				case LUMBER:
					myPlayer.getPlayerInfo().getLumberInStock()
							.addTo(amountAdded);
					break;
				case FOOD:
					myPlayer.getPlayerInfo().getFoodInStock()
							.addTo(amountAdded);
					break;
			}

			switch (fromResource)
			{
				case GOLD:
					myPlayer.getPlayerInfo().getGoldInStock()
							.removeFrom(amount);
					break;
				case STONE:
					myPlayer.getPlayerInfo().getStoneInStock()
							.removeFrom(amount);
					break;
				case LUMBER:
					myPlayer.getPlayerInfo().getLumberInStock()
							.removeFrom(amount);
					break;
				case FOOD:
					myPlayer.getPlayerInfo().getFoodInStock()
							.removeFrom(amount);
					break;
			}

		}
	}

	/**
	 * get the absolute value of each resource, comparing to gold
	 * 
	 * @param resource
	 *            type
	 * @return an integer showing the resource value
	 */
	private double getResourceValue(ResourceType resourceType)
	{
		if (resourceType.equals(ResourceType.GOLD))
			return 1;
		else if (resourceType.equals(ResourceType.STONE))
			return 3;
		else if (resourceType.equals(ResourceType.LUMBER))
			return 6;
		else if (resourceType.equals(ResourceType.FOOD))
			return 10;
		return 0;
	}

}
