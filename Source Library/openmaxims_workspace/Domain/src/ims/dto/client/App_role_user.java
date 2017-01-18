// Template ver. 1.1.0 - Last modified on 04/10/2004 11:00 by Marius Mihalec

package ims.dto.client;

public final class App_role_user implements ims.vo.ImsCloneable
{
	private App_role_userFilter lastGetFilter = null;
	private final String serviceName = "APP_ROLE_USER";
	private boolean listInProgress = false;
	private ims.dto.ResultData lastResultData = null;
	/**
	 * Represents the Data Transfer Object Connection used for this client object.
	 */ 
	public ims.dto.Connection Connection = null;
	/**
	 * Represents the filter used by the current Data Transfer Object.
	 */ 
	public App_role_userFilter Filter = new App_role_userFilter();			
	/**
	 * Represents the filter used by the current Data Transfer Object to Insert/Update data.
	 */ 
	public App_role_userEditFilter EditFilter = new App_role_userEditFilter();
	/**
	 * Contains the data records for the current Data Transfer Object
	 */ 
	public App_role_userCollection DataCollection = new App_role_userCollection();

	/**
	 * Creates a new App_role_user Data Transfer Object.
	 */ 
	public App_role_user(ims.dto.Connection connection)
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
	public App_role_user cloneObject()
	{
		App_role_user cloneObject = new App_role_user(Connection);
			
		if(Filter != null)
			cloneObject.Filter = Filter.cloneObject();			
					
		if(lastGetFilter != null)
			cloneObject.lastGetFilter = lastGetFilter.cloneObject();
		else
			cloneObject.lastGetFilter = null;
				
		for(int x = 0; x < DataCollection.count(); x++)
		{
			int index = cloneObject.DataCollection.add();
			
			cloneObject.DataCollection.get(index).Role_id = DataCollection.get(x).Role_id;
			cloneObject.DataCollection.get(index).User_id = DataCollection.get(x).User_id;
			cloneObject.DataCollection.get(index).Active = DataCollection.get(x).Active;
			cloneObject.DataCollection.get(index).Readonly = DataCollection.get(x).Readonly;
			cloneObject.DataCollection.get(index).Role_name = DataCollection.get(x).Role_name;
							
			
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
		return "App_role_user.imx".toLowerCase();
	}
	/**
	 * Returns the number of records using the specified filter. This method always returns a non null result. The ID field holds the count result (when greater or equal to zero) or the error number (when less than zero).
	 */
	public ims.dto.Result count()
	{
		ims.dto.Result reLoginResult = Connection.reLogin();
		if(reLoginResult != null)
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_role_user.Count");
			
		int result = Connection.count(serviceName, encodeNASFilter());
		if(result >= 0)
			return new ims.dto.Result(result, "No error detected. The count result is held in the ID field", "DTO.Client.App_role_user.Count");
				
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
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_role_user.Get");
			
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
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_role_user.Get");
			
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
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_role_user.GetForUpdate");
							
		if(lastGetFilter == null)	
			return new ims.dto.Result("Last get method failed or not called", "DTO.Client.App_role_user.GetForUpdate");
			
		ims.dto.Result result = Connection.getForUpdate(serviceName, encodeNASFilter(lastGetFilter));
		if(result != null)
			return result;
			
		if(Connection.countResponseItems(Connection.getValueAt(6)) == 0)
			return null;
			
		DataCollection.clear();	
		decodeNASMessageWithRepeatingGroups();
				
		return new ims.dto.Result("The data was changed by another user", "DTO.Client.App_role_user.GetForUpdate");
	}		
	/**
	* Inserts a new record. This method always returns a non null result. The ID field holds the Unique ID for the inserted record (when greater than zero) or the error number (when less than zero). If the ID is zero, the record was inserted but the server did not returned the Unique ID.
	*/
	public ims.dto.Result insert()
	{
		this.lastResultData = null;
		ims.dto.Result reLoginResult = Connection.reLogin();
		if(reLoginResult != null)
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_role_user.Insert");
					
		if(DataCollection.count() == 0)
			return new ims.dto.Result("No data to insert", "DTO.Client.App_role_user.Insert");
			
		if(DataCollection.count() > 1)
			return new ims.dto.Result("Multiple object insert not allowed", "DTO.Client.App_role_user.Insert");
			
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
			return new ims.dto.Result("Invalid record ID returned", "DTO.Client.App_role_user.Insert");
		}
		
		decodeResultData();
		
		return new ims.dto.Result(recordID, "No error. The ID of the new record is in the ID field", "DTO.Client.App_role_user.Insert");
	}
	/**
	 * Executes a specific action. This method always returns a non null result.
	 */
	public ims.dto.Result executeAction(String action)
	{
		if(action.length() == 0)
			return new ims.dto.Result("Invalid action name", "DTO.Client.App_role_user.ExecuteAction");
			
		ims.dto.Result reLoginResult = Connection.reLogin();
		if(reLoginResult != null)
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_role_user.ExecuteAction");
		
		if(DataCollection.count() == 0)
			return new ims.dto.Result("Data container is empty", "DTO.Client.App_role_user.ExecuteAction");
			
		if(DataCollection.count() > 1)
			return new ims.dto.Result("Multiple objects are not allowed", "DTO.Client.App_role_user.ExecuteAction");
			
		ims.dto.Result result = Connection.executeAction(serviceName, encodeNASMessage(), action);
		if(result != null)
			return result;
			
		try
		{
			return new ims.dto.Result(new Integer(Connection.getValueAt(2)).intValue(), "No error", "DTO.Client.App_role_user.ExecuteAction");	
		}
		catch(NumberFormatException ex)
		{
			return new ims.dto.Result("Invalid server response", "DTO.Client.App_role_user.ExecuteAction");
		}
	}
	/**
	 * Transfers a record. If the result returned is not null an error occured.
	 */
	public ims.dto.Result transferData(String action)
	{
		if(action.length() == 0)
			return new ims.dto.Result("Invalid action name", "DTO.Client.App_role_user.TransferData");
			
		ims.dto.Result reLoginResult = Connection.reLogin();
		if(reLoginResult != null)
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_role_user.TransferData");
		
		if(DataCollection.count() == 0)
			return new ims.dto.Result("No data to transfer", "DTO.Client.App_role_user.TransferData");
			
		if(DataCollection.count() > 1)
			return new ims.dto.Result("Multiple objects not allowed", "DTO.Client.App_role_user.TransferData");
			
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
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_role_user.Update");
				
		if(DataCollection.count() == 0)
			return new ims.dto.Result("No data to update", "DTO.Client.App_role_user.Update");	
			
		if(DataCollection.count() > 1)
			return new ims.dto.Result("Multiple object update not allowed", "DTO.Client.App_role_user.Update");
					
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
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_role_user.List");
							
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
	private String encodeNASFilter(App_role_userFilter filter)
	{
		if(filter == null)
			return "";
			
		String filterString = "";
		
		if(Filter.Role_id != null && filter.Role_id.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "ROLE_ID" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Role_id;
		}
		
		if(Filter.User_id != null && filter.User_id.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "USER_ID" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.User_id;
		}
		
		if(Filter.Active != null && filter.Active.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "ACTIVE" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Active;
		}
		
		if(Filter.Readonly != null && filter.Readonly.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "READONLY" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Readonly;
		}
		
		if(Filter.Role_name != null && filter.Role_name.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "ROLE_NAME" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Role_name;
		}
		
		return filterString;	
	}
	
	private String encodeNASMessage()
	{
		String dataString = "";
		if(DataCollection.count() == 0)
			return dataString;
			
		App_role_userRecord data = DataCollection.get(0);
		
		if(EditFilter.IncludeRole_id)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "ROLE_ID" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Role_id);
		}
		
