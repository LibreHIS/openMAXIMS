// Template ver. 1.0.6 - Last modified on 25/05/2004 15:00 by Marius Mihalec

package ims.dto.client;

public final class App_users
{
	private App_usersFilter lastGetFilter = null;
	private final String serviceName = "APP_USERS";
	private boolean listInProgress = false;
	/**
	 * Represents the Data Transfer Object Connection used for this client object.
	 */ 
	public ims.dto.Connection Connection = null;
	/**
	 * Represents the filter used by the current Data Transfer Object.
	 */ 
	public App_usersFilter Filter = new App_usersFilter();			
	/**
	 * Represents the filter used by the current Data Transfer Object to Insert/Update data.
	 */ 
	public App_usersEditFilter EditFilter = new App_usersEditFilter();
	/**
	 * Contains the data records for the current Data Transfer Object
	 */ 
	public App_usersCollection DataCollection = new App_usersCollection();

	/**
	 * Creates a new App_users Data Transfer Object.
	 */ 
	public App_users(ims.dto.Connection connection) throws ims.dto.Exception
	{	
		if(connection == null)
			throw new ims.dto.Exception("Invalid Data Transfer Object Connection");
		this.Connection = connection;
	}
	/**
	 * Creates a new copy of the current Data Transfer Object
	 */
	public App_users cloneObject() throws ims.dto.Exception
	{
		App_users cloneObject = new App_users(Connection);
			
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
			cloneObject.DataCollection.get(index).Uname = DataCollection.get(x).Uname;
			cloneObject.DataCollection.get(index).Upass = DataCollection.get(x).Upass;
			cloneObject.DataCollection.get(index).Dbname = DataCollection.get(x).Dbname;
			cloneObject.DataCollection.get(index).Dbpass = DataCollection.get(x).Dbpass;
			cloneObject.DataCollection.get(index).Spec1 = DataCollection.get(x).Spec1;
			cloneObject.DataCollection.get(index).Spec2 = DataCollection.get(x).Spec2;
			cloneObject.DataCollection.get(index).Rdat = DataCollection.get(x).Rdat;
			cloneObject.DataCollection.get(index).Rtim = DataCollection.get(x).Rtim;
			cloneObject.DataCollection.get(index).Rusr = DataCollection.get(x).Rusr;
			cloneObject.DataCollection.get(index).Udat = DataCollection.get(x).Udat;
			cloneObject.DataCollection.get(index).Utim = DataCollection.get(x).Utim;
			cloneObject.DataCollection.get(index).Uusr = DataCollection.get(x).Uusr;
			cloneObject.DataCollection.get(index).Admin = DataCollection.get(x).Admin;
			cloneObject.DataCollection.get(index).Comment = DataCollection.get(x).Comment;
			cloneObject.DataCollection.get(index).Effr = DataCollection.get(x).Effr;
			cloneObject.DataCollection.get(index).Efft = DataCollection.get(x).Efft;
			cloneObject.DataCollection.get(index).Passwd_exp = DataCollection.get(x).Passwd_exp;
			cloneObject.DataCollection.get(index).Active = DataCollection.get(x).Active;
			cloneObject.DataCollection.get(index).Dbencrypt = DataCollection.get(x).Dbencrypt;
			cloneObject.DataCollection.get(index).Pwdencrypt = DataCollection.get(x).Pwdencrypt;
			cloneObject.DataCollection.get(index).Realname = DataCollection.get(x).Realname;
			cloneObject.DataCollection.get(index).Rusrtxt = DataCollection.get(x).Rusrtxt;
			cloneObject.DataCollection.get(index).Uusrtxt = DataCollection.get(x).Uusrtxt;
			cloneObject.DataCollection.get(index).Spec1txt = DataCollection.get(x).Spec1txt;
			cloneObject.DataCollection.get(index).Hpcd = DataCollection.get(x).Hpcd;
			cloneObject.DataCollection.get(index).Ntyp = DataCollection.get(x).Ntyp;
			cloneObject.DataCollection.get(index).New_dbm = DataCollection.get(x).New_dbm;
			
							
			
		}
		
