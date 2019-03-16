package main.java.jsonParser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class jsonReader {
	public static void main(String[] args) throws ParseException, FileNotFoundException, IOException {
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("d:\\test.json");

		Object jsonObj = parser.parse(reader);
		System.out.println("messages = " + jsonObj);
		JSONObject jsonObject = (JSONObject) jsonObj;

		String name = (String) jsonObject.get("name");
		System.out.println("Name = " + name);

		Integer age = new Integer(((Long) jsonObject.get("age")).toString());
		System.out.println("Age = " + age);

		JSONArray messages = (JSONArray) jsonObject.get("messages");
		
	
		Iterator it = messages.iterator();
		while (it.hasNext()) {
			System.out.println("messages = " + it.next());
		}
		reader.close();
	}
}
