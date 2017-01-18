package ims.framework.utils.graphing;

public class GraphicLineStyle
{
	public static final GraphicLineStyle SOLID = new GraphicLineStyle(0, "solid");
	public static final GraphicLineStyle DOT = new GraphicLineStyle(1, "dot");
	public static final GraphicLineStyle DASH = new GraphicLineStyle(2, "dash");
	public static final GraphicLineStyle DASHDOT = new GraphicLineStyle(3, "dashdot");
	public static final GraphicLineStyle LONGDASH = new GraphicLineStyle(4, "longdash");
	public static final GraphicLineStyle LONGDASHDOT = new GraphicLineStyle(5, "longdashdot");
	public static final GraphicLineStyle LONGDASHDOTDOT = new GraphicLineStyle(6, "longdashdotdot");	
	
	private GraphicLineStyle(int id, String value)
	{
		this.id = id;
		this.value = value;
	}
	
	public int getID()
	{
		return this.id;
	}
	public String getValue()
	{
		return this.value;	
	}
	public boolean equals(Object obj)
	{
		if(obj instanceof GraphicLineStyle)
			return ((GraphicLineStyle)obj).getID() == this.id;
		return false;
	}
	public int hashCode()
	{
		return this.id;
	}
	public String toString()
	{
		return this.value;
	}
	
	private int id;
	private String value;
}
