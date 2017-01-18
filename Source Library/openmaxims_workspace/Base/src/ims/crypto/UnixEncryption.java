package ims.crypto;
/*
 * Created on Jan 26, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

/** Encryption algorithm used to encrypt the db-password before storing it in APP_USERS
 ** and for decrypting it after retrieving it from APP_USERS
 ** Failed to get this to work with passwords that had been encrypted by the C-code version
 ** Should be possible in theory. The code below is got from the web as a Java ported version
 ** of the original C code. */
public class UnixEncryption
{
	private static final int ENCRYPT = 1;
	private static final int DECRYPT = 2;


	char ax,bx,cx,dx,si,tmp,x1a2,res,i,inter,cfc,cfd,compte;
	char x1a0[] = new char[5];
	private byte cle[];

	private void code()
	{
		dx=(char) (x1a2+i);
		ax=x1a0[i];

		cx=0x015a;
		bx=0x4e35;

		tmp=ax;
		ax=si;
		si=tmp;

		tmp=ax;
		ax=dx;
		dx=tmp;

		if (ax!=0)
		{
			ax=(char) (ax*bx);
		}

		tmp=ax;
		ax=cx;
		cx=tmp;

		if (ax!=0)
		{
			ax=(char) (ax*si);
			cx=(char) (ax+cx);
		}

		tmp=ax;
		ax=si;
		si=tmp;
		ax=(char) (ax*bx);
		dx=(char) (cx+dx);

		ax=(char) (ax+1);

		x1a2=dx;
		x1a0[i]=ax;

		res=(char) (ax^dx);
		i=(char) (i+1);
	}


	private void assemble()
	{
		x1a0[0]= (char) (( cle[0]*256 )+ cle[1]);

		code();
		inter=res;

		x1a0[1]= (char) (x1a0[0] ^ ( (cle[2]*256) + cle[3] ));
		code();
		inter=(char) (inter^res);

		x1a0[2]= (char) (x1a0[1] ^ ( (cle[4]*256) + cle[5] ));
		code();
		inter=(char) (inter^res);

		x1a0[3]= (char) (x1a0[2] ^ ( (cle[6]*256) + cle[7] ));
		code();
		inter=(char) (inter^res);

		x1a0[4]= (char) (x1a0[3] ^ ( (cle[8]*256) + cle[9] ));
		code();
		inter=(char) (inter^res);
		i=0;
	}


	private int endecypher( byte[] dst,  byte[] src, int action)
	{
		si=0;
		x1a2=0;
		i=0;

		String key = "Imsmaxims!";
		cle = key.getBytes();

		for (int lp = 0; lp < src.length; lp++)
		{
			int c = src[lp];

			assemble();
			cfc=(char) (inter>>8);
			cfd=(char) (inter&255); /* cfc^cfd = random byte */

			/* K ZONE !!!!!!!!!!!!! */
			/* if ENCRYPT here the mix of c and cle[compte] is before the encryption of c */
			/* else if DECRYPT here the mix of c and cle[compte] is after the decryption of c */


			if(action == DECRYPT)
			{
				c--;
				c = c ^ (cfc^cfd);
			}

			for (compte=0;compte<=9;compte++)
			{
				/* we mix the plaintext byte with the key */
				cle[compte]=(byte) (cle[compte]^ c);
			}

			if(action == ENCRYPT)
			{
				c = c ^ (cfc^cfd);
				c++;
			}
			if (c == 0) return 101;

			dst[lp]=(byte)c; /* we write the crypted/decrypted byte in the destination string */
		}

		return 0;
	}

	private void changeSrc(byte[] src, int count)
	{
		int i ;
		int len = src.length;

		for (i = 0; i < len; i++)
		{
			if (((1<<i) & count) != 0)
			{
				src[i] = (byte)(src[i] + 0x80);
			}
		}

		return;
	}

	public int pc1Encrypt(byte[] dst, byte[] src)
	{
		int ret = 0;
		int i,count,tot;

		i=count=0;
		tot = (1 << src.length);

		byte[] lsrc = new byte[src.length];

		while(count < tot)
		{
			for (i = 0; i<src.length; i++) lsrc[i] = src[i];
			ret = endecypher(dst, lsrc, ENCRYPT);
			if (ret < 0) return ret;
			if (ret == 101)
			{
				count++;
				for (i = 0; i<src.length; i++) lsrc[i] = src[i];
				changeSrc(lsrc, count);
			}
			else
				break;
		}

		if (count == tot) return -1;
		else return count;
	}

