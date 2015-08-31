package gui.panels;

import gui.gui.GUIGame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import logic.game.Game;

@SuppressWarnings("serial")
public class CommandRequestPanel extends JPanel
{
	private JTextArea infoTextArea;
	private JButton nextTurnButton;
	private JLabel whoseTurnPlayer;

	public CommandRequestPanel(int x, int y, int width, int height)
	{
		this.setBounds(x, y, width, height);
		this.setLayout(null);

		this.setOpaque(true);
		this.setBackground(new Color(0, 11, 38));

		whoseTurnPlayer = new JLabel();
		whoseTurnPlayer.setBounds(3 * width / 4, 0, width / 4, height / 4);
		whoseTurnPlayer.setOpaque(true);
		whoseTurnPlayer.setBackground(new Color(0, 11, 38));
		whoseTurnPlayer.setForeground(Color.WHITE);
		whoseTurnPlayer.setBorder(BorderFactory.createTitledBorder(" "));
		this.add(whoseTurnPlayer);

		infoTextArea = new JTextArea();
		infoTextArea.setBounds(0, 0, 3 * width / 4, height);
		infoTextArea.setOpaque(true);
		infoTextArea.setBackground(new Color(0, 11, 38));
		infoTextArea.setForeground(Color.WHITE);
		infoTextArea.setBorder(BorderFactory.createTitledBorder("Commands"));
		this.add(infoTextArea);
		infoTextArea.setEditable(false);
		infoTextArea.setAutoscrolls(true);

		nextTurnButton = new JButton("NEXT TURN");
		nextTurnButton.setBounds(3 * width / 4, height / 4, width / 4, height);
		nextTurnButton.setOpaque(true);
		nextTurnButton.setForeground(Color.WHITE);
		nextTurnButton.setBackground(new Color(0, 11, 38));
		nextTurnButton.setBorder(BorderFactory.createTitledBorder(""));

		// In order to make the jbutton look flat
		nextTurnButton.setFocusPainted(false);
		nextTurnButton.setContentAreaFilled(false);

		this.add(nextTurnButton);

		nextTurnButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				GUIGame.getGUIGame().nextTurn();

			}
		});

	}

	public void setWhoIsThisTurn()
	{
		whoseTurnPlayer.setText(Game.getGame().getRecentTurn());
	}

	public String getText()
	{
		return infoTextArea.getText();
	}

	public void showCommand(String command)
	{
		infoTextArea.setText(command);
	}

}
