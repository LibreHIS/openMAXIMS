package ims.framework.utils.graphing;

import ims.framework.utils.Color;
import ims.framework.utils.DateTime;

public class GraphingTemperature extends GraphingPoint
{	
	private static final long serialVersionUID = 1L;
	
	public GraphingTemperature(DateTime recordingDateTime, Float value, Object tag, String tooltip)
	{
		initPoint(recordingDateTime, value, tag, tooltip);
	}
	public GraphingTemperature(DateTime recordingDateTime, Float value, Object tag)
	{
		initPoint(recordingDateTime, value, tag, null);
	}
	public GraphingTemperature(DateTime recordingDateTime, Float value)
	{
		initPoint(recordingDateTime, value, null, null);
	}
	
	private void initPoint(DateTime recordingDateTime, Float value, Object tag, String tooltip)
	{
		super.id = GraphingPointType.getNextID();
		super.type = GraphingPointType.TEMPERATURE;
		
		super.recordingDateTime = recordingDateTime;
		if(super.recordingDateTime.getTime() == null)
		{
			super.recordingDateTime.getTime().setHour(0);
			super.recordingDateTime.getTime().setMinute(0);
			super.recordingDateTime.getTime().setSecond(0);
		}
		super.tag = tag;
		super.tooltip = tooltip;
		
		this.value = value;
	}
	public Float getValue()
	{
		return this.value;
	}
	public String getTooltip()
	{
		StringBuffer finalTooltip = new StringBuffer();
		
		finalTooltip.append("<b>");
		finalTooltip.append(super.getType().toString());
		finalTooltip.append("</b>");
		finalTooltip.append("<br><br>");
		finalTooltip.append("<b>Date and Time:</b> " + super.recordingDateTime.toString());
		finalTooltip.append("<br>");
		finalTooltip.append("<b>Value:</b> " + ((this.value == null) ? "" : this.value.toString()));
		if(super.tooltip != null && super.tooltip.length() > 0)
		{
			finalTooltip.append("<br><br>");
			finalTooltip.append(super.tooltip);
		}
		return finalTooltip.toString();
	}
	public String getPrintInfo()
	{
		StringBuffer printInfo = new StringBuffer();
		
		printInfo.append("Date and Time: " + super.recordingDateTime.toString());
		printInfo.append(", Value: " + ((this.value == null) ? "" : this.value.toString()));
		
		return printInfo.toString();
	}
	public static String getImageUrl()
	{
		return "g/LittleBlueCircle.gif";
	}
	public static Color getLineColor()
	{
		return Color.DarkBlue;
	}
	@Override
	public String getTitle() 
	{
		return "Temperature";		
	}
		
	private Float value;	
}
