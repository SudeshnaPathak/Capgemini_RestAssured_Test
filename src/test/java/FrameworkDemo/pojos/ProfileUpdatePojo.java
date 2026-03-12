package FrameworkDemo.pojos;

public class ProfileUpdatePojo {
	private String city;
	private String state;
	private String firstName;
	private String lastName;
	private String country;
	private Long phone;
	
	public ProfileUpdatePojo(String city, String state, String firstName, String lastName, String country,
			Long phone) {
		super();
		this.city = city;
		this.state = state;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.phone = phone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "ProfileUpdatePojo [city=" + city + ", state=" + state + ", firstName=" + firstName + ", lastName="
				+ lastName + ", country=" + country + ", phone=" + phone + "]";
	}
	
	
	
}
