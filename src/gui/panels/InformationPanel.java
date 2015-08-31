package gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class InformationPanel extends JPanel
{
	private ResourcesPanel resourcesPanel;
	private PlayerPanel playerPanel;
	private ExchangePanel exchangePanel;
	private ResearchPanel researchPanel;
	private JTabbedPane tabbedPane;

	public ResourcesPanel getResourcesPanel()
	{
		return resourcesPanel;
	}

	public PlayerPanel getPlayerPanel()
	{
		return playerPanel;
	}

	public ExchangePanel getExchangePanel()
	{
		return exchangePanel;
	}

	public ResearchPanel getResearchPanel()
	{
		return researchPanel;
	}

	public InformationPanel(int x, int y, int width, int height)
	{
		this.setBounds(x, y, width, height);
		this.setLayout(new BorderLayout());
		this.setOpaque(true);
		this.setBackground(new Color(0, 11, 38));

		resourcesPanel = new ResourcesPanel(x, y, width, height);
		playerPanel = new PlayerPanel(x, y, width, height);
		exchangePanel = new ExchangePanel(x, y, width, height);
		researchPanel = new ResearchPanel(x, y, width, height);

		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Player", playerPanel);
		tabbedPane.addTab("Resource", resourcesPanel);
		tabbedPane.addTab("Exchange", exchangePanel);
		tabbedPane.addTab("Research", researchPanel);

		this.add(tabbedPane, BorderLayout.CENTER);

	}
}
