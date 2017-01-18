/*
 * Created on May 4, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.domain.lookups;

import ims.domain.exceptions.DomainException;
import ims.framework.utils.Color;
import ims.framework.utils.Image;

import java.util.Set;

/**
 * @author gcoghlan
 *
 */
public class LookupInstanceRef extends LookupInstance {
    private static final long serialVersionUID = 1L;
    private LookupInstance instance;
	
	/**
	 * Default no-arg constructor required by Hibernate.
	 *
	 */
	public LookupInstanceRef() {
		
	}
	
	/**
	 * Constructor to set instance. Avoids need for setter
	 * @param instance
	 */
	public LookupInstanceRef(LookupInstance instance) {
		this.instance = instance;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) 
	{
		return this.instance.equals(obj);
	}

	/* (non-Javadoc)
	 * @see ims.domain.lookups.LookupInstance#getText()
	 */
	public String getText() 
	{
		return this.instance.getText();
	}

	/* (non-Javadoc)
	 * @see ims.domain.lookups.LookupInstance#getType()
	 */
	public Lookup getType() 
	{
		return this.instance.getType();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return this.instance.hashCode();
	}

	/* (non-Javadoc)
	 * @see ims.domain.lookups.LookupInstance#isActive()
	 */
	public boolean isActive() 
	{
		return this.instance.isActive();
	}
	
	/**
	 * @see ims.domain.lookups.LookupInstance#addMapping(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public LookupMapping addMapping(String extSystem, String extCode) throws DomainException 
	{
		return this.instance.addMapping(extSystem, extCode);
	}

	/**
	 * @see ims.domain.lookups.LookupInstance#getImageFile()
	 */
	public Image getImage() 
	{
		return this.instance.getImage();
	}

	/**
	 * @see ims.domain.lookups.LookupInstance#getMappings()
	 */
	public Set getMappings() 
	{
		return this.instance.getMappings();
	}

	public LookupMapping getMapping(String extSystem)
	{
		return this.instance.getMapping(extSystem);		
	}

	/**
	 * @see ims.domain.lookups.LookupInstance#getOrder()
	 */
	public int getOrder() 
	{
		return this.instance.getOrder();
	}

	/**
	 * @see ims.domain.lookups.LookupInstance#getParent()
	 */
	public LookupInstance getParent() 
	{
		return this.instance.getParent();
	}

	/**
	 * @see ims.domain.lookups.LookupInstance#removeMapping(ims.domain.lookups.LookupMapping)
	 */
	public boolean removeMapping(LookupMapping mapping) 
	{
		return this.instance.removeMapping(mapping);
	}

	/**
	 * @see ims.domain.lookups.LookupInstance#setActive(boolean)
	 */
	public void setActive(boolean b) 
	{
		this.instance.setActive(b);
	}

	/**
	 * @see ims.domain.lookups.LookupInstance#setImageFile(java.lang.String)
	 */
	public void setImage(Image image) 
	{
		this.instance.setImage(image);
	}

	/**
	 * @see ims.domain.lookups.LookupInstance#setOrder(int)
	 */
	public void setOrder(int i) 
	{
		this.instance.setOrder(i);
	}

	/**
	 * @see ims.domain.lookups.LookupInstance#setParent(ims.domain.lookups.LookupInstance)
	 */
	public void setParent(LookupInstance instance) {
		instance.setParent(instance);
	}

	/**
	 * @see ims.domain.lookups.LookupInstance#setText(java.lang.String)
	 */
	public void setText(String s) 
	{
		this.instance.setText(s);
	}
	
	public Color getColor()
	{
		return this.instance.getColor();
	}
	
	public void setColor(Color color)
	{
		this.instance.setColor(color);
	}
	
	public int getId()
	{
		return this.instance.getId();
	}
	
	public String getClassVersion()
	{
		return this.instance.getClassVersion();
	}
}
