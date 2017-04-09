/**
 * 
 */
package wingman;

/**
 * @author Chris Oka && Dalton Logan
 *
 */
public class HashItem {

	/**
	 * @param	person - the person object that you want to add to the object
	 * 			rating - the rating associated with the person
	 */
	int rating;
	Person person;
	public HashItem(Person personToAdd, int ratingToAdd){
		person = personToAdd;
		rating = ratingToAdd;
	}
	
	public void modifyRating(int change){
		rating+=change;
	}
	
	public Person getPerson(){
		return person;
	}
	
	public void setPerson(Person newPerson){
		person = newPerson;
	}
	
	public int getRating(){
		return rating;
	}
	
	public void setRating(int newRating){
		rating = newRating;
	}
	
	public String toString(){
		return person.toString() + "Rating: " + rating + "\n\n";
	}
	
	//compareTo is set to work with the compare method in Main.java
	public int compareTo(HashItem other){
		if(rating<other.rating){
			return 1;
		}
		else if(rating>other.rating){
			return -1;
		}
		else
			return 0;
	}
}
