package ims.framework.interfaces;

public interface ISecurityTokenHandler
{
	/**
	 * 
	 * @param serviceName
	 * @param parameters
	 */
	
	
	void setApplicationContextFromRequest(IContextSetter setter,String serviceName,String parameters);
	
	void setApplicationContextFromRequest(IContextSetter setter,String serviceName, ISecurityTokenParameter[] parameters);
	
	/**
	 * returns the ST that is handed back to the remote application 
	 * takes a service name to help decide how to handle it
	 * and a VO 
	 * @param serviceName String to identify the service 
	 * @param vo a value object
	 */
	String setSTParameters(String serviceName,ims.vo.ValueObject vo);
	
	/**
	 * returns the ST that is handed back to the remote application 
	 * takes a service name to help decide how to handle it
	 * and a VO collection
	 * @param serviceName String to identify the service 
	 * @param voCollection a value object

	 */
	String setSTParameters(String serviceName,ims.vo.ValueObjectCollection voCollection);
	
	
	/**
	 * Simple menthod to test if the class can be loaded
	 * @return always should return true
	 */
	boolean testClassLoad();
}
