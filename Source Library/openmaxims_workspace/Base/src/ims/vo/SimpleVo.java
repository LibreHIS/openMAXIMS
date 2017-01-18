/*
 * Created on 14-Oct-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.vo;

import java.util.ArrayList;

import ims.framework.cn.data.TreeNode;
import ims.framework.utils.Image;


/**
 * @author bworwood
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SimpleVo extends ims.vo.ValueObject implements TreeNode
{

	private static final long serialVersionUID = 1L;
	private String primValues;
	private SimpleVoCollection collValues;
	private SimpleVo parent;
	private ArrayList<SimpleVo> children;
	private boolean isValidated = false;
	private boolean isBusy = false;

	public SimpleVo()
	{
		this.children = new ArrayList<SimpleVo>();
	}

	public String getFieldValue()
	{
		return this.primValues;
	}
	
	public void setStringValue(String value)
	{
		this.primValues = value;
	}
	
	public void setVoColl(SimpleVoCollection value)
	{
		this.collValues = value;
	}
	
	public SimpleVoCollection getVoColl()
	{
		return this.collValues;
	}
	 public TreeNode getParentNode()
	 {
		 return this.parent;
	 }
	 public SimpleVo getParent()
	 {
		 return this.parent;
	 }
	 public void setParent(SimpleVo parent)
	 {
		 this.parent = parent;
	 }
	 public TreeNode[] getChildren()
	 {
	 	if (this.children == null)
	 		return null;
		SimpleVo[] typedChildren = new SimpleVo[this.children.size()];
		for (int i = 0; i < this.children.size(); i++)
		{
		 typedChildren[i] = this.children.get(i);
		}
		return typedChildren;
	 }
	 public int addChild(TreeNode child)
	 {
		 if (child instanceof SimpleVo)
		 {
		 	if (this.children == null)
		 		this.children = new ArrayList<SimpleVo>();
		 	this.children.add((SimpleVo)child);
		 }
		 return this.children.size();
	 }
	 public int removeChild(TreeNode child)
	 {
		 if (child instanceof SimpleVo)
		 {
		 	for (int i=0; i < this.children.size(); i++)
		 	{
		 		SimpleVo val = this.children.get(i);
		 		if (val.equals(child))
		 			this.children.remove(i);
		 	}
		 }
		 return this.children.size();
	 }
	 public Image getExpandedImage()
	 {
	 	return null;
		 //return super.getImage();
	 }
	 public Image getCollapsedImage()
	 {
	 	return null;
		 //return super.getImage();
	 }
	 public static SimpleVo[] getNegativeInstances()
	 {
		 return new SimpleVo[] {};
	 }
	 public static String[] getNegativeInstanceNames()
	 {
		 return new String[] {};
	 }
	 public boolean isValidated() 
	 {
	     if(this.isBusy)
			return true;
		this.isBusy = true;
		
		if(!this.isValidated)
		{
			this.isBusy = false;
			return false;
		}
		this.isBusy = false;
		return true;
     }

	 @SuppressWarnings("unchecked")
	 public String[] validate() 
	 {	
	     if(this.isBusy)
				return null;
		this.isBusy = true;
		java.util.ArrayList listOfErrors = new java.util.ArrayList();
		
		if (this.parent != null)
		    listOfErrors.add(this.parent.validate());
		
		if (this.collValues != null)
		{
			String[] listOfOtherErrors = this.collValues.validate();
			if(listOfOtherErrors != null)
			{
				for(int x = 0; x < listOfOtherErrors.length; x++)
				{
					listOfErrors.add(listOfOtherErrors[x]);
				}
			}
		}
		
		int errorCount = listOfErrors.size();
		if(errorCount == 0)
		{
			this.isBusy = false;
			this.isValidated = true;
			return null;
		}
		String[] result = new String[errorCount];
		for(int x = 0; x < errorCount; x++)
		{
	        result[x] = (String)listOfErrors.get(x);
		}
		this.isBusy = false;
		this.isValidated = false;
		return result;
     }
	 
	 @SuppressWarnings("unchecked")
	 public Object clone()
	 {
	     if(this.isBusy)
			return this;
		this.isBusy = true;
		
		SimpleVo clone = new SimpleVo();
		clone.children = (ArrayList<SimpleVo>) this.children.clone();
		clone.collValues = (SimpleVoCollection) this.collValues.clone();
		clone.primValues = this.primValues;
		clone.parent = (SimpleVo) this.parent.clone();
		
		return clone;
			
	 }
	 public String getIItemText()
	 {
		 return toString();
	 }
	 public String toString()
	 {
 		return this.primValues;
 	 }

	public Integer getBoId() 
	{
		return null;
	}

	public String getBoClassName() {
		return null;
	}
 }


