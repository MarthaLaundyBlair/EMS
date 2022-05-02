import java.time.LocalDate;
import java.time.LocalTime;

public class Event{

	private String name;
	private String location;
	private LocalDate date; // "2016-06-12" format
	private LocalTime time; // "06:30" format
	
	//Constructor

	public Event(String name, String location, String dateString, String timeString) {
		this.name = name;
		this.location = location;
		this.date = LocalDate.parse(dateString);
		this.time = LocalTime.parse(timeString);
		}

	//To String

	public String toString() {
		return "Name = " + name + ", Location = " + location + ", Date = " + date + ", Time = " + time;
	}
	
	//Getters
	
	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public LocalDate getDate() {
		return date;
	}

	public LocalTime getTime() {
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
			this.date = LocalDate.parse(new_value);
			break;
		case "time" :
			this.time = LocalTime.parse(new_value);
			break;
		}
		

	}

	
	
}
