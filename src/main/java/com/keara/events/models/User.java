package com.keara.events.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@Entity
@Table(name="users")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=3, message="First name must be 3 characters or longer!")
	@Size(max=200, message="First name must be shorter than 200 characters!")
	private String firstName;
	
	@Size(min=3, message="Last name must be 3 characters or longer!")
	@Size(max=200, message="Last name must be shorter than 200 characters!")
	private String lastName;
	
	@Size(min=1, message="Email is required!")
	@Size(max=200, message="Email must be shorter than 200 characters!")
	@Email(message="Invalid Email!")
	private String email;
	
	@Size(min=3, message="Location must be 3 characters or longer!")
	@Size(max=200, message="Location must be shorter than 200 characters!")
	private String location;
	
	@Size(min=1, message="State is mandatory!")
	private String state;
	
	
	@Size(min=8, message="Password must be 8 characters or longer!")
	@Size(max=200, message="Password must be shorter than 200 characters!")
	private String password;
	
    @Transient
	private String confirm;
	
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Event> myEvents;
    
    
    @OneToMany(mappedBy="commenter", fetch = FetchType.LAZY)
    private List<Comment> myComments;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "event_attendees", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> attendees;
    
	@Column(updatable=false)
    private Date createdAt;
	
    private Date updatedAt;

	
	
	public User(
			String firstName,
			String lastName,
			String email,
			String location,
			String state,
			String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.location = location;
		this.state = state;
		this.password = password;
	}



	public User() {}

	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
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



	public List<Event> getMyEvents() {
		return myEvents;
	}



	public void setMyEvents(List<Event> myEvents) {
		this.myEvents = myEvents;
	}


	

	public List<Event> getAttendees() {
		return attendees;
	}



	public void setAttendees(List<Event> attendees) {
		this.attendees = attendees;
	}

	
	

	public List<Comment> getMyComments() {
		return myComments;
	}



	public void setMyComments(List<Comment> myComments) {
		this.myComments = myComments;
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

	

	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    
    @PreUpdate
    protected void onUpdate(){
    	this.updatedAt = new Date();
    }
    
    
	
}