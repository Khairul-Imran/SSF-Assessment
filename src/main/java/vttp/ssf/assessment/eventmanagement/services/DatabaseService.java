package vttp.ssf.assessment.eventmanagement.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import vttp.ssf.assessment.eventmanagement.Utils;
import vttp.ssf.assessment.eventmanagement.models.Event;

@Service
public class DatabaseService {
    
    // TODO: Task 1 (Done)
    public List<Event> readFile(String fileName) throws IOException {
        
        List<Event> listOfEvents = new ArrayList<>();

        File file = new File(fileName);

        if (!file.exists()) {
			System.err.print("File not found");
			System.exit(1);
		}

        // File -> read file contents and convert it to a string (print)
        // -> use toJson to convert to JsonArray
        // -> iterate through the JsonArray and convert each obj to event.

        // Reading the json file, and converting the contents to a string.
		InputStream is = new FileInputStream(file);
		StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			String line = "";
			while ((line = br.readLine()) != null) {
				resultStringBuilder.append(line);
			}
		}

        String eventDataInString = resultStringBuilder.toString();

        JsonArray jsonArray = Utils.toJson(eventDataInString);

        for (JsonValue jsonObject : jsonArray) {
            JsonObject currentEvent = (JsonObject) jsonObject;
            System.out.println("Current event: " + currentEvent);

            Event event = Utils.convertJsonObjectToEvent(currentEvent);

            listOfEvents.add(event);
        }
        System.out.println("This is our list of events: " + listOfEvents);

        return listOfEvents;
    }
}
