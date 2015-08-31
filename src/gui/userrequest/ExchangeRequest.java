package gui.userrequest;

import gui.gui.GUIGameObject;
import gui.types.Type;

public class ExchangeRequest extends UserRequest
{
	private int amount;
	private Type fromResource;
	private Type toResource;

	public ExchangeRequest(GUIGameObject uiObject, Type fromResource,
			Type toResource, int amount)
	{
		super(uiObject);
		this.fromResource = fromResource;
		this.toResource = toResource;
		this.amount = amount;
	}

	public int getAmount()
	{
		return amount;
	}

	public Type getFromResource()
	{
		return fromResource;
	}

	public Type getToResource()
	{
		return toResource;
	}
}
