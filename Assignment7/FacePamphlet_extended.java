
/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

public class FacePamphlet_extended extends Program implements FacePamphletConstants {
	private JLabel name = new JLabel("Name");
	private static JTextField field = new JTextField(TEXT_FIELD_SIZE);
	private JButton add = new JButton("add");
	private JButton delete = new JButton("delete");
	private JButton lookup = new JButton("lookup");
	private JButton block = new JButton("block");
	private JButton unblock = new JButton("unblock");
	private JTextField changeS = new JTextField(TEXT_FIELD_SIZE);
	private JButton status = new JButton("change status");
	private JTextField changeP = new JTextField(TEXT_FIELD_SIZE);
	private JButton picture = new JButton("change picture");
	private JTextField addF = new JTextField(TEXT_FIELD_SIZE);
	private JButton fren = new JButton("add friend");
	private JTextField delF = new JTextField(TEXT_FIELD_SIZE);
	private JButton frennomor = new JButton("remove friend");
	private JLabel blcklab = new JLabel("block");
	private JTextField blockfield = new JTextField(TEXT_FIELD_SIZE);
	private static FacePamphletCanvas_extended canvas = new FacePamphletCanvas_extended();
	private JLabel filler1 = new JLabel(EMPTY_LABEL_TEXT);
	private JLabel filler2 = new JLabel(EMPTY_LABEL_TEXT);
	private JLabel filler3 = new JLabel(EMPTY_LABEL_TEXT);// makes all interactors
	private static FacePamphletDatabase_extended base = new FacePamphletDatabase_extended();// makes database
	private FacePamphletProfile_extended current;// x is in essence the current/displayed profile

	/**
	 * This method has the responsibility for initializing the interactors in the
	 * application, and taking care of any other initialization that needs to be
	 * performed.
	 */
	public void init() {
		// You fill this in
		add(canvas);
		add(name, NORTH);
		add(field, NORTH);
		add(add, NORTH);
		add(delete, NORTH);
		add(lookup, NORTH);
		add(block, NORTH);
		add(unblock, NORTH);
		add(changeS, WEST);
		add(status, WEST);
		add(filler1, WEST);
		add(changeP, WEST);
		add(picture, WEST);
		add(filler2, WEST);
		add(addF, WEST);
		add(fren, WEST);
		add(filler3, WEST);
		add(delF, WEST);
		add(frennomor, WEST);
		add(blcklab, WEST);
		add(blockfield, WEST);// adds all interactors
		addActionListeners();
	}

	/**
	 * This class is responsible for detecting when the buttons are clicked or
	 * interactors are used, so you will have to add code to respond to these
	 * actions.
	 */
	
	//jumps to a profile from the friends list if theyre clicked on. compromises had to be made and
	//some things were made static :(
	
