package logic.actions;

import logic.game.Player;
import logic.types.ObjectType;
import logic.types.ResearchType;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
public class ResearchAction extends Action
{
	private ResearchType researchType;
	private Player myPlayer;

	/**
	 * 
	 * @param my
	 *            player
	 * @param research
	 *            type
	 */
	public ResearchAction(Player myPlayer, ResearchType researchType)
	{
		this.researchType = researchType;
		this.myPlayer = myPlayer;
	}

	/**
	 * Do the core action of the research action - without checking conditions
	 */
	@Override
	public void doAction()
	{
		@SuppressWarnings("unused")
		int numOfResearch = 0;
		myPlayer.getPlayerInfo().getResearchesMap().get(researchType)
				.setFinished(true);

		switch (researchType)
		{
			case AGRICULTURE:
			{
				myPlayer.getPlayerInfo().setAbilityToBuildFarm(true);
				numOfResearch = 2;
			}
				break;

			case ALPHABET:
			{
				myPlayer.getPlayerInfo().setKnowledgePerScholar(
						(int) (myPlayer.getPlayerInfo()
								.getKnowledgePerScholar() * 1.1));
				numOfResearch = 20;
			}
				break;

			case CARPENTRY:
			{
				myPlayer.getPlayerInfo().setWoodProductionRate(
						myPlayer.getPlayerInfo().getWoodProductionRate() * 1.2);
				numOfResearch = 8;
			}
				break;

			case CERN:
			{
				myPlayer.getPlayerInfo().setMaxUniversityCapacity(
						Integer.MAX_VALUE);
				numOfResearch = 25;
			}
				break;

			case ECONOMY:
			{
				myPlayer.getPlayerInfo()
						.setGoldProductionRate(
								myPlayer.getPlayerInfo()
										.getGoldProductionRate() * 1.05);
				numOfResearch = 10;
			}
				break;

			case ENGINEERING:
			{
				myPlayer.getPlayerInfo()
						.getTurnsToBuild()
						.put(ObjectType.FARM,
								(int) (myPlayer.getPlayerInfo()
										.getTurnsToBuild().get(ObjectType.FARM) * (.75)));

				myPlayer.getPlayerInfo()
						.getTurnsToBuild()
						.put(ObjectType.GOLD_MINE,
								(int) (myPlayer.getPlayerInfo()
										.getTurnsToBuild()
										.get(ObjectType.GOLD_MINE) * (.75)));

				myPlayer.getPlayerInfo()
						.getTurnsToBuild()
						.put(ObjectType.MAIN_BUILDING,
								(int) (myPlayer.getPlayerInfo()
										.getTurnsToBuild()
										.get(ObjectType.MAIN_BUILDING) * (.75)));

				myPlayer.getPlayerInfo()
						.getTurnsToBuild()
						.put(ObjectType.MARKET,
								(int) (myPlayer.getPlayerInfo()
										.getTurnsToBuild()
										.get(ObjectType.MARKET) * (.75)));

				myPlayer.getPlayerInfo()
						.getTurnsToBuild()
						.put(ObjectType.PORT,
								(int) (myPlayer.getPlayerInfo()
										.getTurnsToBuild().get(ObjectType.PORT) * (.75)));

				myPlayer.getPlayerInfo()
						.getTurnsToBuild()
						.put(ObjectType.STOCKPILE,
								(int) (myPlayer.getPlayerInfo()
										.getTurnsToBuild()
										.get(ObjectType.STOCKPILE) * (.75)));

				myPlayer.getPlayerInfo()
						.getTurnsToBuild()
						.put(ObjectType.STONE_MINE,
								(int) (myPlayer.getPlayerInfo()
										.getTurnsToBuild()
										.get(ObjectType.STONE_MINE) * (.75)));

				myPlayer.getPlayerInfo()
						.getTurnsToBuild()
						.put(ObjectType.UNIVERSITY,
								(int) (myPlayer.getPlayerInfo()
										.getTurnsToBuild()
										.get(ObjectType.UNIVERSITY) * (.75)));

				myPlayer.getPlayerInfo()
						.getTurnsToBuild()
						.put(ObjectType.WOOD_CAMP,
								(int) (myPlayer.getPlayerInfo()
										.getTurnsToBuild()
										.get(ObjectType.WOOD_CAMP) * (.75)));
				numOfResearch = 22;
			}
				break;

			case FISHING:
			{
				myPlayer.getPlayerInfo().setAbilityToBuildPort(true);
				myPlayer.getPlayerInfo().setAbilityToTrainBoat(true);
				numOfResearch = 9;
			}
				break;

			case IRRIGATION:
			{
				myPlayer.getPlayerInfo()
						.setFoodProductionRate(
								myPlayer.getPlayerInfo()
										.getFoodProductionRate() * 1.20);
				numOfResearch = 6;
			}
				break;

			case LUMBER_MILL:
			{
				myPlayer.getPlayerInfo().setAbilityToBuildWoodCamp(true);
				numOfResearch = 4;
			}
				break;

			case MACRO_ECONOMY:
			{
				myPlayer.getPlayerInfo()
						.setFoodProductionRate(
								myPlayer.getPlayerInfo()
										.getFoodProductionRate() * 1.15);
				numOfResearch = 12;
			}
				break;

			case MARKET:
			{
				myPlayer.getPlayerInfo().setAbilityToBuildMarket(true);
				numOfResearch = 16;
			}
				break;

			case MATHEMATICS:
			{
				myPlayer.getPlayerInfo().setKnowledgePerScholar(
						(int) (myPlayer.getPlayerInfo()
								.getKnowledgePerScholar() * 1.1));
				numOfResearch = 21;
			}
				break;

			case MICRO_ECONOMY:
			{
				myPlayer.getPlayerInfo().setMaxCarryingLoadByWorker(
						2 * myPlayer.getPlayerInfo()
								.getMaxCarryingLoadByWorker());
				numOfResearch = 11;
			}
				break;

			case MINING:
			{
				myPlayer.getPlayerInfo().setAbilityToBuildGoldMine(true);
				myPlayer.getPlayerInfo().setAbilityToBuildStoneMine(true);
				numOfResearch = 3;
			}
				break;

			case PARSIMONY:
			{
				myPlayer.getPlayerInfo()
						.getNeededResources()
						.put(ObjectType.BOAT,
								myPlayer.getPlayerInfo().getNeededResources()
										.get(ObjectType.BOAT));
				myPlayer.getPlayerInfo()
						.getNeededResources()
						.put(ObjectType.FARM,
								myPlayer.getPlayerInfo().getNeededResources()
										.get(ObjectType.FARM));
				myPlayer.getPlayerInfo()
						.getNeededResources()
						.put(ObjectType.GOLD_MINE,
								myPlayer.getPlayerInfo().getNeededResources()
										.get(ObjectType.GOLD_MINE));
				myPlayer.getPlayerInfo()
						.getNeededResources()
						.put(ObjectType.MAIN_BUILDING,
								myPlayer.getPlayerInfo().getNeededResources()
										.get(ObjectType.MAIN_BUILDING));
				myPlayer.getPlayerInfo()
						.getNeededResources()
						.put(ObjectType.MARKET,
								myPlayer.getPlayerInfo().getNeededResources()
										.get(ObjectType.MARKET));
				myPlayer.getPlayerInfo()
						.getNeededResources()
						.put(ObjectType.PORT,
								myPlayer.getPlayerInfo().getNeededResources()
										.get(ObjectType.PORT));
				myPlayer.getPlayerInfo()
						.getNeededResources()
						.put(ObjectType.SCHOLAR,
								myPlayer.getPlayerInfo().getNeededResources()
										.get(ObjectType.SCHOLAR));
				myPlayer.getPlayerInfo()
						.getNeededResources()
						.put(ObjectType.STOCKPILE,
								myPlayer.getPlayerInfo().getNeededResources()
										.get(ObjectType.STOCKPILE));
				myPlayer.getPlayerInfo()
						.getNeededResources()
						.put(ObjectType.STONE_MINE,
								myPlayer.getPlayerInfo().getNeededResources()
										.get(ObjectType.STONE_MINE));
				myPlayer.getPlayerInfo()
						.getNeededResources()
						.put(ObjectType.UNIVERSITY,
								myPlayer.getPlayerInfo().getNeededResources()
										.get(ObjectType.UNIVERSITY));
				myPlayer.getPlayerInfo()
						.getNeededResources()
						.put(ObjectType.WOOD_CAMP,
								myPlayer.getPlayerInfo().getNeededResources()
										.get(ObjectType.WOOD_CAMP));
				myPlayer.getPlayerInfo()
						.getNeededResources()
						.put(ObjectType.WORKER,
								myPlayer.getPlayerInfo().getNeededResources()
										.get(ObjectType.WORKER));
				numOfResearch = 13;
			}
				break;

			case PROJECT_MANAGEMENT:
			{
				// IT DOES NOTHING!!!
				numOfResearch = 15;
			}
				break;

			case RANCHING:
			{
				myPlayer.getPlayerInfo()
						.setFoodProductionRate(
								myPlayer.getPlayerInfo()
										.getFoodProductionRate() * 1.10);

				numOfResearch = 5;
			}
				break;

			case REFINERY:
			{
				myPlayer.getPlayerInfo()
						.setStoneProductionRate(
								myPlayer.getPlayerInfo()
										.getStoneProductionRate() * 1.20);
				numOfResearch = 7;
			}
				break;

			case RESOURCES:
			{
				myPlayer.getPlayerInfo().setBlockResourcesVisible(true);
				numOfResearch = 1;
			}
				break;

			case SCHOOL:
			{
				myPlayer.getPlayerInfo()
						.setMaxUniversityCapacity(
								3 + myPlayer.getPlayerInfo()
										.getMaxUniversityCapacity());
				numOfResearch = 23;
			}
				break;

			case SCIENCE:
			{
				myPlayer.getPlayerInfo().getKnowledgeInStock().addTo(50);
				numOfResearch = 19;
			}
				break;

			case STRATEGIC_PROJECT_MANAGEMENT:
			{
				// IT DOES NOTHING!!!
				numOfResearch = 18;
			}
				break;

			case TAX:
			{
				myPlayer.getPlayerInfo().setTaxCollectable(true);
				numOfResearch = 14;
			}
				break;

			case TEAM_WORK:
			{
				myPlayer.getPlayerInfo().setMaxFarmCapacity(
						2 * myPlayer.getPlayerInfo().getMaxFarmCapacity());
				myPlayer.getPlayerInfo().setMaxGoldMineCapacity(
						2 * myPlayer.getPlayerInfo().getMaxGoldMineCapacity());
				myPlayer.getPlayerInfo().setMaxStoneMineCapacity(
						2 * myPlayer.getPlayerInfo().getMaxStoneMineCapacity());
				myPlayer.getPlayerInfo().setMaxWoodCampCapacity(
						2 * myPlayer.getPlayerInfo().getMaxWoodCampCapacity());

				numOfResearch = 17;
			}
				break;

			case UNIVERSITY:
			{
				myPlayer.getPlayerInfo()
						.setMaxUniversityCapacity(
								6 + myPlayer.getPlayerInfo()
										.getMaxUniversityCapacity());
				numOfResearch = 24;
			}
				break;
			case ADVANCED_ARMORS:
			{
				myPlayer.getPlayerInfo().setAxmanAttackScore(
						(int) (1.2 * myPlayer.getPlayerInfo()
								.getAxmanAttackScore()));
				myPlayer.getPlayerInfo().setSpearmanAttackScore(
						(int) (1.2 * myPlayer.getPlayerInfo()
								.getSpearmanAttackScore()));

				myPlayer.getPlayerInfo().setAxmanDefenceScore(
						(int) (1.2 * myPlayer.getPlayerInfo()
								.getAxmanDefenceScore()));
				myPlayer.getPlayerInfo().setSpearmanDefenceScore(
						(int) (1.2 * myPlayer.getPlayerInfo()
								.getAxmanDefenceScore()));

				numOfResearch = 30;

			}
				break;
			case ANIMAL_HUSBANDRY:
			{
				myPlayer.getPlayerInfo().setAgileCavalryAttackScore(
						(int) (1.2 * myPlayer.getPlayerInfo()
								.getAgileCavalryAttackScore()));
				myPlayer.getPlayerInfo().setHorseMacemanAttackScore(
						(int) (1.2 * myPlayer.getPlayerInfo()
								.getHorseMacemanAttackScore()));

				myPlayer.getPlayerInfo().setAgileCavalryDefenceScore(
						(int) (1.2 * myPlayer.getPlayerInfo()
								.getAgileCavalryDefenceScore()));
				myPlayer.getPlayerInfo().setHorseMacemanDefenceScore(
						(int) (1.2 * myPlayer.getPlayerInfo()
								.getHorseMacemanDefenceScore()));

				numOfResearch = 29;
			}
				break;
			case BASIC_COMBAT:
			{
				myPlayer.getPlayerInfo().setAbilityToBuildBarrack(true);
				myPlayer.getPlayerInfo().setAbilityToTrainSpearman(true);
				myPlayer.getPlayerInfo().setAbilityToTrainAxman(true);

				numOfResearch = 28;
			}
				break;
			case HORSEBACK_RIDING:
			{
				myPlayer.getPlayerInfo().setAbilityToBuildStable(true);
				myPlayer.getPlayerInfo().setAbilityToTrainHorseMaceman(true);
				myPlayer.getPlayerInfo().setAbilityToTrainAgileCavalry(true);

				numOfResearch = 27;
			}
				break;
			case MILITARY:
			{
				numOfResearch = 26;
			}
				break;
		}

	}
}
