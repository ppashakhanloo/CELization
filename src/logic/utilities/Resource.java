package logic.utilities;

import java.io.Serializable;

import logic.types.ResourceType;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
public class Resource implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int MAX;
	private int amount;
	private ResourceType resourceType;

	public Resource(int amount, ResourceType resourceType, int MAX)
	{
		this.MAX = MAX;
		this.amount = amount;
		this.resourceType = resourceType;
	}

	public int getAmount()
	{
		return amount;
	}

	public void setAmount(int amount)
	{
		this.amount = amount;

		if (this.amount >= MAX)
			this.amount = MAX;
	}

	public ResourceType getResourceType()
	{
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType)
	{
		this.resourceType = resourceType;
	}

	public int getMAX()
	{
		return MAX;
	}

	public void setMAX(int mAX)
	{
		MAX = mAX;
	}

	public void addTo(int amount)
	{
		this.amount += amount;
		if (this.amount > MAX)
			this.amount = MAX;
	}

	public void removeFrom(int amount)
	{
		if (this.amount - amount >= 0)
			this.amount -= amount;
	}
}
