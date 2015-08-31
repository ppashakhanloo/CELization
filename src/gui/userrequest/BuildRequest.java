package gui.userrequest;

import gui.gui.GUIGameObject;
import gui.types.Type;

import java.awt.Point;

public class BuildRequest extends UserRequest
{
	private Point destination;
	private Type buildingType;

	public BuildRequest(GUIGameObject uiObject)
	{
		super(uiObject);
	}

	public void setDestination(Point destination)
	{
		this.destination = destination;
	}

	public void setBuildingType(Type buildingType)
	{
		this.buildingType = buildingType;
	}

	public Type getBuildingType()
	{
		return buildingType;
	}

	public Point getDestination()
	{
		return destination;
	}
}
