package hotel_management_system;

public class LinkedListS{ 
    private Node<RoomList> first;

    public LinkedListS() {
        first = null;
    }
    
    public Node<RoomList> getFirst() {
		return first;
    }

    public boolean isEmpty() { // checks to see if the linked list is empty
        return first == null;
    }

    public void insertAtFront(RoomList object) { // inserts object at the front of a linked list
        Node<RoomList> n = new Node<>(object);
        n.setNext(first);
        first = n;
    }
    
    public void deleteFromFront(){ // deletes the first object in the linked list
    	if(isEmpty()) 
    		return;
    	else 
    		first = first.getNext();
    }
    
    public void display() { // displays the objects in the linked list
    	if(isEmpty())
    		System.out.println("The hotel is empty");
    	else {
    		Node<RoomList> current = first;
    		while(current != null) {
    			System.out.println(current.getData().toString());
    			current = current.getNext();
    		}
    	}
	}
		
}
