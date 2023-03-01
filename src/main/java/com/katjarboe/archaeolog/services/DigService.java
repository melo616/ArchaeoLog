package com.katjarboe.archaeolog.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.katjarboe.archaeolog.models.Dig;
import com.katjarboe.archaeolog.models.User;
import com.katjarboe.archaeolog.repositories.DigRepository;

@Service
public class DigService {
	@Autowired
	private DigRepository digRepo;
	
	//get all tables
	public List<Dig> allDigs() {
		return digRepo.findByOrderByCreatedAtDesc();
	}
	
	//create
	public Dig createDig(User user, Dig dig) {
		digRepo.save(dig);
		List <User> participants = new ArrayList<>();
		dig.setDigParticipants(participants);
		dig.getDigParticipants().add(user);
		return digRepo.save(dig);
	}
	//display one dig
	public Dig oneDig(Long id) {
		Optional<Dig> optionalDig = digRepo.findById(id);
		if(optionalDig.isPresent()) {
			return optionalDig.get();
		} else {
			return null;
		}
	}
	//add participant
	public Dig addDigParticipant(User newParticipant, Dig dig) {
		dig.getDigParticipants().add(newParticipant);
		return digRepo.save(dig);
	}
	//find one: used for update
//	public Top oneTop(Long id) {
//		Optional<Top> optionalTop = topRepo.findById(id);
//		if(optionalTop.isPresent()) {
//			return optionalTop.get();
//		}else {
//			return null;
//		}
//	}
	//update
//	public Top editTop(Top top) {
//		return topRepo.save(top);
//	}
	//delete
//	public void deleteTop(Long id) {
//		topRepo.deleteById(id);
//	}
}
