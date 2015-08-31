package gui.gui;

import gui.data.ObjectData;
import gui.types.InfoType;
import gui.types.Type;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Map;

public class GUIGameObject
{
	private String ID;
	private Type type;

	private ArrayList<Point> occupiedPoints;
	private Map<InfoType, ObjectData> infoTypeToObjectData;

	public GUIGameObject(String ID, Type type, ArrayList<Point> occupiedPoints)
	{
		this.ID = ID;
		this.type = type;
		this.occupiedPoints = occupiedPoints;
	}

	public GUIGameObject(String ID, Type type, ArrayList<Point> occupiedPoints,
			Map<InfoType, ObjectData> infoTypeToObjectData)
	{
		this(ID, type, occupiedPoints);
		this.infoTypeToObjectData = infoTypeToObjectData;
	}

	public ArrayList<Point> getOccupiedPoints()
	{
		return occupiedPoints;
	}

	public Type getType()
	{
		return type;
	}

	public Map<InfoType, ObjectData> getInfoTypeToObjectData()
	{
		return infoTypeToObjectData;
	}

	@Override
	public String toString()
	{
		return this.type.toString();
	}

	public String getID()
	{
		return ID;
	}
}
