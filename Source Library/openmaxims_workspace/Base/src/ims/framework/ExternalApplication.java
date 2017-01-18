package ims.framework;

import ims.framework.exceptions.CodingRuntimeException;

import java.io.Serializable;

public class ExternalApplication implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String filePath;
	private boolean allowMultipleInstances;
	private boolean autoRunEditor;
	private String messageToBeDisplayedIfAlreadyRunning;
	private boolean checkForRunningApplicationPerUserSession;
	
	public String getFilePath()
	{
		return filePath;		
	}
	public boolean isAllowingMultipleInstances()
	{
		return allowMultipleInstances;
	}
	public boolean isAutoRunEditor()
	{
		return autoRunEditor;
	}	
	public String getMessageToBeDisplayedIfAlreadyRunning()
	{
		return messageToBeDisplayedIfAlreadyRunning;
	}
	public boolean isCheckForRunningApplicationPerUserSession()
	{
		return checkForRunningApplicationPerUserSession;
	}
	
	public ExternalApplication(String filePath)
	{
		this(filePath, false, true, "", false);
	}
	public ExternalApplication(String filePath, boolean autoRunEditor)
	{
		this(filePath, autoRunEditor, true, "", false);
	}
	public ExternalApplication(String filePath,  boolean autoRunEditor, boolean allowMultipleInstances, String messageToBeDisplayedIfAlreadyRunning, boolean checkForRunningApplicationPerUserSession)
	{
		this.filePath = filePath;
		this.allowMultipleInstances = allowMultipleInstances;
		this.autoRunEditor = autoRunEditor;
		if(!allowMultipleInstances && (messageToBeDisplayedIfAlreadyRunning == null || messageToBeDisplayedIfAlreadyRunning.trim().length() == 0))
			throw new CodingRuntimeException("Invalid message to be displayed if the application is already running.");
		this.messageToBeDisplayedIfAlreadyRunning = messageToBeDisplayedIfAlreadyRunning;
		this.checkForRunningApplicationPerUserSession = checkForRunningApplicationPerUserSession;
	}	
}
