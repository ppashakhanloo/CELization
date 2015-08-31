import logic.buildings.civil.MainBuilding;
import logic.buildings.civil.Port;
import logic.buildings.civil.University;
import logic.game.Game;
import logic.game.Player;
import logic.utilities.Point;

public class Main
{
	public static void main(String[] args)
	{
		char[][] map =
		{
		{ 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W' },
		{ 'W', 'P', 'P', 'W', 'W', 'W', 'W', 'W', 'P', 'P', 'W' },
		{ 'W', 'P', 'P', 'P', 'M', 'M', 'M', 'P', 'P', 'P', 'W' },
		{ 'W', 'W', 'P', 'P', 'M', 'M', 'M', 'P', 'P', 'W', 'W' },
		{ 'W', 'W', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'W', 'W' },
		{ 'W', 'W', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'W', 'W' },
		{ 'W', 'W', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'W', 'W' },
		{ 'W', 'W', 'P', 'P', 'F', 'F', 'F', 'P', 'P', 'W', 'W' },
		{ 'W', 'W', 'P', 'P', 'F', 'F', 'F', 'P', 'P', 'W', 'W' },
		{ 'W', 'W', 'P', 'P', 'F', 'F', 'P', 'P', 'P', 'W', 'W' },
		{ 'W', 'W', 'P', 'P', 'F', 'F', 'F', 'P', 'P', 'W', 'W' },
		{ 'W', 'W', 'P', 'P', 'F', 'F', 'F', 'P', 'P', 'W', 'W' },
		{ 'W', 'W', 'P', 'P', 'F', 'F', 'F', 'P', 'P', 'W', 'W' },
		{ 'W', 'W', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'W', 'W' },
		{ 'W', 'W', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'W', 'W' },
		{ 'W', 'W', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'W', 'W' },
		{ 'W', 'W', 'P', 'P', 'M', 'M', 'M', 'P', 'P', 'W', 'W' },
		{ 'W', 'W', 'P', 'P', 'M', 'M', 'M', 'P', 'P', 'W', 'W' },
		{ 'W', 'W', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'W', 'W' },
		{ 'W', 'W', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'W', 'W' },
		{ 'W', 'W', 'P', 'P', 'M', 'M', 'M', 'P', 'P', 'W', 'W' },
		{ 'W', 'P', 'P', 'P', 'M', 'M', 'M', 'P', 'P', 'P', 'W' },
		{ 'W', 'P', 'P', 'W', 'W', 'W', 'W', 'W', 'P', 'P', 'W' },
		{ 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W' } };

		int[][] foodMap =
		{
		{ 1, 0, 0, 0, 0, 1, 0, 9, 9, 2, 1 },
		{ 0, 2, 0, 9, 9, 2, 0, 9, 9, 2, 4 },
		{ 0, 0, 0, 9, 6, 1, 0, 9, 9, 2, 6 },
		{ 0, 0, 0, 9, 9, 4, 0, 9, 9, 2, 4 },
		{ 0, 0, 0, 9, 9, 5, 0, 9, 9, 2, 3 },
		{ 0, 2, 0, 9, 9, 2, 0, 9, 9, 2, 3 },
		{ 0, 0, 0, 9, 6, 1, 0, 9, 9, 2, 1 },
		{ 0, 0, 0, 9, 9, 4, 6, 9, 9, 2, 0 },
		{ 0, 0, 0, 9, 9, 5, 4, 9, 9, 2, 0 },
		{ 1, 0, 0, 9, 9, 4, 0, 9, 9, 2, 0 },
		{ 0, 2, 0, 0, 0, 4, 1, 9, 9, 2, 0 },
		{ 0, 2, 0, 0, 0, 4, 3, 9, 9, 2, 4 },
		{ 0, 0, 0, 0, 0, 7, 0, 9, 9, 2, 0 },
		{ 1, 0, 0, 9, 9, 4, 5, 9, 9, 2, 0 },
		{ 0, 2, 0, 0, 0, 4, 0, 9, 9, 2, 0 },
		{ 0, 2, 0, 4, 0, 4, 0, 9, 9, 2, 0 },
		{ 0, 0, 6, 0, 0, 7, 0, 9, 9, 2, 0 },
		{ 0, 0, 0, 6, 0, 2, 7, 9, 9, 2, 0 },
		{ 0, 0, 0, 6, 0, 6, 0, 9, 9, 2, 0 },
		{ 1, 0, 0, 0, 0, 7, 0, 9, 9, 2, 0 },
		{ 1, 0, 0, 0, 0, 7, 0, 9, 9, 2, 0 },
		{ 0, 0, 0, 9, 9, 8, 0, 9, 9, 2, 0 },
		{ 0, 0, 0, 6, 9, 3, 1, 9, 9, 2, 0 },
		{ 0, 0, 0, 0, 0, 1, 0, 9, 9, 2, 0 } };
		int[][] goldMap =
		{
		{ 1, 0, 0, 0, 0, 1, 0, 9, 9, 2, 0 },
		{ 0, 2, 0, 9, 9, 2, 0, 9, 9, 2, 0 },
		{ 0, 0, 0, 9, 6, 1, 0, 9, 9, 2, 0 },
		{ 0, 0, 0, 9, 9, 4, 0, 9, 9, 2, 0 },
		{ 0, 0, 5, 6, 9, 5, 0, 6, 3, 2, 0 },
		{ 1, 0, 5, 9, 9, 4, 0, 9, 2, 2, 0 },
		{ 0, 2, 0, 0, 0, 4, 0, 9, 2, 2, 0 },
		{ 0, 0, 0, 9, 9, 4, 0, 9, 1, 2, 0 },
		{ 0, 0, 0, 9, 9, 5, 0, 7, 9, 2, 0 },
		{ 1, 0, 0, 9, 9, 4, 0, 9, 1, 2, 0 },
		{ 0, 2, 0, 0, 0, 4, 0, 9, 9, 2, 0 },
		{ 0, 2, 0, 0, 0, 4, 0, 9, 1, 2, 0 },
		{ 0, 0, 0, 0, 0, 7, 0, 3, 9, 2, 0 },
		{ 1, 0, 4, 9, 9, 4, 0, 9, 1, 2, 0 },
		{ 0, 2, 4, 0, 0, 4, 0, 9, 1, 2, 0 },
		{ 0, 2, 0, 0, 0, 4, 0, 2, 9, 2, 0 },
		{ 0, 0, 4, 0, 0, 7, 0, 9, 9, 2, 0 },
		{ 0, 0, 0, 6, 0, 2, 0, 9, 1, 2, 0 },
		{ 0, 0, 4, 6, 0, 6, 0, 1, 9, 2, 0 },
		{ 1, 0, 4, 0, 0, 7, 0, 9, 9, 2, 0 },
		{ 0, 0, 4, 9, 9, 8, 0, 9, 9, 2, 0 },
		{ 0, 0, 0, 6, 9, 3, 0, 9, 9, 2, 0 },
		{ 0, 0, 0, 6, 9, 3, 0, 9, 9, 2, 0 },
		{ 0, 0, 0, 0, 0, 1, 0, 9, 9, 2, 0 } };
		int[][] stoneMap =
		{
		{ 1, 0, 0, 0, 0, 1, 0, 9, 9, 2, 0 },
		{ 0, 2, 4, 9, 9, 2, 0, 9, 9, 2, 0 },
		{ 0, 0, 4, 4, 6, 1, 0, 9, 9, 2, 0 },
		{ 0, 0, 4, 9, 9, 4, 0, 5, 9, 2, 0 },
		{ 0, 0, 0, 9, 9, 5, 0, 6, 9, 2, 0 },
		{ 1, 0, 4, 9, 9, 4, 0, 5, 9, 2, 0 },
		{ 0, 0, 3, 5, 6, 1, 0, 4, 9, 2, 0 },
		{ 0, 0, 0, 9, 9, 4, 0, 5, 9, 2, 0 },
		{ 0, 0, 3, 5, 9, 5, 0, 3, 9, 2, 0 },
		{ 1, 0, 3, 9, 9, 4, 0, 9, 9, 2, 0 },
		{ 0, 2, 3, 0, 0, 4, 0, 5, 9, 2, 0 },
		{ 0, 2, 0, 5, 0, 4, 0, 5, 9, 2, 0 },
		{ 0, 0, 0, 0, 0, 7, 0, 5, 9, 2, 0 },
		{ 1, 0, 0, 9, 9, 4, 0, 9, 9, 2, 0 },
		{ 0, 2, 0, 0, 0, 4, 0, 9, 9, 2, 0 },
		{ 0, 2, 0, 0, 0, 4, 5, 9, 9, 2, 0 },
		{ 0, 0, 0, 0, 0, 7, 5, 9, 9, 2, 0 },
		{ 0, 0, 0, 6, 0, 2, 5, 9, 9, 2, 0 },
		{ 0, 0, 0, 6, 0, 6, 5, 9, 9, 2, 0 },
		{ 1, 0, 0, 0, 0, 7, 0, 9, 9, 2, 0 },
		{ 0, 0, 0, 9, 9, 8, 0, 9, 9, 2, 0 },
		{ 0, 0, 0, 6, 9, 3, 0, 9, 9, 2, 0 },
		{ 0, 0, 0, 6, 9, 3, 0, 9, 9, 2, 0 },
		{ 0, 0, 0, 0, 0, 1, 0, 9, 9, 2, 0 } };
		int[][] lumberMap =
		{
		{ 1, 0, 0, 0, 0, 1, 0, 9, 9, 2, 1 },
		{ 0, 2, 0, 9, 9, 2, 0, 9, 9, 2, 2, },
		{ 0, 0, 0, 9, 6, 1, 0, 9, 9, 2, 6 },
		{ 0, 0, 0, 9, 9, 4, 0, 9, 9, 2, 3 },
		{ 0, 0, 0, 9, 9, 5, 0, 9, 9, 2, 7 },
		{ 1, 0, 0, 9, 9, 4, 0, 9, 9, 2, 8 },
		{ 0, 2, 0, 0, 0, 4, 0, 9, 9, 2, 6 },
		{ 0, 0, 0, 9, 9, 4, 0, 9, 9, 2, 4 },
		{ 0, 0, 0, 9, 9, 5, 0, 9, 9, 2, 2 },
		{ 1, 0, 0, 9, 9, 4, 0, 9, 9, 2, 1 },
		{ 0, 2, 0, 5, 6, 4, 0, 9, 9, 2, 7 },
		{ 0, 2, 0, 6, 5, 4, 0, 9, 9, 2, 3 },
		{ 0, 0, 0, 0, 1, 7, 0, 9, 9, 2, 1 },
		{ 1, 0, 0, 9, 9, 4, 0, 9, 9, 2, 9 },
		{ 0, 2, 0, 0, 0, 4, 0, 9, 9, 2, 0 },
		{ 0, 2, 0, 0, 0, 4, 0, 9, 9, 2, 4 },
		{ 0, 3, 0, 0, 0, 7, 0, 9, 9, 2, 3 },
		{ 0, 0, 0, 6, 0, 2, 0, 9, 9, 2, 7 },
		{ 0, 0, 0, 6, 0, 6, 0, 9, 9, 2, 5 },
		{ 1, 0, 0, 0, 0, 7, 0, 9, 9, 2, 4 },
		{ 0, 0, 0, 9, 9, 8, 0, 9, 9, 2, 5 },
		{ 0, 0, 0, 6, 9, 3, 0, 9, 9, 2, 4 },
		{ 0, 0, 0, 6, 9, 3, 0, 9, 9, 2, 4 },
		{ 0, 0, 0, 0, 0, 1, 0, 9, 9, 2, 3 } };

		Game game = Game.getGame(map, goldMap, stoneMap, lumberMap, foodMap);

		// // LOAD CHOSEN MAP
		// String mapAddress = JOptionPane
		// .showInputDialog("TYPE THE NUMBER OF ONE OF THE MAPS:\n1.NORTH\n2.SEA\n3.CENTRAL");
		//
		// if (mapAddress.equals("1"))
		// {
		// mapAddress = "maps/NORTH.map";
		// } else if (mapAddress.equals("2"))
		// {
		// mapAddress = "maps/SEA.map";
		// } else if (mapAddress.equals("3"))
		// {
		// mapAddress = "maps/CENTRAL.map";
		// } else
		// mapAddress = "maps/CENTRAL.map";
		//
		// Object loadedMap = null;
		// ObjectInputStream mapStream;
		// try
		// {
		// mapStream = new ObjectInputStream(new FileInputStream(mapAddress));
		// loadedMap = mapStream.readObject();
		// mapStream.close();
		// } catch (Exception exception)
		// {
		// exception.printStackTrace();
		// }
		// System.out.println(loadedMap);
		//
		// Game.getGame().setMap((GameMap) loadedMap);
		// for (int i = 0; i < game.getMap().getMapBlocks().length; i++)
		// {
		// for (int j = 0; j < game.getMap().getMapBlocks()[0].length; j++)
		// {
		// System.out.print(game.getMap().getMapBlocks()[i][j]
		// .isWalkableByWorker() + " ");
		// }
		// System.out.println();
		// }

		// game.getPlayers().get(0).getPlayerInfo().getResearchesMap()
		// .get(ResearchType.ADVANCED_ARMORS).setFinished(true);

		// game.addPlayer(new Point(6, 6));
		// game.addPlayer(new Point(0, 0));
		// // game.addPlayer(new Point(0, 8));

		for (int i = 0; i < game.getPlayers().get(0).getMapVisiblity().length; i++)
		{
			for (int j = 0; j < game.getPlayers().get(0).getMapVisiblity()[0].length; j++)
			{
				game.getPlayers().get(0).getMapVisiblity()[i][j] = true;
			}
		}

		// Worker worker2 = new Worker(game.getPlayers().get(0));
		// worker2.setPosition(new Point(0, 0));
		// worker2.setObjectType(ObjectType.WORKER);
		// worker2.setId("derp2");
		// //
		// Spearman spearman = new Spearman(game.getPlayers().get(0));
		// spearman.setPosition(new Point(5, 5));
		// spearman.setObjectType(ObjectType.SPEARMAN);
		//
		// Spearman spearman2 = new Spearman(game.getPlayers().get(1));
		// spearman2.setPosition(new Point(5, 6));
		// spearman2.setObjectType(ObjectType.SPEARMAN);

		MainBuilding mainBuilding = new MainBuilding(game.getPlayers().get(0));
		mainBuilding.setPosition(new Point(6, 3));

		// Barrack barrack = new Barrack(game.getPlayers().get(0));
		// barrack.setPosition(new Point(6,3));

		University university = new University(game.getPlayers().get(0));
		university.setPosition(new Point(6, 3));

		// Stockpile stockpile = new Stockpile(game.getPlayers().get(0));
		// stockpile.setPosition(new Point(6, 3));

		// Stockpile stockpile2 = new Stockpile(game.getPlayers().get(0));
		// stockpile2.setPosition(new Point(12, 3));

		// Farm farm = new Farm(game.getPlayers().get(0));
		// farm.setPosition(new Point(3, 3));
		//
		// Market market = new Market(game.getPlayers().get(0));
		// market.setPosition(new Point(0, 0));

		Port port = new Port(game.getPlayers().get(0));
		port.setPosition(new Point(0,3));
		
		game.getPlayers().get(0).startTurn();
	}

	private void startTurn(Player p)
	{
		p.startTurn();
	}

	public void nextTurn(Player p)
	{
		startTurn(p);
	}
}
