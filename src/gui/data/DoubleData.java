package gui.data;

public class DoubleData extends ObjectData
{
	private double amount;

	public DoubleData(double amount)
	{
		this.amount = amount;
	}

	@Override
	public String getView()
	{
		return Double.toString(amount);
	}
}
