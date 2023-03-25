package com.katjarboe.archaeolog.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.katjarboe.archaeolog.models.Artifact;
import com.katjarboe.archaeolog.models.Dig;
import com.katjarboe.archaeolog.models.User;
import com.katjarboe.archaeolog.services.ArtifactService;
import com.katjarboe.archaeolog.services.DigService;
import com.katjarboe.archaeolog.services.UserService;


@Controller
public class MainController {
	@Autowired
	private UserService userService;
	@Autowired
	private DigService digService;
	@Autowired
	private ArtifactService artifactService;
	
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
		List<Artifact> artifactList = artifactService.allArtifacts(id);
		model.addAttribute("dig", dig);
		model.addAttribute("loggedInUser", loggedInUser);
		model.addAttribute("artifactList", artifactList);
		return "oneDig.jsp";
	}
	
	//many to many: add participant
	@PutMapping("/digs/addUser/{id}")
	public String getDigAndAddUser(@PathVariable("id") Long id, @RequestParam(value="email") String email, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		Dig dig = digService.oneDig(id);
		User newParticipant = userService.oneUserByEmail(email);
		if (newParticipant == null) {
			redirectAttributes.addFlashAttribute("error", "This email address is not registered.");
			model.addAttribute("dig", dig);
			return "redirect:/digs/view/{id}";
		} else if (dig.getDigParticipants().contains(newParticipant)) {
			redirectAttributes.addFlashAttribute("error", "This user is already a participant of this dig.");
			model.addAttribute("dig", dig);
			return "redirect:/digs/view/{id}";
		} else {
			digService.addDigParticipant(newParticipant, dig);
			return "redirect:/digs/view/{id}";
		}
	}
	
	//remove participant from dig
	@PutMapping("/digs/{digId}/removeUser/{userId}")
	public String getDigAndRemoveUser(@PathVariable("digId")Long digId, @PathVariable("userId") Long userId) {
		Dig dig = digService.oneDig(digId);
		User selectedParticipant = userService.oneUser(userId);
		digService.removeDigParticipant(selectedParticipant, dig);
		return "redirect:/digs/view/{digId}";
	}
	
	//create artifact: form
	@GetMapping("/digs/{id}/newArtifact")
	public String displayNewArtifactForm(@PathVariable("id")Long id, HttpSession session, @ModelAttribute("newArtifact")Artifact artifact, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Dig dig = digService.oneDig(id);
		model.addAttribute("dig", dig);
		return "createArtifact.jsp";
	}
	
	//create artifact: process
	@PostMapping("/digs/{id}/newArtifact")
	public String processNewArtifactForm(@Valid @ModelAttribute("newArtifact")Artifact newArtifact, BindingResult result, @PathVariable("id")Long id, Model model, HttpSession session) {
		if (result.hasErrors()) {
			Dig dig = digService.oneDig(id);
			model.addAttribute("dig", dig);
			return "createArtifact.jsp";
		} else {
			artifactService.createArtifact(newArtifact);
			return "redirect:/digs/view/{id}";
		}
	}
	
	//get all: artifacts by dig
	@GetMapping("/digs/{id}/allArtifacts")
	public String allArtifacts(@PathVariable("id")Long id, Model model, HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		} else {
			Dig dig = digService.oneDig(id);
			Long userId = (Long) session.getAttribute("userId");
			User loggedInUser = userService.oneUser(userId);
			List<Artifact> artifactList = artifactService.allArtifacts(id);
			model.addAttribute("dig", dig);
			model.addAttribute("loggedInUser", loggedInUser);
			model.addAttribute("artifactList", artifactList);
			return "allArtifacts.jsp";
		}
	}
	
	//get one artifact
	@GetMapping("/digs/{digId}/artifacts/{artifactId}")
	public String oneArtifact(@PathVariable("digId")Long digId, @PathVariable("artifactId")Long artifactId, Model model, HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		} else {
			Dig dig = digService.oneDig(digId);
			Artifact artifact = artifactService.oneArtifact(artifactId);
			model.addAttribute("dig", dig);
			model.addAttribute("artifact", artifact);
		return "oneArtifact.jsp";
		}
	}
	
	//delete artifact
	@DeleteMapping("/digs/{digId}/artifacts/delete/{artifactId}")
	public String deleteArtifact(@PathVariable("artifactId")Long artifactId, @PathVariable("digId")Long digId) {
		artifactService.deleteArtifact(artifactId);
		return "redirect:/digs/view/{digId}";
	}
	
	//update artifact: display form
	@GetMapping("/digs/{digId}/artifacts/edit/{artifactId}")
	public String updateArtifactDisplayForm(@PathVariable("digId")Long digId, @PathVariable("artifactId")Long artifactId, Model model) {
		Dig dig = digService.oneDig(digId);
		Artifact artifact = artifactService.oneArtifact(artifactId);
		model.addAttribute("artifact", artifact);
		model.addAttribute("dig", dig);
		return "editArtifact.jsp";
	}
}

