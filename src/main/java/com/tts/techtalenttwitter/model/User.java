package com.tts.techtalenttwitter.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//But we are going to store User in a database..
//and in order to wire up to the database, we have to
//annotate the user with an annotation from JPA (Java Persistence API)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity 
public class User {	
	//Label this as our primary key.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;
	
	@Email(message = "Please provide a valid email")
	@NotEmpty(message = "Please provide an email")
	private String email;
	
	@NotEmpty(message = "Please provide a username")
	@Length(min=3, message = "Your username must have at least 3 characters")
	@Length(max=15, message = "Your username cannot have more than 15 characters")
	@Pattern(regexp="[^\\s]+", message = "Your username cannot contain spaces")
	private String username;
	
	@NotEmpty(message = "Please provide a password")
	@Length(min=5, message = "Your password must have at least 5 characters")
	private String password;
	
	@NotEmpty(message = "Please provide your first name")
	private String firstName;
	
	@NotEmpty(message = "Please provide your last name")
	private String lastName;
	
	private int active; //1 means account is enabled.
	
	@CreationTimestamp 
	private Date createdAt;	
	
	//This "User" class is going to be mapped to a database entry....
	//there is no good way to store an unlimited number of roles to
	//a table entry....
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="user_role", joinColumns = @JoinColumn(name = "user_id"), 
	           inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	@ManyToMany(cascade = CascadeType.ALL) 
	@JoinTable(name="user_followers", joinColumns = @JoinColumn(name="user_id"),
	           inverseJoinColumns = @JoinColumn(name = "follower_id"))
	private List<User> followers; //Who is following me?
	
	
	@ManyToMany(mappedBy="followers")
	private List<User> following; //Who am I following?
	
	
	
	
	
}