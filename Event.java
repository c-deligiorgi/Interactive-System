

/**
 * @author adamm
 *
 */
public class Event implements Comparable <Event>{

	
	
	private String nameOfEvent;
	private int numOfTickets;
	
	public Event(String nameOfEvent, int numOfTickets) {
		this.nameOfEvent = nameOfEvent;
		this.numOfTickets = numOfTickets;
	}

	

	/**
	 * @return the nameOfEvent
	 */
	public String getNameOfEvent() {
		return nameOfEvent;
	}

	/**
	 * @param nameOfEvent the nameOfEvent to set
	 */
	public void setNameOfEvent(String nameOfEvent) {
		this.nameOfEvent = nameOfEvent;
	}

	/**
	 * @return the numOfTickets
	 */
	public int getNumOfTickets() {
		return numOfTickets;
	}

	/**
	 * @param numOfTickets the numOfTickets to set
	 */
	public void setNumOfTickets(int numOfTickets) {
		this.numOfTickets = numOfTickets;
	}
   
	public void ticketsBooked(int bookedTickets) {
		numOfTickets= numOfTickets - bookedTickets;
		
	}
	
	public void clientsBookedTickets(int clientTickets) {
		numOfTickets= numOfTickets + clientTickets;
		
	}
	public void ticketsReturned(int returnedTickets) {
		numOfTickets= numOfTickets + returnedTickets;
		
	}
	
	public void clientsReturnedTickets(int clientTickets) {
		numOfTickets= numOfTickets - clientTickets;
		
	}
	
	@Override
	public int compareTo(Event o) {
	
			int eventCmp = nameOfEvent.compareTo(o.nameOfEvent);
			if (eventCmp !=0) {return eventCmp;}
			return eventCmp;	
		
	}



	@Override
	public String toString() {
		return  String.format("%s  %s", nameOfEvent, numOfTickets);
	}
			//	" nameOfEvent=" + nameOfEvent + ", numOfTickets=" + numOfTickets ;
	
}
