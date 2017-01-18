package ims.framework.utils.graphing;

import ims.framework.utils.Color;
import ims.framework.utils.DateTime;

public class GraphingRespiration extends GraphingPoint
{	
	private static final long serialVersionUID = 1L;
	
	public GraphingRespiration(DateTime recordingDateTime, Integer rate, Object tag, String tooltip)
	{
		initPoint(recordingDateTime, rate, tag, tooltip);
	}
	public GraphingRespiration(DateTime recordingDateTime, Integer rate, Object tag)
	{
		initPoint(recordingDateTime, rate, tag, null);
	}
	public GraphingRespiration(DateTime recordingDateTime, Integer rate)
	{
		initPoint(recordingDateTime, rate, null, null);
	}
	
	private void initPoint(DateTime recordingDateTime, Integer rate, Object tag, String tooltip)
	{
		super.id = GraphingPointType.getNextID();	
		super.type = GraphingPointType.RESPIRATION;
		
		super.recordingDateTime = recordingDateTime;
		if(super.recordingDateTime.getTime() == null)
		{
			super.recordingDateTime.getTime().setHour(0);
			super.recordingDateTime.getTime().setMinute(0);
			super.recordingDateTime.getTime().setSecond(0);
		}
		super.tag = tag;
		super.tooltip = tooltip;
		
		this.rate = rate;
	}
	public Integer getRate()
	{
		return this.rate;
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
		finalTooltip.append("<b>Rate:</b> " + ((this.rate == null) ? "" : this.rate.toString()));
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
		printInfo.append(", Rate: " + ((this.rate == null) ? "" : this.rate.toString()));
		
		return printInfo.toString();
	}
	public static String getImageUrl()
	{
		return "g/littleGreenCircle.gif";
	}
	public static Color getLineColor()
	{
		return Color.DarkGreen;
	}
	@Override
	public String getTitle() 
	{
		return "Respiration";		
	}
	
	private Integer rate;	
}
