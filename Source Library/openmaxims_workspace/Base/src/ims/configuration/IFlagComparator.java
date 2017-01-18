package ims.configuration;

import ims.framework.enumerations.SortOrder;
import java.util.Comparator;

public class IFlagComparator implements Comparator
{
	private int direction = 1;
	private boolean caseInsensitive = true;
	public IFlagComparator()
	{
		this(SortOrder.ASCENDING);
	}
	public IFlagComparator(SortOrder order)
	{
		if (order == SortOrder.DESCENDING)
		{
			direction = -1;
		}
	}
	public IFlagComparator(SortOrder order, boolean caseInsensitive)
	{
		if (order == SortOrder.DESCENDING)
		{
			direction = -1;
		}
		this.caseInsensitive = caseInsensitive;
	}
	public int compare(Object obj1, Object obj2)
	{
		AFlag voObj1 = (AFlag)obj1;
		AFlag voObj2 = (AFlag)obj2;
		return direction*(voObj1.compareTo(voObj2, this.caseInsensitive));
	}
}
