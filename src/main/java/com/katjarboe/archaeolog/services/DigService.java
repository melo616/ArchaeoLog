package com.katjarboe.archaeolog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.katjarboe.archaeolog.models.Dig;
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
//	public Top createTop(Top top) {
//		return topRepo.save(top);
//	}
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
