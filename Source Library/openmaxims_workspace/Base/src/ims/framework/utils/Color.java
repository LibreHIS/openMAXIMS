package ims.framework.utils;

import ims.framework.IEnhancedItem;
import ims.framework.exceptions.CodingRuntimeException;
import ims.framework.utils.beans.ColorBean;
import ims.vo.ImsCloneable;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

/**
 * This class represents an ARGB color.
 * @author Mikhail Chashchin
 * @author Brendan Woods
 * @version 1.2
 */

 /* IMPORTANT: 
  *	When adding / renaming / removing a color 
  * IMS Development Environment needs to be notified
  * and WebApp/g/colours/10x10 needs to be updated.
  */

public class Color implements ImsCloneable, Comparable, Serializable, IEnhancedItem
{
	private static final long serialVersionUID = 2643268102849681153L;
	public static final Color AliceBlue = new Color("AliceBlue", "#f0f8ff", new ImagePath(1, "g/colours/10x10/AliceBlue.gif"));
	public static final Color AntiqueWhite = new Color("AntiqueWhite", "#faebd7", new ImagePath(2, "g/colours/10x10/AntiqueWhite.gif"));
	public static final Color Aqua = new Color("Aqua", "#00ffff", new ImagePath(3, "g/colours/10x10/Aqua.gif"));
	public static final Color AquaMarine = new Color("AquaMarine", "#7fffd4", new ImagePath(4, "g/colours/10x10/AquaMarine.gif"));
	public static final Color Azure = new Color("Azure", "#f0ffff", new ImagePath(5, "g/colours/10x10/Azure.gif"));
	public static final Color Beige = new Color("Beige", "#f5f5dc", new ImagePath(6, "g/colours/10x10/Beige.gif"));
	public static final Color Bisque = new Color("Bisque", "#ffe4c4", new ImagePath(7, "g/colours/10x10/Bisque.gif"));
	public static final Color Black = new Color("Black", "#000000", new ImagePath(8, "g/colours/10x10/Black.gif"), true);
	public static final Color BlanchedAlmond = new Color("BlanchedAlmond", "#ffebcd", new ImagePath(9, "g/colours/10x10/BlanchedAlmond.gif"));
	public static final Color Blue = new Color("Blue", "#0000ff", new ImagePath(10, "g/colours/10x10/Blue.gif"), true);
	public static final Color BlueViolet = new Color("BlueViolet", "#8a2be2", new ImagePath(11, "g/colours/10x10/BlueViolet.gif"));
	public static final Color Brown = new Color("Brown", "#a52a2a", new ImagePath(12, "g/colours/10x10/Brown.gif"), true);
	public static final Color Burlywood = new Color("Burlywood", "#deb887", new ImagePath(13, "g/colours/10x10/Burlywood.gif"));
	public static final Color CadetBlue = new Color("CadetBlue", "#5f9ea0", new ImagePath(14, "g/colours/10x10/CadetBlue.gif"));
	public static final Color Chartreuse = new Color("Chartreuse", "#7fff00", new ImagePath(15, "g/colours/10x10/Chartreuse.gif"));
	public static final Color Chocolate = new Color("Chocolate", "#d2691e", new ImagePath(16, "g/colours/10x10/Chocolate.gif"));
	public static final Color Coral = new Color("Coral", "#ff7f50", new ImagePath(17, "g/colours/10x10/Coral.gif"));
	public static final Color CornFlower = new Color("CornFlower", "#6495ed", new ImagePath(18, "g/colours/10x10/CornFlower.gif"));
	public static final Color Cornsilk = new Color("Cornsilk", "#fff8dc", new ImagePath(19, "g/colours/10x10/Cornsilk.gif"));
	public static final Color Crimson = new Color("Crimson", "#DC143C", new ImagePath(20, "g/colours/10x10/Crimson.gif"));
	public static final Color Cyan = new Color("Cyan", "#00ffff", new ImagePath(21, "g/colours/10x10/Cyan.gif"), true);
	public static final Color DarkBlue = new Color("DarkBlue", "#00008b", new ImagePath(22, "g/colours/10x10/DarkBlue.gif"));
	public static final Color DarkCyan = new Color("DarkCyan", "#008b8b", new ImagePath(23, "g/colours/10x10/DarkCyan.gif"));
	public static final Color DarkGoldenRod = new Color("DarkGoldenRod", "#b8860b", new ImagePath(24, "g/colours/10x10/DarkGoldenRod.gif"));
	public static final Color DarkGray = new Color("DarkGray", "#a9a9a9", new ImagePath(25, "g/colours/10x10/DarkGray.gif"));
	public static final Color DarkGreen = new Color("DarkGreen", "#006400", new ImagePath(26, "g/colours/10x10/DarkGreen.gif"));
	public static final Color DarkKhaki = new Color("DarkKhaki", "#bdb76b", new ImagePath(27, "g/colours/10x10/DarkKhaki.gif"));
	public static final Color DarkMagenta = new Color("DarkMagenta", "#8b008b", new ImagePath(28, "g/colours/10x10/DarkMagenta.gif"));
	public static final Color DarkOliveGreen = new Color("DarkOliveGreen", "#556b2f", new ImagePath(29, "g/colours/10x10/DarkOliveGreen.gif"));
	public static final Color DarkOrange = new Color("DarkOrange", "#ff8c00", new ImagePath(30, "g/colours/10x10/DarkOrange.gif"));
	public static final Color DarkOrchid = new Color("DarkOrchid", "#9932cc", new ImagePath(31, "g/colours/10x10/DarkOrchid.gif"));
	public static final Color DarkRed = new Color("DarkRed", "#8b0000", new ImagePath(32, "g/colours/10x10/DarkRed.gif"));
	public static final Color DarkSalmon = new Color("DarkSalmon", "#e9967a", new ImagePath(33, "g/colours/10x10/DarkSalmon.gif"));
	public static final Color DarkSeareen = new Color("DarkSeareen", "#8fbc8b", new ImagePath(34, "g/colours/10x10/DarkSeareen.gif"));
	public static final Color DarkSlateBlue = new Color("DarkSlateBlue", "#483d8b", new ImagePath(35, "g/colours/10x10/DarkSlateBlue.gif"));
	public static final Color DarkSlateGray = new Color("DarkSlateGray", "#2f4f4f", new ImagePath(36, "g/colours/10x10/DarkSlateGray.gif"));
	public static final Color DarkTurquoise = new Color("DarkTurquoise", "#00ced1", new ImagePath(37, "g/colours/10x10/DarkTurquoise.gif"));
	public static final Color DarkViolet = new Color("DarkViolet", "#9400d3", new ImagePath(38, "g/colours/10x10/DarkViolet.gif"));
	public static final Color DeepPink = new Color("DeepPink", "#ff1493", new ImagePath(39, "g/colours/10x10/DeepPink.gif"));
	public static final Color DeepSkyBlue = new Color("DeepSkyBlue", "#00bfff", new ImagePath(40, "g/colours/10x10/DeepSkyBlue.gif"));
	public static final Color Default = new Color("Default", "", null, true);
	public static final Color DimGray = new Color("DimGray", "#696969", new ImagePath(42, "g/colours/10x10/DimGray.gif"));
	public static final Color DodgerBlue = new Color("DodgerBlue", "#1e90ff", new ImagePath(43, "g/colours/10x10/DodgerBlue.gif"));
	public static final Color FireBrick = new Color("FireBrick", "#b22222", new ImagePath(44, "g/colours/10x10/FireBrick.gif"));
	public static final Color FloralWhite = new Color("FloralWhite", "#fffaf0", new ImagePath(45, "g/colours/10x10/FloralWhite.gif"));
	public static final Color ForestGreen = new Color("ForestGreen", "#228b22", new ImagePath(46, "g/colours/10x10/ForestGreen.gif"));
	public static final Color Fuchia = new Color("Fuchia", "#ff00ff", new ImagePath(47, "g/colours/10x10/Fuchia.gif"));
	public static final Color Gainsboro = new Color("Gainsboro", "#dcdcdc", new ImagePath(48, "g/colours/10x10/Gainsboro.gif"));
	public static final Color GhostWhite = new Color("GhostWhite", "#f8f8ff", new ImagePath(49, "g/colours/10x10/GhostWhite.gif"));
	public static final Color Gold = new Color("Gold", "#ffd700", new ImagePath(50, "g/colours/10x10/Gold.gif"));
	public static final Color Goldenrod = new Color("Goldenrod", "#daa520", new ImagePath(51, "g/colours/10x10/Goldenrod.gif"));
	public static final Color Gray = new Color("Gray", "#808080", new ImagePath(52, "g/colours/10x10/Gray.gif"), true);
	public static final Color Green = new Color("Green", "#008000", new ImagePath(53, "g/colours/10x10/Green.gif"), true);
	public static final Color GreenYellow = new Color("GreenYellow", "#adff2f", new ImagePath(54, "g/colours/10x10/GreenYellow.gif"));
	public static final Color HoneyDue = new Color("HoneyDue", "#f0fff0", new ImagePath(55, "g/colours/10x10/HoneyDue.gif"));
	public static final Color HotPink = new Color("HotPink", "#ff69b4", new ImagePath(56, "g/colours/10x10/HotPink.gif"));
	public static final Color IndianRed = new Color("IndianRed", "#cd5c5c", new ImagePath(57, "g/colours/10x10/IndianRed.gif"));
	public static final Color Indigo = new Color("Indigo", "#4b0082", new ImagePath(58, "g/colours/10x10/Indigo.gif"), true);
	public static final Color Ivory = new Color("Ivory", "#fffff0", new ImagePath(59, "g/colours/10x10/Ivory.gif"));
	public static final Color Khaki = new Color("Khaki", "#e6e6fa", new ImagePath(60, "g/colours/10x10/Khaki.gif"));
	public static final Color Lavender = new Color("Lavender", "#e6e6fa", new ImagePath(61, "g/colours/10x10/Lavender.gif"));
	public static final Color LavenderBlush = new Color("LavenderBlush", "#fff0f5", new ImagePath(62, "g/colours/10x10/LavenderBlush.gif"));
	public static final Color LawnGreen = new Color("LawnGreen", "#7cfc00", new ImagePath(63, "g/colours/10x10/LawnGreen.gif"));
	public static final Color LemonChiffon = new Color("LemonChiffon", "#fffacd", new ImagePath(64, "g/colours/10x10/LemonChiffon.gif"));
	public static final Color LightBlue = new Color("LightBlue", "#add8e6", new ImagePath(65, "g/colours/10x10/LightBlue.gif"), true);
	public static final Color LightCoral = new Color("LightCoral", "#f08080", new ImagePath(66, "g/colours/10x10/LightCoral.gif"));
	public static final Color LightCyan = new Color("LightCyan", "#e0ffff", new ImagePath(67, "g/colours/10x10/LightCyan.gif"));
	public static final Color LightGoldenrodYellow = new Color("LightGoldenrodYellow", "#fafad2", new ImagePath(68, "g/colours/10x10/LightGoldenrodYellow.gif"));
	public static final Color LightGreen = new Color("LightGreen", "#90ee90", new ImagePath(69, "g/colours/10x10/LightGreen.gif"), true);
	public static final Color LightGray = new Color("LightGray", "#d3d3d3", new ImagePath(70, "g/colours/10x10/LightGray.gif"));
	public static final Color LightPink = new Color("LightPink", "#ffb6c1", new ImagePath(71, "g/colours/10x10/LightPink.gif"));
	public static final Color LightSalmon = new Color("LightSalmon", "#ffa07a", new ImagePath(72, "g/colours/10x10/LightSalmon.gif"));
	public static final Color LightSeaGreen = new Color("LightSeaGreen", "#20b2aa", new ImagePath(73, "g/colours/10x10/LightSeaGreen.gif"));
	public static final Color LightSkyBlue = new Color("LightSkyBlue", "#87cefa", new ImagePath(74, "g/colours/10x10/LightSkyBlue.gif"));
	public static final Color LightSlateGray = new Color("LightSlateGray", "#778899", new ImagePath(75, "g/colours/10x10/LightSlateGray.gif"));
	public static final Color LightSteelBlue = new Color("LightSteelBlue", "#b0c4de", new ImagePath(76, "g/colours/10x10/LightSteelBlue.gif"));
	public static final Color LightYellow = new Color("LightYellow", "#ffffe0", new ImagePath(77, "g/colours/10x10/LightYellow.gif"));
	public static final Color Lime = new Color("Lime", "#00ff00", new ImagePath(78, "g/colours/10x10/Lime.gif"));
	public static final Color LimeGreen = new Color("LimeGreen", "#32cd32", new ImagePath(79, "g/colours/10x10/LimeGreen.gif"));
	public static final Color Linen = new Color("Linen", "#faf0e6", new ImagePath(80, "g/colours/10x10/Linen.gif"));
	public static final Color Magenta = new Color("Magenta", "#ff00ff", new ImagePath(81, "g/colours/10x10/Magenta.gif"), true);
	public static final Color Maroon = new Color("Maroon", "#800000", new ImagePath(82, "g/colours/10x10/Maroon.gif"));
	public static final Color MediumAquaMarine = new Color("MediumAquaMarine", "#66cdaa", new ImagePath(83, "g/colours/10x10/MediumAquaMarine.gif"));
	public static final Color MediumBlue = new Color("MediumBlue", "#0000cd", new ImagePath(84, "g/colours/10x10/MediumBlue.gif"));
	public static final Color MediumOrchid = new Color("MediumOrchid", "#ba55d3", new ImagePath(85, "g/colours/10x10/MediumOrchid.gif"));
	public static final Color MediumPurple = new Color("MediumPurple", "#9370db", new ImagePath(86, "g/colours/10x10/MediumPurple.gif"));
	public static final Color MediumSeaGreen = new Color("MediumSeaGreen", "3cb371", new ImagePath(87, "g/colours/10x10/MediumSeaGreen.gif"));
	public static final Color MediumSlateBlue = new Color("MediumSlateBlue", "#7b68ee", new ImagePath(88, "g/colours/10x10/MediumSlateBlue.gif"));
	public static final Color MediumSpringGreen = new Color("MediumSpringGreen", "#00fa9a", new ImagePath(89, "g/colours/10x10/MediumSpringGreen.gif"));
	public static final Color MediumTurquoise = new Color("MediumTurquoise", "#48d1cc", new ImagePath(90, "g/colours/10x10/MediumTurquoise.gif"));
	public static final Color MediumVioletRed = new Color("MediumVioletRed", "#c71585", new ImagePath(91, "g/colours/10x10/MediumVioletRed.gif"));
	public static final Color MidnightBlue = new Color("MidnightBlue", "#191970", new ImagePath(92, "g/colours/10x10/MidnightBlue.gif"));
	public static final Color MintCream = new Color("MintCream", "#f5fffa", new ImagePath(93, "g/colours/10x10/MintCream.gif"));
	public static final Color MistyRose = new Color("MistyRose", "#ffe4e1", new ImagePath(94, "g/colours/10x10/MistyRose.gif"));
	public static final Color Moccasin = new Color("Moccasin", "#ffe4b5", new ImagePath(95, "g/colours/10x10/Moccasin.gif"));
	public static final Color NavajoWhite = new Color("NavajoWhite", "#ffdead", new ImagePath(96, "g/colours/10x10/NavajoWhite.gif"));
	public static final Color Navy = new Color("Navy", "#000080", new ImagePath(97, "g/colours/10x10/Navy.gif"));
	public static final Color OldLace = new Color("OldLace", "#fdf5e6", new ImagePath(98, "g/colours/10x10/OldLace.gif"));
	public static final Color Olive = new Color("Olive", "#808000", new ImagePath(99, "g/colours/10x10/Olive.gif"));
	public static final Color OliveDrab = new Color("OliveDrab", "#6b8e23", new ImagePath(100, "g/colours/10x10/OliveDrab.gif"));
	public static final Color Orange = new Color("Orange", "#ffa500", new ImagePath(101, "g/colours/10x10/Orange.gif"), true);
	public static final Color OrangeRed = new Color("OrangeRed", "#ff4500", new ImagePath(102, "g/colours/10x10/OrangeRed.gif"));
	public static final Color Orchid = new Color("Orchid", "#da70d6", new ImagePath(103, "g/colours/10x10/Orchid.gif"));
	public static final Color PaleGoldenrod = new Color("PaleGoldenrod", "#eee8aa", new ImagePath(104, "g/colours/10x10/PaleGoldenrod.gif"));
	public static final Color PaleGreen = new Color("PaleGreen", "#98fb98", new ImagePath(105, "g/colours/10x10/PaleGreen.gif"));
	public static final Color PaletTurquoise = new Color("PaletTurquoise", "#afeeee", new ImagePath(106, "g/colours/10x10/PaletTurquoise.gif"));
	public static final Color PaleVioletRed = new Color("PaleVioletRed", "#db7093", new ImagePath(107, "g/colours/10x10/PaleVioletRed.gif"));
	public static final Color PapayaWhip = new Color("PapayaWhip", "#ffefd5", new ImagePath(108, "g/colours/10x10/PapayaWhip.gif"));
	public static final Color PeachPuff = new Color("PeachPuff", "#ffdab9", new ImagePath(109, "g/colours/10x10/PeachPuff.gif"));
	public static final Color Peru = new Color("Peru", "#cd853f", new ImagePath(110, "g/colours/10x10/Peru.gif"));
	public static final Color Pink = new Color("Pink", "#ffc0cb", new ImagePath(111, "g/colours/10x10/Pink.gif"));
	public static final Color Plum = new Color("Plum", "#dda0dd", new ImagePath(112, "g/colours/10x10/Plum.gif"));
	public static final Color PowderBlue = new Color("PowderBlue", "#b0e0e6", new ImagePath(113, "g/colours/10x10/PowderBlue.gif"));
	public static final Color Purple = new Color("Purple", "#800080", new ImagePath(114, "g/colours/10x10/Purple.gif"));
	public static final Color Red = new Color("Red", "#ff0000", new ImagePath(115, "g/colours/10x10/Red.gif"), true);
	public static final Color RosyBrown = new Color("RosyBrown", "#bc8f8f", new ImagePath(116, "g/colours/10x10/RosyBrown.gif"));
	public static final Color RoyalBlue = new Color("RoyalBlue", "#4169e1", new ImagePath(117, "g/colours/10x10/RoyalBlue.gif"));
	public static final Color SaddleBrown = new Color("SaddleBrown", "#8b4513", new ImagePath(118, "g/colours/10x10/SaddleBrown.gif"));
	public static final Color Salmon = new Color("Salmon", "#fa8072", new ImagePath(119, "g/colours/10x10/Salmon.gif"));
	public static final Color SandyBrown = new Color("SandyBrown", "#f4a460", new ImagePath(120, "g/colours/10x10/SandyBrown.gif"));
	public static final Color SeaGreen = new Color("SeaGreen", "#2e8b57", new ImagePath(121, "g/colours/10x10/SeaGreen.gif"));
	public static final Color SeaShell = new Color("SeaShell", "#fff5ee", new ImagePath(122, "g/colours/10x10/SeaShell.gif"));
	public static final Color Sienna = new Color("Sienna", "#a0522d", new ImagePath(123, "g/colours/10x10/Sienna.gif"));
	public static final Color Silver = new Color("Silver", "#c0c0c0", new ImagePath(124, "g/colours/10x10/Silver.gif"));
	public static final Color SkyBlue = new Color("SkyBlue", "#87ceeb", new ImagePath(125, "g/colours/10x10/SkyBlue.gif"));
	public static final Color SlateBlue = new Color("SlateBlue", "#6a5acd", new ImagePath(126, "g/colours/10x10/SlateBlue.gif"));
	public static final Color SlateGray = new Color("SlateGray", "#708090", new ImagePath(127, "g/colours/10x10/SlateGray.gif"));
	public static final Color Snow = new Color("Snow", "#fffafa", new ImagePath(128, "g/colours/10x10/Snow.gif"));
	public static final Color SpringGreen = new Color("SpringGreen", "#00ff7f", new ImagePath(129, "g/colours/10x10/SpringGreen.gif"));
	public static final Color SteelBlue = new Color("SteelBlue", "#4682b4", new ImagePath(130, "g/colours/10x10/SteelBlue.gif"));
	public static final Color Tan = new Color("Tan", "#d2b48c", new ImagePath(131, "g/colours/10x10/Tan.gif"));
	public static final Color Teal = new Color("Teal", "#008080", new ImagePath(132, "g/colours/10x10/Teal.gif"));
	public static final Color Thistle = new Color("Thistle", "#d8bfd8", new ImagePath(133, "g/colours/10x10/Thistle.gif"));
	public static final Color Tomato = new Color("Tomato", "#ff6347", new ImagePath(134, "g/colours/10x10/Tomato.gif"));
	public static final Color Turquoise = new Color("Turquoise", "#40e0d0", new ImagePath(135, "g/colours/10x10/Turquoise.gif"), true);
	public static final Color Violet = new Color("Violet", "#ee82ee", new ImagePath(136, "g/colours/10x10/Violet.gif"));
	public static final Color Wheat = new Color("Wheat", "#f5deb3", new ImagePath(137, "g/colours/10x10/Wheat.gif"));
	public static final Color White = new Color("White", "#ffffff", new ImagePath(138, "g/colours/10x10/White.gif"), true);
	public static final Color WhiteSmoke = new Color("WhiteSmoke", "#f5f5f5", new ImagePath(139, "g/colours/10x10/WhiteSmoke.gif"));
	public static final Color Yellow = new Color("Yellow", "#ffff00", new ImagePath(140, "g/colours/10x10/Yellow.gif"), true);
	public static final Color YellowGreen = new Color("YellowGreen", "#9acd32", new ImagePath(141, "g/colours/10x10/YellowGreen.gif"));


