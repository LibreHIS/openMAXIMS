package ims.framework.utils.graphing;

public class GraphingPointType
{
	public static final GraphingPointType TEMPERATURE 		= new GraphingPointType(-200);
	public static final GraphingPointType PULSE 			= new GraphingPointType(-400);
	public static final GraphingPointType RESPIRATION   	= new GraphingPointType(-500);
	public static final GraphingPointType BLOODPRESSURE		= new GraphingPointType(-100);	
	public static final GraphingPointType GLASGOWCOMASCALE 	= new GraphingPointType(-800);
	public static final GraphingPointType OXYGEN 			= new GraphingPointType(-300);
	public static final GraphingPointType BLOODSUGAR 		= new GraphingPointType(-900);
	public static final GraphingPointType METRICS 			= new GraphingPointType(-902);
	public static final GraphingPointType PEAKFLOW 			= new GraphingPointType(-901);
	public static final GraphingPointType PUPILS 			= new GraphingPointType(-600);
	public static final GraphingPointType CUSTOM_ONE_VALUE	= new GraphingPointType(-1000);
	
	private GraphingPointType(int id)
	{
		this.id = id;
	}
	
	public synchronized static int getNextID()
	{
		return ++nextID;
	}
	
	public float getLineWeight()
	{
		if(this.id == GraphingPointType.TEMPERATURE.id)
			return (float)1.3;
		if(this.id == GraphingPointType.PULSE.id)
			return (float)1.3;
		if(this.id == GraphingPointType.RESPIRATION.id)
			return (float)1.3;
		if(this.id == GraphingPointType.BLOODPRESSURE.id)
			return (float)1.3;
		if(this.id == GraphingPointType.GLASGOWCOMASCALE.id)
			return (float)1.1;
		if(this.id == GraphingPointType.OXYGEN.id)
			return (float)1.3;
		if(this.id == GraphingPointType.BLOODSUGAR.id)
			return (float)1.3;
		if(this.id == GraphingPointType.METRICS.id)
			return (float)1.2;
		if(this.id == GraphingPointType.PEAKFLOW.id)
			return (float)1.3;
		if(this.id == GraphingPointType.PUPILS.id)
			return (float)1.3;		
		
		return 1;	
	}
	
	public String getLineType()
	{
		if(this.id == GraphingPointType.TEMPERATURE.id)
			return GraphicLineStyle.SOLID.getValue();
		if(this.id == GraphingPointType.PULSE.id)
			return GraphicLineStyle.SOLID.getValue();
		if(this.id == GraphingPointType.RESPIRATION.id)
			return GraphicLineStyle.SOLID.getValue();
		if(this.id == GraphingPointType.BLOODPRESSURE.id)
			return GraphicLineStyle.SOLID.getValue();
		if(this.id == GraphingPointType.GLASGOWCOMASCALE.id)
			return GraphicLineStyle.SOLID.getValue();
		if(this.id == GraphingPointType.OXYGEN.id)
			return GraphicLineStyle.SOLID.getValue();
		if(this.id == GraphingPointType.BLOODSUGAR.id)
			return GraphicLineStyle.SOLID.getValue();
		if(this.id == GraphingPointType.METRICS.id)
			return GraphicLineStyle.SOLID.getValue();
		if(this.id == GraphingPointType.PEAKFLOW.id)
			return GraphicLineStyle.SOLID.getValue();
		if(this.id == GraphingPointType.PUPILS.id)
			return GraphicLineStyle.SOLID.getValue();
		
		return GraphicLineStyle.SOLID.getValue();
	}
	
	public String toString()
	{
		if(this.id == GraphingPointType.TEMPERATURE.id)
			return "Temperature";
		if(this.id == GraphingPointType.PULSE.id)
			return "Pulse";
		if(this.id == GraphingPointType.RESPIRATION.id)
			return "Respiration";
		if(this.id == GraphingPointType.BLOODPRESSURE.id)
			return "Blood Pressure";
		if(this.id == GraphingPointType.GLASGOWCOMASCALE.id)
			return "GCS";
		if(this.id == GraphingPointType.OXYGEN.id)
			return "Oxygen Saturation";
		if(this.id == GraphingPointType.BLOODSUGAR.id)
			return "Blood Sugar";
		if(this.id == GraphingPointType.METRICS.id)
			return "Metrics";
		if(this.id == GraphingPointType.PEAKFLOW.id)
			return "Peak Flow";
		if(this.id == GraphingPointType.PUPILS.id)
			return "Pupils";		
		
		return "Unknown";		
	}
	
	private int id;
	private static int nextID = 0;
}
