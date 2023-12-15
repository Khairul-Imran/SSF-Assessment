package vttp.ssf.assessment.eventmanagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;
import vttp.ssf.assessment.eventmanagement.services.DatabaseService;

@SpringBootApplication
public class EventmanagementApplication implements CommandLineRunner {

	@Autowired 
	DatabaseService databaseService;

	@Autowired
	RedisRepository redisRepository;

	public static void main(String[] args) {
		SpringApplication.run(EventmanagementApplication.class, args);
	}

	// TODO: Task 1 (Done)

	@Override
	public void run(String... args) throws Exception {

		// Might have to include the path.
		String fileName = "events.json";

		List<Event> listOfEvents = databaseService.readFile(fileName);
		
		// TODO: Task 2 (Done)
		// Need to loop through and save each event individually.
		// for (Event event : listOfEvents) {
		// 	System.out.println("Saving a new event: " + event);
		// 	redisRepository.saveRecord(event);
		// }

		listOfEvents.stream().forEach(event -> {redisRepository.saveRecord(event);
		});

		// TODO: Task 3 (Done)
		Long numberOfEvents = redisRepository.getNumberOfEvents();
		System.out.println("Number of events available:" + numberOfEvents);
		
		// TODO: Task 4 (Done)
		Event testEventIndex = redisRepository.getEvent(0);
		System.out.println("The event at index 0 is: " + testEventIndex);

	}
	

}
