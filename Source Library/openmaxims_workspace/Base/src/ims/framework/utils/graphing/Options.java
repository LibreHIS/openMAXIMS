package ims.framework.utils.graphing;

import java.io.Serializable;

import ims.base.interfaces.IModifiable;

public class Options implements IModifiable, Serializable
{
	private static final long serialVersionUID = 1L;
	
	public int getAdditionalPercent()
	{
		return 3;
	}
	public int getMinimumXRangeAddition()
	{
		return 10;
	}
    public class Pulse implements IModifiable
    {
        public boolean getDisplayRadial()
        {
            return this.displayRadial;
        }
        public void setDisplayRadial(boolean value)
        {
        	if(!this.dataWasChanged)
        		this.dataWasChanged = this.displayRadial != value;
        	
            this.displayRadial = value;
        }
        public boolean getDisplayApex()
        {
            return this.displayApex;
        }
        public void setDisplayApex(boolean value)
        {
        	if(!this.dataWasChanged)
        		this.dataWasChanged = this.displayApex != value;
        	
            this.displayApex = value;
        }  
        public boolean wasChanged() 
    	{
    		return this.dataWasChanged;
    	}
    	public void markUnchanged() 
    	{		
    		this.dataWasChanged = false;
    	}
        
    	private boolean dataWasChanged = true;
        private boolean displayRadial = true;
        private boolean displayApex = true;
    }
    public class BloodSugar implements IModifiable
    {
    	public boolean getDisplayPreValue()
        {
            return this.displayPreValue;
        }
        public void setDisplayPreValue(boolean value)
        {
        	if(!this.dataWasChanged)
        		this.dataWasChanged = this.displayPreValue != value;
        	
            this.displayPreValue = value;
        }
        public boolean getDisplayValue()
        {
            return this.displayValue;
        }
        public void setDisplayValue(boolean value)
        {
        	if(!this.dataWasChanged)
        		this.dataWasChanged = this.displayValue != value;
        	
            this.displayValue = value;
        }
        public boolean getDisplayPostValue()
        {
            return this.displayPostValue;
        }
        public void setDisplayPostValue(boolean value)
        {
        	if(!this.dataWasChanged)
        		this.dataWasChanged = this.displayPostValue != value;
        	
            this.displayPostValue = value;
        }   
        public boolean wasChanged() 
    	{
    		return this.dataWasChanged;
    	}
    	public void markUnchanged() 
    	{		
    		this.dataWasChanged = false;
    	}
        
    	private boolean dataWasChanged = true;
    	private boolean displayPreValue = true;
        private boolean displayValue = true;
        private boolean displayPostValue = true;
    }
    public class PeakFlow implements IModifiable
    {
        public boolean getDisplayPreValue()
        {
            return this.displayPreValue;
        }
        public void setDisplayPreValue(boolean value)
        {
        	if(!this.dataWasChanged)
        		this.dataWasChanged = this.displayPreValue != value;
        	
            this.displayPreValue = value;
        }
        public boolean getDisplayPostValue()
        {
            return this.displayPostValue;
        }
        public void setDisplayPostValue(boolean value)
        {
        	if(!this.dataWasChanged)
        		this.dataWasChanged = this.displayPostValue != value;
        	
            this.displayPostValue = value;
        }  
        public boolean wasChanged() 
    	{
    		return this.dataWasChanged;
    	}
    	public void markUnchanged() 
    	{		
    		this.dataWasChanged = false;
    	}
        
    	private boolean dataWasChanged = true;        
        private boolean displayPreValue = true;
        private boolean displayPostValue = true;
    }
	public class Pupils implements IModifiable
	{
		public boolean getDisplayLeft()
		{
			return this.displayLeft;
		}
		public void setDisplayLeft(boolean value)
		{
			if(!this.dataWasChanged)
        		this.dataWasChanged = this.displayLeft != value;
			
			this.displayLeft = value;
		}
		public boolean getDisplayRight()
		{
			return this.displayRight;
		}
		public void setDisplayRight(boolean value)
		{
			if(!this.dataWasChanged)
        		this.dataWasChanged = this.displayRight != value;
			
			this.displayRight = value;
		}
		public boolean wasChanged() 
	    {
	    	return this.dataWasChanged;
	    }
	    public void markUnchanged() 
	    {		
	    	this.dataWasChanged = false;
	    }
	        
