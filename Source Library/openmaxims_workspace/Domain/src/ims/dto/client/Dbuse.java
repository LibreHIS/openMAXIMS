// Template ver. 1.1.0 - Last modified on 04/10/2004 11:00 by Marius Mihalec

package ims.dto.client;

public final class Dbuse implements ims.vo.ImsCloneable
{
	private DbuseFilter lastGetFilter = null;
	private final String serviceName = "DBUSE";
	private boolean listInProgress = false;
	private ims.dto.ResultData lastResultData = null;
	/**
	 * Represents the Data Transfer Object Connection used for this client object.
	 */ 
	public ims.dto.Connection Connection = null;
	/**
	 * Represents the filter used by the current Data Transfer Object.
	 */ 
	public DbuseFilter Filter = new DbuseFilter();			
	/**
	 * Represents the filter used by the current Data Transfer Object to Insert/Update data.
	 */ 
	public DbuseEditFilter EditFilter = new DbuseEditFilter();
	/**
	 * Contains the data records for the current Data Transfer Object
	 */ 
	public DbuseCollection DataCollection = new DbuseCollection();

	/**
	 * Creates a new Dbuse Data Transfer Object.
	 */ 
	public Dbuse(ims.dto.Connection connection)
	{	
		this.Connection = connection;
	}
	/**
	 * Returns the last result data after an insert or an update call
	 */
	public ims.dto.ResultData getLastResultData()
	{
		return this.lastResultData;
	}
	/**
	 * Creates a new copy of the current Data Transfer Object
	 */
	public Object clone()
	{
		return cloneObject();
	}
	/**
	 * Creates a new typed copy of the current Data Transfer Object
	 */
	public Dbuse cloneObject()
	{
		Dbuse cloneObject = new Dbuse(Connection);
			
		if(Filter != null)
			cloneObject.Filter = Filter.cloneObject();			
					
		if(lastGetFilter != null)
			cloneObject.lastGetFilter = lastGetFilter.cloneObject();
		else
			cloneObject.lastGetFilter = null;
				
		for(int x = 0; x < DataCollection.count(); x++)
		{
			int index = cloneObject.DataCollection.add();
			
			cloneObject.DataCollection.get(index).Recnum = DataCollection.get(x).Recnum;
			cloneObject.DataCollection.get(index).Type = DataCollection.get(x).Type;
							
			
		}
		
		return cloneObject;
	}
	/**
	 * Returns the Imx version
	 */	
	public String getImxVersion()
	{
		return "0";
	}
	/**
	 * Returns the Imx name
	 */	
	public String getImxName()
	{
		return "Dbuse.imx".toLowerCase();
	}
	/**
	 * Returns the number of records using the specified filter. This method always returns a non null result. The ID field holds the count result (when greater or equal to zero) or the error number (when less than zero).
	 */
	public ims.dto.Result count()
	{
		ims.dto.Result reLoginResult = Connection.reLogin();
		if(reLoginResult != null)
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.Dbuse.Count");
			
		int result = Connection.count(serviceName, encodeNASFilter());
		if(result >= 0)
			return new ims.dto.Result(result, "No error detected. The count result is held in the ID field", "DTO.Client.Dbuse.Count");
				
		return Connection.getLastError();				
	}		
	/**
	 * Returns the list of records using the specified filter. Use maxRecords to limit the number of records returned. If the result returned is not null an error occured.
	 */
	public ims.dto.Result list(int maxRecords)
	{
		if(maxRecords <= 0)
			return list();		
				
		return list(false, maxRecords);
	}	
	/**
	* Returns the list of records using the specified filter. If the result returned is not null an error occured.
	*/
	public ims.dto.Result list()
	{	
		return list(true, 0);
	}				
	/**
	* Returns one record using the specified filter. If the result returned is not null an error occured.
	*/
	public ims.dto.Result get()
	{	
		DataCollection.clear();
			
		ims.dto.Result reLoginResult = Connection.reLogin();
		if(reLoginResult != null)
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.Dbuse.Get");
			
		ims.dto.Result result = Connection.get(serviceName, encodeNASFilter());
		if(result != null)
			return result;
			
		lastGetFilter = Filter.cloneObject();						
		decodeNASMessageWithRepeatingGroups();		
			
		return null;
	}	
	/**
	* Returns one record using the specified filter. If the result returned is not null an error occured.
	*/
	public ims.dto.Result getLast()
	{	
		DataCollection.clear();
			
		ims.dto.Result reLoginResult = Connection.reLogin();
		if(reLoginResult != null)
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.Dbuse.Get");
			
		ims.dto.Result result = Connection.getLast(serviceName, encodeNASFilter());
		if(result != null)
			return result;
			
		lastGetFilter = Filter.cloneObject();						
		decodeNASMessageWithRepeatingGroups();	
			
		return null;
	}					
	/**
	* Performs data validation prior to update. If the result returned is not null an error occured.
	*/
	public ims.dto.Result getForUpdate()
	{						
		ims.dto.Result reLoginResult = Connection.reLogin();
		if(reLoginResult != null)
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.Dbuse.GetForUpdate");
							
		if(lastGetFilter == null)	
			return new ims.dto.Result("Last get method failed or not called", "DTO.Client.Dbuse.GetForUpdate");
			
		ims.dto.Result result = Connection.getForUpdate(serviceName, encodeNASFilter(lastGetFilter));
		if(result != null)
			return result;
			
		if(Connection.countResponseItems(Connection.getValueAt(6)) == 0)
			return null;
			
		DataCollection.clear();	
		decodeNASMessageWithRepeatingGroups();
				
		return new ims.dto.Result("The data was changed by another user", "DTO.Client.Dbuse.GetForUpdate");
	}		
	/**
	* Inserts a new record. This method always returns a non null result. The ID field holds the Unique ID for the inserted record (when greater than zero) or the error number (when less than zero). If the ID is zero, the record was inserted but the server did not returned the Unique ID.
	*/
	public ims.dto.Result insert()
	{
		this.lastResultData = null;
		ims.dto.Result reLoginResult = Connection.reLogin();
		if(reLoginResult != null)
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.Dbuse.Insert");
					
		if(DataCollection.count() == 0)
			return new ims.dto.Result("No data to insert", "DTO.Client.Dbuse.Insert");
			
		if(DataCollection.count() > 1)
			return new ims.dto.Result("Multiple object insert not allowed", "DTO.Client.Dbuse.Insert");
			
		ims.dto.Result result = Connection.insert(serviceName, encodeNASMessage());
		if(result != null)
			return result;
					
		int recordID = 0;
					
		try
		{
			recordID = new Integer(Connection.getValueAt(2)).intValue();
		}
		catch(NumberFormatException ex)
		{
			return new ims.dto.Result("Invalid record ID returned", "DTO.Client.Dbuse.Insert");
		}
		
		decodeResultData();
		
		return new ims.dto.Result(recordID, "No error. The ID of the new record is in the ID field", "DTO.Client.Dbuse.Insert");
	}
	/**
	 * Executes a specific action. This method always returns a non null result.
	 */
	public ims.dto.Result executeAction(String action)
	{
		if(action.length() == 0)
			return new ims.dto.Result("Invalid action name", "DTO.Client.Dbuse.ExecuteAction");
			
		ims.dto.Result reLoginResult = Connection.reLogin();
		if(reLoginResult != null)
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.Dbuse.ExecuteAction");
		
		if(DataCollection.count() == 0)
			return new ims.dto.Result("Data container is empty", "DTO.Client.Dbuse.ExecuteAction");
			
		if(DataCollection.count() > 1)
			return new ims.dto.Result("Multiple objects are not allowed", "DTO.Client.Dbuse.ExecuteAction");
			
		ims.dto.Result result = Connection.executeAction(serviceName, encodeNASMessage(), action);
		if(result != null)
			return result;
			
		try
		{
			return new ims.dto.Result(new Integer(Connection.getValueAt(2)).intValue(), "No error", "DTO.Client.Dbuse.ExecuteAction");	
		}
		catch(NumberFormatException ex)
		{
			return new ims.dto.Result("Invalid server response", "DTO.Client.Dbuse.ExecuteAction");
		}
	}
	/**
	 * Transfers a record. If the result returned is not null an error occured.
	 */
	public ims.dto.Result transferData(String action)
	{
		if(action.length() == 0)
			return new ims.dto.Result("Invalid action name", "DTO.Client.Dbuse.TransferData");
			
		ims.dto.Result reLoginResult = Connection.reLogin();
		if(reLoginResult != null)
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.Dbuse.TransferData");
		
		if(DataCollection.count() == 0)
			return new ims.dto.Result("No data to transfer", "DTO.Client.Dbuse.TransferData");
			
		if(DataCollection.count() > 1)
			return new ims.dto.Result("Multiple objects not allowed", "DTO.Client.Dbuse.TransferData");
			
		ims.dto.Result result = Connection.transferData(serviceName, encodeNASMessage(), action.toUpperCase());
		if(result != null)
			return result;
		
		DataCollection.clear();
		decodeNASMessageWithRepeatingGroups();	
		
		return null;
	}
	/**
	 * Updates a record. If the result returned is not null an error occured.
	 */
	public ims.dto.Result update()
	{			
		this.lastResultData = null;
		ims.dto.Result reLoginResult = Connection.reLogin();
		if(reLoginResult != null)
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.Dbuse.Update");
				
		if(DataCollection.count() == 0)
			return new ims.dto.Result("No data to update", "DTO.Client.Dbuse.Update");	
			
		if(DataCollection.count() > 1)
			return new ims.dto.Result("Multiple object update not allowed", "DTO.Client.Dbuse.Update");
					
		ims.dto.Result result = Connection.update(serviceName, encodeNASMessage());
		if(result != null)
			return result;
			
		decodeResultData();
			
		return null;
	}
				
