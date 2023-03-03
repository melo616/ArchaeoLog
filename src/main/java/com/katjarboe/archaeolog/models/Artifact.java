package com.katjarboe.archaeolog.models;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="artifacts")
public class Artifact {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String category;
	
	private String material;
	
	private String description;
	
	@Lob
	private Blob imageFile;
	
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
	
	//many to one: creator; user who created dig will have full permissions
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="creator_id")
	private User artifactCreator;  
	
	//many to one: dig
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dig_id")
	private Dig dig; 
	
	//default constructor
	public Artifact() {}

	//getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public User getArtifactCreator() {
		return artifactCreator;
	}
	public void setArtifactCreator(User artifactCreator) {
		this.artifactCreator = artifactCreator;
	}
	public Dig getDig() {
		return dig;
	}
	public void setDig(Dig dig) {
		this.dig = dig;
	}
	public Blob getImageFile() {
		return imageFile;
	}
	public void setImageFile(Blob imageFile) {
		this.imageFile = imageFile;
	}	
}