	public static void jumpto(String S)
	{
		if (base.containsProfile(S)) {
			canvas.displayProfile(base.getProfile(S));
			canvas.showMessage("Displaying " + S);
			field.setText(S);
		} else {
			canvas.showMessage("A profile with that name doesn't exist");
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		// You fill this in as well as add any additional methods

		// if there isnt a profile with the same name(not just nothing)
		// adds profile. otherwise prints appropriate message

		if (e.getSource() == add) {
			if (!base.containsProfile(field.getText())) {
				if (!field.getText().equals("")) {
					int i = field.getText().indexOf(' ');
					if(i!=field.getText().length()-1)
					{
					current = new FacePamphletProfile_extended(field.getText());
					base.addProfile(current);
					canvas.displayProfile(current);
					canvas.showMessage("A new profile has been created.");
					}
					else
					{
						canvas.showMessage("please enter a name without a space at the end");
					}
					} else {
					canvas.showMessage("please enter something");
				}
			} else {
				canvas.showMessage("Such profile already exists");
			}
		}

		// deletes profile if it exists and also removes it from their friends friend
		// lists

		if (e.getSource() == delete) {
			if (base.containsProfile(field.getText())) {
				Iterator<String> iterator = base.getProfile(field.getText()).getFriends();
				while (iterator.hasNext()) {
					String currentfren = iterator.next();
					current = base.getProfile(currentfren);
					current.removeFriend(field.getText());
					base.addProfile(current);
				}
				current = null;
				base.deleteProfile(field.getText());
				canvas.removeAll();
				canvas.showMessage("The profile of " + field.getText() + " has been deleted");
				field.setText(null);
			} else {
				canvas.showMessage("A profile with that name doesn't exist");
			}
		}

		// looks up profile if it exists

		if (e.getSource() == lookup) {
			if (base.containsProfile(field.getText())) {
				canvas.displayProfile(base.getProfile(field.getText()));
				canvas.showMessage("Displaying " + field.getText());
			} else {
				canvas.showMessage("A profile with that name doesn't exist");
			}
		}

		// sets status

		if (e.getSource() == status) {
			if (base.containsProfile(field.getText())) {
				current = base.getProfile(field.getText());
				current.setStatus(changeS.getText());
				base.addProfile(current);
				canvas.displayProfile(base.getProfile(field.getText()));
				canvas.showMessage("The status of " + field.getText() + " has been changed");
				changeS.setText(null);
			} else {
				canvas.showMessage("Such a profile doesn't exist");
			}
		}

		// sets picture. try and catch for misnamed files

		if (e.getSource() == picture) {
			if (base.containsProfile(field.getText())) {
				try {
					GImage pfp = new GImage("./images/" + changeP.getText() + ".jpg");
					current.setImage(pfp);
					base.addProfile(current);
					canvas.displayProfile(base.getProfile(field.getText()));
					canvas.showMessage("The profile picture of " + field.getText() + " has been set");
					changeP.setText(null);
				} catch (Exception x) {
					canvas.showMessage("theres no such image");
				}
			} else {
				canvas.showMessage("Such a profile doesn't exist");
			}
		}

		// adds friends if both exist

		if (e.getSource() == fren) {
			if (base.containsProfile(addF.getText()) && base.containsProfile(field.getText())) {
				if (!addF.getText().equals(field.getText())) {
					current = base.getProfile(field.getText());
					if (!current.addFriend(addF.getText())) {
						canvas.showMessage("already a friend");
					} else {
						if (!current.blockd(addF.getText())) {
							canvas.showMessage( current.getName() + " has " + addF.getText() + " blocked");
						} else {
							current.addFriend(addF.getText());
							base.addProfile(current);
							current = base.getProfile(addF.getText());
							current.addFriend(field.getText());
							base.addProfile(current);
							current = base.getProfile(field.getText());
							canvas.showMessage(addF.getText() + " has been befriended");
							canvas.displayProfile(base.getProfile(field.getText()));
							current = base.getProfile(field.getText());
							addF.setText(null);
						}
					}
				} else {
					canvas.showMessage("cant add yourself as a friend");
				}
			} else {
				canvas.showMessage("Such a person doesn't exist");
			}
		}

		// removes friend if they're friends already(without deleting the profile)

		if (e.getSource() == frennomor) {
			if (base.containsProfile(delF.getText()) && base.containsProfile(field.getText())) {
				if (!delF.getText().equals(field.getText())) {
					current = base.getProfile(field.getText());
					if (!current.addFriend(delF.getText())) {
						current.removeFriend(delF.getText());
						base.addProfile(current);
						current = base.getProfile(delF.getText());
						current.removeFriend(field.getText());
						base.addProfile(current);
						current = base.getProfile(field.getText());
						canvas.displayProfile(base.getProfile(field.getText()));
						current = base.getProfile(field.getText());
						canvas.showMessage(delF.getText() + " has been unfriended :(");
						delF.setText(null);
					} else {
						canvas.showMessage(field.getText() + " isn't even a friend of " + delF.getText());
					}
				} else {
					canvas.showMessage("cant add yourself as a friend i.e. cant unfriend either");
				}
			} else {
				canvas.showMessage("Such a person doesn't exist");
			}
		}

		//block profile and removes them friends
		
		if (e.getSource() == block) {
			if (base.containsProfile(blockfield.getText()) && base.containsProfile(field.getText())) {
				if (!blockfield.getText().equals(field.getText())) {
					current = base.getProfile(field.getText());
					if (!current.block(blockfield.getText())) {
						canvas.showMessage("already blocked");
					} else {
						current.block(blockfield.getText());
						if (!current.addFriend(blockfield.getText())) {
							current.removeFriend(blockfield.getText());
						}
						base.addProfile(current);
						current = base.getProfile(blockfield.getText());
						if (!current.addFriend(field.getText())) {
							current.removeFriend(field.getText());
						}
						base.addProfile(current);
						current = base.getProfile(field.getText());
						canvas.displayProfile(current);
						canvas.showMessage(blockfield.getText() + " has been blocked :(");
						blockfield.setText(null);
					}
				} else {
					canvas.showMessage("cant block yourself");
				}
			} else {
				canvas.showMessage("Such a person doesn't exist");
			}
		}
		
		//unblock person if theyre already blocked
		
		if (e.getSource() == unblock) {
			if (base.containsProfile(blockfield.getText()) && base.containsProfile(field.getText())) {
				if (!blockfield.getText().equals(field.getText())) {
					current = base.getProfile(field.getText());
					if (current.block(blockfield.getText())) {
						canvas.showMessage("not even blocked yet");
					} else {
						current.unblock(blockfield.getText());
						base.addProfile(current);
						canvas.displayProfile(base.getProfile(field.getText()));
						canvas.showMessage(blockfield.getText() + " has been unblocked :)");
						blockfield.setText(null);
					}
				} else {
					canvas.showMessage("cant unblock yourself");
				}
			} else {
				canvas.showMessage("Such a person doesn't exist");
			}
		}
	}

}
