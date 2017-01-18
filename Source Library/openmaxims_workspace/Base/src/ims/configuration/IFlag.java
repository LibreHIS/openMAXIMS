package ims.configuration;

public interface IFlag
{
	public String getComment();
	public boolean isSystem();
	public String getName();
	public String[] getOptions();
	public boolean hasOptions();
	public String getToolTip();
	public FlagType getType();
	public String getDefaultValAsString();
	public String getValAsString();
	public void setToDefault();
	public void setValue(Object val);
}
