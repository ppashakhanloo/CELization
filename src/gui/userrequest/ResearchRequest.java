package gui.userrequest;

import gui.gui.GUIGameObject;

public class ResearchRequest extends UserRequest
{
	private String research;

	public ResearchRequest(GUIGameObject uiObject, String research)
	{
		super(uiObject);
		this.research = research;
	}

	public String getResearch()
	{
		return research;
	}
}
