import java.awt.print.Book;
import java.util.*;

/**
 * @author adamm
 * @param <bookedEvents>
 * @param <E>
 *
 */
public class Client implements Comparable<Client> {

	
	
	private String firstName;
	private String lastName; 
	private SortedArrayList<Event> bookedEvents = new SortedArrayList<Event>();
	private int id; 
	public Client(String firstName, String lastName) {
	  
	   this.firstName = firstName; 
	   this.lastName = lastName; 
	  
	}


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public String toString() {
		return firstName + " " + lastName;
	}
	
	public int compareTo(Client client) {

		int lnCmp = lastName.compareTo(client.lastName);
		if (lnCmp !=0) 
			return lnCmp;
		else {
			int fnCmp = firstName.compareTo(client.firstName);
			if (fnCmp != 0) 
			return fnCmp;
		 }
		return lnCmp;
	}

   public boolean equalTo(Client c) {
	   return (c.firstName.equals(firstName) && c.lastName.equals(lastName));
	    
   }
  
  public SortedArrayList getList() {
	  return bookedEvents;
	  
  }
  
  

   
  public void addClientsBookedEvents( String eventName, int ticketsNum) {
	  bookedEvents.sortedAdd(new Event(eventName, ticketsNum));
	
   }
	
  
  public void setBookedEventName(String eventName , int numOfTickets) {
	  boolean result= false; 
	  for  (int i = 0 ; i < bookedEvents.size() ; i++) {
		  if (eventName.equals(bookedEvents.get(i).getNameOfEvent())) {
			  bookedEvents.get(i).clientsBookedTickets(numOfTickets);
		       result = true; 
		  }
	  }
	  if (!result) {
		   bookedEvents.sortedAdd(new Event(eventName, numOfTickets));
     }
   
	   
   }
  
  public boolean getEventName(String eventName) {
	 boolean result = false;
	 
	  for  (int i = 0 ; i < bookedEvents.size() ; i++) {
	  if (eventName.equals(bookedEvents.get(i).getNameOfEvent()))
		  result = true;
	  id = i;
	  }  
	  return result; 
  }
  
  public int getBookedTicketsNumber () {
	  return bookedEvents.get(id).getNumOfTickets();
	  
  }
  public void setBookedTicketsNumber(int tkt) {
	  for  (int i = 0 ; i < bookedEvents.size() ; i++) {
		  bookedEvents.get(id).clientsReturnedTickets(tkt); 
	  }
  }
}


