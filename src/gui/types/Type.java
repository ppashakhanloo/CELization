package gui.types;

import java.util.HashSet;
import java.util.Set;

public enum Type
{
	@SuppressWarnings("serial")
	WORKER(new HashSet<CommandType>()
	{
		{
			add(CommandType.MOVE);
			add(CommandType.WORK);
			add(CommandType.BUILD);
		}
	}, new HashSet<InfoType>()
	{
		{
			add(InfoType.INVENTORY);
			add(InfoType.OCCUPATION);
		}
	}, "images/WORKER.png"),

	BOAT(new HashSet<CommandType>(), new HashSet<InfoType>(), "images/BOAT.png"),

	@SuppressWarnings("serial")
	BARRACK(new HashSet<CommandType>()
	{
		{
			add(CommandType.TRAIN_HORSE_MACEMAN);
			add(CommandType.TRAIN_AGILE_CAVALRY);
			add(CommandType.SELL);
		}
	}, new HashSet<InfoType>(), "images/BARRACK.png"),

	@SuppressWarnings("serial")
	STABLE(new HashSet<CommandType>()
	{
		{
			add(CommandType.TRAIN_SPEARMAN);
			add(CommandType.TRAIN_AXMAN);
			add(CommandType.SELL);
		}
	}, new HashSet<InfoType>(), "images/STABLE.png"),

	SCHOLAR(new HashSet<CommandType>(), new HashSet<InfoType>(),
			"images/SCHOLAR.png"),

	@SuppressWarnings("serial")
	AGILE_CAVALRY(new HashSet<CommandType>()
	{
		{
			add(CommandType.MOVE);
			add(CommandType.ATTACK);
		}
	}, new HashSet<InfoType>()
	{
		{
			add(InfoType.HEALTH);
			add(InfoType.ATTACK_SCORE);
			add(InfoType.DEFENCE_SCORE);
			add(InfoType.SCORE_TOWARD_CAVALRY);
			add(InfoType.SCORE_TOWARD_INFANTRY);
			add(InfoType.ATTACK_SCORE_THIS_SOLDIER);
		}
	}, "images/AGILE_CAVALRY.png"),

	@SuppressWarnings("serial")
	SPEARMAN(new HashSet<CommandType>()
	{
		{
			add(CommandType.MOVE);
			add(CommandType.ATTACK);
		}
	}, new HashSet<InfoType>()
	{
		{
			add(InfoType.HEALTH);
			add(InfoType.ATTACK_SCORE);
			add(InfoType.DEFENCE_SCORE);
			add(InfoType.SCORE_TOWARD_CAVALRY);
			add(InfoType.SCORE_TOWARD_INFANTRY);
			add(InfoType.ATTACK_SCORE_THIS_SOLDIER);
		}
	}, "images/SPEARMAN.png"),

	@SuppressWarnings("serial")
	AXMAN(new HashSet<CommandType>()
	{
		{
			add(CommandType.MOVE);
			add(CommandType.ATTACK);
		}
	}, new HashSet<InfoType>()
	{
		{
			add(InfoType.HEALTH);
			add(InfoType.ATTACK_SCORE);
			add(InfoType.DEFENCE_SCORE);
			add(InfoType.SCORE_TOWARD_CAVALRY);
			add(InfoType.SCORE_TOWARD_INFANTRY);
			add(InfoType.ATTACK_SCORE_THIS_SOLDIER);
		}
	}, "images/AXMAN.png"),

	@SuppressWarnings("serial")
	HORSE_MACEMAN(new HashSet<CommandType>()
	{
		{
			add(CommandType.MOVE);
			add(CommandType.ATTACK);
		}
	}, new HashSet<InfoType>()
	{
		{
			add(InfoType.HEALTH);
			add(InfoType.ATTACK_SCORE);
			add(InfoType.DEFENCE_SCORE);
			add(InfoType.SCORE_TOWARD_CAVALRY);
			add(InfoType.SCORE_TOWARD_INFANTRY);
			add(InfoType.ATTACK_SCORE_THIS_SOLDIER);
		}
	}, "images/HORSE_MACEMAN.png"),

