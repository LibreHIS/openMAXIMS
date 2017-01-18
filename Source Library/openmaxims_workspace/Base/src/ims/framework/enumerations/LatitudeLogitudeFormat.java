package ims.framework.enumerations;

import java.io.Serializable;

/**
 * @author snesbitt
 */
public class LatitudeLogitudeFormat implements Serializable
{
	//DEGREES DEG
	//RADIANS RAD
	//SPACES  SPA Spaces instead of colons
	//SYMBOLS SYM Degrees, Minutes and Seconds symbols instead of colons
	//OSGB36  Ordnance Survey of Great Britain (default) datum (default)
	//WGS84   World Geodetic Standard 1984 datum
	
	private static final long serialVersionUID = 1L;
	
	public static final int EDEGREES = 1;
	public static final int ERADIANS = 2;
	public static final int ESPACES = 3;
	public static final int ESYMBOLS = 4;
	public static final int EOSGB36 = 5;
	public static final int EWGS84 = 6;
	
	public static final LatitudeLogitudeFormat DEGREES = new LatitudeLogitudeFormat(1);
	public static final LatitudeLogitudeFormat RADIANS = new LatitudeLogitudeFormat(2);
	public static final LatitudeLogitudeFormat SPACES = new LatitudeLogitudeFormat(3);
	public static final LatitudeLogitudeFormat SYMBOLS = new LatitudeLogitudeFormat(4);
	public static final LatitudeLogitudeFormat OSGB36 = new LatitudeLogitudeFormat(5);
	public static final LatitudeLogitudeFormat WGS84 = new LatitudeLogitudeFormat(6);

	private LatitudeLogitudeFormat(int value)
	{
		this.id = value;
	}

	protected int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public static int parse(int id)
	{
		if(id == DEGREES.id)
			return EDEGREES;
		else if(id == RADIANS.id)
			return ERADIANS;		
		else if(id == SPACES.id)
			return ESPACES;
		else if(id == SYMBOLS.id)
			return ESYMBOLS;
		else if(id == OSGB36.id)
			return EOSGB36;
		else if(id == WGS84.id)
			return EWGS84;
		
		throw new RuntimeException("Unable to find LatitudeLogitudeFormat matching id " + id);
	}	
	
}
