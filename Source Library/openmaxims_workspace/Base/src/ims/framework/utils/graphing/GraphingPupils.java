package ims.framework.utils.graphing;

import ims.framework.utils.Color;
import ims.framework.utils.DateTime;
import ims.framework.utils.StringUtils;

public class GraphingPupils extends GraphingPoint
{	
	private static final long serialVersionUID = 1L;
	
	public GraphingPupils(DateTime recordingDateTime, Integer leftSize, String leftReaction, Integer rightSize, String rightReaction, Object tag, String tooltip)
	{
		initPoint(recordingDateTime, leftSize, leftReaction, rightSize, rightReaction, tag, tooltip);
	}
	public GraphingPupils(DateTime recordingDateTime, Integer leftSize, String leftReaction, Integer rightSize, String rightReaction, Object tag)
	{
		initPoint(recordingDateTime, leftSize, leftReaction, rightSize, rightReaction, tag, null);
	}
	public GraphingPupils(DateTime recordingDateTime, Integer leftSize, String leftReaction, Integer rightSize, String rightReaction)
	{
		initPoint(recordingDateTime, leftSize, leftReaction, rightSize, rightReaction, null, null);
	}
	
	private void initPoint(DateTime recordingDateTime, Integer leftSize, String leftReaction, Integer rightSize, String rightReaction, Object tag, String tooltip)
	{
		super.id = GraphingPointType.getNextID();		
		super.type = GraphingPointType.PUPILS;
		
		super.recordingDateTime = recordingDateTime;
		if(super.recordingDateTime.getTime() == null)
		{
			super.recordingDateTime.getTime().setHour(0);
			super.recordingDateTime.getTime().setMinute(0);
			super.recordingDateTime.getTime().setSecond(0);
		}
		super.tag = tag;
		super.tooltip = tooltip;
		
		this.leftSize = leftSize;
		this.leftReaction = leftReaction;
		this.rightSize = rightSize;
		this.rightReaction = rightReaction;
	}
	public Integer getLeftSize()
	{
		return this.leftSize;
	}
	public Integer getRightSize()
	{
		return this.rightSize;
	}
	public String getLeftReaction()
	{
		return this.leftReaction;
	}
	public String getRightReaction()
	{
		return this.rightReaction;
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
		finalTooltip.append("<b>Left Size:</b> " + ((this.leftSize == null) ? "" : this.leftSize.toString()));
		finalTooltip.append("<br>");
		finalTooltip.append("<b>Left Reaction:</b> " + ((this.leftReaction == null) ? "" : StringUtils.encodeXML(this.leftReaction)));
		finalTooltip.append("<br>");
		finalTooltip.append("<b>Right Size:</b> " + ((this.rightSize == null) ? "" : this.rightSize.toString()));
		finalTooltip.append("<br>");
		finalTooltip.append("<b>Right Reaction:</b> " + ((this.rightReaction == null) ? "" : StringUtils.encodeXML(this.rightReaction)));
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
		printInfo.append(", Left Size: " + ((this.leftSize == null) ? "" : this.leftSize.toString()));
		printInfo.append(", Left Reaction: " + ((this.leftReaction == null) ? "" : StringUtils.encodeXML(this.leftReaction)));
		printInfo.append(", Right Size: " + ((this.rightSize == null) ? "" : this.rightSize.toString()));
		printInfo.append(", Right Reaction: " + ((this.rightReaction == null) ? "" : StringUtils.encodeXML(this.rightReaction)));
		
		return printInfo.toString();
	}
	public static String getLeftImageUrl()
	{
		return "g/blue-triangle-up-1.gif";
	}
	public static String getRightImageUrl()
	{
		return "g/red-circle-1.gif";
	}
	public static Color getLeftLineColor()
	{
		return Color.DarkBlue;
	}
	public static Color getRightLineColor()
	{
		return Color.DarkRed;
	}
	@Override
	public String getTitle() 
	{
		return "Pupils";		
	}
	
	private Integer leftSize;
	private Integer rightSize;
	private String leftReaction;
	private String rightReaction;
}