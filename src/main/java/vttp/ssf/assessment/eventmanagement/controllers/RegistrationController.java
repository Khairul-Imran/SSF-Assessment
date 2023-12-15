package vttp.ssf.assessment.eventmanagement.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.models.EventRegistration;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;

@Controller
@RequestMapping
public class RegistrationController {
    
    @Autowired RedisRepository redisRepository;

    // TODO: Task 6
    @GetMapping(path="/events/register/{eventId}")
    public ModelAndView register(@PathVariable int eventId) {
        ModelAndView mav = new ModelAndView("view1");

        EventRegistration registration = new EventRegistration();
        // Setting the event id directly, to track for below.
        registration.setEventId(eventId);

        Event event = redisRepository.getEvent(eventId - 1);

        // Setting eventname and eventdate, to track for below too.
        registration.setEventName(event.getEventName());
        registration.setEventDate(event.getEventDate());

        mav.addObject("event", event);
        mav.addObject("newregistration", registration);

        return mav;
    }

    // TODO: Task 7
    @PostMapping(path = "registration/register")
    public String processRegistration(@Valid @ModelAttribute("newregistration") EventRegistration registrationForm, BindingResult result, Model model) {

        // If failure, stays at the same page.
        if (result.hasErrors()) { // For basic model validation.
            return "view1";
        }

        // Need to do additional server side validation.
        // Finding user's age.
        Date userBirthday = registrationForm.getBirthDate();
        LocalDate birthday = userBirthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Date currentDate = new Date();
        LocalDate currentDateinLocalDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long age = ChronoUnit.YEARS.between(birthday, currentDateinLocalDate); // Age

        // Find the difference between tickets requested vs tickets avail.
        Integer userNumberOfTicketsRequested = registrationForm.getNumberOfTicketsRequested();
        Event event = redisRepository.getEvent(registrationForm.getEventId());
        Integer numberOfTicketsAvailable = event.getParticipants();
        Integer differenceInNumberOfTickets = numberOfTicketsAvailable - userNumberOfTicketsRequested; // Tickets difference.

        // Error after server validation.
        if (age < 21) {
            String error = "You do not meet the minimum required age of 21.";
            model.addAttribute("error", error);
            return "view3";
        } else if (differenceInNumberOfTickets < 0) { 
            String error = "Your request for tickets exceeded the event size.";
            model.addAttribute("error", error);
            return "view3";
        }

        //If success
        model.addAttribute("savedRegistration", registrationForm);

        // registrationForm.getEventName();
        // registrationForm.getEventDate();

        return "view2";
    }
}
