package com.katjarboe.archaeolog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.katjarboe.archaeolog.models.Dig;

@Repository
public interface DigRepository extends JpaRepository<Dig, Long>{
	List<Dig> findByOrderByCreatedAtDesc();
}