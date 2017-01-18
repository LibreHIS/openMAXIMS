/*
 * Created on 16-Jul-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.framework.cn.data;

import ims.framework.utils.Image;

/**
 * @author jmacenri
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface TreeNode
{
	public TreeNode[] getChildren();
	
	/**
	 * Returns the number of children this now has
	 * @param child
	 * @return
	 */
	public int addChild(TreeNode child);
	
	/**
	 * Returns the number of children this now has
	 * @param child
	 * @return
	 */
	public int removeChild(TreeNode child);
	
	public TreeNode getParentNode();
	
	public Image getExpandedImage();
	public Image getCollapsedImage();
}
