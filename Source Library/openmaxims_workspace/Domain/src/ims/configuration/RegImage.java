package ims.configuration;

import java.io.Serializable;

import ims.framework.utils.ImagePath;

public class RegImage extends ImagePath implements Serializable
{
	private static final long serialVersionUID = 5702319537397194580L;
	protected int version;
	private Integer nameSpace;
	private Boolean isSystem;
	private Boolean isActive;
	private Boolean	isRIE;

	public RegImage()
	{
		super();		
	}

	public RegImage(int imageID)
	{	
		super(imageID);	
	}
	
	public RegImage(Integer id)
	{	
		super(id.intValue());	
	}
	
	public RegImage(int imageID, String path)
	{	
		super(imageID);	
		setImagePath(path);
	}
	
	public RegImage(int imageID, String imagePath, Integer width, Integer height)
	{
		super(imageID, imagePath, width, height);
	}
	
	public Boolean isIsSystem() {
		return this.isSystem;
	}

	public void setIsSystem(Boolean isSystem) {
		this.isSystem = isSystem;
	}

	public Integer getNameSpace() {
		return this.nameSpace;
	}

	public void setNameSpace(Integer nameSpace) {
		this.nameSpace = nameSpace;
	}

	public Boolean getIsActive()
	{
		return isActive;
	}

	public void setIsActive(Boolean active)
	{
		this.isActive = active;
	}
	
	public Boolean getIsRIE()
	{
		return isRIE;
	}

	public void setIsRIE(Boolean isRIE)
	{
		this.isRIE = isRIE;
	}	
}
