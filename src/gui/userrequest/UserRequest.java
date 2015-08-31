package gui.userrequest;

import gui.gui.GUIGameObject;

abstract public class UserRequest
{
	private GUIGameObject uiObject;

	public UserRequest(GUIGameObject uiObject)
	{
		this.uiObject = uiObject;
	}

	public GUIGameObject getUiObject()
	{
		return uiObject;
	}

}
