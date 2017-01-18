/*
 * Created on 16-Jul-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ims.framework.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import ims.configuration.EnvironmentConfig;
import ims.framework.utils.beans.ImageBean;
import ims.utils.Logging;
import ims.vo.ImsCloneable;

/**
 * @author jmacenri
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ImagePath implements Image, ImsCloneable, java.io.Serializable
{
	private static final long serialVersionUID = -1096885506419769240L;
	private static final Logger	LOG	= Logging.getLogger(ImagePath.class);
	private int id;
	private String imagePath;
	private Integer width;
	private Integer height;

	public ImagePath()
	{		
	}
	public ImagePath(int imageID)
	{
		this.id = imageID;
	}
	public ImagePath(int imageID, String imagePath)
	{
		this(imageID, imagePath, null, null);
	}
	public ImagePath(int imageID, String imagePath, Integer width, Integer height)
	{
		this.id = imageID;
		this.imagePath = imagePath;
		
		if(width != null && width.intValue() <= 0)
			throw new RuntimeException("Invalid image width");		
		this.width = width;
		
		if(height != null && height.intValue() <= 0)
			throw new RuntimeException("Invalid image height");		
		this.height = height;
	}
	public int getId()
	{
		return this.id;
	}
	
	public int getImageId()
	{
		return this.getId();
	}
	
	public boolean equals(Object obj)
	{
		if (null == obj)
		{
			return false;
		}
		if ( !(obj instanceof Image) )
		{
			return false;
		}
		Image img = (Image)obj;
		if (this.id == img.getImageId()) 
			return true;
		return false;	
	}
	public boolean isActive()
	{
		return true;
	}
	public int hashCode()
	{
		return this.id;
	}	
	public Object clone()
	{
		return this;
	}	
	public ImageBean getImageBean()
	{
		return getBean();
	}
	public ImageBean getBean()
	{
		return new ImageBean(this);
	}
	public String getImagePath() 
	{
		return this.imagePath;		
	}
	public void setImagePath(String imagePath) 
	{
		if ( null != imagePath && imagePath.length() > 200 ) 
		{
			throw new RuntimeException("MaxLength (200) exceeded for imagePath. Tried to set value: " +
				imagePath);
		}
		this.imagePath = imagePath;
	}
	public static ImageInfo getImageInfo(InputStream in) 
	{
		ImageInfo ii = new ImageInfo();
		ii.setDetermineImageNumber(true);
		ii.setInput(in);
		if (!ii.check())
		{
			return null;
		}
		try 
		{
			in.close();
		}
		catch (IOException e) 
		{
			//No need to do anything about this exception.
		}
		ii.setInput((InputStream)null);
		return ii;		
	}
	public static ImageInfo getImageInfo(byte[] in) 
	{
		InputStream input = new ByteArrayInputStream(in);
		return getImageInfo(input);
	}
	
	public static byte[] getImageContent(String fileName)
	{
		String realFileName = getRealFileName(fileName);
		File file = new File(realFileName);
		try
		{
            FileInputStream in = new FileInputStream(file);
            byte[] ret = new byte[ (int) file.length()];
            in.read(ret);
            return ret;
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	public static ImageInfo getImageInfo(String fileName)
	{
		if(LOG.isDebugEnabled())
			LOG.debug("fileName="+fileName+"#");
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		if (input != null)
		{
			return getImageInfo(input);
		}

		String realFileName = getRealFileName(fileName);
		if(LOG.isDebugEnabled())
			LOG.debug("realFileName="+realFileName+"#");

		try
		{
			input = new FileInputStream(realFileName);
		}
		catch (FileNotFoundException e)
		{
			if(LOG.isDebugEnabled())
				LOG.debug("realFileName="+realFileName+"#",e);
			return null;
			
		}

		return getImageInfo(input);
	}
	
	private static String getRealFileName(String fileName)
	{
		File file = new File(fileName);
		if (file.isFile())
		{
			return fileName;
		}
		
		String locName = fileName;
		if (locName.startsWith("/") || locName.startsWith("\\"))
		{
			locName = locName.substring(1);
		}			
		
		String basePath = EnvironmentConfig.getUserImagesStorePath();
		if (basePath != null && !basePath.equals(""))
		{
			if (!basePath.endsWith("/") && !basePath.endsWith("\\"))
			{
				basePath += "/";
			}			
			
			String filename =  locName.substring(locName.lastIndexOf("/")+1);  
			String fullPath = basePath + filename;
			File testFile = new File(fullPath);
			if(testFile.isFile())
			{
				return fullPath;
			}
		}
		// A system loaded file 
		basePath = EnvironmentConfig.getBaseUri();
		if (!basePath.endsWith("/") && !basePath.endsWith("\\"))
		{
			basePath += "/";
		}
		String fullPath = basePath + locName;
		File testFile = new File(fullPath);
		if(testFile.isFile())
		{
			return fullPath;
		} else
		{
			LOG.warn("File"+fileName +" not found based either " + EnvironmentConfig.getBaseUri() +
					" or " + EnvironmentConfig.getFileUploadMountpoint());
			return fullPath; // A bit naff but needed for compatability with installations where 
							// files are deleted but DB records still exist!
		}
	}
/*
	private static String getRealFileName(String fileName)
	{
		File file = new File(fileName);
		if (file.isFile())
		{
			return fileName;
		}
		
		String locName = fileName;
		if (locName.startsWith("/") || locName.startsWith("\\"))
		{
			locName = locName.substring(1);
		}			
		
		String basePath = ConfigFlag.GEN.FILE_UPLOAD_MOUNTPOINT.getValue();
		if (basePath == null || basePath.equals(""))
			basePath = ConfigFlag.BASE_URI.getValue();
		if (!basePath.endsWith("/") && !basePath.endsWith("\\"))
		{
			basePath += "/";
		}
		String fullPath = basePath + locName;
		File testFile = new File(fullPath);
		if(testFile.isFile())
		{
			return fullPath;
		}
		else // A system loaded file 
		{
			basePath = ConfigFlag.BASE_URI.getValue();
			if (!basePath.endsWith("/") && !basePath.endsWith("\\"))
			{
				basePath += "/";
			}
			fullPath = basePath + locName;
			testFile = new File(fullPath);
			if(testFile.isFile())
			{
				return fullPath;
			} else
			{
				throw new ConfigurationRuntimeException("File not found based either "+ConfigFlag.BASE_URI.getValue()+
						" or "+ConfigFlag.GEN.FILE_UPLOAD_MOUNTPOINT.getValue());
			}
				
		}
	}
*/
	
	
	public ImageInfo getImageInfo()
	{
		return getImageInfo(this.getImagePath());
	}
	public int getImageWidth() 
	{
		return getWidth();
	}
	public int getWidth() 
	{
		if(this.width != null)
			return this.width.intValue();
		
		ImageInfo info = getImageInfo();
		if (info == null) 
			return 0;
		
		this.width = new Integer(info.getWidth());
		return this.width.intValue();
	}
	
	public int getImageHeight() 
	{
		return getHeight();
	}
	public int getHeight() 
	{
		if(this.height != null)
			return this.height.intValue();
		
		ImageInfo info = getImageInfo();
		if (info == null) 
			return 0;

		this.height = new Integer(info.getHeight());
		return this.height.intValue();
	}
	public String toString()
	{
		if (imagePath == null || imagePath.equals(""))
		{
			return "" + id;
			
		}
		return id + "," + imagePath;
	}
	
	public boolean isWidthNull()
	{
		return this.width == null;
	}
	
	public boolean isHeightNull()
	{
		return this.height == null;
	}
	public String toXMLString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<class id=\"" + this.getId() + "\" >");
		sb.append(" source=\"" + EnvironmentConfig.getImportExportSourceName() + "\" >");
		sb.append("<imagePath>");
		sb.append(this.getImagePath());
		sb.append("</imagePath>");
		sb.append("<width>");
		sb.append(this.getWidth());
		sb.append("</width>");
		sb.append("<height>");
		sb.append(this.getHeight());
		sb.append("</height>");
		sb.append("</class>");
		return sb.toString();
	}
}
