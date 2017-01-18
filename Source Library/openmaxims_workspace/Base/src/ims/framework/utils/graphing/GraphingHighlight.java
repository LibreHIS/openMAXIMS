package ims.framework.utils.graphing;

import java.io.Serializable;

import ims.framework.utils.Color;

public class GraphingHighlight implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public GraphingHighlight(float value)
	{
		this(value, (float)2.4, null);
	}
	public GraphingHighlight(float value, float weight)
	{
		this(value, weight, null);
	}
	public GraphingHighlight(float value, Color color)
	{
		this(value, (float)2.4, color);
	}
	public GraphingHighlight(float value, float weight, Color color)
	{
		this.value = value;
		this.weight = weight;
		this.color = color;
	}
	
	public float getValue()
	{
		return value;
	}
	public float getWeight()
	{
		return this.weight;
	}
	public Color getColor()
	{
		return this.color;
	}
	public boolean equals(Object obj)
	{
		if(obj instanceof GraphingHighlight)
			return ((GraphingHighlight)obj).getValue() == value;
		return false;
	}
	public int hashCode()
	{
		return super.hashCode();
	}
	
	private float value;
	private float weight;
	private Color color;
}