		return cloneObject;
	}
	/**
	 * Returns the Imx version
	 */	
	public String getImxVersion()
	{
		return "$Revision$";
	}
	/**
	 * Returns the Imx name
	 */	
	public String getImxName()
	{
		return "App_users.imx".toLowerCase();
	}
	/**
	 * Returns the number of records using the specified filter. This method always returns a non null result. The ID field holds the count result (when greater or equal to zero) or the error number (when less than zero).
	 */
	public ims.dto.Result count()
	{
		ims.dto.Result reLoginResult = Connection.reLogin();
		if(reLoginResult != null)
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_users.Count");
			
		int result = Connection.count(serviceName, encodeNASFilter());
		if(result >= 0)
			return new ims.dto.Result(result, "No error detected. The count result is held in the ID field", "DTO.Client.App_users.Count");
				
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
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_users.Get");
			
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
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_users.Get");
			
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
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_users.GetForUpdate");
							
		if(lastGetFilter == null)	
			return new ims.dto.Result("Last get method failed or not called", "DTO.Client.App_users.GetForUpdate");
			
		ims.dto.Result result = Connection.getForUpdate(serviceName, encodeNASFilter(lastGetFilter));
		if(result != null)
			return result;
			
		if(Connection.countResponseItems(Connection.getValueAt(6)) == 0)
			return null;
			
		DataCollection.clear();	
		decodeNASMessageWithRepeatingGroups();
				
		return new ims.dto.Result("The data was changed by another user", "DTO.Client.App_users.GetForUpdate");
	}		
	/**
	* Inserts a new record. This method always returns a non null result. The ID field holds the Unique ID for the inserted record (when greater than zero) or the error number (when less than zero). If the ID is zero, the record was inserted but the server did not returned the Unique ID.
	*/
	public ims.dto.Result insert()
	{
		ims.dto.Result reLoginResult = Connection.reLogin();
		if(reLoginResult != null)
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_users.Insert");
					
		if(DataCollection.count() == 0)
			return new ims.dto.Result("No data to insert", "DTO.Client.App_users.Insert");
			
		if(DataCollection.count() > 1)
			return new ims.dto.Result("Multiple object insert not allowed", "DTO.Client.App_users.Insert");
			
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
			return new ims.dto.Result("Invalid record ID returned", "DTO.Client.App_users.Insert");
		}
		
		return new ims.dto.Result(recordID, "No error. The ID of the new record is in the ID field", "DTO.Client.App_users.Insert");
	}
	/**
	 * Executes a specific action. This method always returns a non null result.
	 */
	public ims.dto.Result executeAction(String action)
	{
		if(action.length() == 0)
			return new ims.dto.Result("Invalid action name", "DTO.Client.App_users.ExecuteAction");
			
		ims.dto.Result reLoginResult = Connection.reLogin();
		if(reLoginResult != null)
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_users.ExecuteAction");
		
		if(DataCollection.count() == 0)
			return new ims.dto.Result("Data container is empty", "DTO.Client.App_users.ExecuteAction");
			
		if(DataCollection.count() > 1)
			return new ims.dto.Result("Multiple objects are not allowed", "DTO.Client.App_users.ExecuteAction");
			
		ims.dto.Result result = Connection.executeAction(serviceName, encodeNASMessage(), action);
		if(result != null)
			return result;
			
		try
		{
			return new ims.dto.Result(new Integer(Connection.getValueAt(2)).intValue(), "No error", "DTO.Client.App_users.ExecuteAction");	
		}
		catch(NumberFormatException ex)
		{
			return new ims.dto.Result("Invalid server response", "DTO.Client.App_users.ExecuteAction");
		}
	}
	/**
	 * Transfers a record. If the result returned is not null an error occured.
	 */
	public ims.dto.Result transferData(String action)
	{
		if(action.length() == 0)
			return new ims.dto.Result("Invalid action name", "DTO.Client.App_users.TransferData");
			
		ims.dto.Result reLoginResult = Connection.reLogin();
		if(reLoginResult != null)
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_users.TransferData");
		
		if(DataCollection.count() == 0)
			return new ims.dto.Result("No data to transfer", "DTO.Client.App_users.TransferData");
			
		if(DataCollection.count() > 1)
			return new ims.dto.Result("Multiple objects not allowed", "DTO.Client.App_users.TransferData");
			
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
		ims.dto.Result reLoginResult = Connection.reLogin();
		if(reLoginResult != null)
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_users.Update");
				
		if(DataCollection.count() == 0)
			return new ims.dto.Result("No data to update", "DTO.Client.App_users.Update");	
			
		if(DataCollection.count() > 1)
			return new ims.dto.Result("Multiple object update not allowed", "DTO.Client.App_users.Update");
			
		return Connection.update(serviceName, encodeNASMessage());
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
			return new ims.dto.Result(reLoginResult.getMessage(), "DTO.Client.App_users.List");
							
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
						
		if(execResult != null)
		{
			if(execResult.getId() != -3) 
			{
				listInProgress = false;
				return execResult;
			}
		}
		else // NAS next list empty
		{
			listInProgress = false;
			return null;
		}				
					
		if(!loadAllRecords || !listInProgress)
		{
			listInProgress = false;
			return Connection.stopList(serviceName);
		}
		
		listInProgress = false;
		return null;
	}	

	private String encodeNASFilter()
	{
		return encodeNASFilter(Filter);
	}
	private String encodeNASFilter(App_usersFilter filter)
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
		
		if(Filter.Uname != null && filter.Uname.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "UNAME" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Uname;
		}
		
		if(Filter.Upass != null && filter.Upass.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "UPASS" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Upass;
		}
		
		if(Filter.Dbname != null && filter.Dbname.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "DBNAME" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Dbname;
		}
		
		if(Filter.Dbpass != null && filter.Dbpass.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "DBPASS" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Dbpass;
		}
		
		if(Filter.Spec1 != null && filter.Spec1.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "SPEC1" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Spec1;
		}
		
		if(Filter.Spec2 != null && filter.Spec2.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "SPEC2" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Spec2;
		}
		
		if(Filter.Rdat != null && filter.Rdat.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "RDAT" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Rdat;
		}
		
		if(Filter.Rtim != null && filter.Rtim.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "RTIM" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Rtim;
		}
		
		if(Filter.Rusr != null && filter.Rusr.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "RUSR" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Rusr;
		}
		
		if(Filter.Udat != null && filter.Udat.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "UDAT" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Udat;
		}
		
		if(Filter.Utim != null && filter.Utim.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "UTIM" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Utim;
		}
		
		if(Filter.Uusr != null && filter.Uusr.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "UUSR" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Uusr;
		}
		
		if(Filter.Admin != null && filter.Admin.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "ADMIN" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Admin;
		}
		
		if(Filter.Comment != null && filter.Comment.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "COMMENT" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Comment;
		}
		
		if(Filter.Effr != null && filter.Effr.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "EFFR" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Effr;
		}
		
		if(Filter.Efft != null && filter.Efft.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "EFFT" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Efft;
		}
		
		if(Filter.Passwd_exp != null && filter.Passwd_exp.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "PASSWD_EXP" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Passwd_exp;
		}
		
		if(Filter.Active != null && filter.Active.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "ACTIVE" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Active;
		}
		
		if(Filter.Dbencrypt != null && filter.Dbencrypt.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "DBENCRYPT" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Dbencrypt;
		}

		if(Filter.Pwdencrypt != null && filter.Pwdencrypt.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "PWDENCRYPT" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Pwdencrypt;
		}

		
		if(Filter.Realname != null && filter.Realname.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "REALNAME" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Realname;
		}
		
		if(Filter.Rusrtxt != null && filter.Rusrtxt.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "RUSRTXT" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Rusrtxt;
		}
		
		if(Filter.Uusrtxt != null && filter.Uusrtxt.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "UUSRTXT" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Uusrtxt;
		}
		
		if(Filter.Spec1txt != null && filter.Spec1txt.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "SPEC1TXT" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Spec1txt;
		}
		
		if(Filter.Hpcd != null && filter.Hpcd.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "HPCD" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Hpcd;
		}
		
		if(Filter.Ntyp != null && filter.Ntyp.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "NTYP" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.Ntyp;
		}
		
		if(Filter.New_dbm != null && filter.New_dbm.length()> 0)
		{
			if(filterString.length() > 0)
				filterString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			filterString += "NEW_DBM" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + filter.New_dbm;
		}

		return filterString;	
	}
	
	private String encodeNASMessage()
	{
		String dataString = "";
		if(DataCollection.count() == 0)
			return dataString;
			
		App_usersRecord data = DataCollection.get(0);
		
		if(EditFilter.IncludeUser_id)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "USER_ID" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.User_id);
		}
		
		if(EditFilter.IncludeUname)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "UNAME" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Uname);
		}
		
		if(EditFilter.IncludeUpass)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "UPASS" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Upass);
		}
		
		if(EditFilter.IncludeDbname)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "DBNAME" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Dbname);
		}
		
		if(EditFilter.IncludeDbpass)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "DBPASS" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Dbpass);
		}
		
		if(EditFilter.IncludeSpec1)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "SPEC1" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Spec1);
		}
		
		if(EditFilter.IncludeSpec2)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "SPEC2" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Spec2);
		}
		
		if(EditFilter.IncludeRdat)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "RDAT" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Rdat);
		}
		
		if(EditFilter.IncludeRtim)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "RTIM" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Rtim);
		}
		
		if(EditFilter.IncludeRusr)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "RUSR" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Rusr);
		}
		
		if(EditFilter.IncludeUdat)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "UDAT" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Udat);
		}
		
		if(EditFilter.IncludeUtim)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "UTIM" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Utim);
		}
		
		if(EditFilter.IncludeUusr)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "UUSR" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Uusr);
		}
		
		if(EditFilter.IncludeAdmin)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "ADMIN" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Admin);
		}
		
		if(EditFilter.IncludeComment)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "COMMENT" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Comment);
		}
		
		if(EditFilter.IncludeEffr)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "EFFR" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Effr);
		}
		
		if(EditFilter.IncludeEfft)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "EFFT" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Efft);
		}
		
		if(EditFilter.IncludePasswd_exp)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "PASSWD_EXP" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Passwd_exp);
		}
		
		if(EditFilter.IncludeActive)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "ACTIVE" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Active);
		}
		
		if(EditFilter.IncludeDbencrypt)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "DBENCRYPT" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Dbencrypt);
		}

		if(EditFilter.IncludePwdencrypt)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "PWDENCRYPT" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Pwdencrypt);
		}

		
		if(EditFilter.IncludeRealname)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "REALNAME" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Realname);
		}
		
		if(EditFilter.IncludeRusrtxt)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "RUSRTXT" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Rusrtxt);
		}
		
		if(EditFilter.IncludeUusrtxt)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "UUSRTXT" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Uusrtxt);
		}
		
		if(EditFilter.IncludeSpec1txt)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "SPEC1TXT" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Spec1txt);
		}
		
		if(EditFilter.IncludeHpcd)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "HPCD" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Hpcd);
		}
		
		if(EditFilter.IncludeNtyp)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "NTYP" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.Ntyp);
		}
		
		if(EditFilter.IncludeNew_dbm)
		{
			if(dataString.length() > 0)
				dataString += ims.dto.NASMessageCodes.PAIRSEPARATOR;
			dataString += "NEW_DBM" + ims.dto.NASMessageCodes.ATTRIBUTEVALUESEPARATOR + Connection.encodeFieldValue(data.New_dbm);
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
			App_usersRecord record = new App_usersRecord();
			String[] valueItems = Connection.splitResponseItem(items[x]);
			
			record.User_id = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "USER_ID"));
			record.Uname = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "UNAME"));
			record.Upass = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "UPASS"));
			record.Dbname = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "DBNAME"));
			record.Dbpass = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "DBPASS"));
			record.Spec1 = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "SPEC1"));
			record.Spec2 = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "SPEC2"));
			record.Rdat = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "RDAT"));
			record.Rtim = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "RTIM"));
			record.Rusr = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "RUSR"));
			record.Udat = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "UDAT"));
			record.Utim = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "UTIM"));
			record.Uusr = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "UUSR"));
			record.Admin = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "ADMIN"));
			record.Comment = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "COMMENT"));
			record.Effr = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "EFFR"));
			record.Efft = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "EFFT"));
			record.Passwd_exp = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "PASSWD_EXP"));
			record.Active = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "ACTIVE"));
			record.Dbencrypt = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "DBENCRYPT"));
			record.Pwdencrypt = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "PWDENCRYPT"));
			record.Realname = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "REALNAME"));
			record.Rusrtxt = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "RUSRTXT"));
			record.Uusrtxt = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "UUSRTXT"));
			record.Spec1txt = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "SPEC1TXT"));
			record.Hpcd = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "HPCD"));
			record.Ntyp = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "NTYP"));
			record.New_dbm = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "NEW_DBM"));
									
			DataCollection.add(record);
		}	
		
		return records;
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
			App_usersRecord record = new App_usersRecord();
			String[] valueItems = Connection.splitResponseItem(items[x]);
			
			record.User_id = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "USER_ID"));
			record.Uname = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "UNAME"));
			record.Upass = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "UPASS"));
			record.Dbname = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "DBNAME"));
			record.Dbpass = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "DBPASS"));
			record.Spec1 = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "SPEC1"));
			record.Spec2 = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "SPEC2"));
			record.Rdat = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "RDAT"));
			record.Rtim = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "RTIM"));
			record.Rusr = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "RUSR"));
			record.Udat = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "UDAT"));
			record.Utim = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "UTIM"));
			record.Uusr = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "UUSR"));
			record.Admin = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "ADMIN"));
			record.Comment = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "COMMENT"));
			record.Effr = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "EFFR"));
			record.Efft = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "EFFT"));
			record.Passwd_exp = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "PASSWD_EXP"));
			record.Active = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "ACTIVE"));
			record.Dbencrypt = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "DBENCRYPT"));
			record.Pwdencrypt = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "PWDENCRYPT"));
			record.Realname = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "REALNAME"));
			record.Rusrtxt = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "RUSRTXT"));
			record.Uusrtxt = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "UUSRTXT"));
			record.Spec1txt = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "SPEC1TXT"));
			record.Hpcd = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "HPCD"));
			record.Ntyp = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "NTYP"));
			record.New_dbm = Connection.decodeFieldValue(Connection.getValueFor(valueItems, "NEW_DBM"));
									
			
			
			
			DataCollection.add(record);
		}	
		
		return records;
	}
	
	public final class App_usersCollection
	{
		private java.util.ArrayList recordCollection = new java.util.ArrayList();
		
		@SuppressWarnings("unchecked")
		public int add()
		{
			App_usersRecord newRecord = new App_usersRecord();
			recordCollection.add(newRecord);
			return count() - 1;
		}
		@SuppressWarnings("unchecked")
		public int add(App_usersRecord record)
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
		public App_usersRecord get(int index)
		{
			return (App_usersRecord)recordCollection.get(index);
		}
		public void remove(int index)
		{
			recordCollection.remove(index);
		}
	}
	
	public final class App_usersRecord
	{
		public String User_id = "";
		public String Uname = "";
		public String Upass = "";
		public String Dbname = "";
		public String Dbpass = "";
		public String Spec1 = "";
		public String Spec2 = "";
		public String Rdat = "";
		public String Rtim = "";
		public String Rusr = "";
		public String Udat = "";
		public String Utim = "";
		public String Uusr = "";
		public String Admin = "";
		public String Comment = "";
		public String Effr = "";
		public String Efft = "";
		public String Passwd_exp = "";
		public String Active = "";
		public String Dbencrypt = "";
		public String Pwdencrypt = "";
		public String Realname = "";
		public String Rusrtxt = "";
		public String Uusrtxt = "";
		public String Spec1txt = "";
		public String Hpcd = "";
		public String Ntyp = "";
		public String New_dbm = "";
				
		
		public void clear()
		{
			User_id = "";
			Uname = "";
			Upass = "";
			Dbname = "";
			Dbpass = "";
			Spec1 = "";
			Spec2 = "";
			Rdat = "";
			Rtim = "";
			Rusr = "";
			Udat = "";
			Utim = "";
			Uusr = "";
			Admin = "";
			Comment = "";
			Effr = "";
			Efft = "";
			Passwd_exp = "";
			Active = "";
			Dbencrypt = "";
			Pwdencrypt = "";
			Realname = "";
			Rusrtxt = "";
			Uusrtxt = "";
			Spec1txt = "";
			Hpcd = "";
			Ntyp = "";
			New_dbm = "";
			
		}		
	}
		
		
	public final class App_usersFilter
	{			
		public String User_id = "";
		public String Uname = "";
		public String Upass = "";
		public String Dbname = "";
		public String Dbpass = "";
		public String Spec1 = "";
		public String Spec2 = "";
		public String Rdat = "";
		public String Rtim = "";
		public String Rusr = "";
		public String Udat = "";
		public String Utim = "";
		public String Uusr = "";
		public String Admin = "";
		public String Comment = "";
		public String Effr = "";
		public String Efft = "";
		public String Passwd_exp = "";
		public String Active = "";
		public String Dbencrypt = "";
		public String Pwdencrypt = "";
		public String Realname = "";
		public String Rusrtxt = "";
		public String Uusrtxt = "";
		public String Spec1txt = "";
		public String Hpcd = "";
		public String Ntyp = "";
		public String New_dbm = "";
		
		public void clear()
		{				
			User_id = "";
			Uname = "";
			Upass = "";
			Dbname = "";
			Dbpass = "";
			Spec1 = "";
			Spec2 = "";
			Rdat = "";
			Rtim = "";
			Rusr = "";
			Udat = "";
			Utim = "";
			Uusr = "";
			Admin = "";
			Comment = "";
			Effr = "";
			Efft = "";
			Passwd_exp = "";
			Active = "";
			Dbencrypt = "";
			Pwdencrypt = "";
			Realname = "";
			Rusrtxt = "";
			Uusrtxt = "";
			Spec1txt = "";
			Hpcd = "";
			Ntyp = "";
			New_dbm = "";
		}	
		public App_usersFilter cloneObject()
		{
			App_usersFilter newFilter = new App_usersFilter();
			
			newFilter.User_id = this.User_id;
			newFilter.Uname = this.Uname;
			newFilter.Upass = this.Upass;
			newFilter.Dbname = this.Dbname;
			newFilter.Dbpass = this.Dbpass;
			newFilter.Spec1 = this.Spec1;
			newFilter.Spec2 = this.Spec2;
			newFilter.Rdat = this.Rdat;
			newFilter.Rtim = this.Rtim;
			newFilter.Rusr = this.Rusr;
			newFilter.Udat = this.Udat;
			newFilter.Utim = this.Utim;
			newFilter.Uusr = this.Uusr;
			newFilter.Admin = this.Admin;
			newFilter.Comment = this.Comment;
			newFilter.Effr = this.Effr;
			newFilter.Efft = this.Efft;
			newFilter.Passwd_exp = this.Passwd_exp;
			newFilter.Active = this.Active;
			newFilter.Dbencrypt = this.Dbencrypt;
			newFilter.Pwdencrypt = this.Pwdencrypt;
			newFilter.Realname = this.Realname;
			newFilter.Rusrtxt = this.Rusrtxt;
			newFilter.Uusrtxt = this.Uusrtxt;
			newFilter.Spec1txt = this.Spec1txt;
			newFilter.Hpcd = this.Hpcd;
			newFilter.Ntyp = this.Ntyp;
			newFilter.New_dbm = this.New_dbm;
			
			return newFilter;
		}
	}
	public final class App_usersEditFilter
	{			
		public boolean IncludeUser_id = true;
		public boolean IncludeUname = true;
		public boolean IncludeUpass = true;
		public boolean IncludeDbname = true;
		public boolean IncludeDbpass = true;
		public boolean IncludeSpec1 = true;
		public boolean IncludeSpec2 = true;
		public boolean IncludeRdat = true;
		public boolean IncludeRtim = true;
		public boolean IncludeRusr = true;
		public boolean IncludeUdat = true;
		public boolean IncludeUtim = true;
		public boolean IncludeUusr = true;
		public boolean IncludeAdmin = true;
		public boolean IncludeComment = true;
		public boolean IncludeEffr = true;
		public boolean IncludeEfft = true;
		public boolean IncludePasswd_exp = true;
		public boolean IncludeActive = true;
		public boolean IncludeDbencrypt = true;
		public boolean IncludePwdencrypt = true;
		public boolean IncludeRealname = true;
		public boolean IncludeRusrtxt = true;
		public boolean IncludeUusrtxt = true;
		public boolean IncludeSpec1txt = true;
		public boolean IncludeHpcd = true;
		public boolean IncludeNtyp = true;
		public boolean IncludeNew_dbm = true;
		
		public void includeAll()
		{				
			IncludeUser_id = true;
			IncludeUname = true;
			IncludeUpass = true;
			IncludeDbname = true;
			IncludeDbpass = true;
			IncludeSpec1 = true;
			IncludeSpec2 = true;
			IncludeRdat = true;
			IncludeRtim = true;
			IncludeRusr = true;
			IncludeUdat = true;
			IncludeUtim = true;
			IncludeUusr = true;
			IncludeAdmin = true;
			IncludeComment = true;
			IncludeEffr = true;
			IncludeEfft = true;
			IncludePasswd_exp = true;
			IncludeActive = true;
			IncludeDbencrypt = true;
			IncludePwdencrypt = true;
			IncludeRealname = true;
			IncludeRusrtxt = true;
			IncludeUusrtxt = true;
			IncludeSpec1txt = true;
			IncludeHpcd = true;
			IncludeNtyp = true;
			IncludeNew_dbm = true;
		}	
		public void excludeAll()
		{				
			IncludeUser_id = false;
			IncludeUname = false;
			IncludeUpass = false;
			IncludeDbname = false;
			IncludeDbpass = false;
			IncludeSpec1 = false;
			IncludeSpec2 = false;
			IncludeRdat = false;
			IncludeRtim = false;
			IncludeRusr = false;
			IncludeUdat = false;
			IncludeUtim = false;
			IncludeUusr = false;
			IncludeAdmin = false;
			IncludeComment = false;
			IncludeEffr = false;
			IncludeEfft = false;
			IncludePasswd_exp = false;
			IncludeActive = false;
			IncludeDbencrypt = false;
			IncludePwdencrypt = false;
			IncludeRealname = false;
			IncludeRusrtxt = false;
			IncludeUusrtxt = false;
			IncludeSpec1txt = false;
			IncludeHpcd = false;
			IncludeNtyp = false;
			IncludeNew_dbm = false;
		}
		public App_usersEditFilter cloneObject()
		{
			App_usersEditFilter newEditFilter = new App_usersEditFilter();
			
			newEditFilter.IncludeUser_id = this.IncludeUser_id;
			newEditFilter.IncludeUname = this.IncludeUname;
			newEditFilter.IncludeUpass = this.IncludeUpass;
			newEditFilter.IncludeDbname = this.IncludeDbname;
			newEditFilter.IncludeDbpass = this.IncludeDbpass;
			newEditFilter.IncludeSpec1 = this.IncludeSpec1;
			newEditFilter.IncludeSpec2 = this.IncludeSpec2;
			newEditFilter.IncludeRdat = this.IncludeRdat;
			newEditFilter.IncludeRtim = this.IncludeRtim;
			newEditFilter.IncludeRusr = this.IncludeRusr;
			newEditFilter.IncludeUdat = this.IncludeUdat;
			newEditFilter.IncludeUtim = this.IncludeUtim;
			newEditFilter.IncludeUusr = this.IncludeUusr;
			newEditFilter.IncludeAdmin = this.IncludeAdmin;
			newEditFilter.IncludeComment = this.IncludeComment;
			newEditFilter.IncludeEffr = this.IncludeEffr;
			newEditFilter.IncludeEfft = this.IncludeEfft;
			newEditFilter.IncludePasswd_exp = this.IncludePasswd_exp;
			newEditFilter.IncludeActive = this.IncludeActive;
			newEditFilter.IncludeDbencrypt = this.IncludeDbencrypt;
			newEditFilter.IncludePwdencrypt = this.IncludePwdencrypt;
			newEditFilter.IncludeRealname = this.IncludeRealname;
			newEditFilter.IncludeRusrtxt = this.IncludeRusrtxt;
			newEditFilter.IncludeUusrtxt = this.IncludeUusrtxt;
			newEditFilter.IncludeSpec1txt = this.IncludeSpec1txt;
			newEditFilter.IncludeHpcd = this.IncludeHpcd;
			newEditFilter.IncludeNtyp = this.IncludeNtyp;
			newEditFilter.IncludeNew_dbm = this.IncludeNew_dbm;
			
			return newEditFilter;
		}
	}
}