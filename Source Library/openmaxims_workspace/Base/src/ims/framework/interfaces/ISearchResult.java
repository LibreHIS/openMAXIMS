package ims.framework.interfaces;

import ims.framework.utils.Image;

import java.io.Serializable;

public interface ISearchResult extends Serializable
{
	int getId();
	int getScore();
	Image getImage();
	String getText();
	String getDescription();	
	INavForm getLink();
}
