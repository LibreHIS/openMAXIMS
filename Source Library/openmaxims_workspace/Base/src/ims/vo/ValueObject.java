/*
 * Created on 08-Sep-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.vo;

import ims.framework.IItem;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @author jmacenri
 *
 */
public abstract class ValueObject implements ImsCloneable, IItem, Comparable, Serializable
{
	private static final long serialVersionUID = 1L;	
	
    public abstract Object clone();
	public abstract boolean isValidated();
	public abstract String[] validate();
	public abstract Integer getBoId();
	
	public abstract String getBoClassName();
	protected synchronized static int generateUniqueID()
	{
		return ++voInternalUniqueID;
	}
	
	private static int voInternalUniqueID = 0;
	private static HashMap<String, ValueObject> objMap=null;
	private Boolean isRIE;
	
	
	public Boolean getIsRIE() 
	{
		return isRIE;
	}
	
	public void setIsRIE(Boolean isRIE)
	{
		this.isRIE = isRIE;
	}
	
	public String serializeObject()
	{
		return toXML(this);
	}
	
	private String toXML(ValueObject obj)
	{
		try {
			// Reset the objectMap to null
			objMap=null;
			
			// Parse the object into vo
			SimpleVo vo = new SimpleVo();
			parseObject(obj, vo, true);
		
			// Create xml document from vo
			Document document = DocumentHelper.createDocument();
			Element root = document.addElement( "root" );
			// Output the top level vo first
			if (vo.getVoColl() != null)
			{
				root = populateChildren(root, vo.getVoColl(), vo.getFieldValue());
			}
		
			return(document.asXML());
			
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("IllegalArgumentException occurred parsing xml document " + e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("IllegalAccessException occurred parsing xml document " + e.getMessage(), e);
		}
	}
	
	/**
	 * Recursive method to populate the element with child attributes.
	 * @param element
	 * @param coll
	 * @return Modified element
	 */
	private Element populateChildren(Element element, SimpleVoCollection coll, String name)
	{
		Element nextLevel = element.addElement(name);
		for (int i=0; i<coll.size(); i++)
		{
			SimpleVo vo = coll.get(i);
			int sepPos = vo.getFieldValue().indexOf(":");
			if (sepPos != -1)
			{
				String attName = vo.getFieldValue().substring(0, sepPos);
				String attValue = vo.getFieldValue().substring(sepPos+1);
				nextLevel.addAttribute(attName, attValue);
			}
				
			if (vo.getVoColl() != null)
			{
				nextLevel = populateChildren(nextLevel, vo.getVoColl(), vo.getFieldValue());
			}
		}
		return element;
	}
	
	/**
	 * Recursive method which uses reflection to get all fields and objects
	 * from an object and get their values.  
	 * @param factory domainFactory
	 * @param obj the Object to parse
	 * @param vo the value object to populate
	 * @param topLevel whether this is top level or not (needed for parsing Sets)
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private void parseObject(ValueObject obj, SimpleVo vo, boolean topLevel) throws IllegalAccessException
	{
		if (objMap == null)
			objMap = new HashMap<String, ValueObject>();
		if (objMap.containsKey(obj.toString()))
			return;
		
		objMap.put(obj.toString(), obj);
		vo.setStringValue(toShortName(obj));
	
		SimpleVoCollection lst = new SimpleVoCollection();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i=0; i<fields.length; i++)
		{
			Field f = fields[i];
			f.setAccessible(true);
			Object fieldVal = f.get(obj);
			if (fieldVal == null || f.getName().equals("objMap"))
				continue;
				
			SimpleVo newVo = new SimpleVo();

			if (fieldVal instanceof ValueObjectCollection)
			{
				//parseSet((ValueObjectCollection) fieldVal, newVo);
				parseSet(newVo);
			}
			else if (fieldVal instanceof ValueObject)
			{
				// ParseObject
				parseObject((ValueObject) fieldVal, newVo, false);
			}
			else if (fieldVal instanceof LookupInstanceCollection)
			{
				// TODO
			}
			else if (fieldVal instanceof LookupInstVo)
			{
				LookupInstVo inst = (LookupInstVo)fieldVal;
				newVo.setStringValue(f.getName() + ":" + inst.getText());
			}
			else if (fieldVal instanceof SystemInformation)
			{
				newVo.setStringValue(f.getName() + ":" + fieldVal.toString());
			}
			else
			{
				// Primitive type
			    newVo.setStringValue(f.getName() + ":" + fieldVal);
			}
	
			// Sometimes if we have null sets, it creates an empty item - we don't want these items
			if ((newVo.getVoColl() == null || newVo.getVoColl().size() == 0) && newVo.getFieldValue() == null)
				continue;
			lst.add(newVo);
			if (!topLevel)
				newVo.setParent(vo);
			vo.addChild(newVo);
		}
		vo.setVoColl(lst);
		
	}
	
	private void parseSet(SimpleVo vo) 
	{
	
		vo.setStringValue("Collection");
		SimpleVoCollection coll = new SimpleVoCollection();
		// Now go through the iterator and parse each object found
		/* TODO when ValueObjectCollection class has been modified
		for (int i=0; i<obj.size(); i++)
		{
			SimpleVo newVo = new SimpleVo();
			ValueObject itObj = obj.get(i);
			shortName = toShortName(itObj);

			newVo.setStringValue(shortName);
			parseObject((ValueObject) itObj, newVo, false);
			coll.add(newVo);
			vo.addChild(newVo);
		}*/
		vo.setVoColl(coll);
	}
	
	/**
	 * Only included to satisfy the base class ValueObject implementing Comparable
	 * when generated ones don't override the compareTo method properly.
	 */
	public int compareTo(Object obj)
	{
		return -1;
	}
	
	private String toShortName(Object obj)
	{
		String className =obj.getClass().getName(); 
		return (className.substring(className.lastIndexOf(".")+1));
	}
	public String getMapKey()
	{
		int boId = this.hashCode();
		if(getBoId() != null)
			boId = getBoId();
		return getClass().getName() + "-" + String.valueOf(boId);
	}	
}
