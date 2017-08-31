package mongoDB;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoDB {

	public  MongoDB() throws UnknownHostException {
		// TODO Auto-generated constructor stub
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
	}
	
	public static DB dB(MongoClient mongoClient){
		DB database = mongoClient.getDB("TheDatabaseName");
		return database;
	}
	
	public static DBCollection getColDb(DB database){
		DBCollection collection = database.getCollection("TheCollectionName");
		return collection;
	}
	
	public static void exem() throws UnknownHostException{
		
		List<Integer> books = Arrays.asList(27464, 747854);
		DBObject person = new BasicDBObject("_id", "jo")
		                            .append("name", "Jo Bloggs")
		                            .append("address", new BasicDBObject("street", "123 Fake St")
		                                                         .append("city", "Faketon")
		                                                         .append("state", "MA")
		                                                         .append("zip", 12345))
		                            .append("books", books);
		
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		DB database = mongoClient.getDB("Examples");
		DBCollection collection = database.getCollection("people");
		    
		collection.insert(person);
	}

}
