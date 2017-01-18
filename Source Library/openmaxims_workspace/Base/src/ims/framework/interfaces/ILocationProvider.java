package ims.framework.interfaces;

public interface ILocationProvider 
{
	/**
	 * Validates an existing location.
	 * @param ILocation interface
	 * @return The same location if the location exists (maybe with a different name if renamed)
	 * or null if location has been removed or inactivated.
	 */	
	ILocation validateLocation(ILocation location);	
	/**
	 * Returns true if there is at least one active location
	 * @return
	 */
	boolean hasLocations(IAppUser user);	
	/**
	 * Returns true if the user has the right to login from that location
	 * @return
	 */	
	boolean locationIsAllowed(ILocation location, IAppUser user);
	/**
	 * Returns true if the logged user must have at least a location selected
	 * @return
	 */
	boolean shouldSelectLocation(IAppUser user);	
	/**
	 * Returns a list of configured location for the user
	 * @return
	 */
	ILocation[] listConfiguredUserLocations(IAppUser user);	
}
