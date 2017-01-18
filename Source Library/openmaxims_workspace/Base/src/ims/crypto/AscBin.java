package ims.crypto;

public class AscBin 
{
	private static final byte[] OLE_SIGN = new byte[]{(byte)0xD0,(byte)0xCF,(byte)0x11,(byte)0xE0,(byte)0xA1,(byte)0xB1,(byte)0x1A,(byte)0xE1};
	private static final byte[] ZIP_SIGN =  new byte[]{(byte)0x1F,(byte)0x8B,(byte)0x08,(byte)0x08};
	
    public static byte[] bin2asc(byte[] bin) {
        int i;
        int len = bin.length;
        int remainder = len % 3;
        int num = (len - remainder) / 3;
        byte[] bout;

        if (remainder > 0) {
            bout = new byte[len + num + 1];
        }
        else {
            bout = new byte[len + num];
        }

        for (i = 0; i < num; i++) {

            bout[i * 4] = (byte) ( ((bin[i * 3] & 0xff) >> 2) + 32);
            bout[i * 4 + 1] = (byte) ( ((bin[i * 3 + 1] & 0xff) >> 2) + 32);
            bout[i * 4 + 2] = (byte) ( ((bin[i * 3 + 2] & 0xff) >> 2) + 32);
            bout[i * 4 +
                3] = (byte) ( ( (bin[i * 3] & 3) << 4) + ( (bin[i * 3 + 1] & 3) << 2) + (bin[i * 3 + 2] & 3) +
                             32);
        }
        if (remainder > 0) {
            switch (remainder) {
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

    public static byte[] asc2bin(byte[] bin) {
        int i;
        int len = bin.length;
        int remainder = len % 4;
        int num = (len - remainder) / 4;
        byte[] bout;
        byte pb;

        if (remainder > 0) {
            bout = new byte[ (num * 3) + remainder - 1];
        }
        else {
            bout = new byte[num * 3];
        }

        for (i = 0; i < num; i++) {
            pb = (byte) (bin[i * 4 + 3] - 32);
            bout[i * 3] = (byte) ( ( (bin[i * 4] - 32) << 2) + ( (pb >> 4) & 3));
            bout[i * 3 + 1] = (byte) ( ( (bin[i * 4 + 1] - 32) << 2) + ( (pb >> 2) & 3));
            bout[i * 3 + 2] = (byte) ( ( (bin[i * 4 + 2] - 32) << 2) + (pb & 3));
        }
        if (remainder > 0) {
            pb = (byte) (bin[i * 4 + remainder - 1] - 32);
            switch (remainder) {
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
    
	public static boolean isZipped(byte[] b)
	{
		if (b.length < ZIP_SIGN.length) return false;
		int i = 0;		
		for (i = 0; i < ZIP_SIGN.length; i++)
		{
			if (ZIP_SIGN[i] != b[i]) return false;
		}
		return true;		
	}
	public static boolean isOle(byte[] b)
	{
		if (b.length < OLE_SIGN.length) return false;
		int i = 0;
		for (i = 0; i < OLE_SIGN.length; i++)
		{
			if (OLE_SIGN[i] != b[i]) return false;
		}
		return true;		
	}
}

