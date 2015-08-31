package logic.game;

import java.io.Serializable;
import java.util.HashMap;

import logic.types.ObjectType;
import logic.types.ResearchType;
import logic.types.ResourceType;
import logic.utilities.Research;
import logic.utilities.Resource;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
public class GeneralPlayerInfo implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int totalScore = 0;

	private boolean abilityToBuildFarm = false;
	private boolean abilityToBuildGoldMine = false;
	private boolean abilityToBuildMainBuilding = true;
	private boolean abilityToBuildMarket = false;
	private boolean abilityToBuildPort = false;
	private boolean abilityToBuildStockpile = true;
	private boolean abilityToBuildStoneMine = false;
	private boolean abilityToBuildUniversity = true;
	private boolean abilityToBuildWoodCamp = false;
	private boolean abilityToBuildBarrack = false;
	private boolean abilityToBuildStable = true;

	private boolean abilityToTrainBoat = false;
	private boolean abilityToTrainScholar = true;
	private boolean abilityToTrainWorker = true;
	private boolean abilityToTrainSpearman = false;
	private boolean abilityToTrainAxman = false;
	private boolean abilityToTrainHorseMaceman = false;
	private boolean abilityToTrainAgileCavalry = false;

	private int maxCarryingLoadByWorker = 30;
	private int maxUniversityCapacity = 10;

	private int maxLumberResource = 200;
	private int maxFoodResource = 200;
	private int maxGoldResource = 200;
	private int maxStoneResource = 200;
	private int maxKnowledgeResource = 200;

	private int maxWoodCampCapacity = 1;
	private int maxGoldMineCapacity = 1;
	private int maxFarmCapacity = 1;
	private int maxStoneMineCapacity = 1;

	private int knowledgePerScholar = 1;

	private double woodProductionRate = 1;
	private double goldProductionRate = 1;
	private double lumberProductionRate = 1;
	private double stoneProductionRate = 1;
	private double foodProductionRate = 1;

	private int initialLumberResource = 200;
	private int initialFoodResource = 200;
	private int initialGoldResource = 200;
	private int initialStoneResource = 200;
	private int initialKnowledgeResource = 200;

	private boolean isBlockResourcesVisible = false;

	private boolean isTaxCollectable = false;

	private int maxCavalryrHealth = 300;
	private int maxInfantryHealth = 200;

	private int spearmanScoreTowardCavalry = 80;
	private int spearmanScoreTowardInfantry = 30;
	private int spearmanDefenceScore = 60;
	private int spearmanAttackScore = 20;

	private int axmanScoreTowardCavalry = 10;
	private int axmanScoreTowardInfantry = 60;
	private int axmanDefenceScore = 60;
	private int axmanAttackScore = 20;

	private int horseMacemanScoreTowardCavalry = 10;
	private int horseMacemanScoreTowardInfantry = 50;
	private int horseMacemanDefenceScore = 10;
	private int horseMacemanAttackScore = 90;

	private int agileCavalryScoreTowardCavalry = 50;
	private int agileCavalryScoreTowardInfantry = 30;
	private int agileCavalryDefenceScore = 10;
	private int agileCavalryAttackScore = 60;

	private java.util.Map<ObjectType, int[]> neededResources = new HashMap<ObjectType, int[]>();

	public int getTotalScore()
	{
		return totalScore;
	}

	public void setTotalScore(int totalScore)
	{
		this.totalScore = totalScore;
	}

	@SuppressWarnings("serial")
	private java.util.Map<ObjectType, Integer> turnsToBuild = new HashMap<ObjectType, Integer>()
	{
		{
			put(ObjectType.FARM, 6);
			put(ObjectType.GOLD_MINE, 6);
			put(ObjectType.MAIN_BUILDING, 6);
			put(ObjectType.MARKET, 20);
			put(ObjectType.PORT, 24);
			put(ObjectType.STOCKPILE, 15);
			put(ObjectType.STONE_MINE, 6);
			put(ObjectType.UNIVERSITY, 2);
			put(ObjectType.WOOD_CAMP, 6);

			put(ObjectType.BOAT, 8);
			put(ObjectType.SCHOLAR, 6);
			put(ObjectType.WORKER, 2);

			put(ObjectType.BARRACK, 15);
			put(ObjectType.STABLE, 20);
			put(ObjectType.AXMAN, 8);
			put(ObjectType.HORSE_MACEMAN, 14);
			put(ObjectType.SPEARMAN, 8);
			put(ObjectType.AGILE_CAVALRY, 10);
		}
	};

	@SuppressWarnings("serial")
	private java.util.Map<ObjectType, Integer> buildingSize = new HashMap<ObjectType, Integer>()
	{
		{
			put(ObjectType.FARM, 2);
			put(ObjectType.GOLD_MINE, 2);
			put(ObjectType.MAIN_BUILDING, 3);
			put(ObjectType.MARKET, 3);
			put(ObjectType.PORT, 2);
			put(ObjectType.STOCKPILE, 2);
			put(ObjectType.STONE_MINE, 2);
			put(ObjectType.UNIVERSITY, 3);
			put(ObjectType.WOOD_CAMP, 1);

			put(ObjectType.BARRACK, 2);
			put(ObjectType.STABLE, 2);
		}
	};

	private Resource foodInStock;
	private Resource lumberInStock;
	private Resource goldInStock;
	private Resource knowledgeInStock;
	private Resource stoneInStock;

	private final Research[] researches;// Researches that must be done in the
										// game

	private java.util.Map<ResearchType, Research> researchesMap = new HashMap<ResearchType, Research>();

	/**
	 * Initialize general information of a player
	 */
	public GeneralPlayerInfo()
	{
		researches = new Research[30];

		foodInStock = new Resource(initialFoodResource, ResourceType.FOOD,
				maxFoodResource);
		lumberInStock = new Resource(initialLumberResource,
				ResourceType.LUMBER, maxLumberResource);
		goldInStock = new Resource(initialGoldResource, ResourceType.GOLD,
				maxGoldResource);
		knowledgeInStock = new Resource(initialKnowledgeResource,
				ResourceType.KNOWLEDGE, maxKnowledgeResource);
		stoneInStock = new Resource(initialStoneResource, ResourceType.STONE,
				maxStoneResource);

		isTaxCollectable = false;

		initializeResearches();
		initializeNeededResources();
	}

	/**
	 * max wood camp worker capacity
	 * 
	 * @return an integer of wood camp worker capacity
	 */
	public int getMaxWoodCampCapacity()
	{
		return maxWoodCampCapacity;
	}

	/**
	 * max wood camp capacity setter
	 * 
	 * @param max
	 *            wood camp worker capacity
	 */
	public void setMaxWoodCampCapacity(int maxWoodCampCapacity)
	{
		this.maxWoodCampCapacity = maxWoodCampCapacity;
	}

	/**
	 * max gold mine capacity getter
	 * 
	 * @return an integer of max gold mine worker capacity
	 */
	public int getMaxGoldMineCapacity()
	{
		return maxGoldMineCapacity;
	}

	/**
	 * max gold mine capacity setter
	 * 
	 * @param max
	 *            gold mine worker capacity
	 */
	public void setMaxGoldMineCapacity(int maxGoldMineCapacity)
	{
		this.maxGoldMineCapacity = maxGoldMineCapacity;
	}

	/**
	 * max farm capacity getter
	 * 
	 * @return an integer of max farm capacity
	 */
	public int getMaxFarmCapacity()
	{
		return maxFarmCapacity;
	}

	/**
	 * max farm capacity setter
	 * 
	 * @param max
	 *            farm worker capacity
	 */
	public void setMaxFarmCapacity(int maxFarmCapacity)
	{
		this.maxFarmCapacity = maxFarmCapacity;
	}

	/**
	 * max stone mine capacity getter
	 * 
	 * @return an integer of max stone mine capacity
	 */
	public int getMaxStoneMineCapacity()
	{
		return maxStoneMineCapacity;
	}

	/**
	 * max stone mine worker capacity setter
	 * 
	 * @param max
	 *            stone mine worker capacity
	 */
	public void setMaxStoneMineCapacity(int maxStoneMineCapacity)
	{
		this.maxStoneMineCapacity = maxStoneMineCapacity;
	}

	/**
	 * food production rate setter which is related to the food production per
	 * turn
	 * 
	 * @param food
	 *            production rate
	 */
	public void setFoodProductionRate(double foodProductionRate)
	{
		this.foodProductionRate = foodProductionRate;
	}

	/**
	 * food production rate getter
	 * 
	 * @return a double of food production rate
	 */
	public double getFoodProductionRate()
	{
		return foodProductionRate;
	}

	/**
	 * show if a train is able to be trained
	 * 
	 * @return a boolean showing if the player is able to train a boat
	 */
	public boolean isAbilityToTrainBoat()
	{
		return abilityToTrainBoat;
	}

	/**
	 * ability to train a boat setter
	 * 
	 * @param ability
	 *            to train boat
	 */
	public void setAbilityToTrainBoat(boolean abilityToTrainBoat)
	{
		this.abilityToTrainBoat = abilityToTrainBoat;
	}

	/**
	 * show if a scholar is able to be trained
	 * 
	 * @return a boolean showing if the player is able to train a scholar
	 */
	public boolean isAbilityToTrainScholar()
	{
		return abilityToTrainScholar;
	}

	/**
	 * ability to train scholar setter
	 * 
	 * @param ability
	 *            to train scholar
	 */
	public void setAbilityToTrainScholar(boolean abilityToTrainScholar)
	{
		this.abilityToTrainScholar = abilityToTrainScholar;
	}

	/**
	 * show if the player is able to train a worker or not
	 * 
	 * @return a boolean showing if the player is able to train a worker
	 */
	public boolean isAbilityToTrainWorker()
	{
		return abilityToTrainWorker;
	}

	/**
	 * set the status of the ability to train worker
	 * 
	 * @param abilityToTrainWorker
	 */
	public void setAbilityToTrainWorker(boolean abilityToTrainWorker)
	{
		this.abilityToTrainWorker = abilityToTrainWorker;
	}

	/**
	 * set gold production rate
	 * 
	 * @param goldProductionRate
	 */
	public void setGoldProductionRate(double goldProductionRate)
	{
		this.goldProductionRate = goldProductionRate;
	}

	/**
	 * get gold production rate
	 * 
	 * @return a double of gold production rate
	 */
	public double getGoldProductionRate()
	{
		return goldProductionRate;
	}

	/**
	 * get wood production rate
	 * 
	 * @return a double of wood production rate
	 */
	public double getWoodProductionRate()
	{
		return woodProductionRate;
	}

	/**
	 * set wood production rate
	 * 
	 * @param woodProductionRate
	 */
	public void setWoodProductionRate(double woodProductionRate)
	{
		this.woodProductionRate = woodProductionRate;
	}

	/**
	 * get lumber production rate
	 * 
	 * @return a double of lumber production rate
	 */
	public double getLumberProductionRate()
	{
		return lumberProductionRate;
	}

	/**
	 * set lumber production rate
	 * 
	 * @param lumberProductionRate
	 */
	public void setLumberProductionRate(double lumberProductionRate)
	{
		this.lumberProductionRate = lumberProductionRate;
	}

	/**
	 * get stone production rate
	 * 
	 * @return a double of stone production rate
	 */
	public double getStoneProductionRate()
	{
		return stoneProductionRate;
	}

	/**
	 * set stone production rate
	 * 
	 * @param stoneProductionRate
	 */
	public void setStoneProductionRate(double stoneProductionRate)
	{
		this.stoneProductionRate = stoneProductionRate;
	}

	/**
	 * get the status of the ability to build farm
	 * 
	 * @return a boolean which shows the status of the ability to build farm
	 */
	public boolean isAbilityToBuildFarm()
	{
		return abilityToBuildFarm;
	}

	/**
	 * set the status of the ability to build farm
	 * 
	 * @param abilityToBuildFarm
	 */
	public void setAbilityToBuildFarm(boolean abilityToBuildFarm)
	{
		this.abilityToBuildFarm = abilityToBuildFarm;
	}

	/**
	 * get the status of the ability to build gold mine
	 * 
	 * @return a boolean which shows the status of the ability to build gold
	 *         mine
	 */
	public boolean isAbilityToBuildGoldMine()
	{
		return abilityToBuildGoldMine;
	}

	/**
	 * set the status of the ability to build gold mine
	 * 
	 * @param abilityToBuildGoldMine
	 */
	public void setAbilityToBuildGoldMine(boolean abilityToBuildGoldMine)
	{
		this.abilityToBuildGoldMine = abilityToBuildGoldMine;
	}

	/**
	 * get the status of the ability to build main building
	 * 
	 * @return a boolean which shows the status of the ability to build main
	 *         building
	 */
	public boolean isAbilityToBuildMainBuilding()
	{
		return abilityToBuildMainBuilding;
	}

	/**
	 * set the status of the ability to build main building
	 * 
	 * @param abilityToBuildMainBuilding
	 */
	public void setAbilityToBuildMainBuilding(boolean abilityToBuildMainBuilding)
	{
		this.abilityToBuildMainBuilding = abilityToBuildMainBuilding;
	}

	/**
	 * get the status of the ability to build market
	 * 
	 * @return a boolean which shows the status of the ability to build market
	 */
	public boolean isAbilityToBuildMarket()
	{
		return abilityToBuildMarket;
	}

	/**
	 * set the status of the ability to build market
	 * 
	 * @param abilityToBuildMarket
	 */
	public void setAbilityToBuildMarket(boolean abilityToBuildMarket)
	{
		this.abilityToBuildMarket = abilityToBuildMarket;
	}

	/**
	 * get the status of the ability to build port
	 * 
	 * @return a boolean which shows the status of the ability to build port
	 */
	public boolean isAbilityToBuildPort()
	{
		return abilityToBuildPort;
	}

	/**
	 * set the status of the ability to build port
	 * 
	 * @param abilityToBuildPort
	 */
	public void setAbilityToBuildPort(boolean abilityToBuildPort)
	{
		this.abilityToBuildPort = abilityToBuildPort;
	}

	/**
	 * get the status of the ability to build stock pile
	 * 
	 * @return a boolean which shows the status of the ability to build stock
	 *         pile
	 */
	public boolean isAbilityToBuildStockpile()
	{
		return abilityToBuildStockpile;
	}

	/**
	 * set the status of the ability to build stock pile
	 * 
	 * @param abilityToBuildStockpile
	 */
	public void setAbilityToBuildStockpile(boolean abilityToBuildStockpile)
	{
		this.abilityToBuildStockpile = abilityToBuildStockpile;
	}

	/**
	 * get the status of the ability to build stone mine
	 * 
	 * @return a boolean which shows the status of the ability to build stone
	 *         mine
	 */
	public boolean isAbilityToBuildStoneMine()
	{
		return abilityToBuildStoneMine;
	}

	/**
	 * set the status of the ability to build stone mine
	 * 
	 * @param abilityToBuildStoneMine
	 */
	public void setAbilityToBuildStoneMine(boolean abilityToBuildStoneMine)
	{
		this.abilityToBuildStoneMine = abilityToBuildStoneMine;
	}

	/**
	 * get the status of the ability to build university
	 * 
	 * @return a boolean which shows the status of the ability to build
	 *         university
	 */
	public boolean isAbilityToBuildUniversity()
	{
		return abilityToBuildUniversity;
	}

	/**
	 * set the status of the ability to build university
	 * 
	 * @param abilityToBuildUniversity
	 */
	public void setAbilityToBuildUniversity(boolean abilityToBuildUniversity)
	{
		this.abilityToBuildUniversity = abilityToBuildUniversity;
	}

	/**
	 * get the status of the ability to build wood camp
	 * 
	 * @return a boolean which shows the status of the ability to build wood
	 *         camp
	 */
	public boolean isAbilityToBuildWoodCamp()
	{
		return abilityToBuildWoodCamp;
	}

	/**
	 * set the status of the ability to build wood camp
	 * 
	 * @param abilityToBuildWoodCamp
	 */
	public void setAbilityToBuildWoodCamp(boolean abilityToBuildWoodCamp)
	{
		this.abilityToBuildWoodCamp = abilityToBuildWoodCamp;
	}

	/**
	 * set the status of the ability to see map block resources
	 * 
	 * @param the
	 *            status
	 */
	public void setBlockResourcesVisible(boolean isBlockResourcesVisible)
	{
		this.isBlockResourcesVisible = isBlockResourcesVisible;
	}

	/**
	 * get the status of the ability to see block resources
	 * 
	 * @return a boolean of isBlockResourcesVisible
	 */
	public boolean isBlockResourcesVisible()
	{
		return isBlockResourcesVisible;
	}

	/**
	 * getter of the array of researches that each player must do during the
	 * game
	 * 
	 * @return an array of researches
	 */
	public Research[] getResearches()
	{
		return researches;
	}

	/**
	 * getter of the research map, which maps research type to the corresponding
	 * research
	 * 
	 * @return a map of research type to research
	 */
	public java.util.Map<ResearchType, Research> getResearchesMap()
	{
		return researchesMap;
	}

	/**
	 * getter of the max carrying load by worker
	 * 
	 * @return an integer of the max carrying load by worker
	 */
	public int getMaxCarryingLoadByWorker()
	{
		return maxCarryingLoadByWorker;
	}

	/**
	 * setter of the max carrying load by the worker
	 * 
	 * @param maxCarryingLoadByWorker
	 */
	public void setMaxCarryingLoadByWorker(int maxCarryingLoadByWorker)
	{
		this.maxCarryingLoadByWorker = maxCarryingLoadByWorker;
	}

	/**
	 * getter of the max university scholar capacity
	 * 
	 * @return an integer of max university scholar capacity
	 */
	public int getMaxUniversityCapacity()
	{
		return maxUniversityCapacity;
	}

	/**
	 * setter of the max university scholar capacity
	 * 
	 * @param maxUniversityCapacity
	 */
	public void setMaxUniversityCapacity(int maxUniversityCapacity)
	{
		this.maxUniversityCapacity = maxUniversityCapacity;
	}

	/**
	 * getter of the max lumber resource
	 * 
	 * @return an integer of max lumber resource amount
	 */
	public int getMaxLumberResource()
	{
		return maxLumberResource;
	}

	public void setMaxLumberResource(int maxLumberResource)
	{
		this.maxLumberResource = maxLumberResource;
	}

	/**
	 * getter of the max food resource
	 * 
	 * @return an integer of max food resource amount
	 */
	public int getMaxFoodResource()
	{
		return maxFoodResource;
	}

	/**
	 * setter of the max food resource amount
	 * 
	 * @param maxFoodResource
	 */
	public void setMaxFoodResource(int maxFoodResource)
	{
		this.maxFoodResource = maxFoodResource;
	}

	/**
	 * getter of the max gold resource
	 * 
	 * @return an integer of max gold resource amount
	 */
	public int getMaxGoldResource()
	{
		return maxGoldResource;
	}

	/**
	 * setter of the max gold resource amount
	 * 
	 * @param maxGoldResource
	 */
	public void setMaxGoldResource(int maxGoldResource)
	{
		this.maxGoldResource = maxGoldResource;
	}

	/**
	 * getter of the max stone resource
	 * 
	 * @return an integer of max stone resource amount
	 */
	public int getMaxStoneResource()
	{
		return maxStoneResource;
	}

	/**
	 * setter of the max stone resource amount
	 * 
	 * @param maxStoneResource
	 */
	public void setMaxStoneResource(int maxStoneResource)
	{
		this.maxStoneResource = maxStoneResource;
	}

	/**
	 * getter of the max knowledge resource
	 * 
	 * @return an integer of max knowledge resource amount
	 */
	public int getMaxKnowledgeResource()
	{
		return maxKnowledgeResource;
	}

	/**
	 * setter of the max knowledge resource amount
	 * 
	 * @param maxKnowledgeResource
	 */
	public void setMaxKnowledgeResource(int maxKnowledgeResource)
	{
		this.maxKnowledgeResource = maxKnowledgeResource;
	}

	/**
	 * setter of the amount of knowledge which is added to knowledge resource -
	 * per turn-scholar
	 * 
	 * @param knowledgePerScholar
	 */
	public void setKnowledgePerScholar(int knowledgePerScholar)
	{
		this.knowledgePerScholar = knowledgePerScholar;
	}

	/**
	 * getter of the amount of knowledge which is added to knowledge resource -
	 * per turn-scholar
	 * 
	 * @return an integer of knowledge per scholar in each turn
	 */
	public int getKnowledgePerScholar()
	{
		return knowledgePerScholar;
	}

	/**
	 * getter of the initial lumber resource
	 * 
	 * @return an integer of initial lumber resource amount
	 */
	public int getInitialLumberResource()
	{
		return initialLumberResource;
	}

	/**
	 * setter of the initial lumber resource
	 * 
	 * @param initialLumberResource
	 */
	public void setInitialLumberResource(int initialLumberResource)
	{
		this.initialLumberResource = initialLumberResource;
	}

	/**
	 * getter of the initial food resource
	 * 
	 * @return an integer of initial food resource amount
	 */
	public int getInitialFoodResource()
	{
		return initialFoodResource;
	}

	/**
	 * setter of the initial food resource
	 * 
	 * @param initialFoodResource
	 */
	public void setInitialFoodResource(int initialFoodResource)
	{
		this.initialFoodResource = initialFoodResource;
	}

	/**
	 * getter of the initial gold resource
	 * 
	 * @return an integer of initial gold resource amount
	 */
	public int getInitialGoldResource()
	{
		return initialGoldResource;
	}

	/**
	 * setter of the initial gold resource
	 * 
	 * @param initialGoldResource
	 */
	public void setInitialGoldResource(int initialGoldResource)
	{
		this.initialGoldResource = initialGoldResource;
	}

	/**
	 * getter of the initial stone resource
	 * 
	 * @return an integer of initial stone resource amount
	 */
	public int getInitialStoneResource()
	{
		return initialStoneResource;
	}

	/**
	 * setter of the initial stone resource
	 * 
	 * @param initialStoneResource
	 */
	public void setInitialStoneResource(int initialStoneResource)
	{
		this.initialStoneResource = initialStoneResource;
	}

	/**
	 * getter of the initial knowledge resource
	 * 
	 * @return an integer of initial knowledge resource amount
	 */
	public int getInitialKnowledgeResource()
	{
		return initialKnowledgeResource;
	}

	/**
	 * setter of the initial knowledge resource
	 * 
	 * @param initialKnowledgeResource
	 */
	public void setInitialKnowledgeResource(int initialKnowledgeResource)
	{
		this.initialKnowledgeResource = initialKnowledgeResource;
	}

	/**
	 * Initialize the array of researches that must be done by a player, making
	 * a Map<ResearchType, Research>
	 */
	private void initializeResearches()
	{
		researches[0] = new Research(ResearchType.RESOURCES, 5, 0, 0, 0, 1);
		researchesMap.put(ResearchType.RESOURCES, researches[0]);
		researches[0].setAvailable(true);

		researches[1] = new Research(ResearchType.AGRICULTURE, 10, 0, 0, 0, 2);
		researchesMap.put(ResearchType.AGRICULTURE, researches[1]);

		researches[2] = new Research(ResearchType.MINING, 10, 0, 0, 0, 2);
		researchesMap.put(ResearchType.MINING, researches[2]);

		researches[3] = new Research(ResearchType.LUMBER_MILL, 10, 0, 0, 0, 2);
		researchesMap.put(ResearchType.LUMBER_MILL, researches[3]);

		researches[6] = new Research(ResearchType.REFINERY, 70, 30, 10, 120, 16);
		researchesMap.put(ResearchType.REFINERY, researches[6]);

		researches[4] = new Research(ResearchType.RANCHING, 35, 0, 20, 10, 8);
		researchesMap.put(ResearchType.RANCHING, researches[4]);

		researches[5] = new Research(ResearchType.IRRIGATION, 70, 10, 30, 120,
				20);
		researchesMap.put(ResearchType.IRRIGATION, researches[5]);

		researches[7] = new Research(ResearchType.CARPENTRY, 70, 0, 50, 120, 16);
		researchesMap.put(ResearchType.CARPENTRY, researches[7]);

		researches[8] = new Research(ResearchType.FISHING, 80, 0, 100, 50, 16);
		researchesMap.put(ResearchType.FISHING, researches[8]);

		researches[9] = new Research(ResearchType.ECONOMY, 20, 0, 10, 20, 4);
		researchesMap.put(ResearchType.ECONOMY, researches[9]);
		researches[9].setAvailable(true);

		researches[10] = new Research(ResearchType.MICRO_ECONOMY, 65, 15, 15,
				80, 10);
		researchesMap.put(ResearchType.MICRO_ECONOMY, researches[10]);

		researches[11] = new Research(ResearchType.MACRO_ECONOMY, 90, 25, 25,
				140, 24);
		researchesMap.put(ResearchType.MACRO_ECONOMY, researches[11]);

		researches[12] = new Research(ResearchType.PARSIMONY, 80, 10, 0, 130,
				12);
		researchesMap.put(ResearchType.PARSIMONY, researches[12]);

		researches[13] = new Research(ResearchType.TAX, 100, 0, 0, 150, 18);
		researchesMap.put(ResearchType.TAX, researches[13]);

		researches[14] = new Research(ResearchType.PROJECT_MANAGEMENT, 80, 0,
				0, 60, 10);
		researchesMap.put(ResearchType.PROJECT_MANAGEMENT, researches[14]);

		researches[15] = new Research(ResearchType.MARKET, 50, 50, 50, 50, 12);
		researchesMap.put(ResearchType.MARKET, researches[15]);

		researches[16] = new Research(ResearchType.TEAM_WORK, 100, 0, 0, 120,
				22);
		researchesMap.put(ResearchType.TEAM_WORK, researches[16]);

		researches[17] = new Research(
				ResearchType.STRATEGIC_PROJECT_MANAGEMENT, 100, 0, 0, 120, 16);
		researchesMap.put(ResearchType.STRATEGIC_PROJECT_MANAGEMENT,
				researches[17]);

		researches[18] = new Research(ResearchType.SCIENCE, 10, 0, 0, 0, 2);
		researchesMap.put(ResearchType.SCIENCE, researches[18]);
		researches[18].setAvailable(true);

		researches[19] = new Research(ResearchType.ALPHABET, 20, 0, 0, 25, 6);
		researchesMap.put(ResearchType.ALPHABET, researches[19]);

		researches[20] = new Research(ResearchType.MATHEMATICS, 20, 0, 0, 50,
				10);
		researchesMap.put(ResearchType.MATHEMATICS, researches[20]);

		researches[21] = new Research(ResearchType.ENGINEERING, 50, 50, 50,
				120, 24);
		researchesMap.put(ResearchType.ENGINEERING, researches[21]);

		researches[22] = new Research(ResearchType.SCHOOL, 30, 40, 30, 20, 4);
		researchesMap.put(ResearchType.SCHOOL, researches[22]);

		researches[23] = new Research(ResearchType.UNIVERSITY, 60, 80, 60, 100,
				12);
		researchesMap.put(ResearchType.UNIVERSITY, researches[23]);

		researches[24] = new Research(ResearchType.CERN, 150, 150, 150, 250, 28);
		researchesMap.put(ResearchType.CERN, researches[24]);

		// (researchType, gold, stone, lumber, knowledge, ETA)
		researches[25] = new Research(ResearchType.MILITARY, 20, 0, 20, 0, 4);
		researchesMap.put(ResearchType.MILITARY, researches[25]);

		researches[26] = new Research(ResearchType.HORSEBACK_RIDING, 70, 0, 40,
				15, 10);
		researchesMap.put(ResearchType.HORSEBACK_RIDING, researches[26]);

		researches[27] = new Research(ResearchType.BASIC_COMBAT, 50, 0, 50, 15,
				8);
		researchesMap.put(ResearchType.BASIC_COMBAT, researches[27]);

		researches[28] = new Research(ResearchType.ANIMAL_HUSBANDRY, 200, 0,
				300, 30, 20);
		researchesMap.put(ResearchType.ANIMAL_HUSBANDRY, researches[28]);

		researches[29] = new Research(ResearchType.ADVANCED_ARMORS, 150, 0,
				350, 30, 20);
		researchesMap.put(ResearchType.ADVANCED_ARMORS, researches[29]);

		// //////////////////////////////
		researches[1].addToNeededResearch(researchesMap
				.get(ResearchType.RESOURCES));
		researches[2].addToNeededResearch(researchesMap
				.get(ResearchType.RESOURCES));
		researches[3].addToNeededResearch(researchesMap
				.get(ResearchType.RESOURCES));

		researches[22].addToNeededResearch(researchesMap
				.get(ResearchType.SCIENCE));
		researches[19].addToNeededResearch(researchesMap
				.get(ResearchType.SCIENCE));

		researches[11].addToNeededResearch(researchesMap
				.get(ResearchType.ECONOMY));
		researches[14].addToNeededResearch(researchesMap
				.get(ResearchType.ECONOMY));

		researches[6].addToNeededResearch(researchesMap
				.get(ResearchType.MINING));

		researches[4].addToNeededResearch(researchesMap
				.get(ResearchType.AGRICULTURE));
		researches[5].addToNeededResearch(researchesMap
				.get(ResearchType.AGRICULTURE));

		researches[7].addToNeededResearch(researchesMap
				.get(ResearchType.LUMBER_MILL));

		researches[23].addToOptionalResearch(researchesMap
				.get(ResearchType.SCHOOL));
		researches[23].addToOptionalResearch(researchesMap
				.get(ResearchType.CARPENTRY));

		researches[20].addToNeededResearch(researchesMap
				.get(ResearchType.ALPHABET));

		researches[11].addToOptionalResearch(researchesMap
				.get(ResearchType.MATHEMATICS));
		researches[11].addToOptionalResearch(researchesMap
				.get(ResearchType.MICRO_ECONOMY));

		researches[15].addToNeededResearch(researchesMap
				.get(ResearchType.MICRO_ECONOMY));
		researches[15].addToNeededResearch(researchesMap
				.get(ResearchType.PROJECT_MANAGEMENT));

		researches[17].addToNeededResearch(researchesMap
				.get(ResearchType.PROJECT_MANAGEMENT));

		researches[8].addToNeededResearch(researchesMap
				.get(ResearchType.LUMBER_MILL));
		researches[8].addToNeededResearch(researchesMap
				.get(ResearchType.RANCHING));

		researches[24].addToNeededResearch(researchesMap
				.get(ResearchType.ENGINEERING));
		researches[24].addToNeededResearch(researchesMap
				.get(ResearchType.UNIVERSITY));

		researches[21].addToNeededResearch(researchesMap
				.get(ResearchType.MATHEMATICS));

		researches[13].addToNeededResearch(researchesMap
				.get(ResearchType.MACRO_ECONOMY));

		researches[12].addToNeededResearch(researchesMap
				.get(ResearchType.MACRO_ECONOMY));

		researches[16].addToNeededResearch(researchesMap
				.get(ResearchType.STRATEGIC_PROJECT_MANAGEMENT));

		researches[28].addToNeededResearch(researchesMap
				.get(ResearchType.HORSEBACK_RIDING));

		researches[26].addToNeededResearch(researchesMap
				.get(ResearchType.MILITARY));

		researches[27].addToNeededResearch(researchesMap
				.get(ResearchType.MILITARY));

		researches[29].addToNeededResearch(researchesMap
				.get(ResearchType.BASIC_COMBAT));

	}

	/**
	 * Initialize needed number of each resource, making a Map<ObjectType,
	 * int[][]> which maps each object to its needed resources in the below
	 * order: [gold, stone, lumber,food, knowledge]
	 */
	private void initializeNeededResources()
	{
		neededResources.put(ObjectType.BOAT,
				getNeededResourcesArray(20, 0, 80, 0, 0));

		neededResources.put(ObjectType.SCHOLAR,
				getNeededResourcesArray(40, 0, 0, 0, 0));

		neededResources.put(ObjectType.WORKER,
				getNeededResourcesArray(10, 0, 0, 0, 0));

		neededResources.put(ObjectType.PORT,
				getNeededResourcesArray(20, 160, 140, 0, 0));

		neededResources.put(ObjectType.MARKET,
				getNeededResourcesArray(20, 160, 140, 0, 0));

		neededResources.put(ObjectType.STOCKPILE,
				getNeededResourcesArray(10, 100, 100, 0, 0));

		neededResources.put(ObjectType.WOOD_CAMP,
				getNeededResourcesArray(20, 0, 20, 0, 0));

		neededResources.put(ObjectType.FARM,
				getNeededResourcesArray(10, 10, 10, 0, 0));

		neededResources.put(ObjectType.STONE_MINE,
				getNeededResourcesArray(30, 20, 20, 0, 0));

		neededResources.put(ObjectType.GOLD_MINE,
				getNeededResourcesArray(50, 50, 50, 0, 0));

		neededResources.put(ObjectType.UNIVERSITY,
				getNeededResourcesArray(0, 20, 20, 0, 0));

		neededResources.put(ObjectType.MAIN_BUILDING,
				getNeededResourcesArray(0, 10, 20, 0, 0));

		// ////////////////////////////////////
		neededResources.put(ObjectType.BARRACK,
				getNeededResourcesArray(20, 50, 200, 0, 0));

		neededResources.put(ObjectType.STABLE,
				getNeededResourcesArray(50, 50, 250, 0, 0));

		neededResources.put(ObjectType.SPEARMAN,
				getNeededResourcesArray(30, 0, 50, 0, 0));

		neededResources.put(ObjectType.AXMAN,
				getNeededResourcesArray(30, 0, 80, 0, 0));

		neededResources.put(ObjectType.HORSE_MACEMAN,
				getNeededResourcesArray(60, 0, 100, 0, 0));

		neededResources.put(ObjectType.AGILE_CAVALRY,
				getNeededResourcesArray(50, 0, 80, 0, 0));
	}

	/**
	 * Generate an array, based on the number of each resource
	 * 
	 * @param gold
	 *            an integer
	 * @param stone
	 *            an integer
	 * @param lumber
	 *            an integer
	 * @param knowledge
	 *            an integer
	 * @param food
	 *            an integer
	 * @return a 2d array of integer containing needed resources of an object
	 */
	private int[] getNeededResourcesArray(int gold, int stone, int lumber,
			int knowledge, int food)
	{
		int[] numOfNeededResources = new int[5];
		numOfNeededResources[0] = gold;
		numOfNeededResources[1] = stone;
		numOfNeededResources[2] = lumber;
		numOfNeededResources[3] = knowledge;
		numOfNeededResources[4] = food;

		return numOfNeededResources;
	}

	/**
	 * current food resource getter
	 * 
	 * @return the food resource in stock
	 */
	public Resource getFoodInStock()
	{
		return foodInStock;
	}

	/**
	 * setter of the current food resource in stock
	 * 
	 * @param foodInStock
	 */
	public void setFoodInStock(Resource foodInStock)
	{
		this.foodInStock = foodInStock;

		if (this.foodInStock.getAmount() > this.foodInStock.getMAX())
			this.foodInStock.setAmount(this.foodInStock.getMAX());

	}

	/**
	 * current lumber resource getter
	 * 
	 * @return the lumber resource in stock
	 */
	public Resource getLumberInStock()
	{
		return lumberInStock;
	}

	/**
	 * setter of the current lumber resource in stock
	 * 
	 * @param lumberInStock
	 */
	public void setLumberInStock(Resource lumberInStock)
	{
		this.lumberInStock = lumberInStock;

		if (this.lumberInStock.getAmount() > this.lumberInStock.getMAX())
			this.lumberInStock.setAmount(this.lumberInStock.getMAX());
	}

	/**
	 * current gold resource getter
	 * 
	 * @return the gold
	 * 
	 *         resource in stock
	 */
	public Resource getGoldInStock()
	{
		return goldInStock;
	}

	/**
	 * setter of the current gold resource in stock
	 * 
	 * @param goldInStock
	 */
	public void setGoldInStock(Resource goldInStock)
	{
		this.goldInStock = goldInStock;
		if (this.goldInStock.getAmount() > this.goldInStock.getMAX())
			this.goldInStock.setAmount(this.goldInStock.getMAX());
	}

	/**
	 * current knowledge resource getter
	 * 
	 * @return the knowledge resource in stock
	 */
	public Resource getKnowledgeInStock()
	{
		return knowledgeInStock;
	}

	/**
	 * setter of the current knowledge resource in stock
	 * 
	 * @param knowledgeInStock
	 */
	public void setKnowledgeInStock(Resource knowledgeInStock)
	{
		this.knowledgeInStock = knowledgeInStock;
	}

	/**
	 * current stone resource getter
	 * 
	 * @return the stone resource in stock
	 */
	public Resource getStoneInStock()
	{
		return stoneInStock;
	}

	/**
	 * setter of the current stone resource in stock
	 * 
	 * @param stoneInStock
	 */
	public void setStoneInStock(Resource stoneInStock)
	{
		this.stoneInStock = stoneInStock;

		if (this.stoneInStock.getAmount() > this.stoneInStock.getMAX())
			this.stoneInStock.setAmount(this.stoneInStock.getMAX());
	}

	/**
	 * building size map getter
	 * 
	 * @return a map, from ObjectType to an integer BuildingSize
	 */
	public java.util.Map<ObjectType, Integer> getBuildingSize()
	{
		return buildingSize;
	}

	/**
	 * turns to build map getter
	 * 
	 * @return a map, from ObjectType to an integer TurnsToBuild
	 */
	public java.util.Map<ObjectType, Integer> getTurnsToBuild()
	{
		return turnsToBuild;
	}

	/**
	 * get needed amount of resource for every game object
	 * 
	 * @return an array of integer showing the needed amount of each resource
	 *         per game object, by order: GOLD-LUMBER-STONE-KNOWLEDGE-FOOD
	 */
	public java.util.Map<ObjectType, int[]> getNeededResources()
	{
		return neededResources;
	}

	/**
	 * show if the player is able to collect tax or not
	 * 
	 * @return a boolean showing if the player can collect tax or not
	 */
	public boolean isTaxCollectable()
	{
		return isTaxCollectable;
	}

	/**
	 * set the status of tax collecting
	 * 
	 * @param a
	 *            boolean showing is tax collectable or not
	 */
	public void setTaxCollectable(boolean isTaxCollectable)
	{
		this.isTaxCollectable = isTaxCollectable;
	}

	public boolean isAbilityToBuildBarrack()
	{
		return abilityToBuildBarrack;
	}

	public void setAbilityToBuildBarrack(boolean abilityToBuildBarrack)
	{
		this.abilityToBuildBarrack = abilityToBuildBarrack;
	}

	public boolean isAbilityToBuildStable()
	{
		return abilityToBuildStable;
	}

	public void setAbilityToBuildStable(boolean abilityToBuildStable)
	{
		this.abilityToBuildStable = abilityToBuildStable;
	}

	public boolean isAbilityToTrainSpearman()
	{
		return abilityToTrainSpearman;
	}

	public void setAbilityToTrainSpearman(boolean abilityToTrainSpearman)
	{
		this.abilityToTrainSpearman = abilityToTrainSpearman;
	}

	public boolean isAbilityToTrainAxman()
	{
		return abilityToTrainAxman;
	}

	public void setAbilityToTrainAxman(boolean abilityToTrainAxman)
	{
		this.abilityToTrainAxman = abilityToTrainAxman;
	}

	public boolean isAbilityToTrainHorseMaceman()
	{
		return abilityToTrainHorseMaceman;
	}

	public void setAbilityToTrainHorseMaceman(boolean abilityToTrainHorseMaceman)
	{
		this.abilityToTrainHorseMaceman = abilityToTrainHorseMaceman;
	}

	public boolean isAbilityToTrainAgileCavalry()
	{
		return abilityToTrainAgileCavalry;
	}

	public void setAbilityToTrainAgileCavalry(boolean abilityToTrainAgileCavalry)
	{
		this.abilityToTrainAgileCavalry = abilityToTrainAgileCavalry;
	}

	public void setMaxCavalryrHealth(int maxCavalryrHealth)
	{
		this.maxCavalryrHealth = maxCavalryrHealth;
	}

	public void setMaxInfantryHealth(int maxInfantryHealth)
	{
		this.maxInfantryHealth = maxInfantryHealth;
	}

	public int getMaxCavalryrHealth()
	{
		return maxCavalryrHealth;
	}

	public int getMaxInfantryHealth()
	{
		return maxInfantryHealth;
	}

	public int getSpearmanScoreTowardCavalry()
	{
		return spearmanScoreTowardCavalry;
	}

	public void setSpearmanScoreTowardCavalry(int spearmanScoreTowardCavalry)
	{
		this.spearmanScoreTowardCavalry = spearmanScoreTowardCavalry;
	}

	public int getSpearmanScoreTowardInfantry()
	{
		return spearmanScoreTowardInfantry;
	}

	public void setSpearmanScoreTowardInfantry(int spearmanScoreTowardInfantry)
	{
		this.spearmanScoreTowardInfantry = spearmanScoreTowardInfantry;
	}

	public int getSpearmanDefenceScore()
	{
		return spearmanDefenceScore;
	}

	public void setSpearmanDefenceScore(int spearmanDefenceScore)
	{
		this.spearmanDefenceScore = spearmanDefenceScore;
	}

	public int getSpearmanAttackScore()
	{
		return spearmanAttackScore;
	}

	public void setSpearmanAttackScore(int spearmanAttackScore)
	{
		this.spearmanAttackScore = spearmanAttackScore;
	}

	public int getAxmanScoreTowardCavalry()
	{
		return axmanScoreTowardCavalry;
	}

	public void setAxmanScoreTowardCavalry(int axmanScoreTowardCavalry)
	{
		this.axmanScoreTowardCavalry = axmanScoreTowardCavalry;
	}

	public int getAxmanScoreTowardInfantry()
	{
		return axmanScoreTowardInfantry;
	}

	public void setAxmanScoreTowardInfantry(int axmanScoreTowardInfantry)
	{
		this.axmanScoreTowardInfantry = axmanScoreTowardInfantry;
	}

	public int getAxmanDefenceScore()
	{
		return axmanDefenceScore;
	}

	public void setAxmanDefenceScore(int axmanDefenceScore)
	{
		this.axmanDefenceScore = axmanDefenceScore;
	}

	public int getAxmanAttackScore()
	{
		return axmanAttackScore;
	}

	public void setAxmanAttackScore(int axmanAttackScore)
	{
		this.axmanAttackScore = axmanAttackScore;
	}

	public int getHorseMacemanScoreTowardCavalry()
	{
		return horseMacemanScoreTowardCavalry;
	}

	public void setHorseMacemanScoreTowardCavalry(
			int horseMacemanScoreTowardCavalry)
	{
		this.horseMacemanScoreTowardCavalry = horseMacemanScoreTowardCavalry;
	}

	public int getHorseMacemanScoreTowardInfantry()
	{
		return horseMacemanScoreTowardInfantry;
	}

	public void setHorseMacemanScoreTowardInfantry(
			int horseMacemanScoreTowardInfantry)
	{
		this.horseMacemanScoreTowardInfantry = horseMacemanScoreTowardInfantry;
	}

	public int getHorseMacemanDefenceScore()
	{
		return horseMacemanDefenceScore;
	}

	public void setHorseMacemanDefenceScore(int horseMacemanDefenceScore)
	{
		this.horseMacemanDefenceScore = horseMacemanDefenceScore;
	}

	public int getHorseMacemanAttackScore()
	{
		return horseMacemanAttackScore;
	}

	public void setHorseMacemanAttackScore(int horseMacemanAttackScore)
	{
		this.horseMacemanAttackScore = horseMacemanAttackScore;
	}

	public int getAgileCavalryScoreTowardCavalry()
	{
		return agileCavalryScoreTowardCavalry;
	}

	public void setAgileCavalryScoreTowardCavalry(
			int agileCavalryScoreTowardCavalry)
	{
		this.agileCavalryScoreTowardCavalry = agileCavalryScoreTowardCavalry;
	}

	public int getAgileCavalryScoreTowardInfantry()
	{
		return agileCavalryScoreTowardInfantry;
	}

	public void setAgileCavalryScoreTowardInfantry(
			int agileCavalryScoreTowardInfantry)
	{
		this.agileCavalryScoreTowardInfantry = agileCavalryScoreTowardInfantry;
	}

	public int getAgileCavalryDefenceScore()
	{
		return agileCavalryDefenceScore;
	}

	public void setAgileCavalryDefenceScore(int agileCavalryDefenceScore)
	{
		this.agileCavalryDefenceScore = agileCavalryDefenceScore;
	}

	public int getAgileCavalryAttackScore()
	{
		return agileCavalryAttackScore;
	}

	public void setAgileCavalryAttackScore(int agileCavalryAttackScore)
	{
		this.agileCavalryAttackScore = agileCavalryAttackScore;
	}

	public void setBuildingSize(java.util.Map<ObjectType, Integer> buildingSize)
	{
		this.buildingSize = buildingSize;
	}

	public void setAbilityToTrainSoldier(boolean b)
	{
		this.abilityToTrainAgileCavalry = b;
		this.abilityToTrainAxman = b;
		this.abilityToTrainHorseMaceman = b;
		this.abilityToTrainSpearman = b;
	}

	public boolean isAbilityToTrainSoldier()
	{
		if (abilityToTrainAgileCavalry && abilityToTrainAxman
				&& abilityToTrainHorseMaceman && abilityToTrainSpearman)
			return true;
		return false;

	}
}
