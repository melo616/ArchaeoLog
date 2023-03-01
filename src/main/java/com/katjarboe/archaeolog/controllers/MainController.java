package com.katjarboe.archaeolog.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.katjarboe.archaeolog.models.Dig;
import com.katjarboe.archaeolog.models.User;
import com.katjarboe.archaeolog.repositories.UserRepository;
import com.katjarboe.archaeolog.services.DigService;
import com.katjarboe.archaeolog.services.UserService;


@Controller
public class MainController {
	@Autowired
	private UserService userService;
	@Autowired
	private DigService digService;
//	@Autowired
//	private ArtifactService artifactService;
	@Autowired
	private UserRepository userRepo;
	
	//homepage: utilizes get all
	@GetMapping("/home")
	public String home(HttpSession session, Model model) {
		if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		Long userId = (Long) session.getAttribute("userId");
	   	model.addAttribute("loggedInUser", userService.oneUser(userId));
    	model.addAttribute("digList", digService.allDigs());
		return "home.jsp";
	}
	
	//create dig: display form
	@GetMapping("/digs/new")
	public String displayNewDigForm(HttpSession session, @ModelAttribute("newDig") Dig dig) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		return "createDig.jsp";
	}
	
	//create dig: post form
	@PostMapping("/digs/new")
	public String processNewDigForm(@Valid @ModelAttribute("newDig") Dig newDig, HttpSession session, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "newDig.jsp";
		} else {
		Long userId = (Long) session.getAttribute("userId");
		User loggedInUser = userService.oneUser(userId);
		digService.createDig(loggedInUser, newDig);
		return "redirect:/home";
		}
	}
	
	//view one dig
	@GetMapping("/digs/view/{id}")
	public String viewOneDig(@PathVariable("id")Long id, Model model, HttpSession session) {
		Dig dig = digService.oneDig(id);
		Long userId = (Long) session.getAttribute("userId");
		User loggedInUser = userService.oneUser(userId);
		model.addAttribute("dig", dig);
		model.addAttribute("loggedInUser", loggedInUser);
		return "oneDig.jsp";
	}
	
	//many to many: add participant
	@PutMapping("/digs/addUser/{id}")
	public String getDigAndAddUser(@PathVariable("id") Long id, @RequestParam(value="email") String email) {
		Dig dig = digService.oneDig(id);
		User newParticipant = userRepo.findByEmail(email).get();
		digService.addDigParticipant(newParticipant, dig);
		return "redirect:/digs/view/{id}";
	}
}

