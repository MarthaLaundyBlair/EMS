import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class EventsManager {
	//Events manager contains the buisness logic for the EventsManagement User Interface

	ArrayList<Event> eventsArrayList = new ArrayList <>();
	//ArrayList<Event> eventsArrayList = ReadObjectFromFile();


	//Write Object to "object.da" when user opts to save 
	public void writeObjectToFile() {
		try (
				FileOutputStream fos = new FileOutputStream("object.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {

			oos.writeObject(eventsArrayList);
			oos.close();
			fos.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}


	//Read events from file saving as an array list
	public ArrayList<Event> ReadObjectFromFile() {

		try {

			FileInputStream fis = new FileInputStream("object.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);

			ArrayList<Event> eventsArrayList = (ArrayList<Event>) ois.readObject();

			ois.close();
			fis.close();
			return eventsArrayList;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	// List all events currently saved
	public String listEvents() {
		StringBuilder sb = new StringBuilder("");
		for(Event event: eventsArrayList) {
			sb.append(event.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
	
	// Add an event - only add if name has not been given to another event already
	public String addEvent(String name, String location, String date, String time) {
		// check existing name has not already been taken
		boolean duplicate = false;
		for (Event event: eventsArrayList) {
			if (event.getName().equals(name)) {
				duplicate = true;
				break;
			}
		}
		if (duplicate == false) {
			Event e1 = new Event(name, location, date, time);
			eventsArrayList.add(e1);
			return "Event added sucsessfully!";
		}
		else {
			return "An event with that name already exists!";
		}

	} 

	public Event searchEvents(String value) {
		for (Event event : eventsArrayList) {
			if (value.equals(event.getName())) {
				return event;

			}
		}
		return null;

	}

	//create an array of events corresponding to a specific location, date or name
	public ArrayList<Event> filterEvents(String key, String value) {

		ArrayList<Event> searchedEvents = new ArrayList <>();

		for (Event event : eventsArrayList) {
			if (key.equals("location") & value.equals(event.getLocation())) {
				searchedEvents.add(event);
			}
			else if (key.equals("date") & value.equals(event.getDate())) {
				searchedEvents.add(event);
			}
			else if (key.equals("time") & value.equals(event.getTime())) {
				searchedEvents.add(event);
			}

		}
		return searchedEvents;

	}

	//convert any array of events to a string to print out
	public String listGenerate(ArrayList<Event> listEvents) {
		StringBuilder sb = new StringBuilder("");
		for(Event event: listEvents) {
			sb.append(event.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

	
	//edit event
	public String editEvent(String key, String value, Event eventToEdit) {
		boolean duplicate = false;
		// this checks when changing name that the new name is not already taken
		//names must be unique for searching purposes
		if (key.equals("name")) {
			for (Event event: eventsArrayList) {
				if (event.getName().equals(value) && eventToEdit != event) {
					duplicate = true;
					break;
				}
			}
		}

		if (duplicate == false) {
			eventToEdit.updateEvent(key, value);
			return "Event edited sucsessfully!";
		}
		else {
			return "An event with that name already exists!";
		}

	}

	// delete event
	public void deleteEvent(Event eventToDel) {
		eventsArrayList.remove(eventToDel);
	}
	
	//Check user inputs a date correctly
		public boolean checkDate(String strDate) {
			Boolean valid;
			try {
				LocalDate.parse(strDate);
				valid = true;
			}
			catch (DateTimeParseException e) {
				valid = false;
			}
			return valid;
			
		}
		
		//Check user inputs time correctly
		public boolean checkTime(String strTime) {
			Boolean valid;
			try {
				LocalTime.parse(strTime);
				valid = true;
			}
			catch (DateTimeParseException e) {
				valid = false;
			}
			return valid;
			
		}
	
	


}