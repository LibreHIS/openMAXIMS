package ims.framework.utils.graphing;

import java.io.Serializable;

import ims.framework.utils.DateTime;

abstract public class GraphingPoint implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	public abstract String getTooltip();
	public abstract String getPrintInfo();
	public abstract String getTitle();
	
	public int getID()
	{
		return this.id;
	}
	
	public GraphingPointType getType()
	{
		return this.type;
	}
	public DateTime getRecordingDateTime()
	{
		return this.recordingDateTime;
	}	
	public Object getTag()
	{
		return this.tag;
	}
	
	protected int id;
	protected GraphingPointType type;
	protected String tooltip;	
	protected DateTime recordingDateTime;
	protected Object tag;
}

