package ims.framework.utils.graphing;

import ims.framework.utils.Color;
import ims.framework.utils.DateTime;

public class GraphingBloodSugar extends GraphingPoint
{	
	private static final long serialVersionUID = 1L;
	
	public GraphingBloodSugar(DateTime recordingDateTime, Float preValue, Float value, Float postValue, Object tag, String tooltip)
	{
		initPoint(recordingDateTime, preValue, value, postValue, tag, tooltip);		
	}
	public GraphingBloodSugar(DateTime recordingDateTime, Float preValue, Float value, Float postValue, Object tag)
	{
		initPoint(recordingDateTime, preValue, value, postValue, tag, null);		
	}
	public GraphingBloodSugar(DateTime recordingDateTime, Float preValue, Float value, Float postValue)
	{
		initPoint(recordingDateTime, preValue, value, postValue, null, null);		
	}
	private void initPoint(DateTime recordingDateTime, Float preValue, Float value, Float postValue, Object tag, String tooltip)
	{
		super.id = GraphingPointType.getNextID();
		super.type = GraphingPointType.BLOODSUGAR;
						
		super.recordingDateTime = recordingDateTime;
		if(super.recordingDateTime.getTime() == null)
		{
			super.recordingDateTime.getTime().setHour(0);
			super.recordingDateTime.getTime().setMinute(0);
			super.recordingDateTime.getTime().setSecond(0);
		}
		super.tag = tag;
		super.tooltip = tooltip;
		
		this.preValue = preValue;
		this.value = value;		
		this.postValue = postValue;
	}
	public Float getPreValue()
	{
		return this.preValue;
	}
	public Float getValue()
	{
		return this.value;
	}
	public Float getPostValue()
	{
		return this.postValue;
	}
	public String getTooltip()
	{
		StringBuffer finalTooltip = new StringBuffer();
		
		finalTooltip.append("<b>");
		finalTooltip.append(super.getType().toString());
		finalTooltip.append("</b>");
		finalTooltip.append("<br><br>");
		finalTooltip.append("<b>Date and Time:</b> " + super.recordingDateTime.toString());
		if(this.preValue != null)
		{
			finalTooltip.append("<br>");
			finalTooltip.append("<b>CBG Pre Value:</b> " + ((this.preValue == null) ? "" : this.preValue.toString()));
		}		
		if(this.postValue != null)
		{
			finalTooltip.append("<br>");
			finalTooltip.append("<b>CBG Post Value:</b> " + ((this.postValue == null) ? "" : this.postValue.toString()));
		}
		if(this.value != null)
		{
			finalTooltip.append("<br>");
			finalTooltip.append("<b>Random Blood Glucose:</b> " + ((this.value == null) ? "" : this.value.toString()));
		}
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
		if(this.preValue != null)
			printInfo.append(", CBG Pre Value: " + ((this.preValue == null) ? "" : this.preValue.toString()));		
		if(this.postValue != null)
			printInfo.append(", CBG Post Value: " + ((this.postValue == null) ? "" : this.postValue.toString()));
		if(this.value != null)
			printInfo.append(", Random Blood Glucose: " + ((this.value == null) ? "" : this.value.toString()));
		
		return printInfo.toString();
	}
	public static String getPreValueImageUrl()
	{
		return "g/LittleBlueCircle.gif";
	}
	public static String getValueImageUrl()
	{
		return "g/red-circle-1.gif";
	}
	public static String getPostValueImageUrl()
	{
		return "g/LittleBlackCircle.gif";
	}
	public static Color getPreValueLineColor()
	{
		return Color.DarkBlue;
	}
	public static Color getValueLineColor()
	{
		return Color.DarkRed;
	}
	public static Color getPostValueLineColor()
	{
		return Color.Black;
	}
	@Override
	public String getTitle() 
	{
		return "Blood Sugar";		
	}

	private Float preValue;
	private Float value;	
	private Float postValue;
}
