package gui.panels;

import gui.gui.GUIGame;
import gui.gui.GUIGameObject;
import gui.types.CommandType;
import gui.types.Type;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

@SuppressWarnings("serial")
public class ResearchPanel extends JPanel
{
	private JLabel researchLabel = new JLabel("Choose: ");
	private JComboBox<String> researchComboBox = new JComboBox<String>();
	private JButton researchButton = new JButton("RESEARCH");
	private JLabel numOfScholars = new JLabel();

	public String getResearchType()
	{
		return (String) (researchComboBox.getSelectedItem());
	}

	public void setEnabled(boolean flag)
	{
		researchComboBox.setEnabled(flag);
		researchButton.setEnabled(flag);
	}

	/**
	 * it fills the combo box with available researches!
	 * 
	 * @param an
	 *            array of Strings
	 */
	public void setResearchesOfComboBox(String[] researches)
	{
		researchComboBox.removeAllItems();
		for (int i = 0; i < researches.length; i++)
		{
			researchComboBox.addItem(researches[i]);
		}

	}

	public ResearchPanel(int x, int y, int width, int height)
	{
		researchComboBox.setEditable(false);
		SpringLayout springLayout = new SpringLayout();

		this.setBounds(x, y, width, height);
		this.setLayout(springLayout);

		springLayout.putConstraint(SpringLayout.NORTH, researchLabel,
				height / 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER,
				researchLabel, 0, SpringLayout.HORIZONTAL_CENTER, this);
		researchLabel.setForeground(Color.WHITE);
		this.add(researchLabel);

		springLayout.putConstraint(SpringLayout.NORTH, researchComboBox,
				height / 5, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER,
				researchComboBox, 0, SpringLayout.HORIZONTAL_CENTER, this);
		springLayout.putConstraint(SpringLayout.WEST, researchComboBox,
				width / 4, SpringLayout.WEST, this);
		this.add(researchComboBox);

		springLayout.putConstraint(SpringLayout.NORTH, researchButton,
				4 * height / 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER,
				researchButton, 0, SpringLayout.HORIZONTAL_CENTER, this);
		springLayout.putConstraint(SpringLayout.WEST, researchButton,
				width / 4, SpringLayout.WEST, this);
		this.add(researchButton);

		springLayout.putConstraint(SpringLayout.NORTH, numOfScholars,
				3 * height / 5, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER,
				numOfScholars, 0, SpringLayout.HORIZONTAL_CENTER, this);
		numOfScholars.setForeground(Color.WHITE);
		this.add(numOfScholars);

		this.setOpaque(true);
		this.setBackground(new Color(0, 11, 38));

		researchButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				GUIGameObject object = null;
				OUTER_BLOCK: for (int i = 0; i < GUIGame.getGUIGame()
						.getGameMapPanel().getCells().length; i++)
				{
					for (int j = 0; j < GUIGame.getGUIGame().getGameMapPanel()
							.getCells()[0].length; j++)
					{
						for (int l = 0; l < GUIGame.getGUIGame()
								.getGameMapPanel().getCells()[i][j]
								.getUiObjects().size(); l++)
						{

							if (GUIGame.getGUIGame().getGameMapPanel().getCells()[i][j]
									.getUiObjects().get(l).getType() == Type.UNIVERSITY)
							{
								object = GUIGame.getGUIGame().getGameMapPanel()
										.getCells()[i][j].getUiObjects().get(l);
								break OUTER_BLOCK;
							}
						}
					}
				}
				GUIGame.getGUIGame().analyseRequest(object, CommandType.RESEARCH);
			}
		});

	}

	public void setNumOfScholars(int num)
	{
		numOfScholars.setText("Number of Scholars: " + Integer.toString(num));
	}
}
