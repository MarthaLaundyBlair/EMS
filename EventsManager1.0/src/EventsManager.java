import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;

public class EventsManager {

	ArrayList<Event> eventsArrayList = new ArrayList<>();


	/*
	public void writeObjectToFile() {
		try (
				FileOutputStream fos = new FileOutputStream("object.dat");
			     ObjectOutputStream oos = new ObjectOutputStream(fos)) {

			    oos.writeObject(eventsArrayList);

			} catch (IOException ex) {
			    ex.printStackTrace();
			}
	}


	public ArrayList<Event> ReadObjectFromFile() {

        try {

            FileInputStream fileIn = new FileInputStream("object.dat");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            ArrayList<Event> eventsArrayList = (ArrayList<Event>) objectIn.readObject();

            objectIn.close();
            return eventsArrayList;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }*/

	public void addEvent(String name, String location, String date, String time) {

		Event e1  = new Event(name, location, date, time);
		eventsArrayList.add(e1);
		//writeObjectToFile();

	} 
	
	public String searchEvents(String key, String value) {
		StringBuilder sb = new StringBuilder("");
		for (Event event : eventsArrayList) {
			if (key.equals("name") & value.equals(event.getName())) {
				sb.append(event.toString());
				sb.append("\n");
			}
			else if (key.equals("location") & value.equals(event.getLocation())) {
				sb.append(event.toString());
				sb.append("\n");
			}
			/*else if (key.equals("date") & value.equals(event.getDate())) {
				sb.append(event.toString());
				sb.append("\n");
			}
			else if (key.equals("time") & value.equals(event.getTime())) {
				sb.append(event.toString());
				sb.append("\n");
			}*/
			else {
				sb.append("Sorry no events found!");
				sb.append("\n");
			}
		}
		return sb.toString();

	}


	public String listEvents() {
		StringBuilder sb = new StringBuilder("");
		
		for(Event event: eventsArrayList) {
			sb.append(event.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

	

}