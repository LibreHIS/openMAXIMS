/*
 * Created on 04-Mar-04
 *
 */
package ims.domain.impl;

import ims.domain.servicemanager.ProxyInvocationHandler;

import ims.domain.DomainFactory;
import ims.domain.DomainSession;
import ims.domain.ListRecordInformation;
import ims.domain.SessionData;
import ims.domain.Transaction;
import ims.domain.exceptions.StaleObjectException;
import ims.framework.SessionConstants;
import ims.framework.interfaces.IContextEvalProvider;
import ims.vo.ValueObjectCollection;
import ims.vo.ValueObject;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A InvocationHandler used to wrap domain implementation method
 * with a database Transaction.
 * @author gcoghlan
 *
 */

public class DomainImplProxyHandler implements ProxyInvocationHandler, Serializable
{
	private static final long serialVersionUID = 1L;
	
	private static final org.apache.log4j.Logger LOG = ims.utils.Logging.getLogger(DomainImplProxyHandler.class);
	private final DomainImpl domainImpl;
	protected static final boolean ProxyStartTrans = false;

	public static Object newInstance(DomainImpl domainImpl) {
		if (LOG.isDebugEnabled())
			LOG.debug("Creating new proxy for "+domainImpl);
		if (domainImpl.getSessLogger() != null)
		{
			domainImpl.getSessLogger().warn("Creating new proxy for " + domainImpl);
		}
		return java.lang.reflect.Proxy.newProxyInstance(
			domainImpl.getClass().getClassLoader(),
			getAllInterfaces(domainImpl.getClass()),
			new DomainImplProxyHandler(domainImpl));
	}
	private static Class[] getAllInterfaces(Class clazz)
	{ 
        ArrayList<Class> list = new ArrayList<Class>();         
        while(clazz != null)
        {
        	Class[] i = clazz.getInterfaces();
        	for(int x = 0; x < i.length; x++)
            {
        		if(!list.contains(i[x]))
        			list.add(i[x]);
            }
            clazz = clazz.getSuperclass(); 
        }        
        Class[] all = new Class[list.size()];
        for(int x = 0; x < list.size(); x++)
        {
        	all[x] = list.get(x);
        }        
        return all;
    } 
	/**
	 * 
	 */
	public DomainImplProxyHandler(DomainImpl domainImpl) {
		super();
		this.domainImpl = domainImpl;
		if (LOG.isDebugEnabled())
			LOG.debug("DomainImplProxyHandler for domainImpl:"+domainImpl);
		if (domainImpl.getSessLogger() != null)
		{
			domainImpl.getSessLogger().warn("DomainImplProxyHandler for domainImpl:"+domainImpl);
		}

	}
	
	public DomainImpl getDomainImpl() {
		return this.domainImpl;
	}

