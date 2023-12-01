package hotel_management_system;

public class LinkedList{
	private Node<Room> first;

    public LinkedList() {
        first = null;
    }
    
    public Node<Room> getFirst() {
		return first;
    }

    public boolean isEmpty() { // checks to see if the linked list is empty
        return first == null;
    }

    public void insertAtFront(Room object) { // inserts object at the front of a linked list
        Node<Room> n = new Node<>(object);
        n.setNext(first);
        first = n;
    }
    
    public void insertAtBack(Room object) { // inserts object at the end of a linked list 
    	if(isEmpty())
    		insertAtFront(object);
    	else {
    		Node<Room> n = new Node<>(object);
    		Node<Room> current = first;
    		while(current.getNext()!= null)
    			current = current.getNext();
    		current.setNext(n);
    	}
    }
    
    public void deleteFromFront(){ // deletes the first object in the linked list
    	if(isEmpty()) 
    		return;
    	else 
    		first = first.getNext();
    }
    
    public void deleteFromBack() { // deletes the last object in the linked list
    	if(isEmpty())
    		return;
    	else {
    		Node<Room> current = first;
    		while(current.getNext() != null)
    			current = current.getNext();
    		current.setNext(null);
    	}
    }
    
    public Node<Room> findRoomById(int id) {
		if(!isEmpty()) {
			Node<Room> current = getFirst();
			boolean flag = false;
			while(current != null) {
				if(current.getData().getID() == id) {
					flag = true;
					break;
				}
				current = current.getNext();
			}
			
			if(flag == true)
				return current;
			else
				return null;
		}
		return null;
	}
    
    public void display() { // displays the objects in the linked list
    	if(isEmpty())
    		System.out.println("The room list is empty");
    	else {
    		Node<Room> current = first;
    		while(current != null) {
    			System.out.println(current.getData().toString());
    			current = current.getNext();
    		}
    	}
		}
		
}