	INVISIBLE_BLOCK(new HashSet<CommandType>(), new HashSet<InfoType>(),
			"images/INVISIBLE.png"),

	@SuppressWarnings("serial")
	MOUNTAIN_BLOCK(new HashSet<CommandType>(), new HashSet<InfoType>()
	{
		{
			add(InfoType.STONE);
			add(InfoType.GOLD);
		}
	}, "images/MOUNTAIN.png"),

	@SuppressWarnings("serial")
	PLAIN_BLOCK(new HashSet<CommandType>(), new HashSet<InfoType>()
	{
		{
			add(InfoType.FOOD);
		}
	}, "images/PLAIN.png"),

	@SuppressWarnings("serial")
	JUNGLE_BLOCK(new HashSet<CommandType>(), new HashSet<InfoType>()
	{
		{
			add(InfoType.LUMBER);
		}
	}, "images/JUNGLE.png"),

	@SuppressWarnings("serial")
	WATER_BLOCK(new HashSet<CommandType>(), new HashSet<InfoType>()
	{
		{
			add(InfoType.FOOD);
		}
	}, "images/WATER.png"),

	@SuppressWarnings("serial")
	MAIN_BUILDING(new HashSet<CommandType>()
	{
		{
			add(CommandType.TRAIN_WORKER);
			add(CommandType.SELL);
		}
	}, new HashSet<InfoType>(), "images/MAIN_BUILDING.png"), @SuppressWarnings("serial")
	PORT(new HashSet<CommandType>()
	{
		{
			add(CommandType.TRAIN_BOAT);
		}
	}, new HashSet<InfoType>(), "images/PORT.png"), @SuppressWarnings("serial")
	UNIVERSITY(new HashSet<CommandType>()
	{
		{
			add(CommandType.TRAIN_SCHOLAR);
			add(CommandType.SELL);
		}
	}, new HashSet<InfoType>(), "images/UNIVERSITY.png"), @SuppressWarnings("serial")
	MARKET(new HashSet<CommandType>()
	{
		{
			add(CommandType.SELL);
		}
	}, new HashSet<InfoType>(), "images/MARKET.png"), @SuppressWarnings("serial")
	FARM(new HashSet<CommandType>()
	{
		{
			add(CommandType.SELL);
		}
	}, new HashSet<InfoType>(), "images/FARM.png"), @SuppressWarnings("serial")
	STOCKPILE(new HashSet<CommandType>()
	{
		{
			add(CommandType.SELL);
		}
	}, new HashSet<InfoType>(), "images/STOCKPILE.png"), @SuppressWarnings("serial")
	GOLD_MINE(new HashSet<CommandType>()
	{
		{
			add(CommandType.SELL);
		}
	}, new HashSet<InfoType>(), "images/GOLD_MINE.png"), @SuppressWarnings("serial")
	STONE_MINE(new HashSet<CommandType>()
	{
		{
			add(CommandType.SELL);
		}
	}, new HashSet<InfoType>(), "images/STONE_MINE.png"), @SuppressWarnings("serial")
	WOOD_CAMP(new HashSet<CommandType>()
	{
		{
			add(CommandType.SELL);
		}
	}, new HashSet<InfoType>(), "images/WOODCAMP.gif"),

	GOLD(new HashSet<CommandType>(), new HashSet<InfoType>(), ""),

	STONE(new HashSet<CommandType>(), new HashSet<InfoType>(), ""),

	FOOD(new HashSet<CommandType>(), new HashSet<InfoType>(), ""),

	KNOWLEDGE(new HashSet<CommandType>(), new HashSet<InfoType>(), ""),

	LUMBER(new HashSet<CommandType>(), new HashSet<InfoType>(), "");

	private Set<CommandType> commandTypes;
	private Set<InfoType> infoTypes;
	private String address;

	private Type(Set<CommandType> commandTypes, Set<InfoType> infoTypes,
			String address)
	{
		this.commandTypes = commandTypes;
		this.infoTypes = infoTypes;
		this.address = address;
	}

	public Set<CommandType> getCommandTypes()
	{
		return commandTypes;
	}

	public Set<InfoType> getInfoTypes()
	{
		return infoTypes;
	}

	public String getAddress()
	{
		return address;
	}
};
