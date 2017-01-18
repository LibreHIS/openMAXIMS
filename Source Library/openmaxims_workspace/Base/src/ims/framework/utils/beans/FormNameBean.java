/*
 * Created on 11-May-2005
 *
 */
package ims.framework.utils.beans;

/**
 * @author jmacenri
 *
 */
public class FormNameBean 
{
	private int id;	
	
	public FormNameBean()
	{
	}
	
	public FormNameBean(ims.framework.FormName val)
	{
		this.id = val.getID();
	}
	public ims.framework.FormName buildFormName()
	{
		class FormName extends ims.framework.FormName
		{
            private static final long serialVersionUID = 1L;            
    		FormName(int id)
			{
    			super(id);
    		}			
		}
		return new FormName(this.id);
	}
	public static ims.framework.FormNameCollection buildFromBeanCollection(java.util.Collection beans)
	{
		ims.framework.FormNameCollection ret = new ims.framework.FormNameCollection();
		java.util.Iterator iter = beans.iterator();
		while (iter.hasNext())
		{
			FormNameBean bean = (FormNameBean)iter.next();
			ret.add(bean.buildFormName());			
		}
		return ret;		
	}
	
	public int getId() 
	{
		return this.id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}

}
