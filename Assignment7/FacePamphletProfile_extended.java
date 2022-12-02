
/*
 * File: FacePamphletProfile.java
 * ------------------------------
 * This class keeps track of all the information for one profile
 * in the FacePamphlet social network.  Each profile contains a
 * name, an image (which may not always be set), a status (what 
 * the person is currently doing, which may not always be set),
 * and a list of friends.
 */

import acm.graphics.*;
import java.util.*;

public class FacePamphletProfile_extended implements FacePamphletConstants {
	private String fullname;
	private String stat;
	private GImage face;
	private ArrayList<String> friends = new ArrayList<>();
	private ArrayList<String> blacklist = new ArrayList<>();//makes all variables for profile

	/**
	 * Constructor This method takes care of any initialization needed for the
	 * profile.
	 */
	public FacePamphletProfile_extended(String name) {
		// You fill this in
		if (name != null) {
			fullname = name;//sets fullname as the current name
		}
	}

	/** This method returns the name associated with the profile. */
	
	//just returns the name
	
	public String getName() {
		// You fill this in. Currently always returns the empty string.
		return fullname;
	}

	/**
	 * This method returns the image associated with the profile. If there is no
	 * image associated with the profile, the method returns null.
	 */
	
	//returns face
	
	public GImage getImage() {
		// You fill this in. Currently always returns null.
		return face;
	}

	/** This method sets the image associated with the profile. */
	
	//sets face gimage as entered gimage
	
	public void setImage(GImage image) {
		// You fill this in
		face = image;
	}

	/**
	 * This method returns the status associated with the profile. If there is no
	 * status associated with the profile, the method returns the empty string ("").
	 */
	
	//returns status string 
	
	public String getStatus() {
		// You fill this in. Currently always returns the empty string.
		return stat;
	}

	/** This method sets the status associated with the profile. */
	
	//sets status
	
	public void setStatus(String status) {
		// You fill this in
		stat = status;
	}

	/**
	 * This method adds the named friend to this profile's list of friends. It
	 * returns true if the friend's name was not already in the list of friends for
	 * this profile (and the name is added to the list). The method returns false if
	 * the given friend name was already in the list of friends for this profile (in
	 * which case, the given friend name is not added to the list of friends a
	 * second time.)
	 */
	public boolean addFriend(String friend) {
		// You fill this in. Currently always returns true.
		//if already friend, returns false and doesnt add them again
		if (friends.contains(friend)) {
			return false;
		} else {
			//else adds the as a friend and returns true
			friends.add(friend);
			return true;
		}
	}

	/**
	 * This method removes the named friend from this profile's list of friends. It
	 * returns true if the friend's name was in the list of friends for this profile
	 * (and the name was removed from the list). The method returns false if the
	 * given friend name was not in the list of friends for this profile (in which
	 * case, the given friend name could not be removed.)
	 */
	public boolean removeFriend(String friend) {
		// You fill this in. Currently always returns false.
		//removes string from friends if theyre already there
		if (friends.contains(friend)) {
			friends.remove(friend);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method returns an iterator over the list of friends associated with the
	 * profile.
	 */
	
	public Iterator<String> getFriends() {
		// You fill this in. Currently always returns null.
		//makes iterator
		return friends.iterator();
	}
	

	//works the same way as addfriend unfriend and getfriends. boolean for safe measure
	
	public boolean unblock(String friend) {
		// You fill this in. Currently always returns false.
		//removes string from friends if theyre already there
		if (blacklist.contains(friend)) {
			blacklist.remove(friend);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean block(String friend) {
		// You fill this in. Currently always returns true.
		//if already friend, returns false and doesnt add them again
		if (blacklist.contains(friend)) {
			return false;
		} else {
			//else adds the as a friend and returns true
			blacklist.add(friend);
			return true;
		}
	}
	
	public boolean blockd(String friend)
	{
		if (blacklist.contains(friend)) {
			return false;
		}  else {
			return true;
		}
	}

	public Iterator<String> getBlockeds() {
		// You fill this in. Currently always returns null.
		//makes iterator
		return blacklist.iterator();
	}
	
	/**
	 * This method returns a string representation of the profile. This string is of
	 * the form: "name (status): list of friends", where name and status are set
	 * accordingly and the list of friends is a comma separated list of the names of
	 * all of the friends in this profile.
	 * 
	 * For example, in a profile with name "Alice" whose status is "coding" and who
	 * has friends Don, Chelsea, and Bob, this method would return the string:
	 * "Alice (coding): Don, Chelsea, Bob"
	 */
	public String toString() {
		return "";
		// You fill this in. Currently always returns the empty string.
	}

}
