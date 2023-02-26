package com.katjarboe.archaeolog.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="digs")
public class Dig {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="You must give this dig a name.")
	@Size(min=2, max=50, message="Dig name must be between 2-50 characters.")
	private String digName;
	
	@NotNull
//	Look up date format
	private Date startDate;
	
	private Date endDate;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	//many to one: creator
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="creator_id")
	private User digCreator;
	
	//one to many: artifacts
	@OneToMany(mappedBy="XXX", fetch=FetchType.LAZY)
    private List<Artifact> artifacts;
    
	
	//many to many: participants
	
	//default constructor
	public Dig() {}
	
	//getters and setters
}
