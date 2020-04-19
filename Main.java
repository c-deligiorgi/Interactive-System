import java.util.*;
import java.io.*;

public class Main {

	private static Scanner read;
	private static SortedArrayList<Client> listOfClients = new SortedArrayList<Client>();
	private static SortedArrayList<Event> events = new SortedArrayList<Event>();

	public static void main(String[] args) throws FileNotFoundException {
		openFile();
		readFile();
		closeFile();

		

		printMenu();
		read = new Scanner(System.in);

		char ch = read.next().charAt(0);

		while (ch != 'f') {
			switch (ch) {

			case 'e':
				listEvents();
				break;

			case 'c':
				listClients();
				break;

			case 'b':
				bookTicket();
				break;

			case 'r':
				returnTicket();
				break;

			// case 'c': sportClub.payFee(readNames(),outFile);
			// break;

			// case 'b': if (sportClub.placeAvailable())
			// {
			// ClubMember member = readMemberData(sportClub);
			// if (member != null)
			// sportClub.register(member);
			// else
			// System.out.println("You are already registered as a member.");
			// }

			// System.out.println("Sorry, we cannot accept any more members.");
			// break;

			// case 'r': sportClub.sendReminders(outFile);
			// break;

			default:
				System.out.println("Invalid entry, try again");

			}
			printMenu();
			ch = read.next().charAt(0);
			read.nextLine();
		}
		System.out.println("Shutting down ...");
	}

	/*
	 * public static void main(String[] args){ // openFile(); // readFile(); //
	 * closeFile(); SortedArrayList<String> test = new SortedArrayList<String>();
	 * 
	 * // Client t = new Client("adam", "willims");
	 * 
	 * // Client r = new Client("james", "willims");
	 * 
	 * // Client y = new Client("james", "aaaa");
	 * 
	 * test.sortedAdd("a"); System.out.println(test); test.sortedAdd("b");
	 * System.out.println(test); test.sortedAdd("z"); System.out.println(test);
	 * test.sortedAdd("c"); System.out.println(test); test.sortedAdd("f");
	 * System.out.println(test); test.sortedAdd("e"); System.out.println(test); }
	 */

	private static void printMenu() {
		System.out.println("------------------------------");
		System.out.println("MENU");
		System.out.println("f - finish");
		System.out.println("e - display all events");
		System.out.println("c - display all clients");
		System.out.println("b - book tickets");
		System.out.println("r - cancel/return tickets");
		System.out.println("------------------------------");
		System.out.println("Type a letter and press Enter");
	}

	public static void openFile() {
		try {
			read = new Scanner(new File("test"));
		} catch (Exception e) {
			System.out.println("could not find file");
		}
	}

	public static void readFile() {

		int n1 = read.nextInt();
		while (read.hasNextLine()) {
			for (int i = 1; i <= n1; i++) {
				String s = read.nextLine();
				events.sortedAdd(new Event(read.nextLine(), read.nextInt()));
			}

			int n2 = read.nextInt();
			for (int i = 1; i <= n2; i++) {
				listOfClients.sortedAdd(new Client(read.next(), read.next()));
			}
		}
	}

	public static void closeFile() {
		read.close();
	}

	public static void listEvents() {
		for (Event e : events)
			System.out.println(e.getNameOfEvent() + ", Number of Tickets Avillible:" + e.getNumOfTickets());
	}

	public static void listClients() {
		for (Client c : listOfClients)
			System.out.println(c.getFirstName() + " " + c.getLastName());
	}

	private static Client readClientName() {
		System.out.println("Enter client's firstname and lastname," + " and press Enter");
		String name1 = read.next();
		String name2 = read.next();
		read.nextLine();
		return new Client(name1, name2);
	}

