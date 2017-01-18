package ims.framework.utils.graphing;

import ims.framework.utils.Color;
import ims.framework.utils.DateTime;

public class GraphingPeakFlow extends GraphingPoint
{	
	private static final long serialVersionUID = 1L;
	
	public GraphingPeakFlow(DateTime recordingDateTime, Integer preValue, Integer postValue, Object tag, String tooltip)
	{
		initPoint(recordingDateTime, preValue, postValue, null, tag, tooltip);
	}
	public GraphingPeakFlow(DateTime recordingDateTime, Integer preValue, Integer postValue, Integer timeInterval, Object tag, String tooltip)
	{
		initPoint(recordingDateTime, preValue, postValue, timeInterval, tag, tooltip);
	}
	public GraphingPeakFlow(DateTime recordingDateTime, Integer preValue, Integer postValue, Object tag)
	{
		initPoint(recordingDateTime, preValue, postValue, null, tag, null);
	}
	public GraphingPeakFlow(DateTime recordingDateTime, Integer preValue, Integer postValue, Integer timeInterval, Object tag)
	{
		initPoint(recordingDateTime, preValue, postValue, timeInterval, tag, null);
	}
	public GraphingPeakFlow(DateTime recordingDateTime, Integer preValue, Integer postValue)
	{
		initPoint(recordingDateTime, preValue, postValue, null, null, null);
	}
	public GraphingPeakFlow(DateTime recordingDateTime, Integer preValue, Integer postValue, Integer timeInterval)
	{
		initPoint(recordingDateTime, preValue, postValue, timeInterval, null, null);
	}
	
	private void initPoint(DateTime recordingDateTime, Integer preValue, Integer postValue, Integer timeInterval, Object tag, String tooltip)
	{
		super.id = GraphingPointType.getNextID();
		super.type = GraphingPointType.PEAKFLOW;
		
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
        this.postValue = postValue;
        this.timeInterval = timeInterval;
	}
	public Integer getPreValue()
	{
		return this.preValue;
	}
    public Integer getPostValue()
    {
        return this.postValue;
    }
    public Integer getTimeInterval()
    {
        return this.timeInterval;
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
		finalTooltip.append("<b>Pre Value:</b> " + ((this.preValue == null) ? "" : this.preValue.toString()));
        finalTooltip.append("<br>");
        finalTooltip.append("<b>Post Value:</b> " + ((this.postValue == null) ? "" : this.postValue.toString()));        
        finalTooltip.append("<br>");
        finalTooltip.append("<b>Time Interval:</b> " + ((this.timeInterval == null) ? "" : this.timeInterval.toString()));        
        
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
		printInfo.append(", Pre Peak Flow Value: " + ((this.preValue == null) ? "" : this.preValue.toString()));
        printInfo.append(", Post Peak Flow Value: " + ((this.postValue == null) ? "" : this.postValue.toString()));
        printInfo.append(", Time Interval: " + ((this.timeInterval == null) ? "" : this.timeInterval.toString()));
		
		return printInfo.toString();
	}
	public static String getPreImageUrl()
	{
		return "g/LittleBlackCircle.gif";
	}
    public static String getPostImageUrl()
    {
        return "g/brush-1-red.gif";
    }
	public static Color getPreLineColor()
	{
		return Color.Black;
	}
    public static Color getPostLineColor()
    {
        return Color.Red;
    }
    @Override
	public String getTitle() 
	{
		return "Peak Flow";		
	}
	
	private Integer preValue;
    private Integer postValue;
    private Integer timeInterval;
}
