package com.katjarboe.archaeolog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.katjarboe.archaeolog.models.Artifact;
import com.katjarboe.archaeolog.repositories.ArtifactRepository;

@Service
public class ArtifactService {
	@Autowired
	private ArtifactRepository artifactRepo;
	
	//get all artifacts by dig
	public List<Artifact> allArtifacts(Long digId) {
		return artifactRepo.findByDigIdOrderByCreatedAtDesc(digId);
	}
	
	//create
	public Artifact createArtifact(Artifact artifact) {
		artifact.setId(null);
		return artifactRepo.save(artifact);
	}
	
	//get one
	public Artifact oneArtifact(Long id) {
		Optional<Artifact> optionalArtifact = artifactRepo.findById(id);
		if(optionalArtifact.isPresent()) {
			return optionalArtifact.get();
		} else {
			return null;
		}
	}
	
	//delete
	public void deleteArtifact(Long id) {
		artifactRepo.deleteById(id);
	}
}
