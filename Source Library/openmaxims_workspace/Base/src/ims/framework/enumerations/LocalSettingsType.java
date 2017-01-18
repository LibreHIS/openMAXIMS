package ims.framework.enumerations;

import java.io.Serializable;

/**
 * @author mmihalec
 */
public class LocalSettingsType implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int id;
	
    public static final LocalSettingsType WORD_EDITOR_PATH = new LocalSettingsType(1);
    
    private LocalSettingsType(int id)
    {
        this.id = id;
    }       
    public boolean equals(Object obj)
    {
    	if(obj != null  && obj instanceof LocalSettingsType)
    		return this.id == ((LocalSettingsType)obj).id;
    	return false;
    }
    public int hashCode()
    {
    	return this.id;
    }           
}
