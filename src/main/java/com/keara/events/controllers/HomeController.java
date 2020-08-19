package com.keara.events.controllers;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.keara.events.models.Comment;
import com.keara.events.models.Event;
import com.keara.events.models.User;
import com.keara.events.services.CommentService;
import com.keara.events.services.EventService;
import com.keara.events.services.UserService;

@Controller
public class HomeController {

	private final EventService eServ;
	private final UserService uServ;
	private final CommentService cServ;
	
	public HomeController(EventService eServ, UserService uServ, CommentService cServ) {
		this.eServ = eServ;
		this.uServ = uServ;
		this.cServ = cServ;
	}
	
	@GetMapping("/home")
	public String home(Model model, HttpSession session) {
		User userFromSession = (User) session.getAttribute("user");
		if(userFromSession == null) {
			return "redirect:/";
		}
		
		
		model.addAttribute("user", userFromSession);
		model.addAttribute("allEvents", eServ.getAll());
		model.addAttribute("newEvent", new Event());
		return "dashboard.jsp";
	}
	
	@PostMapping("/createEvent")
	public String create(@Valid @ModelAttribute("newEvent") Event newEvent, BindingResult result, HttpSession session, Model model) {
		User userFromSession = (User) session.getAttribute("user");
		ArrayList<String> errors = new ArrayList<String>();
		System.out.println(newEvent.getEventDate());
		System.out.println(newEvent.getEventDate());

		if(userFromSession == null) {
			return "redirect:/";
		}
		if(newEvent.getEventDate()==null) {
			errors.add("Start date is required");
		    result.rejectValue("eventDate", "required", "Event Date is required.");
		}
		else if(newEvent.getEventDate().before(new Date())) {
			errors.add("Start date must be in future");
		    result.rejectValue("eventDate", "future", "Start Date must be in the future.");
		}
//		if(errors.size() > 0) {	
//			flash.addFlashAttribute("errors", errors);
//			return "dashboard.jsp";
//		} 
		if(result.hasErrors()) {
			model.addAttribute("user", userFromSession);
			model.addAttribute("allEvents", eServ.getAll());
			return "dashboard.jsp";
		}
		 else {
			eServ.create(newEvent);
			return "redirect:/home";
		}
	}
	
	
	@GetMapping("/events/{id}/join")
	public String joinEvent(@PathVariable("id") Long id, HttpSession session, Model model) {
		User userFromSession = (User) session.getAttribute("user");
		if(userFromSession == null) {
			return "redirect:/";
		}
		Event toJoin = eServ.getOne(id);
		List<User> attendees = toJoin.getAttendees();
		attendees.add(userFromSession);
		toJoin.setAttendees(attendees);
		eServ.update(toJoin);
		return "redirect:/home";
	}
	
	
	@GetMapping("/events/{id}/details")
	public String eventDetails(@PathVariable("id") Long id, HttpSession session, Model model) {
		User userFromSession = (User) session.getAttribute("user");
		if(userFromSession == null) {
			return "redirect:/";
		}
		Event thisEvent = eServ.getOne(id);
		model.addAttribute("event", thisEvent);
		model.addAttribute("user", userFromSession);
		//needs a first comment to bind onto
		model.addAttribute("newComment", new Comment());
		model.addAttribute("allComments", thisEvent.getComments());
		return "eventDetails.jsp";
	}
	
	@PostMapping("/{id}/addComment")
	public String addComment(@Valid @ModelAttribute("newComment") Comment newComment, BindingResult result, HttpSession session, @PathVariable("id") Long id, Model model) {
		User userFromSession = (User) session.getAttribute("user");
		if(userFromSession == null) {
			return "redirect:/";
		}
		if(result.hasErrors()) {
			Event thisEvent = eServ.getOne(id);
			model.addAttribute("user", userFromSession);
			model.addAttribute("event", thisEvent);
			return "eventDetails.jsp";
		} else {
			cServ.create(newComment);
			return "redirect:/events/"+id+"/details";
		}
	
	}
	
	@RequestMapping("/events/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model, HttpSession session) {
        Event eventToUpdate = eServ.getOne(id);
		User userFromSession = (User) session.getAttribute("user");
        model.addAttribute("eventToUpdate", eventToUpdate);
        return "editEvent.jsp";
    }
    
	@RequestMapping(value="/events/{id}/update", method=RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("eventToUpdate") Event eventToUpdate, BindingResult result) {
        if (result.hasErrors()) {
            return "editEvent.jsp";
        } else {
            eServ.update(eventToUpdate);
            return "redirect:/home";
        }
    }
	
	@GetMapping("/events/{id}/cancel")
	public String cancelJoin(@PathVariable("id") Long id, HttpSession session, Model model) {
		User userFromSession = (User) session.getAttribute("user");
		if(userFromSession == null) {
			return "redirect:/";
		}
		Event toCancel = eServ.getOne(id);
		List<User> attendees = toCancel.getAttendees();
		attendees.remove(userFromSession);
		toCancel.setAttendees(attendees);
		eServ.update(toCancel);
		return "redirect:/home";
	}
	
	
	
	
	
}