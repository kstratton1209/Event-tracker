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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "events")
public class Event {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=3, message="Event name must be 3 characters or longer!")
	@Size(max=100, message="Event name must be 100 characters or shorter!")
	private String name;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date eventDate;
	
	@Size(min=5, message="Location must be 5 characters or longer")
	@Size(max=1000, message="Location must be shorter than 1000 characters!")
	private String location;
	
	
	@Size(min=2, message="State must be 2 characters or longer")
	@Size(max=1000, message="State must be shorter than 1000 characters!")
	private String state;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
	private User user;
	
	@ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(
	        name = "event_attendees", 
	        joinColumns = @JoinColumn(name = "event_id"), 
	        inverseJoinColumns = @JoinColumn(name = "user_id")
	    )
	    private List<User> attendees;
	
	@OneToMany(mappedBy="event", fetch = FetchType.LAZY)
    private List<Comment> comments;
	
	@Column(updatable=false)
    private Date createdAt;
	
    private Date updatedAt;
   
    
	public Event(String name, String location, String state, Date eventDate) {
		this.name = name;
		this.location = location;
		this.state = state;
		this.eventDate = eventDate;
	}
	
	public Event() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	public List<User> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<User> attendees) {
		this.attendees = attendees;
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
	
	

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Boolean isAttending(Long id) {
		// if user is in attendes 
    	for(User u:attendees) {
    		if (u.getId() == id) {
    			return true;
    		}
    	}
    	return false;
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
