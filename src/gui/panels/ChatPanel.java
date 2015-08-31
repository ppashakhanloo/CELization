package gui.panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import logic.game.Game;

@SuppressWarnings("serial")
public class ChatPanel extends JPanel
{
	private JTextField textField;
	private JTextArea textArea;
	private JButton sendButton;
	private JScrollPane scrollPane;

	public ChatPanel(int x, int y, int width, int height)
	{
		this.setBounds(x, y, width, height);
		SpringLayout springLayout = new SpringLayout();
		this.setLayout(springLayout);

		textArea = new JTextArea("");
		springLayout.putConstraint(SpringLayout.NORTH, textArea, height / 10,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, textArea,
				3 * height / 5, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, textArea, width / 10,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, this, width / 10,
				SpringLayout.EAST, textArea);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		this.add(textArea);

		scrollPane = new JScrollPane(textArea);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, height / 10,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane,
				3 * height / 5, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, width / 10,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, this, width / 10,
				SpringLayout.EAST, scrollPane);

		this.add(scrollPane);

		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, height / 12,
				SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, textField, width / 10,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, textField,
				7 * width / 10, SpringLayout.WEST, this);
		this.add(textField);

		sendButton = new JButton("Send");
		springLayout.putConstraint(SpringLayout.NORTH, sendButton, height / 12,
				SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, sendButton, 0,
				SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, sendButton,
				7 * width / 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, sendButton, 0,
				SpringLayout.EAST, scrollPane);
		this.add(sendButton);

		this.setBorder(BorderFactory.createTitledBorder("Chat"));

		sendButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				textArea.setText(textArea.getText() + "\n"
						+ Game.getGame().getRecentTurn() + ": "
						+ textField.getText());
				textField.setText("");
			}
		});

		this.setOpaque(true);
		this.setBackground(new Color(0, 11, 38));
	}
}
