package ims.framework;

import ims.framework.utils.Color;
import ims.framework.utils.Image;

/**
 * @author mmihalec
 */
public interface IEnhancedItem extends IItem 
{
    Image getIItemImage();
    Color getIItemTextColor();    
}