	public static void bookTicket() {

		boolean clientVeried = false;
		boolean eventVerified = false;
		boolean eventTicketsNumVerified = false;
		boolean eventTicketsSoldout = false; 

		Client cc = readClientName();
		int clientId = 0;
		int eventId = 0;
		String eventName = "";
		int eventTicketNum = 0;

		for (int i = 0; i < listOfClients.size(); i++) {
			if (listOfClients.get(i).equalTo(cc)) {
				System.out.println("Client's name valid");
				clientVeried = true;
				clientId = i;
				break;
			}
		}

		if (clientVeried) {
			System.out.println("Please enter an event name");
			eventName = read.nextLine();
			for (int i = 0; i < events.size(); i++) {
				if ((events.get(i).getNameOfEvent().compareTo(eventName)) == 0) {
					System.out.println("Event's valid");
					eventVerified = true;
					eventId = i;
					break;
				}
			}
		}

		if (eventVerified) {
			System.out.println("Please enter a number");
			eventTicketNum = read.nextInt();
			while(eventTicketNum < 0) {
				System.out.print("Enter a valid number");
				eventTicketNum = read.nextInt();
			}
				if ((events.get(eventId).getNumOfTickets() >= eventTicketNum)) {
					System.out.println("Number's valid");
					eventTicketsNumVerified = true;
				}
				else {
					eventTicketsSoldout = true;
				}
				
				if (eventTicketsSoldout) 
					printLatterToClient(listOfClients.get(clientId).getLastName(), events.get(eventId).getNameOfEvent(), eventTicketNum );
			
		}

		if (eventTicketsNumVerified) {
			if (listOfClients.get(clientId).getList().size() == 0) {
				listOfClients.get(clientId).addClientsBookedEvents(eventName, eventTicketNum);
				events.get(eventId).ticketsBooked(eventTicketNum);
			} else if (listOfClients.get(clientId).getList().size() < 3) {
				//	if (listOfClients.get(clientId).getEventName(eventName)) {
					listOfClients.get(clientId).setBookedEventName(eventName, eventTicketNum);
					events.get(eventId).ticketsBooked(eventTicketNum);
				}
			else if (listOfClients.get(clientId).getList().size() >=3) {
				System.out.println("You have already passed the limti number of events");
			
			//}
		}  System.out.println(listOfClients.get(clientId).getList());
	}
 }

		public static void printLatterToClient( String clientName, String eventName , int ticketsNum) {
			 try {
				 PrintWriter outFile = new PrintWriter(new File ("output"));
				 outFile.println("Dear Mr." + clientName  );
				 outFile.println("We are sorry to inform you that the  " + eventName  );
				 outFile.println("event, with " + ticketsNum  );
				 outFile.println("tickets number has soldout" );
				 outFile.close();
			 }
			 
			 catch (Exception e){
				 System.out.println("Error!!");
				 
			 }
			 
		}
		
		public static void returnTicket () {
			boolean clientVeried = false;
			boolean eventVerified = false;
			//boolean eventTicketsNumVerified = false;
			
			Client cc = readClientName();
			int clientId = 0;
			int eventId = 0;
			String eventName = "";
			int tktNum = 0;

			for (int i = 0; i < listOfClients.size(); i++) {
				if (listOfClients.get(i).equalTo(cc)) {
					System.out.println("Client's name valid");
					clientVeried = true;
					clientId = i;
					break;
				}
			}
			
			if (clientVeried) {
				System.out.println("Please enter an event name");
				eventName = read.nextLine();
				for (int i = 0; i < events.size(); i++) {
					if ((events.get(i).getNameOfEvent().compareTo(eventName)) == 0) {
						eventVerified = true;
						eventId = i;
						break;
					}
				}	
			}
			
			if (eventVerified) {
			
					if (listOfClients.get(clientId).getEventName(eventName)) {
						System.out.println("Please enter number of tickets to return or cancel");
					    tktNum = read.nextInt();
					    	if ( tktNum > listOfClients.get(clientId).getBookedTicketsNumber())
					    		System.out.println("The number you enter in not valid");
					    	else {
					    		listOfClients.get(clientId).setBookedTicketsNumber(tktNum);
					    		events.get(eventId).ticketsReturned(tktNum);
					    		}
					         }
					else { 
						System.out.println("The name of event are not found");
					}
			   }
		}
}