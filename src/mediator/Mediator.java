package mediator;

import gui.data.DoubleData;
import gui.data.EnumData;
import gui.data.IntegerData;
import gui.data.ObjectData;
import gui.gui.GUIGame;
import gui.gui.GUIGameObject;
import gui.types.InfoType;
import gui.types.Type;
import gui.userrequest.AttackRequest;
import gui.userrequest.BuildRequest;
import gui.userrequest.ExchangeRequest;
import gui.userrequest.MoveRequest;
import gui.userrequest.ResearchRequest;
import gui.userrequest.SellRequest;
import gui.userrequest.TrainRequest;
import gui.userrequest.UserRequest;
import gui.userrequest.WorkRequest;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import logic.actionController.ActionController;
import logic.actionController.AttackController;
import logic.actionController.BuildController;
import logic.actionController.ExchangeController;
import logic.actionController.MoveController;
import logic.actionController.ResearchController;
import logic.actionController.SellController;
import logic.actionController.TrainController;
import logic.actionController.WorkController;
import logic.agents.Human;
import logic.agents.soldiers.AgileCavalry;
import logic.agents.soldiers.Axman;
import logic.agents.soldiers.HorseMaceman;
import logic.agents.soldiers.Soldier;
import logic.agents.soldiers.Spearman;
import logic.agents.workers.Worker;
import logic.buildings.Building;
import logic.game.Game;
import logic.game.GameObject;
import logic.game.Player;
import logic.types.ObjectType;
import logic.types.ResearchType;
import logic.types.ResourceType;

public class Mediator
{
	private static Mediator mediator = null;
	private static Player me;

	public static Mediator getMediator()
	{
		if (mediator == null)
			mediator = new Mediator();

		return mediator;
	}

	private Mediator()
	{
	}

	public static void nextTurn()
	{
		for (int i = 0; i < GUIGame.getGUIGame().getUserRequests().size(); i++)
		{
			getUserRequest(GUIGame.getGUIGame().getUserRequests().get(i));
		}

		GUIGame.getGUIGame().getCommandRequestPanel().showCommand("");
		me.nextTurn();
	}

