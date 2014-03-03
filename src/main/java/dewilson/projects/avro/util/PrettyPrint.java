package dewilson.projects.avro.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

public class PrettyPrint {

	/** thread safe according to docs **/
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public static String prettyPrintJson(String jsonString) {
		return gson.toJson(new JsonParser().parse(jsonString));
	}

}
