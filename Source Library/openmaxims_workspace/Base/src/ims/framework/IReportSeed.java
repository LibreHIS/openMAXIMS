package ims.framework;

/**
 * @author mmihalec
 * 
 * For BO Fields, the unique identifier should be like: "BO-"<BOID>"-"<BOFieldName> - everything in uppercase
 */
public interface IReportSeed 
{
    String getType();
	String getName();
	String getUniqueIdentifier();
	boolean canBeNull();
}
