package gui.panels;

import gui.gui.GUIMapBlock;
import gui.gui.GUIGameObject;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameMapPanel extends JPanel
{
	private GUIMapBlock cells[][];

	public GameMapPanel(int width, int height, int rows, int columns)
	{
		this.cells = new GUIMapBlock[rows][columns];

		this.setBounds(0, 0, width, height);
		this.setLayout(null);

		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < columns; j++)
			{
				cells[i][j] = new GUIMapBlock(i, j, width / rows, height / columns);
				this.add(cells[i][j]);
			}
		}
		this.setVisible(true);
	}

	public void clearCells()
	{
		for (int i = 0; i < cells.length; i++)
		{
			for (int j = 0; j < cells[0].length; j++)
			{
				cells[i][j].clearUIObjectsSet();
			}
		}
	}

	public GUIMapBlock[][] getCells()
	{
		return cells;
	}

	public void addUIObjectToCell(GUIGameObject uiObject, int x, int y)
	{
		cells[x][y].addUIObject(uiObject);
	}

}