	private ims.dto.Result nextList()
	{				
		ims.dto.Result result = Connection.nextList(serviceName);
		if(result != null)
			return result;
					
		decodeNASMessage();		
		return null;
	}
		
	private boolean canContinueToList(boolean loadAllRecords, int maxRecords)
	{
		if(!listInProgress)
			return false;				
		if(loadAllRecords)
			return true;			
		return DataCollection.count() < maxRecords;
	}
	
	private ims.dto.Result list(boolean loadAllRecords, int maxRecords)
	{	
		DataCollection.clear();
			
		ims.dto.Result reLoginResult = Connection.reLogin();
		if(reLoginResult != null)
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.Dbuse.List");
							
		listInProgress = true;	
		ims.dto.Result result = Connection.list(serviceName, encodeNASFilter());
		if(result != null)
		{
			listInProgress = false;
			if(result.getId() == -2) // NAS list empty
				return null;
			return result;
		}
					
		if(decodeNASMessage() == 0)
		{
			listInProgress = false;
			return null;
		}
						
		ims.dto.Result execResult = null;
		while(execResult == null && canContinueToList(loadAllRecords, maxRecords))
			execResult = nextList();
						
		listInProgress = false;

		if(execResult != null)
		{ 
			if(execResult.getId() != -3) 
			{
				Connection.stopList(serviceName);
				return execResult;
			}

			return null;
		}
		
		Connection.stopList(serviceName);
		return null; 
	}	