		if(EditFilter.IncludeUser_id)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "USER_ID" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.User_id);
		}
		
		if(EditFilter.IncludeActive)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "ACTIVE" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Active);
		}
		
		if(EditFilter.IncludeReadonly)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "READONLY" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Readonly);
		}
		
		if(EditFilter.IncludeRole_name)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "ROLE_NAME" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Role_name);
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
			App_role_userRecord record = new App_role_userRecord();
			String[] valueItems = Connection.splitResponseItem(items[x]);
			
			record.Role_id = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "ROLE_ID"));
			record.User_id = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "USER_ID"));
			record.Active = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "ACTIVE"));
			record.Readonly = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "READONLY"));
			record.Role_name = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "ROLE_NAME"));
									
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
			App_role_userRecord record = new App_role_userRecord();
			String[] valueItems = Connection.splitResponseItem(items[x]);
			
			record.Role_id = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "ROLE_ID"));
			record.User_id = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "USER_ID"));
			record.Active = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "ACTIVE"));
			record.Readonly = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "READONLY"));
			record.Role_name = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "ROLE_NAME"));
									
			
			
			
			DataCollection.add(record);
		}	
		
		return records;
	}
	
	public final class App_role_userCollection
	{
		private java.util.ArrayList recordCollection = new java.util.ArrayList();
		
		@SuppressWarnings("unchecked")
		public int add()
		{
			App_role_userRecord newRecord = new App_role_userRecord();
			recordCollection.add(newRecord); 
			return count() - 1;
		}
		@SuppressWarnings("unchecked")
		public int add(App_role_userRecord record)
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
		public App_role_userRecord get(int index)
		{
			return (App_role_userRecord)recordCollection.get(index);
		}
		public void remove(int index)
		{
			recordCollection.remove(index);
		}
	}
	
	public final class App_role_userRecord
	{
		public String Role_id = "";
		public String User_id = "";
		public String Active = "";
		public String Readonly = "";
		public String Role_name = "";
				
		
		public void clear()
		{
			Role_id = "";
			User_id = "";
			Active = "";
			Readonly = "";
			Role_name = "";
			
		}		
	}
		
		
	public final class App_role_userFilter
	{			
		public String Role_id = "";
		public String User_id = "";
		public String Active = "";
		public String Readonly = "";
		public String Role_name = "";
		
		public void clear()
		{				
			Role_id = "";
			User_id = "";
			Active = "";
			Readonly = "";
			Role_name = "";
		}	
		public App_role_userFilter cloneObject()
		{
			App_role_userFilter newFilter = new App_role_userFilter();
			
			newFilter.Role_id = this.Role_id;
			newFilter.User_id = this.User_id;
			newFilter.Active = this.Active;
			newFilter.Readonly = this.Readonly;
			newFilter.Role_name = this.Role_name;
			
			return newFilter;
		}
	}
	public final class App_role_userEditFilter
	{			
		public boolean IncludeRole_id = true;
		public boolean IncludeUser_id = true;
		public boolean IncludeActive = true;
		public boolean IncludeReadonly = true;
		public boolean IncludeRole_name = true;
		
		public void includeAll()
		{				
			IncludeRole_id = true;
			IncludeUser_id = true;
			IncludeActive = true;
			IncludeReadonly = true;
			IncludeRole_name = true;
		}	
		public void excludeAll()
		{				
			IncludeRole_id = false;
			IncludeUser_id = false;
			IncludeActive = false;
			IncludeReadonly = false;
			IncludeRole_name = false;
		}
		public App_role_userEditFilter cloneObject()
		{
			App_role_userEditFilter newEditFilter = new App_role_userEditFilter();
			
			newEditFilter.IncludeRole_id = this.IncludeRole_id;
			newEditFilter.IncludeUser_id = this.IncludeUser_id;
			newEditFilter.IncludeActive = this.IncludeActive;
			newEditFilter.IncludeReadonly = this.IncludeReadonly;
			newEditFilter.IncludeRole_name = this.IncludeRole_name;
			
			return newEditFilter;
		}
	}
}