package logic.actions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import logic.agents.boats.Boat;
import logic.agents.workers.Worker;
import logic.agents.soldiers.*;
import logic.game.GameObject;
import logic.game.Player;
import logic.types.MoveType;
import logic.utilities.Point;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
public class MoveAction extends Action
{
	private Point destination;

	private Player myPlayer;
	private GameObject gameObject;

	/**
	 * 
	 * @param my
	 *            player
	 * @param game
	 *            object
	 * @param destination
	 *            of moving
	 */
	public MoveAction(Player myPlayer, GameObject gameObject, Point destination)
	{
		this.destination = destination;
		this.myPlayer = myPlayer;
		this.gameObject = gameObject;
	}

	/**
	 * make the (at last 8) blocks around a worker visible in the map
	 * 
	 * @param block
	 *            that the rounding blocks must become visible
	 */
	public static void makeRoundBlocksVisible(Player myPlayer, Point block)
	{
		for (int i = Math.max(block.getX() - 1, 0); i < Math.min(myPlayer
				.getMyMap().getMapBlocks().length, block.getX() + 2); i++)
		{
			for (int j = Math.max(block.getY() - 1, 0); j < Math.min(myPlayer
					.getMyMap().getMapBlocks()[0].length, block.getY() + 2); j++)
			{
				myPlayer.getMapVisiblity()[i][j] = true;
			}
		}
	}

	/**
	 * BFS implementation used for finding shortest path to move from starting
	 * point to the destination
	 * 
	 * @return a MoveType showing whether the move action must be UP, RIGHT,
	 *         DOWN, LEFT, or NONE
	 */
	private MoveType BFS()
	{
		Queue<Point> queue = new LinkedList<Point>();
		ArrayList<Point> markedPoints = new ArrayList<Point>();
		ArrayList<Point> adjacentPoints = new ArrayList<Point>();
		ArrayList<Point> parents = new ArrayList<Point>();

		queue.offer(gameObject.getPosition());
		markedPoints.add(gameObject.getPosition());
		parents.add(null);

		while (!queue.isEmpty())
		{
			try
			{
				Point temp = queue.poll();
				if (temp.equals(destination))
					break;
				findAdjacentPoints(temp, adjacentPoints);
				for (int i = 0; i < adjacentPoints.size(); i++)
					if (!markedPoints.contains(adjacentPoints.get(i)))
					{
						queue.offer(adjacentPoints.get(i));
						markedPoints.add(adjacentPoints.get(i));
						parents.add(temp);
					}

			} catch (Exception exp)
			{
				System.err.println("Error, queue is empty");
			}

		}

		Point temp = destination;
		Point parent = null;
		for (int i = 0; i < markedPoints.size(); i++)
		{
			if (markedPoints.get(i).equals(temp))
			{
				parent = parents.get(i);
				break;
			}
		}
		if (parent == null)
			return MoveType.NONE;
		while (!parent.equals(gameObject.getPosition()) && parent != null)
		{
			for (int i = 0; i < markedPoints.size(); i++)
				if (parent != null && markedPoints.get(i).equals(parent))
				{
					temp = markedPoints.get(i);
					parent = parents.get(i);
				}
		}
		if (temp.getX() - gameObject.getPosition().getX() == -1)
			return MoveType.UP;
		if (temp.getX() - gameObject.getPosition().getX() == 1)
			return MoveType.DOWN;
		if (temp.getY() - gameObject.getPosition().getY() == -1)
			return MoveType.LEFT;
		if (temp.getY() - gameObject.getPosition().getY() == 1)
			return MoveType.RIGHT;

		return MoveType.NONE;

	}

	public void getAdjacentPoints(ArrayList<Point> adjacentPoints, Point point)
	{
		findAdjacentPoints(point, adjacentPoints);
	}

