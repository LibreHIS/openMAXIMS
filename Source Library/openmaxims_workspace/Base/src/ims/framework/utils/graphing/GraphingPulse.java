package ims.framework.utils.graphing;

import ims.framework.utils.DateTime;
import ims.framework.utils.Color;

public class GraphingPulse extends GraphingPoint
{	
	private static final long serialVersionUID = 1L;
	
	public GraphingPulse(DateTime recordingDateTime, Integer radialRate, Integer apexRate, Object tag, String tooltip)
	{
		initPoint(recordingDateTime, radialRate, apexRate, null, tag, tooltip);
	}
	public GraphingPulse(DateTime recordingDateTime, Integer radialRate, Integer apexRate, Boolean irregular, Object tag, String tooltip)
	{
		initPoint(recordingDateTime, radialRate, apexRate, irregular, tag, tooltip);
	}
	public GraphingPulse(DateTime recordingDateTime, Integer radialRate, Integer apexRate, Object tag)
	{
		initPoint(recordingDateTime, radialRate, apexRate, null, tag, null);
	}
	public GraphingPulse(DateTime recordingDateTime, Integer radialRate, Integer apexRate, Boolean irregular, Object tag)
	{
		initPoint(recordingDateTime, radialRate, apexRate, irregular, tag, null);
	}
	public GraphingPulse(DateTime recordingDateTime, Integer radialRate, Integer apexRate)
	{
		initPoint(recordingDateTime, radialRate, apexRate, null, null, null);
	}
	public GraphingPulse(DateTime recordingDateTime, Integer radialRate, Integer apexRate, Boolean irregular)
	{
		initPoint(recordingDateTime, radialRate, apexRate, irregular, null, null);
	}
	
	private void initPoint(DateTime recordingDateTime, Integer radialRate, Integer apexRate, Boolean irregular, Object tag, String tooltip)
	{
		super.id = GraphingPointType.getNextID();
		super.type = GraphingPointType.PULSE;		
		
		super.recordingDateTime = recordingDateTime;
		if(super.recordingDateTime.getTime() == null)
		{
			super.recordingDateTime.getTime().setHour(0);
			super.recordingDateTime.getTime().setMinute(0);
			super.recordingDateTime.getTime().setSecond(0);
		}
		super.tag = tag;
		super.tooltip = tooltip;
		
		this.radialRate = radialRate;
        this.apexRate = apexRate;
        this.irregular = irregular; 
	}
	public Integer getRadialRate()
	{
		return this.radialRate;
	}
    public Integer getApexRate()
    {
        return this.apexRate;
    }
    public Boolean getIrregular()
    {
    	return this.irregular;
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
		finalTooltip.append("<b>Radial Rate:</b> " + ((this.radialRate == null) ? "" : this.radialRate.toString()));
        finalTooltip.append("<br>");
        finalTooltip.append("<b>Apex Rate:</b> " + ((this.apexRate == null) ? "" : this.apexRate.toString()));
        
        if(this.irregular != null && this.irregular.booleanValue())
        {
        	finalTooltip.append("<br>");
        	finalTooltip.append("<b>This is an irregular pulse</b>");
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
		printInfo.append(", Radial Rate: " + ((this.radialRate == null) ? "" : this.radialRate.toString()));
        printInfo.append(", Apex Rate: " + ((this.apexRate == null) ? "" : this.apexRate.toString()));
        if(this.irregular != null && this.irregular.booleanValue())
        	printInfo.append(", Irregular Pulse");        	
		
		return printInfo.toString();
	}
	public static String getRadialImageUrl()
	{
		return "g/LittleBlackCircle.gif";
	}
    public static String getApexImageUrl()
    {
        return "g/brush-1-red.gif";
    }
    public static Color getRadialLineColor()
    {
        return Color.Black;
    }
	public static Color getApexLineColor()
	{
		return Color.Red;
	}
	@Override
	public String getTitle() 
	{
		return "Pulse";		
	}
	
	private Integer radialRate;	
    private Integer apexRate;
    private Boolean irregular;
}
