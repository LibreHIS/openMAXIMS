package ims.reports;

import java.lang.reflect.Constructor;

import ims.domain.exceptions.DomainRuntimeException;

public class ReportEngineFactory
{
	private static final String DEFAULT_ENGINE = "ims.reports.qb.QbReportEngine";
	
	public static ReportEngine createReportEngine()
	{
		return createReportEngine("");
	}
	public static ReportEngine createReportEngine(String sessionId)
	{
		try
		{
			Class c = Class.forName(DEFAULT_ENGINE);
			Constructor cons = c.getConstructor(new Class[]{String.class});
			return (ReportEngine)cons.newInstance(new Object[]{sessionId});
		}
		catch (Exception e)
		{
			throw new DomainRuntimeException(e);
		}		
	}


}