	/**
	 * a method filling an array list with adjacent points of a particular point
	 * 
	 * @param the
	 *            point
	 * @param adjacent
	 *            points array list(which will be filled by adjacent points)
	 */
	private void findAdjacentPoints(Point p, ArrayList<Point> adjacentPoints)
	{
		int mapX = myPlayer.getMyMap().getMapBlocks().length;
		int mapY = myPlayer.getMyMap().getMapBlocks()[0].length;

		adjacentPoints.clear();

		if (gameObject instanceof Worker)
		{
			if (p.getX() - 1 >= 0
					&& (!myPlayer.getMapVisiblity()[p.getX() - 1][p.getY()] || myPlayer
							.getMyMap().getMapBlocks()[p.getX() - 1][p.getY()]
							.isWalkableByWorker()))
			{
				adjacentPoints.add(new Point(p.getX() - 1, p.getY())); // LEFT
			}
			if (p.getX() + 1 < mapX
					&& (!myPlayer.getMapVisiblity()[p.getX() + 1][p.getY()] || myPlayer
							.getMyMap().getMapBlocks()[p.getX() + 1][p.getY()]
							.isWalkableByWorker()))
			{
				adjacentPoints.add(new Point(p.getX() + 1, p.getY())); // RIGHT
			}
			if (p.getY() + 1 < mapY
					&& (!myPlayer.getMapVisiblity()[p.getX()][p.getY() + 1] || myPlayer
							.getMyMap().getMapBlocks()[p.getX()][p.getY() + 1]
							.isWalkableByWorker()))
			{
				adjacentPoints.add(new Point(p.getX(), p.getY() + 1)); // DOWN
			}
			if (p.getY() - 1 >= 0
					&& (!myPlayer.getMapVisiblity()[p.getX()][p.getY() - 1] || myPlayer
							.getMyMap().getMapBlocks()[p.getX()][p.getY() - 1]
							.isWalkableByWorker()))
			{
				adjacentPoints.add(new Point(p.getX(), p.getY() - 1)); // UP
			}
		} else if (gameObject instanceof Boat)
		{
			if (p.getX() - 1 >= 0
					&& (!myPlayer.getMapVisiblity()[p.getX() - 1][p.getY()] || myPlayer
							.getMyMap().getMapBlocks()[p.getX() - 1][p.getY()]
							.isPassableByBoat()))
			{
				adjacentPoints.add(new Point(p.getX() - 1, p.getY())); // LEFT
			}
			if (p.getX() + 1 < mapX
					&& (!myPlayer.getMapVisiblity()[p.getX() + 1][p.getY()] || myPlayer
							.getMyMap().getMapBlocks()[p.getX() + 1][p.getY()]
							.isPassableByBoat()))
			{
				adjacentPoints.add(new Point(p.getX() + 1, p.getY())); // RIGHT
			}
			if (p.getY() + 1 < mapY
					&& (!myPlayer.getMapVisiblity()[p.getX()][p.getY() + 1] || myPlayer
							.getMyMap().getMapBlocks()[p.getX()][p.getY() + 1]
							.isPassableByBoat()))
			{
				adjacentPoints.add(new Point(p.getX(), p.getY() + 1)); // DOWN
			}
			if (p.getY() - 1 >= 0
					&& (!myPlayer.getMapVisiblity()[p.getX()][p.getY() - 1] || myPlayer
							.getMyMap().getMapBlocks()[p.getX()][p.getY() - 1]
							.isPassableByBoat()))
			{
				adjacentPoints.add(new Point(p.getX(), p.getY() - 1)); // UP
			}
		} else if (gameObject instanceof Soldier)
		{
			if (p.getX() - 1 >= 0
					&& (!myPlayer.getMapVisiblity()[p.getX() - 1][p.getY()] || myPlayer
							.getMyMap().getMapBlocks()[p.getX() - 1][p.getY()]
							.isWalkableByMilitary()))
			{
				adjacentPoints.add(new Point(p.getX() - 1, p.getY())); // LEFT
			}
			if (p.getX() + 1 < mapX
					&& (!myPlayer.getMapVisiblity()[p.getX() + 1][p.getY()] || myPlayer
							.getMyMap().getMapBlocks()[p.getX() + 1][p.getY()]
							.isWalkableByMilitary()))
			{
				adjacentPoints.add(new Point(p.getX() + 1, p.getY())); // RIGHT
			}
			if (p.getY() + 1 < mapY
					&& (!myPlayer.getMapVisiblity()[p.getX()][p.getY() + 1] || myPlayer
							.getMyMap().getMapBlocks()[p.getX()][p.getY() + 1]
							.isWalkableByMilitary()))
			{
				adjacentPoints.add(new Point(p.getX(), p.getY() + 1)); // DOWN
			}
			if (p.getY() - 1 >= 0
					&& (!myPlayer.getMapVisiblity()[p.getX()][p.getY() - 1] || myPlayer
							.getMyMap().getMapBlocks()[p.getX()][p.getY() - 1]
							.isWalkableByMilitary()))
			{
				adjacentPoints.add(new Point(p.getX(), p.getY() - 1)); // UP
			}
		}
	}

	/**
	 * Do the core action of the move action - without checking conditions
	 */
	@Override
	public void doAction()
	{
		MoveType moveType = BFS();
		Point nextBlock = new Point(gameObject.getPosition().getX(), gameObject
				.getPosition().getY());
		switch (moveType)
		{
			case DOWN:
				nextBlock = new Point(gameObject.getPosition().getX() + 1,
						gameObject.getPosition().getY());
				break;

			case UP:
				nextBlock = new Point(gameObject.getPosition().getX() - 1,
						gameObject.getPosition().getY());
				break;

			case RIGHT:
				nextBlock = new Point(gameObject.getPosition().getX(),
						gameObject.getPosition().getY() + 1);
				break;

			case LEFT:
				nextBlock = new Point(gameObject.getPosition().getX(),
						gameObject.getPosition().getY() - 1);
				break;
		}

		gameObject.setPosition(nextBlock);
		myPlayer.getDoneActions().add(
				"[" + gameObject.getId() + " MOVED TO " + nextBlock + "]");
		makeRoundBlocksVisible(myPlayer, nextBlock);
	}
}
