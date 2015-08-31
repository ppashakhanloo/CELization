package logic.agents.workers;

import logic.agents.Human;
import logic.game.Player;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
@SuppressWarnings("serial")
public class Worker extends Human
{
	private double goldMiningExperience;
	private double stoneMiningExperience;
	private double woodCampExperience;
	private double foodCollectingExperience;
	private double buildingExperience;

	private int loadInInventory;

	/**
	 * 
	 * @param my
	 *            player
	 */
	public Worker(Player myPlayer)
	{
		super(myPlayer);
		getMyPlayer().getWorkers().add(this);

		this.goldMiningExperience = 0;
		this.stoneMiningExperience = 0;
		this.woodCampExperience = 0;
		this.foodCollectingExperience = 0;
		this.buildingExperience = 0;
	}

	/**
	 * load in inventory getter
	 * 
	 * @return an integer showing the amount of load in inventory
	 */
	public int getLoadInInventory()
	{
		return loadInInventory;
	}

	/**
	 * load in inventory setter
	 * 
	 * @param load
	 *            that must be assigned to the load in inventory
	 */
	public void setLoadInInventory(int load)
	{
		loadInInventory = load;
		if (loadInInventory > super.getMyPlayer().getPlayerInfo()
				.getMaxCarryingLoadByWorker())
			loadInInventory = super.getMyPlayer().getPlayerInfo()
					.getMaxCarryingLoadByWorker();
	}

	/**
	 * food collecting experience setter
	 * 
	 * @param a
	 *            double of food collecting experience
	 */
	public void setFoodCollectingExperience(double foodCollectingExperience)
	{
		this.foodCollectingExperience = foodCollectingExperience;
	}

	/**
	 * food collecting experience getter
	 * 
	 * @return a double of food collecting experience
	 */
	public double getFoodCollectingExperience()
	{
		return foodCollectingExperience;
	}

	/**
	 * wood collecting experience setter
	 * 
	 * @param a
	 *            double of wood collecting experience
	 */
	public void setWoodCampExperience(double woodCampExperience)
	{
		this.woodCampExperience = woodCampExperience;
	}

	/**
	 * wood collecting experience getter
	 * 
	 * @return a double of wood collecting experience
	 */
	public double getWoodCampExperience()
	{
		return woodCampExperience;
	}

	/**
	 * stone mining experience setter
	 * 
	 * @param a
	 *            double of stone mining experience
	 */
	public void setStoneMiningExperience(double stoneMiningExperience)
	{
		this.stoneMiningExperience = stoneMiningExperience;
	}

	/**
	 * stone mining experience getter
	 * 
	 * @return a double of stone mining experience
	 */
	public double getStoneMiningExperience()
	{
		return stoneMiningExperience;
	}

	/**
	 * gold mining experience setter
	 * 
	 * @param a
	 *            double of gold mining experience
	 */
	public void setGoldMiningExperience(double goldMiningExperience)
	{
		this.goldMiningExperience = goldMiningExperience;
	}

	/**
	 * gold mining experience getter
	 * 
	 * @return a double of gold mining experience
	 */
	public double getGoldMiningExperience()
	{
		return goldMiningExperience;
	}

	/**
	 * building experience setter
	 * 
	 * @param a
	 *            double of building experience
	 */
	public void setBuildingExperience(double buildingExperience)
	{
		this.buildingExperience = buildingExperience;
	}

	/**
	 * building experience getter
	 * 
	 * @return a double of building experience
	 */
	public double getBuildingExperience()
	{
		return buildingExperience;
	}

	/**
	 * remove the current Worker from the player's objects
	 */
	@Override
	public void remove()
	{
		super.remove();
		getMyPlayer().getWorkers().remove(this);
	}
}
