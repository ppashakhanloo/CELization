package gui.userrequest;

import gui.gui.GUIGameObject;

public class WorkRequest extends UserRequest
{
	private GUIGameObject destBuilding;

	public WorkRequest(GUIGameObject uiObject, GUIGameObject destBuilding)
	{
		super(uiObject);
		this.destBuilding = destBuilding;
	}

	public WorkRequest(GUIGameObject uiObject)
	{
		super(uiObject);
	}

	public GUIGameObject getDestBuilding()
	{
		return destBuilding;
	}

	public void setDestBuilding(GUIGameObject destBuilding)
	{
		this.destBuilding = destBuilding;
	}
}
