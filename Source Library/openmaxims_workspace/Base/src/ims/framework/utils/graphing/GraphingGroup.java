package ims.framework.utils.graphing;

import java.io.Serializable;

import ims.framework.exceptions.CodingRuntimeException;
import ims.framework.utils.Color;
import ims.framework.utils.Image;

public class GraphingGroup implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public GraphingGroup(String name, Color lineColor, GraphicLineStyle lineStyle, Image image)
	{
		this(name, lineColor, lineStyle, (float)1.3, image, null);
	}
	public GraphingGroup(String name, Color lineColor, GraphicLineStyle lineStyle, Image image, String legendTooltip)
	{
		this(name, lineColor, lineStyle, (float)1.3, image, legendTooltip);
	}
	public GraphingGroup(String name, Color lineColor, GraphicLineStyle lineStyle, float lineWidth, Image image, String legendTooltip)
	{
		setName(name);
		setLineColor(lineColor);
		setLineStyle(lineStyle);
		this.lineWidth = lineWidth;
		setImage(image);
		this.legendTooltip = legendTooltip;
	}
	
	public void setName(String value)
	{
		if(value == null)
			throw new CodingRuntimeException("Invalid graphing group name");
		
		this.name = value;
	}
	public String getName()
	{
		return this.name;
	}	
	public void setLineColor(Color value)
	{
		if(value == null)
			throw new CodingRuntimeException("Invalid graphing group line color");
		
		this.lineColor = value;
	}
	public Color getLineColor()
	{
		return this.lineColor;
	}	
	public void setLineStyle(GraphicLineStyle value)
	{
		if(value == null)
			throw new CodingRuntimeException("Invalid graphing group line style");
		
		this.lineStyle = value;
	}
	public GraphicLineStyle getLineStyle()
	{
		return this.lineStyle;
	}
	public void setLineWidth(float value)
	{
		this.lineWidth = value;
	}
	public float getLineWidth()
	{
		return this.lineWidth;
	}
	public void setImage(Image value)
	{
		if(value == null)
			throw new CodingRuntimeException("Invalid graphing group image");
		
		this.image = value;
	}
	public Image getImage()
	{
		return this.image;
	}
	public void setLegendTooltip(String value)
	{
		this.legendTooltip = value;
	}
	public String getLegendTooltip()
	{
		return this.legendTooltip == null ? "" : this.legendTooltip;
	}
			
	private String name; 
	private Color lineColor;	
	private GraphicLineStyle lineStyle;
	private float lineWidth; 
	private Image image;
	private String legendTooltip;
}
