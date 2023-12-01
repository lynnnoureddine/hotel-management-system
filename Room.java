package hotel_management_system;

import java.util.Scanner;

public class Room {
	private int capacity, ID;
	private static int count = 1;
	private String description, type;
	private boolean availability;
	private double price;
	private PriorityQueue bookings;

	Scanner scan = new Scanner(System.in);
	
	public Room() {
		capacity = 0 ;
		ID = count;
		count++;
		description = "";
		availability = true;
		type = "";
		price = 0 ;
		bookings = new PriorityQueue();
	}
	
	public Room(int cap, String des, String t, double pri, boolean av) {
		capacity = cap;
		ID = count;
		count++;
		description = des;
		type = t;
		price = pri;
		availability = av;
		bookings = new PriorityQueue();
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getID() {
		return ID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public PriorityQueue getBookings() {
		return bookings;
	}

	public void setBookings(PriorityQueue bookings) {
		this.bookings = bookings;
	}
		
	public void readRoomInformation() {
		System.out.print("Enter the room type: ");
		String type = scan.next(); 
		setType(type);

		System.out.print("Enter the room capacity: ");
		int cap = scan.nextInt(); 
		setCapacity(cap);

		System.out.print("Enter the room price: ");
		double price = scan.nextDouble(); 
		setPrice(price);

		System.out.print("Enter the room description: ");
		String des = scan.next(); 
		setDescription(des);
	}
	
	public void updateRoomInformation() {
		System.out.print("Would you like to update this rooms information? (yes/no)");
		String answer = scan.next();
		while(answer.equals("yes")){
			System.out.println("Enter the number corresponding to the attribute you would like to upadate: "+
			"\n1- Capacity \n2-Price \n3-Availability \n4-Description ");
			int num = scan.nextInt();
			switch(num){
				case 1:
					System.out.print("\nEnter the capacity: ");
					int c = scan.nextInt();
					setCapacity(c);
					break;
				case 2:
					System.out.print("Enter the price: ");
					double p = scan.nextDouble();
					setPrice(p);
					break;
				case 3:
					System.out.print("Enter the availability (true/false): ");
					boolean a = scan.nextBoolean();
					setAvailability(a);
					break;
				case 4:
					System.out.print("Enter the room's description: ");
					String d = scan.nextLine();
					setDescription(d);
					break;
				default:
					System.out.println("Invalid choice");
					break;
			}
			System.out.print("Would you like to update any more information? (yes/no)");
			answer = scan.next();
		}
	}
	
	public void printBookingsList() {
		if(!bookings.isEmpty()) {
			System.out.println("\n The list of bookings for this room are: ");
			bookings.display();
		}
		else
			System.out.println("\nThere are no current bookings for this room");
	}
	
	public void addBooking() {
		Booking booking = new Booking();
		booking.readBookingInformation();
		System.out.print("\nEnter the number of guests staying in this room: ");
		int num = scan.nextInt();
		while(num>capacity || num<=0) { // the number of guests can not be zero or negative and can not be greater than the capacity of the room 
			System.out.println("Invalid Number of guests");
			System.out.print("Re-enter the number of guests staying in this room: ");
			num = scan.nextInt();
		}
		booking.setNumber_of_guests(num);
		System.out.print("Enter the check in date: (yyyy-mm-dd)");
		String in = scan.next();
		System.out.print("Enter the check out date: (yyyy-mm-dd)");
		String out = scan.next();
		while(validateDate(in, out) == false) { //stops when the check in and out dates are valid 
			System.out.println("Invalid check in/out date, Please re-enter the check in and check out dates: (yyyy-mm-dd)");
			in = scan.next();
			out = scan.next();
		}
		booking.setCheck_in_date(in);
		booking.setCheck_out_date(out);
		String numeric = in.replaceAll("-", "");
		int date = Integer.parseInt(numeric); //turns the check-in date into the priority of the node where the nodes are sorted by the date of checking in (earliest date 1st)
		bookings.enqueue(booking, date);
	}
	
	public boolean validateDate(String check_in, String check_out) { // checks if the entered check in and out dates are valid
		String in = check_in.replaceAll("-", "");
		int indate = Integer.parseInt(in);
		String out = check_out.replaceAll("-", "");
		int outdate = Integer.parseInt(out);
		if(outdate <= indate) //checking to see if the check out dates comes after the check in date
			return false;
			
		boolean flag = true;
		PriorityQueue temp = new PriorityQueue();
		Nodep current;
		while(!bookings.isEmpty()) { // compares the check in and check out dates to the existing dates
			current = bookings.dequeue();
			temp.enqueue(current.getData(), current.getPriority());
			if(current.getData().getCheck_in_date().equals(check_in)) //two bookings can't have the same check in date
				flag = false;
			
			else if(!current.getData().getCheck_in_date().equals(check_in)) { // a booking can't have a check in date that comes after a check in date and before the check out date of another
				String current_in = current.getData().getCheck_in_date().replaceAll("-", "");
				int date_in = Integer.parseInt(current_in);
				String current_out = current.getData().getCheck_out_date().replaceAll("-", "");
				int date_out = Integer.parseInt(current_out);
				if(indate > date_in && indate < date_out)
					flag = false;
			}	
		}
		
		while(!temp.isEmpty()) {
			Nodep x = temp.dequeue();
			bookings.enqueue(x.getData(), x.getPriority());
		}
		
		return flag;
	}
	
	public void cancelBooking(){ //cancels a booking based on the name that it was booked under
		System.out.print("\nPLease enter your full name: ");
		String name = scan.next();
		if(bookings.deleteByName(name)) 
			System.out.println("Your booking was successfully canceled.");
		else
			System.out.println("No booking under this name was found.");
	}

	public void checkin() { // checks in a guest making sure that they have a booking under their name
		System.out.print("\nEnter your full name: ");
		String name = scan.next();
		if(bookings.searchByName(name) == false) {
			System.out.println("Im sorry. It seems that there is no booking under this full name. Would you like to book this room? (yes/no)");
			String answer = scan.next();
			if(answer.equals("yes")) {
				addBooking();
				System.out.println("\nThank you for you patience. Have a nice day");
			}
			else
					System.out.println("I apologize for the inconvenience. I hope you have a nice day!");
		}
		else {
			setAvailability(false);
			System.out.println("Your total is: " + calculateTotalByName(name));
			System.out.println("Thank you for choosing our hotel. I hope you have a pleasant stay! ");
		}
	}
	
	public double calculateTotalByName(String name) { // calculates the total prices of a guest's stay based on the name provided 
		if(bookings.searchByName(name)) {
			PriorityQueue temp = new PriorityQueue();
			Nodep current;
			int indate = 0 , outdate = 0;
			boolean flag = false;
			while(!bookings.isEmpty()) { // gets the check in date and check out date of the booking with the name that was sent (if found)
				current = bookings.dequeue();
				if(current.getData().getGuest().getFull_name().equals(name)) {
					String in = current.getData().getCheck_in_date().replaceAll("-", "");
					String out = current.getData().getCheck_out_date().replaceAll("-", "");
					indate = Integer.parseInt(in);
					outdate = Integer.parseInt(out);
				}
				if(current.getData().getGuest().isLoyalty_program() == true)
					flag = true;
				temp.enqueue(current.getData(), current.getPriority());
			}
			
			while(!temp.isEmpty()) {
				Nodep x = temp.dequeue();
				bookings.enqueue(x.getData(), x.getPriority());
			}
			
			if(indate != 0 && outdate != 0 && flag == true) 
				return (outdate - indate) * price - ((outdate - indate) * 0.25);
			else
				return (outdate - indate) * price;
		}
		
		return 0;
	}
	
	public void checkout() {
		System.out.print("\nEnter your full name: ");
		String name = scan.next();
		if(bookings.deleteByName(name)) 
			System.out.println("You have successfully checked out. Thank you for choosing our hotel and i hope you enjoyed your stay!");
		else
			System.out.println("I apologize, but there doesn't seem to be any booking under the name " + name);
	}
	
	public String toString(){
		return "\nRoom " + ID + " information: "+
						"\nCapacity: " + capacity +
						"\nPrice: " + price+
						"\nDescription: " + description+
						"\nAvailability: " + availability +
						"\nThe bookings of this room are: \n";
						
	}

}