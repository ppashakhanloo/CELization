package gui.panels;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel
{
	private JTextArea generalInfo;

	public void setGeneralInfo(int score, int tax, int numOfSoldiers,
			int numOfWorkers, int numOfBuildings, int numOfBoats)
	{
		generalInfo.setText("MY SCORE: " + score + "\n"
				+ "TAX COLLECTED THIS TURN: " + tax + "\n"
				+ "NUMBER OF SOLDIERS: " + numOfSoldiers + "\n"
				+ "NUMBER OF WORKERS: " + numOfWorkers + "\n"
				+ "NUMBER OF BUILDINGS: " + numOfBuildings + "\n"
				+ "NUMBER OF BOATS: " + numOfBoats);
	}

	public PlayerPanel(int x, int y, int width, int height)
	{
		this.setBounds(x, y, width, height);
		generalInfo = new JTextArea();
		generalInfo.setLineWrap(true);
		generalInfo.setOpaque(true);
		generalInfo.setForeground(Color.white);
		generalInfo.setBackground(new Color(0, 11, 38));
		generalInfo.setBounds(width, height, width, height);
		generalInfo.setMargin(new Insets(20, 30, 10, 10));

		this.add(generalInfo);

		this.setOpaque(true);
		this.setBackground(new Color(0, 11, 38));
	}
}
