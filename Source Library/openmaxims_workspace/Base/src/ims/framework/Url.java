package ims.framework;

import java.util.List;

public class Url
{
	private String url;	
	private List<UrlParam> params = null;
	private int windowID;
	private List<WindowParam> windowParams = null;
	private String target;
	private Boolean handleWindowEvents;
		
	public Url(String url)
	{
		this(url, null, -1, null, null, false);
	}
	public Url(String url, String target)
	{
		this(url, null, -1, null, target, false);
	}
	public Url(String url, List<UrlParam> params)
	{
		this(url, params, -1, null, null, false);
	}
	public Url(String url, List<WindowParam> windowParams, boolean handleWindowEvents)
	{
		this(url, null, -1, windowParams, null, handleWindowEvents);
	}
	public Url(String url, int windowsID, List<WindowParam> windowParams, boolean handleWindowEvents)
	{
		this(url, null, windowsID, windowParams, null, handleWindowEvents);
	}
	public Url(String url, List<UrlParam> params, int windowID, List<WindowParam> windowParams, String target, boolean handleWindowEvents)
	{
		this.url = url;
		this.windowID = windowID;
		this.params = params;
		this.windowParams = windowParams;
		this.target = target;
		this.handleWindowEvents = handleWindowEvents;
	}
	
	public String getUrl()
	{
		return url;
	}
	public List<UrlParam> getParams()
	{
		return params;
	}
	public int getWindowsID()
	{
		return windowID;
	}
	public List<WindowParam> getWindowParams()
	{
		return windowParams;
	}
	public String getTarget()
	{
		return target;
	}
	public Boolean handleWindowEvents()
	{
		return handleWindowEvents;
	}
}
