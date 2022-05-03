import java.io.Serializable;
import java.util.ArrayList;


public class Event implements Serializable{

	private String name;
	private String location;
	private String date; // "2016-06-12" format
	private String time; // "06:30" format
	private ArrayList<Attendee> attendees = new ArrayList <>();

	//Constructor

	public Event(String name, String location, String date, String time) {
		this.name = name;
		this.location = location;
		this.date = date;
		this.time = time;
	}

	//To String

	public String toString() {
		return "name = " + name + ", location = " + location + ", date = " + date + ", time = " + time;
	}

	//Getters

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}	

	//Update Event

	public void updateEvent(String field, String new_value) {
		switch(field) {
		case "name" :
			this.name = new_value;
			break;
		case "location" :
			this.location = new_value;
			break;
		case "date" :
			this.date = new_value;
			break;
		case "time" :
			this.time = new_value;
			break;
		}
	}
	
	// Search Attendees
		public Attendee searchAttendee(String first, String last) {

			for (Attendee attendee : attendees) {

				if (first.equals(attendee.getFirstName()) & last.equals(attendee.getLastName())) {
					return attendee;	
				}
			}
			return null;
		}

	// Add Attendee

	public void addAttendee(String first, String last, String email, String number) {
		Attendee attendeeToAdd = new Attendee(first, last, email, number);
		attendees.add(attendeeToAdd);
	}

	// Delete Attendee
	public void deleteAttendee(Attendee attendeeToDel) {

		attendees.remove(attendeeToDel);

	}

	// List Attendees
	public String listAttendees() {
		StringBuilder sb = new StringBuilder("");
		sb.append("Attendees:");
		sb.append("\n");
		for(Attendee attendee: attendees) {
			sb.append(attendee.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
	

}
