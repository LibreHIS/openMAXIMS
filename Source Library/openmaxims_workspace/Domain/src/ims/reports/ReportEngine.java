package ims.reports;


public interface ReportEngine
{
	public static final String PDF = "PDF";
	public static final String HTML = "HTML";
	public static final String RTF = "RTF";
	public static final String FP3 = "FP3";
	public static final String CSV = "CSV";
	
	public String[] listPrinters(String filter);
//	public void addSeed(String name, Object value, Class type);
//	public byte[] buildReport(Report report, Template template, String format);
//	public boolean printReport(Report report, Template template, String printerName, int nCopies);
}
