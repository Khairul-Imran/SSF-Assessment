package vttp.ssf.assessment.eventmanagement;



import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.ssf.assessment.eventmanagement.models.Event;

public class Utils {

    // Reads the JsonString, and converts it to a JsonArray.
    public static JsonArray toJson(String input) {
        JsonArray jsonArray = null;
        try (JsonReader jsonReader = Json.createReader(new StringReader(input))) {
            jsonArray = jsonReader.readArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static Event convertJsonObjectToEvent(JsonObject jsonObject) {
        Integer eventId = jsonObject.getInt("eventId");
        String eventName = jsonObject.getString("eventName");
        Integer eventSize = jsonObject.getInt("eventSize");
        Integer eventDate = jsonObject.getInt("eventDate");
        Integer participants = jsonObject.getInt("participants");

        Event event = new Event(eventId, eventName, eventSize, eventDate, participants);
        return event;
    }


    
}
