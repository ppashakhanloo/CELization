package logic.actionController;

import logic.actions.ResearchAction;
import logic.game.Player;
import logic.types.ResearchType;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
public class ResearchController extends ActionController
{
	private ResearchType researchType;
	private int remainingTurns;

	private boolean isBeginingOfTheResearching;
	private boolean abilityToResearch;

	/**
	 * 
	 * @param myPlayer
	 * @param researchType
	 */
	public ResearchController(Player myPlayer, ResearchType researchType)
	{
		super(myPlayer);
		this.researchType = researchType;
		this.remainingTurns = myPlayer.getPlayerInfo().getResearchesMap()
				.get(researchType).getETA();
		this.isBeginingOfTheResearching = true;
		myPlayer.getUniversities().get(0).setActionController(this);
	}

	/**
	 * research type getter
	 * 
	 * @return research type
	 */
	public ResearchType getResearchType()
	{
		return researchType;
	}

	/**
	 * get current remaining turns
	 * 
	 * @return remaining turns integer
	 */
	public int getRemainingTurns()
	{
		return remainingTurns;
	}

	/**
	 * set the status of the research as FINISHED
	 */
	private void finished()
	{
		getMyPlayer().getPlayerInfo().getResearchesMap().get(researchType)
				.setFinished(true);
	}

	/**
	 * 
	 * @return a boolean showing if previous researches have been done in ordet
	 *         to make this research available or not
	 */
	public boolean isPreviousReserachesFinished()
	{
		for (int i = 0; i < getMyPlayer().getPlayerInfo().getResearchesMap()
				.get(researchType).getNeededResearches().size(); i++)
		{

			if (!getMyPlayer().getPlayerInfo().getResearchesMap()
					.get(researchType).getNeededResearches().get(i)
					.isFinished())
				return false;
		}

		for (int i = 0; i < getMyPlayer().getPlayerInfo().getResearchesMap()
				.get(researchType).getOptionalResearches().size(); i++)
		{
			if (getMyPlayer().getPlayerInfo().getResearchesMap()
					.get(researchType).getOptionalResearches().get(i)
					.isFinished())
				return true;
		}

		return true;

	}

	/**
	 * 
	 * @return a boolean showing if resources are enough for the research or not
	 */
	public boolean isResorcesEnough()
	{
		if (getMyPlayer().getPlayerInfo().getGoldInStock().getAmount() >= getMyPlayer()
				.getPlayerInfo().getResearchesMap().get(researchType)
				.getGoldResource().getAmount()

				&& getMyPlayer().getPlayerInfo().getLumberInStock().getAmount() >= getMyPlayer()
						.getPlayerInfo().getResearchesMap().get(researchType)
						.getLumberResource().getAmount()
				&& getMyPlayer().getPlayerInfo().getStoneInStock().getAmount() >= getMyPlayer()
						.getPlayerInfo().getResearchesMap().get(researchType)
						.getStoneResource().getAmount()
				&& getMyPlayer().getPlayerInfo().getKnowledgeInStock()
						.getAmount() >= getMyPlayer().getPlayerInfo()
						.getResearchesMap().get(researchType)
						.getKnowledgeResource().getAmount())
			return true;

		return false;
	}

	/**
	 * decrease resources if the research is able to be done
	 */
	public void decreaseResources()
	{
		getMyPlayer()
				.getPlayerInfo()
				.getGoldInStock()
				.removeFrom(
						getMyPlayer().getPlayerInfo().getResearchesMap()
								.get(researchType).getGoldResource()
								.getAmount());
		getMyPlayer()
				.getPlayerInfo()
				.getStoneInStock()
				.removeFrom(
						getMyPlayer().getPlayerInfo().getResearchesMap()
								.get(researchType).getStoneResource()
								.getAmount());
		getMyPlayer()
				.getPlayerInfo()
				.getLumberInStock()
				.removeFrom(
						getMyPlayer().getPlayerInfo().getResearchesMap()
								.get(researchType).getLumberResource()
								.getAmount());
		getMyPlayer()
				.getPlayerInfo()
				.getKnowledgeInStock()
				.removeFrom(
						getMyPlayer().getPlayerInfo().getResearchesMap()
								.get(researchType).getKnowledgeResource()
								.getAmount());
	}

	/**
	 * Call the next turn of the RESEARCH
	 */
	@Override
	public void nextTurn()
	{

		abilityToResearch = (isResorcesEnough() && isPreviousReserachesFinished());

		if (abilityToResearch)
		{
			this.remainingTurns--;
			if (isBeginingOfTheResearching)
			{
				decreaseResources();
				isBeginingOfTheResearching = false;
			} else if (remainingTurns == 0)
			{
				ResearchAction researchAction = new ResearchAction(
						getMyPlayer(), researchType);
				researchAction.doAction();
			}
			if (this.remainingTurns == 0)
			{
				getMyPlayer().getDoneActions().add(
						"[RESEARCH " + researchType + " WAS FINISHED]");
				finished();
			}
		}
	}

}
