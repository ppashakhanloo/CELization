package gui.gui;

import gui.panels.ChatPanel;
import gui.panels.CommandRequestPanel;
import gui.panels.GameMapPanel;
import gui.panels.InformationPanel;
import gui.panels.ObjectInfoPanel;
import gui.types.CommandType;
import gui.userrequest.AttackRequest;
import gui.userrequest.BuildRequest;
import gui.userrequest.ExchangeRequest;
import gui.userrequest.MoveRequest;
import gui.userrequest.ResearchRequest;
import gui.userrequest.SellRequest;
import gui.userrequest.TrainRequest;
import gui.userrequest.UserRequest;
import gui.userrequest.WorkRequest;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import logic.game.Game;
import logic.game.Player;
import mediator.Mediator;

/**
 * This class is singleton and works as the main frame for the game It has the
 * duty of analyzing real user request and converting mouse clicks and such
 * events to UserRequest
 * 
 * @author Pardis Pashakhanloo
 * 
 */
@SuppressWarnings("serial")
public class GUIGame extends JFrame
{
	private static GUIGame guiGame = null;
	private UserRequest userRequest;
	private GameMapPanel gameMapPanel;
	private ChatPanel chatPanel;
	private CommandRequestPanel commandRequestPanel;
	private InformationPanel informationPanel;
	private ObjectInfoPanel objectInfoPanel;
	ArrayList<UserRequest> userRequests = new ArrayList<UserRequest>();

	public static GUIGame getGUIGame(int width, int height, int rows,
			int columns)
	{
		if (guiGame == null)
		{
			guiGame = new GUIGame(width, height, rows, columns);
		}
		return guiGame;
	}

	public static GUIGame getGUIGame()
	{
		return guiGame;
	}

	public void addToUserRequests(UserRequest userRequest)
	{
		userRequests.add(userRequest);
	}

	public InformationPanel getInformationPanel()
	{
		return informationPanel;
	}

	public boolean isUserRequestNull()
	{
		if (userRequest == null)
			return true;
		return false;
	}

	public UserRequest getUserRequest()
	{
		return userRequest;
	}

	public void nextTurn()
	{
		Mediator.nextTurn();
		this.userRequests.clear();
	}

	public GameMapPanel getGameMapPanel()
	{
		return gameMapPanel;
	}

