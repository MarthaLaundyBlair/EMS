import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// need to deal with issues regarding duplicate events 
//need to make sure only accepts correct time and date format!!!!

public class EventsManagementUI extends EventsManager {

	static EventsManager Em = new EventsManager();


	public static void readAndPrint() {
		String line;

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);


		try {

			do {
				System.out.println("What would you like to do now?"); 
				System.out.println("Options:");
				System.out.println(" - add event");
				System.out.println(" - list events");
				System.out.println(" - search events");
				System.out.println(" - edit event");
				System.out.println(" - delete event");
				System.out.println(" - save");
				line = br.readLine();
				switch(line) {
				case "add event" :
					EventsManagementUI.userAddEvent();
					break;

				case "list events" :
					EventsManagementUI.userListEvents();
					break;

				case "search events" :
					EventsManagementUI.userSearchEvents();
					break;

				case "edit event" :
					EventsManagementUI.userEditEvent();
					break;

				case "delete event" :
					EventsManagementUI.userDeleteEvent();
					break;

				case "save" :
					Em.writeObjectToFile();
					System.out.println("Bye!");
					System.exit(0);
					break;

				default:
					System.out.println("Sorry I didnt get that!");
				}
			}
			while (!line.equals("save"));


		} catch (IOException e) {

			e.printStackTrace();
		} 
	}


	public static void userAddEvent() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("What event would you like to add?");

		try {
			System.out.print("Name : ");
			String name = br.readLine();
			System.out.print("Location : ");
			String location = br.readLine();
			System.out.print("Date (yyyy-mm-dd) : ");
			String date = br.readLine();
			System.out.print("Time (hh:mm) : ");
			String time = br.readLine();

			Em.addEvent(name, location, date, time);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private static void userSearchEvents() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Search event by name, location, date or time?");

		try {
			String key = br.readLine();
			if (key.equals("name") || key.equals("location") || key.equals("date") || key.equals("time")) {
				System.out.print(key + " : ");
				String value = br.readLine();
				Event event = Em.searchEvents(key, value);
				if (event != null) {
					System.out.println(event);
					AttendeesManagementUI AM = new AttendeesManagementUI();
					AM.displayMenu(event);

				}
				else {
					System.out.println("Search key not found!");
				}
			}
			else {
				System.out.println("Event not found!");
			}




		} catch (IOException e) { 

			e.printStackTrace();
		}

	}

	private static void userDeleteEvent() {
		String name;
		Event eventToDel;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("What is the name of the event you would like to delete?");

		try {
			name = br.readLine();
			eventToDel = Em.searchEvents("name", name);
			if (eventToDel != null) {
				System.out.print("Will delete: " + eventToDel);
				Em.deleteEvent(eventToDel);
				System.out.println("Event deleted!");
			}
			else {
				System.out.println("No event found!");
			}

		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void userEditEvent() {
		String name;
		String key;
		String new_value;
		Event eventToEdit;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("What is the name of the event you would like to edit?");

		try {
			name = br.readLine();
			eventToEdit = Em.searchEvents("name", name);
			if (eventToEdit != null) {
				System.out.println("Will edit: " + eventToEdit);
				System.out.println("Edit name,location, date or time?");
				key = br.readLine();
				System.out.print("New vaue for " + key + " is: ");
				new_value = br.readLine();
				Em.editEvent(key, new_value, eventToEdit);
				System.out.println("Event updated!");
				System.out.println(eventToEdit);
			}
			else {
				System.out.println("No event found!");
			}

		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void userListEvents() {
		System.out.println("The events are as follows: ");
		System.out.println(Em.listEvents());


	}

	public static void main(String[] args) {


		EventsManagementUI.readAndPrint();
	}

}
