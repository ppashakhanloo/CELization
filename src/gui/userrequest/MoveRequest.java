package gui.userrequest;

import gui.gui.GUIGameObject;

import java.awt.Point;

public class MoveRequest extends UserRequest
{
	private Point destination;

	public MoveRequest(GUIGameObject uiObject, Point destination)
	{
		super(uiObject);
		this.destination = destination;
	}

	public MoveRequest(GUIGameObject uiObject)
	{
		super(uiObject);
	}

	public void setDestination(Point destination)
	{
		this.destination = destination;
	}

	public Point getDestination()
	{
		return destination;
	}
}
