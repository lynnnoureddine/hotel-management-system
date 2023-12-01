package hotel_management_system;

import java.util.Scanner;

public class RoomList {
	private int floor;
	private LinkedList rooms;
	
	Scanner scan = new Scanner(System.in);
	
	public RoomList () {
		floor = 0;
		rooms = new LinkedList();
	}
	
	public RoomList(int floor) {
		this.floor = floor;
		rooms = new LinkedList();
	}
	
	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public void addRooms() {
		System.out.print("How many rooms would you like to add to floor " + floor + " ?" );
		int num = scan.nextInt();
		while(num <= 0){
			System.out.print("Invalid number. Re-enter the number of rooms you would like to add:");
			num = scan.nextInt();
		}
		System.out.print("Enter the number of the floor: ");
		int f = scan.nextInt();
		setFloor(f);		
		for (int i = 1 ; i <= num ; i++){
			System.out.println("\nEnter the information of room " + i + ": ");
			Room m = new Room();
			m.readRoomInformation();
			rooms.insertAtBack(m);
		}
		if(!rooms.isEmpty())
			System.out.println("Rooms added successfully");
		else
			System.out.println("Error adding rooms");
	}
	
	public void addBooking() {
		if(!rooms.isEmpty()) {
			System.out.println("\nHere are the rooms in this room type: ");
			rooms.display();
			System.out.print("Enter the number of the room you would like to book: ");
			int num = scan.nextInt();
			Node<Room> room = rooms.findRoomById(num);
			while(room == null) {
				System.out.print("This room doens't exist. Please re-enter the room number: ");
				num = scan.nextInt();
				room = rooms.findRoomById(num);
			}
			room.getData().addBooking();
			System.out.println("Your room was booked successfully. Hope you have a nice day!");
		}
	}
	
	public void checkin() {
		if(!rooms.isEmpty()) {
			System.out.print("\nEnter the number of the room that you booked: ");
			int num = scan.nextInt();
			Node<Room> room = rooms.findRoomById(num);
			while(room == null) {
				System.out.print("This room doens't exist. Please re-enter the room number: ");
				num = scan.nextInt();
				room = rooms.findRoomById(num);
			}
			room.getData().checkin();
		}
	}
	
	public void checkout() {
		if(!rooms.isEmpty()) {
			System.out.print("\nEnter the number of the room that you stayed in: ");
			int num = scan.nextInt();
			Node<Room> room = rooms.findRoomById(num);
			while(room == null) {
				System.out.print("This room doens't exist. Please re-enter the room number: ");
				num = scan.nextInt();
				room = rooms.findRoomById(num);
			}
			room.getData().checkout();
		}
	}
	
	public void cancelBooking() {
		if(!rooms.isEmpty()) {
			System.out.print("\nEnter the number of the room that you booked: ");
			int num = scan.nextInt();
			Node<Room> room = rooms.findRoomById(num);
			while(room == null) {
				System.out.print("This room doens't exist. Please re-enter the room number: ");
				num = scan.nextInt();
				room = rooms.findRoomById(num);
			}
			room.getData().cancelBooking();
		}
	}
	
	public String toString() {
		return "Floor " + floor + " information: "+
				"\n";
	}
	
}
