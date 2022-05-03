import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AttendeesManagementUI {

	public AttendeesManagementUI() { 

	}

	public void displayMenu(Event event){
		String line;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);


		try {

			do {

				System.out.println("Options");
				System.out.println(" - show attendees");
				System.out.println(" - add attendee");
				System.out.println(" - delete attendee");
				System.out.println(" - exit");
				line = br.readLine();

				switch(line) {
				case "show attendees" :
					AttendeesManagementUI.userShowAttendees(event);
					break;

				case "add attendee" :
					AttendeesManagementUI.userAddAttendee(event);
					break;

				case "delete attendee" :
					AttendeesManagementUI.userDeleteAttendee(event);
					break;

				case "exit" :
					System.out.println("Returning to main menu...");
					System.out.println("\n");
					break;

				default:
					System.out.println("Sorry I didnt get that!");
				}
			}
			while (!line.equals("exit"));


		} catch (IOException e) {

			e.printStackTrace();
		} 
	}

	private static void userDeleteAttendee(Event event) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			System.out.println("Delete attendee from: " + event);
			System.out.println("Which attendee would you like to delete?");
			System.out.print("First name : ");
			String firstName = br.readLine();
			System.out.print("Last Name : ");
			String lastName = br.readLine();

			Attendee attendee = event.searchAttendee(firstName, lastName);
			if (attendee != null) {
				event.deleteAttendee(attendee);
				System.out.println("Attendee deleted!");
			}
			else {
				System.out.println("Attendee not found!");
			}

		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void userAddAttendee(Event event) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {

			System.out.println("Add attendee to: " + event);
			System.out.println("What attendee would you like to add?");
			System.out.print("First name : ");
			String firstName = br.readLine();
			System.out.print("Last Name : ");
			String lastName = br.readLine();
			System.out.print("Email : ");
			String email = br.readLine();
			System.out.print("Number : ");
			String number = br.readLine();

			event.addAttendee(firstName, lastName, email, number);
			System.out.println("Attendee added!");
		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void userShowAttendees(Event event) {
		System.out.println(event.listAttendees());

	}


}
