package ims.configuration;

public abstract class AFlag implements Comparable, IFlag
{
	protected String name;
	protected String comment;
	protected String[] options = new String[0];
	protected boolean isSystem = false;

	public String getComment()
	{
		return comment;
	}

	public boolean isSystem()
	{
		return isSystem;
	}

	public String getName()
	{
		return name;
	}

	public String[] getOptions()
	{
		return options;
	}

	public boolean hasOptions()
	{
		if (options == null)
			return false;
		
		return options.length > 0;
	}

	public int compareTo(Object o) 
	{
		return compareTo(o, true);
	}
	
	public int compareTo(Object o, boolean caseInsensitive) 
	{
		if(o == null) return -1;
		if(!(o instanceof IFlag)) return -1;
		IFlag cf = (IFlag)o;
		if (caseInsensitive) return cf.getName().compareToIgnoreCase(this.getName());
		
		return cf.getName().compareTo(this.getName());
	}
	public String getToolTip()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<b>Name : </b>" + this.getName());
		sb.append("<br><b>Type : </b>" + getType());
		sb.append("<br><b>Default Value : </b>" + this.getDefaultValAsString());
		sb.append("<br><b>Current Value : </b>" + this.getValAsString());
		sb.append("<br><b>System Flag   : </b>" + this.isSystem());
		sb.append("<br><b>Comment : </b>" + this.getComment());		
		return sb.toString();
	}
	
	public abstract Object getObjectVal();
	public abstract void setToDefault();	
	public abstract void setValue(Object val);	
	public abstract void setDefaultValue(String value);
}
