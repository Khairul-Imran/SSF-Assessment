package vttp.ssf.assessment.eventmanagement.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import vttp.ssf.assessment.eventmanagement.Utils;
import vttp.ssf.assessment.eventmanagement.models.Event;

@Repository
public class RedisRepository {

	@Autowired @Qualifier("eventRedisTemplate")
    private RedisTemplate<String, String> template;

	// TODO: Task 2 (Done)
	public void saveRecord(Event event) {
		// Save the list of events objects to Redis....
		ListOperations<String, String> list = template.opsForList();
		String body = Utils.serialiseEvent(event);
		// String eventId = event.getEventId().toString();

		list.rightPush("Events", body);
	}

	// TODO: Task 3 (Done)
	public Long getNumberOfEvents() {
		return template.opsForList().size("Events");
	}

	// TODO: Task 4 (Done)
	public Event getEvent(Integer index) {
		// Need to convert from a string to event.
		String eventInString = template.opsForList().index("Events", index);

		Event event = new Event();

		String[] tokens = eventInString.split(",");
        for (String s : tokens) {

            String[] keyValue = s.split(":", 2);

            switch (keyValue[0]) {
                case "eventId": event.setEventId(Integer.parseInt(keyValue[1])); break;
                case "eventName": event.setEventName(keyValue[1]); break;
                case "eventSize": event.setEventSize(Integer.parseInt(keyValue[1])); break;
                case "eventDate": event.setEventDate(Long.parseLong(keyValue[1])); break;
                case "participants": event.setParticipants(Integer.parseInt(keyValue[1])); break;
            }
        }
		return event;
	}
}
