package ims.framework.utils.graphing;

import ims.framework.exceptions.CodingRuntimeException;
import ims.framework.utils.DateTime;
import ims.framework.utils.Color;
import ims.framework.utils.Image;

public class GraphingCustomOneValue extends GraphingPoint
{	 
	private static final long serialVersionUID = 1L;
	
	public GraphingCustomOneValue(GraphingGroup group, DateTime recordingDateTime, Float value, Object tag, String tooltip, String printInfo)
	{
		initPoint(group, recordingDateTime, value, tag, tooltip, printInfo);		
	}
	public GraphingCustomOneValue(GraphingGroup group, DateTime recordingDateTime, Float value, Object tag, String tooltip)
	{
		initPoint(group, recordingDateTime, value, tag, tooltip, null);		
	}
	public GraphingCustomOneValue(GraphingGroup group, DateTime recordingDateTime, Float value, Object tag)
	{
		initPoint(group, recordingDateTime, value, tag, null, null);		
	}
	public GraphingCustomOneValue(GraphingGroup group, DateTime recordingDateTime, Float value)
	{
		initPoint(group, recordingDateTime, value, null, null, null);		
	}
	private void initPoint(GraphingGroup group, DateTime recordingDateTime, Float value, Object tag, String tooltip, String printInfo)
	{
		if(group == null)
			throw new CodingRuntimeException("Invalid graphing group");
		if(value == null)
			throw new CodingRuntimeException("Invalid value");
		if(recordingDateTime == null)
			throw new CodingRuntimeException("Invalid datetime");
		
		this.group = group;
		
		super.id = GraphingPointType.getNextID();
		super.type = GraphingPointType.CUSTOM_ONE_VALUE;
		
		super.recordingDateTime = recordingDateTime;
		if(super.recordingDateTime.getTime() == null)
		{
			super.recordingDateTime.getTime().setHour(0);
			super.recordingDateTime.getTime().setMinute(0);
			super.recordingDateTime.getTime().setSecond(0);
		}
		super.tag = tag;
		super.tooltip = tooltip;	
		this.printInfo = printInfo;
		
		this.value = value;			
	}
	
	public Float getValue()
	{
		return this.value;
	}
	public String getTooltip()
	{
		return super.tooltip == null ? "" : super.tooltip;
	}
	public String getPrintInfo()
	{
		return this.printInfo == null ? "" : this.printInfo;
	}
	public String getImage()
	{
		return this.image == null ? "" : this.image.getImagePath();
	}
	public void setImage(Image value)
	{
		if(value == null)
			throw new CodingRuntimeException("Invalid image");
		
		this.image = value;
	}
	public Color getLineColor()
	{
		return this.lineColor;
	}
	public void setLineColor(Color value)
	{
		if(value == null)
			throw new CodingRuntimeException("Invalid line color");
		
		this.lineColor = value;
	}
	public GraphingGroup getGroup()
	{
		return this.group;
	}
	@Override
	public String getTitle() 
	{
		if(group == null || group.getName() == null)
			return "";
		return group.getName();
	}
	
	private GraphingGroup group;
	private Float value;	
	private Image image;
	private Color lineColor = Color.Black;
	private String printInfo = null;
}
