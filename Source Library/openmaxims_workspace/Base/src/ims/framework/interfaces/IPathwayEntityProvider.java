package ims.framework.interfaces;

import ims.framework.utils.DateTime;


public interface IPathwayEntityProvider 
{
	public void createEvent(Integer eventId, String entityName, Integer entityId, Integer patientId, Integer careContextId, DateTime scheduledDateTime, String description);
}
