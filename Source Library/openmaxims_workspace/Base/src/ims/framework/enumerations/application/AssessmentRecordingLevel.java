package ims.framework.enumerations.application;

import java.io.Serializable;

public final class AssessmentRecordingLevel implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int id;
	private String text;
	
	public int getId()
	{
		return id;
	}
	public String getText()
	{
		return text;
	}
	
	@Override
	public String toString()
	{
		return text;
	}
	
   	public static final AssessmentRecordingLevel PATIENT = new AssessmentRecordingLevel(1, "Patient");
    public static final AssessmentRecordingLevel CARECONTEXT = new AssessmentRecordingLevel(2, "Care Context");
	public static final AssessmentRecordingLevel EPISODEOFCARE = new AssessmentRecordingLevel(3, "Episode of Care");
	
	public static AssessmentRecordingLevel[] AllInstances = new AssessmentRecordingLevel[] { PATIENT, CARECONTEXT, EPISODEOFCARE };
    
    private AssessmentRecordingLevel(int id, String text)
    {
        this.id = id;
        this.text = text;
    }
    
    public static AssessmentRecordingLevel parse(int id)
    {
    	for(int x = 0; x < AllInstances.length; x++)
    	{
    		if(AllInstances[x].id == id)
    			return AllInstances[x];
    	}
    	
    	return null;
    }
}
