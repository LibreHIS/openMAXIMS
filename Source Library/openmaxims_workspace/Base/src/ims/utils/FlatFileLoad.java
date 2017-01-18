package ims.utils;

import ims.framework.interfaces.ITaxonomyLoadActions;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;

import net.sf.flatpack.DataSet;
import net.sf.flatpack.InitialisationException;
import net.sf.flatpack.brparse.BuffReaderDelimParser;
import net.sf.flatpack.brparse.BuffReaderFixedParser;
import net.sf.flatpack.brparse.BuffReaderParseFactory;

public class FlatFileLoad implements ITaxonomyLoadActions 
{
		
	DataSet ds = null;
	BuffReaderDelimParser parser = null;
	BuffReaderFixedParser fixedParser = null;
	int recordsInserted,recordsUpdated = 0;

	@SuppressWarnings("unchecked")
	public String[] getErrors() 
	{ 
		if ((ds != null) && (ds.getErrorCount()>0))
		{
			String[] strErr = new String[ds.getErrorCount()];
			ds.getErrors().toArray(strErr);
			return strErr;			
		}
		
		return null;
				
	}
	public void load(String file,String fileMap,StringBuffer fDelimiter,StringBuffer fQualifier) throws IOException
	{
		load(file,fileMap,fDelimiter,fQualifier,true);
	}
	public void load(String file,String fileMap,StringBuffer fDelimiter,StringBuffer fQualifier, boolean ignoreFirstRecord) throws IOException
	  {				
		
		try
		{
			if (fDelimiter.length()>0 && fDelimiter.toString() != "")
			{
				if (fileMap != null)
				{
					//cater for tab delimited
					if (fDelimiter.length()>1)
							parser = (BuffReaderDelimParser) BuffReaderParseFactory.getInstance().newDelimitedParser(
									new FileReader(fileMap),
									new FileReader(file),
									'\t',
									fQualifier.length()>0?fQualifier.charAt(0):'"',
									true);						
					else
							parser = (BuffReaderDelimParser) BuffReaderParseFactory.getInstance().newDelimitedParser(
									new FileReader(fileMap),
									new FileReader(file),
									fDelimiter.length()>0?fDelimiter.charAt(0):',',
									fQualifier.length()>0?fQualifier.charAt(0):'"',
									ignoreFirstRecord);
				}
				else
				{
					//cater for tab delimited
					if (fDelimiter.length()>1)
					{
						//no map file, so columns are in first record
						parser = (BuffReaderDelimParser) BuffReaderParseFactory.getInstance().newDelimitedParser(
								new FileReader(file),
								'\t',
								fQualifier.length()>0?fQualifier.charAt(0):'"');						
					}
					else
					{
						//no map file, so columns are in first record
						parser = (BuffReaderDelimParser) BuffReaderParseFactory.getInstance().newDelimitedParser(
								new FileReader(file),
								fDelimiter.length()>0?fDelimiter.charAt(0):',',
								fQualifier.length()>0?fQualifier.charAt(0):'"');
					}
				}
			}
			else
			{
				if (fileMap != null)
					fixedParser = (BuffReaderFixedParser) BuffReaderParseFactory.getInstance().newFixedLengthParser(
									new FileReader(fileMap),
									new FileReader(file));
				else
					fixedParser = (BuffReaderFixedParser) BuffReaderParseFactory.getInstance().newFixedLengthParser(
							null,
							new FileReader(file));				
			}
		}
		catch (NoClassDefFoundError e)
		{
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}

	    //obtain DataSet
				
		if (parser != null)
		{
			parser.setHandlingShortLines(true);
			parser.setIgnoreExtraColumns(true);
			parser.setIgnoreParseWarnings(true);
		}
		else if (fixedParser != null)
		{
			fixedParser.setHandlingShortLines(true);
			fixedParser.setIgnoreExtraColumns(true);
			fixedParser.setIgnoreParseWarnings(true);			
		}
		
		try
		{
			if (parser != null)
				ds = parser.parse();
			else if (fixedParser != null)
				ds = fixedParser.parse();
		}
		catch (InitialisationException e)
		{
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
		
	    if (ds.getErrorCount()>0)
	    {
	    	throw new IOException(ds.getErrors().toString());
	    }	    
	    	    
	  }
	
	public boolean next()
	{
		if (ds != null)
			return ds.next();		
		return false;
	}
	
	public String getString(String item) throws NoSuchElementException
	{
		if (ds != null)
			return ds.getString(item);
		return null;
	}
			
	public boolean verify(String filename, String definitionFile) 
	{
		//TODO
		return true;
	}
	
	public void close() throws IOException
	{
		if (parser != null)
			parser.close();
		else if (fixedParser != null)
			fixedParser.close();
	}

	public void setRecordsInserted(int numRecords) {
		recordsInserted = numRecords;
		
	}

	public void setRecordsUpdated(int numRecords) {
		recordsUpdated = numRecords;
		
	}

	public int getRecordsInserted() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getRecordsUpdated() {
		// TODO Auto-generated method stub
		return 0;
	}
}
