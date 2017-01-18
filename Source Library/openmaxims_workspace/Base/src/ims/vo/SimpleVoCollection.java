/*
 * Created on 14-Oct-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.vo;


import java.util.ArrayList;

import ims.framework.IItem;
import ims.framework.cn.data.TreeModel;
import ims.framework.cn.data.TreeNode;

/**
 * @author bworwood
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SimpleVoCollection extends ims.vo.ValueObjectCollection implements TreeModel
{
	private static final long serialVersionUID = 1L;
	private ArrayList<SimpleVo> col = new ArrayList<SimpleVo>();
	
	public void add(SimpleVo value)
	 {
		 this.col.add(value);
		 
	 }
	 public void remove(int index)
	 {
		 this.col.remove(index);
	 }
	 public int size()
	 {
		 return this.col.size();
	 }
	 public int indexOf(SimpleVo instance)
	 {
		 return this.col.indexOf(instance);
	 }
	 public SimpleVo get(int index)
	 {
		 return this.col.get(index);
	 }
	 public Object clone()
	 {
		SimpleVoCollection newCol = new SimpleVoCollection();
		SimpleVo item;
		 for (int i = 0; i < this.col.size(); i++)
		 {
			 item = this.get(i);
			 newCol.add(new SimpleVo());
		 }
		 for (int i = 0; i < newCol.size(); i++)
		 {
			 item = newCol.get(i);
			 if (item.getParent() != null)
			 {
				 item.setParent(newCol.get(this.col.indexOf(item.getParent())));
			 }
		 }
		 return newCol;
	 }
	 private void sortHierarchy()
	 {
		 if (this.sorted) return;
		 ArrayList<SimpleVo> rootItems = new ArrayList<SimpleVo>();
		SimpleVo item;
		 for (int i = 0; i < this.col.size(); i++)
		 {
			 item = this.col.get(i);
			 if (item.getParent() == null)
			 {
				 rootItems.add(item);
			 }
			 else
			 {
				 item.getParent().addChild(item);
			 }
		 }
		 this.rootNodes = new SimpleVo[rootItems.size()];
		 rootItems.toArray(this.rootNodes);
		 this.sorted = true;
	 }
	 public TreeNode[] getRootNodes()
	 {
		 if (!this.sorted)
		 {
			 sortHierarchy();
		 }
		 return this.rootNodes;
	 }
	 
	 public boolean isValidated()
	 {
	     for(int x = 0; x < this.col.size(); x++)
			if(!(this.col.get(x)).isValidated())
				return false;
		return true;
	 }
 	 @SuppressWarnings("unchecked")
	public String[] validate()
	 {
	 	if(this.col.size() == 0)
	 		return null;
	 	java.util.ArrayList listOfErrors = new java.util.ArrayList();
	 	for(int x = 0; x < this.col.size(); x++)
		{
			String[] listOfOtherErrors = this.col.get(x).validate();
			if(listOfOtherErrors != null)
			{
				for(int y = 0; y < listOfOtherErrors.length; y++)
				{
					listOfErrors.add(listOfOtherErrors[y]);
				}
			}
		}
		
		int errorCount = listOfErrors.size();
		if(errorCount == 0)
			return null;
		String[] result = new String[errorCount];
		for(int x = 0; x < errorCount; x++)
			result[x] = (String)listOfErrors.get(x);
		return result;
	 }
 	 public IItem[] getItems() 
     {
        return null;
    }

 	public String getBoClassName()
	{
		return "SimpleVo";
	}    
	 
	 private boolean sorted = false;
	 private SimpleVo[] rootNodes;
	
	 @Override
	protected ArrayList getTypedCollection()
	{
		return col;
	}
 }
