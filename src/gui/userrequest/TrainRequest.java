package gui.userrequest;

import gui.gui.GUIGameObject;
import gui.types.Type;

public class TrainRequest extends UserRequest
{
	private Type trainType;

	public TrainRequest(GUIGameObject uiObject)
	{
		super(uiObject);
	}

	public void setTrainType(Type trainType)
	{
		this.trainType = trainType;
	}

	public Type getTrainType()
	{
		return trainType;
	}
}
