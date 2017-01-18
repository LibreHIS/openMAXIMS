package ims.framework.enumerations;

import java.io.Serializable;
import java.text.ParseException;

public class Position implements Serializable
{
	private static final long serialVersionUID = 1L;

	private int id;
	private String text;
	
	public static final Position LEFT = new Position(1, "l");
	public static final Position RIGHT = new Position(2, "r");
	public static final Position TOP = new Position(3, "t");
	public static final Position BOTTOM = new Position(4, "b");
	
	private Position(int id, String text)
	{
		this.id = id;
		this.text = text;
	}
	
	public static Position[] getAllInstances()
	{
		return new Position[] { LEFT, RIGHT, TOP, BOTTOM };
	}
	
	public static Position parse(int id)
	{
		for(int x = 0; x < getAllInstances().length; x++)
		{
			if(getAllInstances()[x].id == id)
				return getAllInstances()[x];
		}
		
		return null;
	}
	public static Position parse(String text) throws ParseException
	{
		for(int x = 0; x < getAllInstances().length; x++)
		{
			if(getAllInstances()[x].text.equals(text))
				return getAllInstances()[x];
		}
		
		throw new ParseException("Unable to parse position", 0);
	}
	
	@Override
	public boolean equals(Object value)
	{
		if(value instanceof Position)
			return ((Position)value).id == id;
		return false;
	}
	@Override
	public String toString()
	{
		return text;
	}	
}
