package ims.framework.utils.graphing;

import ims.framework.utils.Color;
import ims.framework.utils.DateTime;

public class GraphingGlasgowComaScale extends GraphingPoint
{	
	private static final long serialVersionUID = 1L;
	
	public GraphingGlasgowComaScale(DateTime recordingDateTime, Integer verbalValue, Integer motorValue, Integer eyeValue, Integer totalValue, Object tag, String tooltip)
	{
		initPoint(recordingDateTime, verbalValue, motorValue, eyeValue, totalValue, tag, tooltip);
	}
	public GraphingGlasgowComaScale(DateTime recordingDateTime, Integer verbalValue, Integer motorValue, Integer eyeValue, Integer totalValue, Object tag)
	{
		initPoint(recordingDateTime, verbalValue, motorValue, eyeValue, totalValue, tag, null);
	}
	public GraphingGlasgowComaScale(DateTime recordingDateTime, Integer verbalValue, Integer motorValue, Integer eyeValue, Integer totalValue)
	{
		initPoint(recordingDateTime, verbalValue, motorValue, eyeValue, totalValue, null, null);
	}
	
	private void initPoint(DateTime recordingDateTime, Integer verbalValue, Integer motorValue, Integer eyeValue, Integer totalValue, Object tag, String tooltip)
	{
		super.id = GraphingPointType.getNextID();
		super.type = GraphingPointType.GLASGOWCOMASCALE;
	
		super.recordingDateTime = recordingDateTime;
		if(super.recordingDateTime.getTime() == null)
		{
			super.recordingDateTime.getTime().setHour(0);
			super.recordingDateTime.getTime().setMinute(0);
			super.recordingDateTime.getTime().setSecond(0);
		}
		super.tag = tag;
		super.tooltip = tooltip;
		
		this.verbalValue = verbalValue;
		this.motorValue = motorValue;
		this.eyeValue = eyeValue;
		this.totalValue = totalValue;
	}
	public Integer getVerbalValue()
	{
		return this.verbalValue;
	}
	public Integer getMotorValue()
	{
		return this.motorValue;
	}
	public Integer getEyeValue()
	{
		return this.eyeValue;
	}
	public Integer getTotalValue()
	{
		return this.totalValue;
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
		finalTooltip.append("<b>Verbal:</b> " + ((this.verbalValue == null) ? "" : this.verbalValue.toString()));
		finalTooltip.append("<br>");
		finalTooltip.append("<b>Motor:</b> " + ((this.motorValue == null) ? "" : this.motorValue.toString()));
		finalTooltip.append("<br>");
		finalTooltip.append("<b>Eye:</b> " + ((this.eyeValue == null) ? "" : this.eyeValue.toString()));
		finalTooltip.append("<br>");
		finalTooltip.append("<b>Total:</b> " + ((this.totalValue == null) ? "" : this.totalValue.toString()));
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
		printInfo.append(", Verbal: " + ((this.verbalValue == null) ? "" : this.verbalValue.toString()));
		printInfo.append(", Motor: " + ((this.motorValue == null) ? "" : this.motorValue.toString()));
		printInfo.append(", Eye: " + ((this.eyeValue == null) ? "" : this.eyeValue.toString()));
		printInfo.append(", Total: " + ((this.totalValue == null) ? "" : this.totalValue.toString()));
		
		return printInfo.toString();
	}
	public static String getVerbalImageUrl()
	{
		return "g/green-sq-1.gif";
	}
	public static String getMotorImageUrl()
	{
		return "g/blue-1.gif";
	}
	public static String getEyeImageUrl()
	{
		return "g/888888-1.gif";
	}
	public static String getTotalImageUrl()
	{
		return "g/red-circle-1.gif";
	}
	public static Color getVerbalLineColor()
	{
		return Color.DarkGreen;
	}
	public static Color getMotorLineColor()
	{
		return Color.DarkBlue;
	}
	public static Color getEyeLineColor()
	{
		return Color.DarkGray;
	}
	public static Color getTotalLineColor()
	{
		return Color.DarkRed;
	}
	@Override
	public String getTitle() 
	{
		return "Glasgow Coma Scale";		
	}
	
	private Integer verbalValue;
	private Integer motorValue;
	private Integer eyeValue;
	private Integer totalValue;
}
