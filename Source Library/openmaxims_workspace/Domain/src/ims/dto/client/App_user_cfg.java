// Template ver. 1.1.0 - Last modified on 04/10/2004 11:00 by Marius Mihalec

package ims.dto.client;

public final class App_user_cfg implements ims.vo.ImsCloneable
{
	private App_user_cfgFilter lastGetFilter = null;
	private final String serviceName = "APP_USER_CFG";
	private boolean listInProgress = false;
	private ims.dto.ResultData lastResultData = null;
	/**
	 * Represents the Data Transfer Object Connection used for this client object.
	 */ 
	public ims.dto.Connection Connection = null;
	/**
	 * Represents the filter used by the current Data Transfer Object.
	 */ 
	public App_user_cfgFilter Filter = new App_user_cfgFilter();			
	/**
	 * Represents the filter used by the current Data Transfer Object to Insert/Update data.
	 */ 
	public App_user_cfgEditFilter EditFilter = new App_user_cfgEditFilter();
	/**
	 * Contains the data records for the current Data Transfer Object
	 */ 
	public App_user_cfgCollection DataCollection = new App_user_cfgCollection();

	/**
	 * Creates a new App_user_cfg Data Transfer Object.
	 */ 
	public App_user_cfg(ims.dto.Connection connection)
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
	public App_user_cfg cloneObject()
	{
		App_user_cfg cloneObject = new App_user_cfg(Connection);
			
		if(Filter != null)
			cloneObject.Filter = Filter.cloneObject();			
					
		if(lastGetFilter != null)
			cloneObject.lastGetFilter = lastGetFilter.cloneObject();
		else
			cloneObject.lastGetFilter = null;
				
		for(int x = 0; x < DataCollection.count(); x++)
		{
			int index = cloneObject.DataCollection.add();
			
			cloneObject.DataCollection.get(index).User_id = DataCollection.get(x).User_id;
			cloneObject.DataCollection.get(index).Screen_id = DataCollection.get(x).Screen_id;
			cloneObject.DataCollection.get(index).Role_id = DataCollection.get(x).Role_id;
			cloneObject.DataCollection.get(index).Read_write = DataCollection.get(x).Read_write;
			cloneObject.DataCollection.get(index).Active = DataCollection.get(x).Active;
			cloneObject.DataCollection.get(index).Start_screen = DataCollection.get(x).Start_screen;
			cloneObject.DataCollection.get(index).Screen_title = DataCollection.get(x).Screen_title;
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
		return "App_user_cfg.imx".toLowerCase();
	}
	/**
	 * Returns the number of records using the specified filter. This method always returns a non null result. The ID field holds the count result (when greater or equal to zero) or the error number (when less than zero).
	 */
	public ims.dto.Result count()
	{
		ims.dto.Result reLoginResult = Connection.reLogin();
		if(reLoginResult != null)
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_user_cfg.Count");
			
		int result = Connection.count(serviceName, encodeNASFilter());
		if(result >= 0)
			return new ims.dto.Result(result, "No error detected. The count result is held in the ID field", "DTO.Client.App_user_cfg.Count");
				
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
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_user_cfg.Get");
			
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
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_user_cfg.Get");
			
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
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_user_cfg.GetForUpdate");
							
		if(lastGetFilter == null)	
			return new ims.dto.Result("Last get method failed or not called", "DTO.Client.App_user_cfg.GetForUpdate");
			
		ims.dto.Result result = Connection.getForUpdate(serviceName, encodeNASFilter(lastGetFilter));
		if(result != null)
			return result;
			
		if(Connection.countResponseItems(Connection.getValueAt(6)) == 0)
			return null;
			
		DataCollection.clear();	
		decodeNASMessageWithRepeatingGroups();
				
		return new ims.dto.Result("The data was changed by another user", "DTO.Client.App_user_cfg.GetForUpdate");
	}		
	/**
	* Inserts a new record. This method always returns a non null result. The ID field holds the Unique ID for the inserted record (when greater than zero) or the error number (when less than zero). If the ID is zero, the record was inserted but the server did not returned the Unique ID.
	*/
	public ims.dto.Result insert()
	{
		this.lastResultData = null;
		ims.dto.Result reLoginResult = Connection.reLogin();
		if(reLoginResult != null)
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_user_cfg.Insert");
					
		if(DataCollection.count() == 0)
			return new ims.dto.Result("No data to insert", "DTO.Client.App_user_cfg.Insert");
			
		if(DataCollection.count() > 1)
			return new ims.dto.Result("Multiple object insert not allowed", "DTO.Client.App_user_cfg.Insert");
			
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
			return new ims.dto.Result("Invalid record ID returned", "DTO.Client.App_user_cfg.Insert");
		}
		
		decodeResultData();
		
		return new ims.dto.Result(recordID, "No error. The ID of the new record is in the ID field", "DTO.Client.App_user_cfg.Insert");
	}
	/**
	 * Executes a specific action. This method always returns a non null result.
	 */
	public ims.dto.Result executeAction(String action)
	{
		if(action.length() == 0)
			return new ims.dto.Result("Invalid action name", "DTO.Client.App_user_cfg.ExecuteAction");
			
		ims.dto.Result reLoginResult = Connection.reLogin();
		if(reLoginResult != null)
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_user_cfg.ExecuteAction");
		
		if(DataCollection.count() == 0)
			return new ims.dto.Result("Data container is empty", "DTO.Client.App_user_cfg.ExecuteAction");
			
		if(DataCollection.count() > 1)
			return new ims.dto.Result("Multiple objects are not allowed", "DTO.Client.App_user_cfg.ExecuteAction");
			
		ims.dto.Result result = Connection.executeAction(serviceName, encodeNASMessage(), action);
		if(result != null)
			return result;
			
		try
		{
			return new ims.dto.Result(new Integer(Connection.getValueAt(2)).intValue(), "No error", "DTO.Client.App_user_cfg.ExecuteAction");	
		}
		catch(NumberFormatException ex)
		{
			return new ims.dto.Result("Invalid server response", "DTO.Client.App_user_cfg.ExecuteAction");
		}
	}
	/**
	 * Transfers a record. If the result returned is not null an error occured.
	 */
	public ims.dto.Result transferData(String action)
	{
		if(action.length() == 0)
			return new ims.dto.Result("Invalid action name", "DTO.Client.App_user_cfg.TransferData");
			
		ims.dto.Result reLoginResult = Connection.reLogin();
		if(reLoginResult != null)
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_user_cfg.TransferData");
		
		if(DataCollection.count() == 0)
			return new ims.dto.Result("No data to transfer", "DTO.Client.App_user_cfg.TransferData");
			
		if(DataCollection.count() > 1)
			return new ims.dto.Result("Multiple objects not allowed", "DTO.Client.App_user_cfg.TransferData");
			
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
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_user_cfg.Update");
				
		if(DataCollection.count() == 0)
			return new ims.dto.Result("No data to update", "DTO.Client.App_user_cfg.Update");	
			
		if(DataCollection.count() > 1)
			return new ims.dto.Result("Multiple object update not allowed", "DTO.Client.App_user_cfg.Update");
					
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
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_user_cfg.List");
							
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
	private String encodeNASFilter(App_user_cfgFilter filter)
	{
		if(filter == null)
			return "";
			
		String filterString = "";
		
		if(Filter.User_id != null && filter.User_id.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "USER_ID" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.User_id;
		}
		
		if(Filter.Screen_id != null && filter.Screen_id.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "SCREEN_ID" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Screen_id;
		}
		
		if(Filter.Role_id != null && filter.Role_id.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "ROLE_ID" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Role_id;
		}
		
		if(Filter.Read_write != null && filter.Read_write.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "READ_WRITE" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Read_write;
		}
		
		if(Filter.Active != null && filter.Active.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "ACTIVE" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Active;
		}
		
		if(Filter.Start_screen != null && filter.Start_screen.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "START_SCREEN" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Start_screen;
		}
		
		if(Filter.Screen_title != null && filter.Screen_title.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "SCREEN_TITLE" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Screen_title;
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
			
		App_user_cfgRecord data = DataCollection.get(0);
		
		if(EditFilter.IncludeUser_id)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "USER_ID" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.User_id);
		}
		
		if(EditFilter.IncludeScreen_id)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "SCREEN_ID" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Screen_id);
		}
		
		if(EditFilter.IncludeRole_id)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "ROLE_ID" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Role_id);
		}
		
		if(EditFilter.IncludeRead_write)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "READ_WRITE" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Read_write);
		}
		
		if(EditFilter.IncludeActive)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "ACTIVE" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Active);
		}
		
		if(EditFilter.IncludeStart_screen)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "START_SCREEN" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Start_screen);
		}
		
		if(EditFilter.IncludeScreen_title)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "SCREEN_TITLE" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Screen_title);
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
			App_user_cfgRecord record = new App_user_cfgRecord();
			String[] valueItems = Connection.splitResponseItem(items[x]);
			
			record.User_id = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "USER_ID"));
			record.Screen_id = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "SCREEN_ID"));
			record.Role_id = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "ROLE_ID"));
			record.Read_write = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "READ_WRITE"));
			record.Active = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "ACTIVE"));
			record.Start_screen = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "START_SCREEN"));
			record.Screen_title = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "SCREEN_TITLE"));
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
			App_user_cfgRecord record = new App_user_cfgRecord();
			String[] valueItems = Connection.splitResponseItem(items[x]);
			
			record.User_id = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "USER_ID"));
			record.Screen_id = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "SCREEN_ID"));
			record.Role_id = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "ROLE_ID"));
			record.Read_write = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "READ_WRITE"));
			record.Active = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "ACTIVE"));
			record.Start_screen = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "START_SCREEN"));
			record.Screen_title = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "SCREEN_TITLE"));
			record.Role_name = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "ROLE_NAME"));
									
			
			
			
			DataCollection.add(record);
		}	
		
		return records;
	}
	
	public final class App_user_cfgCollection
	{
		private java.util.ArrayList recordCollection = new java.util.ArrayList();
		
		@SuppressWarnings("unchecked")
		public int add()
		{
			App_user_cfgRecord newRecord = new App_user_cfgRecord();
			recordCollection.add(newRecord);
			return count() - 1;
		}
		@SuppressWarnings("unchecked")
		public int add(App_user_cfgRecord record)
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
		public App_user_cfgRecord get(int index)
		{
			return (App_user_cfgRecord)recordCollection.get(index);
		}
		public void remove(int index)
		{
			recordCollection.remove(index);
		}
	}
	
	public final class App_user_cfgRecord
	{
		public String User_id = "";
		public String Screen_id = "";
		public String Role_id = "";
		public String Read_write = "";
		public String Active = "";
		public String Start_screen = "";
		public String Screen_title = "";
		public String Role_name = "";
				
		
		public void clear()
		{
			User_id = "";
			Screen_id = "";
			Role_id = "";
			Read_write = "";
			Active = "";
			Start_screen = "";
			Screen_title = "";
			Role_name = "";
			
		}		
	}
		
		
	public final class App_user_cfgFilter
	{			
		public String User_id = "";
		public String Screen_id = "";
		public String Role_id = "";
		public String Read_write = "";
		public String Active = "";
		public String Start_screen = "";
		public String Screen_title = "";
		public String Role_name = "";
		
		public void clear()
		{				
			User_id = "";
			Screen_id = "";
			Role_id = "";
			Read_write = "";
			Active = "";
			Start_screen = "";
			Screen_title = "";
			Role_name = "";
		}	
		public App_user_cfgFilter cloneObject()
		{
			App_user_cfgFilter newFilter = new App_user_cfgFilter();
			
			newFilter.User_id = this.User_id;
			newFilter.Screen_id = this.Screen_id;
			newFilter.Role_id = this.Role_id;
			newFilter.Read_write = this.Read_write;
			newFilter.Active = this.Active;
			newFilter.Start_screen = this.Start_screen;
			newFilter.Screen_title = this.Screen_title;
			newFilter.Role_name = this.Role_name;
			
			return newFilter;
		}
	}
	public final class App_user_cfgEditFilter
	{			
		public boolean IncludeUser_id = true;
		public boolean IncludeScreen_id = true;
		public boolean IncludeRole_id = true;
		public boolean IncludeRead_write = true;
		public boolean IncludeActive = true;
		public boolean IncludeStart_screen = true;
		public boolean IncludeScreen_title = true;
		public boolean IncludeRole_name = true;
		
		public void includeAll()
		{				
			IncludeUser_id = true;
			IncludeScreen_id = true;
			IncludeRole_id = true;
			IncludeRead_write = true;
			IncludeActive = true;
			IncludeStart_screen = true;
			IncludeScreen_title = true;
			IncludeRole_name = true;
		}	
		public void excludeAll()
		{				
			IncludeUser_id = false;
			IncludeScreen_id = false;
			IncludeRole_id = false;
			IncludeRead_write = false;
			IncludeActive = false;
			IncludeStart_screen = false;
			IncludeScreen_title = false;
			IncludeRole_name = false;
		}
		public App_user_cfgEditFilter cloneObject()
		{
			App_user_cfgEditFilter newEditFilter = new App_user_cfgEditFilter();
			
			newEditFilter.IncludeUser_id = this.IncludeUser_id;
			newEditFilter.IncludeScreen_id = this.IncludeScreen_id;
			newEditFilter.IncludeRole_id = this.IncludeRole_id;
			newEditFilter.IncludeRead_write = this.IncludeRead_write;
			newEditFilter.IncludeActive = this.IncludeActive;
			newEditFilter.IncludeStart_screen = this.IncludeStart_screen;
			newEditFilter.IncludeScreen_title = this.IncludeScreen_title;
			newEditFilter.IncludeRole_name = this.IncludeRole_name;
			
			return newEditFilter;
		}
	}
}