	private String encodeNASFilter()
	{
		return encodeNASFilter(Filter);
	}
	private String encodeNASFilter(DbuseFilter filter)
	{
		if(filter == null)
			return "";
			
		String filterString = "";
		
		if(Filter.Recnum != null && filter.Recnum.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "RECNUM" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Recnum;
		}
		
		if(Filter.Type != null && filter.Type.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "TYPE" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Type;
		}
		
		return filterString;	
	}
	
	private String encodeNASMessage()
	{
		String dataString = "";
		if(DataCollection.count() == 0)
			return dataString;
			
		DbuseRecord data = DataCollection.get(0);
		
		if(EditFilter.IncludeRecnum)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "RECNUM" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Recnum);
		}
		
		if(EditFilter.IncludeType)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "TYPE" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Type);
		}
		
		
			
		return dataString;	
	}
	
	private int decodeNASMessage()
	{
		String[] items = Connection.getResponseItems(Connection.getValueAt(6));
		if(items == null)
			return 0;
		int records = items.length;
		if(records == 0)
			return 0;
					
		for(int x = 0; x < records; x++)
		{
			DbuseRecord record = new DbuseRecord();
			String[] valueItems = Connection.splitResponseItem(items[x]);
			
			record.Recnum = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "RECNUM"));
			record.Type = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "TYPE"));
									
			DataCollection.add(record);
		}	
		
		return records;
	}
	private void decodeResultData()
	{
		this.lastResultData = null;
		String[] items = Connection.getResponseItems(Connection.getValueAt(6));
		if(items == null)
			return;
		int records = items.length;
		if(records == 0)
			return;
		
		String[] valueItems = Connection.splitResponseItem(items[0]);
		
		this.lastResultData = new ims.dto.ResultData();
		String attName = "";
		for(int x = 0; x < valueItems.length; x++)
		{
			attName = Connection.getAttributeName(valueItems[x]);
			this.lastResultData.add(attName, Connection.getValueFor(valueItems, attName));
		}
	}
	private int decodeNASMessageWithRepeatingGroups()
	{
		String[] items = Connection.getResponseItems(Connection.getValueAt(6));
		if(items == null)
			return 0;
		int records = items.length;
		if(records == 0)
			return 0;
			
		for(int x = 0; x < records; x++)
		{
			DbuseRecord record = new DbuseRecord();
			String[] valueItems = Connection.splitResponseItem(items[x]);
			
			record.Recnum = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "RECNUM"));
			record.Type = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "TYPE"));
									
			
			
			
			DataCollection.add(record);
		}	
		
		return records;
	}
	
	public final class DbuseCollection
	{
		private java.util.ArrayList recordCollection = new java.util.ArrayList();
		
		@SuppressWarnings("unchecked")
		public int add()
		{
			DbuseRecord newRecord = new DbuseRecord();
			recordCollection.add(newRecord);
			return count() - 1;
		}
		@SuppressWarnings("unchecked")
		public int add(DbuseRecord record)
		{
			recordCollection.add(record);
			return count() - 1;
		}
		public void clear()
		{
			recordCollection.clear();
		}
		public int count()
		{
			return recordCollection.size();
		}
		public DbuseRecord get(int index)
		{
			return (DbuseRecord)recordCollection.get(index);
		}
		public void remove(int index)
		{
			recordCollection.remove(index);
		}
	}
	
	public final class DbuseRecord
	{
		public String Recnum = "";
		public String Type = "";
				
		
		public void clear()
		{
			Recnum = "";
			Type = "";
			
		}		
	}
		
		
	public final class DbuseFilter
	{			
		public String Recnum = "";
		public String Type = "";
		
		public void clear()
		{				
			Recnum = "";
			Type = "";
		}	
		public DbuseFilter cloneObject()
		{
			DbuseFilter newFilter = new DbuseFilter();
			
			newFilter.Recnum = this.Recnum;
			newFilter.Type = this.Type;
			
			return newFilter;
		}
	}
	public final class DbuseEditFilter
	{			
		public boolean IncludeRecnum = true;
		public boolean IncludeType = true;
		
		public void includeAll()
		{				
			IncludeRecnum = true;
			IncludeType = true;
		}	
		public void excludeAll()
		{				
			IncludeRecnum = false;
			IncludeType = false;
		}
		public DbuseEditFilter cloneObject()
		{
			DbuseEditFilter newEditFilter = new DbuseEditFilter();
			
			newEditFilter.IncludeRecnum = this.IncludeRecnum;
			newEditFilter.IncludeType = this.IncludeType;
			
			return newEditFilter;
		}
	}
}