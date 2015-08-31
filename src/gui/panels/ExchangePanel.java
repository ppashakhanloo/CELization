package gui.panels;

import gui.gui.GUIGame;
import gui.gui.GUIGameObject;
import gui.types.CommandType;
import gui.types.Type;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

@SuppressWarnings("serial")
public class ExchangePanel extends JPanel
{
	private JLabel fromLabel;
	private JTextField fromAmount;
	private JComboBox<String> fromResource;

	private JLabel toLabel;
	private JTextField toAmount;
	private JComboBox<String> toResource;

	private JButton exchangeButton;

	public ExchangePanel(int x, int y, int width, int height)
	{
		this.setBounds(x, y, width, height);
		SpringLayout springLayout = new SpringLayout();
		this.setLayout(springLayout);

		fromLabel = new JLabel("FROM :");
		fromLabel.setForeground(Color.WHITE);
		this.add(fromLabel);

		fromAmount = new JTextField(5);
		fromAmount.setText("0");
		this.add(fromAmount);

		fromResource = new JComboBox<String>();
		this.add(fromResource);

		toLabel = new JLabel("TO : ");
		toLabel.setForeground(Color.WHITE);
		this.add(toLabel);

		toAmount = new JTextField(5);

		toResource = new JComboBox<String>();
		this.add(toResource);

		exchangeButton = new JButton("EXCHANGE");
		this.add(exchangeButton);

		// PUT CONSTRAINTS
		springLayout.putConstraint(SpringLayout.NORTH, fromLabel, height / 10,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, fromLabel,
				width / 2, SpringLayout.WEST, this);

		springLayout.putConstraint(SpringLayout.NORTH, fromAmount, height / 10,
				SpringLayout.NORTH, fromLabel);
		springLayout.putConstraint(SpringLayout.EAST, fromAmount, 0,
				SpringLayout.HORIZONTAL_CENTER, this);

		springLayout.putConstraint(SpringLayout.NORTH, fromResource,
				height / 10, SpringLayout.NORTH, fromLabel);
		springLayout.putConstraint(SpringLayout.WEST, fromResource, 0,
				SpringLayout.HORIZONTAL_CENTER, this);

		springLayout.putConstraint(SpringLayout.NORTH, toLabel, height / 3,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, toLabel,
				width / 2, SpringLayout.WEST, this);

		springLayout.putConstraint(SpringLayout.NORTH, toAmount, height / 10,
				SpringLayout.NORTH, toLabel);
		springLayout.putConstraint(SpringLayout.EAST, toAmount, 0,
				SpringLayout.HORIZONTAL_CENTER, this);

		springLayout.putConstraint(SpringLayout.NORTH, toResource, height / 10,
				SpringLayout.NORTH, toLabel);
		springLayout.putConstraint(SpringLayout.WEST, toResource, 0,
				SpringLayout.HORIZONTAL_CENTER, this);

		springLayout.putConstraint(SpringLayout.WEST, exchangeButton, 0,
				SpringLayout.WEST, toAmount);
		springLayout.putConstraint(SpringLayout.EAST, exchangeButton, 0,
				SpringLayout.EAST, toResource);
		springLayout.putConstraint(SpringLayout.SOUTH, exchangeButton,
				height / 4, SpringLayout.NORTH, toAmount);

		fromResource.addItem("FOOD");
		fromResource.addItem("GOLD");
		fromResource.addItem("LUMBER");
		fromResource.addItem("STONE");

		toResource.addItem("FOOD");
		toResource.addItem("GOLD");
		toResource.addItem("LUMBER");
		toResource.addItem("STONE");

		fromResource.setEditable(false);
		toResource.setEditable(false);

		this.setOpaque(true);
		this.setBackground(new Color(0, 11, 38));

		exchangeButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				GUIGame.getGUIGame().analyseRequest(
						new GUIGameObject("", Type.MARKET,
								new ArrayList<Point>()), CommandType.EXCHANGE);
			}
		});
	}

	public void setEnabled(boolean flag)
	{
		fromResource.setEnabled(flag);
		toResource.setEnabled(flag);
		fromAmount.setEnabled(flag);
		exchangeButton.setEnabled(flag);
	}

	public String getFromResource()
	{
		return (String) (fromResource.getSelectedItem());
	}

	public String getToResource()
	{
		return (String) (toResource.getSelectedItem());
	}

	public String getAmuont()
	{
		return (String) (fromAmount.getText());
	}

}
