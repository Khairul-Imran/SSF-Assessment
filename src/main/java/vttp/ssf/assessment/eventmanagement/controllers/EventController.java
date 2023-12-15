package vttp.ssf.assessment.eventmanagement.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.services.DatabaseService;

@Controller
@RequestMapping(path = {"/", "/events/listing"})
public class EventController {

	@Autowired DatabaseService databaseService;

	//TODO: Task 5
	@GetMapping
	public ModelAndView displayEvents(Model model) {
		ModelAndView mav = new ModelAndView("view0");
		List<Event> listOfEvents = null;

		try {
			listOfEvents = databaseService.readFile("events.json");
		} catch (IOException e) {
			e.printStackTrace();
		}

		mav.addObject("events", listOfEvents);

		return mav;
	}


}
