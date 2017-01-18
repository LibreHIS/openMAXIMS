/*
 * Created on 20-Jul-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.framework.controls;

import ims.framework.utils.Color;
import ims.framework.utils.Image;

/**
 * @author jmacenri
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface AnswerBoxOption
{	
	public boolean equals(Object obj);
	public int hashCode();
	public int getID();
	public String getText();
	public Image getImage();
    public Color getTextColor();
}
