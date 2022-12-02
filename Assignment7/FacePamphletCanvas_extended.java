
/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */

import acm.graphics.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

public class FacePamphletCanvas_extended extends GCanvas implements FacePamphletConstants {
	private int y = 0;
	private int yb = 0;
	private boolean passed = false;
	private GLabel message;
	private GLabel connected;
	private GLabel blocked;
	private GLabel Status;
	private GLabel header;
	private GLabel Name;
	private GLabel blckd;
	private GLabel KNULL;

	/**
	 * Constructor This method takes care of any initialization needed for the
	 * display
	 */
	public FacePamphletCanvas_extended() {
		// You fill this in

		//looks up the clicked profile
		
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
					GLabel cur = (GLabel) getElementAt(e.getX(),e.getY());
					if(cur!=null || cur!=Status || cur!= header|| cur!=Name|| cur!=blckd||cur!=KNULL)
					{
					FacePamphlet_extended.jumpto(cur.getLabel());
					}
					}
		});
		
	}

	/**
	 * This method displays a message string near the bottom of the canvas. Every
	 * time this method is called, the previously displayed message (if any) is
	 * replaced by the new message text passed in.
	 */
	public void showMessage(String msg) {
		// You fill this in
		if (passed == true) {
			remove(message);
		}
		message = new GLabel(msg);
		message.setFont(MESSAGE_FONT);
		add(message, getWidth() / 2 - message.getWidth() / 2, getHeight() - BOTTOM_MESSAGE_MARGIN);
		passed = true;
	}

	/**
	 * This method displays the given profile on the canvas. The canvas is first
	 * cleared of all existing items (including messages displayed near the bottom
	 * of the screen) and then the given profile is displayed. The profile display
	 * includes the name of the user from the profile, the corresponding image (or
	 * an indication that an image does not exist), the status of the user, and a
	 * list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile_extended profile) {
//		 You fill this in
		removeAll();
		y = 0;
		yb = 0;
		GLabel Name = new GLabel(profile.getName());
		Name.setFont(PROFILE_NAME_FONT);
		Name.setColor(Color.BLUE);
		add(Name, LEFT_MARGIN, TOP_MARGIN + Name.getHeight());

		// draws name

		GImage dp = profile.getImage();
		if (dp != null) {
			dp.scale(IMAGE_WIDTH / dp.getWidth(), IMAGE_HEIGHT / dp.getHeight());
			add(dp, LEFT_MARGIN, IMAGE_MARGIN + TOP_MARGIN + Name.getHeight());
		} else {
			GRect empty = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);
			add(empty, LEFT_MARGIN, 2 * TOP_MARGIN + Name.getHeight());
			GLabel KNULL = new GLabel("NO IMAGE");
			KNULL.setFont(PROFILE_IMAGE_FONT);
			add(KNULL, LEFT_MARGIN + IMAGE_WIDTH / 2 - KNULL.getWidth() / 2,
					TOP_MARGIN + KNULL.getHeight() + IMAGE_HEIGHT / 2 + IMAGE_MARGIN);

		}

		// draws image or rect with no image text if none is given

		GLabel Status = new GLabel(profile.getName() + " is " + profile.getStatus());
		if (profile.getStatus() == null) {
			Status = new GLabel("No Status :/");
		}
		Status.setFont(PROFILE_STATUS_FONT);
		add(Status, LEFT_MARGIN, IMAGE_MARGIN + TOP_MARGIN + STATUS_MARGIN + Name.getHeight() + IMAGE_HEIGHT);

		// adds status

		GLabel header = new GLabel("Friends:");
		header.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(header, getWidth() / 2, TOP_MARGIN + IMAGE_MARGIN + Name.getHeight());
		Iterator<String> iterator = profile.getFriends();// makes local iterator since otherwise it loops
		while (iterator.hasNext()) {
			String aqc = iterator.next();
			System.out.println(aqc);
			connected = new GLabel(aqc);
			connected.setFont(PROFILE_FRIEND_FONT);
			add(connected, getWidth() / 2, TOP_MARGIN + IMAGE_MARGIN + Name.getHeight() + header.getHeight() + y);
			y += connected.getHeight();
		}

		// adds friends

		GLabel blckd = new GLabel("Blocked:");
		blckd.setFont(PROFILE_FRIEND_LABEL_FONT);
		blckd.setColor(Color.RED);
		add(blckd, 3 * getWidth() / 4, TOP_MARGIN + IMAGE_MARGIN + Name.getHeight());
		Iterator<String> blockterator = profile.getBlockeds();// makes local iterator since otherwise it loops
		while (blockterator.hasNext()) {
			String aqc = blockterator.next();
			System.out.println(aqc);
			blocked = new GLabel(aqc);
			blocked.setColor(Color.RED);
			blocked.setFont(PROFILE_FRIEND_FONT);
			add(blocked, 3 * getWidth() / 4, TOP_MARGIN + IMAGE_MARGIN + Name.getHeight() + header.getHeight() + yb);
			yb += blocked.getHeight();
		}
		// add blockds
	}
}