	/**
	 * Analyze requests that come from GUIBlock or panels which are created by
	 * users
	 * 
	 * @param uiObject
	 * @param commandType
	 */
	public void analyseRequest(GUIGameObject uiObject, CommandType commandType)
	{
		switch (commandType)
		{
			case MOVE:
			{
				if (isUserRequestNull())
				{
					this.userRequest = new MoveRequest(uiObject);
					commandRequestPanel.showCommand(commandRequestPanel
							.getText()
							+ "\n"
							+ "[CLICK ON A DESTINATION TO MOVE]");
				} else
				{
					((MoveRequest) (this.userRequest))
							.setDestination(((Point) uiObject
									.getOccupiedPoints().toArray()[0]));

					GUIGame.getGUIGame().addToUserRequests(userRequest);

					commandRequestPanel
							.showCommand(commandRequestPanel.getText()
									+ "\n"
									+ "[MOVE TO ("
									+ ((Point) (uiObject.getOccupiedPoints()
											.toArray()[0])).x
									+ ", "
									+ ((Point) (uiObject.getOccupiedPoints()
											.toArray()[0])).y + ")]");
					this.userRequest = null;
				}
			}
				break;
			case BUILD:
			{
				if (isUserRequestNull())
				{
					this.userRequest = new BuildRequest(uiObject);
					commandRequestPanel.showCommand(commandRequestPanel
							.getText()
							+ "\n"
							+ commandRequestPanel.getText()
							+ "\n" + "[CLICK ON A DESTINATION TO BUILD]");
				} else
				{
					((BuildRequest) (this.userRequest))
							.setDestination(((Point) uiObject
									.getOccupiedPoints().toArray()[0]));
					((BuildRequest) (this.userRequest))
							.setBuildingType(uiObject.getType());

					GUIGame.getGUIGame().addToUserRequests(userRequest);

					commandRequestPanel
							.showCommand(commandRequestPanel.getText()
									+ "\n"
									+ "[BUILD IN ("
									+ ((Point) (uiObject.getOccupiedPoints()
											.toArray()[0])).x
									+ ", "
									+ ((Point) (uiObject.getOccupiedPoints()
											.toArray()[0])).y + ")]");
					this.userRequest = null;
				}

			}
				break;
			case WORK:
			{
				if (isUserRequestNull())
				{
					this.userRequest = new WorkRequest(uiObject);
					commandRequestPanel.showCommand(commandRequestPanel
							.getText()
							+ "\n"
							+ "[CLICK ON A DESTINATION TO WORK]");
				} else
				{

					((WorkRequest) (this.userRequest))
							.setDestBuilding(uiObject);

					GUIGame.getGUIGame().addToUserRequests(userRequest);

					commandRequestPanel.showCommand(commandRequestPanel
							.getText()
							+ "\n"
							+ "[WORK IN "
							+ uiObject.getType() + "]");
					this.userRequest = null;
				}
			}
				break;
			case ATTACK:
			{
				if (isUserRequestNull())
				{
					this.userRequest = new AttackRequest(uiObject);
					commandRequestPanel.showCommand(commandRequestPanel
							.getText()
							+ "\n"
							+ "[CLICK ON A DESTINATION TO ATTACK]");
				} else
				{
					((AttackRequest) (this.userRequest))
							.setAttackObject(uiObject);

					GUIGame.getGUIGame().addToUserRequests(userRequest);

					commandRequestPanel.showCommand("[ATTACK "
							+ uiObject.getID() + "]");

					this.userRequest = null;
				}
			}
				break;
			case EXCHANGE:
			{
				gui.types.Type toResource = null;
				gui.types.Type fromResource = null;
				int amount;

				String toResourceStr = GUIGame.getGUIGame()
						.getInformationPanel().getExchangePanel()
						.getToResource();
				String fromResourceStr = GUIGame.getGUIGame()
						.getInformationPanel().getExchangePanel()
						.getFromResource();
				String amountStr = GUIGame.getGUIGame().getInformationPanel()
						.getExchangePanel().getAmuont();

				// Convert TO_RESOURCE
				if (toResourceStr.equals("FOOD"))
				{
					toResource = gui.types.Type.FOOD;
				} else if (toResourceStr.equals("GOLD"))
				{
					toResource = gui.types.Type.GOLD;
				} else if (toResourceStr.equals("STONE"))
				{
					toResource = gui.types.Type.STONE;
				} else if (toResourceStr.equals("LUMBER"))
				{
					toResource = gui.types.Type.LUMBER;
				}

				// Convert FROM_RESOURCE
				if (fromResourceStr.equals("FOOD"))
				{
					fromResource = gui.types.Type.FOOD;
				} else if (fromResourceStr.equals("GOLD"))
				{
					fromResource = gui.types.Type.GOLD;
				} else if (fromResourceStr.equals("STONE"))
				{
					fromResource = gui.types.Type.STONE;
				} else if (fromResourceStr.equals("LUMBER"))
				{
					fromResource = gui.types.Type.LUMBER;
				}

				amount = Integer.parseInt(amountStr);

				this.userRequest = new ExchangeRequest(uiObject, fromResource,
						toResource, amount);

				commandRequestPanel
						.showCommand(commandRequestPanel.getText()
								+ "\n"
								+ "[EXCHANGE "
								+ ((ExchangeRequest) userRequest).getAmount()
								+ " "
								+ ((ExchangeRequest) userRequest)
										.getFromResource()
								+ " WITH "
								+ ((ExchangeRequest) userRequest)
										.getToResource() + "]");
				GUIGame.getGUIGame().addToUserRequests(userRequest);
				this.userRequest = null;
			}
				break;
			case RESEARCH:
			{
				this.userRequest = new ResearchRequest(uiObject, GUIGame
						.getGUIGame().getInformationPanel().getResearchPanel()
						.getResearchType());
				commandRequestPanel.showCommand(commandRequestPanel.getText()
						+ "\n" + "[RESEARCH "
						+ ((ResearchRequest) userRequest).getResearch() + "]");
				GUIGame.getGUIGame().addToUserRequests(userRequest);
				this.userRequest = null;
			}
				break;
			case SELL:
			{
				this.userRequest = new SellRequest(uiObject, uiObject.getType());

				commandRequestPanel.showCommand(commandRequestPanel.getText()
						+ "\n" + "[SELL "
						+ ((SellRequest) userRequest).getUiObject().getID()
						+ " (" + ((SellRequest) userRequest).getSellType()
						+ ")]");
				GUIGame.getGUIGame().addToUserRequests(userRequest);
				this.userRequest = null;
			}
				break;
			case TRAIN_SCHOLAR:
			{
				this.userRequest = new TrainRequest(uiObject);
				commandRequestPanel.showCommand(commandRequestPanel.getText()
						+ "\n" + "[TRAIN " + "SCHOLAR" + "]");

				((TrainRequest) userRequest)
						.setTrainType(gui.types.Type.SCHOLAR);

				GUIGame.getGUIGame().addToUserRequests(userRequest);

				this.userRequest = null;
			}
				break;
			case TRAIN_WORKER:
			{
				this.userRequest = new TrainRequest(uiObject);
				commandRequestPanel.showCommand(commandRequestPanel.getText()
						+ "\n" + "[TRAIN " + "WORKER" + "]");

				((TrainRequest) userRequest)
						.setTrainType(gui.types.Type.WORKER);

				GUIGame.getGUIGame().addToUserRequests(userRequest);

				this.userRequest = null;
			}
				break;
			case TRAIN_AGILE_CAVALRY:
			{
				this.userRequest = new TrainRequest(uiObject);
				commandRequestPanel.showCommand(commandRequestPanel.getText()
						+ "\n" + "[TRAIN " + "AGILE_CAVALRY" + "]");

				((TrainRequest) userRequest)
						.setTrainType(gui.types.Type.AGILE_CAVALRY);

				GUIGame.getGUIGame().addToUserRequests(userRequest);

				this.userRequest = null;
			}
				break;
			case TRAIN_HORSE_MACEMAN:
			{
				this.userRequest = new TrainRequest(uiObject);
				commandRequestPanel.showCommand(commandRequestPanel.getText()
						+ "\n" + "[TRAIN " + "HORSE_MACEMAN" + "]");

				((TrainRequest) userRequest)
						.setTrainType(gui.types.Type.HORSE_MACEMAN);

				GUIGame.getGUIGame().addToUserRequests(userRequest);

				this.userRequest = null;
			}
				break;
			case TRAIN_AXMAN:
			{
				this.userRequest = new TrainRequest(uiObject);
				commandRequestPanel.showCommand(commandRequestPanel.getText()
						+ "\n" + "[TRAIN " + "AXMAN" + "]");

				((TrainRequest) userRequest).setTrainType(gui.types.Type.AXMAN);

				GUIGame.getGUIGame().addToUserRequests(userRequest);

				this.userRequest = null;
			}
				break;
			case TRAIN_SPEARMAN:
			{
				this.userRequest = new TrainRequest(uiObject);
				commandRequestPanel.showCommand(commandRequestPanel.getText()
						+ "\n" + "[TRAIN " + "SPEARMAN" + "]");

				((TrainRequest) userRequest)
						.setTrainType(gui.types.Type.SPEARMAN);

				GUIGame.getGUIGame().addToUserRequests(userRequest);

				this.userRequest = null;
			}
				break;
			case TRAIN_BOAT:
			{
				this.userRequest = new TrainRequest(uiObject);
				commandRequestPanel.showCommand(commandRequestPanel.getText()
						+ "\n" + "[TRAIN " + "BOAT" + "]");

				((TrainRequest) userRequest).setTrainType(gui.types.Type.BOAT);

				GUIGame.getGUIGame().addToUserRequests(userRequest);

				this.userRequest = null;
			}
				break;
		}
	}

