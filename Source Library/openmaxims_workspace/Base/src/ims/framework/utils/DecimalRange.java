package ims.framework.utils;

import java.io.Serializable;

public class DecimalRange implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public DecimalRange()
	{
		this.min = null;
		this.max = null;
	}
	public DecimalRange(Float min, Float max)
	{
		this.min = min;
		this.max = max;
	}
	public Float getMax()
	{
		return this.max;
	}
	public Float getMin()
	{
		return this.min;
	}
	private Float min;
	private Float max;
}