	    private boolean dataWasChanged = true;				
		private boolean displayLeft = true;
		private boolean displayRight = true;
	}
	public class BloodPressure implements IModifiable
	{
		public boolean getDisplayStanding()
		{
			return this.displayStanding;
		}
		public void setDisplayStanding(boolean value)
		{
			if(!this.dataWasChanged)
        		this.dataWasChanged = this.displayStanding != value;
			
			this.displayStanding = value;
		}
		public boolean getDisplaySitting()
		{
			return this.displaySitting;
		}
		public void setDisplaySitting(boolean value)
		{
			if(!this.dataWasChanged)
        		this.dataWasChanged = this.displaySitting != value;
			
			this.displaySitting = value;
		}
		public boolean getDisplayLying()
		{
			return this.displayLying;
		}
		public void setDisplayLying(boolean value)
		{
			if(!this.dataWasChanged)
        		this.dataWasChanged = this.displayLying != value;
			
			this.displayLying = value;
		}
		public boolean wasChanged() 
	    {
	    	return this.dataWasChanged;
	    }
	    public void markUnchanged() 
	    {		
	    	this.dataWasChanged = false;
	    }
	        
	    private boolean dataWasChanged = true;
		private boolean displayStanding = true;
		private boolean displaySitting = true;
		private boolean displayLying = true;
	}
	public class Metrics implements IModifiable
	{
		public boolean getDisplayHeight()
		{
			return this.displayHeight;
		}
		public void setDisplayHeight(boolean value)
		{
			if(!this.dataWasChanged)
        		this.dataWasChanged = this.displayHeight != value;
			
			this.displayHeight = value;
		}
		public boolean getDisplayWeight()
		{
			return this.displayWeight;
		}
		public void setDisplayWeight(boolean value)
		{
			if(!this.dataWasChanged)
        		this.dataWasChanged = this.displayWeight != value;
			
			this.displayWeight = value;
		}
		public boolean getDisplayBMI()
		{
			return this.displayBMI;
		}
		public void setDisplayBMI(boolean value)
		{
			if(!this.dataWasChanged)
        		this.dataWasChanged = this.displayBMI != value;
			
			this.displayBMI = value;
		}
		public boolean wasChanged() 
	    {
	    	return this.dataWasChanged;
	    }
	    public void markUnchanged() 
	    {		
	    	this.dataWasChanged = false;
	    }
	        
	    private boolean dataWasChanged = true;
		private boolean displayHeight = false;
		private boolean displayWeight = false;
		private boolean displayBMI = true;
	}
	public class GlasgowComaScale implements IModifiable
	{
		public boolean getDisplayVerbal()
		{
			return this.displayVerbal;
		}
		public void setDisplayVerbal(boolean value)
		{
			if(!this.dataWasChanged)
        		this.dataWasChanged = this.displayVerbal != value;
			
			this.displayVerbal = value;
		}
		public boolean getDisplayMotor()
		{
			return this.displayMotor;
		}
		public void setDisplayMotor(boolean value)
		{
			if(!this.dataWasChanged)
        		this.dataWasChanged = this.displayMotor != value;
			
			this.displayMotor = value;
		}
		public boolean getDisplayEye()
		{
			return this.displayEye;
		}
		public void setDisplayEye(boolean value)
		{
			if(!this.dataWasChanged)
        		this.dataWasChanged = this.displayEye != value;
			
			this.displayEye = value;
		}
		public boolean getDisplayTotal()
		{
			return this.displayTotal;
		}
		public void setDisplayTotal(boolean value)
		{
			if(!this.dataWasChanged)
        		this.dataWasChanged = this.displayTotal != value;
			
			this.displayTotal = value;
		}
		public boolean wasChanged() 
	    {
	    	return this.dataWasChanged;
	    }
	    public void markUnchanged() 
	    {		
	    	this.dataWasChanged = false;
	    }
	        
	    private boolean dataWasChanged = true;
		private boolean displayVerbal = false;
		private boolean displayMotor = false;
		private boolean displayEye = false;
		private boolean displayTotal = true;
	}
	public boolean wasChanged() 
	{
		if(this.pulse.wasChanged())
			return true;
		if(this.peakFlow.wasChanged())
			return true;
		if(this.pupils.wasChanged())
			return true;
		if(this.bloodPressure.wasChanged())
			return true;
		if(this.metrics.wasChanged())
			return true;
		if(this.glasgowComaScale.wasChanged())
			return true;
		
		return false;
	}
	public void markUnchanged() 
	{		
		this.pulse.markUnchanged();
		this.peakFlow.markUnchanged();
		this.pupils.markUnchanged();
		this.bloodPressure.markUnchanged();
		this.metrics.markUnchanged();
		this.glasgowComaScale.markUnchanged();
	}
	
    public Pulse pulse = new Pulse();
    public BloodSugar bloodSugar = new BloodSugar();
    public PeakFlow peakFlow = new PeakFlow();
	public Pupils pupils = new Pupils();
	public BloodPressure bloodPressure = new BloodPressure();
	public Metrics metrics = new Metrics();
	public GlasgowComaScale glasgowComaScale = new GlasgowComaScale();
}
