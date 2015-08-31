package logic.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import logic.agents.Human;
import logic.agents.boats.Boat;
import logic.agents.scholar.Scholar;
import logic.agents.soldiers.AgileCavalry;
import logic.agents.soldiers.Axman;
import logic.agents.soldiers.Cavalry;
import logic.agents.soldiers.HorseMaceman;
import logic.agents.soldiers.Infantry;
import logic.agents.soldiers.Soldier;
import logic.agents.soldiers.Spearman;
import logic.agents.workers.Worker;
import logic.buildings.Building;
import logic.buildings.army.Barrack;
import logic.buildings.army.Stable;
import logic.buildings.civil.Farm;
import logic.buildings.civil.GoldMine;
import logic.buildings.civil.MainBuilding;
import logic.buildings.civil.Market;
import logic.buildings.civil.Port;
import logic.buildings.civil.Stockpile;
import logic.buildings.civil.StoneMine;
import logic.buildings.civil.University;
import logic.buildings.civil.WoodCamp;
import logic.map.GameMap;
import mediator.Mediator;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
public class Player implements NextTurnable, Serializable
{
	private static final long serialVersionUID = 1L;
	private String id;
	private GeneralPlayerInfo playerInfo;
	private GameMap myMap;
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private ArrayList<Human> humans = new ArrayList<Human>();
	private ArrayList<Worker> workers = new ArrayList<Worker>();
	private ArrayList<Barrack> barracks = new ArrayList<Barrack>();
	private ArrayList<Stable> stables = new ArrayList<Stable>();
	private ArrayList<Boat> boats = new ArrayList<Boat>();
	private ArrayList<Scholar> scholars = new ArrayList<Scholar>();
	private ArrayList<Building> buildings = new ArrayList<Building>();
	private ArrayList<Farm> farms = new ArrayList<Farm>();
	private ArrayList<GoldMine> goldMines = new ArrayList<GoldMine>();
	private ArrayList<MainBuilding> mainBuildings = new ArrayList<MainBuilding>();
	private ArrayList<Market> markets = new ArrayList<Market>();
	private ArrayList<Port> ports = new ArrayList<Port>();
	private ArrayList<Stockpile> stockpiles = new ArrayList<Stockpile>();
	private ArrayList<StoneMine> stoneMines = new ArrayList<StoneMine>();
	private ArrayList<University> universities = new ArrayList<University>();
	private ArrayList<WoodCamp> woodCamps = new ArrayList<WoodCamp>();
	private ArrayList<Spearman> spearmans = new ArrayList<Spearman>();
	private ArrayList<Axman> axmans = new ArrayList<Axman>();
	private ArrayList<HorseMaceman> horseMacemans = new ArrayList<HorseMaceman>();
	private ArrayList<AgileCavalry> agileCavalries = new ArrayList<AgileCavalry>();
	private ArrayList<Cavalry> cavalries = new ArrayList<Cavalry>();
	private ArrayList<Infantry> infantries = new ArrayList<Infantry>();
	private ArrayList<Soldier> soldiers = new ArrayList<Soldier>();

	private ArrayList<String> doneActions = new ArrayList<String>();

	private Map<String, GameObject> getGameObjectByIdMap = new HashMap<String, GameObject>();

	private boolean[][] mapVisiblity;

	private Game myGame;