	private String value;
	private String name;
	private Image image;
	private boolean isPrimary = false;

	public static Color fromRGB(int red, int green, int blue)
	{
		if(red < 0 || red > 255)
			throw new CodingRuntimeException("Invalid red color");
		if(green < 0 || green > 255)
			throw new CodingRuntimeException("Invalid green color");
		if(blue < 0 || blue > 255)
			throw new CodingRuntimeException("Invalid blue color");
		
		String redS = Integer.toHexString(red);
		redS = redS.length() == 1 ? "0" + redS : redS;
		
		String greenS = Integer.toHexString(green);
		greenS = greenS.length() == 1 ? "0" + greenS : greenS;
		
		String blueS = Integer.toHexString(blue);
		blueS = blueS.length() == 1 ? "0" + blueS : blueS;
		
		String hex = redS + greenS + blueS;
		return new Color(hex, "#" + hex);
	}
	
	public Color()
	{
		this("Default");
	}
	public Color(ColorBean bean)
	{
		this.name = bean.getName();
		this.value = bean.getValue();
	}
	private Color(String name, String value)
	{
		this.value = value;
		this.name = name;
	}
	private Color(String name, String value, Image image)
	{
		this.value = value;
		this.name = name;
		this.image = image;
	}

	private Color(String name, String value, Image image, boolean isPrimary)
	{
		this.value = value;
		this.name = name;
		this.image = image;
		this.isPrimary = isPrimary;
	}

