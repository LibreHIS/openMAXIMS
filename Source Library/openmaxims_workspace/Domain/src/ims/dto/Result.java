package ims.dto;

/**
 * @author mmihalec
 */
public final class Result implements Cloneable
{
	private int id;
	private String message;
	private String source;
	private ResultData data;
	
	public Result(int id, String message, String source, ResultData data)
	{
		this.id = id;
		this.message = message;
		this.source = source;
		this.data = data;
	}
	public Result(int id, String message, String source)
	{
		this.id = id;
		this.message = message;
		this.source = source;
		this.data = null;
	}
	public Result(String message, String source)
	{
		this.id = -37;
		this.message = message;
		this.source = source;
		this.data = null;
	}
	public Result(String message)
	{
		this.id = -37;
		this.message = message;
		this.source = "DTO";
		this.data = null;
	}
	
	public Result cloneObject()
	{
		if(this.data != null)
			return new Result(this.id, this.message, this.source, (ResultData)this.data.clone());
		
		return new Result(this.id, this.message, this.source);
	}
	public Object clone()
	{
		return cloneObject();
	}
	
	/**
	 * @return Returns the full result message.
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer(message);
		sb.append(" (ID: ").append(id);
		sb.append(", Source: ").append(source).append(")");
		return sb.toString();
	}
	/**
	 * @return Returns the result ID.
	 */
	public int getId() 
	{
		return this.id;
	}
	/**
	 * @return Returns the result message.
	 */
	public String getMessage() 
	{
		return this.message;
	}
	/**
	 * @return Returns the result source.
	 */
	public String getSource() 
	{
		return this.source;
	}
	/**
	 * @return Returns the result additional data (if available).
	 */
	public ResultData getData() 
	{
		return this.data;
	}
}