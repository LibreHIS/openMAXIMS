/*
 * Created on 06-May-2005
 *
 */
package ims.framework.utils.beans;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.commons.betwixt.io.BeanReader;
import org.apache.commons.betwixt.io.BeanWriter;
import org.xml.sax.SAXException;


/**
 * @author bworwood
 *
 * BeanSerializer
 * Class that uses Betwixt to serialize a value
 * object into xml
 **/
public class BeanSerializer 
{
	private static final org.apache.log4j.Logger Logger = ims.utils.Logging.getLogger(BeanSerializer.class);

    /**
     * serialize
     * <p> Serialize the given bean object into xml
     * and return the result in a string
     * @param beanObject - the ValueObjectBean to be serialized
     * @return String representation of the xml </p>
     */
    public static String serialize(Object beanObject)
    {
        try
        {
            if (Logger.isDebugEnabled())
                Logger.debug("Serialize entry - " + beanObject.toString());
            
            // We need to convert from the value object
            // to the value object bean class
            String fullName = beanObject.getClass().getName();            
            String voName = fullName.substring(Math.max(0,fullName.indexOf('.')), fullName.length());
            
            StringWriter outputWriter = new StringWriter();
            outputWriter.write("<?xml version='1.0' ?>\n");
            BeanWriter beanWriter = new BeanWriter(outputWriter);
            beanWriter.enablePrettyPrint();
            
            beanWriter.write(voName, beanObject);
        
            if (Logger.isDebugEnabled())
                Logger.debug("Serialize return (" + outputWriter.toString() + ")");
                
            return outputWriter.toString();
        }
        catch (IntrospectionException e)
        {
            Logger.error("IntrospectionException occurred writing bean object - " + e.getMessage(), e);
        }
        catch (SAXException e)
        {
            Logger.error("SAXException occurred writing bean object - " + e.getMessage(), e);
        }
        catch (IOException e)
        {
         	Logger.error("IOException occurred writing bean object - " + e.getMessage(), e);
        }
        
        if (Logger.isDebugEnabled())
            Logger.debug("Serialize return null");
        return null;
    }
    
    /**
     * deSerialize
     * <p> deSerialize the given xml string 
     * into a beanObject
     * and return the result in a string
     * @param beanObject - the ValueObjectBean to be serialized
     * @return String representation of the xml </p>
     */
    public static Object deSerialize(String xmlInput, Class cls)
    {
        try
        {
            String fullName = cls.getName();            
            String simpleName = fullName.substring(Math.max(0,fullName.indexOf('.')), fullName.length());

            if (Logger.isDebugEnabled())
                Logger.debug("deSerialize into class " + simpleName + "(" + xmlInput + ")");
            // First construct the xml which will be read in
            // For this example, read in from a hard coded string
            StringReader xmlReader = new StringReader(xmlInput);
            
            // Now convert this to a bean using betwixt
            // Create BeanReader
            BeanReader beanReader  = new BeanReader();
            
            // Register beans so that betwixt knows what the xml is to be converted to
            // Since the element mapped to a PersonBean isn't called the same, 
            // need to register the path as well
            beanReader.registerBeanClass(simpleName, cls);
            
            // Now we parse the xml
            Object object = beanReader.parse(xmlReader);
            
            if (Logger.isDebugEnabled())
                Logger.debug("deSerialize return " + object.toString());
            return object;

        }
        catch (IntrospectionException e)
        {
           Logger.error("IntrospectionException occurred parsing xml - " + e.getMessage(), e);
        }
        catch (SAXException e)
        {
            Logger.error("SAXException occurred parsing xml - " + e.getMessage(), e);
        }
        catch (IOException e)
        {
            Logger.error("IOException occurred parsing xml - " + e.getMessage(), e);
        }
        
        if (Logger.isDebugEnabled())
            Logger.debug("deSerialize return null");
        return null;
    }
    
    
}
