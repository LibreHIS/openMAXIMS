package ims.framework.utils;

import java.io.Serializable;

public class IntRange implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public IntRange()
	{
		this.min = null;
		this.max = null;
	}
	public IntRange(Integer min, Integer max)
	{
		this.min = min;
		this.max = max;
	}
	public Integer getMax()
	{
		return this.max;
	}
	public Integer getMin()
	{
		return this.min;
	}
	
	private Integer min;
	private Integer max;
}
