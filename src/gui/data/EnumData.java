package gui.data;

public class EnumData extends ObjectData
{
	@SuppressWarnings("rawtypes")
	private Enum enum1;

	@SuppressWarnings("rawtypes")
	public EnumData(Enum enum1)
	{
		this.enum1 = enum1;
	}

	@Override
	public String getView()
	{
		return enum1.toString();
	}

}
