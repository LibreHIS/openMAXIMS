package ims.framework.interfaces;

public interface ITaxonomyLoadActions 
{
	public void load(String filename,String definitionFile,StringBuffer delimeter, StringBuffer qualifier) throws java.io.IOException;
	public void load(String filename,String definitionFile,StringBuffer delimeter, StringBuffer qualifier, boolean ignoreFirstRecord) throws java.io.IOException;
	public String[] getErrors();
	public boolean verify(String filename,String definitionFile);
	public int getRecordsInserted();
	public int getRecordsUpdated();
}
