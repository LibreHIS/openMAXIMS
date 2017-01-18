/*
 * Created on 12-Feb-04
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.dto;

import ims.utils.Logging;

import java.lang.reflect.Constructor;

import org.apache.log4j.Logger;

/**
 * @author gcoghlan
 *
 */
public class DTOFactory
{
	public static final Logger LOG = Logging.getLogger(DTOFactory.class);
	public static DTOFactory factory = new DTOFactory();
	private static final Class[] DTO_CTOR_PARAM_TYPES = { Connection.class };
	
	public static DTOFactory getFactory() {
		return factory;
	}

	/**
	 * 
	 */
	private DTOFactory() {
		if (LOG.isDebugEnabled() )
			LOG.debug("Consruction of DTOFactory");
	}
	
	/**
	 * Create + initialize a DTO instance.
	 * @param clazz
	 * @param connection
	 * @return
	 * @throws RuntimeException if the clazz does not support the required constructor
	 * or initialization methods.
	 */
	public Object getDTOInstance(Class clazz, Connection connection)
	{
		if (LOG.isDebugEnabled())
			LOG.debug("getDTOInstance(clazz="+clazz+", connection="+connection+")");
		try
		{
			// 1. get the Constructor that accepts a connection
			Constructor ctor = getDTOConstructor(clazz);
			// 2. construct the DTO
			Object[] initargs = { connection };
			return ctor.newInstance(initargs);
		}
		catch(RuntimeException e) {
			LOG.error("Construction of DTOInstance failed.", e);
			throw e;
		}
		catch(java.lang.Exception e) {
			LOG.error("Construction of DTOInstance failed.", e);
			throw new ims.domain.exceptions.DomainRuntimeException(e);
		}
	}

	/**
	 * Get the Calendar from the active Connection.
	 * @param session
	 * @return
	 */
	public java.util.Calendar getDateTime(Connection connection) {
		if (LOG.isDebugEnabled())
			LOG.debug(">>getDateTime()");
		return connection.getDateTime();
	}
	
	/**
	 * Get the constructor for a DTO object.
	 * @param clazz
	 * @return
	 * @throws NoSuchMethodException
	 */
	private Constructor getDTOConstructor(Class clazz) throws NoSuchMethodException
	{
		// TODO cache the constructors
		Constructor ctor = clazz.getConstructor(DTO_CTOR_PARAM_TYPES);
		return ctor;
	}
}