	protected Color(String name)
	{
		this.name = name;
		Color val = Color.getColor(name);
		if (val != null) this.value = val.getValue();
	}
	
	public String getName() 
	{
		return this.name;
	}
	
	public Image getImage()
	{
		return this.image;
	}
	public Image getIItemImage()
	{
		return this.image;
	}

	public String getValue() 
	{
		if (this.name != null && (this.value == null || (this.value.equals("") && !this.name.equals("Default"))))
		{
			Color val = Color.getColor(this.name);
			if (val != null) 
				this.value = val.getValue();
		}
		return this.value;
	}

	public Color getTextColor() 
	{
		return null;
	}
	public Color getIItemTextColor() 
	{
		return null;
	}
	public String getText() 
	{
		return getName();
	}
	public String getIItemText() 
	{
		return getName();
	}

	public String toString()
	{
		return this.getValue();
	}

	public boolean equals(Object obj)
	{
		if(obj != null && obj instanceof Color)
		{
			Color col = (Color)obj;
			return col.name.toLowerCase().equals(this.name.toLowerCase()) ||
				col.getValue().toLowerCase().equals(this.getValue().toLowerCase());
		}
		
		return false;		
	}
	
	public int hashCode()
	{
		return this.name.hashCode();
	}
	
	@SuppressWarnings("unchecked")
	private static Color[] getColors(boolean primaryOnly)
	{
		ArrayList colors = new ArrayList();
		Field[] flds = Color.class.getFields();
		for (int i = 0; i < flds.length; i++)
		{
			Class c = flds[i].getType();
			if (Color.class.isAssignableFrom(c))
			{
				try 
				{
					Color col = (Color)flds[i].get(null);
					if (col.isPrimary == true || primaryOnly == false)
					{
						colors.add(flds[i].get(null));						
					}
				}
				catch (IllegalArgumentException e) 
				{
					return new Color[0];
				}
				catch (IllegalAccessException e) 
				{
					return new Color[0];
				}
			}
		}
		Color[] ret = new Color[colors.size()];
		colors.toArray(ret);
		return ret;		
	}

