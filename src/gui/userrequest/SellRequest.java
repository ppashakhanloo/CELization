package gui.userrequest;

import gui.gui.GUIGameObject;
import gui.types.Type;

public class SellRequest extends UserRequest
{
	private Type sellType;

	public SellRequest(GUIGameObject uiObject, Type type)
	{
		super(uiObject);
		this.sellType = type;
	}

	public Type getSellType()
	{
		return sellType;
	}
}
