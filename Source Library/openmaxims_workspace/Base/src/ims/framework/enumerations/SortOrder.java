package ims.framework.enumerations;

import java.io.Serializable;

/**
 * @author mchashchin
 */
public class SortOrder implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public static final SortOrder NONE = new SortOrder(1);
	public static final SortOrder ASCENDING = new SortOrder(2);
	public static final SortOrder DESCENDING = new SortOrder(3);

	private SortOrder(int value)
	{
		this.id = value;
	}

	protected int id;
}
