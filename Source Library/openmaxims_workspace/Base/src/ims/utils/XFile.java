package ims.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XFile extends File 
{
    private static final long serialVersionUID = 1L;

    public XFile(String pathname)
	{
		super(pathname);
	}

	public String getContent() 
	{
		if (!exists()) return null;
		FileReader fr;
		try 
		{
			fr = new FileReader(this);
			int fileLen = (int)this.length();
			char charBuf[] = new char[fileLen];
			fr.read(charBuf,0,fileLen);
			String txtContent = new String(charBuf);
			fr.close();
			return txtContent;			
		}
		catch (FileNotFoundException e) 
		{
			return null;
		}
		catch (IOException e) 
		{
			return null;			
		}
	}
	
	public String[] list(String filter)
	{
		if (filter == null || 
			filter.equals("") ||
			filter.equals("*") ||
			filter.equals("*.*"))
		{
			return this.list();
		}
		return list(new XFileFilter(filter));		
	}

	public File[] listFiles(String filter)
	{
		if (filter == null || 
			filter.equals("") ||
			filter.equals("*") ||
			filter.equals("*.*"))
		{
			return this.listFiles();
		}
		return listFiles(new XFileFilter(filter));		
	}

	private class XFileFilter implements FilenameFilter
	{
		private Pattern pat;
		
		public XFileFilter(String filter)
		{
			filter = filter.replaceAll("\\.", "\\\\.");
			if (filter.startsWith("*")) filter = "." + filter;
			this.pat = Pattern.compile(filter.toLowerCase());
		}
		public boolean accept(File dir, String name)
		{
			Matcher m = this.pat.matcher(name.toLowerCase());
			return m.matches();
		}
	}

}
