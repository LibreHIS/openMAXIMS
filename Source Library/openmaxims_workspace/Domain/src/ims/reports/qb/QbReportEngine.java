package ims.reports.qb;

import java.util.ArrayList;

import com.ims.query.builder.client.PrinterAvailableCollection;
import com.ims.query.builder.client.QueryBuilderClient;
import com.ims.query.builder.client.exceptions.QueryBuilderClientException;

import ims.configuration.ConfigFlag;
import ims.domain.exceptions.DomainRuntimeException;
import ims.reports.ReportEngine;

public class QbReportEngine implements ReportEngine
{
	//private static final Logger LOG = Logging.getLogger(QbReportEngine.class);
	private QueryBuilderClient qb;
	private String sessionId;

	public QbReportEngine(String sessionId)
	{
		this.sessionId = sessionId;
		init();
	}
	
	private void init()
	{
		if (qb == null)
		{
			qb = new QueryBuilderClient(ConfigFlag.GEN.QUERY_SERVER_URL.getValue(), this.sessionId);							
		}
		else
		{
			qb.setQueryServer(ConfigFlag.GEN.QUERY_SERVER_URL.getValue());
			qb.setSeeds(new ArrayList());
		}
	}

	@SuppressWarnings({"unchecked"})
	public String[] listPrinters(String filter)
	{
		if (qb == null)
			init();

		PrinterAvailableCollection coll = null;
		try
		{
			coll = qb.listPrinters(ConfigFlag.GEN.REPORT_SERVER_URL.getValue());
		}
		catch (QueryBuilderClientException e)
		{
			throw new DomainRuntimeException(e);
		}
		
		ArrayList ret = new ArrayList();
		for (int i = 0; i < coll.size(); i++)
		{
			if (filter == null || filter.equals("") || 
					coll.get(i).getName().toUpperCase().indexOf(filter.toUpperCase()) != -1)
			{
				ret.add(coll.get(i).getName());
			}
		}
		String[] ps = new String[ret.size()];
		ret.toArray(ps);
		return ps;
	}

/*	public void addSeed(String name, Object value, Class type)
	{
		if (qb == null)
			init();
		
		qb.addSeed(new SeedValue(name, value, type));		
	}

	
	public byte[] buildReport(Report report, Template template, String format)
	{
		return buildReport(report.getReportXml(), template.getTemplateXml(), format);
	}

	public boolean printReport(Report report, Template template, String printerName, int nCopies)
	{
		return printReport(report.getReportXml(), template.getTemplateXml(), printerName, nCopies);
	}

	private byte[] buildReport(String report, String template, String format)
	{
		if (qb == null)
			init();
		
		try
		{
			return qb.buildReport(report, template, ConfigFlag.GEN.REPORT_SERVER_URL.getValue(), format, "", 0);
		}
		catch (QueryBuilderClientException e)
		{
			throw new DomainRuntimeException(e);
		}		
	}
	
	public boolean printReport(String report, String template, String printerName, int nCopies)
	{
		if (qb == null)
			init();
		
		try
		{
			return qb.printReport(report, template, ConfigFlag.GEN.REPORT_SERVER_URL.getValue(), printerName, nCopies);
		}
		catch (QueryBuilderClientException e)
		{
			throw new DomainRuntimeException(e);
		}
		
	}
*/	
	
	
	

}
