import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class EventsManagementUI{
	
	//The Events Management User Interface deals with the input and output interactions with a user
	// The buisness logic required to actually fulfil the user requests is dealt with using Events Manager

	//Create a new events manager to deal with user requests
	static EventsManager Em = new EventsManager();

	//create the menu from which users can interact with the program
	public static void displayMenu() {
		String line;

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);


		try {

			do {
				System.out.println("");
				System.out.println("What would you like to do now?"); 
				System.out.println("Options:");
				System.out.println(" - add event");
				System.out.println(" - list events");
				System.out.println(" - search events   // search for an event to view and acsess its attendees");
				System.out.println(" - edit event");
				System.out.println(" - delete event ");
				System.out.println(" - filter events ");
				System.out.println(" - save");
				line = br.readLine();
				switch(line) {
				//each command will call a method prompting the user for any additional details
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

				case "filter events" :
					EventsManagementUI.userFilterEvents();
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

	
	// add a user event
	private static void userAddEvent() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("What event would you like to add?");

		try {
			// collect details of new event
			System.out.print("Name : ");
			String name = br.readLine();
			System.out.print("Location : ");
			String location = br.readLine();
			System.out.print("Date (yyyy-mm-dd) : ");
			String date = br.readLine();
			System.out.print("Time (hh:mm) : ");
			String time = br.readLine();
			
			if (Em.checkDate(date) == true & Em.checkTime(time) == true) {
				//Event Manager carries out the buisness logic
				System.out.println(Em.addEvent(name, location, date, time));
			}
			else {
				System.out.println("Date or time incorrectly formatted!");
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	// list all events that share a given location, date or time
	private static void userFilterEvents() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Filter events by location, date or time?");

		try {
			String key = br.readLine();
			if (key.equals("name") || key.equals("location") || key.equals("date") || key.equals("time")) {
				System.out.print(key + " : ");
				String value = br.readLine();
				//Event Manager carries out the buisness logic
				ArrayList<Event> events = Em.filterEvents(key, value);

				if (events.size() != 0) {
					//Event Manager carries out the buisness logic
					System.out.println(Em.listGenerate(events));
				}

				else {
					System.out.println("No events found!");
				}
			}
			else {
				System.out.println("Search key not found!");
			}

		} catch (IOException e) { 

			e.printStackTrace();
		}

	}

	// search for an event by name
	// have ensured that all events have unique names
	private static void userSearchEvents() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Search event by name:");
		System.out.print("name : ");
		try {
			String value = br.readLine();
			//Event Manager carries out the buisness logic
			Event event = Em.searchEvents(value);
			if (event != null) {
				System.out.println(event);
				AttendeesManagementUI AM = new AttendeesManagementUI();
				AM.displayMenu(event);

			}
			else {
				System.out.println("Event not found!");
			}

		} catch (IOException e) { 

			e.printStackTrace();
		}

	}
	
	// delete a user event by name - all names are unique
	private static void userDeleteEvent() {
		String name;
		Event eventToDel;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("What is the name of the event you would like to delete?");

		try {
			name = br.readLine();
			//Event Manager carries out the buisness logic
			eventToDel = Em.searchEvents(name);
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
	
	//edit event
		//search for event by unique name
		private static void userEditEvent() {
			String name;
			String key;
			String newValue;
			Event eventToEdit;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("What is the name of the event you would like to edit?");

			try {
				name = br.readLine();
				//Event Manager Em contains buisness logic
				eventToEdit = Em.searchEvents(name);
				//check event with that name exists
				if (eventToEdit != null) {
					System.out.println("Will edit: " + eventToEdit);
					System.out.println("Edit name,location, date or time?");
					key = br.readLine();
					//check correct key is given
					if (key.equals("name") || key.equals("location") || key.equals("date") || key.equals("time")) {
						System.out.print("New vaue for " + key + " is: ");
						newValue = br.readLine();
						//check new date format okay
						if (key.equals("date") & Em.checkDate(newValue) == false) {
							System.out.println("Issue with date formatting!");
						}
						//check new time format okay
						else if (key.equals("time") & Em.checkTime(newValue) == true) {
							System.out.println("Issue with time formatting!");
						}
						else {
							System.out.println(Em.editEvent(key, newValue, eventToEdit));
							System.out.println(eventToEdit);
						}
					}
					else {
						System.out.println("Search key not found!");
					}
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


		EventsManagementUI.displayMenu();
	}

}
