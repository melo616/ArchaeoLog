package com.katjarboe.archaeolog.models;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="Username is required!")
    @Size(min=3, max=30, message="Username must be between 3 and 30 characters")
    private String userName;
    
    @NotEmpty(message="Email is required!")
    @Email(message="Please enter a valid email!")
    private String email;
    
    @NotEmpty(message="Password is required!")
    @Size(min=8, message="Password must be between 8 and 128 characters")
    private String password;
    
    @Transient //annotation to prevent db storage
    @NotEmpty(message="Confirm Password is required!")
    @Size(min=8, max=128, message="Confirm Password must be between 8 and 128 characters")
    private String confirm;
    
    //one to many
    //user to dig
    @OneToMany(mappedBy="digCreator", fetch=FetchType.LAZY)
    private List<Dig> createdDigs;
    
    //one to many
    //user to artifacts
    @OneToMany(mappedBy="artifactCreator", fetch=FetchType.LAZY)
    private List<Artifact> createdArtifacts;
    
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "dig_participants",
		joinColumns = @JoinColumn(name="dig_id"),
		inverseJoinColumns=@JoinColumn(name="participant_id")
	)
	private List<Dig> digs;
    
    //default constructor
    public User() {}

    //getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public List<Dig> getCreatedDigs() {
		return createdDigs;
	}

	public void setCreatedDigs(List<Dig> createdDigs) {
		this.createdDigs = createdDigs;
	}

	public List<Artifact> getCreatedArtifacts() {
		return createdArtifacts;
	}

	public void setCreatedArtifacts(List<Artifact> createdArtifacts) {
		this.createdArtifacts = createdArtifacts;
	}

	public List<Dig> getDigs() {
		return digs;
	}

	public void setDigs(List<Dig> digs) {
		this.digs = digs;
	}
}
