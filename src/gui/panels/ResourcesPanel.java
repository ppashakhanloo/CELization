package gui.panels;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

@SuppressWarnings("serial")
public class ResourcesPanel extends JPanel
{
	private JTextField goldField;
	private JTextField stoneField;
	private JTextField lumberField;
	private JTextField knowledgeField;
	private JTextField foodField;

	private JLabel goldLabel;
	private JLabel stoneLabel;
	private JLabel lumberLabel;
	private JLabel knowledgeLabel;
	private JLabel foodLabel;

	public void setPanel(int gold, int stone, int lumber, int knowledge,
			int food)
	{
		goldField.setText(Integer.toString(gold));
		stoneField.setText(Integer.toString(stone));
		lumberField.setText(Integer.toString(lumber));
		foodField.setText(Integer.toString(food));
		knowledgeField.setText(Integer.toString(knowledge));
	}

	public ResourcesPanel(int x, int y, int width, int height)
	{
		SpringLayout springLayout = new SpringLayout();

		this.setBounds(x, y, width, height);
		this.setLayout(springLayout);

		goldLabel = new JLabel("GOLD :");
		goldLabel.setForeground(Color.WHITE);
		goldLabel.setAlignmentY(2f);
		goldField = new JTextField(5);
		goldField.setForeground(Color.WHITE);
		goldField.setOpaque(false);
		goldField.setEditable(false);

		stoneLabel = new JLabel("STONE :");
		stoneLabel.setForeground(Color.WHITE);
		stoneField = new JTextField(5);
		stoneField.setForeground(Color.WHITE);
		stoneField.setOpaque(false);
		stoneField.setEditable(false);

		lumberLabel = new JLabel("LUMBER :");
		lumberLabel.setForeground(Color.WHITE);
		lumberField = new JTextField(5);
		lumberField.setForeground(Color.WHITE);
		lumberField.setOpaque(false);
		lumberField.setEditable(false);

		knowledgeLabel = new JLabel("KNOWLEDGE :");
		knowledgeLabel.setForeground(Color.WHITE);
		knowledgeField = new JTextField(5);
		knowledgeField.setForeground(Color.WHITE);
		knowledgeField.setOpaque(false);
		knowledgeField.setEditable(false);

		foodLabel = new JLabel("FOOD :");
		foodLabel.setForeground(Color.WHITE);
		foodField = new JTextField(5);
		foodField.setForeground(Color.WHITE);
		foodField.setOpaque(false);
		foodField.setEditable(false);
		// //////FOOD//////////////
		springLayout.putConstraint(SpringLayout.NORTH, foodLabel, height / 14,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, foodLabel, width / 10,
				SpringLayout.WEST, this);

		springLayout.putConstraint(SpringLayout.NORTH, foodField, 0,
				SpringLayout.NORTH, foodLabel);
		springLayout.putConstraint(SpringLayout.WEST, foodField, width / 4,
				SpringLayout.WEST, foodLabel);

		// /////////////STONE//////////////
		springLayout.putConstraint(SpringLayout.NORTH, stoneLabel, height / 7,
				SpringLayout.NORTH, foodLabel);
		springLayout.putConstraint(SpringLayout.WEST, stoneLabel, width / 10,
				SpringLayout.WEST, this);

		springLayout.putConstraint(SpringLayout.NORTH, stoneField, 0,
				SpringLayout.NORTH, stoneLabel);
		springLayout.putConstraint(SpringLayout.WEST, stoneField, width / 4,
				SpringLayout.WEST, stoneLabel);
		// ////GOLD///////////////////////
		springLayout.putConstraint(SpringLayout.NORTH, goldLabel, height / 7,
				SpringLayout.NORTH, stoneLabel);
		springLayout.putConstraint(SpringLayout.WEST, goldLabel, width / 10,
				SpringLayout.WEST, this);

		springLayout.putConstraint(SpringLayout.NORTH, goldField, 0,
				SpringLayout.NORTH, goldLabel);
		springLayout.putConstraint(SpringLayout.WEST, goldField, width / 4,
				SpringLayout.WEST, goldLabel);
		// /////LUMBER////////////////////
		springLayout.putConstraint(SpringLayout.NORTH, lumberLabel, height / 7,
				SpringLayout.NORTH, goldLabel);
		springLayout.putConstraint(SpringLayout.WEST, lumberLabel, width / 10,
				SpringLayout.WEST, this);

		springLayout.putConstraint(SpringLayout.NORTH, lumberField, 0,
				SpringLayout.NORTH, lumberLabel);
		springLayout.putConstraint(SpringLayout.WEST, lumberField, width / 4,
				SpringLayout.WEST, lumberLabel);
		// /////////KNOWLEDGE///////////////
		springLayout.putConstraint(SpringLayout.NORTH, knowledgeLabel,
				height / 7, SpringLayout.NORTH, lumberLabel);
		springLayout.putConstraint(SpringLayout.WEST, knowledgeLabel,
				width / 10, SpringLayout.WEST, this);

		springLayout.putConstraint(SpringLayout.NORTH, knowledgeField, 0,
				SpringLayout.NORTH, knowledgeLabel);
		springLayout.putConstraint(SpringLayout.WEST, knowledgeField,
				width / 4, SpringLayout.WEST, knowledgeLabel);
		// /////////END!!///////////////
		this.add(foodLabel);
		this.add(foodField);
		this.add(stoneLabel);
		this.add(stoneField);
		this.add(goldLabel);
		this.add(goldField);
		this.add(lumberLabel);
		this.add(lumberField);
		this.add(knowledgeLabel);
		this.add(knowledgeField);
		//

		this.setOpaque(true);
		this.setBackground(new Color(0, 11, 38));

	}
}