	public static Color[] getColors()
	{
		return getColors(true);
	}

	public static Color[] getAllColors()
	{
		return getColors(false);		
	}
	
	public static Color getColor(String name)
	{
		if (name == null) return Color.Default;
		
		Field[] flds = Color.class.getFields();
		for (int i = 0; i < flds.length; i++)
		{
			Class c = flds[i].getType();
			if (Color.class.isAssignableFrom(c) && flds[i].getName().equalsIgnoreCase(name))
			{
				try 
				{
					return (Color)flds[i].get(null);
				}
				catch (IllegalArgumentException e) 
				{
					return Color.Default;
				}
				catch (IllegalAccessException e) 
				{
					return Color.Default;
				}
			}
		}
		return Color.Default;
	}

	public Object clone()
	{
		return new Color(this.name, this.value);
	}
	
	public ColorBean getBean()
	{
		return new ColorBean(this);
	}
	public int compareTo(Object arg0) 
	{
		if (!(arg0 instanceof Color)) return 1;
		Color val = (Color)arg0;
		return this.getName().compareTo(val.getName());
	}
	
	public static ColorBean[] getBeanArray(Color[] val)
	{
		ColorBean[] beans = new ColorBean[val.length];
		for (int i = 0; i < val.length; i++)
		{
			beans[i] = val[i].getBean();			
		}
		return beans;
	}
	
	@SuppressWarnings("unchecked")
	public static void loadColourImages(Map map)
	{
		Color[] ca = Color.getAllColors();
		for (int i = 0; i < ca.length; i++)
		{
			if (ca[i].getImage() == null) continue;
			map.put(new Integer(ca[i].getImage().getImageId()), ca[i].getImage());
		}
	}
}
