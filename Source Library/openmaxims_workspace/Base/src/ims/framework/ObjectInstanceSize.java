package ims.framework;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectInstanceSize
{
	public static double getSize(Object instance)	
	{
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		ObjectOutputStream out;
		
		try
		{
			out = new ObjectOutputStream(bs);		
			out.writeObject(instance);
			out.close();
		}
		catch (IOException e)
		{			
			e.printStackTrace();
			return -1;
		}
		
		return bs.toByteArray().length;		
	}
}
