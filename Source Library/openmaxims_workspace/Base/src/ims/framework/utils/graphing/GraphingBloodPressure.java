package ims.framework.utils.graphing;

import ims.framework.exceptions.CodingRuntimeException;
import ims.framework.utils.DateTime;
import ims.framework.utils.Color;
import ims.framework.utils.StringUtils;

public class GraphingBloodPressure extends GraphingPoint
{	
	private static final long serialVersionUID = 1L;
	
	public GraphingBloodPressure(DateTime recordingDateTime, Float standingSysValue, Float standingDiaValue, Float sittingSysValue, Float sittingDiaValue, Float lyingSysValue, Float lyingDiaValue, Object tag, String tooltip)
	{
		initPoint(recordingDateTime, standingSysValue, standingDiaValue, sittingSysValue, sittingDiaValue, lyingSysValue, lyingDiaValue, tag, tooltip);		
	}
	public GraphingBloodPressure(DateTime recordingDateTime, Float standingSysValue, Float standingDiaValue, Float sittingSysValue, Float sittingDiaValue, Float lyingSysValue, Float lyingDiaValue, Object tag)
	{
		initPoint(recordingDateTime, standingSysValue, standingDiaValue, sittingSysValue, sittingDiaValue, lyingSysValue, lyingDiaValue, tag, null);		
	}
	public GraphingBloodPressure(DateTime recordingDateTime, Float standingSysValue, Float standingDiaValue, Float sittingSysValue, Float sittingDiaValue, Float lyingSysValue, Float lyingDiaValue)
	{
		initPoint(recordingDateTime, standingSysValue, standingDiaValue, sittingSysValue, sittingDiaValue, lyingSysValue, lyingDiaValue, null, null);		
	}
	private void initPoint(DateTime recordingDateTime, Float standingSysValue, Float standingDiaValue, Float sittingSysValue, Float sittingDiaValue, Float lyingSysValue, Float lyingDiaValue, Object tag, String tooltip)
	{
		super.id = GraphingPointType.getNextID();
		super.type = GraphingPointType.BLOODPRESSURE;
		
		super.recordingDateTime = recordingDateTime;
		if(super.recordingDateTime.getTime() == null)
		{
			super.recordingDateTime.getTime().setHour(0);
			super.recordingDateTime.getTime().setMinute(0);
			super.recordingDateTime.getTime().setSecond(0);
		}
		super.tag = tag;
		super.tooltip = tooltip;		
		
		this.standingSysValue = standingSysValue;
		this.standingDiaValue = standingDiaValue;
		this.sittingSysValue = sittingSysValue;
		this.sittingDiaValue = sittingDiaValue;	
		this.lyingSysValue = lyingSysValue;
		this.lyingDiaValue = lyingDiaValue;
	}
	
	public Float getStandingSysValue()
	{
		return this.standingSysValue;
	}
	public Float getStandingDiaValue()
	{
		return this.standingDiaValue;
	}
	public Float getSittingSysValue()
	{
		return this.sittingSysValue;
	}
	public Float getSittingDiaValue()
	{
		return this.sittingDiaValue;
	}
	public Float getLyingSysValue()
	{
		return this.lyingSysValue;
	}
	public Float getLyingDiaValue()
	{
		return this.lyingDiaValue;
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
		finalTooltip.append("<b>" + StringUtils.encodeXML(this.sittingLabel) + ":</b> " + ((this.sittingSysValue == null) ? "" : this.sittingSysValue.toString()) + "/" + ((this.sittingDiaValue == null) ? "" : this.sittingDiaValue.toString()));
		finalTooltip.append("<br>");
		finalTooltip.append("<b>" + StringUtils.encodeXML(this.standingLabel) + ":</b> " + ((this.standingSysValue == null) ? "" : this.standingSysValue.toString()) + "/" + ((this.standingDiaValue == null) ? "" : this.standingDiaValue.toString()));
		finalTooltip.append("<br>");
		finalTooltip.append("<b>" + StringUtils.encodeXML(this.lyingLabel) + ":</b> " + ((this.lyingSysValue == null) ? "" : this.lyingSysValue.toString()) + "/" + ((this.lyingDiaValue == null) ? "" : this.lyingDiaValue.toString()));
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
		printInfo.append(", " + this.sittingLabel + ": " + ((this.sittingSysValue == null) ? "" : this.sittingSysValue.toString()) + "/" + ((this.sittingDiaValue == null) ? "" : this.sittingDiaValue.toString()));
		printInfo.append(", " + this.standingLabel + ": " + ((this.standingSysValue == null) ? "" : this.standingSysValue.toString()) + "/" + ((this.standingDiaValue == null) ? "" : this.standingDiaValue.toString()));		
		printInfo.append(", " + this.lyingLabel + ": " + ((this.lyingSysValue == null) ? "" : this.lyingSysValue.toString()) + "/" + ((this.lyingDiaValue == null) ? "" : this.lyingDiaValue.toString()));
		
		return printInfo.toString();
	}
	public static String getStandingSysImageUrl()
	{
		return "g/red-white-triangle-up-1.gif";
	}
	public static String getStandingDiaImageUrl()
	{
		return "g/red-white-triangle-down-1.gif";
	}
	public static String getSittingSysImageUrl()
	{
		return "g/black-triangle-up-1.gif";
	}
	public static String getSittingDiaImageUrl()
	{
		return "g/black-triangle-down-1.gif";
	}
	public static String getLyingSysImageUrl()
	{
		return "g/blue-triangle-up-1.gif";
	}
	public static String getLyingDiaImageUrl()
	{
		return "g/blue-triangle-down-1.gif";
	}
	public static Color getLineColorStanding()
	{
		return Color.DarkRed;
	}
	public static Color getLineColorSitting()
	{
		return Color.Black;
	}
	public static Color getLineColorLying()
	{
		return Color.DarkBlue;
	}		
	public void setSittingLabel(String value)
	{
		if(value == null)
			throw new CodingRuntimeException("Invalid sitting label");
		this.sittingLabel = value;
	}
	public String getSittingLabel()
	{
		return this.sittingLabel;
	}
	public void setStandingLabel(String value)
	{
		if(value == null)
			throw new CodingRuntimeException("Invalid standing label");
		this.standingLabel = value;
	}
	public String getStandingLabel()
	{
		return this.standingLabel;
	}
	public void setLyingLabel(String value)
	{
		if(value == null)
			throw new CodingRuntimeException("Invalid lying label");
		this.lyingLabel = value;
	}
	public String getLyingLabel()
	{
		return this.lyingLabel;
	}
	@Override
	public String getTitle() 
	{
		return "Blood Pressure";		
	}
	
	private Float standingSysValue;
	private Float standingDiaValue;
	private Float sittingSysValue;
	private Float sittingDiaValue;
	private Float lyingSysValue;
	private Float lyingDiaValue;
		
	private String standingLabel = "Standing";
	private String sittingLabel = "Sitting";
	private String lyingLabel = "Lying";
}
