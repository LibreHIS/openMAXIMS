package ims.framework.utils.graphing;

import ims.framework.utils.Color;
import ims.framework.utils.DateTime;

public class GraphingMetrics extends GraphingPoint
{	
	private static final long serialVersionUID = 1L;
	
	public GraphingMetrics(DateTime recordingDateTime, Float weightValue, Float heightValue, Float bmiValue, Object tag, String tooltip)
	{
		initPoint(recordingDateTime, weightValue, heightValue, bmiValue, tag, tooltip);
	}
	public GraphingMetrics(DateTime recordingDateTime, Float weightValue, Float heightValue, Float bmiValue, Object tag)
	{
		initPoint(recordingDateTime, weightValue, heightValue, bmiValue, tag, null);
	}
	public GraphingMetrics(DateTime recordingDateTime, Float weightValue, Float heightValue, Float bmiValue)
	{
		initPoint(recordingDateTime, weightValue, heightValue, bmiValue, null, null);
	}
	
	private void initPoint(DateTime recordingDateTime, Float weightValue, Float heightValue, Float bmiValue, Object tag, String tooltip)
	{
		super.id = GraphingPointType.getNextID();
		super.type = GraphingPointType.METRICS;
		
		super.recordingDateTime = recordingDateTime;
		if(super.recordingDateTime.getTime() == null)
		{
			super.recordingDateTime.getTime().setHour(0);
			super.recordingDateTime.getTime().setMinute(0);
			super.recordingDateTime.getTime().setSecond(0);
		}
		super.tag = tag;
		super.tooltip = tooltip;
		
		this.weightValue = weightValue;
		this.heightValue = heightValue;
		this.bmiValue = bmiValue;
	}
	public Float getWeightValue()
	{
		return this.weightValue;
	}
	public Float getHeightValue()
	{
		return this.heightValue;		
	}
	public Float getBMIValue()
	{
		return this.bmiValue;
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
		finalTooltip.append("<b>Weight:</b> " + ((this.weightValue == null) ? "" : this.weightValue.toString()));
		finalTooltip.append("<br>");
		finalTooltip.append("<b>Height:</b> " + ((this.heightValue == null) ? "" : this.heightValue.toString()));
		finalTooltip.append("<br>");
		finalTooltip.append("<b>BMI:</b> " + ((this.bmiValue == null) ? "" : this.bmiValue.toString()));
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
		printInfo.append(", Weight: " + ((this.weightValue == null) ? "" : this.weightValue.toString()));
		printInfo.append(", Height: " + ((this.heightValue == null) ? "" : this.heightValue.toString()));
		printInfo.append(", BMI: " + ((this.bmiValue == null) ? "" : this.bmiValue.toString()));
		
		return printInfo.toString();
	}
	public static String getWeightImageUrl()
	{
		return "g/blue-1.gif";
	}
	public static String getHeightImageUrl()
	{
		return "g/red-circle-1.gif";
	}
	public static String getBMIImageUrl()
	{
		return "g/green-sq-1.gif";
	}
	public static Color getWeightLineColor()
	{
		return Color.DarkBlue;
	}
	public static Color getHeightLineColor()
	{
		return Color.DarkRed;
	}
	public static Color getBMILineColor()
	{
		return Color.DarkGreen;
	}
	@Override
	public String getTitle() 
	{
		return "Metrics";		
	}
	
	private Float weightValue;
	private Float heightValue;
	private Float bmiValue;
}