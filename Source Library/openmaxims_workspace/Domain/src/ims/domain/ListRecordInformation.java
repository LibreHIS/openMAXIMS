package ims.domain;

import java.io.Serializable;

public class ListRecordInformation implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public ListRecordInformation(int rieCount, int activeCount, String boClassName)
	{
		this.rieCount = rieCount;
		this.activeCount = activeCount;
		this.boClassName = boClassName;
	}
	private int rieCount;
	private int activeCount;
	private String boClassName;

	public int getActiveCount()
	{
		return activeCount;
	}
	public void setActiveCount(int activeCount)
	{
		this.activeCount = activeCount;
	}
	public String getBoClassName()
	{
		return boClassName;
	}
	public void setBoClassName(String boClassName)
	{
		this.boClassName = boClassName;
	}
	public int getRieCount()
	{
		return rieCount;
	}
	public void setRieCount(int rieCount)
	{
		this.rieCount = rieCount;
	}

}
