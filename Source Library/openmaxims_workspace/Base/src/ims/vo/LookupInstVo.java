package ims.vo;

import java.io.Serializable;
import java.util.ArrayList;

import ims.framework.IEnhancedItem;
import ims.framework.controls.AnswerBoxOption;
import ims.framework.utils.Image;
import ims.framework.utils.Color;

/**
 * Abstract base class for LookupTypes used in presentation layer.
 * All methods are final.
 * There are no abstract methods on this class.
 * @author gcoghlan
 *
 */
public class LookupInstVo extends LookupInstRefVo implements ImsCloneable, AnswerBoxOption, IEnhancedItem, Serializable, Comparable
{
	private static final long serialVersionUID = 1L;
	
	private LookupInstVo parent;
	private Image image;
	private Color color;
	private int order;
	private ArrayList<LookupInstVo> children = new ArrayList<LookupInstVo>();
	private LookupMappingVoCollection mappings = new LookupMappingVoCollection();
	
	public LookupInstVo() 
	{
		super();
	}

	public LookupInstVo(int id) 
	{
		super(id);
	}
	
	public LookupInstVo(int id, String text, boolean active) 
	{
		super(id, text, active);
		this.parent = null;
		this.image = null;
		this.color = Color.Black;
	}

	public LookupInstVo(int id, String text, boolean active, LookupInstVo parent, Image image) 
	{
		super(id, text, active);
		this.parent = parent;
		this.image = image;
		this.color = Color.Black;
	}
	
	public LookupInstVo(int id, String text, boolean active, LookupInstVo parent, Image image, Color color) 
	{
		super(id, text, active);
		this.parent = parent;
		this.image = image;
		this.color = color;
	}

	public LookupInstVo(int id, String text, boolean active, LookupInstVo parent, Image image, Color color, int order) 
	{
		super(id, text, active);
		this.parent = parent;
		this.image = image;
		this.color = color;
		this.order = order;
	}

	public final int getID()
	{
		return super.getId();
	}
			
	/**
	 * @return
	 */
	public LookupInstVo getParentInstance() 
	{
		return this.parent;
	}

	/**
	 * @param type
	 */
	public void setParentInstance(LookupInstVo type) 
	{
		this.parent = type;
	}

	public Image getImage() 
	{
		return this.image;
	}
	public Image getIItemImage() 
	{
		return this.image;
	}
	public void setImage(Image image) 
	{
		this.image = image;
	}
	
	public Color getColor()
	{
		return this.color;
	}
	public Color getTextColor()
    {
        return this.getColor();
    }
	public Color getIItemTextColor()
    {
        return this.getColor();
    }
	public void setColor(Color color) 
	{
		this.color = color;
	}

	public int getOrder()
	{
		return this.order;		
	}
	public void setOrder(int order)
	{
		this.order = order;
	}
	
	public final String getIItemText()
	{
		return toString();
	}

	/**
	 * @see java.lang.Object#clone()
	 */
	public Object clone() 
	{
		LookupInstVo clone = null;
		try {
			Class[] params = { Integer.TYPE };
			java.lang.reflect.Constructor ctor = this.getClass().getConstructor(params);			
			Object[] args = {new Integer(this.getId())};			
			clone = (LookupInstVo)ctor.newInstance(args);
		}
		catch(Throwable e) {
			throw new RuntimeException(e.getMessage(), e);
		}		
		clone.setActive(this.isActive());
		clone.setText(this.getText());
		clone.color = this.color;
		clone.image = this.image;
		if(this.parent != null)
			clone.parent = (LookupInstVo)this.parent.clone();
		return clone;
	}
	
	public ArrayList<LookupInstVo> getChildInstances() 
	{
		return this.children;
	}

	public void setChildren(ArrayList<LookupInstVo> list) 
	{
		this.children = list;
	}
	
	public int addChild(LookupInstVo child)
	{
		this.children.add(child);
		return this.children.size();
	}

	public int removeChild(LookupInstVo child)
	{
		this.children.remove(child);
		return this.children.size();
	}
	
	/**
	 * Checks to see if this lookup instance has any active child instances
	 * @return
	 */
	public boolean hasActiveChildren()
	{
		return hasActiveChildren(this.getChildInstances());
	}

	private boolean hasActiveChildren(ArrayList coll)
	{
		if (coll.size() == 0) return false;
		for (int i = 0; i < coll.size(); i++)
		{
			LookupInstVo child = (LookupInstVo)coll.get(i);
			if (child.isActive()) return true;
			if (hasActiveChildren(child.getChildInstances())) return true;
		}
		return false;
	}
	
	public LookupMappingVoCollection getMappings() {
		return mappings;
	}
	
	public int addMapping(LookupMappingVo mapping)
	{
		if (mappings.contains(mapping))
		{
			return mappings.size();
		}
		mappings.add(mapping);
		return mappings.size();
	}

	public void setMappings(LookupMappingVoCollection mappings) {
		this.mappings = mappings;
	}
	
	public void clearMappings()
	{
		if (this.mappings != null) mappings.clear();
	}
	
	public int getTypeId()
	{
		return 0;
	}

	public LookupInstanceBean getBean()
	{
		return new LookupInstanceBean(this);
	}

	public static LookupInstanceBean[] getBeanArray(LookupInstVo[] val)
	{
		LookupInstanceBean[] beans = new LookupInstanceBean[val.length];
		for (int i = 0; i < val.length; i++)
		{
			beans[i] = val[i].getBean();			
		}
		return beans;
	}

	/**
	 *  FWB-448
	 *  This method will return the mapping value for the given external system
	 *  or null if none found
	 * @param extSystem
	 * @return LookupMapping if found, or null
	 */
	public LookupMappingVo getLookupMappingForExtSystem(String extSystem)
	{
		if (mappings == null || mappings.size() == 0 || extSystem == null)
			return null;
		
		for (int i=0; i<mappings.size(); i++)
		{
			if (mappings.get(i).getExtSystem() != null && mappings.get(i).getExtSystem().equalsIgnoreCase(extSystem))
				return mappings.get(i);
		}
		
		return null;
	}
	
}
