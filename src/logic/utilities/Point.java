/*
 * Class Point keeps X and Y of a point 
 */

package logic.utilities;

import java.io.Serializable;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
@SuppressWarnings("serial")
public class Point implements Serializable
{
	private int x;
	private int y;

	/**
	 * @param x
	 *            of the point
	 * @param y
	 *            of the point
	 */
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * @return an integer which is the x coordinate of the point
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * @return an integer which is the y coordinate of the point
	 */
	public int getY()
	{
		return y;
	}

	/**
	 * check the equality of two Points
	 */
	@Override
	public boolean equals(Object point)
	{
		if (this.getX() == ((Point) point).getX()
				&& this.getY() == ((Point) point).getY())
			return true;
		return false;
	}

	/**
	 * overridden toString for Point, showing the position in the format of (x,
	 * y)
	 */
	@Override
	public String toString()
	{
		return "(" + this.x + ", " + this.y + ")";
	}

}