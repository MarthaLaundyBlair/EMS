import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EventsManagementSystem extends EventsManager {
	
	static EventsManager Em = new EventsManager();
	
	
	public static void readAndPrint() {
		String line;
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		 
		try {
			
			do {
				System.out.println("What would you like to do now?"); 
				line = br.readLine();
				switch(line) {
				case "add event" :
					EventsManagementSystem.userAddEvent();
					break;
				
				case "list events" :
					EventsManagementSystem.userListEvents();
					break;
				
				case "search events" :
					EventsManagementSystem.userSearchEvents();
				
				case "edit events" :
					EventsManagementSystem.userEditEvent();
					break;
				
				case "delete event" :
					EventsManagementSystem.userDeleteEvent();
					break;

				case "stop" :
					System.out.println("Bye!");
					break;
				
				default:
					System.out.println("Sorry I didnt get that!");
				}
			}
			while (!line.equals("stop"));
		
			
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
			System.out.print(key + " : ");
			String value = br.readLine();
			System.out.print(Em.searchEvents(key, value));
		} catch (IOException e) {
	
			e.printStackTrace();
		}
		
	}

	private static void userDeleteEvent() {
		// TODO Auto-generated method stub
		
	}

	private static void userEditEvent() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("What is the name of the event you would like to edit?");
		
		try {
			String name;
			name = br.readLine();
			System.out.print("Will edit: " + Em.searchEvents("name", name));
			System.out.println("Edit name, location, date or time?");
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
		
		
		EventsManagementSystem.readAndPrint();
	}

}
