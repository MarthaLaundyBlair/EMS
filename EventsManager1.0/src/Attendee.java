import java.io.Serializable;

public class Attendee implements Serializable{

	private String firstName;
	private String lastName;
	private String email;
	private String number;

	//constructor
	public Attendee(String firstName, String lastName, String email, String number) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.number = number;
	}


	// getters
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public String getNumber() {
		return number;
	}


	//to string
	@Override
	public String toString() {
		return "First Name = " + firstName + ", Last Name = " + lastName + ", Email = " + email + ", Number = " + number
				+ "]";
	}



}
