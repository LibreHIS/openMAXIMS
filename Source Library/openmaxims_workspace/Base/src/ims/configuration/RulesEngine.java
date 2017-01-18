package ims.configuration;

public class RulesEngine 
{
	private int type;
	
	public static RulesEngine NONE = new RulesEngine(0);
	public static RulesEngine DROOLS = new RulesEngine(1);
	
	private RulesEngine(int type)
	{
		this.type = type;
	}	
	
	public static RulesEngine parse(Integer type)
	{
		if(type == null)
			return NONE;
		
		if(type == NONE.type)
			return NONE;
		else if(type == DROOLS.type)
			return DROOLS;
		
		return NONE;
	}
}
