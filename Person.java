package wingman;

/**
 * @author Chris Oka && Dalton Logan
 *
 */

public class Person {
	String name;
	String gender;
	String orientation;
	String id;
	
	
	public Person(String theirName, String theirGender, String theirOrientation, String theirID){
		name = theirName;
		gender = theirGender;
		orientation = theirOrientation;
		id = theirID;
	}
	
	public String toString(){
		return "Name: " + name + ", ID: " + id +"\n";
	}
}