	public CommandRequestPanel getCommandRequestPanel()
	{
		return commandRequestPanel;
	}

	public ArrayList<UserRequest> getUserRequests()
	{
		return userRequests;
	}

	public static void addSelectedUIObjectToInfoPanel(GUIGameObject uiObject)
	{
		ObjectInfoPanel.setUIObject(uiObject);
	}

	private GUIGame(int width, int height, int rows, int columns)
	{
		GraphicsEnvironment e = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		setMaximizedBounds(e.getMaximumWindowBounds());
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(e.getMaximumWindowBounds().width,
				e.getMaximumWindowBounds().height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("FILE");
		fileMenu.setMnemonic('F');

		JMenuItem saveGame = new JMenuItem("SAVE");
		JMenuItem loadGame = new JMenuItem("LOAD");
		JMenuItem quitGame = new JMenuItem("QUIT");

		fileMenu.add(saveGame);
		fileMenu.add(loadGame);
		fileMenu.add(quitGame);

		menuBar.add(fileMenu);

		saveGame.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Game.getGame().saveGame();
			}
		});

		loadGame.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Game.loadGame();
			}
		});

		quitGame.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});

		menuBar.setOpaque(true);
		menuBar.setBorder(null);
		menuBar.setForeground(Color.WHITE);
		setJMenuBar(menuBar);
		setUndecorated(true);

		gameMapPanel = new GameMapPanel(e.getMaximumWindowBounds().width,
				height, rows, columns);
		add(gameMapPanel);

		informationPanel = new InformationPanel(0, height,
				e.getMaximumWindowBounds().width / 4,
				e.getMaximumWindowBounds().height - height - 25);
		add(informationPanel);

		commandRequestPanel = new CommandRequestPanel(
				e.getMaximumWindowBounds().width / 4, height,
				e.getMaximumWindowBounds().width / 4,
				e.getMaximumWindowBounds().height - height - 25);
		add(commandRequestPanel);

		objectInfoPanel = new ObjectInfoPanel(
				e.getMaximumWindowBounds().width / 2, height,
				e.getMaximumWindowBounds().width / 4,
				e.getMaximumWindowBounds().height - height - 25);
		add(objectInfoPanel);

		chatPanel = new ChatPanel(3 * e.getMaximumWindowBounds().width / 4,
				height, e.getMaximumWindowBounds().width / 4,
				e.getMaximumWindowBounds().height - height - 25);
		add(chatPanel);

		this.setFocusable(true);
		this.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
				{
					if (userRequest != null)
						commandRequestPanel.showCommand(commandRequestPanel
								.getText() + "\n" + "[LAST COMMAND REMOVED]");
					userRequest = null;
				}
			}
		});

		setVisible(true);
	}

	public static GUIGameObject sendInfoToInfoPanel(GUIGameObject uiObject)
	{
		return uiObject;
	}

	/**
	 * This method adds GUIGameObjects to the game panel, in order to
	 * reconstruct the GUI
	 * 
	 * @param uiObjects
	 * @param me
	 */
	public void reconstructGUI(ArrayList<GUIGameObject> uiObjects, Player me)
	{
		gameMapPanel.clearCells();

		for (int i = 0; i < uiObjects.size(); i++)
		{
			GUIGameObject uiObject = uiObjects.get(i);
			for (Point point : uiObject.getOccupiedPoints())
				gameMapPanel.addUIObjectToCell(uiObject, point.x, point.y);
		}
	}

	public ObjectInfoPanel getObjectInfoPanel()
	{
		return objectInfoPanel;
	}
}
