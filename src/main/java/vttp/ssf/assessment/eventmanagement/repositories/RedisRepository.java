package vttp.ssf.assessment.eventmanagement.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
		String body = Utils.serialiseEvent(event);
		String eventId = event.getEventId().toString();

		template.opsForList().rightPush(eventId, body);
	}


	// TODO: Task 3


	// TODO: Task 4
}
