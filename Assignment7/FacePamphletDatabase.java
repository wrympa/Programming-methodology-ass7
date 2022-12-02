/*
 * File: FacePamphletDatabase.java
 * -------------------------------
 * This class keeps track of the profiles of all users in the
 * FacePamphlet application.  Note that profile names are case
 * sensitive, so that "ALICE" and "alice" are NOT the same name.
 */

import java.util.*;

public class FacePamphletDatabase implements FacePamphletConstants {

	HashMap<String, FacePamphletProfile> base;//makes hashmap
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the database.
	 */
	public FacePamphletDatabase() {
		// You fill this in
		 base = new HashMap<String,FacePamphletProfile>();//Initialises hashmap
	}
	
	
	/** 
	 * This method adds the given profile to the database.  If the 
	 * name associated with the profile is the same as an existing 
	 * name in the database, the existing profile is replaced by 
	 * the new profile passed in.
	 */
	
	//adds/changes profile based on whether if its already added
	
	public void addProfile(FacePamphletProfile profile) {
		// You fill this in
		if(!containsProfile(profile.getName()))
		{
			base.put(profile.getName(), profile);
		}
		else
		{
			base.replace(profile.getName(), profile);
		}
		
	}

	
	/** 
	 * This method returns the profile associated with the given name 
	 * in the database.  If there is no profile in the database with 
	 * the given name, the method returns null.
	 */
	
	// returns profile if it exists
	
	public FacePamphletProfile getProfile(String name) {
		// You fill this in.  Currently always returns null.
		if(containsProfile(name))
		{
			return base.get(name);
		}
		else
		{
			return null;
		}
	}
	
	
	/** 
	 * This method removes the profile associated with the given name
	 * from the database.  It also updates the list of friends of all
	 * other profiles in the database to make sure that this name is
	 * removed from the list of friends of any other profile.
	 * 
	 * If there is no profile in the database with the given name, then
	 * the database is unchanged after calling this method.
	 */
	
	// deletes profile if it already exists
	
	public void deleteProfile(String name) {
		// You fill this in
		if(containsProfile(name))
		{
			base.remove(name);
		}
	}

	
	/** 
	 * This method returns true if there is a profile in the database 
	 * that has the given name.  It returns false otherwise.
	 */
	
	//returns whether the profile exists
	
	public boolean containsProfile(String name) {
		// You fill this in.  Currently always returns false.
		return base.containsKey(name);
	}

}