	/**
	 * Wraps each method invocation with a database Transaction.
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		boolean isContextEval = (IContextEvalProvider.class.isAssignableFrom(proxy.getClass()));		
		String methodName = method.getName();
		
		validateMethodParametersBeforeInvocation(method, args);		
		
		if (LOG.isInfoEnabled() && !isContextEval)
			LOG.info("Invocation of method "+methodName);
		if (domainImpl.getSessLogger() != null && !isContextEval)
		{
			domainImpl.getSessLogger().warn("Invocation of method "+methodName);
		}
		boolean rollbackOnly = false;
		DomainFactory domainFactory = this.domainImpl.getDomainFactory();
		Object result;
		
		Transaction transaction = null;
		boolean methodTransactional = domainImpl.isMethodTransactional(methodName);
		if (ProxyStartTrans || methodTransactional)
		{
			if (methodTransactional)
				domainFactory.getJdbcConnection().setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
			
			if (LOG.isDebugEnabled())
				LOG.debug("Begin transaction");
			if (domainImpl.getSessLogger() != null)
			{
				domainImpl.getSessLogger().warn("Begin transaction");
			}
			transaction = domainFactory.beginTransaction();
		}
		try 
		{
			if (LOG.isDebugEnabled())
				LOG.debug("pre method " + methodName);
			
			if (domainImpl.getSessLogger() != null)
			{
				domainImpl.getSessLogger().warn("pre method " + methodName);
			}
			result = method.invoke(this.domainImpl, args);
			if (LOG.isDebugEnabled())
				LOG.debug("post method "+methodName);
			if (domainImpl.getSessLogger() != null)
			{
				domainImpl.getSessLogger().warn("post method " + methodName);
			}
			
//			domainFactory.flush();
//			if (logger.isInfoEnabled())
//				logger.info("Session flushed before commit.");
			domainImpl.endTransaction();
		}
		catch (InvocationTargetException e) 
		{			
			rollbackOnly = true;
			LOG.fatal(methodName, e);
			if (domainImpl.getSessLogger() != null)
			{
				domainImpl.getSessLogger().fatal(methodName, e);
			}
			throw e.getTargetException();
		}
		catch (StaleObjectException e) 
		{
			rollbackOnly = true;
			LOG.error(methodName, e);
			if (domainImpl.getSessLogger() != null)
			{
				domainImpl.getSessLogger().error(methodName, e);
			}
			throw e;			
		}
		catch (Exception e) {
			rollbackOnly = true;
			LOG.fatal(methodName, e);
			if (domainImpl.getSessLogger() != null)
			{
				domainImpl.getSessLogger().fatal(methodName, e);
			}
			throw new RuntimeException("Unexpected invocation exception during invocation of "+methodName +" :"+
				e.getMessage(), e);
		}
		finally 
		{
			if (LOG.isDebugEnabled())
				LOG.debug("after method " + methodName);
			if (domainImpl.getSessLogger() != null)
			{
				domainImpl.getSessLogger().warn("after method " + methodName);
			}
			if (rollbackOnly) {
				// rollback transaction
				transaction = domainFactory.getTransaction();

				if (transaction != null)
				{
					try {
						transaction.rollback();
					}
					finally {
						transaction.abandon();				
					}										
				}
			}
			domainFactory.setTransaction(null);
			if (methodTransactional)
				domainFactory.getJdbcConnection().setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			domainFactory.discardSession();
		}
		if (LOG.isInfoEnabled() && !isContextEval)
			LOG.info("returning from method: "+methodName);
		if (domainImpl.getSessLogger() != null)
		{
			domainImpl.getSessLogger().warn("returning from method " + methodName);
		}
		
		DomainSession sess = domainImpl.getSession();
		SessionData sessData = (SessionData)sess.getAttribute(SessionConstants.SESSION_DATA);
		
		if (result instanceof ValueObjectCollection)
		{
			ValueObjectCollection coll = (ValueObjectCollection) result;
			if (coll.getBoClassName() != null && !coll.getBoClassName().equals(""))
			{
				// Set record information within the session
				HashMap infoMap = sessData.listRecordInfo.get();
				if (infoMap != null)
				{
					infoMap.put(coll.getBoClassName(), new ListRecordInformation(coll.getRieCount(), coll.getActiveCount(), coll.getBoClassName()));
					sessData.listRecordInfo.set(infoMap);
				}
			}
		}
		else if (result instanceof ValueObject)
		{
			Boolean includeRIE = sessData.listRIERecordsOnly.get();
			// Only if not in rie mode
			if (includeRIE == null || !includeRIE.booleanValue())
			{				
				String boClassName = sessData.rieBoClassName.get();
				if (boClassName != null && boClassName.indexOf(",") >= 0)
				{
						// 	Walk through vo for all vo collections 
						Field[] fields = result.getClass().getDeclaredFields();
						for (int i=0; i<fields.length; i++)
						{
							Field f = fields[i];
							f.setAccessible(true);
							Object fieldVal = f.get(result);
							if (fieldVal instanceof ims.vo.ValueObjectCollection)
							{
								ValueObjectCollection coll = (ValueObjectCollection) fieldVal;
								if (coll != null && coll.getRieCount() > 0)
								{
									HashMap infoMap = sessData.listRecordInfo.get();
									infoMap.put(coll.getBoClassName(), new ListRecordInformation(coll.getRieCount(), coll.getActiveCount(), coll.getBoClassName()));
									sessData.listRecordInfo.set(infoMap);
									break;
								}
							}
					}
				}
			}
		}
		return result;
	}
	private void validateMethodParametersBeforeInvocation(Method method, Object[] args) throws Throwable 
	{
		if(args != null && args.length > 0)
		{
			String validateParametersMethodName = "validate" + method.getName();
			Method validateParametersMethod = null;
			try
			{
				validateParametersMethod = domainImpl.getClass().getMethod(validateParametersMethodName, method.getParameterTypes());
			}
			catch(Exception ex)
			{
				// there is no parameter validation method available 
				// or the impl does not extend BaseImpl where the validate method is generated.
				return;
			}
			
			if(validateParametersMethod != null)
			{
				try
				{
					validateParametersMethod.invoke(this.domainImpl, args);
				}
				catch(InvocationTargetException e)
				{
					throw e.getTargetException();
				}
			}
		}
	}

	public static boolean isProxyStartTrans() 
	{
		return ProxyStartTrans;
	}
	
	/*
	 * returns the DomainImpl's DomainSessions object
	 */
	public DomainSession getSession()
	{
		return domainImpl.getSession();
	}
}
