package gui.data;

public class IntegerData extends ObjectData
{
	private int amount;

	public IntegerData(int amount)
	{
		this.amount = amount;
	}

	@Override
	public String getView()
	{
		return Integer.toString(amount);
	}

}
