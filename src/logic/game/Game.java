package logic.game;

import gui.gui.GUIGame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import logic.agents.workers.Worker;
import logic.map.GameMap;
import logic.types.ObjectType;
import logic.utilities.Point;
import mediator.Mediator;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
public class Game implements Serializable
{
	private static final long serialVersionUID = 1L;
	public int idNumberGameObject = -1;
	public int idNumberPlayer = -1;
	private GameMap map;
	private ArrayList<Player> players = new ArrayList<Player>();
	private static Game game = null;

	public int getIdNumberGameObject()
	{
		idNumberGameObject++;
		return idNumberGameObject;
	}

	public int getIdNumberPlayer()
	{
		idNumberPlayer++;
		return idNumberPlayer;
	}

	public static Game getGame(char[][] map, int[][] goldMap, int[][] stoneMap,
			int[][] lumberMap, int[][] foodMap)
	{
		if (game == null)
			game = new Game(map, goldMap, stoneMap, lumberMap, foodMap);

		return game;
	}

	public static Game getGame(int row, int column)
	{
		if (game == null)
			game = new Game(row, column);
		return game;
	}

	public static Game getGame()
	{
		return game;
	}

	/**
	 * also generate a map according to some arrays
	 * 
	 * @param map
	 *            : containing map block types
	 * @param goldMap
	 *            : containing the amount of gold in blocks
	 * @param stoneMap
	 *            : containing the amount of stone in blocks
	 * @param lumberMap
	 *            : containing the amount of lumber in blocks
	 * @param foodMap
	 *            : containing the amount of food in blocks
	 */
	@SuppressWarnings("unused")
	private Game(char[][] map, int[][] goldMap, int[][] stoneMap,
			int[][] lumberMap, int[][] foodMap)
	{
		this.map = new GameMap();
		this.map.generateMap(map, goldMap, stoneMap, lumberMap, foodMap);
		Mediator mediator = Mediator.getMediator();
		GUIGame uiCore = GUIGame
				.getGUIGame(600, 600, map.length, map[0].length);

		int numOfPlayers = Integer.parseInt(JOptionPane
				.showInputDialog("ENTER NUMBER OF PLAYERS:"));
		for (int i = 0; i < numOfPlayers; i++)
		{
			String playerNameAndPosition = JOptionPane
					.showInputDialog("ENTER NAME OF THE PLAYER\nAND THE DERP POSITION\nIN FORMAT name,x,y");
			addPlayer(
					new Point(
							Integer.parseInt(playerNameAndPosition.split(",")[1]),
							Integer.parseInt(playerNameAndPosition.split(",")[2])),
					playerNameAndPosition.split(",")[0]);
		}

	}

	@SuppressWarnings("unused")
	private Game(int row, int column)
	{
		this.map = new GameMap();
		this.map.generateRandomMap(row, column);
		Mediator mediator = Mediator.getMediator();
		GUIGame uiCore = GUIGame.getGUIGame(600, 600, row, column);
	}

	public void addPlayer(Point derpPosition)
	{
		Player player = new Player(this);
		Worker worker = new Worker(player);
		worker.setPosition(derpPosition);
		worker.setObjectType(ObjectType.WORKER);
		players.add(player);
	}

	public void addPlayer(Point derpPosition, String ID)
	{
		Player player = new Player(this);
		player.setId(ID);
		Worker worker = new Worker(player);
		worker.setPosition(derpPosition);
		worker.setObjectType(ObjectType.WORKER);
		players.add(player);
	}

	/**
	 * game map getter
	 * 
	 * @return the map of the game
	 */
	public GameMap getMap()
	{
		return map;
	}

	/**
	 * nextTurn the current player
	 */
	public void nextTurn(Player player)
	{
		player.startTurn();
	}

	private int counter = 0;

	public String getRecentTurn()
	{
		return players.get(counter).getId();
	}

	public void nextPlayer()
	{
		counter++;
		counter %= players.size();
		this.nextTurn(players.get(counter));
	}

	/**
	 * Getter of the players
	 * 
	 * @return Players array list
	 */
	public ArrayList<Player> getPlayers()
	{
		return players;
	}

	public void saveGame()
	{
		String fileName = JOptionPane
				.showInputDialog("ENTER THE DESTINATION WHERE\nYOU WANT TO SAVE YOUR GAME");

		Game gameSave = Game.getGame();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		File file = new File(fileName);

		try
		{
			FileOutputStream saveFile = new FileOutputStream(file, false);
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			save.writeObject(gameSave);
			save.close();
		} catch (IOException exception)
		{
			exception.printStackTrace();
		}

		JOptionPane.showMessageDialog(null,
				"YOUR GAME IS\nSAVED WITH " + players.size() + " PLAYERS\nAT:"
						+ dateFormat.format(cal.getTime()));
	}

	public static void loadGame()
	{
		String fileName = JOptionPane
				.showInputDialog("ENTER THE DESTINATION\nWHERE YOU SAVED YOUR GAME");
		File file = new File(fileName);

		try
		{
			FileInputStream loadFile = new FileInputStream(file);
			ObjectInputStream loadObject = new ObjectInputStream(loadFile);
			Object loadedGame = loadObject.readObject();
			game = (Game) loadedGame;
			loadObject.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		game.nextPlayer();
	}

}
