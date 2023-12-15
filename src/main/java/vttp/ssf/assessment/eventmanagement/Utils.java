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
        Long eventDate = Long.valueOf(jsonObject.getInt("eventDate"));
        Integer participants = jsonObject.getInt("participants");

        Event event = new Event(eventId, eventName, eventSize, eventDate, participants);
        return event;
    }

    // Event Obj -> String
    public static String serialiseEvent(Event event) {
        String eventId = event.getEventId().toString();
        String eventName = event.getEventName().toString();
        String eventSize = event.getEventSize().toString();
        String  eventDate = event.getEventDate().toString();
        String  participants = event.getParticipants().toString();

        // Building your string of an event to be inserted to redis.
        StringBuilder sb = new StringBuilder()
                .append("eventId:"+eventId+",")
                .append("eventName:"+eventName+",")
                .append("eventSize:"+eventSize+",")
                .append("eventDate:"+eventDate+",")
                .append("participants:"+participants);
        String body = sb.toString();
        return body;
    }


    
}
