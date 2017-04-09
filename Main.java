/**
 * 
 */

package wingman;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * @author Chris Oka && Dalton Logan
 *	Info:	Program takes in a .json file including a user and reactions to that user, then analyzes the information
 *			so that a .json file can be output that'll include a rating for each user, organized from highest to lowest
 *
 */
public class Main {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		Hashtable<String, HashItem> ht = new Hashtable<String, HashItem>();//for easy removing and re-adding people
        JSONParser parser = new JSONParser();

        try {
        	//can modify to take a file name with the comman to run the program?
            Object obj = parser.parse(new FileReader("C:/Users/dalto/Documents/test2.json"));//this can be changed based on the current layout of the program

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray msg = (JSONArray) jsonObject.get("reactions");//each indevidual JSON object

            Iterator<JSONObject> iterator = msg.iterator();//warning here for generic type
            while (iterator.hasNext()) {//hits every object, tested so it doesn't skip last
            	JSONObject output = iterator.next();//changes iterator to next object, and set to output for gaining information
                int change = 0;
                
                String outputStr = output.get("type").toString();
                switch (outputStr){
    			case "LIKE":	change = 2;
    							break;
    			case "LOVE":	change = 3;
    							break;
    			case "HAHA":	change = 1;
    							break;
    			case "WOW":		change = 0;
    							break;
    			case "SAD":		change = -1;
    							break;
    			case "ANGRY":	change = -2;
    							break;
    		}
                //following checks if person is already within the hashtable, if not then it jumps to second option
                if(ht.containsKey(output.get("name").toString())){
        			ht.get(output.get("name").toString()).modifyRating(change);
        		}
        		else{
        			Person tempPerson = new Person(output.get("name").toString(), "gender","orientation", output.get("id").toString());
            		HashItem tempItem = new HashItem(tempPerson, change);
            		ht.put(output.get("name").toString(), tempItem);
        		}
            }
            Comparator<HashItem> compar = new Comparator<HashItem>() {//comparator so the array will be able to sort itself  based on rating

                @Override
                public int compare(HashItem o1, HashItem o2) {
                    return o1.compareTo(o2);
                }
                      };
            ArrayList<HashItem> arr = new ArrayList<HashItem>(ht.values());
            arr.sort(compar);
            //System.out.println(arr);
            try (FileWriter file = new FileWriter("C:/Users/dalto/Documents/test.json")) {
                JSONObject mainObj = new JSONObject();
                mainObj.put("person_id", jsonObject.get("person_id"));
            	JSONArray ja = new JSONArray();
            	
	            for(int i = 0; i<arr.size();i++){
	    		JSONObject obj1 = new JSONObject();
	
	    		//creating jsonObject to put into the new json file. Warning here for generic types, all three
	    		obj1.put("score", arr.get(i).rating);
	            obj1.put("id", arr.get(i).person.id);
	            obj1.put("name", arr.get(i).person.name);
	
	            ja.add(obj1);
	            }
	
	            mainObj.put("reactions", ja);
	            file.write(mainObj.toJSONString());
	            file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
	}//main

	//PLAN:Create a method to add or modify people in table automatically
	
}//Main
