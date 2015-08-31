package gui.gui;

import gui.types.CommandType;
import gui.types.Type;
import gui.userrequest.AttackRequest;
import gui.userrequest.BuildRequest;
import gui.userrequest.MoveRequest;
import gui.userrequest.TrainRequest;
import gui.userrequest.UserRequest;
import gui.userrequest.WorkRequest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class GUIMapBlock extends JLabel
{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private int xID;
	@SuppressWarnings("unused")
	private int yID;

	private int width;
	private int height;

	private Type blockType = Type.INVISIBLE_BLOCK;

	private ArrayList<GUIGameObject> uiObjects = new ArrayList<GUIGameObject>();

	private BufferedImage blockImage;

	public GUIMapBlock(final int xID, final int yID, int width, int height)
	{
		this.xID = xID;
		this.yID = yID;

		this.width = width;
		this.height = height;

		this.setBounds(xID * width, yID * height, width, height);

		try
		{
			blockImage = ImageIO.read(new File(this.blockType.getAddress()));

		} catch (IOException ex)
		{
			ex.printStackTrace();
		}

		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.addMouseListener(new TriggerPopupListener());
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(blockImage, 0, 0, width, height, GUIGame.getGUIGame());
		GUIGame.getGUIGame().repaint();
	}

	public void clearUIObjectsSet()
	{
		uiObjects.clear();
	}

	public void setBlockType(Type blockType)
	{
		this.blockType = blockType;
	}

	public Type getBlockType()
	{
		return blockType;
	}

	public void addUIObject(GUIGameObject uiObject)
	{
		this.uiObjects.add(uiObject);
		try
		{
			blockImage = ImageIO
					.read(new File(uiObject.getType().getAddress()));

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	class GetInfoListener implements ActionListener
	{
		private GUIGameObject uiObject;

		public GetInfoListener(GUIGameObject uiObject)
		{
			this.uiObject = uiObject;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			GUIGame.addSelectedUIObjectToInfoPanel(uiObject);
		}
	}

	class GetCommandListener implements ActionListener
	{
		private GUIGameObject uiObject;
		private CommandType commandType;

		public GetCommandListener(GUIGameObject uiObject,
				CommandType commandType)
		{
			this.uiObject = uiObject;
			this.commandType = commandType;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			GUIGame.getGUIGame().analyseRequest(uiObject, commandType);
		}

	}

	class TriggerPopupListener extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent e)
		{
			JPopupMenu popupMenu = new JPopupMenu();
			if (!GUIGame.getGUIGame().isUserRequestNull())
			{
				popupMenu = new JPopupMenu();
				Set<Type> selectTypes = new HashSet<Type>();
				UserRequest request = GUIGame.getGUIGame().getUserRequest();
				CommandType commandType = null;
				if (request instanceof MoveRequest)
				{
					selectTypes.add(Type.INVISIBLE_BLOCK);
					selectTypes.add(Type.JUNGLE_BLOCK);
					selectTypes.add(Type.MOUNTAIN_BLOCK);
					selectTypes.add(Type.PLAIN_BLOCK);
					selectTypes.add(Type.WATER_BLOCK);
					commandType = CommandType.MOVE;
				} else if (request instanceof BuildRequest)
				{
					selectTypes.add(Type.FARM);
					selectTypes.add(Type.UNIVERSITY);
					selectTypes.add(Type.PORT);
					selectTypes.add(Type.MAIN_BUILDING);
					selectTypes.add(Type.STOCKPILE);
					selectTypes.add(Type.GOLD_MINE);
					selectTypes.add(Type.STONE_MINE);
					selectTypes.add(Type.WOOD_CAMP);
					selectTypes.add(Type.STABLE);
					selectTypes.add(Type.BARRACK);
					selectTypes.add(Type.MARKET);
					commandType = CommandType.BUILD;
				} else if (request instanceof WorkRequest)
				{
					selectTypes.add(Type.WOOD_CAMP);
					selectTypes.add(Type.GOLD_MINE);
					selectTypes.add(Type.STONE_MINE);
					selectTypes.add(Type.FARM);

					commandType = CommandType.WORK;
				} else if (request instanceof TrainRequest)
				{
					// commandType = CommandType.TRAIN;
					// System.out.println("train command : " + commandType);
				}

				else if (request instanceof AttackRequest)
				{
					commandType = CommandType.ATTACK;
				}

				// SECOND IFs!
				if (request instanceof BuildRequest)
				{
					GUIGameObject object;
					JMenuItem menu;
					for (GUIGameObject obj : uiObjects)
					{
						switch (obj.getType())
						{
							case JUNGLE_BLOCK:
								object = new GUIGameObject("", Type.WOOD_CAMP,
										obj.getOccupiedPoints());
								menu = new JMenuItem("BUILD " + Type.WOOD_CAMP);
								menu.addActionListener(new GetCommandListener(
										object, commandType));
								popupMenu.add(menu);
								break;

							case MOUNTAIN_BLOCK:
								object = new GUIGameObject("", Type.GOLD_MINE,
										obj.getOccupiedPoints());
								menu = new JMenuItem("BUILD " + Type.GOLD_MINE);
								menu.addActionListener(new GetCommandListener(
										object, commandType));
								popupMenu.add(menu);

								object = new GUIGameObject("", Type.STONE_MINE,
										obj.getOccupiedPoints());
								menu = new JMenuItem("BUILD " + Type.STONE_MINE);
								menu.addActionListener(new GetCommandListener(
										object, commandType));
								popupMenu.add(menu);
								break;

							case PLAIN_BLOCK:
								object = new GUIGameObject("", Type.BARRACK,
										obj.getOccupiedPoints());
								menu = new JMenuItem("BUILD " + Type.BARRACK);
								menu.addActionListener(new GetCommandListener(
										object, commandType));
								popupMenu.add(menu);

								object = new GUIGameObject("", Type.FARM,
										obj.getOccupiedPoints());
								menu = new JMenuItem("BUILD " + Type.FARM);
								menu.addActionListener(new GetCommandListener(
										object, commandType));
								popupMenu.add(menu);

								object = new GUIGameObject("", Type.MARKET,
										obj.getOccupiedPoints());
								menu = new JMenuItem("BUILD " + Type.MARKET);
								menu.addActionListener(new GetCommandListener(
										object, commandType));
								popupMenu.add(menu);

								object = new GUIGameObject("", Type.STABLE,
										obj.getOccupiedPoints());
								menu = new JMenuItem("BUILD " + Type.STABLE);
								menu.addActionListener(new GetCommandListener(
										object, commandType));
								popupMenu.add(menu);

								object = new GUIGameObject("", Type.STOCKPILE,
										obj.getOccupiedPoints());
								menu = new JMenuItem("BUILD " + Type.STOCKPILE);
								menu.addActionListener(new GetCommandListener(
										object, commandType));
								popupMenu.add(menu);

								object = new GUIGameObject("", Type.UNIVERSITY,
										obj.getOccupiedPoints());
								menu = new JMenuItem("BUILD " + Type.UNIVERSITY);
								menu.addActionListener(new GetCommandListener(
										object, commandType));
								popupMenu.add(menu);

								object = new GUIGameObject("",
										Type.MAIN_BUILDING,
										obj.getOccupiedPoints());
								menu = new JMenuItem("BUILD "
										+ Type.MAIN_BUILDING);
								menu.addActionListener(new GetCommandListener(
										object, commandType));
								popupMenu.add(menu);
								break;

							case WATER_BLOCK:
								object = new GUIGameObject("", Type.PORT,
										obj.getOccupiedPoints());
								menu = new JMenuItem("BUILD " + Type.PORT);
								menu.addActionListener(new GetCommandListener(
										object, commandType));
								popupMenu.add(menu);
								break;
						}
					}
				} else if (request instanceof AttackRequest)
				{
					GUIGameObject object = null;
					JMenuItem menu;

					for (GUIGameObject obj : uiObjects)
					{
						if (!(obj.getType() == Type.INVISIBLE_BLOCK
								|| obj.getType() == Type.PLAIN_BLOCK
								|| obj.getType() == Type.JUNGLE_BLOCK
								|| obj.getType() == Type.MOUNTAIN_BLOCK || obj
									.getType() == Type.WATER_BLOCK))
						{
							object = new GUIGameObject(obj.getID(),
									obj.getType(), obj.getOccupiedPoints());
							menu = new JMenuItem("ATTACK " + obj.getType()
									+ " " + obj.getID());
							menu.addActionListener(new GetCommandListener(
									object, commandType));
							popupMenu.add(menu);
						}
					}
					System.out.println("uiobject: " + object);

				} else
				{
					for (GUIGameObject obj : uiObjects)
					{
						if (selectTypes.contains(obj.getType()))
						{
							JMenuItem menu = new JMenuItem(commandType
									+ " -> [" + obj.toString() + "]");
							menu.addActionListener(new GetCommandListener(obj,
									commandType));
							popupMenu.add(menu);
						}
					}
				}
			}
			popupMenu.show(e.getComponent(), e.getX(), e.getY());
		}

		@Override
		public void mouseReleased(MouseEvent e)
		{
			if (GUIGame.getGUIGame().isUserRequestNull())
			{
				JPopupMenu popupMenu = new JPopupMenu();
				for (GUIGameObject uiObject : uiObjects)
				{
					JMenu menu = new JMenu(uiObject.toString() + " ["
							+ uiObject.getID() + "]");

					JMenuItem detailsItem = new JMenuItem("DETAILS");
					menu.add(detailsItem);
					detailsItem
							.addActionListener(new GetInfoListener(uiObject));

					menu.addActionListener(new GetInfoListener(uiObject));

					for (CommandType commandType : uiObject.getType()
							.getCommandTypes())
					{
						JMenuItem item = new JMenuItem(commandType.toString());
						item.addActionListener(new GetCommandListener(uiObject,
								commandType));
						menu.add(item);
					}
					popupMenu.add(menu);
				}

				if (e.isPopupTrigger())
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}

		}
	}

	public ArrayList<GUIGameObject> getUiObjects()
	{
		return uiObjects;
	}

}
