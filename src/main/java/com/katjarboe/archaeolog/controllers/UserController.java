package com.katjarboe.archaeolog.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.katjarboe.archaeolog.models.LoginUser;
import com.katjarboe.archaeolog.models.User;
import com.katjarboe.archaeolog.services.UserService;


@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index(Model model) {
	//binding empty user/loginuser objects to JSP to hold form input
	model.addAttribute("newUser", new User());
	model.addAttribute("newLogin", new LoginUser());
	return "logreg.jsp";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser")User newUser, BindingResult result, Model model, HttpSession session) {
		User registeredUser = userService.register(newUser, result);
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "logreg.jsp";
		}
		//if no errors, set in session (ie login) and redirect to home
		session.setAttribute("userId", registeredUser.getId());
		//to render userName on home
        session.setAttribute("userName", registeredUser.getUserName()); 
		return "redirect:/home";
	}
	
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
         User loginUser = userService.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "logreg.jsp";
        }
        //if no errors, get user info and store in session
        session.setAttribute("userId", loginUser.getId());
        session.setAttribute("userName", loginUser.getUserName()); 
        return "redirect:/home";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
}
