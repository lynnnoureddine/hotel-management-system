package hotel_management_system;

public class LinkedListPQ {
	private Nodep first;

	public LinkedListPQ() {
		first = null;
	}

	public Nodep getFirst(){
		return first;
	}
	
	public boolean isEmpty() {
		return first == null;
	}

	public void insertAtBack(Booking v, int p) {
		Nodep n = new Nodep(v, p);
		if (isEmpty()) {
			first = n;
		} else {
			Nodep current = first;
			while (current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(n);
			n.setNext(null);
		}
	}

	public void priorityEnqueue(Booking a, int priority2) {
		Nodep n = new Nodep(a, priority2);
		if (isEmpty())
			first = n;
		else if (first.getPriority() >= priority2) {
			n.setNext(first);
			first = n;
		} else {
			Nodep current = first;
			if (first.getNext() != null) {
				while (current.getNext() != null && current.getNext().getPriority() < priority2) 
					current = current.getNext();
				if (current.getNext() != null) {
					n.setNext(current.getNext());
					current.setNext(n);
				} 
				else 
					current.setNext(n);
			} 
			else 
				first.setNext(n);
		}
	}

	public boolean searchObject (Booking Object) {
		if(isEmpty())
			return false;
		else {
			Nodep current = first;
			while(current != null) {
				if(current.getData().equals(Object))
					return true;
				current= current.getNext();
			}
			return false;
		}
	}
	
	public Nodep deleteFromFront() {
		Nodep temp = first;
		if (!isEmpty()) {
			first = first.getNext();
		}
		return temp;
	}

	public void display() {
		Nodep current = first;
		while (current != null) {
			System.out.print(current.getData() + " ");
			current = current.getNext();
		}
		System.out.println("\n");
	}
	
}