	/**
	 * 
	 * @param map
	 *            of the game
	 */
	public Player(Game game)
	{
		this.myGame = game;
		this.myMap = game.getMap();
		this.id = "PLAYER[" + Integer.toString(game.getIdNumberPlayer()) + "]";
		playerInfo = new GeneralPlayerInfo();
		initializeMapVisiblity();
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getId()
	{
		return id;
	}

	public Game getMyGame()
	{
		return myGame;
	}

	public ArrayList<String> getDoneActions()
	{
		return doneActions;
	}

	/**
	 * gameObjectById getter
	 * 
	 * @return a hash map from game objects to their IDs
	 */
	public Map<String, GameObject> getGameObjectByIdMap()
	{
		return getGameObjectByIdMap;
	}

	/**
	 * map visibility array getter
	 * 
	 * @return an array of boolean showing map visibility
	 */
	public boolean[][] getMapVisiblity()
	{
		return mapVisiblity;
	}

	/**
	 * initialize the visibility of all the map blocks at the beginning of the
	 * game and change all of them to false - e.g. not seen -
	 */
	private void initializeMapVisiblity()
	{
		mapVisiblity = new boolean[myMap.getMapBlocks().length][myMap
				.getMapBlocks()[0].length];

		for (int i = 0; i < mapVisiblity.length; i++)
		{
			for (int j = 0; j < mapVisiblity[0].length; j++)
			{
				mapVisiblity[i][j] = false;
			}
		}
	}

	public void startTurn()
	{
		Mediator.sendFromLogicToGUI(Game.getGame(), this);
	}

	/**
	 * Call the next turn of the player
	 */
	@Override
	public void nextTurn()
	{
		playerInfo.setAbilityToTrainWorker(true);
		playerInfo.setAbilityToTrainScholar(true);

		getPlayerInfo().getFoodInStock().addTo(boats.size() * 15);

		getPlayerInfo().getKnowledgeInStock().addTo(
				getPlayerInfo().getKnowledgePerScholar() * scholars.size());

		if (playerInfo.isTaxCollectable())
			collectTax();

		if (playerInfo.getFoodInStock().getAmount() >= scholars.size()
				+ workers.size() * 2 + soldiers.size() * 3)
		{
			playerInfo.getFoodInStock().removeFrom(
					scholars.size() + workers.size() * 2 + soldiers.size() * 3);
		} else
		{
			Random randomNumber = new Random();
			if (humans.size() > 0)
			{
				int hasToDieHuman = randomNumber.nextInt(humans.size());
				humans.get(hasToDieHuman).remove();
			} else
			{
				playerInfo.setAbilityToTrainWorker(false);
				playerInfo.setAbilityToTrainScholar(false);
				playerInfo.setAbilityToTrainSoldier(false);
			}
		}

		for (int i = 0; i < gameObjects.size(); i++)
		{
			if (gameObjects.get(i).getActionController() != null)
			{
				gameObjects.get(i).getActionController().nextTurn();
			}
		}
		myGame.nextPlayer();
	}

	/**
	 * game map getter
	 * 
	 * @return the map of the game
	 */
	public GameMap getMyMap()
	{
		return myMap;
	}

	/**
	 * worker array list getter
	 * 
	 * @return an array list of player's workers
	 */
	public ArrayList<Worker> getWorkers()
	{
		return workers;
	}

	/**
	 * boat array list getter
	 * 
	 * @return an array list of player's boats
	 */
	public ArrayList<Boat> getBoats()
	{
		return boats;
	}

	/**
	 * scholar array list getter
	 * 
	 * @return an array list of player's scholars
	 */
	public ArrayList<Scholar> getScholars()
	{
		return scholars;
	}

	/**
	 * building array list getter
	 * 
	 * @return an array list of player's buildings
	 */
	public ArrayList<Building> getBuildings()
	{
		return buildings;
	}

	/**
	 * farm array list getter
	 * 
	 * @return an array list of player's farms
	 */
	public ArrayList<Farm> getFarms()
	{
		return farms;
	}

	/**
	 * gold mine array list getter
	 * 
	 * @return an array list of player's gold mines
	 */
	public ArrayList<GoldMine> getGoldMines()
	{
		return goldMines;
	}

	public ArrayList<AgileCavalry> getAgileCavalries()
	{
		return agileCavalries;
	}

	public ArrayList<Axman> getAxmans()
	{
		return axmans;
	}

	public ArrayList<Cavalry> getCavalries()
	{
		return cavalries;
	}

	public ArrayList<HorseMaceman> getHorseMacemans()
	{
		return horseMacemans;
	}

	public ArrayList<Infantry> getInfantries()
	{
		return infantries;
	}

	public ArrayList<Soldier> getSoldiers()
	{
		return soldiers;
	}

	public ArrayList<Spearman> getSpearmans()
	{
		return spearmans;
	}

	/**
	 * main building array list getter
	 * 
	 * @return an array list of player's main buildings
	 */
	public ArrayList<MainBuilding> getMainBuildings()
	{
		return mainBuildings;
	}

	/**
	 * market array list getter
	 * 
	 * @return an array list of player's markets
	 */
	public ArrayList<Market> getMarkets()
	{
		return markets;
	}

	/**
	 * port array list getter
	 * 
	 * @return an array list of player's ports
	 */
	public ArrayList<Port> getPorts()
	{
		return ports;
	}

	/**
	 * stock pile array list getter
	 * 
	 * @return an array list of player's stock pile
	 */
	public ArrayList<Stockpile> getStockpiles()
	{
		return stockpiles;
	}

	/**
	 * stone mine array list getter
	 * 
	 * @return an array list of player's stone mines
	 */
	public ArrayList<StoneMine> getStoneMines()
	{
		return stoneMines;
	}

	/**
	 * university array list getter
	 * 
	 * @return an array list of player's game universities
	 */
	public ArrayList<University> getUniversities()
	{
		return universities;
	}

	/**
	 * wood camp array list getter
	 * 
	 * @return an array list of player's wood camps
	 */
	public ArrayList<WoodCamp> getWoodCamps()
	{
		return woodCamps;
	}

	/**
	 * game object array list getter
	 * 
	 * @return an array list of player's game objects
	 */
	public ArrayList<GameObject> getGameObjects()
	{
		return gameObjects;
	}

	/**
	 * humans array list getter
	 * 
	 * @return an array list of player's humans
	 */
	public ArrayList<Human> getHumans()
	{
		return humans;
	}

	/**
	 * barracks array list getter
	 * 
	 * @return an array list of player's barracks
	 */
	public ArrayList<Barrack> getBarracks()
	{
		return barracks;
	}

	/**
	 * stables array list getter
	 * 
	 * @return an array list of player's stables
	 */
	public ArrayList<Stable> getStables()
	{
		return stables;
	}

	/**
	 * general player info getter
	 * 
	 * @return player information
	 */
	public GeneralPlayerInfo getPlayerInfo()
	{
		return playerInfo;
	}

	/**
	 * collect tax the method of calculating tax per turn: (number of scholars +
	 * number of workers) / 4
	 */
	public void collectTax()
	{
		getPlayerInfo().getGoldInStock().addTo(humans.size() / 4);
	}

}
