package com.katjarboe.archaeolog.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