	public static void getUserRequest(UserRequest userRequest)
	{
		if (getPlayerFromGameObjectID(userRequest.getUiObject().getID()) != me)
			return;

		ActionController actionController = null;
		if (userRequest instanceof MoveRequest)
		{
			logic.utilities.Point destination = new logic.utilities.Point(
					(int) ((MoveRequest) userRequest).getDestination().getX(),
					(int) ((MoveRequest) userRequest).getDestination().getY());

			actionController = new MoveController(
					me,
					(Human) getGameObjectByID(userRequest.getUiObject().getID()),
					destination);
			Human human = (Human) getGameObjectByID(userRequest.getUiObject()
					.getID());
			human.setActionController(actionController);

		} else if (userRequest instanceof BuildRequest)
		{
			ObjectType buildType = null;
			Human human = null;
			switch (((BuildRequest) userRequest).getBuildingType())
			{
				case BARRACK:
					if (me.getPlayerInfo().getResearchesMap()
							.get(ResearchType.BASIC_COMBAT).isFinished())
					{
						buildType = ObjectType.BARRACK;
						actionController = new BuildController(me, buildType,
								(Worker) getGameObjectByID(userRequest
										.getUiObject().getID()),
								new logic.utilities.Point(
										(int) ((BuildRequest) userRequest)
												.getDestination().getX(),
										(int) ((BuildRequest) userRequest)
												.getDestination().getY()));

						human = (Human) getGameObjectByID(userRequest
								.getUiObject().getID());
						human.setActionController(actionController);
					} else
					{
						GUIGame.getGUIGame().getObjectInfoPanel()
								.showError("[DO BASIC_COMBAT RESEARCH FIRST]");
					}
					break;
				case FARM:
				{
					if (me.getPlayerInfo().getResearchesMap()
							.get(ResearchType.AGRICULTURE).isFinished())
					{
						buildType = ObjectType.FARM;
						actionController = new BuildController(me, buildType,
								(Worker) getGameObjectByID(userRequest
										.getUiObject().getID()),
								new logic.utilities.Point(
										(int) ((BuildRequest) userRequest)
												.getDestination().getX(),
										(int) ((BuildRequest) userRequest)
												.getDestination().getY()));

						human = (Human) getGameObjectByID(userRequest
								.getUiObject().getID());
						human.setActionController(actionController);
					} else
					{
						GUIGame.getGUIGame().getObjectInfoPanel()
								.showError("[DO AGRICULTURE RESEARCH FIRST]");
					}
				}
					break;
				case GOLD_MINE:
					if (me.getPlayerInfo().getResearchesMap()
							.get(ResearchType.MINING).isFinished())
					{
						buildType = ObjectType.GOLD_MINE;
						actionController = new BuildController(me, buildType,
								(Worker) getGameObjectByID(userRequest
										.getUiObject().getID()),
								new logic.utilities.Point(
										(int) ((BuildRequest) userRequest)
												.getDestination().getX(),
										(int) ((BuildRequest) userRequest)
												.getDestination().getY()));

						human = (Human) getGameObjectByID(userRequest
								.getUiObject().getID());
						human.setActionController(actionController);
					} else
					{
						GUIGame.getGUIGame().getObjectInfoPanel()
								.showError("[DO MINING RESEARCH FIRST]");
					}

					break;
				case MARKET:
					if (me.getPlayerInfo().getResearchesMap()
							.get(ResearchType.MARKET).isFinished())
					{
						buildType = ObjectType.MARKET;
						actionController = new BuildController(me, buildType,
								(Worker) getGameObjectByID(userRequest
										.getUiObject().getID()),
								new logic.utilities.Point(
										(int) ((BuildRequest) userRequest)
												.getDestination().getX(),
										(int) ((BuildRequest) userRequest)
												.getDestination().getY()));

						human = (Human) getGameObjectByID(userRequest
								.getUiObject().getID());
						human.setActionController(actionController);
					} else
					{
						GUIGame.getGUIGame().getObjectInfoPanel()
								.showError("[DO MARKET RESEARCH FIRST]");
					}
					break;
				case MAIN_BUILDING:
					buildType = ObjectType.MAIN_BUILDING;
					actionController = new BuildController(me, buildType,
							(Worker) getGameObjectByID(userRequest
									.getUiObject().getID()),
							new logic.utilities.Point(
									(int) ((BuildRequest) userRequest)
											.getDestination().getX(),
									(int) ((BuildRequest) userRequest)
											.getDestination().getY()));

					human = (Human) getGameObjectByID(userRequest.getUiObject()
							.getID());
					human.setActionController(actionController);
					break;
				case PORT:
					if (me.getPlayerInfo().getResearchesMap()
							.get(ResearchType.FISHING).isFinished())
					{
						buildType = ObjectType.PORT;
						actionController = new BuildController(me, buildType,
								(Worker) getGameObjectByID(userRequest
										.getUiObject().getID()),
								new logic.utilities.Point(
										(int) ((BuildRequest) userRequest)
												.getDestination().getX(),
										(int) ((BuildRequest) userRequest)
												.getDestination().getY()));

						human = (Human) getGameObjectByID(userRequest
								.getUiObject().getID());
						human.setActionController(actionController);
					} else
					{
						GUIGame.getGUIGame().getObjectInfoPanel()
								.showError("[DO FISHING RESEARCH FIRST]");
					}
					break;
				case STABLE:
					if (me.getPlayerInfo().getResearchesMap()
							.get(ResearchType.HORSEBACK_RIDING).isFinished())
					{
						buildType = ObjectType.STABLE;
						actionController = new BuildController(me, buildType,
								(Worker) getGameObjectByID(userRequest
										.getUiObject().getID()),
								new logic.utilities.Point(
										(int) ((BuildRequest) userRequest)
												.getDestination().getX(),
										(int) ((BuildRequest) userRequest)
												.getDestination().getY()));

						human = (Human) getGameObjectByID(userRequest
								.getUiObject().getID());
						human.setActionController(actionController);
					} else
					{
						GUIGame.getGUIGame()
								.getObjectInfoPanel()
								.showError(
										"[DO HORSEBACK_RIDING RESEARCH FIRST]");
					}
					break;
				case STOCKPILE:
					buildType = ObjectType.STOCKPILE;
					actionController = new BuildController(me, buildType,
							(Worker) getGameObjectByID(userRequest
									.getUiObject().getID()),
							new logic.utilities.Point(
									(int) ((BuildRequest) userRequest)
											.getDestination().getX(),
									(int) ((BuildRequest) userRequest)
											.getDestination().getY()));

					human = (Human) getGameObjectByID(userRequest.getUiObject()
							.getID());
					human.setActionController(actionController);
					break;
				case STONE_MINE:
					if (me.getPlayerInfo().getResearchesMap()
							.get(ResearchType.MINING).isFinished())
					{
						buildType = ObjectType.STONE_MINE;
						actionController = new BuildController(me, buildType,
								(Worker) getGameObjectByID(userRequest
										.getUiObject().getID()),
								new logic.utilities.Point(
										(int) ((BuildRequest) userRequest)
												.getDestination().getX(),
										(int) ((BuildRequest) userRequest)
												.getDestination().getY()));

						human = (Human) getGameObjectByID(userRequest
								.getUiObject().getID());
						human.setActionController(actionController);
					} else
					{
						GUIGame.getGUIGame().getObjectInfoPanel()
								.showError("[DO MINING RESEARCH FIRST]");
					}
					break;
				case UNIVERSITY:
					buildType = ObjectType.UNIVERSITY;
					actionController = new BuildController(me, buildType,
							(Worker) getGameObjectByID(userRequest
									.getUiObject().getID()),
							new logic.utilities.Point(
									(int) ((BuildRequest) userRequest)
											.getDestination().getX(),
									(int) ((BuildRequest) userRequest)
											.getDestination().getY()));

					human = (Human) getGameObjectByID(userRequest.getUiObject()
							.getID());
					human.setActionController(actionController);
					break;
				case WOOD_CAMP:
					if (me.getPlayerInfo().getResearchesMap()
							.get(ResearchType.LUMBER_MILL).isFinished())
					{
						buildType = ObjectType.WOOD_CAMP;
						actionController = new BuildController(me, buildType,
								(Worker) getGameObjectByID(userRequest
										.getUiObject().getID()),
								new logic.utilities.Point(
										(int) ((BuildRequest) userRequest)
												.getDestination().getX(),
										(int) ((BuildRequest) userRequest)
												.getDestination().getY()));

						human = (Human) getGameObjectByID(userRequest
								.getUiObject().getID());
						human.setActionController(actionController);
					} else
					{
						GUIGame.getGUIGame().getObjectInfoPanel()
								.showError("[DO LUMBER_MILL RESEARCH FIRST]");
					}
					break;

				default:
					break;
			}

		} else if (userRequest instanceof AttackRequest)// TODO
		{
			logic.utilities.Point attackPoint = new logic.utilities.Point(
					(int) ((AttackRequest) userRequest).getAttackObject()
							.getOccupiedPoints().get(0).getX(),
					(int) ((AttackRequest) userRequest).getAttackObject()
							.getOccupiedPoints().get(0).getY());

			GameObject attackObject = null;

			attackObject = getGameObjectByID(((AttackRequest) userRequest)
					.getAttackObject().getID());

			actionController = new AttackController(me,
					(Soldier) getGameObjectByID(userRequest.getUiObject()
							.getID()), attackPoint, attackObject);

			Soldier soldier = (Soldier) getGameObjectByID(userRequest
					.getUiObject().getID());
			soldier.setActionController(actionController);

		} else if (userRequest instanceof ResearchRequest)
		{
			ResearchType type = null;
			if (((ResearchRequest) userRequest).getResearch().equals(
					"RESOURCES"))
			{
				type = ResearchType.RESOURCES;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"AGRICULTURE"))
			{
				type = ResearchType.AGRICULTURE;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"MINING"))
			{
				type = ResearchType.MINING;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"LUMBER_MILL"))
			{
				type = ResearchType.LUMBER_MILL;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"RANCHING"))
			{
				type = ResearchType.RANCHING;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"IRRIGATION"))
			{
				type = ResearchType.IRRIGATION;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"REFINERY"))
			{
				type = ResearchType.REFINERY;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"CARPENTRY"))
			{
				type = ResearchType.CARPENTRY;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"FISHING"))
			{
				type = ResearchType.FISHING;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"ECONOMY"))
			{
				type = ResearchType.ECONOMY;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"MICRO_ECONOMY"))
			{
				type = ResearchType.MICRO_ECONOMY;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"MACRO_ECONOMY"))
			{
				type = ResearchType.MACRO_ECONOMY;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"PARISMONY"))
			{
				type = ResearchType.PARSIMONY;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"TAX"))
			{
				type = ResearchType.TAX;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"PROJECT_MANAGEMENT"))
			{
				type = ResearchType.PROJECT_MANAGEMENT;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"MARKET"))
			{
				type = ResearchType.MARKET;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"TEAM_WORK"))
			{
				type = ResearchType.TEAM_WORK;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"STRATEGIC_PROJECT_MANAGEMENT"))
			{
				type = ResearchType.STRATEGIC_PROJECT_MANAGEMENT;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"SCIENCE"))
			{
				type = ResearchType.SCIENCE;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"ALPHABET"))
			{
				type = ResearchType.ALPHABET;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"MATHEMATICS"))
			{
				type = ResearchType.MATHEMATICS;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"ENGINEERING"))
			{
				type = ResearchType.ENGINEERING;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"SCHOOL"))
			{
				type = ResearchType.SCHOOL;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"UNIVERSITY"))
			{
				type = ResearchType.UNIVERSITY;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"CERN"))
			{
				type = ResearchType.CERN;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"MILITARY"))
			{
				type = ResearchType.MILITARY;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"HORSEBACK_RIDING"))
			{
				type = ResearchType.HORSEBACK_RIDING;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"BASIC_COMBAT"))
			{
				type = ResearchType.BASIC_COMBAT;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"ANIMAL_HUSBANDRY"))
			{
				type = ResearchType.ANIMAL_HUSBANDRY;
			} else if (((ResearchRequest) userRequest).getResearch().equals(
					"ADVANCED_ARMORS"))
			{
				type = ResearchType.ADVANCED_ARMORS;
			}

			actionController = new ResearchController(me, type);
			me.getUniversities().get(0).setActionController(actionController);
		} else if (userRequest instanceof TrainRequest)
		{
			ObjectType trainType = null;
			Building building = null;

			switch (((TrainRequest) userRequest).getTrainType())
			{
				case AGILE_CAVALRY:
				{
					if (me.getPlayerInfo().getResearchesMap()
							.get(ResearchType.HORSEBACK_RIDING).isFinished())
					{
						trainType = ObjectType.AGILE_CAVALRY;
						building = (Building) getGameObjectByID(userRequest
								.getUiObject().getID());

						actionController = new TrainController(me, building,
								trainType);
						building.setActionController(actionController);
					} else
					{
						GUIGame.getGUIGame()
								.getObjectInfoPanel()
								.showError(
										"[DO HORSEBACK_RIDING RESEARCH FIRST]");
					}
				}
					break;
				case AXMAN:
					if (me.getPlayerInfo().getResearchesMap()
							.get(ResearchType.BASIC_COMBAT).isFinished())
					{
						trainType = ObjectType.AXMAN;
						building = (Building) getGameObjectByID(userRequest
								.getUiObject().getID());

						actionController = new TrainController(me, building,
								trainType);
						building.setActionController(actionController);
					} else
					{
						GUIGame.getGUIGame().getObjectInfoPanel()
								.showError("[DO BASIC_COMBAT RESEARCH FIRST]");
					}

					break;
				case BOAT:
				{
					trainType = ObjectType.BOAT;
					building = (Building) getGameObjectByID(userRequest
							.getUiObject().getID());

					actionController = new TrainController(me, building,
							trainType);
					building.setActionController(actionController);
				}
					break;
				case HORSE_MACEMAN:
					if (me.getPlayerInfo().getResearchesMap()
							.get(ResearchType.HORSEBACK_RIDING).isFinished())
					{
						trainType = ObjectType.HORSE_MACEMAN;
						building = (Building) getGameObjectByID(userRequest
								.getUiObject().getID());

						actionController = new TrainController(me, building,
								trainType);
						building.setActionController(actionController);
					} else
					{
						GUIGame.getGUIGame()
								.getObjectInfoPanel()
								.showError(
										"[DO HORSEBACK_RIDING RESEARCH FIRST]");
					}

					break;
				case SPEARMAN:
					if (me.getPlayerInfo().getResearchesMap()
							.get(ResearchType.BASIC_COMBAT).isFinished())
					{
						trainType = ObjectType.SPEARMAN;
						building = (Building) getGameObjectByID(userRequest
								.getUiObject().getID());

						actionController = new TrainController(me, building,
								trainType);
						building.setActionController(actionController);
					} else
					{
						GUIGame.getGUIGame().getObjectInfoPanel()
								.showError("[DO BASIC_COMBAT RESEARCH FIRST]");
					}
					break;
				case WORKER:
					trainType = ObjectType.WORKER;
					building = (Building) getGameObjectByID(userRequest
							.getUiObject().getID());

					actionController = new TrainController(me, building,
							trainType);
					building.setActionController(actionController);
					break;
				case SCHOLAR:
					if (me.getScholars().size() < me.getPlayerInfo()
							.getMaxUniversityCapacity())
					{
						trainType = ObjectType.SCHOLAR;
						building = (Building) getGameObjectByID(userRequest
								.getUiObject().getID());

						actionController = new TrainController(me, building,
								trainType);
						building.setActionController(actionController);
					} else
					{
						GUIGame.getGUIGame()
								.getObjectInfoPanel()
								.showError(
										"[YOU CAN'T HAVE MORE THAN "
												+ me.getPlayerInfo()
														.getMaxUniversityCapacity()
												+ " SCHOLARS RIGHT NOW]");
					}
					break;
				default:
					break;

			}

		} else if (userRequest instanceof WorkRequest)
		{
			Worker worker = null;
			worker = (Worker) getGameObjectByID(userRequest.getUiObject()
					.getID());
			Building destBuilding = null;
			destBuilding = (Building) getGameObjectByID(((WorkRequest) userRequest)
					.getDestBuilding().getID());

			actionController = new WorkController(me, destBuilding, worker);
			Human human = (Human) getGameObjectByID(userRequest.getUiObject()
					.getID());
			human.setActionController(actionController);
		} else if (userRequest instanceof ExchangeRequest)
		{
			ResourceType fromResource = null;
			ResourceType toResource = null;
			switch (((ExchangeRequest) userRequest).getFromResource())
			{
				case FOOD:
					fromResource = ResourceType.FOOD;
					break;
				case GOLD:
					fromResource = ResourceType.GOLD;
					break;
				case LUMBER:
					fromResource = ResourceType.LUMBER;
					break;
				case STONE:
					fromResource = ResourceType.STONE;
					break;
				default:
					break;
			}

			switch (((ExchangeRequest) userRequest).getToResource())
			{
				case FOOD:
					toResource = ResourceType.FOOD;
					break;
				case GOLD:
					toResource = ResourceType.GOLD;
					break;
				case LUMBER:
					toResource = ResourceType.LUMBER;
					break;
				case STONE:
					toResource = ResourceType.STONE;
					break;
				default:
					break;
			}

			actionController = new ExchangeController(me, fromResource,
					toResource, ((ExchangeRequest) userRequest).getAmount());
			me.getMarkets().get(0).setActionController(actionController);
		} else if (userRequest instanceof SellRequest)
		{
			Building myBuilding = ((Building) (getGameObjectByID(userRequest
					.getUiObject().getID())));

			ObjectType buildingType = null;
			switch (((SellRequest) userRequest).getSellType())
			{
				case BARRACK:
					buildingType = ObjectType.BARRACK;
					break;
				case FARM:
					buildingType = ObjectType.FARM;
					break;
				case GOLD_MINE:
					buildingType = ObjectType.GOLD_MINE;
					break;
				case MAIN_BUILDING:
					buildingType = ObjectType.MAIN_BUILDING;
					break;
				case MARKET:
					buildingType = ObjectType.MARKET;
					break;
				case PORT:
					buildingType = ObjectType.PORT;
					break;
				case STABLE:
					buildingType = ObjectType.STABLE;
					break;
				case STOCKPILE:
					buildingType = ObjectType.STOCKPILE;
					break;
				case STONE_MINE:
					buildingType = ObjectType.STONE_MINE;
					break;
				case UNIVERSITY:
					buildingType = ObjectType.UNIVERSITY;
					break;
				case WOOD_CAMP:
					buildingType = ObjectType.WOOD_CAMP;
					break;

				default:
					break;
			}

			actionController = new SellController(me, myBuilding, buildingType);
			myBuilding.setActionController(actionController);
		}
		userRequest = null;
	}

	private static GameObject getGameObjectByID(String ID)
	{
		for (int i = 0; i < Game.getGame().getPlayers().size(); i++)
		{
			for (int j = 0; j < Game.getGame().getPlayers().get(i)
					.getGameObjects().size(); j++)
			{
				if (Game.getGame().getPlayers().get(i).getGameObjects().get(j)
						.getId().equals(ID))
					return Game.getGame().getPlayers().get(i).getGameObjects()
							.get(j);
			}
		}

		return null;
	}

	private static Player getPlayerFromGameObjectID(String ID)
	{
		for (int i = 0; i < Game.getGame().getPlayers().size(); i++)
		{
			for (int j = 0; j < Game.getGame().getPlayers().get(i)
					.getGameObjects().size(); j++)
			{
				if (Game.getGame().getPlayers().get(i).getGameObjects().get(j)
						.getId().equals(ID))
					return Game.getGame().getPlayers().get(i);
			}
		}

		return null;
	}

	public static void sendFromLogicToGUI(Game game, Player me)
	{
		GUIGame.getGUIGame().getCommandRequestPanel().setWhoIsThisTurn();

		Mediator.me = me;
		if (me.getUniversities().size() == 0)
		{
			GUIGame.getGUIGame().getInformationPanel().getResearchPanel()
					.setEnabled(false);
		} else
		{
			GUIGame.getGUIGame().getInformationPanel().getResearchPanel()
					.setEnabled(true);
		}

		if (me.getMarkets().size() == 0)
		{
			GUIGame.getGUIGame().getInformationPanel().getExchangePanel()
					.setEnabled(false);
		} else
		{
			GUIGame.getGUIGame().getInformationPanel().getExchangePanel()
					.setEnabled(true);
		}

		ArrayList<String> researches = new ArrayList<String>();
		for (int i = 0; i < me.getPlayerInfo().getResearches().length; i++)
		{
			if (me.getPlayerInfo().getResearches()[i].isAvailable()
					&& !me.getPlayerInfo().getResearches()[i].isFinished())
			{
				researches.add(me.getPlayerInfo().getResearches()[i]
						.getResearchType().toString());
			}
		}

		GUIGame.getGUIGame().getObjectInfoPanel()
				.showDoneActions(me.getDoneActions());
		me.getDoneActions().clear();

		GUIGame.getGUIGame()
				.getInformationPanel()
				.getResearchPanel()
				.setResearchesOfComboBox(
						researches.toArray(new String[researches.size()]));

		GUIGame.getGUIGame()
				.getInformationPanel()
				.getResourcesPanel()
				.setPanel(me.getPlayerInfo().getGoldInStock().getAmount(),
						me.getPlayerInfo().getStoneInStock().getAmount(),
						me.getPlayerInfo().getLumberInStock().getAmount(),
						me.getPlayerInfo().getKnowledgeInStock().getAmount(),
						me.getPlayerInfo().getFoodInStock().getAmount());

		GUIGame.getGUIGame()
				.getInformationPanel()
				.getPlayerPanel()
				.setGeneralInfo(me.getPlayerInfo().getTotalScore(),
						((int) me.getHumans().size() / 4),
						me.getSoldiers().size(), me.getWorkers().size(),
						me.getBuildings().size(), me.getBoats().size());

		GUIGame.getGUIGame().getInformationPanel().getResearchPanel()
				.setNumOfScholars(me.getScholars().size());

		ArrayList<GUIGameObject> uiObjects = new ArrayList<GUIGameObject>();

		for (int i = 0; i < game.getMap().getMapBlocks().length; i++)
		{
			for (int j = 0; j < game.getMap().getMapBlocks()[0].length; j++)
			{
				Type type = null;
				Map<InfoType, ObjectData> infoTypeToObjectData = new HashMap<InfoType, ObjectData>();
				if (me.getMapVisiblity()[i][j])
				{
					switch (game.getMap().getMapBlocks()[i][j].getBlockType())
					{
						case FOREST:
							type = Type.JUNGLE_BLOCK;
							break;
						case MOUNTAIN:
							type = Type.MOUNTAIN_BLOCK;
							break;
						case PLAIN:
							type = Type.PLAIN_BLOCK;
							break;
						case WATER:
							type = Type.WATER_BLOCK;
							break;

						default:
							break;
					}

					if (me.getPlayerInfo().getResearchesMap()
							.get(ResearchType.RESOURCES).isFinished())
					{
						infoTypeToObjectData.put(
								InfoType.LUMBER,
								new IntegerData(
										game.getMap().getMapBlocks()[i][j]
												.getLumber()));

						infoTypeToObjectData.put(
								InfoType.GOLD,
								new IntegerData(
										game.getMap().getMapBlocks()[i][j]
												.getGold()));
						infoTypeToObjectData.put(
								InfoType.STONE,
								new IntegerData(
										game.getMap().getMapBlocks()[i][j]
												.getStone()));
						infoTypeToObjectData.put(
								InfoType.FOOD,
								new IntegerData(
										game.getMap().getMapBlocks()[i][j]
												.getFood()));
					} else
					{
						infoTypeToObjectData.put(InfoType.LUMBER,
								new IntegerData(-1));
						infoTypeToObjectData.put(InfoType.GOLD,
								new IntegerData(-1));
						infoTypeToObjectData.put(InfoType.STONE,
								new IntegerData(-1));
						infoTypeToObjectData.put(InfoType.FOOD,
								new IntegerData(-1));
					}

				} else
					type = Type.INVISIBLE_BLOCK;

				ArrayList<Point> points = new ArrayList<Point>();
				points.add(new Point(i, j));
				uiObjects.add(new GUIGameObject(
						game.getMap().getMapBlocks()[i][j].getID(), type,
						points, infoTypeToObjectData));
			}
		}

		for (Player player : game.getPlayers())
		{
			for (GameObject gameObject : player.getGameObjects())
			{
				if (gameObject instanceof Building)
				{
					Type type;
					if (!isBuildingVisible(me, ((Building) gameObject)))
						continue;

					ArrayList<Point> points = new ArrayList<Point>();
					for (int k1 = 0; k1 < player.getPlayerInfo()
							.getBuildingSize().get(gameObject.getObjectType()); k1++)
					{
						for (int k2 = 0; k2 < player.getPlayerInfo()
								.getBuildingSize()
								.get(gameObject.getObjectType()); k2++)
						{
							points.add(new Point(k1
									+ gameObject.getPosition().getX(), k2
									+ gameObject.getPosition().getY()));
						}
					}

					switch (((Building) gameObject).getObjectType())
					{
						case BARRACK:
						{
							type = Type.BARRACK;
							uiObjects.add(new GUIGameObject(gameObject.getId(),
									type, points,
									new HashMap<InfoType, ObjectData>()));
						}
							break;
						case FARM:
						{
							type = Type.FARM;
							uiObjects.add(new GUIGameObject(gameObject.getId(),
									type, points,
									new HashMap<InfoType, ObjectData>()));

						}
							break;
						case GOLD_MINE:
						{
							type = Type.GOLD_MINE;
							uiObjects.add(new GUIGameObject(gameObject.getId(),
									type, points,
									new HashMap<InfoType, ObjectData>()));
						}
							break;
						case MAIN_BUILDING:
						{
							type = Type.MAIN_BUILDING;
							uiObjects.add(new GUIGameObject(gameObject.getId(),
									type, points,
									new HashMap<InfoType, ObjectData>()));
						}
							break;
						case MARKET:
						{
							type = Type.MARKET;
							uiObjects.add(new GUIGameObject(gameObject.getId(),
									type, points,
									new HashMap<InfoType, ObjectData>()));
						}
							break;
						case PORT:
						{
							type = Type.PORT;
							uiObjects.add(new GUIGameObject(gameObject.getId(),
									type, points,
									new HashMap<InfoType, ObjectData>()));
						}
							break;
						case STABLE:
						{
							type = Type.STABLE;
							uiObjects.add(new GUIGameObject(gameObject.getId(),
									type, points,
									new HashMap<InfoType, ObjectData>()));
						}
							break;
						case STOCKPILE:
						{
							type = Type.STOCKPILE;
							uiObjects.add(new GUIGameObject(gameObject.getId(),
									type, points,
									new HashMap<InfoType, ObjectData>()));
						}
							break;
						case WOOD_CAMP:
						{
							type = Type.WOOD_CAMP;
							uiObjects.add(new GUIGameObject(gameObject.getId(),
									type, points,
									new HashMap<InfoType, ObjectData>()));
						}
							break;
						case STONE_MINE:
						{
							type = Type.STONE_MINE;
							uiObjects.add(new GUIGameObject(gameObject.getId(),
									type, points,
									new HashMap<InfoType, ObjectData>()));
						}
							break;
						case UNIVERSITY:
						{
							type = Type.UNIVERSITY;
							uiObjects.add(new GUIGameObject(gameObject.getId(),
									type, points,
									new HashMap<InfoType, ObjectData>()));
						}
							break;

						default:
							break;
					}

				} else
				{
					if (!me.getMapVisiblity()[gameObject.getPosition().getX()][gameObject
							.getPosition().getY()])
						continue;

					Type type;
					ArrayList<Point> points = new ArrayList<Point>();
					points.add(new Point(gameObject.getPosition().getX(),
							gameObject.getPosition().getY()));

					switch (gameObject.getObjectType())
					{
						case WORKER:
						{
							type = Type.WORKER;
							Map<InfoType, ObjectData> infoTypeToObjectData = new HashMap<InfoType, ObjectData>();
							infoTypeToObjectData.put(
									InfoType.INVENTORY,
									new IntegerData(((Worker) gameObject)
											.getLoadInInventory()));
							infoTypeToObjectData.put(
									InfoType.OCCUPATION,
									new EnumData(((Worker) gameObject)
											.getOccupationType()));

							infoTypeToObjectData.put(
									InfoType.GOLD_MINING_EXPERIENCE,
									new DoubleData(((Worker) gameObject)
											.getGoldMiningExperience()));

							infoTypeToObjectData.put(
									InfoType.STONE_MINING_EXPERIENCE,
									new DoubleData(((Worker) gameObject)
											.getStoneMiningExperience()));

							infoTypeToObjectData.put(
									InfoType.WOOD_CAMP_EXPERIENCE,
									new DoubleData(((Worker) gameObject)
											.getWoodCampExperience()));

							infoTypeToObjectData.put(
									InfoType.FOOD_COLLECTING_EXPERIENCE,
									new DoubleData(((Worker) gameObject)
											.getFoodCollectingExperience()));

							infoTypeToObjectData.put(
									InfoType.BUILDING_EXPERIENCE,
									new DoubleData(((Worker) gameObject)
											.getBuildingExperience()));

							uiObjects.add(new GUIGameObject(gameObject.getId(),
									type, points, infoTypeToObjectData));
						}
							break;

						case AGILE_CAVALRY:
						{
							type = Type.AGILE_CAVALRY;

							Map<InfoType, ObjectData> infoTypeToObjectData = new HashMap<InfoType, ObjectData>();
							infoTypeToObjectData.put(
									InfoType.HEALTH,
									new IntegerData(((AgileCavalry) gameObject)
											.getHealth()));

							infoTypeToObjectData.put(
									InfoType.ATTACK_SCORE,

									new IntegerData(((AgileCavalry) gameObject)
											.getAttackScore()));
							infoTypeToObjectData.put(
									InfoType.DEFENCE_SCORE,

									new IntegerData(((AgileCavalry) gameObject)
											.getDefenceScore()));
							infoTypeToObjectData.put(
									InfoType.SCORE_TOWARD_CAVALRY,
									new IntegerData(((AgileCavalry) gameObject)
											.getScoreTowardCavalry()));
							infoTypeToObjectData.put(
									InfoType.SCORE_TOWARD_INFANTRY,
									new IntegerData(((AgileCavalry) gameObject)
											.getScoreTowardInfantry()));

							uiObjects.add(new GUIGameObject(gameObject.getId(),
									type, points, infoTypeToObjectData));
						}
							break;
						case AXMAN:
						{
							type = Type.AXMAN;
							Map<InfoType, ObjectData> infoTypeToObjectData = new HashMap<InfoType, ObjectData>();
							infoTypeToObjectData.put(
									InfoType.HEALTH,
									new IntegerData(((Axman) gameObject)
											.getHealth()));

							infoTypeToObjectData.put(
									InfoType.ATTACK_SCORE,

									new IntegerData(((Axman) gameObject)
											.getAttackScore()));
							infoTypeToObjectData.put(
									InfoType.DEFENCE_SCORE,

									new IntegerData(((Axman) gameObject)
											.getDefenceScore()));
							infoTypeToObjectData.put(
									InfoType.SCORE_TOWARD_CAVALRY,
									new IntegerData(((Axman) gameObject)
											.getScoreTowardCavalry()));
							infoTypeToObjectData.put(
									InfoType.SCORE_TOWARD_INFANTRY,
									new IntegerData(((Axman) gameObject)
											.getScoreTowardInfantry()));

							uiObjects.add(new GUIGameObject(gameObject.getId(),
									type, points, infoTypeToObjectData));
						}
							break;
						case BOAT:
						{
							type = Type.BOAT;
							uiObjects.add(new GUIGameObject(gameObject.getId(),
									type, points,
									new HashMap<InfoType, ObjectData>()));

						}
							break;
						case HORSE_MACEMAN:
						{
							type = Type.HORSE_MACEMAN;
							Map<InfoType, ObjectData> infoTypeToObjectData = new HashMap<InfoType, ObjectData>();
							infoTypeToObjectData.put(
									InfoType.HEALTH,
									new IntegerData(((HorseMaceman) gameObject)
											.getHealth()));

							infoTypeToObjectData.put(
									InfoType.ATTACK_SCORE,

									new IntegerData(((HorseMaceman) gameObject)
											.getAttackScore()));
							infoTypeToObjectData.put(
									InfoType.DEFENCE_SCORE,

									new IntegerData(((HorseMaceman) gameObject)
											.getDefenceScore()));
							infoTypeToObjectData.put(
									InfoType.SCORE_TOWARD_CAVALRY,
									new IntegerData(((HorseMaceman) gameObject)
											.getScoreTowardCavalry()));
							infoTypeToObjectData.put(
									InfoType.SCORE_TOWARD_INFANTRY,
									new IntegerData(((HorseMaceman) gameObject)
											.getScoreTowardInfantry()));

							uiObjects.add(new GUIGameObject(gameObject.getId(),
									type, points, infoTypeToObjectData));
						}
							break;
						case SPEARMAN:
						{
							type = Type.SPEARMAN;

							Map<InfoType, ObjectData> infoTypeToObjectData = new HashMap<InfoType, ObjectData>();
							infoTypeToObjectData.put(
									InfoType.HEALTH,
									new IntegerData(((Spearman) gameObject)
											.getHealth()));

							infoTypeToObjectData.put(
									InfoType.ATTACK_SCORE,

									new IntegerData(((Spearman) gameObject)
											.getAttackScore()));
							infoTypeToObjectData.put(
									InfoType.DEFENCE_SCORE,

									new IntegerData(((Spearman) gameObject)
											.getDefenceScore()));
							infoTypeToObjectData.put(
									InfoType.SCORE_TOWARD_CAVALRY,
									new IntegerData(((Spearman) gameObject)
											.getScoreTowardCavalry()));
							infoTypeToObjectData.put(
									InfoType.SCORE_TOWARD_INFANTRY,
									new IntegerData(((Spearman) gameObject)
											.getScoreTowardInfantry()));

							uiObjects.add(new GUIGameObject(gameObject.getId(),
									type, points, infoTypeToObjectData));
						}
							break;

						default:
							break;
					}
				}

			}

		}

		GUIGame.getGUIGame().reconstructGUI(uiObjects, me);

	}

	private static boolean isBuildingVisible(Player player, Building building)
	{
		for (int i = 0; i < player.getPlayerInfo().getBuildingSize()
				.get(building.getObjectType()); i++)
		{
			for (int j = 0; j < player.getPlayerInfo().getBuildingSize()
					.get(building.getObjectType()); j++)
			{
				if (player.getMapVisiblity()[i + building.getPosition().getX()][j
						+ building.getPosition().getY()])
					return true;
			}
		}

		return false;
	}
}
