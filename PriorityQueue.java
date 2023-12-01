package hotel_management_system;

public class PriorityQueue extends LinkedListPQ {
	public PriorityQueue(){
		super();
	}
	
	public void enqueue(Booking a , int p){
		priorityEnqueue(a, p);
	}
	
	public void enqueue2(Booking a, int p){
		insertAtBack(a, p);
	}
	
	public Nodep dequeue(){
		return deleteFromFront();
	}
	
	public boolean searchObject(Booking object) {
		return searchObject(object);
	}
	
	public void print() {
		display();
	}
	
	public boolean searchByName(String name) { // searches for a booking based on the name given
		boolean flag = false;
		PriorityQueue temp = new PriorityQueue();
		Nodep current;
		while(isEmpty()) { // compares the check in and check out dates to the existing dates
			current = dequeue();
			if(current.getData().getGuest().getFull_name().equals(name)) {
				flag = true;
			}
			temp.enqueue(current.getData(), current.getPriority());
		}
		
		while(!temp.isEmpty()) {
			Nodep x = temp.dequeue();
			enqueue(x.getData(), x.getPriority());
		}
		
		return flag;
	}
	
	public boolean deleteByName(String name) { // deletes the booking with the given name from the priority queue
		if(searchByName(name)) {
			PriorityQueue temp = new PriorityQueue();
			Nodep current;
			while(!isEmpty()) { 
				current = dequeue();
				if(!current.getData().getGuest().getFull_name().equals(name)) {
					temp.enqueue(current.getData(), current.getPriority());
				}
			}
			
			while(!temp.isEmpty()) {
				Nodep x = temp.dequeue();
				enqueue(x.getData(), x.getPriority());
			}
			
			return true;
		}
		
		return false;
	}
	
	public void display() {
		display();
	}

}
