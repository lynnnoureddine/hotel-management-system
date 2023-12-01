package hotel_management_system;

import java.util.Scanner;

public class Hotel {
	private String name, address, contact_info;
	private int floors;
	private Stack hotel;
	
	Scanner scan = new Scanner(System.in);
	
	public Hotel() {
		name = "";
		address = "";
		contact_info = "";
		floors = 0;
		hotel = new Stack();
	}
	
	public Hotel(String name, String address, String contact, int num) {
		this.name = name;
		this.address = address;
		contact_info = contact;
		floors = num;
		hotel = new Stack();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact_info() {
		return contact_info;
	}

	public void setContact_info(String contact_info) {
		this.contact_info = contact_info;
	}

	public int getFloors() {
		return floors;
	}

	public void setFloors(int floors) {
		this.floors = floors;
	}
	
	public void readHotelInfo() {
		System.out.print("Enter the name of your hotel: ");
		String name = scan.next();
		setName(name);
		
		System.out.print("Enter the address of your hotel: ");
		String address = scan.next();
		setAddress(address);
		
		System.out.print("Enter the contact information of your hotel: ");
		String contact = scan.next();
		setContact_info(contact);
		
		System.out.print("Enter the number of floors you wish to have in your hotel: ");
		int num = scan.nextInt();
		setFloors(num);
		
		System.out.println("\n------------------------------");
	}
	
	public void addFloor() {
		System.out.println("Enter the rooms on each floor of you hotel: ");
		for(int i = 1 ; i <= floors ; i++) {
			System.out.println("Floor " + i + ": ");
			RoomList rooms = new RoomList(i);
			rooms.addRooms();
			System.out.println();
		}
		
		if(hotel.isEmpty())
			System.out.println("Unsuccessful addition of rooms");
		else
			System.out.println("Rooms added successfully");
	}
	
	
}
