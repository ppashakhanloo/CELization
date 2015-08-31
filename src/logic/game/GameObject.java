package logic.game;

import java.io.Serializable;

import logic.actionController.ActionController;
import logic.types.ObjectType;
import logic.utilities.Point;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
public abstract class GameObject implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Point position;
	private String id;
	private ActionController actionController;
	private Player myPlayer;
	private ObjectType objectType;

	/**
	 * 
	 * @param player
	 */
	public GameObject(Player player)
	{
		this.myPlayer = player;
		myPlayer.getGameObjects().add(this);
		this.id = myPlayer.getId() + "-UNIT["
				+ Integer.toString(player.getMyGame().getIdNumberGameObject())
				+ "]";
		myPlayer.getGameObjectByIdMap().put(id, this);
	}

	/**
	 * Remove the current GameObject
	 */
	public void remove()
	{
		myPlayer.getGameObjects().remove(this);
	}

	/**
	 * Assign an id to the object
	 * 
	 * @param id
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * Getter of the ID
	 * 
	 * @return a string containing ID
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Getter of the current position of the object
	 * 
	 * @return a Point indicating current position of an object
	 */
	public Point getPosition()
	{
		return position;
	}

	/**
	 * Getter of the player
	 * 
	 * @return a Player
	 */
	public Player getMyPlayer()
	{
		return myPlayer;
	}

	/**
	 * Getter of the action controller
	 * 
	 * @return an ActionController
	 */
	public ActionController getActionController()
	{
		return actionController;
	}

	/**
	 * action controller setter
	 * 
	 * @param action
	 *            controller
	 */
	public void setActionController(ActionController actionController)
	{
		this.actionController = actionController;
	}

	/**
	 * object position's setter
	 * 
	 * @param position
	 */
	public void setPosition(Point position)
	{
		this.position = position;
	}

	/**
	 * Getter of the object type
	 * 
	 * @return an ObjectType
	 */
	public ObjectType getObjectType()
	{
		return objectType;
	}

	/**
	 * objectType setter
	 * 
	 * @param objectType
	 */
	public void setObjectType(ObjectType objectType)
	{
		this.objectType = objectType;
	}

}
