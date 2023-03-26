package com.katjarboe.archaeolog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.katjarboe.archaeolog.models.Artifact;

@Repository
public interface ArtifactRepository extends JpaRepository<Artifact, Long>{
	List<Artifact> findByDigIdOrderByCreatedAtDesc(Long digId);
	void deleteByDigId(Long digId);
}
