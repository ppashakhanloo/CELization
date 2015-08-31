package gui.userrequest;

import gui.gui.GUIGameObject;

import java.awt.Point;

public class AttackRequest extends UserRequest
{
	private Point attackPoint;
	private GUIGameObject attackObject;

	public AttackRequest(GUIGameObject uiObject, Point attackPoint,
			GUIGameObject attackObject)
	{
		super(uiObject);
		this.attackPoint = attackPoint;
		this.attackObject = attackObject;
	}

	public AttackRequest(GUIGameObject uiObject)
	{
		super(uiObject);
	}

	public GUIGameObject getAttackObject()
	{
		return attackObject;
	}

	public Point getAttackPoint()
	{
		return attackPoint;
	}

	public void setAttackObject(GUIGameObject attackObject)
	{
		this.attackObject = attackObject;
	}

	public void setAttackPoint(Point attackPoint)
	{
		this.attackPoint = attackPoint;
	}
}
