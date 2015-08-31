package logic.utilities;

import java.io.Serializable;
import java.util.ArrayList;

import logic.types.ResearchType;
import logic.types.ResourceType;

/**
 * @author Pardis Pashakhanloo
 * @since 1.6
 */
public class Research implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Resource goldResource;
	private Resource stoneResource;
	private Resource lumberResource;
	private Resource knowledgeResource;
	private ResearchType researchType;
	private int ETA;

	private ArrayList<Research> neededResearches = new ArrayList<Research>();
	private ArrayList<Research> optionalResearches = new ArrayList<Research>();
	private boolean isFinished;
	private boolean isAvailable;

	public Research(ResearchType researchType, int gold, int stone, int lumber,
			int knowledge, int ETA)
	{
		setResearchType(researchType);

		goldResource = new Resource(gold, ResourceType.GOLD, 200);
		stoneResource = new Resource(stone, ResourceType.STONE, 200);
		lumberResource = new Resource(lumber, ResourceType.LUMBER, 200);
		knowledgeResource = new Resource(knowledge, ResourceType.KNOWLEDGE, 200);

		setETA(ETA);
		isAvailable = false;
	}

	public void setAvailable(boolean isAvailable)
	{
		this.isAvailable = isAvailable;
	}

	public boolean isAvailable()
	{
		if (isAvailable)
			return true;
		for (int i = 0; i < neededResearches.size(); i++)
		{
			if (!neededResearches.get(i).isFinished)
			{
				return false;
			}
		}
		for (int i = 0; i < optionalResearches.size(); i++)
		{
			if (optionalResearches.get(i).isFinished)
				return true;
		}
		return true;
	}

	public void setETA(int eTA)
	{
		ETA = eTA;
	}

	public int getETA()
	{
		return ETA;
	}

	public void setResearchType(ResearchType researchType)
	{
		this.researchType = researchType;
	}

	public Resource getGoldResource()
	{
		return goldResource;
	}

	public Resource getStoneResource()
	{
		return stoneResource;
	}

	public Resource getLumberResource()
	{
		return lumberResource;
	}

	public Resource getKnowledgeResource()
	{
		return knowledgeResource;
	}

	public ArrayList<Research> getNeededResearches()
	{
		return neededResearches;
	}

	public ArrayList<Research> getOptionalResearches()
	{
		return optionalResearches;
	}

	public void setFinished(boolean isFinished)
	{
		this.isFinished = isFinished;
	}

	public boolean isFinished()
	{
		return isFinished;
	}

	public void addToNeededResearch(Research research)
	{
		neededResearches.add(research);
	}

	public void addToOptionalResearch(Research research)
	{
		optionalResearches.add(research);
	}

	public ResearchType getResearchType()
	{
		return researchType;
	}

}