	public int pc1Decrypt(byte[] dst, byte[] src)
	{
		int ret = 0;
		int i;

		ret = endecypher(dst, src, DECRYPT);
		for (i = 0; i < dst.length; i++)
		{
			int uval = dst[i] & 0xff; //Mask off sign bit
			if (uval > 128)
			{
				uval = uval - 0x80;
				dst[i] = (byte)uval;
			}
		}
		return ret;
	}
	
	public byte[] asc2bin(byte[] bin) 
	{
		int i;
		int len = bin.length;
		int remainder = len % 4;
		int num = (len - remainder) / 4;
		byte[] bout;
		byte pb;
		
		if (remainder > 0) 
		{
			bout = new byte[ (num * 3) + remainder - 1];
		}
		else 
		{
			bout = new byte[num * 3];
		}
		
		for (i = 0; i < num; i++) 
		{
			pb = (byte) (bin[i * 4 + 3] - 32);
			bout[i * 3] = (byte) ( ( (bin[i * 4] - 32) << 2) + ( (pb >> 4) & 3));
			bout[i * 3 + 1] = (byte) ( ( (bin[i * 4 + 1] - 32) << 2) + ( (pb >> 2) & 3));
			bout[i * 3 + 2] = (byte) ( ( (bin[i * 4 + 2] - 32) << 2) + (pb & 3));
		}
		if (remainder > 0) 
		{
			pb = (byte) (bin[i * 4 + remainder - 1] - 32);
			switch (remainder) 
			{
			case 2:
				bout[i * 3] = (byte) ( ( (bin[i * 4] - 32) << 2) + (pb & 3));
				break;
			case 3:
				bout[i * 3] = (byte) ( ( (bin[i * 4] - 32) << 2) + ( (pb >> 2) & 3));
				bout[i * 3 + 1] = (byte) ( ( (bin[i * 4 + 1] - 32) << 2) + (pb & 3));
				break;
			}
		}
		return bout;
	}
	public byte[] bin2asc(byte[] bin) 
	{
		int i;
		int len = bin.length;
		int remainder = len % 3;
		int num = (len - remainder) / 3;
		byte[] bout;
		
		if (remainder > 0) 
		{
			bout = new byte[len + num + 1];
		}
		else 
		{
			bout = new byte[len + num];
		}
		
		for (i = 0; i < num; i++) 
		{
			
			bout[i * 4] = (byte) ( ((bin[i * 3] & 0xff) >> 2) + 32);
			bout[i * 4 + 1] = (byte) ( ((bin[i * 3 + 1] & 0xff) >> 2) + 32);
			bout[i * 4 + 2] = (byte) ( ((bin[i * 3 + 2] & 0xff) >> 2) + 32);
			bout[i * 4 +
				 3] = (byte) ( ( (bin[i * 3] & 3) << 4) + ( (bin[i * 3 + 1] & 3) << 2) + (bin[i * 3 + 2] & 3) +
				 		32);
		}
		if (remainder > 0) 
		{
			switch (remainder) 
			{
			case 1:
				bout[i * 4] = (byte) ( ((bin[i * 3] & 0xff) >> 2) + 32);
				bout[i * 4 + 1] = (byte) ( (bin[i * 3] & 3) + 32);
				break;
			case 2:
				bout[i * 4] = (byte) (((bin[i * 3] & 0xff) >> 2) + 32);
				bout[i * 4 + 1] = (byte) (((bin[i * 3 + 1] & 0xff) >> 2) + 32);
				bout[i * 4 + 2] = (byte) ( ( (bin[i * 3] & 3) << 2) + (bin[i * 3 + 1] & 3) + 32);
				break;
			}
		}
		
		int k = bout.length;
		while (k > 0)
		{
			if (bout[--k] != 0x20) break;
			bout[k] = 0x20 + 0x40;
		}
		return bout;
	}
}
