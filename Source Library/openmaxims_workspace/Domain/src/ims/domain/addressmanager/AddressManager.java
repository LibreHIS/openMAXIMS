package ims.domain.addressmanager;

import java.io.IOException;
import ims.framework.exceptions.FrameworkInternalException;

public class AddressManager
{
	private static capscan.client.McConnection connection = null;    // Connection to Matchcode server
	private static capscan.client.NcConnection ncConnection = null;    // Connection to Nearcode server

    public AddressManager() throws Exception
	{
    	
	}

    public static synchronized int connect(String host,String pools,int mode) throws Exception
    {
        if (connection != null) {
            disconnect();
        }

        connection = new capscan.client.McConnection(host, pools, "conntest", mode);

        if (connection.errno() == capscan.client.McConnection.BADPOOL) {
            disconnect();
            throw new Exception("Pool(s) not found:" + pools.toString());
        }

        return connection.errno();
        
    }

    
    public static synchronized int ncConnect(String host,String pools,int mode) throws Exception
    {
        if (ncConnection != null) {
            ncDisconnect();
        }

        ncConnection = new capscan.client.NcConnection(host, pools, "conntest", mode);

        if (ncConnection.errno() == capscan.client.McConnection.BADPOOL) {
        	ncDisconnect();
            throw new Exception("Pool(s) not found:" + pools.toString());
        }

        return ncConnection.errno();
        
    }
    
    public static void disconnect() throws FrameworkInternalException
    {
        try {
            if (connection != null) {
                connection.disconnect();
            }
        } catch (IOException e) 
        {
        	throw new FrameworkInternalException(e.getMessage());
        }

        connection = null;
    }

    public static void ncDisconnect() throws FrameworkInternalException
    {
        try {
            if (ncConnection != null) {
                ncConnection.disconnect();
            }
        } catch (IOException e) 
        {
        	throw new FrameworkInternalException(e.getMessage());
        }

        ncConnection = null;
    }
    
	public static capscan.client.McConnection getConnection() throws Exception
	{
		return connection;
	}
	public static capscan.client.NcConnection getncConnection() throws Exception
	{
		return ncConnection;
	}	
}
	
	