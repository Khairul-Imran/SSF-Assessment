package vttp.ssf.assessment.eventmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.ssf.assessment.eventmanagement.services.DatabaseService;

@SpringBootApplication
public class EventmanagementApplication implements CommandLineRunner {

	@Autowired 
	DatabaseService databaseService;

	public static void main(String[] args) {
		SpringApplication.run(EventmanagementApplication.class, args);
	}

	// TODO: Task 1 (Done)

	@Override
	public void run(String... args) throws Exception {

		// Might have to include the path.
		String fileName = "events.json";

		databaseService.readFile(fileName);
		
	}
	

}
