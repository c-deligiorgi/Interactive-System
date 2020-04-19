
import java.util.*;




public class SortedArrayList<E extends Comparable<E>>  extends ArrayList<E>  {


   public void sortedAdd(E a) {
		
	   if ( size() == 0) {
		    add (a); 
	   }
	   else {   
		   if (a.compareTo(get(0)) < 0) {add ( 0 , a);}
		   	  
		   if (a.compareTo(get(size()-1)) > 0)  {add (size() , a);}

		  int s = size()-1;
		   for (int i = 0; i < size()-1; i++) {
		   	 
		   	    if (a.compareTo(get(i)) > 0 && a.compareTo(get(i+1)) < 0) {  
		   	    	add( i+1, a );
		   	    	
		         }
	   	   	 }  
	     } 
     }  
} 